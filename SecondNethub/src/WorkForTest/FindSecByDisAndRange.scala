package WorkForTest

import java.io.{File, PrintWriter, IOException, FileNotFoundException}

import TimeAndSpaceMining.{GetDistance, TrajPoint}

import scala.collection.mutable.ArrayBuffer
import scala.io.Source

/**
 * Created by yang on 16-1-5.
 */
object FindSecByRange {

  def main (args: Array[String]){
    try{

      val jack = 6
      var jacklocation: TrajPoint = new TrajPoint()
      val distance = new GetDistance
      val pfile = "/home/yang/Desktop/GraphData/ChekinOnce"
      val secfile = "/home/yang/Desktop/tmp/edge-id/SecFriendsOf6"
      val edge = "/home/yang/Desktop/tmp/edge-id/AllEdgeOf-6"
      val outSecDistAndRan = new PrintWriter(new File("/home/yang/Desktop/tmp/test/SecDisAndRanOf-" + jack))
      val outDistDesc = new PrintWriter(new File("/home/yang/Desktop/tmp/test/SecDistanceOf-" + jack + "desc"))
      val outMed = new PrintWriter(new File("/home/yang/Desktop/tmp/test/SecDistanceOf-" + jack + "andMed"))
      val pointfile = Source.fromFile(pfile)
      val secfriend = Source.fromFile(secfile)
      val plines = pointfile.getLines()
      val slines = secfriend.getLines()
      val secFriend: ArrayBuffer[Int] = new ArrayBuffer[Int]()
      val secNearPoint: ArrayBuffer[TrajPoint] = new ArrayBuffer[TrajPoint]()
      val distanceOfSec = new ArrayBuffer[DistanceOfSecRange]()

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
          if(idnum == secFriend(i)){
            val tmp = new TrajPoint(x(0).toInt,x(1).toDouble,x(2).toDouble,x(3).toLong)
            val dist = distance.getDistance(jacklocation,tmp)
            distanceOfSec.append(new DistanceOfSecRange(idnum, dist))
          }
        }
      })

//      var temp = new DistanceOfSecRange()
//      val len = distanceOfSec.length - 1
//      for (i <- 0 until len;from = len - i){
//        for(j <- 0 until from){
//          if(distanceOfSec(j+1).distance < distanceOfSec(j).distance){
//            temp = distanceOfSec(j)
//            distanceOfSec(j) = distanceOfSec(j+1)
//            distanceOfSec(j+1) = temp
//          }
//        }
//      }

      val medfile = Source.fromFile(edge)
      val medlines = medfile.getLines()
      medlines.foreach(line => {
        val pair = line.split(",")
        val friend = pair(0).toInt
        val sec = pair(1).toInt

        //affirm that friend recommended is not his or her friend
        if(friend != jack){
          for(i <- 0 until distanceOfSec.length){
            if(distanceOfSec(i).id == sec){
              distanceOfSec(i).medium = friend
            }
          }
        }
      })

      //get final list that does not contain jack's friend and himself
      val finaldisandran = new ArrayBuffer[DistanceOfSecRange]()
      for(i <- 0 until distanceOfSec.length){
        if(distanceOfSec(i).medium != 999999){
          finaldisandran.append(distanceOfSec(i))
        }
      }

      //get range by distance and then write out
      for(i <- 0 until finaldisandran.length){
        val a:Int = finaldisandran(i).id
        val b:Double = finaldisandran(i).distance
        if(finaldisandran(i).distance > 0){
          if(finaldisandran(i).distance > 2000 && finaldisandran(i).distance < 30000) {
            finaldisandran(i).range = 1
          }
          if(finaldisandran(i).distance > 10000 && finaldisandran(i).distance < 10000) {
            finaldisandran(i).range = 2
          }
          if(finaldisandran(i).distance > 30000 && finaldisandran(i).distance < 200000) {
            finaldisandran(i).range = 3
          }
          if(finaldisandran(i).distance > 200000 && finaldisandran(i).distance < 1000000) {
            finaldisandran(i).range = 4
          }
          if(finaldisandran(i).distance > 1000000) {
            finaldisandran(i).range = 5
          }
        }
        val c:Int = finaldisandran(i).range
        val d:Int = finaldisandran(i).medium
        outSecDistAndRan.println(f"$a\t$b%.2f\t$c")
        outMed.println(f"$a\t$d\t$b%.2f\t$c")
        outDistDesc.println(f"$a\t$b%.2f")
        if(d != 999999){
          println(f"$a $d $b%.2f $c")
        }

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

class DistanceOfSecRange(){
  var id: Int = 0
  var medium: Int = 999999
  var distance:Double = 0.0
  var range: Short = 0

  def this(id: Int, dis: Double){
    this()
    this.id = id
    this.distance = dis
  }

  def this(id: Int, dis: Double, ran:Int){
    this(id, dis)
    this.range
  }

  def this(id: Int, med: Int, dis: Double, ran:Int){
    this(id, dis, ran)
    this.medium = med
  }

}