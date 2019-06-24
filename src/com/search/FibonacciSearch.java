package com.search;

import java.util.Arrays;

/**
 * 实现斐波那契查找(有序数组中查找)：
 *
 */
public class FibonacciSearch {
    /**声明要生成斐波那契数组的长度 */
    public static final int MAX_SIZE = 20;

    public static void main(String[] args) {
        int[] num = new int[]{0,1,5,6,7,11,18,35};
        int index = FibonacciSearch.search(num,35);
        if(-1 != index){
            System.out.println(index);
        }
    }

    /**生成斐波那契数组*/
    public static int[] getFibonacci(){
        int[] f = new int[MAX_SIZE];
        f[0] = 1;
        f[1] = 1;
        for (int i = 2; i < MAX_SIZE; i++) {
            f[i] = f[i-1] + f[i - 2];
        }
        return f;
    }

    /**
     *
     * @param num 斐波那契数组
     * @param value 要找的值
     * @return 没有则返回 -1
     */
    public static int search(int[] num,int value){
        int low = 0;
        int high = num.length - 1;
        /**k : 斐波那契分割数值对应的下标*/
        int k  = 0;
        int mid = 0;
        /**获取到斐波那契数组*/
        int[] fib = FibonacciSearch.getFibonacci();
        /**
         * 获取到 k
         */
        while(high > fib[k] - 1){
            k++;
        }
        /**
         * 因为 fib[k]的值可能会大于数组num的长度，
         * 因此我们要用Arrays类，构造一个新的数组，并指定num[],不足的地方会有0补足
         * 如：int[] num = new int[]{0,1,5,6,7,11,18,35} ->
         *   int[] num = new int[]{0,1,5,6,7,11,18,35,0,0,0,0};
         * */
        num = Arrays.copyOf(num,fib[k]);
        /**实际上要用num的最后的值来填充默认为0的值
         * 如 int[] num = new int[]{0,1,5,6,7,11,18,35,0,0,0,0} ->
         * int[] num = new int[]{0,1,5,6,7,11,18,35,35,35,35,35};
         * */
        for (int i = high + 1; i < num.length; i++) {
            num[i] = num[high];
        }

        while(low <= high){
            mid = low + fib[k - 1] - 1;
            int midVal = num[mid];
            /**向左查找*/
            if(midVal > value){
                high = mid - 1;
                /**
                 *
                 */
                k -= 1;
            }
            else if(midVal < value){
                low = mid + 1;
                k -= 2;
            }
            else {
                /**找到*/
                if(mid <= high){
                    return mid;
                }
                else {
                    return high;
                }
            }
        }
        return -1;
    }
}
