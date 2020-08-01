package src.demo.map;

import java.util.ArrayList;
import java.util.List;

/**
 * @author dlq
 * @Description
 * @Date 2020/7/30 14:52
 */
public class MyHashMap<K, V> implements MyMap<K, V> {
    /**
     * 负载因子
     */
    private final static double default_load_factor = 0.75;
    /**
     * 默认长度
     */
    private final static int default_length = 1 << 4;
    /**
     * 使用数组位置的数量--(已经被使用位置的数量或者是占用位置数量)
     */
    private int size = 0;
    /**
     * 最终的负载因子
     */
    private double loadFactor;

    /**
     * 容量
     */
    private int capacity;

    /**
     * table可以说是哈希桶数组，里面存在是的k,v和next MyEntry
     */
    private MyEntry<K, V>[] table;

    public MyHashMap() {
        this(default_length, default_load_factor);
    }

    public MyHashMap(int initialCapacity) {
        this(initialCapacity, default_load_factor);
    }

    public MyHashMap(int initialCapacity, double loadFactor) {
        if (initialCapacity < 0) {
            throw new IllegalArgumentException("Illegal initial capacity:" + initialCapacity);
        }
        if (loadFactor <= 0 || Double.isNaN(loadFactor)) {
            throw new IllegalArgumentException("Illegal load factor: " + loadFactor);
        }

        this.loadFactor = loadFactor;
        this.capacity = initialCapacity;
        table = new MyEntry[capacity];

    }


    @Override
    public V put(K k, V v) {
        //判断使用的数组(已经被使用位置的数量或者是占用位置数量)是否是达到了初始化的容量
        if (size > loadFactor * capacity) {
            //扩容
            expanSize();
        }
        //得到K对象的索引--为了看K对象之前是否已存在
        int index = getIndex(k, table.length);
        MyEntry<K, V> myEntry = table[index];
        //myEntry 为null的时候，说明，之前这个位置没有存储任何东西
        if (myEntry == null) {
            table[index] = new MyEntry<>(k, v, null);
            //使用的数组(已经被使用位置的数量或者是占用位置数量)+1，因为占用一个
            size++;
        } else {//当myEntry 不为null的时候，说明 index 这个索引位置存在值了

            //entry 在内存指向的地址和table[index]指向的地址一样，所以entry更改之的时候，table[index]拿到的也是更改的值
            MyEntry<K, V> entry = table[index];

            //写法1
            //-----------------
            //这里要当前index索引是否已经存在这个key 判断是否是更改
            //这里要用equals 和 == 因为key有可能是基本数据类型，也有可能是引用类型
            //hash中表存在相同key值
            if (entry.getKey() == k || (k != null && k.equals(entry.getKey()))) {
                entry.v = v;
            } else {//当index索引位置的key不等于k的时候，还要遍历所有节点(entry)判断是否有存在key等于k的
                while (entry != null) {
                    if (entry.next.getKey() == k || (k != null && k.equals(entry.next.getKey()))) {
                        entry.v = v;
                        break;
                    }
                    entry = entry.next;
                }
                //如果entry 还为null的话，证明hash表中不存在索引为k
                if (entry == null) {
                    //当所以位置存在对象的时候,因为是拉链式，所以，新的元素存储在旧元素之前，
                    //新元素的下一个位置就是旧元素的位置
                    MyEntry<K, V> newMyEntry = new MyEntry<>(k, v, table[index]);
                    //当时新增才占用位置数量才进行++进行
                    size++;
                }
            }

            //-----------------

/*            //写法2
            //-----------------
            //直接进行while循环，
            //判断当前index索引是否已经存在这个key，以及table[index]的下一个，下下一个，下下下一个......
            while (entry != null){
                if (entry.getKey() == k || (k != null && k.equals(entry.getKey()))){
                    entry.v = v;
                    break;
                }
                entry = entry.next;
            }
            if (entry == null){
                //当所以位置存在对象的时候,因为是拉链式，所以，新的元素存储在旧元素之前，
                //新元素的下一个位置就是旧元素的位置
                MyEntry<K,V> newMyEntry = new MyEntry<>(k,v, table[index]);
                //当时新增才占用位置数量才进行++进行
                size++;
            }
            //-----------------*/

        }

        //每次新增之后，判断size是否大于容量,大于，进行扩容
        if (size > capacity) {
            expanSize();
        }
        //同对象的对应的值
        return table[index].getValue();
    }

