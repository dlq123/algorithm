package src.grap;

import java.awt.font.TextMeasurer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Stack;

/**
 * @author dlq
 * @Description 对BFS进行封装
 * @Date 2020/8/4 11:30
 */
public class BreadthFirstSearchUtil {
    /**
     * 已被访问的节点
     */
    private List<String> visitedNode;
    /**
     * 保存最路径
     */
    private Map<String, String> pathMap;

    public Map<String, String> getPath(){
        return pathMap;
    }

    public void  bfs(Grap grap, String firstNode) {
        //初始进来的时候，进行初始化数据
        if (visitedNode == null) {
            visitedNode = new ArrayList<>();
        }
        //初始进来的时候，进行初始化数据
        if (pathMap == null) {
            pathMap = new HashMap<>();
        }
        //在进行广度优先搜索的时候，一定先遍历一度关系，然后在遍历二度关系，三度、四度...依次下去
        //一度关系如  A---C---D
        //          |---B--F---E
        //以A为初始节点，B、C就是一度关系，D、F是二度关系，E是三度关系
        //这个队列要包含从初始节点到终节点的所有节点
        Queue<String> queue = new LinkedList<>();

        //标记初始节点,防止出现死循环，如，A---B，因为是无向图，关系是双向的
        visitedNode.add(firstNode);

        //队列添加初始节点
        queue.add(firstNode);

        while (!queue.isEmpty()) {
            //取出队列第一个元素
            String head = queue.poll();
            //每次循环，都将每一个节点的邻节点添加到队列的队尾中  A(B,C),B(A,F),C(A,D),F(B,E)
            //第一次循环（假如:String node = queue.peek()）  queue数据方向--->  A
            //第二次次循环  queue数据   C  B  A 然后往下走
   //         queue.addAll(grap.getAdjNodeMap().get(head));不能这样直接添加，队列中最好不要重复添加元素

            if (grap.getAdjNodeMap().get(head) == null){
                continue;
            }
            //遍历邻节点
            for (String node : grap.getAdjNodeMap().get(head)){
                if (visitedNode.contains(node)){
                    //表示之前遍历/判断这个邻节点。所以不再进行遍历，不然会出现死循环
                   continue;
                }
                //判断是否到达了目的地
                visitedNode.add(node);
                queue.add(node);
                //1、可以用户判断，找到某个目的地，比如找到最近的芒果商是谁，这里直接判断返回
                //if XXXX retrun

                //2、得到最短路径，因为上面的判断进行过滤，这里不会出现重复的key情况
                //key当前节点，value是队列的第一个元素节点
                pathMap.put(node,head);
            }
        }
    }
    public List<String> findPathOneToOther(Grap grap,String  fromNode,String  toNode){
        if (fromNode.equals(toNode)){
            return null;
        }
        bfs(grap,fromNode);
        Map<String, String> path = getPath();
        List<String> list = new ArrayList<>();
        String location = toNode;
        while (!path.isEmpty()){
            list.add(location);
            String temp = location;
            if (path.get(location) == null){
                break;
            }
            location = path.get(location);
            path.remove(temp);
        }
       return list;
    }
}

