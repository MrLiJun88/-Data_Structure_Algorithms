package com.algorithm;
/**
 * 实现二分查找算法（非递归）
 */
public class BinarySearchDemo {
    public static void main(String[] args) {
        int[] arr = new int[]{1,3, 8, 10, 11, 67, 100};
        int index = BinarySearchDemo.binarySearch(arr,8);
        System.out.println(index);
    }
    /**二分查找非递归实现
     * arr 待查找的数组，默认是升序的状态
     * target 要查找的数
     * 返回下标 -1代表没有找到
     * */
    public static int binarySearch(int[] arr,int target){
        int left = 0;
        int right = arr.length - 1;

        while(left <= right){
            int mid = (left + right) / 2;
            /**找对即返回该下标*/
            if(target == arr[mid]){
                return mid;
            }
            /**向左边查找*/
            else if(arr[mid] > target){
                right = mid - 1;
            }
            /**向右边查找*/
            else {
                left = mid + 1;
            }
        }
        return -1;
    }
}
