package SocialRelationMining

import java.io.PrintWriter

import scala.collection.mutable.ArrayBuffer
import scala.io.Source

/**
 * Created by yang on 15-12-30.
 */
object TestGetId {
  def main(args: Array[String]) {
    val source = Source.fromFile("/home/yang/data/graph/soc.txt", "GBK")
    val lineIter = source.getLines
    val AllId: ArrayBuffer[Int] = new ArrayBuffer[Int]()
    val outfile = new PrintWriter("/home/yang/data/graph/socid.txt")

    lineIter.foreach(line =>{
      val pair = line.split("\\s+")
      AllId.append(pair(0).toInt)
      AllId.append(pair(1).toInt)
    })

    val DistId = AllId.distinct

    for(i <- 0 to DistId.length-1){
      if(DistId(i)!=null){
        println(DistId(i))
        outfile.println(DistId(i))
      }

    }
    println(AllId.length)
    println("Length: " + DistId.length)

  }
}
