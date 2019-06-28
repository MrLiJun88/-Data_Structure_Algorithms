package com.sort;

/**
 * 实现堆排序（用大顶堆实现对数组的升序排序）
 * 降序（小顶堆）
 */
public class HeapSort {
    public static void main(String[] args) {
        int[] num = new int[]{4,1,2,-1,5,9,0,7,12,3};
        num = HeapSort.sort(num);

        for(int i : num){
            System.out.print(i + " ");
        }
    }
    public static int[] sort(int[] num){

        /**将数组按非叶子节点调整成大顶堆*/
        for (int i = num.length / 2 - 1; i >= 0 ; i--) {
            HeapSort.adjustHeap(num,i,num.length);
        }

        for (int i = num.length - 1; i > 0;i--) {
            /**交换,num[0]中存放的是最大的值，与数组最后的值进行交换*/
            int temp = num[0];
            num[0] = num[i];
            num[i] = temp;
            /**每此调整大顶堆，都将数组最后范围开始减小*/
            HeapSort.adjustHeap(num,0,i);
        }
        return num;
    }

    /**
     * 将一个数组（二叉树）调成大顶堆
     * 方法功能：将 i 对应的非叶子节点在其数组中调整成大顶堆形式
     * @param num 待调整的数组
     * @param i  表示非叶子节点在数组中的索引下标
     * @param length  对多少个元素进行调整，因为每一次调整，个数在逐渐减少
     * @return
     */
    public static void adjustHeap(int[] num,int i,int length){
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
