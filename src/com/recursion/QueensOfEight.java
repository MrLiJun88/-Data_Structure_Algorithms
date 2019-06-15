package com.recursion;

/**
 * 用一维数组结局八皇后问题
 */
public class QueensOfEight {
    /**定义一个max表示共有多少个皇后*/
    int max = 8;
    /**定义数组array，保存皇后放置位置的结果，比如arr={0,4,7,5,2,6,1,3}*/
    int[] array = new int[max];

    static int count = 0;

    public static void main(String[] args) {
        QueensOfEight queens = new QueensOfEight();
        queens.place(0);
        System.out.println("一共有 " + count + " 种解法");
    }

    /**将八皇后的位置信息输出*/
    public void print(){
        count++;
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " ");
        }
        System.out.println();
    }

    /**编写一个方法，放置第n个皇后*/
    public void place(int n){
        /**n = 8, 其实8个皇后就已经放好位置了*/
        if(max == n){
            this.print();
            return;
        }
        for (int i = 0; i < max; i++) {
            /**先把当前这个皇后n，放到该行的第1列*/
            array[n] = i;
            /**判断当前皇后在i列位置上是否冲突*/
            if(this.isConflict(n)){
                /**不冲突，接碰上放n+1的位置，即开始递归*/
                this.place(n + 1);
            }
            /**如果冲突，就继续执行array[n] = i，即将第n个皇后的位置在本行向后移一个，继续判断*/
        }
    }

    /**当我们放置第n个皇后时，检查该皇后位置是否与我们前面所放置皇后的位置冲突
     * @param n:表示第 n + 1个皇后
     */
    public boolean isConflict(int n){
        for (int i = 0; i < n; i++) {
            /**
             * array[i] == array[n] : 判断第n+1个皇后是否与前面i个皇后在同一列上
             * Math.abs(n - i) == Math.abs(array[n] - array[i]) ：判断第n + 1个皇后是否与前面i个皇后在同一斜线上
             * 例：n = 1放置在第2列  array[1] = 1
             * Math.abs(1-0) = 1 ; Math.abs(array[n] - array[i]) = Math.abs(array[1] - array[0]) = 1
             */
            if(array[i] == array[n] || Math.abs(n - i) == Math.abs(array[n] - array[i])){
                return false;
            }
        }
        return true;
    }
}
