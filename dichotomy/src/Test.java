package src;

import java.util.Arrays;

/**
 * @Description  二分法查找
 * @Date 2020/6/8 15:49
 * @Created by dlq
 */
public class Test {
    public static void main(String[] args) {
        //在使用二分法查找的时候，首先要将数据进行排序操作，
        int[] arr={7,5,10,32,11,67,2,1,66,4,6,9,77,55,101,89};
        //排序
        Arrays.sort(arr);
        Arrays.stream(arr).forEach(item ->{
            System.out.print(item+" ");
        });
        //通过二分法查询排序后的，某个数的位置,若不存在，返回-1
        int index =  dichotomyLoop(arr,66);
        System.out.println("index=== "+index);
    }

    /**
     * @description 循环方式  通过二分法查询排序后的，某个数的位置,若不存在，返回-1
     * @param arr
     * @param: num
     * @return int
     * @author dlq
     * @date 2020/6/8 17:26
     */
    private static int dichotomyLoop(int[] arr, int num) {

        //个人理解：在二分法中，开始索引位置和结束索引位置一直在变动，中间索引随着开始和结束在变动，
        //所以，初始化开始索引和结束索引，然后在比较中，变动开始索引和结束索引
        int high=arr.length - 1 ;
        int low = 0;
        while (low <= high){
            //二分法，从中间开始比较
            int mid = (low+high)/2;
            if(arr[mid] > num ){//中间之前
               high = mid -1;
            }else if (arr[mid] < num){//中间之后
                low = mid +1;
            }else {
                return mid;
            }
        }
        return -1;
    }

    /**
     * @description 递归  通过二分法查询排序后的，某个数的位置,若不存在，返回-1
     * @param
     * @return int
     * @author dengliqiang
     * @date 2020/6/9 19:43
     */
    private static  int dichotomyRecursion(){
        return  0;
    }
}
