package src.grap;

/**
 * @author dlq
 * @Description 边的类
 * @Date 2020/8/4 17:31
 */
public class Edge {
    /**
     * 起始节点---from节点
     */
     private String name;
    /**
     * 终止节点---to节点
     */
     private String nextName;

    public Edge(String name, String nextName) {
        this.name = name;
        this.nextName = nextName;
    }

    public Edge(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNextName() {
        return nextName;
    }

    public void setNextName(String nextName) {
        this.nextName = nextName;
    }
}
