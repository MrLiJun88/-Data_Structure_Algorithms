package com.algorithm;

import java.util.Arrays;

/**
 * 实现弗洛伊德算法
 * 设置顶点vi到顶点vk的最短路径已知为Lik，顶点vk到vj的最短路径已知为Lkj，
 * 顶点vi到vj的路径为Lij，则vi到vj的最短路径为：min((Lik+Lkj),Lij)，
 * vk的取值为图中所有顶点，则可获得vi到vj的最短路径
 * 至于vi到vk的最短路径Lik或者vk到vj的最短路径Lkj，是以同样的方式获得
 */
public class FreudAlgorithmDemo {
    public static void main(String[] args) {
        final int N = 65536;
        char[] vertexs = new char[]{'A','B','C','D','E','F','G'};
        int[][] matrix = new int[][]{
                {0,5,7,N,N,N,2},
                {5,0,N,9,N,N,3},
                {7,N,0,N,8,N,N},
                {N,9,N,0,N,4,N},
                {N,N,8,N,0,5,4},
                {N,N,N,4,5,0,6},
                {2,3,N,N,4,6,0}
        };
        Graph2 graph2 = new Graph2(vertexs.length,matrix,vertexs);
        graph2.freud();
        graph2.showArr();
    }
}
/**创建图*/
class Graph2 {
    /**图中顶点*/
    char[] vertexs;
    /**图中各个顶点到其他顶点的距离,最后结果也存放在该数组中*/
    int[][] distance;
    /**目标顶点的前驱顶点*/
    int[][] pre_vertex;

    /**
     *构造器
     * @param lenght 图中顶点的个数
     * @param matrix 图对应的邻接矩阵
     * @param vertexs 图中顶点的数组
     */
    public Graph2(int lenght,int[][] matrix,char[] vertexs){
        this.vertexs = vertexs;
        this.distance = matrix;
        this.pre_vertex = new int[lenght][lenght];
        for (int i = 0; i < lenght; i++) {
            Arrays.fill(pre_vertex[i],i);
        }
    }
    /**显示pre_vertex数组和distance数组*/
    public void showArr(){
        System.out.println("显示pre_vertext数组");
        for(int[] row : pre_vertex){
            System.out.println(Arrays.toString(row));
        }
        System.out.println("---------------------");
        System.out.println("显示distance数组");
        for(int[] row : distance){
            for (int col : row) {
                System.out.printf("%8d", col);
            }
            System.out.println();
        }
    }
    /**弗洛伊德算法*/
    public void freud(){
        /**dis存放顶点的距离*/
        int dis = 0;
        /**中间顶点*/
        for (int i = 0; i < distance.length; i++) {
            /**开始顶点*/
            for (int j = 0; j < distance.length; j++) {
                /**到达顶点*/
                for (int k = 0; k < distance.length; k++) {
                    /**dis等于从j顶点出发经过i顶点最后达到k顶点的距离*/
                    dis = distance[j][i] + distance[i][k];
                    if(dis < distance[j][k]){
                        /**更新距离*/
                        distance[j][k] = dis;
                        /**更新前驱顶点,即变成中间顶点*/
                        pre_vertex[j][k] = pre_vertex[i][k];
                    }
                }
            }
        }
    }
}