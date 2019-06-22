package com.search;

/**
 * 实现线性查找:
 * 逐一比对，发现有相同值时，就返回该下标
 */
public class SeqSearch {
    public static void main(String[] args) {
        int[] num = new int[]{4,0,2,-1,5,9,0,7,12,0};
        int result = SeqSearch.search(num,0);
        if(-1 != result){
            System.out.println(result);
        }
    }

    public static int search(int[] num,int value){
        for (int i = 0; i < num.length; i++) {
            if(value == num[i]){
                return i;
            }
        }
        return -1;
    }
}
