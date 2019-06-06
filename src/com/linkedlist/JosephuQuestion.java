package com.linkedlist;

/**
 * 用环形链表实现约瑟夫问题
 * 一：先创建一个单向的环形链表
 *   1.先创建第一个节点，让first指向该节点，形成环状
 *   2.后面再创建的新节点，就把改节点加入到环形链表中
 *
 */
public class JosephuQuestion {
    public static void main(String[] args) {
        SingleCircleLinkedList list = new SingleCircleLinkedList();
        list.addNode(5);
//        list.showList();
        list.play(1,2,5);
    }
}

class SingleCircleLinkedList {

    Node first = new Node();

    /**添加小孩到链表*/
    public void addNode(int num){
        if(1 > num){
            System.out.println("num值不合法");
            return;
        }
        /**指向当前的节点位置*/
        Node curNode = new Node();

        for(int i = 1;i <= num;i++){
            Node node = new Node(i);
            /**如果是第一个小孩*/
            if(i == 1){
                first = node;
                /**构成一个环*/
                first.next = first;
                /**curNode指向当前节点位置*/
                curNode = first;
            }
            else {
                curNode.next = node;
                node.next = first;
                curNode = node;
            }
        }
    }
    /**遍历环形列表*/
    public void showList(){
        if(null == first){
            System.out.println("链表为空");
            return;
        }
        /**因为frist指针不能动，所以要创建一个辅助指针完成遍历*/
        Node temp = first;
        while(true){
            System.out.println(temp);
            if(first == temp.next){
                System.out.println(temp);
                break;
            }
            temp = temp.next;
        }
    }

    /**
     *
     * @param startNo：开始位置
     * @param countNum：数多少个数
     * @param totalNum：开始的总人数
     */
    public void play(int startNo,int countNum,int totalNum){
        if(null == first){
            System.out.println("链表为空");
            return;
        }
        /**helper初始指向链表的最后一个节点位置*/
        Node helper = first;
        while (true){
            if(helper.next == first){
                break;
            }
            helper = helper.next;
        }

        for(int i = 0;i < startNo - 1;i++){
            first = first.next;
            helper = helper.next;
        }
        /**开始循环出圈*/
        while(true){
            /**说明圈中只有一个*/
            if(helper == first){
                break;
            }
            /**移动countNum-1次*/
            for(int i = 0;i < countNum - 1;i++){
                first = first.next;
                helper = helper.next;
            }
            /**此时first是要出圈的小防*/
            System.out.println(first + " 出圈");
            first = first.next;
            helper.next = first;
        }
        System.out.println("圈中最后节点的信息： " + first);
    }
}

class Node {
    int no;
    Node next;

    public Node(int no){
        this.no = no;
    }

    public Node(){}

    @Override
    public String toString() {
        return "Node[ no = " + no + " ]";
    }
}