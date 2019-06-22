package com.search;

/**
 * 实现二分查找（必须已经是有序的数字）：
 *
 */
public class BinarySearch {
    public static void main(String[] args) {
        int[] num = new int[]{1,8,10,89,1000,234};
        int index = BinarySearch.search(num,89,0,num.length - 1);
        if(-1 != index){
            System.out.println(index);
        }
    }

    /**
     *
     * @param num 待查找的数组
     * @param value 要查找的数
     * @param left 左边开始下标
     * @param right 右边开始下标
     * @return 如果找到则返回下标,没有则返回 -1
     */
    public static int search(int[] num,int value,int left,int right){

        /**mid 中间下标*/
        int mid = (left + right) / 2;
        int midNum = num[mid];

        /**当left > right时说明没有找到，返回-1*/
        if(left > right){
            return -1;
        }
        /**向左边查询*/
        if(midNum > value){
            return BinarySearch.search(num,value,left,mid - 1);
        }
        /**向右边查询*/
        else if(midNum < value){
            return BinarySearch.search(num,value,mid + 1,right);
        }
        else {
            return mid;
        }
    }
}
