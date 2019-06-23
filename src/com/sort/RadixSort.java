package com.sort;

/**
 * 实现基数排序(空间换时间的经典算法)：
 * 基本是最快的排序
 * 思想：
 * 将所有待比较数值统一为同样的数位长度，数位较短的数前面补零。
 * 然后，从最低位开始，依次进行一次排序。
 * 这样从最低位排序一直到最高位排序完成以后, 数列就变成一个有序序列。
 */
public class RadixSort {
    public static void main(String[] args) {
        int[] num = new int[]{4,55,894,77,12,125,86,1,100,1024,522,8};
        num = RadixSort.sort(num);

        for(int i : num){
            System.out.print(i + " ");
        }
    }

    public static int[] sort(int[] num){
        /**找出数组中最大的数
         * 最大数的位数，决定了要进行多少轮
         * */
        int max = num[0];
        for (int i = 0; i < num.length; i++) {
            if(max < num[i]){
                max = num[i];
            }
        }
        /**得到最大数是位数,位数决定一共要进行几轮*/
        int maxLength = (max + "").length();

        /**定义一个二维数组，表示10桶，每个一维数组就是一个桶*/
        int[][] buckets = new int[10][num.length];
        /**为了记录每个桶中实际存放了多少个数据
         * 我们定义一个一维数组来记录各个桶每次放入的数据个数
         * 如：bucketElemCount[0]=3 记录的就是第0个桶中数据的个数为3条
         * */
        int[] bucketElemCount = new int[10];

        for (int k = 0,n = 1;k < maxLength; k++,n *= 10) {
            /**
             * 针对每个元素的位数进行排序，第一次是个位
             * 第二次是十位
             * 第三次是百位
             */
            for (int i = 0; i < num.length; i++) {
                /**取出每个元素的位数上数，开始从个数开始*/
                int digitOfElem = num[i] /n % 10;
                /**放入对应的桶中*/
                buckets[digitOfElem][bucketElemCount[digitOfElem]] = num[i];
                /**每往桶里放一个数，就要统计该桶中有存放了多少个数据*/
                bucketElemCount[digitOfElem]++;
            }
            /**按照桶的顺序将桶中元素放入原来的数组中*/
            int index = 0;
            /**遍历这十个桶，将桶中数据依次放回到原数组中*/
            for (int i = 0; i < buckets.length; i++) {
                /**如果桶中有数据，才放入到原数组中*/
                if(0 != bucketElemCount[i]) {
                    for (int j = 0; j < bucketElemCount[i]; j++) {
                        num[index++] = buckets[i][j];
                    }
                    /**每轮处理后，需要要将每个桶清为0，将桶中数据清空*/
                    bucketElemCount[i] = 0;
                }
            }
        }
        return num;
    }
}
