package com.sparsearray;

/***
 * 稀疏数组：
 * 二维数组转稀疏数组的思路
 * 遍历原始的二维数组，得到有效数据的个数 sum
 * 根据sum就可以创建稀疏数组sparseArr int[sum + 1][3]
 * 将二维数组有效数据存入到稀疏数组
 *
 * 稀疏数组转原始的二位数组的思路
 * 先读取稀疏数组的第一行，根据第一行的数据，创建原始的二维数组
 * 在读取稀疏数组后几行的数据，并赋值给原始的二维数组即可
 */
public class SpareArr {
    public static void main(String[] args) {
        /**先创建一个原始的二维数组 11 * 11
         * 0：没有棋子
         * 1：黑子
         * 2：蓝子
         */
        int[][] arr = new int[11][11];
        arr[1][2] = 1;
        arr[2][3] = 2;
        arr[9][0] = 2;
        /**输出原始的二维数组*/
        System.out.println("原始数组");
        for(int[] row : arr){
            for(int data : row){
                System.out.print(data + " ");
            }
            System.out.println();
        }
        /**将原始二维数组转成稀疏数组
         * 1.先遍历二维数组，得到非0数据的个数
         */
        int sum = 0;
        for(int i = 0;i < 11;i++){
            for(int j = 0;j < 11;j++){
                if(0 != arr[i][j]){
                    sum++;
                }
            }
        }
        /**创建稀疏数组*/
        int[][] spareArr = new int[sum + 1][3];
        /**给稀疏数组赋值*/
        spareArr[0][0] = 11;
        spareArr[0][1] = 11;
        spareArr[0][2] = sum;
        /**count:用来记录是第几个非0数据*/
        int count = 0;
        /**遍历二维数组，将非0的值存放到稀疏数组中*/
        for(int i = 0;i < 11;i++){
            for(int j = 0;j < 11;j++){
                if(0 != arr[i][j]){
                    count++;
                    spareArr[count][0] = i;
                    spareArr[count][1] = j;
                    spareArr[count][2] = arr[i][j];
                }
            }
        }

        /**输出稀疏数组*/
        System.out.println("稀疏数组");
        for(int i = 0;i < spareArr.length;i++){
            for (int j = 0;j < 3;j++){
                System.out.print(spareArr[i][j] + " ");
            }
            System.out.println();
        }
        /**将稀疏数组再转成原始数组*/
        System.out.println("将上述稀疏数组再转换成稀疏数组");
        int row = spareArr[0][0];
        int col = spareArr[0][1];

        int[][] arr2 = new int[row][col];

        for(int i = 1;i < spareArr.length;i++){
           row = spareArr[i][0];
           col = spareArr[i][1];
           int data = spareArr[i][2];
           arr2[row][col] = data;
        }

        /**输出还原后的原始数组*/
        for(int[] row2 : arr2){
            for(int data : row2){
                System.out.print(data + " ");
            }
            System.out.println();
        }
    }
}
