package com.tree;

/**
 * 实现平衡二叉树（AVL）
 */
public class BalancedBinaryTree {
    public static void main(String[] args) {
        int[] arr = new int[]{10,12,8,9,7,6};
        AVLTree avlTree = new AVLTree();
        for (int i = 0; i < arr.length; i++) {
            avlTree.add(new LeafNode(arr[i]));
        }
        System.out.println("树的高度 " + avlTree.root.getTreeHeight());
        System.out.println("左子树的高度 " + avlTree.root.getLeftHeight());
        System.out.println("右子树的高度 " + avlTree.root.getRightHeight());
        avlTree.midOrder();
        System.out.println("当前根节点 " + avlTree.root);
    }
}
/**创建AVL树*/
class AVLTree {
    /**定义根节点信息*/
    LeafNode root;
    /**添加节点*/
    public void add(LeafNode node){
        if (null == root){
            root = node;
            return;
        }
        else {
            root.addNode(node);
        }
    }
    /**中序遍历二叉排序树*/
    public void midOrder(){
        if(null == root){
            System.out.println("当前是空树，无法遍历");
            return;
        }
        System.out.println("中序遍历二叉排序树");
        this.root.infixOrder();
    }
}