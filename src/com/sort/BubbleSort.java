package com.sort;

import java.util.Arrays;

/**
 * 实现冒泡排序：
 *
 */
public class BubbleSort {
    public static void main(String[] args) {
        int[] num = new int[]{4,1,2,-1,5,9,0,7,12,3};
        num = BubbleSort.sort(num);

        for(int i : num){
            System.out.print(i + " ");
        }
    }

    public static int[] sort(int [] num){
        for (int i = 0; i < num.length - 1; i++) {
            boolean flag = false;
            for(int j = 0;j < num.length -1 - i;j++){
                if(num[j] > num[j + 1]){
                    int temp = num[j];
                    num[j] = num[j + 1];
                    num[j + 1] = temp;
                    flag = true;
                }
            }
            System.out.println(Arrays.toString(num));
            /**在一趟排序中，一次也没有交换过*/
            if(!flag){
                break;
            }
        }
        return num;
    }
}
