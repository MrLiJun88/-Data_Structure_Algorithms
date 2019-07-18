package com.algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

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
        kruskalDemo.kruskal();
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
    private EData[] sortEdgeByAsc(EData[] eDatas){
       List<EData> list = Arrays.stream(eDatas).sorted(Comparator.comparing(EData::getWeight)).collect(Collectors.toList());
       return list.toArray(EData[]::new);
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
    /**获取图中的边实例对象，放在EData[]中，然后遍历该数组
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

    /**
     * 功能：获取下标为i的顶点的终点，用于后面判断两个顶点的终点是否相同,即是否构成了回路
     * @param ends 存放各个顶点对应的终点是哪个，ends数组在遍历过程中，逐步形成
     * @param i  传入顶点对应的下标
     * @return  返回i顶点对应终点的下标
     */
    private int getEnd(int[] ends,int i){
        while(0 != ends[i]){
            i = ends[i];
        }
        return i;
    }
    /**算法实现*/
    public void kruskal(){
        /**定义边的start对应end顶点数组*/
        int[] ends = new int[edgeNums];
        EData[] eDatas = null;
        List<EData> results = new ArrayList<>();
        eDatas = this.getEdges();
        /**按照边的权值大小进行升序排序*/
        eDatas = this.sortEdgeByAsc(eDatas);
        /**将边添加到最小生成树，并判断要加入的边是否构成回路，如果没有就加入到树中，否则不能加入*/
        for (int i = 0; i < edgeNums; i++) {
            /**获取到第i条边对应的顶点下标*/
            int p1 = this.getPosition(eDatas[i].start);
            /**获取到第i条边对应的终点下标*/
            int p2 = this.getPosition(eDatas[i].end);
            /**获取这这p1在已有最小生成的中对应的终点是*/
            int m = this.getEnd(ends,p1);
            /**获取这这p2在已有最小生成的中对应的终点是*/
            int n = this.getEnd(ends,p2);
            /**判断构成回路,如果 n!=m，则没有构成回路*/
            if(n != m){
                /**设置m 在已有最小生成树中的终点*/
                ends[m] = n;
                /**将最小并不构成回路的边加入到结果集合中*/
                results.add(eDatas[i]);
            }
        }
        /**统计并打印最小生成树,即输出 result[]*/
        System.out.println("图对应的最小点生成树" + results);
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
        return "EData[ < " + this.start + " " + this.end + " > " + " weight: " + this.weight;
    }
    public int getWeight(){
        return this.weight;
    }
}