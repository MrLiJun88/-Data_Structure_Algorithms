package com.tree;
/**
 * 实现二叉排序树
 * 对于二叉排序树的任何一个非叶子节点，要求左子节点的值比当前节点的值小，
 * 右子节点的值比当前节点的值大。
 * 特别说明：如果有相同的值，可以将该节点放在左子节点或右子节点
 *
 * 二叉排序树删除节点的三种情况:
 * 一：删除的是叶子节点
 *   1):先找到要删除的节点 targetNode
 *   2):找到要删除节点的父节点 parentNode (还需要考虑是否存在父节点)
 *   3):确定 targetNode 是 parentNode 的左子节点还是右子节点，再确定删除
 *   4):根据以上情况来对应删除 parentNode.left=null,parentNode.right=null
 *
 * 二：删除只有一棵子树的节点
 *   1):先找到要删除的节点 targetNode
 *   2):找到要删除节点的父节点 parentNode (还需要考虑是否存在父节点)
 *   3):确定 targetNode的子节点是 左子节点还是右子节点
 *   4):确定 targetNode是parentNode 的左子节点还是右子节点
 *   5):如果 targetNode是parentNode的左子节点，并且targetNode的子节点是左子节点
 *    5.1):parentNode.left = targetNode.left
 *    5.2):如果 targetNode是parentNode的左子节点，并且targetNode的子节点是右子节点
 *    5.3):parentNode.left = targetNode.right
 *   6):如果 targetNode是parentNode的右子节点，并且targetNode的子节点是左子节点
 *     6.1):parentNode.right = targetNode.left
 *     6.2):如果 targetNode是parentNode的右子节点，并且targetNode的子节点是右子节点
 *     6.3):parentNode.right = targetNode.right
 *
 * 三：删除有左右两边都有子树的节点
 *   1):先找到要删除的节点 targetNode
 *   2):找到要删除节点的父节点 parentNode (还需要考虑是否存在父节点)
 *   3):从targetNode的右子树找到最小的节点 (如果从targetNode的左子树找，就应该找最大的节点)
 *   4):用一个临时变量，将最小节点的值保存 temp = minValue
 *   5):删除该最小的节点
 *   6):targetNode.value = temp;
 */
