package com.algorithm;

/**
 * 实现迪杰斯特拉算法，求最短路径
 *  迪杰斯特拉(Dijkstra)算法是典型最短路径算法，用于计算一个结点到其他结点的最短路径。
 *  它的主要特点是以起始点为中心向外层层扩展(广度优先搜索思想)，直到扩展到终点为止.
 */
public class DijkstraAlgorithmDemo {
    public static void main(String[] args) {
        final int N = 65536;
        char[] vertexs = new char[]{'A','B','C','D','E','F','G'};
        int[][] matrix = new int[][]{
                {N,5,7,N,N,N,2},
                {5,N,N,9,N,N,3},
                {7,N,N,N,8,N,N},
                {N,9,N,N,N,4,N},
                {N,N,8,N,N,5,4},
                {N,N,N,4,5,N,6},
                {2,3,N,N,4,6,N}
        };
        MyGraph myGraph = new MyGraph(vertexs,matrix);
        myGraph.showGraph();
    }
}
/**创建图类*/
class MyGraph {
    /**图中顶点数组*/
    char[] vertexs;
    /**图对应的邻接矩阵*/
    int[][] matrix;
    public MyGraph(char[] vertexs,int[][] matrix){
        this.vertexs = vertexs;
        this.matrix = matrix;
    }
    /**显示图*/
    public void showGraph(){
        System.out.println("图对应的二维邻接矩阵");
        for(int[] row : matrix){
            for(int col : row){
                System.out.printf("%8d",col);
            }
            System.out.println();
        }
    }
}