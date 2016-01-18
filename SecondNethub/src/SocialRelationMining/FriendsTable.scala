package SocialRelationMining

import scala.collection.mutable.ArrayBuffer
import scala.io.Source

/**
 * Created by yang on 15-12-30.
 */
class FriendsTable {

  var IDnum: Int = 0
  var FriendsId: ArrayBuffer[Int] = new ArrayBuffer[Int]()
  var SecFriendsId: ArrayBuffer[Int] = new ArrayBuffer[Int]()

  def this(id: Int){
    this()
    this.IDnum = id
  }

  def this(id: Int, friends: ArrayBuffer[Int]){
    this(id)
    this.FriendsId = friends
  }

  def this(id: Int, friends: ArrayBuffer[Int], secFriendsId: ArrayBuffer[Int]){
    this(id, friends)
    this.SecFriendsId = secFriendsId
  }


  def setFriends(id: Int, friend: Int): Unit ={
    if(this.IDnum == id) {
      this.FriendsId.append(friend)
    }
  }

  def getFriendsList(id: Int, filein: String): ArrayBuffer[Int] ={
    val flist: ArrayBuffer[Int] = new ArrayBuffer[Int]()
    val source = Source.fromFile(filein)
    val line = source.getLines()
    line.foreach(x => {
      val pair = x.split("\\s+")
      val idnum = pair(0).toInt
      if(idnum == id){
        val list = pair(1).split(",")
        for(i <- 0 until list.length){
          flist.append(list(i).toInt)
        }
      }
    })
    flist
  }

  def getSecondFriends(id: Int, sec: ArrayBuffer[Int], filein: String): ArrayBuffer[Int] ={
    if(this.IDnum == id){
      for (i <- 0 until this.FriendsId.length){
        sec.++=(getFriendsList(FriendsId(i), filein))
      }
    }
    sec
  }

}
