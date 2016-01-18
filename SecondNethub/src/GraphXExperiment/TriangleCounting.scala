package GraphXExperiment

import java.io.PrintWriter

import org.apache.spark.SparkContext
import org.apache.spark.graphx.{PartitionStrategy, GraphLoader}

/**
 * Created by yang on 16-1-10.
 */
object TriangleCounting {
  def main(args: Array[String]) {

    val outfile = new PrintWriter("/home/yang/data/graph/soctc.txt")

    val startTime = System.currentTimeMillis()

    //val conf = new SparkConf().setAppName("Triangle Count")
    val sc = new SparkContext("local[1]", "triangle")
    // Load the edges in canonical order and partition the graph for triangle count
    val graph = GraphLoader.edgeListFile(sc, "/home/yang/data/graph/soc.txt", true).partitionBy(PartitionStrategy.RandomVertexCut)
    // Find the triangle count for each vertex
    val triCounts = graph.triangleCount().vertices
    // Join the triangle counts with the usernames
    val users = sc.textFile("/home/yang/data/graph/socid.txt").map { line =>
      val fields = line
      (fields.toLong, fields)
    }
    val triCountByUsername = users.join(triCounts).map { case (id, (username, tc)) =>
      (username, tc)
    }
    // Print the result
    println(triCountByUsername.collect().mkString("\n"))

    val endTime = System.currentTimeMillis()
    val runtime = endTime - startTime

    val tcount = triCountByUsername.collect().sorted

    outfile.println(tcount.mkString("\n"))
    println("Run Time: " + runtime)

  }
}
