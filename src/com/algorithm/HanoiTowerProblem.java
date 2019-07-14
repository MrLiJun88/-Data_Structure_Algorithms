package com.algorithm;
/**
 * 使用分治算法的思想来解决汉诺塔问题
 */
public class HanoiTowerProblem {
    public static void main(String[] args) {
        HanoiTowerProblem.hanoiTower(5,'A','B','C');
    }
    /**
     * @param num 共有多少个盘
     * @param a  柱子a
     * @param b  柱子b
     * @param c  柱子c
     */
    public static void hanoiTower(int num,char a,char b,char c){
        /**如果只有一个盘，则直接将从A移到C*/
        if(1 == num){
            System.out.println("第1个盘从 " + a +" -> " + c);
        }
        /**
         * 如果 num >= 2 ,我们总可以看作是两个盘，
         * 1.最下的盘，上面的盘
         * 2.先把最上面的盘 A->B
         * 3.把最下面的盘 A->C
         * 4.将B塔中所有的盘从B->C
         */
        else {
            HanoiTowerProblem.hanoiTower(num - 1,a,c,b);
            System.out.println("第" + num + "个盘从 " + a + " -> " + c);
            HanoiTowerProblem.hanoiTower(num - 1,b,a,c);
        }
    }
}
