package com.graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
/**
 * 用邻接数组实现对图的存储
 *
 * 深度优先遍历，从初始访问结点出发，初始访问结点可能有多个邻接结点，
 * 深度优先遍历的策略就是首先访问第一个邻接结点，然后再以这个被访问的邻接结点作为初始结点，
 * 访问它的第一个邻接结点,可以这样理解：每次都在访问完当前结点后首先访问当前结点的第一个邻接结点。
 * 我们可以看到，这样的访问策略是优先往纵向挖掘深入，而不是对一个结点的所有邻接结点进行横向访问。
 * 显然，深度优先搜索是一个递归的过程
 *
 * 深度优先遍历算法步骤
 *   1.访问初始结点v，并标记结点v为已访问。
 *   2.查找结点v的第一个邻接结点w。
 *   3.若w存在，则继续执行4，如果w不存在，则回到第1步，将从v的下一个结点继续。
 *   4.若w未被访问，对w进行深度优先遍历递归（即把w当做另一个v，然后进行步骤123）。
 *   5.查找结点v的w邻接结点的下一个邻接结点，转到步骤3。
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
        System.out.println("图所对应的邻接矩阵");
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
        for (int i = v2; i < vertexList.size(); i++) {
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
            /**如果已经被访问过了，则对它的下一个邻接节点进行深度遍历*/
            else {
                w = this.getNextAdjacencyIndex(i,w + 1);
            }
        }
    }
    /**对dfs进行一个重载,遍历所有的顶点，并进行dfs*/
    public void dfs(){
        System.out.println("对图进行深度访问遍历");
        for (int i = 0; i < this.getVertexNums(); i++) {
            if(! isVisited[i]){
                this.dfs(isVisited,i);
            }
        }
    }
    /***广度优先遍历算法*/
    private void bfs(boolean[] isVisited,int i){
        /**index 表示队列的头节点对应的下标*/
        int index = 0;
        /**邻接节点的下标*/
        int w = 0;
        /**队列，记录节点访问的顺序*/
        LinkedList<Integer> queueList = new LinkedList();
        /**访问输出节点*/
        System.out.print(this.getValueByIndex(i) + " -> ");
        /**将节点标记已访问过*/
        isVisited[i] = true;
        /**将访问过的节点下标加入队列*/
        queueList.addLast(i);
        /**若队列不为空，则取出列表中的头节点*/
        while(! queueList.isEmpty()){
            /**取出头节点,并从列表中删除*/
            index = queueList.removeFirst();
            /**查找结点下标index的第一个邻接结点w*/
            w = this.getFirstAdjacencyIndex(index);
            while(-1 != w){
                /**
                 * 若节点没有被访问过
                 * 则访问输出，并标记为已被访问过，再加入已访问过的队列中
                 */
                if(! isVisited[w]){
                    System.out.print(this.getValueByIndex(w) + " -> ");
                    isVisited[w] = true;
                    queueList.addLast(w);
                }
                /**若节点已被访问过，则查找以index为前驱点，找w的下一个邻接节点*/
                else {
                    w = this.getNextAdjacencyIndex(index,w + 1);
                }
            }
        }
    }
    /**对bfs进行一个重载,遍历所有的顶点，并进行bfs*/
    public void bfs(){
        System.out.println("对图进行广度访问遍历");
        for (int i = 0; i < this.getVertexNums(); i++) {
            if(! isVisited[i]){
                this.bfs(isVisited,i);
            }
        }
    }
    public static void main(String[] args) {
        /**n 顶点的个数*/
        int n = 5;
        /**定义顶点，用String[]保存*/
        String[] vertexs = {"1","2","3","4","5","6","7","8"};
        GraphDemo graphDemo = new GraphDemo(8);
        /**添加顶点*/
        for (int i = 0; i < vertexs.length; i++) {
            graphDemo.addVertex(vertexs[i]);
        }
        /**添加边 A-B A-C B-C B-D B-E*/
        graphDemo.addEdge(0,1,1);
        graphDemo.addEdge(0,2,1);
        graphDemo.addEdge(1,3,1);
        graphDemo.addEdge(1,4,1);
        graphDemo.addEdge(3,7,1);
        graphDemo.addEdge(4,7,1);
        graphDemo.addEdge(2,5,1);
        graphDemo.addEdge(2,6,1);
        graphDemo.addEdge(5,6,1);
        /**显示图对应的邻接矩阵*/
        graphDemo.showGraph();
//        graphDemo.dfs();
        graphDemo.bfs();
    }
}
