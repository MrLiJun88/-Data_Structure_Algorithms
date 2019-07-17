package com.algorithm;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 实现克鲁斯卡尔算法
 */
public class KruskalDemo {
    /**
     * edgeNums : 图的边个数
     * vertexs ：图的顶点数组
     * matrix ：图对应的邻接矩阵
     * INF ：表示图中两个顶点不联通
     */
    int edgeNums;
    char[] vertexs;
    int[][] matrix;
    static final int INF = Integer.MAX_VALUE;
    public static void main(String[] args) {
        char[] vertexs = new char[]{'A','B','C','D','E','F','G'};
        /**
         * matrix 图对应的邻接矩阵 0:表示自己与自己是不联通的
         * INF 表示两个顶点是不联通的
         */
        int[][] matrix = new int[][]{
                {0,12,INF,INF,INF,16,14},
                {12,0,10,INF,INF,7,INF},
                {INF,10,0,3,5,6,INF},
                {INF,INF,3,0,4,INF,INF},
                {INF,INF,5,4,0,2,8},
                {16,7,6,INF,2,0,9},
                {14,INF,INF,INF,8,9,0}
        };
        KruskalDemo kruskalDemo = new KruskalDemo(vertexs,matrix);
        kruskalDemo.printMatrix();
        System.out.println(Arrays.toString(kruskalDemo.getEdges()));
    }
    /**构造方法*/
    public KruskalDemo(char[] vertexs,int[][] matrix){
        /**图对应的顶点个数*/
        int vertexNum = vertexs.length;
        this.vertexs = new char[vertexNum];
        /**初始化图中顶点*/
        for (int i = 0; i < vertexs.length; i++) {
            this.vertexs[i] = vertexs[i];
        }
        /**初始化图对应的邻接矩阵*/
        this.matrix = new int[vertexNum][vertexNum];
        for (int i = 0; i < vertexNum;i++) {
            for (int j = 0; j < vertexNum; j++) {
                this.matrix[i][j] = matrix[i][j];
            }
        }
        /**统计边的条数*/
        for (int i = 0; i < vertexNum; i++) {
            for (int j = i + 1; j < vertexNum; j++) {
                if(INF != this.matrix[i][j]){
                    edgeNums++;
                }
            }
        }
    }
    /**显示图对应的邻接矩阵*/
    public void printMatrix(){
        System.out.println("图对应的邻接矩阵为： ");
        for(int[] row : matrix){
            for(int col : row){
                System.out.printf("%12d",col);
            }
            System.out.println();
        }
    }
    /**对边的权值进行升序*/
    private void sortEdgeByAsc(EData[] eDatas){
        Arrays.stream(eDatas).sorted(Comparator.comparing(EData::getWeight));
    }
    /**返回顶点在数组中对应的下标，否则返回 -1*/
    private int getPosition(char ch){
        for (int i = 0; i < vertexs.length; i++) {
            if(ch == vertexs[i]){
                return i;
            }
        }
        return -1;
    }
    /**获取图中的边，放在EData[]中，然后遍历该数组
     * 是matrix[][]邻接矩阵得到的
     * */
    private EData[] getEdges(){
        int index = 0;
        EData[] eDatas = new EData[edgeNums];
        for (int i = 0; i < matrix.length; i++) {
            /**j = i + 1 是将自己排除掉，自己不与自己相比*/
            for (int j = i + 1; j < matrix[i].length; j++) {
                if(INF != matrix[i][j]){
                    eDatas[index++] = new EData(vertexs[i],vertexs[j],matrix[i][j]);
                }
            }
        }
        return eDatas;
    }
}
/**创建一个类EData，它的实例对象就是一条边*/
class EData {
    /**
     * start: 边的一头顶点
     * end: 边的另一头顶点
     * weight: 边的权值
     */
    char start;
    char end;
    private int weight;
    public EData(char start,char end,int weight){
        this.start = start;
        this.end = end;
        this.weight = weight;
    }
    @Override
    public String toString() {
        return "EData[ start: " + this.start + " end: " + this.end + " weight: " + this.weight;
    }
    public int getWeight(){
        return this.weight;
    }
}