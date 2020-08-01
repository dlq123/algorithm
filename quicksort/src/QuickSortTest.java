import java.util.Arrays;

/**
 * @author dlq
 * @Description 快排--时间复杂度 p
 * @Date 2020/7/17 10:00
 */
public class QuickSortTest {
    public static void main(String[] args) {
        //获取数组
        //int[] arr = getArray(1,0);
        int[] arr = {16, 2, 1, 11, 19, 3, 3, 5};
        //升序
        quickSortRecursion(arr, 0, arr.length - 1);
        for (int i : arr){
            System.out.print(i+ " ");
        }

    }

    /**
     * @description 递归的方法
     * @param arr
     * @param: start 索引范围--起始索引位置
     * @param: end 索引范围--终止索引位置
     * @return void
     * @author dlq
     * @date 2020/7/23 15:27
     */
    private static void quickSortRecursion(int[] arr, int start, int end) {
        //基线条件：数组存在一个元素或者 是当执行完所有排序后，end等于或者小于start的时候就可以跳出递归
        if (start >= end) {
            return;
        }
        //这里没有把 if 的else写出来，暗含一个递归条件，即 end > start
        //获取每次排序的,基值最后的索引
        //单独把每次排序提取出来
        int basePosition = divisionArr(arr, start, end);
        //排序完毕，后续排序以 basePosition 为节点，左边所有小于 arr[basePosition] 右边所有大于arr[basePosition]，左右两边以 basePosition 按规则继续拆分下去
        //左边的索引范围 0 - basePosition-1
        quickSortRecursion(arr, start, basePosition - 1);
        //右边的索引范围 basePosition+1 , arr.length - 1
        quickSortRecursion(arr, basePosition + 1, end);

    }

    /**
     * @description
     * @param arr 原数组
     * @param: start 索引范围--起始索引位置
     * @param: end  索引范围--终止索引位置
     * @return int  返回基值的索引
     * @author dlq
     * @date 2020/7/23 14:44
     */
    private static int divisionArr(int[] arr, int start, int end) {
        //选择一个基值，这里我们选择最后一个元素作为基值
        int base = arr[end];
        while (end > start) {
            //左边遍历的比较，左边所有小于等于基值
            //比如，第一次排序的时候，因为这里用的是 最后一个元素作为基值 ，相当于但不是等于，第一次都是左边遍历 {16, 2, 1, 11, 19, 3, 3, 5}
            while (end > start && arr[start] <= base) {
                //满足这个条件，说明左边都小于基值，不用改动，比较下一个，
                //因为start是开始位置，所以递增
                start++;
            }
            //当跳出循环的时候，要么endIndex <= endIndex 或者 arr[startIndex] > base
            // 如果还满足startIndex < endIndex,此时arr[startIndex] > base 进行交换
            //上面的数组列字： 5和16进行对调
            if (start < end) {
                //进行交换
                int temp = arr[end];
                arr[end] = arr[start];
                arr[start] = temp;

            }
            //虽然交换位置，但是base 基值在这个方法里面，值没有变得，依然等于 arr[end]
            //右边遍历的比较，左边所有小于等于基值
            //比如：这个数组， {5,2,1,11,19,3,3,16} 相当于但不是等于 右边遍历
            while (start < end && arr[end] >= base) {
                //满足这个条件，说明右边都大于基值，不用改动，比较下一个
                //因为start是终止位置，所以递减
                end--;
            }
            //当跳出循环的时候，要么endIndex <= endIndex 或者 arr[startIndex] > base
            // 如果还满足startIndex < endIndex, 此时arr[startIndex] > base  进行交换
            //上面的数组列字： 5和3进行对调
            if (start < end){
                //进行交换
                int temp = arr[start];
                arr[start] = arr[end];
                arr[end] = temp;
            }
        }
        //返回最后start的值
        return end;
    }

    /**
     * @param num
     * @param num
     * @return int[]
     * @description 在某个数范围生成一个随机数组，不包含0
     * @author dlq
     * @date 2020/7/22 15:35
     */
    public static int[] getArray(int length, int num) {
        int[] arr = new int[length];
        for (int i = 0; i < arr.length; i++) {
            //如果在某个数范围生成一个随机数组，需要包含0 就要到+1
            //如：arr[i] = (int) (Math.random() * num);
            arr[i] = (int) (Math.random() * num + 1);
            System.out.print(arr[i] + ",");
        }

        return arr;

    }
}
