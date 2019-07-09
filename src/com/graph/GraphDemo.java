package com.graph;

import java.util.ArrayList;
import java.util.List;
/**
 * 用邻接数组实现对图的存储
 */
public class GraphDemo {
    /**用于存储图中顶点的集合*/
    List<String> vertexList;
    /**用于存储图对应的邻接矩阵*/
    int[][] edges;
    /**表示图中边的个数*/
    int numOfEdges;
    /**标记某个顶点是否被访问过*/
    boolean[] isVisited;

    /**构造器，n 表示顶点个数*/
    public GraphDemo(int n){
        vertexList = new ArrayList<>(n);
        edges = new int[n][n];
        numOfEdges = 0;
        isVisited = new boolean[n];
    }
    /**添加顶点*/
    public void addVertex(String vertex){
        vertexList.add(vertex);
    }
    /**
     * 添加边
     * @param v1 二维数组中坐标,即第一个顶点的下标
     * @param v2 二维数组中坐标，即第二个顶点的下标
     * @param weight 权值，是 0 或者 是 1，描述两个顶点之间是否相通
     */
    public void addEdge(int v1,int v2,int weight){
        edges[v1][v2] = weight;
        /**因为是无向图，所以还要反过来赋值*/
        edges[v2][v1] = weight;
        /**每次添加，边的个数自加*/
        numOfEdges++;
    }
    /**返回图中顶点的个数*/
    public int getVertexNums(){
        return vertexList.size();
    }
    /**返回图中边的个数*/
    public int getNumOfEdges(){
        return  numOfEdges;
    }
    /**返回顶点i对应的值*/
    public String getValueByIndex(int i){
        return vertexList.get(i);
    }
    /**返回v1与v2两个顶点的权值*/
    public int getWeight(int v1,int v2){
        return edges[v1][v2];
    }
    /**显示图对应的矩阵*/
    public void showGraph(){
        for(int[] link : edges){
            for (int weight : link){
                System.out.print(weight + " ");
            }
            System.out.println();
        }
    }
    /**得到第一个邻接顶点的下标,否则返回 -1*/
    public int getFirstAdjacencyIndex(int index){
        for (int i = 0; i < vertexList.size(); i++) {
            if(edges[index][i] > 0){
                return i;
            }
        }
        return -1;
    }
    /**根据前一个邻接顶点的下标，来获取下一个邻接顶点,否则返回 -1*/
    public int getNextAdjacencyIndex(int v1,int v2){
        for (int i = v2 + 1; i < vertexList.size(); i++) {
            if(edges[v1][i] > 0){
                return i;
            }
        }
        return -1;
    }
    /**深度优先遍历算法*/
    private void dfs(boolean[] isVisited,int i){
        /**首先访问该顶点，即输出*/
        System.out.print(this.getValueByIndex(i) + " -> ");
        /**将已访问过的顶点，置为已访问*/
        isVisited[i] = true;
        /**查找顶点i的第一个邻接顶点*/
        int w = this.getFirstAdjacencyIndex(i);
        while(-1 != w){
            if(! isVisited[w]){
                this.dfs(isVisited,w);
            }
            else {
                w = this.getNextAdjacencyIndex(i,w);
            }
        }
    }
    /**对dfs进行一个重载,遍历所有的顶点，并进行dfs*/
    public void dfs(){
        for (int i = 0; i < this.getVertexNums(); i++) {
            if(! isVisited[i]){
                this.dfs(isVisited,i);
            }
        }
    }
    public static void main(String[] args) {
        /**n 顶点的个数*/
        int n = 5;
        /**定义顶点，用String[]保存*/
        String[] vertexs = {"A","B","C","D","E"};
        GraphDemo graphDemo = new GraphDemo(5);
        /**添加顶点*/
        for (int i = 0; i < vertexs.length; i++) {
            graphDemo.addVertex(vertexs[i]);
        }
        /**添加边 A-B A-C B-C B-D B-E*/
        graphDemo.addEdge(0,1,1);
        graphDemo.addEdge(0,2,1);
        graphDemo.addEdge(1,2,1);
        graphDemo.addEdge(1,3,1);
        graphDemo.addEdge(1,4,1);
        /**显示图对应的邻接矩阵*/
//        graphDemo.showGraph();
        graphDemo.dfs();
    }
}
