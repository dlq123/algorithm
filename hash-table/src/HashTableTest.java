package src;

import src.demo.map.MyHashMap;
import src.demo.map.MyMap;

import java.util.HashMap;
import java.util.Map;

/**
 * @author dlq
 * @Description
 * @Date 2020/7/24 14:42
 */
public class HashTableTest {

    public static void main(String[] args) {
        test();

        MyMap<Integer,String> myMap = new MyHashMap<>(2);
        myMap.put(1,"aaa");
        myMap.put(2,"bbbb");

        //到这一步进行扩容
        myMap.put(3,"vvvvvvvvv");
        myMap.print();
        myMap.put(2,"fffff");
        myMap.print();


    }

    private static void test() {
        // Map<String,String> map = new HashMap<>(2);
        //这里虽然初始化了一个值，但是，在hasp内部初始化的时候 tableSizeFor 方法，最终会的结果就是能够返回刚好大于初始化值(这里是15)的2的n次方(即返回恰好大于15的(2^n))
        //这里是最终结果是16
        //真实的初始化，其实是在put的时候进行扩容操作
        Map<String,String> map = new HashMap<>(15);
        map.put("a","a");
        map.put("a","ddda");
        map.put("c","a");
        map.put("u","u");
        map.put("sda","sda");
        map.put("asd","asd");
        map.put("fsa","fsa");
        map.put("gg","gg");
        map.put("ud","ud");
        map.put("iii","iii");
    }
}
