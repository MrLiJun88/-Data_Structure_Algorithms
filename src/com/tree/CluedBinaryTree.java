package com.tree;

/**
 * 线索二叉树
 */
public class CluedBinaryTree {
    public static void main(String[] args) {

    }
}
/**创建二叉树*/
class ClueBinTree {
    ClueNode root;
    ClueNode preNode;

    public ClueBinTree() {
    }
     /**赋值根节点*/

    public ClueBinTree(ClueNode root) {
        this.root = root;
    }

    /**中序线索化二叉树*/
    public void clueWithTree(ClueNode clueNode){
        if(null == clueNode){
            return;
        }
        /**向左边线索化*/
        this.clueWithTree(clueNode.left);

        if(null == clueNode.left){
            clueNode.left = preNode;
            clueNode.leftType = 1;
        }
        if(null != preNode && null == preNode.right){
            preNode.right = clueNode;
            preNode.rightType = 1;
        }
        preNode = clueNode;
        /**向右边线索化*/
        this.clueWithTree(clueNode.right);
    }
}
/**创建节点*/
class  ClueNode {
    int id;
    String name;
    ClueNode left;
    ClueNode right;
    /**
     * leftType 0:指向左子树  1：前驱节点
     * rightType 0:右子树  1：后继节点
     */
    int leftType;
    int rightType;

    public ClueNode() {
    }
    public ClueNode(int id, String name) {
        this.id = id;
        this.name = name;
    }
}