package com.tree;
/**
 * 实现二叉树的前，中，后序三种遍历方式
 */
public class BinaryTree {
    public static void main(String[] args) {
        Node root = new Node(1,"root");
        Node node1 = new Node(2,"lijun");
        Node node2 = new Node(3,"wangwu");
        Node node3 = new Node(4,"zhangsan");
        Node node4 = new Node(5,"hello");
        Node node5 = new Node(6,"world");

        root.left = node1;
        root.right = node2;
        node1.left = node3;
        node1.right = node4;
        node2.left = node5;
        BinTree tb = new BinTree(root);

        tb.preOrder();
        System.out.println("--------------");
        tb.midOrder();
        System.out.println("--------------");
        tb.postOrder();
    }
}
/**创建二叉树*/
class BinTree {
    Node root;
    public BinTree(){}
    /**赋值根节点*/
    public BinTree(Node root){
        this.root = root;
    }
    /**前序遍历*/
    public void preOrder(){
        if(null != this.root){
            this.root.preOrder();
        }
        else {
            System.out.println("当前二叉树为空树");
        }
    }
    /**中序遍历*/
    public void midOrder(){
        if(null != this.root){
            this.root.midOrder();
        }
        else {
            System.out.println("当前二叉树为空树");
        }
    }
    /**后序遍历*/
    public void postOrder(){
        if(null != this.root){
            this.root.postOrder();
        }
        else {
            System.out.println("当前二叉树为空树");
        }
    }
}
/**创建节点*/
class Node {
    int id;
    String name;
    Node left;
    Node right;
    public Node(){}
    public Node(int id,String name){
        this.id = id;
        this.name = name;
    }
    @Override
    public String toString() {
        return "Node[ id= " + this.id + " name= " + this.name + " ] ";
    }
    /**二叉树前序遍历*/
    public void preOrder(){
        System.out.println(this);
        /**向左边递归*/
        if(null != this.left){
            this.left.preOrder();
        }
        if(null != this.right){
            this.right.preOrder();
        }
    }
    /**中序遍历*/
    public void midOrder(){
        if(null != this.left){
            this.left.midOrder();
        }
        System.out.println(this);
        if(null != this.right){
            this.right.midOrder();
        }
    }
    /**后序遍历*/
    public void postOrder(){
        if(null != this.left){
            this.left.postOrder();
        }
        if(null != this.right){
            this.right.postOrder();
        }
        System.out.println(this);
    }
}