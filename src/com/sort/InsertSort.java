package com.sort;

import java.util.Arrays;

/**
 * 插入排序
 * 思想：把n个待排序的元素看成为一个有序表和一个无序表，开始时有序表中只包含一个元素
 * 无序表中包含有n - 1个元素，排序过程中每次从无序表中取出第一个元素，把它的排序码依次与有序
 * 表元素的排序码进行比较，将它插入到有序表中的适当位置，使之成为新的有序表
 */
public class InsertSort {
    public static void main(String[] args) {
        int[] num = new int[]{4,1,2,-1,5,9,0,7,12,3};
        num = InsertSort.sort(num);

        for(int i : num){
            System.out.print(i + " ");
        }
    }

    public static int[] sort(int[] num){
        for (int i = 1; i < num.length; i++) {
            /**待插入的数*/
            int insertValue = num[i];
            /**num[i]前面这个数的下标*/
            int insertIndex = i - 1;
            /**
             * insertIndex >= 0:保证在给insertIndex找插入位置，不越界
             * insertValue < num[insertIndex]：待插入的数，还没有找到插入位置
             * 需要将num[insertIndex]后移
             */
            while(insertIndex >= 0 && insertValue < num[insertIndex]){
                num[insertIndex + 1] = num[insertIndex];
                insertIndex--;
            }
            /**
             * 当退出while循环时，说明插入的位置找到，insertIndex + 1
             */
            num[insertIndex + 1] = insertValue;
            System.out.println("第 " + i + " 轮排序 " + Arrays.toString(num));
        }
        return num;
    }
}
