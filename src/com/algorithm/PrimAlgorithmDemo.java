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
        /**表示图顶点间的距离，10000 代表不联通 ,即图对应的邻接矩阵的二维数组*/
        int[][] weight = new int[][]{
                {10000,5,7,10000,10000,10000,2},
                {5,10000,10000,9,10000,10000,3},
                {7,10000,10000,10000,8,10000,10000},
                {10000,9,10000,10000,10000,4,10000},
                {10000,10000,8,10000,10000,5,4},
                {10000,10000,10000,4,5,10000,6},
                {2,3,10000,10000,4,6,10000}
        };
        /**创建图实例对象*/
        Graph graph = new Graph(vertexs);
        /**创建最小生成树*/
        MinTree minTree = new MinTree();
        minTree.createGraph(graph,vertexs,data,weight);
        minTree.showGraph(graph);
        minTree.prim(graph,0);
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

    /**
     * 实现Prim算法，得到图对应的最小生成树
     * @param graph  图
     * @param vertexIndex  从图中哪个顶点开始生成 A -> 对应下标0 B->对应下标 1
     */
    public void prim(Graph graph,int vertexIndex){
        /**visited用于标记每个顶点是否已经被访问过,默认都为0，表示没有访问过，1 表示已访问过*/
        int[] visited = new int[graph.vertexs];
        /**将当前的顶点标记为已访问*/
        visited[vertexIndex] = 1;
        /**v1Index,v2Index 用来记录两个顶点的下标*/
        int v1Index = -1;
        int v2Index = -1;
        /**minWeight 代表两顶点间，路径最短值*/
        int minWeight = 10000;
        /**因为根据prim算法，有n个顶点会产生n-1条边，所以 graph.vertexs - 1,从0开始*/
        for (int i = 0; i < graph.vertexs - 1; i++) {
            /**确定每次生成的子图，和哪个顶点的距离最近
             * j ： 假定已访问过的节点
             * k ： 假定未访问的节点
             * */
            for (int j = 0; j < graph.vertexs; j++) {
                for (int k = 0; k < graph.vertexs; k++) {
                    /**
                     *condition 比较已经访问过的顶点与未访问的顶点，
                     * 例 A 与 B比较
                     *   A 与 C 比较
                     *   A 与 D 比较，
                     *   最后找出A与BCD顶点间，路径最短的那条路，并记下与A顶点最短的那个顶点下标
                     */
                    boolean condition = (visited[j] == 1 && visited[k] == 0 && graph.weight[j][k] < minWeight);
                    if(condition){
                        minWeight = graph.weight[j][k];
                        v1Index = j;
                        v2Index = k;
                    }
                }
            }
            /**找到最小的一条边*/
            System.out.println("边< " + graph.data[v1Index] + " , " + graph.data[v2Index] + " > 权值: " + minWeight);
            /**将当前的节点标记为已访问*/
            visited[v2Index] = 1;
            /**重置minWeight值*/
            minWeight = 10000;
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