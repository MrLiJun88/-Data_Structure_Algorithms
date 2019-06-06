package com.linkedlist.doublelinkedlist;
/**
 * 创建一个双向链表
 * @author Administrator
 */
public class DouleLinkedListDemo {

    public static void main(String[] args) {
        DoubleLinkedList list = new DoubleLinkedList();
        HeroNode node1 = new HeroNode(2,"王五");
        HeroNode node2 = new HeroNode(7,"陈麻子");
        HeroNode node3 = new HeroNode(5,"李四");
        HeroNode node4 = new HeroNode(4,"狗蛋");
//		HeroNode node5 = new HeroNode(7,"小小露");

        list.addListOrdeByNo(node1);
        list.addListOrdeByNo(node2);
        list.addListOrdeByNo(node3);
        list.addListOrdeByNo(node4);

        list.showList();
    }

}

class DoubleLinkedList {

    /**初始化一个头节点*/
    HeroNode head = new HeroNode();

    /**遍历双向链表*/
    public void showList(){
        if(null == head.next){
            System.out.println("链表为空");
            return;
        }
        HeroNode temp = head.next;
        while(true){
            if(null == temp){
                break;
            }
            else {
                System.out.println(temp);
            }
            temp = temp.next;
        }
    }

    /**将节点添加到链表的最后*/
    public void addListToLast(HeroNode node){
        HeroNode temp = head;
        while(true){
            if(null == temp.next){
                break;
            }
            temp = temp.next;
        }
        temp.next = node;
        node.pre = temp;
    }

    /**按照no升序生成链表*/
    public void addListOrdeByNo(HeroNode node){
        HeroNode temp = head;

        if(null == temp.next){
            temp.next = node;
            node.pre = temp;
            return;
        }
        temp = temp.next;
        boolean flag = false;

        while(true){
            if(null == temp){
                break;
            }
            if(node.no < temp.no){
                break;
            }
            if(node.no == temp.no){
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if(!flag){
            node.next = temp;
            node.pre = temp.pre;
        }
        else {
            System.out.println("编号已经存在，不能重复添加该no：" + node.no);
        }
    }
    /**根据no号修改指定的节点信息*/
    public void alterNode(HeroNode node){

        if(null == head.next){
            System.out.println("链表为空");
            return;
        }
        HeroNode temp = head.next;
        while(true){
            if(null == temp){
                break;
            }
            else {
                if(node.no == temp.no){
                    temp.name = node.name;
                    break;
                }
            }
            temp = temp.next;
        }
    }

    /**从双向链表中删除一个节点*/
    public void deleteNode(int no){
        if(null == head.next){
            System.out.println("链表为空");
            return;
        }
        HeroNode temp = head.next;
        while(true){
            if(null == temp){
                break;
            }
            else {
                if(no == temp.no){
                    /**如果是已经到了最后的节点，则只要将该节点的前一个节点的下一个指向为空即可*/
                    if(null == temp.next){
                        temp.pre.next = null;
                        break;
                    }
                    temp.pre.next = temp.next;
                    temp.next.pre = temp.pre;
                }
            }
            temp = temp.next;
        }
    }
}

class HeroNode {
    int no;
    String name;
    /**指向前一个节点*/
    HeroNode pre;
    /**指向后一个节点*/
    HeroNode next;

    public HeroNode(){}

    public HeroNode(int no,String name){
        this.no = no;
        this.name = name;
    }

    @Override
    public String toString() {
        return "HeroNod[no = " + no + " name = " + name + "]";
    }
}
