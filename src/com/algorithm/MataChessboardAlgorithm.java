package com.algorithm;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * 马踏棋盘算法
 */
public class MataChessboardAlgorithm {
    /**棋盘的列数*/
    static int X;
    /**棋盘的行数*/
    static int Y;
    /**创建一个数组，标记棋盘中各个位置是否被访问过*/
    static boolean isVisited[];
    /**使用一个属性，标记棋盘的所有位置都被访问过了,true表示成功，反之false*/
    static boolean isFinished;

    public static void main(String[] args) {
        X = 8;
        Y = 8;
        int row = 1;
        int col = 1;
        int step = 1;
        /**创建棋盘*/
        int[][] checkerboard = new int[X][Y];
        isVisited = new boolean[X * Y];
        long start = System.currentTimeMillis();
        MataChessboardAlgorithm.startWalking(checkerboard,row - 1,col - 1,step);
        long end = System.currentTimeMillis();
        System.out.println("运行时间: " + String.valueOf(end - start) + " sm");
        /**输出棋盘的最后情况*/
        for(int[] rows :checkerboard){
            System.out.println(Arrays.toString(rows));
        }
    }

    /**
     * 完成马踏棋盘算法的核心
     * @param checkerboard 棋盘
     * @param row  马当前位置的行，从0开始
     * @param col  马当前位置的列，从0开始
     * @param step 是第几步，初始是从1开始，后面递增
     */
    public static void startWalking(int[][] checkerboard,int row,int col,int step){
        System.out.println("游戏开始...");
        checkerboard[row][col] = step;
        /**标记该位置被访问过了*/
        isVisited[row * X + col] = true;
        /**获取当前位置可以走的下一个位置的集合*/
        ArrayList<Point> list = MataChessboardAlgorithm.next(new Point(row,col));
        /**遍历集合中的所有位置，走得通就走，走不通就回溯*/
        while(! list.isEmpty()){
            Point nextPoint = list.remove(0);
            /**判断该点是否已经被访问过*/
            if(!isVisited[nextPoint.x * X + nextPoint.y]){
                /**如果该点还没有被访问过，则从该点出发继续递归访问*/
                MataChessboardAlgorithm.startWalking(checkerboard,nextPoint.x,nextPoint.y,step + 1);
            }
        }
        /**如果走的步数小于棋盘中所有位置，则说明没有全部走完，算法不成功*/
        if(step < X * Y && !isFinished){
            /**将不成功的这点置为0*/
            checkerboard[row][col] = 0;
            /**将该点置为未访问*/
            isVisited[row * X + col] = false;
        }
        /**如果满足，则说明马已经走遍了棋盘上的所有位置，则将isFinished置为true*/
        else {
            isFinished = true;
        }
    }

    /**
     *根据当前位置，计算马还能走哪些位置，并放入到一个集合中(ArrayList)，最多有8个位置
     * @param curPoint 表示当前位置
     * @return  返回可以走的位置集合
     */
    public static ArrayList<Point> next(Point curPoint){
        ArrayList<Point> list = new ArrayList<>();
        /**创建一个Point*/
        Point point1 = new Point();
        /**如果当前位置向左移两列，向上移一行，大于0，则说明该位置是可以走的*/
        boolean condition = ((point1.x = curPoint.x - 2) >= 0 && (point1.y  = curPoint.y - 1) >= 0);
        if(condition){
            list.add(new Point(point1));
        }
        boolean condition2 = ((point1.x = curPoint.x - 1) >= 0 && (point1.y = curPoint.y - 2) >= 0);
        if(condition2){
            list.add(new Point(point1));
        }
        boolean condition3 = ((point1.x = curPoint.x + 1) < 0 && (point1.y = curPoint.y - 2) >= 0);
        if(condition3){
            list.add(new Point(point1));
        }
        boolean condition4 = ((point1.x = curPoint.x + 2) < X && (point1.y = curPoint.y - 1) >= 0);
        if(condition4){
            list.add(new Point(point1));
        }
        boolean condition5 = ((point1.x = curPoint.x + 2) < X && (point1.y = curPoint.y + 1) < Y);
        if(condition5){
            list.add(new Point(point1));
        }
        boolean condition6 = ((point1.x = curPoint.x + 1) < X && (point1.y = curPoint.y + 2) < Y);
        if(condition6){
            list.add(new Point(point1));
        }
        boolean condition7 = ((point1.x = curPoint.x - 1) >= 0 && (point1.y = curPoint.y + 2) < Y);
        if(condition7){
            list.add(new Point(point1));
        }
        boolean condition8 = ((point1.x = curPoint.x - 2) >= 0 && (point1.y = curPoint.y + 1) < Y);
        if(condition8){
            list.add(new Point(point1));
        }
        return list;
    }
}
