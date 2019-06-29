package com.tree;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 实现霍夫曼编码
 */
public class HuffmanCodingDemo {
    /**codingMap用于存放每个字符所对应的编码 如 a -> 100,b -> 0110*/
    static Map<Character,String> codingMap = new HashMap<>();
    /**用于递归路径的拼接*/
    static StringBuilder stringBuilder = new StringBuilder();

    public static void main(String[] args) {
        String str = "i like like like java do you like a java";
        /**统计字符串中每个字符出现的个数*/
        Map<Character,Integer> map = HuffmanCodingDemo.getWeight(str);
        /**将生成的每个节点存入List集合中*/
        List<HuffmanNode> nodes = new ArrayList<>();
        for (Map.Entry<Character, Integer> entry : map.entrySet()) {
            nodes.add(new HuffmanNode(entry.getKey(),entry.getValue()));
        }
        HuffmanNode huffmanNode = HuffmanCodingDemo.createHuffmanCodingTree(nodes);
//        huffmanNode.preOrder();
        /**根据霍夫曼树生成对应的霍夫曼编码*/
        HuffmanCodingDemo.generateHuffmanCoding(huffmanNode,"",stringBuilder);
        System.out.println(codingMap);
    }
    /**计算字符串中每个字符出现的个数*/
    public static Map<Character,Integer> getWeight(String str){
        Map<Character,Integer> map = new HashMap<>(16);
        for (int i = 0; i < str.length(); i++) {
            Character ch = str.charAt(i);
            /**如果map包含了该key，则value+1并再放入map中*/
            if(map.containsKey(ch)){
                map.put(ch,map.get(ch) + 1);
            }
            else {
                map.put(ch,1);
            }
        }
        return map;
    }
    /**创建霍夫曼树*/
    public static HuffmanNode createHuffmanCodingTree(List<HuffmanNode> nodes){
        while (nodes.size() > 1){
            /**按节点的权值升序排序*/
            nodes = nodes.stream().sorted(Comparator.comparing(HuffmanNode::getCount)).collect(Collectors.toList());
            /**获得权值最小的节点和次小的节点*/
            HuffmanNode leftNode = nodes.get(0);
            HuffmanNode rightNode = nodes.get(1);
            /**parentNode 没有data，只有权值,只有叶子节点才会有data*/
            HuffmanNode parentNode = new HuffmanNode(null,leftNode.getCount() + rightNode.getCount());
            parentNode.left = leftNode;
            parentNode.right = rightNode;
            /**从集合中移除已经处理过的节点，并加入新的节点，循环排序*/
            nodes.remove(leftNode);
            nodes.remove(rightNode);
            nodes.add(parentNode);
        }
        return nodes.get(0);
    }
    /**
     * 根据霍夫曼树生成对应的霍夫曼编码
     * @param node 树的根节点
     * @param path  路径 ，左：0，右：1
     * @param stringBuilder  用于路径的拼接，如 0001,1010
     * @return
     */
    public static void generateHuffmanCoding(HuffmanNode node,String path,StringBuilder stringBuilder){

        StringBuilder builder = new StringBuilder(stringBuilder);
        builder.append(path);
        if(null != node){
            /**说明该node是非叶子节点*/
            if(null == node.data){
                /**向左递归处理*/
                generateHuffmanCoding(node.left,"0",builder);
                /**向右递归处理*/
                generateHuffmanCoding(node.right,"1",builder);
            }
            else {
                /**说明node是一个叶子节点*/
                codingMap.put(node.data,builder.toString());
            }
        }
    }
}
/**霍夫曼节点*/
class HuffmanNode {
    /**数据*/
    Character data;
    /**权值，即各字符在字符串中存在的次数*/
    private int count;
    HuffmanNode left;
    HuffmanNode right;
    public HuffmanNode(Character data,int count){
        this.data = data;
        this.count = count;
    }
    @Override
    public String toString() {
        return "HuffmanNode[ data= " + this.data + " ,count= " + this.count + " ] ";
    }
    public int getCount(){
        return count;
    }
    /**前序遍历*/
    public void preOrder(){
        System.out.println(this);
        if(null != this.left){
            this.left.preOrder();
        }
        if(null != this.right){
            this.right.preOrder();
        }
    }
}
