package com.search;

import java.util.ArrayList;
import java.util.List;

/**
 * 实现二分查找（必须已经是有序的数字）：
 *
 */
public class BinarySearch {
    public static void main(String[] args) {
        int[] num = new int[]{89,0,1,89,10,89,78,89,234};
        List<Integer> list = BinarySearch.search(num,89,0,num.length - 1);

        for(Integer i : list){
            System.out.print(i + " ");
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
    public static List<Integer> search(int[] num,int value,int left,int right){

        /**mid 中间下标*/
        int mid = (left + right) / 2;
        int midNum = num[mid];

        /**当left > right时说明没有找到，返回-1*/
        if(left > right){
            return null;
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
            /**
             * 如果要查询的数在数组中有多个，则用一个集合保存
             * 在找到后，不用立即返回，而是向该数的左边，右边扫描
             * 看看有没有相同的数，有则放入集合中，最后返回
             */
            List<Integer> list = new ArrayList<>();
            int temp = mid - 1;
            /**向左边扫描*/
            while(true){
                if(temp < 0){
                    break;
                }
                if(num[temp] == value){
                    list.add(temp);
                }
                temp--;
            }
            list.add(mid);
            /**向右边扫描*/
             temp = mid + 1;
            while(true){
                if(temp > num.length - 1){
                    break;
                }
                if(num[temp] == value){
                    list.add(temp);
                }
                temp++;
            }
            return list;
        }
    }
}
