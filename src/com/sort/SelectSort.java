package com.sort;
/**
 * 选择排序：
 */
public class SelectSort {
    public static void main(String[] args) {
        int[] num = new int[]{4,1,2,-1,5,9,0,7,12,3};
        num = SelectSort.sort(num);

        for(int i : num){
            System.out.print(i + " ");
        }
    }

    public static int[] sort(int[] num){
        for (int i = 0; i < num.length - 1; i++) {
            /**假设min为当前最小数*/
            int min = num[i];
            /**记录当前最小数的下标*/
            int minIndex = i;
            for(int j = i + 1;j < num.length;j++){
                if(min > num[j]){
                    /**重置最小数值*/
                    min = num[j];
                    /**重置最小数下标*/
                    minIndex = j;
                }
            }
            /**当换最小数，放在前面
             * 或者 minIndex != i
             * */
            if(num[i] != min) {
                int temp;
                temp = num[i];
                num[i] = num[minIndex];
                num[minIndex] = temp;
            }
        }
        return num;
    }
}
