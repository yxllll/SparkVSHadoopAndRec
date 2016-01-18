package SocialRelationMining

import java.io.{File, FileNotFoundException, IOException, PrintWriter}

import scala.collection.mutable.ArrayBuffer
import scala.io.Source

/**
 * Created by yang on 16-1-4.
 */
object GetAllConnection {
    def main(args: Array[String]) {

      try {
        val str = "/home/yang/Desktop/GraphData/Friends.txt"
        val source = Source.fromFile(str, "GBK")
        val lineIter = source.getLines
        val AllId: ArrayBuffer[Int] = new ArrayBuffer[Int]()
        val AllEdge: ArrayBuffer[Edges] = new ArrayBuffer[Edges]()
        val targ: Int = 505
        val outedge = new PrintWriter(new File("/home/yang/Desktop/tmp/AllEdgeOf-" + targ))
        val outid = new PrintWriter(new File("/home/yang/Desktop/tmp/SecFriendsOf" + targ))

        lineIter.foreach(line => {
          val pair = line.split("\t")
          if (pair(0).toInt == targ) {
            if(pair.length == 2){
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
              println("Friends number: " + friend.length)
            }
          }
        })

        var allids = AllId.distinct
        val firstfriend = allids.length
        var alledges = AllEdge.distinct
        var secFriendNum: Int = 0

        for(j <- 0 until allids.length){
          val str2 = "/home/yang/Desktop/GraphData/Friends.txt"
          val source = Source.fromFile(str2, "GBK")
          val lines = source.getLines
          lines.foreach(line => {
            val pair = line.split("\t")
            if (pair(0).toInt == allids(j)) {

              if(pair.length == 2){
                val id = pair(0).toInt
                println("ID: " + id)
                val friend = pair(1).split(",")
                for (i <- 1 until friend.length) {
                  val m = friend(i).split(" ")
                  val real = m(1).toInt
                  val edge = new Edges(allids(j), real)
                  alledges.append(edge)
                  allids.append(real)
                  secFriendNum = secFriendNum + 1
                }
                println("Friends number: " + friend.length)
              }

            }
          })

        }

        allids = allids.distinct
        alledges = alledges.distinct

        for (i <- 0 until allids.length) {
          val m: Int = allids(i)
          outid.println(m)
        }
        for (i <- 0 until alledges.length) {
          val a = alledges(i).left
          val b = alledges(i).right
          outedge.println(a + "," + b)
        }
        outid.close()
        outedge.close()

        println("--------**********--------")
        println("Second Friendships of " + targ + " is: " + secFriendNum)
        println("First Friends Number of " + targ + " is: " + firstfriend)
        println("Second Friends Number of " + targ + " is: " + (allids.length - firstfriend))
      } catch {
        case ex: FileNotFoundException =>{
          println("Missing file exception")
        }
        case ex: IOException => {
          println("IO Exception")
        }
      }

    }
}