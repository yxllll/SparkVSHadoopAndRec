package SocialRelationMining

import java.io.PrintWriter

import scala.collection.mutable.ArrayBuffer
import scala.io.Source

/**
 * Created by yang on 15-12-30.
 */
object TestGetFriends {
    def main(args: Array[String]) {
      val source = Source.fromFile("/home/yang/Desktop/GraphData/LocNetworks/loc-gowalla_edges.txt", "GBK")
      val idfile = Source.fromFile("/home/yang/Desktop/GraphData/LocNetworks/test.txt")
      val lineIter = source.getLines
      val IDIter = idfile.getLines
      val AllId: ArrayBuffer[Int] = new ArrayBuffer[Int]()
      val outfile = new PrintWriter("/home/yang/Desktop/tmp/Friend.txt")
      //数组缓冲记录ID 和 friends
      val friendRecord: ArrayBuffer[FriendsTable] = new ArrayBuffer[FriendsTable]()

      IDIter.foreach(line =>{
        AllId.append(line.toInt)
      })

      val all = AllId.length
      println(all)


      for(i <- 0 until all){
        val tmp: FriendsTable = new FriendsTable(AllId(i))
        friendRecord.+=(tmp)
      }

      lineIter.foreach(line =>{
        val pair = line.split("\\s+")
        val left = pair(0).toInt
        val right = pair(1).toInt
        for(j <- 0 until all){
          if(AllId(j) == pair(0).toInt){
            friendRecord(j).setFriends(left, right)
          }
        }
      })

      for(i <- 0 until friendRecord.length){
        val m:Int = friendRecord(i).IDnum
        val a = friendRecord(i).FriendsId.toString
        val n = a.substring(12,a.length-2)

        outfile.println(m + "\t" + n)
        println(m + " - " + n)
      }
    }
}
