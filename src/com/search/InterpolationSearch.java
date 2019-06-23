package com.search;

import java.util.ArrayList;
import java.util.List;

/**
 * 实现插值查找算法（二分查找的一种改进）也是要求被查找的数据是有序的
 * 对于数据量较大，关键字分布比较均匀的查找表来说，采用插值查找, 速度较快.
 * 关键字分布不均匀的情况下，该方法不一定比折半查找要好
 */
public class InterpolationSearch {
    public static void main(String[] args) {
        int[] num = new int[]{2,2,2,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,2,25};
        List<Integer> list = InterpolationSearch.search(num,0,num.length - 1,2);

        for(Integer i : list){
            System.out.print(i + " ");
        }
    }

    public static List<Integer> search(int[] num,int left,int right,int value){
        /**mid 是一个自适应的值*/
        int mid = left + (right - left) * (value - num[left]) / (num[right] - num[left]);
        /**
         * 注意：
         * value < num[0] || value > num[num.length - 1]
         * 这个判断必须要有，否则 mid 可能会越界
         */
        if(left > right || value < num[0] || value > num[num.length - 1]){
            return null;
        }

        if(num[mid] > value){
            return InterpolationSearch.search(num,left,mid - 1,value);
        }
        else if(num[mid] < value){
            return InterpolationSearch.search(num,mid + 1,right,value);
        }
        else {
            List<Integer> list = new ArrayList<>();
            /**向左查找*/
            int t = mid - 1;
            while(true){
                if(t < 0){
                    break;
                }
                if(num[t] == value){
                    list.add(t);
                }
                t -= 1;
            }
            list.add(mid);
            /**向右查找*/
            t = mid + 1;
            while(true){
                if(t > num.length - 1){
                    break;
                }
                if(num[t] == value){
                    list.add(t);
                }
                t += 1;
            }
            return list;
        }
    }
}
