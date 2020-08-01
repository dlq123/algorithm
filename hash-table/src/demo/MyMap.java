package src.demo.map;

/**
 * @author dlq
 * @Description 基础Map，和Map一样，是接口
 * @Date 2020/7/30 14:06
 */
public interface MyMap<K, V> {

    /**
     * @param k 存储的key,即是更改也是添加
     * @return V 返回对象
     * @param: v 存储key对应的value值
     * @author dlq
     * @date 2020/7/30 14:23
     */
    V put(K k, V v);

    /**
     * @param k  key
     * @return V 返回对象
     * @author dlq
     * @date 2020/7/30 14:31
     */
    V get(K k);

    /**
     * @description 这里直接复用Map的size
     * @param
     * @return int
     * @author dengliqiang
     * @date 2020/7/31 9:34
     */
    int size();
    /**
     * @description 简单打印
     * @param
     * @return void
     * @author dlq
     * @date 2020/7/31 15:01
     */
    void print();
    /**
     *  Entry内部接口
     */
    interface  Entry<K,V>{
        /**
         * @description  根据Entry对象获取Key的值
         * @param
         * @return K
         * @author dlq
         * @date 2020/7/30 14:41
         */
        K getKey();

        /**
         * @description 根据Entry对象获取Value的值
         * @param
         * @return V
         * @author dlq
         * @date 2020/7/30 14:41
         */
        V getValue();

    }

}
