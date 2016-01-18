package WorkForTest

import java.io.PrintWriter

import scala.collection.mutable.ArrayBuffer
import scala.io.Source

/**
 * Created by yang on 16-1-6.
 */
object GetIdAndRange {
  def main(args: Array[String]) {
    val source = Source.fromFile("/home/yang/Desktop/tmp/test/all6", "GBK")
    val lineIter = source.getLines
    val jack = 6
    val finaledge: ArrayBuffer[finalEdge] = new ArrayBuffer[finalEdge]()
    val outfile = new PrintWriter("/home/yang/Desktop/tmp/test/finalEdge.txt")

    lineIter.foreach(line => {
      val pair = line.split(" ")
      finaledge.append(new finalEdge(pair(1).toInt,pair(0).toInt,pair(3).toInt))
      finaledge.append(new finalEdge(jack,pair(1).toInt,pair(3).toInt))
    })

    for (i <- 0 until finaledge.length) {
      println(finaledge(i).left + " " + finaledge(i).right + " " + finaledge(i).range)
      outfile.println(finaledge(i).left + "\t" + finaledge(i).right + "\t" + finaledge(i).range)
    }
    println(finaledge.length)
  }
}

class finalEdge(){
  var left:Int = 0
  var right:Int = 0
  var range:Int = 99

  def this(l:Int, r:Int, ran:Int){
    this()
    this.left = l
    this.right = r
    this.range = ran
  }
}
