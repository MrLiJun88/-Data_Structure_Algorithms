package com.tree;

/**
 * 顺序二叉树：
 * 思想：
 * 从数据存储来看，数组存储方式和树
 * 的存储方式可以相互转换，即数组可
 * 以转换成树，树也可以转换成数组，
 * 要求:
 * 右图的二叉树的结点，要求以数组的方式来存放 arr : [1, 2, 3, 4, 5, 6, 6]
 * 要求在遍历数组 arr时，仍然可以以前序遍历，中序遍历和后序遍历的方式完成结点的遍历
 */
public class ArrBinaryTree {
    public static void main(String[] args) {
        int[] arrTree = new int[]{1,2,3,4,5,6,7};
        ArrayBinaryTree tree = new ArrayBinaryTree(arrTree);
        tree.proOrder();
        System.out.println("顺序二叉树中序遍历");
        tree.midOrder(0);
        System.out.println("顺序二叉树后序遍历");
        tree.postOrder(0);
    }
}

class ArrayBinaryTree {
    int[] arrTree;
    public ArrayBinaryTree(int[] arrTree){
        this.arrTree = arrTree;
    }
    public void proOrder(){
        this.proOrder(0);
    }
    /**前序遍历*/
    public void proOrder(int index){
        if(null == arrTree || 0 == arrTree.length){
            System.out.println("数组为空,不能前序遍历");
            return;
        }
        /**输出当前这个元素*/
        System.out.print(arrTree[index] + " ");
        /**向左递归,并判断下标下能溢出*/
        if((2 * index + 1) < arrTree.length){
            this.proOrder(2 * index + 1);
        }
        /**向右递归，并判断下标不能溢出*/
        if((2 * index + 2) < arrTree.length){
            this.proOrder(2 * index + 2);
        }
    }
    /**中序遍历*/
    public void midOrder(int index){
        /**向左递归,并判断下标下能溢出*/
        if((2 * index + 1) < arrTree.length){
            this.midOrder(2 * index + 1);
        }
        /**输出当前这个元素*/
        System.out.print(arrTree[index] + " ");
        /**向右递归，并判断下标不能溢出*/
        if((2 * index + 2) < arrTree.length){
            this.midOrder(2 * index + 2);
        }
    }
    /**后序遍历*/
    public void postOrder(int index){
        /**向左递归,并判断下标下能溢出*/
        if((2 * index + 1) < arrTree.length){
            this.postOrder(2 * index + 1);
        }
        if((2 * index + 2) < arrTree.length){
            this.postOrder(2 * index + 2);
        }
        /**输出当前这个元素*/
        System.out.print(arrTree[index] + " ");
    }
}