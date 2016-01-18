/*
package SocialRelationMining

import java.io.PrintWriter

import org.apache.spark.SparkContext
import org.apache.spark.graphx.{PartitionStrategy, GraphLoader}
/**
 * Created by yang on 16-1-7.
 */
object PageRank {

  def main(args: Array[String]) {
    var newGraph = generateInitGraph(graph, degreeSum).cache()
    do {
      // 每个节点获得邻居节点的信息
      val vertexRdd = newGraph.mapReduceTriplets(edgeMapFunc, _ ++ _).cache()
      // 根据上一轮中邻居的信息，更新节点的社区
      val idCommunity = vertexRdd.map {
        case (vid, vdArray) => (vid, getBestCommunity(vdArray, curDegree))
      }.cache()
      // 根据新的节点社区，获得更新信息
      val updateMessage = getUpdateMessage(idCommunity)
      // 更新图
      newGraph = newGraph.joinVertices(updateMessage) {}
    } while (changeRate > minThreshold && i < maxIterations)

    edgeRdd.leftOuterJoin(communityRdd)
      .map{ case (srcId, (dstId, srcComm)) => (dstId, srcComm.getOrElse(0L)) }
      .leftOuterJoin(communityRdd)
      .map{ case (dstId, (srcComm, dstComm)) =>(srcComm, dstComm.getOrElse(0L)) }

    val rawG = Graph.fromEdgeTuples(rawIdCommnity, 1)
    // 获得连通区域
    val connetedComponent = rawG.connectedComponents().vertices
    // 得到最终结果
    val idCommunity = rawIdCommunity.join(connetedComponent).map {
      case(id, (rawCommunity, newCommunity)) => (id, newCommunity)
    }
  }


}

class VertexData ( ) extends Serializable {

  var degree : Int = 0    // 该节点度值
  var community : Long = 0    // 该节点所属社区
  var communityDegreeSum : Long = - 1 // 该社区的度数之和
  var neighDegree : Int = 0    // 目标节点的度值
  var neighCommunity : Long = - 1 // 目标节点所属社区
  var neighCommunityDegreeSum : Long = - 1 // 目标节点的社区总权重
  var edgeCount : Long = - 1    // 该节点与目标节点的连线条数

}

*/
