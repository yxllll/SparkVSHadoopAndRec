package TimeAndSpaceMining

/**
 * Created by yang on 16-1-4.
 * mark the trajectory point and ID
 */
class TrajPoint {

  var IDnum: Int = 0
  var time: Long = 0
  var lng: Double = 0
  var lat: Double = 0

  def this(id: Int){
    this
    this.IDnum = id
  }

  def this(x: Double, y: Double){
    this()
    this.lng = x
    this.lat = y
  }

  def this(id: Int, lng: Double, lat: Double){
    this(id)
    this.lng = lng
    this.lat = lat
  }

  def this(id: Int, lng: Double, lat: Double, time: Long){
    this(id, lng, lat)
    this.time = time
  }

}
