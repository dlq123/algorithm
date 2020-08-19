package src.grap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author dlq
 * @Description (这里是无向图)图
 * @Date 2020/8/4 10:53
 */
public class Grap {
    /**
     * 第一个节点
     */
    private String firstNode;

    /**
     * 这个map存储的是节点相连的其他的节点，key--当前节点，value---相邻链接的节点
     * 如  A---C
     *     |---B    map(A,[B,C])，map(B,[A]),map(C,[A])
     */
    private Map<String, List<String>> adjNodeMap;

    public String getFirstNode() {
        return firstNode;
    }

    public void setFirstNode(String firstNode) {
        this.firstNode = firstNode;
    }

    public Map<String, List<String>> getAdjNodeMap() {
        return adjNodeMap;
    }

    public void setAdjNodeMap(Map<String, List<String>> adjNodeMap) {
        this.adjNodeMap = adjNodeMap;
    }

    /**
     * @description 添加顶点
     * @param node
     * @return
     * @author dlq
     * @date 2020/8/4 11:13
     */
    public void addNode(String node){
        if (adjNodeMap == null ){
            adjNodeMap = new HashMap<>();
        }
        adjNodeMap.put(node,new ArrayList<>());
    }

    /**
     * @description 添加边  A -----（边） B
     * @param fromNode  起点
     * @param: toNode  终点
     * @return void
     * @author dlq
     * @date 2020/8/4 11:09
     */
    public void addEdge(String fromNode, String toNode){

        //如果没有指定一个节点(头结点)，就将起点作为头结点
        if (firstNode == null){
            firstNode = fromNode;
        }

        if (adjNodeMap.get(fromNode) != null){
            //这里是双向图，如果将fromNode 做起点，toNode就是终点，
            //           如果将toNode 做起点，fromNode就是终点
            //所以都要进行添加
            adjNodeMap.get(fromNode).add(toNode);
        }


        if ( adjNodeMap.get(toNode) != null){
            //如果是单向图的话，可以不要下列语句，关系：A欠B的钱，A--->B单向的
            adjNodeMap.get(toNode).add(fromNode);
        }

    }

}
