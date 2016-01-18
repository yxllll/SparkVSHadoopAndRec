package GraphXExperiment

import scala.collection.mutable.ArrayBuffer

/**
 * Created by yang on 16-1-10.
 */
class GetIdByEdges {
  var left:Int = 0
  var right: Int = 0

  def this(l:Int){
    this()
    this.left = l
  }

  def this(l: Int, r:Int){
    this(l)
    this.right = r
  }

}