    private void expanSize() {
        //
        int newCapacity = capacity << 1;
        //进行二倍扩容
        MyEntry<K, V>[] newMyEntrys = new MyEntry[newCapacity];
        //重新散列
        //创建一个装载旧map的所有MyEntry,到时候重新进行组装
        List<MyEntry<K, V>> list = new ArrayList<>();
        //遍历哈希桶
        for (int i = 0; i < table.length; i++) {
            //如果哈希桶里面为空，表示这个哈希桶(table[i])没有MyEntry
            if (table[i] == null) {
                continue;
            }

            //table[i]的MyEntry
            MyEntry<K, V> tempEntry = table[i];
            //循环获取每个table[i]下面的next MyEntry
            while (tempEntry != null) {
                list.add(tempEntry);
                tempEntry = tempEntry.next;
            }
        }
        //list里面有值
        if (list.size() > 0) {
            //这里要把 使用数组位置的数量--(已经被使用位置的数量或者是占用位置数量或者是占用位置数量)进行清零
            //需要重新计算
            size = 0;
            //新的容量也需要重新设置
            capacity = newCapacity;
            //重新散列，table也需要进行重新初始化，扩容后的新数组
            table = newMyEntrys;
            //真正的rehash(重新散列)--里面size进行重新更改
            for (MyEntry<K, V> entry : list) {
                //因为这里重新散列了，所以需要将next设置为null,在put方法中重新设置
                if (entry != null) {
                    entry.next = null;
                }

                put(entry.getKey(), entry.getValue());
            }
        }

    }

    @Override
    public V get(K k) {
        //进行取值的时候，还是要获取index索引
        int index = getIndex(k, table.length);
        MyEntry<K, V> entry = table[index];
        if (entry == null) {
            return null;
        } else {
            //直接进行while循环，
            //判断当前index索引是否已经存在这个key，以及table[index]的下一个，下下一个，下下下一个......等是否存在相同的key
            while (entry != null) {
                //这里要用equals 和 == 因为key有可能是基本数据类型，也有可能是引用类型
                if (entry.getKey() == k || (k != null && k.equals(entry.getKey()))) {
                    return entry.v;
                }
                entry = entry.next;
            }
        }
        //假如说index对应是null,table[index]的下一个，下下一个，下下下一个......等都是为空的话，就只能返回为空
        return null;
    }

    @Override
    public int size() {
        return size;
    }

    /**
     * @param key
     * @return int
     * @description 计算hash，自定义hash--任意进行定义
     * @author dlq
     * @date 2020/7/30 17:01
     */
    public int hash(Object key) {
        if (key == null) {
            return 0;
        }
        int hashCode = key.hashCode() ^ (key.hashCode() >>> 18);
        return hashCode ^ (hashCode >>> 6) ^ (hashCode >>> 3);
    }

    /**
     * @param key
     * @return int
     * @description 根据key的哈希值得到一个index索引，即存放到数组中的下标,根据网上查找，做与(&)运算
     * @param: length 哈希桶的数组
     * @author dengliqiang
     * @date 2020/7/31 9:40
     */
    public int getIndex(Object key, int length) {
        int m = length - 1;
        //int index = hash(key) % m  和 key.hashCode() %m,会生成很多相同的余数，所以 这样方式可能产生比较多的冲突
        //另一种Hash公式，做与运算，jdk1.8的一种运算
        int index = hash(key) & m;
        return index >= 0 ? index : -index;
    }

    @Override
    public void print() {
        for (int i = 0; i < table.length; i++) {
            if (table[i] == null) continue;
            MyEntry<K, V> entry = table[i];
            while (entry != null) {
                System.out.print(entry.getKey()+":"+entry.getValue() +" ");;
                entry = entry.next;
            }
        }
        System.out.println();
    }
}