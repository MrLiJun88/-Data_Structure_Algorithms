package com.sort;

/**
 * 实现快速排序(冒泡排序的改进):
 * 思想;
 * 通过一趟排序将要排序的数据分割成独立的两部分，其中一部分的所有数据都比另外一部分的所有数据都要小，
 * 然后再按此方法对这两部分数据分别进行快速排序，整个排序过程可以递归进行，以此达到整个数据变成有序序列
 */
public class QuickSort {
    public static void main(String[] args) {
        int[] num = new int[]{4,1,2,-1,5,9,0,7,12,3};
        num = QuickSort.sort(num,0,num.length - 1);

        for(int i : num){
            System.out.print(i + " ");
        }
    }

    public static int[] sort(int[] num,int left,int right){

        int l = left;
        int r = right;
        int base = num[(left + right) / 2];

        while(l < r){
            while(num[l] < base){
                l++;
            }
            while(num[r] > base){
                r--;
            }
            /**
             * 如果 l >= r 说明base的左右两边的值，已经按照左边全是
             * 小于等于base的值，右边全是大于等于base的值
             */
            if(l >= r){
                break;
            }
            /**交换两数*/
            int temp = 0;
            temp = num[r];
            num[r] = num[l];
            num[l] = temp;

            if(num[l] == base){
                r--;
            }
            if(num[r] == base){
                l++;
            }
        }
        /**如果 l == r,则必须 l++,r--,否则会栈溢出*/
        if(l == r){
            r--;
            l++;
        }
        /**向左递归*/
        if(left < r){
            QuickSort.sort(num,left,r);
        }
        /**向右递归*/
        if(right > l){
            QuickSort.sort(num,l,right);
        }
        return num;
    }
}
