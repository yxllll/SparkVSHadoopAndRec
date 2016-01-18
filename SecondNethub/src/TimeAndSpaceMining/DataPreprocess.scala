package TimeAndSpaceMining

import java.io.PrintWriter
import scala.io.Source

/**
 * Created by yang on 16-1-4.
 * This Object just use once for pre processing the raw data
 */
object DataPreprocess {
  def main(args: Array[String]) {
    val source = Source.fromFile("/home/yang/Desktop/GraphData/LocNetworks/loc-gowalla_totalCheckins.txt", "GBK")
    val lineIter = source.getLines
    val outfile = new PrintWriter("/home/yang/Desktop/GraphData/Checkins")

    lineIter.foreach(line => {
      val pair = line.split("\\s+")
      val id = pair(0).toInt
      val x = pair(3).toDouble
      val y = pair(2).toDouble
      val pretime = pair(1).split("T")
      val ptime = pretime(0).split("-")
      val time = (ptime(0) + ptime(1) + ptime(2)).toLong
      outfile.println(id + " " + x + " " + y + " " + time)
    })

    println("That's OK!")
  }
}
