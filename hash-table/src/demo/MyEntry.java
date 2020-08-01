package src.demo;

/**
 * @author dlq
 * @Description
 * @Date 2020/7/30 14:48
 */
public class MyEntry<K, V> implements MyMap.Entry<K, V> {
    K k;
    V v;
    MyEntry<K, V> next;

    public MyEntry(K k, V v, MyEntry<K, V> next) {
        this.k = k;
        this.v = v;
        this.next = next;
    }

    @Override
    public K getKey() {
        return k;
    }

    @Override
    public V getValue() {
        return v;
    }
}
