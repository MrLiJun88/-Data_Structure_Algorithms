package com.tree;
/**
 * 实现二叉树的前，中，后序三种遍历方式
 * 以及通过指定的节点 id 来前，中，后序来查找节点信息
 *
 * 实现节点的删除：
 * 如果删除的节眯是叶子节点，则删除该节点
 * 如果删除的节点是非叶子节点，则删除该子树
 * 思路：
 * 因为我们的二叉树是单向的，所以我们是判断当前节点的子节红开是否需要删除，而不能
 * 判断当前这个节点是否需要删除
 * 如果当前节点的左子节点不为空，并且左子节点就是要删除的节点，就将this.left = null,并且返回结束递归删除
 * 如果当前节点的右子节点不为空，并且右子节点就是要删除的节点，就this.right = null ,并且返回结束递归删除
 * 如果2，3两步都没有删除节点，那么我们就需要向左子树进行递归删除
 * 如果第4步也没有删除节点，则应当向右子树进行递归删除
 * 如果该树是空树 ,即可以一个root节点，则将二叉树置空
 *
 */
public class BinaryTree {
    public static void main(String[] args) {
        Node root = new Node(1,"root");
        Node node1 = new Node(2,"lijun");
        Node node2 = new Node(3,"wangwu");
        Node node3 = new Node(4,"zhangsan");
        Node node4 = new Node(5,"hello");
        Node node5 = new Node(6,"world");
        Node node6 = new Node(7,"vvvv");
        Node node7 = new Node(8,"kkkkk");

        root.left = node1;
        root.right = node2;
        node1.left = node3;
        node1.right = node4;
        node2.left = node5;
        node3.left = node6;
        node3.right = node7;
        BinTree tb = new BinTree(root);
        tb.preOrder();
//        System.out.println("--------------");
//        tb.midOrder();
//        System.out.println("--------------");
//        tb.postOrder();
//        tb.findByPre(3);
//        tb.findByMid(5);
//        tb.findByPost(5);
//        tb.deleteById2(4);
        System.out.println("---------------");
        tb.preOrder();
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

    public void findByPre(int id){
        if(null != this.root){
            System.out.println(this.root.preFind(id));
        }
        else {
            System.out.println("找不到该节点信息");
        }
    }
    /**中序查找指定节点*/
    public void findByMid(int id){
        if(null != this.root){
            System.out.println(this.root.midFind(id));
        }
        else {
            System.out.println("找不到该节点信息");
        }
    }
    /**后序查找指定节点*/
    public void findByPost(int id){
        if(null != this.root){
            System.out.println(this.root.postFind(id));
        }
        else {
            System.out.println("找不到该节点信息");
        }
    }
    /**删除指定的节点*/
    public void deleteById(int id){
        if(null != this.root){
            /**如果根节点就是要删除的节点，则直接置空*/
            if(id == this.root.id){
                this.root = null;
                return;
            }
            this.root.delete(id);
        }
        else {
            System.out.println("这是一棵空树，无法删除");
        }
    }

//    public void deleteById2(int id){
//        if(null != this.root){
//            /**如果根节点就是要删除的节点，则直接置空*/
//            if(id == this.root.id){
//                this.root = null;
//                return;
//            }
//            this.root.delete2(id);
//        }
//        else {
//            System.out.println("这是一棵空树，无法删除");
//        }
//    }
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

    /**前序查找指定的节点*/
    public Node preFind(int id){
        System.out.println("进入前序遍历");
        if(id == this.id){
            return this;
        }
        /**定义resNode，用来保存找到后返回回来的节点*/
        Node resNode = null;
        if(null != this.left){
            resNode = this.left.preFind(id);
        }
        /**如果resNode不为null，说明已经找到了，不用再继续找，直接返回*/
        if(null != resNode){
            return resNode;
        }
        if(null != this.right){
            resNode = this.right.preFind(id);
        }
        return resNode;
    }
    /**中序查找指定的节点*/
    public Node midFind(int id){
        Node resNode = null;
        /**先在左子树上遍历*/
        if(null != this.left){
            resNode = this.left.midFind(id);
        }
        /**如果在左子树上找到，则直接返回*/
        if(null != resNode){
            return resNode;
        }
        System.out.println("进入中序遍历");
        if(id == this.id){
            return this;
        }
        if(null != this.right){
            resNode = this.right.midFind(id);
        }
        return resNode;
    }
    /**后序查找指定的节点*/
    public Node postFind(int id){
        Node resNode = null;
        if(null != this.left){
            resNode = this.left.postFind(id);
        }
        /**说明在左子树上*/
        if(null != resNode){
            return resNode;
        }
        if(null != this.right){
            resNode = this.right.postFind(id);
        }
        if(null != resNode){
            return resNode;
        }
        System.out.println("进入后序遍历");
        if(id == this.id){
            return this;
        }
        return resNode;
    }
    /**删除指定节点*/
    public void delete(int id){
        if(null != this.left && id == this.left.id){
            this.left = null;
            return;
        }
        if(null != this.right && id == this.right.id){
            this.right = null;
            return;
        }
        /**递归向左查找删除*/
        if(null != this.left){
            this.left.delete(id);
        }
        /**递归向右查找删除*/
        if(null != this.right){
            this.right.delete(id);
        }
    }

    /**
     * 如果要删除的节点有左右子节点，则用该节点的左子节点来代替原来的位置，
     * 如果要删除的节点左右只存在一个子节点，则用它唯一的子节点来代替原来的位置
     * @param id
     */


}