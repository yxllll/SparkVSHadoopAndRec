package TimeAndSpaceMining

/**
 * Created by yang on 16-1-4.
 */
 class GetDistance {
  def getDistance(t1: TrajPoint, t2: TrajPoint): Double ={
    if(t1.lng == t2.lng && t1.lat == t2.lat){
      return 0
    }

    val R:Double = 6370996.81
    var distance:Double = Math.cos(t1.lat*Math.PI/180)*Math.cos(t2.lat*Math.PI/180)*Math.cos(t1.lng*Math.PI/180-t2.lng*Math.PI/180)+Math.sin(t1.lat*Math.PI/180)*Math.sin(t2.lat*Math.PI/180);
    if(Math.abs(distance) > 1){
      val b = BigDecimal(distance)
      distance = b.setScale(6).doubleValue()
    }
    val result = R*Math.acos(distance)
    result
  }
}
