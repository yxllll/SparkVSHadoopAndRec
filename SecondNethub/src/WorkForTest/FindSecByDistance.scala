package WorkForTest

import java.io.{File, PrintWriter, IOException, FileNotFoundException}

import TimeAndSpaceMining.{GetDistance, TrajPoint}

import scala.collection.mutable.ArrayBuffer
import scala.io.Source

/**
 * Created by yang on 16-1-5.
 */
object FindSecByDistance {

  def main (args: Array[String]){
    try{

      val jack = 6
      var jacklocation: TrajPoint = new TrajPoint()
      val distance = new GetDistance
      val pfile = "/home/yang/Desktop/GraphData/ChekinOnce"
      val secfile = "/home/yang/Desktop/tmp/edge-id/SecFriendsOf6"
      val outSecDistance = new PrintWriter(new File("/home/yang/Desktop/tmp/newdis/SecDistanceOf-" + jack))
      val outDistDesc = new PrintWriter(new File("/home/yang/Desktop/tmp/newdis/SecDistanceOf-" + jack + "desc"))
      val pointfile = Source.fromFile(pfile)
      val secfriend = Source.fromFile(secfile)
      val plines = pointfile.getLines()
      val slines = secfriend.getLines()
      val secFriend: ArrayBuffer[Int] = new ArrayBuffer[Int]()
      val secNearPoint: ArrayBuffer[TrajPoint] = new ArrayBuffer[TrajPoint]()
      val distanceOfSec = new ArrayBuffer[DistanceOfSec]()

      slines.foreach(line =>{
        val num = line.toInt
        secFriend.append(num)
      })

      val secnum = secFriend.length
      println("Secnum: " + secnum)

      //get jack's location
      plines.foreach(line =>{
        val str = line.split(" ")
        if(str(0).toInt == jack){
          jacklocation = new TrajPoint(str(0).toInt, str(1).toDouble, str(2).toDouble, str(3).toLong)
        }
      })

      println("Jack'Location is: " + jacklocation.lng + jacklocation.lat)

      val pfile2 = Source.fromFile("/home/yang/Desktop/GraphData/ChekinOnce")
      val plines2 = pfile2.getLines()
      plines2.foreach(line =>{
        val x = line.split(" ")
        val idnum = x(0).toInt
        for(i <- 0 until secFriend.length){
          if(idnum == secFriend(i) && idnum != jack){
            val tmp = new TrajPoint(x(0).toInt,x(1).toDouble,x(2).toDouble,x(3).toLong)
            val dist = distance.getDistance(jacklocation,tmp)
            distanceOfSec.append(new DistanceOfSec(idnum, dist))
          }
        }

      })

      var temp = new DistanceOfSec()
      val len = distanceOfSec.length - 1
      for (i <- 0 until len;from = len - i){
        for(j <- 0 until from){
          if(distanceOfSec(j+1).distance < distanceOfSec(j).distance){
            temp = distanceOfSec(j)
            distanceOfSec(j) = distanceOfSec(j+1)
            distanceOfSec(j+1) = temp
          }
        }
      }


      for(i <- 0 until distanceOfSec.length){
        val a = distanceOfSec(i).id
        val b = distanceOfSec(i).distance
        outSecDistance.println(f"$a\t$b%.2f")
        println(f"$a Distance: $b%.2f")
      }

    }catch {
      case ex: FileNotFoundException =>{
        println("Missing file exception")
      }
      case ex: IOException => {
        println("IO Exception")
      }
    }
  }
}

class DistanceOfSec(){
  var id: Int = 0
  var distance:Double = 0.0

  def this(id: Int, dis: Double){
    this()
    this.id = id
    this.distance = dis
  }

}