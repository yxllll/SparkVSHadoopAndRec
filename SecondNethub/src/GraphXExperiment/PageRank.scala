package TestPackage

import java.io.PrintWriter

import org.apache.spark.SparkContext
import org.apache.spark.graphx.{PartitionStrategy, GraphLoader}

/**
 * Created by yang on 16-1-8.
 */
object PageRank {
  def main(args: Array[String]) {

    val outfile = new PrintWriter("/home/yang/data/graph/socout.txt")


    val sc = new SparkContext("local[1]", "PageRank")
    // Load the edges as a graph
    val graph = GraphLoader.edgeListFile(sc, "/home/yang/data/graph/soc.txt")
    // Run PageRank
    val startTime = System.currentTimeMillis()
    val ranks = graph.pageRank(0.0001).vertices
    // Join the ranks with the usernames
//    val users = sc.textFile("/home/yang/data/graph/p2pid.txt").map { line =>
//      val fields = line.split("\t")
//      (fields(0).toLong, fields(1))
//    }

    val users = sc.textFile("/home/yang/data/graph/socid.txt").map { line =>
      val fields = line
      (fields.toLong, fields)
    }

    val ranksByUsername = users.join(ranks).map {
      case (id, (username, rank)) => (username, rank)
    }
    val endTime = System.currentTimeMillis()
    val runtime = endTime - startTime
    // Print the result
    println(ranksByUsername.collect().mkString("\n"))
    outfile.println(ranksByUsername.collect().mkString("\n"))
    println("Run Time: " + runtime)

  }
}
