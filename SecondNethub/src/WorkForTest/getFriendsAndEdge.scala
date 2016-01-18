package WorkForTest

import java.io.{File, PrintWriter}

import scala.collection.mutable.ArrayBuffer
import scala.io.Source

/**
 * Created by yang on 16-1-4.
 */
object getFriendsAndEdge {
  def main(args: Array[String]) {

    val str = "/home/yang/Desktop/GraphData/Friends.txt"
    val source = Source.fromFile(str, "GBK")
    val lineIter = source.getLines
    val AllId: ArrayBuffer[Int] = new ArrayBuffer[Int]()
    val AllEdge: ArrayBuffer[Edges] = new ArrayBuffer[Edges]()
    val targ: Int = 6
    val outedge = new PrintWriter(new File("/home/yang/Desktop/tmp/EdgeOf-" + targ))
    val outid = new PrintWriter(new File("/home/yang/Desktop/tmp/FriendsOf" + targ ))

    lineIter.foreach(line => {
      val pair = line.split("\t")
      if (pair(0).toInt == targ) {
        val id = pair(0).toInt
        println("ID: " + id)
        val friend = pair(1).split(",")
        for (i <- 1 until friend.length) {
          val m = friend(i).split(" ")
          val real = m(1).toInt
          val edge = new Edges(targ, real)
          AllEdge.append(edge)
          AllId.append(real)
          print(real + " ")
        }
        println("\n")
        println("-----------------------")
        println("length:" + friend.length)
      }
    })

    val allids = AllId.distinct
    val alledges = AllEdge.distinct

    for(i <- 0 until allids.length){
      val m: Int = allids(i)
      outid.println(m)
    }
    for(i <- 0 until alledges.length){
      val a = alledges(i).left
      val b = alledges(i).right
      outedge.println(a + " " + b)
    }
    outid.close()
    outedge.close()

  }

}