package com.tree;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 实现霍夫曼树
 * 构成赫夫曼树的步骤：
 * 从小到大进行排序, 将每一个数据，每个数据都是一个节点 ， 每个节点可以看成是一颗最简单的二叉树
 * 取出根节点权值最小的两颗二叉树
 * 组成一颗新的二叉树, 该新的二叉树的根节点的权值是前面两颗二叉树根节点权值的和
 * 再将这颗新的二叉树，以根节点的权值大小 再次排序， 不断重复  1-2-3-4 的步骤
 * 直到数列中，所有的数据都被处理，就得到一颗赫夫曼树
 */
public class HuffmanTree {
    public static void main(String[] args) {
        int[] num = new int[]{13,7,8,3,29,6,1};
        MyNode node = HuffmanTree.createHuffmanTree(num);
        /**对生成的霍夫曼树进行前序遍历*/
        node.preOrder(node);
    }
    /**创建霍夫曼树*/
    public static MyNode createHuffmanTree(int[] num){
        /**为了后续的方法操作，需要将创建的节点放在集合中*/
        List<MyNode> nodeList = new ArrayList<>(0);
        for (int i = 0; i < num.length; i++) {
            /**根据权值创建节点，并加入到集合中*/
            nodeList.add(new MyNode(num[i]));
        }
        /**每次生成一个霍夫曼节点，则集合中会减少一个元素，直到集合中只剩最后一个节点，则处理完毕，跳出循环*/
        while (nodeList.size() > 1){
            /**将nodeList集合中的MyNode对象中按value升序排序*/
            nodeList = nodeList.stream().sorted(Comparator.comparing(MyNode::getValue)).collect(Collectors.toList());

            /**得到权值最小的二叉树节点*/
            MyNode leftNode = nodeList.get(0);
            /**得到权值次小的二叉树节点*/
            MyNode rightNode = nodeList.get(1);

            /**根据以上两个节点，创建新的二叉树,父节点的仅值是左右子节点权值的和*/
            MyNode parentNode = new MyNode(leftNode.getValue() + rightNode.getValue());
            parentNode.left  = leftNode;
            parentNode.right = rightNode;

            /**从nodeList中删除处理后的两个节点，并加入新的节点*/
            nodeList.remove(leftNode);
            nodeList.remove(rightNode);
            nodeList.add(parentNode);
        }
        /**最后返回霍夫曼树的root节点，即集合中第一个节点,也是唯一的节点*/
        return nodeList.get(0);
    }
}
/**节点类*/
 class MyNode{
     /**节点权值*/
     private int value;
     /**指向左子节点*/
     MyNode left;
     /**指向右子节点*/
     MyNode right;
     public MyNode(){}
     public MyNode(int value){
         this.value = value;
     }
    @Override
    public String toString() {
        return "MyNode[ value= " + this.value + " ] ";
    }
    public int getValue(){
         return value;
    }
    /**前序遍历*/
    public void preOrder(MyNode node){
        System.out.println(node);
        if(null != node.left){
            this.preOrder(node.left);
        }
        if(null != node.right){
            this.preOrder(node.right);
        }
    }
}