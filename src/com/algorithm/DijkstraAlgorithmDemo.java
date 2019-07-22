package com.algorithm;

import java.util.Arrays;

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
//        myGraph.showGraph();
        myGraph.djs(2);
        myGraph.show();
    }
}
/**创建图类*/
class MyGraph {
    /**图中顶点数组*/
    char[] vertexs;
    /**图对应的邻接矩阵*/
    int[][] matrix;
    /**已经访问过的顶点的集合*/
    VisitedVertex visitedVertex;
    public MyGraph(char[] vertexs,int[][] matrix){
        this.vertexs = vertexs;
        this.matrix = matrix;
    }
    /**显示图*/
    public void showGraph() {
        System.out.println("图对应的二维邻接矩阵");
        for (int[] row : matrix) {
            for (int col : row) {
                System.out.printf("%8d", col);
            }
            System.out.println();
        }
    }
    /**实现迪杰斯特拉算法
     * @param index 表示出发顶点对应的下标
     */
    public void djs(int index){
        visitedVertex = new VisitedVertex(vertexs.length,index);
        /**更新index下标对应的顶点到其他顶点的距离和前驱顶点*/
        this.update(index);
        /**选择并返回新的出发访问顶点*/
        for (int i = 1; i < vertexs.length; i++) {
            index = visitedVertex.updateArr();
            this.update(index);
        }
    }
    /**更新index下标对应的顶点到其他顶点的距离，其他顶点的前驱顶点*/
    public void update(int index){
        int len =0;
        for (int i = 0; i < matrix[index].length; i++) {
            /**len 出发顶点到index顶点的距离 + index顶点到i顶点的距离和*/
            len = visitedVertex.getDis(index) + matrix[index][i];
            /**如果i顶点没有被访问过，，并且len小于出发顶点到i顶点的距离，就需要更新*/
            if(! visitedVertex.isVisited(i) && len < visitedVertex.getDis(i)){
                visitedVertex.updateDis(i,len);
                visitedVertex.updatePre(i,index);
            }
        }
    }
    /**显示最终结果*/
    public void show(){
        visitedVertex.showResult();
    }
}
/**已经访问过的顶点集合*/
class VisitedVertex {
    /**记录各个顶点是否已经访问过了，1：已访问过 0：未访问过*/
    public int[] already_visited;
    /**每个下标对应的值为前一个顶点下标，为动态更新*/
    public int[] pre_vertex;
    /**记录出发点到其他所有顶点距离，并将最短的路径距离放在dis数组中*/
    public int[] distance;

    /**
     * 构造器
     * @param length  个数，图中顶点的个数
     * @param index   出发顶点的下标，从哪个顶点做为出发顶点
     */
    public VisitedVertex(int length,int index){
        this.already_visited = new int[length];
        this.pre_vertex = new int[length];
        this.distance = new int[length];
        /**初始化dis数组,设置出发顶点到其他顶点的距离为65535，与自己的距离则为0*/
        Arrays.fill(distance,65535);
        /**设置与自己的顶点距离为0*/
        this.distance[index] = 0;
        /**设置出发顶点被访问过*/
        this.already_visited[index] = 1;
    }
    /**判断index下标对应的顶点是否被访问过，如果访问过就返回true*/
    public boolean isVisited(int index){
        return 1 == already_visited[index];
    }
    /**
     * 更新出发顶点到index对应顶点的距离
     * @param index index下标对应的顶多
     * @param dis  出发顶点到index对应顶点的距离
     */
    public void updateDis(int index,int dis){
        distance[index] = dis;
    }
    /**
     *更新前驱顶点
     * @param pre 待更新的前驱顶点下标
     * @param index  新的前驱顶点下标值
     */
    public void updatePre(int pre,int index){
        pre_vertex[pre] = pre_vertex[index];
    }
    /**返回初始顶点到index对应顶点的距离*/
    public int getDis(int index){
        return distance[index];
    }
    /**继续选择并返回新的开始顶点*/
    public int updateArr(){
        int min = 65535;
        int index = 0;
        for (int i = 0; i < already_visited.length; i++) {
            /**找到还没有被访问过的顶点，并且路径值最小的*/
            if(0 == already_visited[i] && distance[i] < min){
                min = distance[i];
                index = i;
            }
        }
        /**将index顶点标记已被访问*/
        already_visited[index] = 1;
        return index;
    }
    /**显示最后的结果*/
    public void showResult(){
        System.out.println("已经访问过的顶点 " + Arrays.toString(already_visited));
        System.out.println("前驱顶点 " + Arrays.toString(pre_vertex));
        System.out.println("最终的最短路径 " + Arrays.toString(distance));
    }
}