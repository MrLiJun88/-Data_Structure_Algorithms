package com.algorithm;

/**
 * 实现普里姆算法，求最小生成树（最小连通子图）
 */
public class PrimAlgorithmDemo {
    public static void main(String[] args) {
        /**定义图的顶点*/
        char[] data = new char[]{'A','B','C','D','E','F','G'};
        /**定义图顶点的个数*/
        int vertexs = data.length;
        /**表示图顶点间的距离，-1 代表不联通 */
        int[][] weight = new int[][]{
                {-1,5,7,-1,-1,-1,2},
                {5,-1,-1,9,-1,-1,3},
                {7,-1,-1,-1,8,-1,-1},
                {-1,9,-1,-1,-1,4,-1},
                {-1,-1,8,-1,-1,5,4},
                {-1,-1,-1,4,5,-1,6},
                {2,3,-1,-1,4,6,-1}
        };
        /**创建图实例对象*/
        Graph graph = new Graph(vertexs);
        /**创建最小生成树*/
        MinTree minTree = new MinTree();
        minTree.createGraph(graph,vertexs,data,weight);
        minTree.showGraph(graph);
    }
}
/**创建最小生成树->村庄的图*/
class MinTree {
    /**
     *创建图对应的邻接矩阵
     * @param graph  图的对象
     * @param vertexs 图的顶点个数
     * @param data  顶点的权值
     * @param weight  图对应的邻接矩阵
     */
    public void createGraph(Graph graph,int vertexs,char[] data,int[][] weight){
        for (int i = 0; i < vertexs; i++) {
            graph.data[i] = data[i];
            for (int j = 0; j < vertexs; j++) {
                graph.weight[i][j] = weight[i][j];
            }
        }
    }
    /**显示图对应的邻接矩阵*/
    public void showGraph(Graph graph){
        for (int[] row : graph.weight) {
            for(int col : row){
                System.out.print(col + " ");
            }
            System.out.println();
        }
    }
}
/**创建图*/
class Graph {
    /**图顶点个数*/
    int vertexs;
    /**存放节点数据*/
    char[] data;
    /**存放边，即 邻接矩阵*/
    int[][] weight;
    public Graph(int vertexs){
        this.vertexs = vertexs;
        data = new char[vertexs];
        weight = new int[vertexs][vertexs];
    }
}