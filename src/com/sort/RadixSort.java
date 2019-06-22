package com.sort;

/**
 * 实现基数排序(空间换时间的经典算法)
 *
 */
public class RadixSort {
    public static void main(String[] args) {
        int[] num = new int[]{4,1,2,-1,5,9,0,7,12,3};
        num = RadixSort.sort(num);

        for(int i : num){
            System.out.print(i + " ");
        }
    }

    public static int[] sort(int[] num){
        /**定义一个二维数组，表示10桶，每个一维数组就是一个桶*/
        int[][] buckets = new int[10][num.length];
        /**为了记录每个桶中实际存放了多少个数据
         * 我们定义一个一维数组来记录各个桶每次放入的数据个数
         * 如：bucketElemCount[0]=3 记录的就是第0个桶中数据的个数为3条
         * */
        int[] bucketElemCount = new int[10];

        for (int i = 0; i < num.length; i++) {
            /**取出每个元素的个位*/
            int digitOfElem = num[i] % 10;
            /**放入对应的桶中*/
            buckets[digitOfElem][bucketElemCount[digitOfElem]] = num[i];
            bucketElemCount[digitOfElem]++;
        }
        /**按照桶的顺序将桶中元素放入原来的数组中*/
        int index = 0;
        for (int i = 0; i < buckets.length; i++) {
            /**如果桶中有数据，才放入到原数组中*/
            if(0 != buckets[i].length) {
                for (int j = 0; j < buckets[i].length; j++) {
                    num[index++] = buckets[i][j];
                }
                /**每轮处理后，需要要将每个桶清为0，将桶中数据清空*/
                bucketElemCount[i] = 0;
            }
        }

        return num;
    }
}
