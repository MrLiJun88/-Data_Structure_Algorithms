package com.sort;

import java.util.Arrays;

/**
 * 实现归并排序:
 * 该算法采用经典的分治（divide-and-conquer）策略（分治法将问题分(divide)成一些小的问题然后递归求解，
 * 而治(conquer)的阶段则将分的阶段得到的各答案"修补"在一起，即分而治之)。
 */
public class MergeSort {
    public static void main(String[] args) {
        int[] num = new int[]{4,1,2,-1,5,9,0,7,12,3};
        int[] temp = new int[num.length];
        num = MergeSort.branch(num,0,num.length - 1,temp);

        for(int i : num){
            System.out.print(i + " ");
        }
    }

    public static int[] branch(int[] num,int left,int right,int[] temp){

        if(left < right){
            int mid = (left + right) / 2;
            /**对左边进行分*/
            MergeSort.branch(num,left,mid,temp);
            /**对右边进行分*/
            MergeSort.branch(num,mid + 1,right,temp);

            MergeSort.merge(num,left,right,mid,temp);
        }
        return num;
    }
    /**
     *
     * @param num 待排序的原始数组
     * @param left 数组左边的索引下标
     * @param right 数组右边的索引下标
     * @param mid 数组中间的索引下标,即右边数组的初始化索引
     * @param temp  临时数组，做中转用
     * @return
     */
    public static void merge(int[] num,int left,int right,int mid,int[] temp){

        int i = left;
        int j = mid + 1;
        int t = 0;

        while(i <= mid && j <= right){
            if(num[i] <= num[j]){
                temp[t++] = num[i++];
            }
            else {
                temp[t++] = num[j++];
            }
        }
        // 把左边剩余的数移入数组
        while(i <= mid){
            temp[t++] = num[i++];
        }
        // 把右边边剩余的数移入数组
        while(j <= right){
            temp[t++] = num[j++];
        }
        // 把新数组中的数覆盖nums数组
        int k = 0;
        for (int tempLeft = left; tempLeft <= right; tempLeft++) {
            num[tempLeft] = temp[k++];
        }
            System.out.println(Arrays.toString(num));
    }
}
