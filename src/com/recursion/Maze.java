package com.recursion;

/**
 * 用递归来解决迷宫问题
 */
public class Maze {
    public static void main(String[] args) {
        /**
         * 创建一个二维数组来模拟迷宫
         * 用 1 代替迷宫四周的墙
         */
        int[][] maze = new int[8][7];
        for (int i = 0; i < 7; i++) {
            maze[0][i] = 1;
            maze[7][i] = 1;
        }
        maze[3][1] = maze[3][2] = 1;
        maze[1][2] = maze[2][2] = 1;

        for (int i = 0; i < 8; i++) {
            maze[i][0] = 1;
            maze[i][6] = 1;
        }
        for(int[] i : maze){
            for(int j : i){
                System.out.print(j + " ");
            }
            System.out.println();
        }
        System.out.println("------------------------------");

        /**输出走过并标识过的地图*/
        Maze.findWay(maze,1,1);
        for(int[] i : maze){
            for(int j : i){
                System.out.print(j + " ");
            }
            System.out.println();
        }
    }
    /**
     *使用递归回溯来给小球找路
     * 约定：
     * 1.maze表示迷宫地图
     * 2.i,j表示从地图的哪个位置开始出发 (1,1)
     * 3.如果小球能到 maze[6][5]位置，则说明通路找到
     * 4.当 maze[i][j]为0时，表示该点还没有走过，为1时
     * 表示是墙，为2表示为通路，可以走
     * 1：为墙
     * 2：已经走过的线
     * 3：已经走过但是是死线
     *
     * 策略：
     * 下  右  上  左，如果该点走不通，则回溯
     *
     * @param maze:表示迷宫地图
     * @param i：从哪个位置上开始找
     * @param j：从哪个位置上开始找
     * @return：找到通路就返回 true,找不到返回 false
     */
    public static boolean findWay(int[][] maze,int i,int j){
        /**说明出口已经找到了*/
        if(maze[6][5] == 2){
            return true;
        }
        else {
            /**为0 则表示当前这个点还没有走过
             * 则可以按照我们约定的策略来走
             * */
            if(0 == maze[i][j]){
                /**先假定该点是可以走通的*/
                maze[i][j] = 2;
                /**先假定为通路，则按照我们的约定向下走，即向下递归*/
                if(Maze.findWay(maze,i + 1,j)){
                    return true;
                }
                /**向右走*/
                else if(Maze.findWay(maze,i,j + 1)){
                    return true;
                }
                /**向上走*/
                else if(Maze.findWay(maze,i - 1,j)){
                    return true;
                }
                /**向左走*/
                else if(Maze.findWay(maze,i,j - 1)){
                    return true;
                }
                else {
                    /**到这一步，说明该点是走不通的，是死路*/
                    maze[i][j] = 3;
                    return false;
                }
            }
            /**如果maze[i][j] != 0 ,则有可能是 1,2,3*/
            else {
                return false;
            }
        }
    }
}
