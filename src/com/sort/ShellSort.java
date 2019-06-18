package com.sort;

import java.util.Arrays;

/**
 * 采用交换的方式实现希尔排序：是一种优化后的插入排序
 *
 */
public class ShellSort {
    public static void main(String[] args) {
        int[] num = new int[]{4,1,2,-1,5,9,0,7,12,3};
        num = ShellSort.sortByMove(num);

        for(int i : num){
            System.out.print(i + " ");
        }
    }
    /**采用交换的形式来排序*/
    public static int[] sortByExchange(int[] num){
        int count = 0;
        int times = num.length / 2;
        while(0 != times){
            for(int i = times;i < num.length;i++){
                for(int j = i - times;j >= 0;j -=times){
                    if(num[j] > num[j + times]) {
                        int temp = 0;
                        temp = num[j];
                        num[j] = num[j + times];
                        num[j + times] = temp;
                    }
                }
            }
            System.out.println("第 " + (++count) + " 轮排序 " + Arrays.toString(num));
            times /= 2;
        }
        return num;
    }

    /**采用移动的方式来排序*/
    public static int[] sortByMove(int[] num){
        int count = 0;
        int times = num.length / 2;
        while(0 != times){
            for (int i = times;i < num.length;i++){
                int temp = num[i];
                int j = i - times;
                while(j  >= 0 && temp < num[j]){
                    /**移动*/
                    num[j + times] = num[j];
                    j -= times;
                }
                num[j + times] = temp;
                System.out.println("第 " + (++count) + " 轮排序 " + Arrays.toString(num));
            }
            times /= 2;
        }
        return num;
    }
}
