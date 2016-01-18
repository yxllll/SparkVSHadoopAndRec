package TimeAndSpaceMining

import java.io.PrintWriter
import scala.io.Source

/**
 * Created by yang on 16-1-4.
 * This Object just use once for select a trajectory data for every ID
 */
object GetOneTrajOnly {
  def main(args: Array[String]) {
    val source = Source.fromFile("/home/yang/Desktop/GraphData/LocNetworks/loc-gowalla_totalCheckins.txt", "GBK")
    val lineIter = source.getLines
    val outfile = new PrintWriter("/home/yang/Desktop/GraphData/ChekinOnce")
    var idremenber: Int = 9999999

    lineIter.foreach(line => {
      val pair = line.split("\\s+")
      val id = pair(0).toInt
      if(idremenber != id){
        idremenber = id
        val x = pair(3).toDouble
        val y = pair(2).toDouble
        val pretime = pair(1).split("T")
        val ptime = pretime(0).split("-")
        val time = (ptime(0) + ptime(1) + ptime(2)).toLong
        outfile.println(id + " " + x + " " + y + " " + time)
      }

    })

    println("That's OK!")
  }
}
