package com.sort;

/**
 * 实现堆排序（用大顶堆实现对数组的升序排序）
 * 降序（小顶堆）
 * 堆排序的基本思想是：
 * 将待排序序列构造成一个大顶堆
 * 此时，整个序列的最大值就是堆顶的根节点。
 * 将其与末尾元素进行交换，此时末尾就为最大值。
 * 然后将剩余n-1个元素重新构造成一个堆，这样会得到n个元素的次小值。如此反复执行，便能得到一个有序序列了
 */
public class HeapSort {
    public static void main(String[] args) {
        int[] num = new int[]{4,1,2,-1,5,9,0,7,12,3};
        /**升序排序*/
//        num = HeapSort.sortAsc(num);
        /**降序排序*/
        num = HeapSort.sortDesc(num);

        for(int i : num){
            System.out.print(i + " ");
        }
    }
    /**升序排序*/
    public static int[] sortAsc(int[] num){

        /**将数组按非叶子节点调整成大顶堆*/
        for (int i = num.length / 2 - 1; i >= 0 ; i--) {
            HeapSort.adjustHeapForAsc(num,i,num.length);
        }

        for (int i = num.length - 1; i > 0;i--) {
            /**交换,num[0]每次存放的是调整后数组中的最大值，再与数组最后的值进行交换*/
            int temp = num[0];
            num[0] = num[i];
            num[i] = temp;
            /**每此调整大顶堆，都将数组最后范围开始减小*/
            HeapSort.adjustHeapForAsc(num,0,i);
        }
        return num;
    }
    /**降序排序*/
    public static int[] sortDesc(int[] num){
        /**将数组按非叶子节点调整成小顶堆*/
        for (int i = num.length / 2 - 1; i >= 0 ; i--) {
            HeapSort.adjustHeapForDesc(num,i,num.length);
        }
        for (int i = num.length - 1; i > 0;i--) {
            /**交换,num[0]每次存放的是调整后数组中的最小值，再与数组最后的值进行交换*/
            int temp = num[0];
            num[0] = num[i];
            num[i] = temp;
            /**每此调整大顶堆，都将数组最后范围开始减小*/
            HeapSort.adjustHeapForDesc(num,0,i);
        }
        return num;
    }

    /**
     *
     * @param num
     * @param i
     * @param length
     */
    public static void adjustHeapForDesc(int[] num,int i,int length){
        /**取出当前非叶子节点的值，保存在一个临时变量中*/
        int temp = num[i];
        /**j=i*2+1代表当前非叶子节点的左子节点*/
        for (int j = i * 2 + 1; j < length; j = j * 2 + 1) {
            /**用左子节点值与右子节点值比较，找出大的那个数
             * j + 1< length  控制在元素个数范围内
             * */
            if(j + 1 < length && num[j] > num[j + 1]){
                /**让j指向右子节点*/
                j++;
            }
            /**如果子节点小于该父节点，即父节点，左节点，右节点中最小的那个值*/
            if(num[j] < temp){
                /**将较大的值赋给当前节点*/
                num[i] = num[j];
                /**i = j 继续循环比较*/
                i = j;
            }
            /**如果不满足，则说明当前非叶子节点的值比它左右两节点值小，已经是最小的了*/
            else {
                break;
            }
        }
        /**当for循环结束后，我们已经将以 j 为父节点的值，放在在最顶上*/
        /**将temp放置到调整后的位置*/
        num[i] = temp;
    }
    /**
     * 将一个数组（二叉树）调成大顶堆
     * 方法功能：将 i 对应的非叶子节点在其数组中调整成大顶堆形式
     * @param num 待调整的数组
     * @param i  表示非叶子节点在数组中的索引下标
     * @param length  对多少个元素进行调整，因为每一次调整，个数在逐渐减少
     * @return
     */
    public static void adjustHeapForAsc(int[] num,int i,int length){
        /**取出当前非叶子节点的值，保存在一个临时变量中*/
        int temp = num[i];
        /**j=i*2+1代表当前非叶子节点的左子节点*/
        for (int j = i * 2 + 1; j < length; j = j * 2 + 1) {
            /**用左子节点值与右子节点值比较，找出大的那个数
             * j + 1< length  控制在元素个数范围内
             * */
            if(j + 1 < length && num[j] < num[j + 1]){
                /**让j指向右子节点*/
                j++;
            }
            /**如果子节点大于该父节点，即父节点，左节点，右节点中最大的那个值*/
            if(num[j] > temp){
                /**将较大的值赋给当前节点*/
                num[i] = num[j];
                /**i = j 继续循环比较*/
                i = j;
            }
            /**如果不满足，则说明当前非叶子节点的值比它左右两节点值大，已经是最大的了*/
            else {
                break;
            }
        }
        /**当for循环结束后，我们已经将以 j 为父节点的值，放在在最顶上*/
        /**将temp放置到调整后的位置*/
        num[i] = temp;
    }
}
