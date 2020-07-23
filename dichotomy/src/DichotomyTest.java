package src;

import java.util.Arrays;

/**
 * @author dlq
 * @Description 二分法查找
 * @Date 2020/6/8 15:49
 */
public class DichotomyTest {
    public static void main(String[] args) {
        //在使用二分法查找的时候，首先要将数据进行排序操作，
        int[] arr = {7, 5, 10, 32, 11, 67, 2, 1, 66, 4, 6, 9, 77, 55, 101, 89};
        //排序
        Arrays.sort(arr);
        Arrays.stream(arr).forEach(item -> {
            System.out.print(item + " ");
        });
        //通过二分法查询排序后的，某个数的位置,若不存在，返回-1
        int index = dichotomyLoop(arr, 66);
        System.out.println("index=== " + index);

        System.out.println("======================= ");

        int index2 = dichotomyRecursion(0, arr.length - 1, arr, 68);
        System.out.println("index=== " + index2);
    }

    /**
     * @param arr
     * @return int
     * @description 循环方式  通过二分法查询排序后的，某个数的位置,若不存在，返回-1
     * @param: num
     * @author dlq
     * @date 2020/6/8 17:26
     */
    private static int dichotomyLoop(int[] arr, int num) {

        //个人理解：在二分法中，开始索引位置和结束索引位置一直在变动，中间索引随着开始和结束在变动，
        //所以，初始化开始索引和结束索引，然后在比较中，变动开始索引和结束索引
        int high = arr.length - 1;
        int low = 0;
        while (low <= high) {//只要范围没有缩小到只有一个元素
            //二分法，从中间开始比较
            //第一种：
           // int mid = (low + high) / 2;
           //第二种
            int mid = low + (high - low)/2;
            if (arr[mid] > num) {//num在中间之前
                high = mid - 1;
            } else if (arr[mid] < num) {//num在中间之后
                low = mid + 1;
            } else {
                return mid;
            }
        }
        return -1;
    }

    /**
     * @param low 可能变动的开始索引位置
     * @return int
     * @description 递归  通过二分法查询排序后的，某个数的位置,若不存在，返回-1
     * @param: high 可能变动的结束索引位置
     * @param: arr  传入的数组
     * @param: num  我们要查找的对象
     * @author dlq
     * @date 2020/6/9 19:47
     */
    private static int dichotomyRecursion(int low, int high, int[] arr, int num) {
        if (low > high) return -1;//只要范围没有缩小到只有一个元素
        int mid = (low + high) / 2;
        if (arr[mid] > num) { //num在中间之前   ==》递归条件
            //单独取出来，更加明了,high的变化
            high = mid - 1;
            return dichotomyRecursion(low, high, arr, num);
        }
        if (arr[mid] < num) {//num在中间之后   ==》递归条件
            //单独取出来，更加明了，low的变化
            low = mid + 1;
            return dichotomyRecursion(low, high, arr, num);
        }
        if (arr[mid] == num)  // ==》基线条件
            return mid;
        return -1;
    }
}
