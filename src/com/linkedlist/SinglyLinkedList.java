package com.linkedlist;

/**
 * 创建带head结点的单向链表
 */
public class SinglyLinkedList {
    public static void main(String[] args) {
        HeroNode node1 = new HeroNode(1,"李俊");
        HeroNode node2 = new HeroNode(5,"张三");
        HeroNode node3 = new HeroNode(2,"王五");
        HeroNode node4 = new HeroNode(7,"赵六");

        SingleList list = new SingleList();
        list.addNodeOrderByNo(node1);
        list.addNodeOrderByNo(node2);
        list.addNodeOrderByNo(node3);
        list.addNodeOrderByNo(node4);
        list.showList();
        System.out.println("------------");
        HeroNode node5 = new HeroNode(5,"狗蛋");
        list.alterNode(node5);
        list.showList();

    }
}

/**创建HeroNode，每一个HeroNode对象就是一个结点*/
class HeroNode {
    int no;
    String name;
    /**指向结点的下一个位置*/
    HeroNode next;

    public HeroNode(int no,String name){
        this.no = no;
        this.name = name;
    }

    public HeroNode(){}

    @Override
    public String toString() {
        return "HeroNode[" + this.no + " " + this.name + "]";
    }
}

class SingleList {
    /**先初始化一个head节点，这个节点不有动*/
    HeroNode head = new HeroNode();

    /**添加节点到单向链表
     * 思路：找到当前链表的最后节点
     * 将最后节点的next指向新的节点
     */
    public void addNode(HeroNode node){
        /**因为head节点不能动，所以我们需要一个辅助节点*/
        HeroNode temp = head;
        /**遍历链表找到最后节点*/
        while (true){
            /**找到了链表的最后*/
            if(null == temp.next){
                break;
            }
            else {
                /**条件不符合，向后遍历*/
                temp = temp.next;
            }
        }
        /**
         * 当while退出循环后，temp已经是指向了链表的最后
         * 再将新的节点插入到链表的最后
         */
        temp.next = node;
    }

    /**根据No号，升序插入链表,如果no已存在，则提示添加失败*/
    public void addNodeOrderByNo(HeroNode node){
        /**因为head节点不能动，所以我们需要一个辅助节点*/
        HeroNode temp = head;
        /**因为是单链表，因此我们找的temp是添加位置的前一个节点，否则添加不了*/
        boolean flag = false;
        while(true){
            /**说明temp已经在链表的最后了*/
            if(null == temp.next){
                break;
            }
            /**位置找到了，就在temp的后面*/
            if(temp.next.no > node.no){
                break;
            }
            /**说明希望要添加的节点已经存在了，不能重复添加*/
            else if(temp.next.no == node.no){
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if(!flag){
            /**将新节点添加到temp后面*/
            node.next = temp.next;
            temp.next = node;
        }
        else {
            System.out.println("编号已经存在，不能重复添加该no：" + node.no);
        }
    }

    /**遍历链表*/
    public void showList() {
        /**先判断链表是否为空*/
        if(null == head.next){
            System.out.println("链表为空");
            return;
        }
        /**因为head不能动，所以需要一个辅助节点来帮助遍历*/
        HeroNode temp = head;
        while(true){
            if(null == temp){
                break;
            }
            else {
                System.out.println(temp);
                temp = temp.next;
            }
        }
    }

    /**从链表中删除指定节点*/
    public void deleteNode(int no){
        HeroNode temp = head;
        while(true){
            if(no == temp.next.no){
                temp.next = temp.next.next;
                break;
            }
            else {
                temp = temp.next;
            }
        }
    }

    /**修改节点信息，节点no不能改变*/
    public void alterNode(HeroNode newNode){
        HeroNode temp = head;
        boolean flag = false;
        if(null == temp.next){
            System.out.println("链表为空");
            return;
        }

        while(true){
            /**已经到链表最后，则退出*/
            if (null == temp){
                break;
            }
            else {
                if (temp.no == newNode.no){
                    flag = true;
                    break;
                }
            }
            temp = temp.next;
        }
        if (flag){
            temp.name = newNode.name;
        }
        else {
            System.out.println("没有找到该节点信息，不能修改");
        }
    }
}