package com.sort;
/**
 * 选择排序：
 * 思路：
 * 1:选择排序一共有数组大小-1轮排序
 * 2:每一轮排序，又是一个循环，循环的规则代码
 *   2.1:先假定当前这就个数是最小数
 *   2.2:然后和后面的每个数进行比较，如果发现有比当前更小的数，就重置最小数，并记录其下标
 *   2.3:当遍历到数组的后面时，就得到了本轮最小的数和它的下标
 *   2.4:交换代码再继续循环
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
