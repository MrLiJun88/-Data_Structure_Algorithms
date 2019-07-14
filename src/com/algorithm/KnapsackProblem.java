package com.algorithm;
/**
 * 使用动态规划思想解决背包问题
 */
public class KnapsackProblem {
    public static void main(String[] args) {
        /**物品的重量*/
        int[] weight = new int[]{1,4,3};
        /**物品的价值*/
        int[] value = new int[]{1500,3000,2000};
        /**背包的容量*/
        int capacity = 4;
        /**物品的个数*/
        int num = value.length;
        int[][] v = new int[num + 1][capacity + 1];
        int[][] path = new int[num + 1][capacity + 1];
        /**初始化第一行，第一列都为0*/
        for (int i = 0; i < v.length; i++) {
            v[i][0] = 0;
        }
        for (int i = 0; i < v[0].length; i++) {
            v[0][i] = 0;
        }
        /**从1开始，不处理第一行，因为第一行第一列都为0*/
        for (int i = 1; i < v.length; i++) {
            for (int j = 1; j < v[0].length; j++) {
                /**因为从1开始，所以公式要调整成 i - 1
                 * 如果要放入的物品比当前背包的容量大时，则将上次放入背包的物品放入背包中*/
                if(weight[i - 1] > j){
                    v[i][j] = v[i - 1][j];
                }
                else {
                    /**因为 i 是从1开始的，所以公式要调整成下面的代码*/
//                    v[i][j] = Math.max(v[i - 1][j],value[i - 1] + v[i - 1][j - weight[i - 1]]);
                    if(v[i - 1][j] < value[i - 1] + v[i - 1][j - weight[i - 1]]){
                        v[i][j] = value[i - 1] + v[i - 1][j - weight[i - 1]];
                        path[i][j] = 1;
                    }
                    else {
                        v[i][j] = v[i - 1][j];
                    }
                }
            }
        }
        for(int[] row : v){
            for(int col : row){
                System.out.print(col + " ");
            }
            System.out.println();
        }
        System.out.println("--------------------");
        int i = path.length - 1;
        int j = path[0].length - 1;
        while(i > 0 && j > 0){
            if(1 == path[i][j]){
                System.out.printf("第%d个商品放入背包中\n",i);
                j -= weight[i - 1];
            }
            i--;
        }
    }
}
