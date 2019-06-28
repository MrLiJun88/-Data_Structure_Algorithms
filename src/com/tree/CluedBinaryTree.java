package com.tree;

/**
 * 线索二叉树
 */
public class CluedBinaryTree {
    public static void main(String[] args) {
        ClueNode root = new ClueNode(1,"root");
        ClueNode cNode3 = new ClueNode(3,"lijun");
        ClueNode cNode8 = new ClueNode(8,"world");
        ClueNode cNode10 = new ClueNode(10,"hello");
        ClueNode cNode6 = new ClueNode(6,"www");
        ClueNode cNode14 = new ClueNode(14,"kkk");

        root.left = cNode3;
        root.right = cNode6;
        cNode3.left = cNode8;
        cNode3.right = cNode10;
        cNode6.left = cNode14;

        ClueBinTree tree = new ClueBinTree(root);
        /**将二叉树按中序线索化*/
//        tree.clueWithTree(root);
//        ClueNode testNode1 = cNode6.left;
//        ClueNode testNode2 = cNode6.right;
//        System.out.println(cNode6.id + " 号节点的前驱节点是：" + testNode1 );
//        System.out.println(cNode6.id + " 号节点的后继节点是：" + testNode2 );
//        tree.infixOrder();
//        tree.beforeOrder();
//        tree.preCludeWithTree(root);
//        ClueNode testNode1 = cNode8.left;
//        ClueNode testNode2 = cNode8.right;
//        System.out.println(cNode8.id + " 号节点的前驱节点是：" + testNode1 );
//        System.out.println(cNode8.id + " 号节点的后继节点是：" + testNode2 );
        tree.preCludeWithTree(root);
//        tree.beforeOrder();


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

    /**先序线索化二叉树*/
    public void preCludeWithTree(ClueNode clueNode){
        if(null == clueNode){
            return;
        }
        if(null == clueNode.left){
            clueNode.left = preNode;
            clueNode.leftType = 1;
        }
        if(null != preNode && null == preNode.right){
            preNode.right = clueNode;
            preNode.rightType = 1;
        }
        /**每处理一个节点后，让当前节点成为下一个节点的前驱节点*/
        preNode = clueNode;

        /**向左边线索化*/
        if (0 == clueNode.leftType) {
            this.preCludeWithTree(clueNode.left);
        }
        /**向右边线索化*/
        if(0 == clueNode.rightType){
            this.preCludeWithTree(clueNode.right);
        }
    }

    /**中序线索化二叉树*/
    public void clueWithTree(ClueNode clueNode){
        if(null == clueNode){
            return;
        }
        /**向左边线索化
         * 0 : 代表是子树
         * 1 ： 代表已经线索化过了
         * */
        if(0 == clueNode.leftType){
            this.clueWithTree(clueNode.left);

        }
        if(null == clueNode.left){
            clueNode.left = preNode;
            clueNode.leftType = 1;
        }
        if(null != preNode && null == preNode.right){
            preNode.right = clueNode;
            preNode.rightType = 1;
        }
        /**每处理一个节点后，让当前节点成为下一个节点的前驱节点*/
        preNode = clueNode;
        /**向右边线索化*/
        if(0 == clueNode.rightType){
            this.clueWithTree(clueNode.right);
        }
    }
    /**后序线索化二叉树*/
    public void postWithTree(ClueNode clueNode){
        if(null == clueNode){
            return;
        }
        /**向左边线索化*/
        if(0 == clueNode.leftType){
            this.postWithTree(clueNode.left);
        }

        /**向右边线索化*/
        if(0 == clueNode.rightType){
            this.postWithTree(clueNode.right);
        }
        if(null == clueNode.left){
            clueNode.left = preNode;
            clueNode.leftType = 1;
        }
        if(null != preNode && null == preNode.right){
            preNode.right = clueNode;
            preNode.rightType = 1;
        }
        /**每处理一个节点后，让当前节点成为下一个节点的前驱节点*/
        preNode = clueNode;

    }
    /**中序遍历线索二叉树*/
    public void infixOrder(){
        System.out.println("中序遍历为： ");
        if(null != this.root){
            this.root.midOrder(root);
        }
        else {
            System.out.println("链表为空，无法遍历");
        }
    }
    /**先序遍历线索二叉树*/
    public void beforeOrder(){
        System.out.println("先序遍历为： ");
        if(null != this.root){
            this.root.preOrder(root);
        }
        else {
            System.out.println("链表为空，无法遍历");
        }
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

    @Override
    public String toString() {
        return "ClueNode[ id= " + this.id + " name= " + this.name + " ] ";
    }

    /**线索中序遍历*/
    public void midOrder(ClueNode root){
        ClueNode temp = root;

        while(null != temp){
            while (0 == temp.leftType){
                temp = temp.left;
            }
            System.out.println(temp);
            while (1 == temp.rightType){
                temp = temp.right;
                System.out.println(temp);
            }
            temp = temp.right;
        }
    }
    /**线索先序遍历*/
    public void preOrder(ClueNode root){
        ClueNode temp = root;

        while(null != temp){
            while(0 == temp.leftType){
                System.out.println(temp);
                temp = temp.left;
          }
            System.out.println(temp);
            temp = temp.right;
        }
    }
}