public class BinarySortingTree {
    public static void main(String[] args) {
        int[] arr = new int[]{7,3,10,12,5,1,9,2};
        BinSortedTree binSortedTree = new BinSortedTree();
        for (int i = 0; i < arr.length; i++) {
            binSortedTree.add(new LeafNode(arr[i]));
        }
//        binSortedTree.midOrder();
        binSortedTree.delete(3);
        binSortedTree.midOrder();
    }
}
/**创建二叉排序树*/
class BinSortedTree {
    /**树的根节点*/
    LeafNode root;
    public BinSortedTree(){}
    public BinSortedTree(LeafNode root){this.root = root;}
    /**添加节点*/
    public void add(LeafNode node){
        if(null == root){
            this.root = node;
            return;
        }
        root.addNode(node);
    }
    /**查找到要删除的节点*/
    public LeafNode getTargetNode(int value){
        return root.searchTargetNode(value);
    }
    /**查找到要删除节点的父节点*/
    public LeafNode getParentNode(int value){
        return root.searchParentNode(value);
    }
    /**删除指定节点*/
    public void delete(int value){
        if(null == root){return;}
        LeafNode targetNode = this.getTargetNode(value);
        System.out.println("目标节点： " + targetNode);
        if(null == targetNode){ return;}
        /**如果要删除的节点就是根节点，则直接置空*/
        if(this.root == targetNode){
            this.root = null;
            return;
        }
        LeafNode parentNode = this.getParentNode(value);
        System.out.println("目标节点的父节点 " + parentNode);
        /**如果删除的是叶子节点，叶子节点的左右没有节点*/
        if(null == targetNode.left && null == targetNode.right){
            /**判断targetNode是parentNode的左节点，还是右节点*/
            if(null != parentNode.left && parentNode.left.value == value){
                parentNode.left = null;
            }
            else if(null != parentNode.right && parentNode.right.value == value){
                parentNode.right = null;
            }
        }
        /**删除有两棵子树的节点*/
        else if(null != targetNode.left && null != targetNode.right){
            /**从targetNode的右子节点开始找最小的数来替代targetNode值，并删除最小的节点
             * 或者从targetNode的左子节点找最大的数，并删除最大节点
             * searchMinOfRightTree(targetNode.left);
             */
//            int min = this.searchMinOfRightTree(targetNode.right);
            int max = this.searchMaxOfLeftTree(targetNode.left);
            targetNode.value = max;
        }
        /**删除只有一棵子树的节点*/
        else {
            /**要删除的节点有左子节点*/
            if(null != targetNode.left){
                /**如果parentNode为null，则说明要删除的节点就是根节点，并且它只有一个左子树*/
                if(null == parentNode){
                    root = targetNode.left;
                }
                else if(parentNode.left.value == targetNode.value){
                    parentNode.left = targetNode.left;
                }
                else {
                    parentNode.right = targetNode.left;
                }
            }
            /**要删除的节点有右子节点*/
            else {
                /**如果parentNode为null，则说明要删除的节点就是根节点，并且它只有一个右子树*/
                if (null == parentNode){
                    root = targetNode.right;
                }
                else if(parentNode.right.value == targetNode.value){
                    parentNode.right = targetNode.right;
                }
                else {
                    parentNode.left = targetNode.right;
                }
            }
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
    /**查找到目标节点的左子树中权值最小的值,然后删除该最小值节点
     并返回它的权值,该方法用于删除节点左右都有节点或子树的情况下*/
    public int searchMinOfRightTree(LeafNode node){
        LeafNode temp = node;
        while(true){
            if(null == temp.left){
                break;
            }
            temp = temp.left;
        }
        /**这是target就指向了最小的值,将该节点删除，并返回它的权值*/
        this.delete(temp.value);
        return temp.value;
    }
    /**从目标节点的左子树中找最大的值，删除并返回它的权值*/
    public int searchMaxOfLeftTree(LeafNode node){
        LeafNode temp = node;
        while(true){
            if(null == temp.right){
                break;
            }
            temp = temp.right;
        }
        this.delete(temp.value);
        return temp.value;
    }
}
/**创建节点*/
class LeafNode {
    int value;
    LeafNode left;
    LeafNode right;
    public LeafNode(){}
    public LeafNode(int value){this.value = value;}
    @Override
    public String toString() {
        return "LeafNode[ value= " + this.value + " ] ";
    }
    /**按照二叉排序树的方式添加节点*/
    public void addNode(LeafNode node){
        if(null == node){
            return;
        }
        /**判断传入的节点与当前子树的根节点关系*/
        if(node.value < this.value){
            if(null == this.left){
                this.left = node;
            }
            else {
                this.left.addNode(node);
            }
        }
        /**如果传入节点的值大于等于当前子树的名节点*/
        else {
            if(null == this.right){
                this.right = node;
            }
            else {
                this.right.addNode(node);
            }
        }
        /**当添加完一个节点后，如果右子树的高度与左子树的高度差值大于1，则进行左旋转*/
        if(this.getRightHeight() - this.getLeftHeight() > 1){
            /**如果它的右子树的左子树高度大于它的右子树的高度，先对它的右子树进行右旋转*/
            if(null != this.right && this.right.getRightHeight() < this.right.getLeftHeight()){
                this.right.rightRotate();
            }
            this.leftRotate();
            /**这个return 是必须要的,因为已经平衡了，下面没必要再去执行*/
            return;
        }
        /**当添加完一个节点后，如果左子树的高度与右子树的高度差值大于1，则进行右旋转*/
        if(this.getLeftHeight() - this.getRightHeight() > 1){
            /**
             * 当满足右旋转的条件时
             * 如果它的左子树的右子树高度大于它的左子树的高度
             * 先对当前这个节点进行左旋转
             * 再对当前节点进行右旋转的操作
             */
            if(null != this.left && this.left.getRightHeight() > this.left.getLeftHeight()){
                this.left.leftRotate();
            }
            this.rightRotate();
        }
    }
    /**中序遍历二叉树*/
    public void infixOrder(){
        if(null != this.left){
            this.left.infixOrder();
        }
        System.out.println(this);
        if(null != this.right){
            this.right.infixOrder();
        }
    }
    /**查找要删除指定的节点，并返回*/
    public LeafNode searchTargetNode(int value){
        if(value == this.value){
            return this;
        }
        /**向右子树查找*/
        else if(value >= this.value){
            if(null == this.right){
                return null;
            }
            return this.right.searchTargetNode(value);
        }
        /**向左子树查找*/
        else {
            if(null == this.left){
                return null;
            }
            return this.left.searchTargetNode(value);
        }
    }
    /**查找要删除节点的父节点,并返回*/
    public LeafNode searchParentNode(int value){
       boolean condition = (null != this.left && this.left.value == value) || (null != this.right && this.right.value == value);
       if(condition){
           return this;
       }
       else {
           /**如果查找的值小于当前节点的值,并且当前节点的左子节点不为空*/
           if(null != this.left && value < this.value){
               return this.left.searchParentNode(value);
           }
           /**如果查找的值大于等于当前节点的值，并且当前节点的右子节点不为空*/
           else if(null != this.right && value >= this.value){
               return this.right.searchParentNode(value);
           }
           else {
               return null;
           }
       }
    }
    /**返回当前节点的高度，以该节点为根节点的树的高度*/
    public int getTreeHeight(){
        return Math.max(null == left ? 0 : left.getTreeHeight(),null == right ? 0 : right.getTreeHeight()) + 1;
    }
    /**返回左子树的高度*/
    public int getLeftHeight(){
        if(null == left){
            return 0;
        }
        return left.getTreeHeight();
    }
    /**返回右子树的高度*/
    public int getRightHeight(){
        if(null == right){
            return 0;
        }
        return right.getTreeHeight();
    }
    /**左旋转*/
    public void leftRotate(){
        /**
         * 1.创建新的节点，以当前根节点的权值创建的
         * 2.将新的节点的左子树设置成当前节点的左子树
         * 3.将新的节点的右子树设置成当前节点的右子树的左子树
         * 4.将当前节点的值替换成右子节点的值
         * 5.将当前节点的右子树设置成右子树的右子树
         * 6.将当前节点的左子树设置成新的节点
         */
        LeafNode newNode = new LeafNode(this.value);
        newNode.left = this.left;
        newNode.right = this.right.left;
        this.value = this.right.value;
        this.right = this.right.right;
        this.left = newNode;
    }
    /**右旋转*/
    public void rightRotate(){
        /**
         * 1.创建新的节点，以当前根节点的权值创建的
         * 2.将新节点的右子树设置成当前节点的右节点
         * 3.将新节点的左子树设置成当前节点的左子树的右节点
         * 4.将当前节点的值设置成当前节点左子节点的值
         * 5.将当前节点的左子树设置成当前节点左子树的左子树
         * 6.将当前节点的右子树设置成新节点
         */
        LeafNode newNode = new LeafNode(this.value);
        newNode.right = this.right;
        newNode.left = this.left.right;
        this.value = this.left.value;
        this.left = this.left.left;
        this.right = newNode;
    }
}