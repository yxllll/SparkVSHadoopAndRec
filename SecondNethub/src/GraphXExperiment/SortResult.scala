package TestPackage

import java.io.PrintWriter

import scala.collection.mutable.ArrayBuffer
import scala.io.Source

/**
 * Created by yang on 16-1-8.
 */
object SortResult {

  def main(args: Array[String]) {
    val outfile = new PrintWriter("/home/yang/Desktop/PageRankSorted.txt")
    val source = Source.fromFile("/home/yang/Desktop/PageRank.txt")
    val lineIter = source.getLines()
    val all = new ArrayBuffer[Node]()
    lineIter.foreach(line => {
      val str1 = line.split("(")
      val str2 = str1(0).split(")")
      val pair = str2(0).split(",")
      val n = pair(0).toString
      val p = pair(1).toDouble
      all.append(new Node(n, p))
    })
    all.sortBy(node => node.pr)
    for (i <- 0 until all.length) {
      outfile.println(all(i).name + "\t" + all(i).pr)
    }
  }

  class Node() {
    var name: String = ""
    var pr: Double = 0.0

    def this(n: String, p: Double) {
      this()
      this.name = n
      this.pr = p
    }
  }

}