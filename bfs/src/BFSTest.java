package src;

import src.grap.BreadthFirstSearchUtil;
import src.grap.Edge;
import src.grap.Grap;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Stack;

/**
 * @author dlq
 * @Description
 * @Date 2020/8/3 14:26
 */
public class BFSTest {
    public static void main(String[] args) {
        Queue<String> queue = new LinkedList<>();
        queue.add("a");
        queue.add("b");
        queue.add("c");
   ///     queue.offer("a");放在队尾

        //返回查询的第一个元素，删除第一个元素，根据源码查询LinkedList，
        // 并不是删除其实就是将第一个等于下一个next，然后原有的就没得这个值了
        queue.poll();
        System.out.println(queue.size());
//        queue.peek();//返回查询的第一个元素，不删除元素
//        System.out.println(queue.size());

        //----------------
        Grap grap = new Grap();
        String[] vertex = {"A", "B", "C", "D", "E", "F"};

        Edge[] edges = {
                new Edge("A", "B"),
                new Edge("A", "C"),
                new Edge("B", "D"),
                new Edge("C", "D"),
                new Edge("C", "F"),
                new Edge("D", "G"),
                new Edge("F", "G"),
                };
        for (String s:vertex){
            grap.addNode(s);
        }
        for (Edge edge : Arrays.asList(edges)){
            grap.addEdge(edge.getName(),edge.getNextName());
        }
        BreadthFirstSearchUtil breadthFirstSearchUtil = new BreadthFirstSearchUtil();
        List<String> list = breadthFirstSearchUtil.findPathOneToOther(grap, "A", "G");
        System.out.println(list);
        for (int i=list.size()-1;i>-1;i--){
            System.out.print(list.get(i)+" ");
        }
    }
}
