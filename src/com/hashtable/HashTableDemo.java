package com.hashtable;

/**
 * 有一个公司,当有新的员工来报道时,要求将该员工的信息加入  (id,性别,年龄,名字,住址..)
 * 当输入该员工的id时,要求查找到该员工的所有信息.
 *
 * 要求:
 * 哈希表(散列)
 * 添加时，保证按照id从低到高插入  [课后思考：如果id不是从 低到高插入，但要求各条链表仍是从低到高，怎么解决?]
 * 使用链表来实现哈希表, 该链表不带表头 [即: 链表的第一个结点就存放雇员信息]
 * 代码实现[增删改查(显示所有员工，按id查询)]
 */
public class HashTableDemo {
    public static void main(String[] args) {
        HashTab hashTab = new HashTab(7);
        for (int i = 0; i < 20; i++) {
            hashTab.add(new Employee(i,"lijun" + i));
        }
        hashTab.list();
//        Employee employee = hashTab.get(13);
//        if(null != employee){
//            System.out.println(employee);
//        }
//        else {
//            System.out.println("查找不到，链表中没有");
//        }
        System.out.println("---------------");
        hashTab.remove(6);
        hashTab.list();
    }
}
/**创建hash表，用来管理多条的链表*/
class HashTab {
    EmpLinkedList[] linkedLists;
    /**size: 表中hash table中共有多少条链表*/
    int size;

    public HashTab(int size){
        this.size = size;
        linkedLists = new EmpLinkedList[size];
        /**分别初始化每一条链表*/
        for (int i = 0; i < linkedLists.length; i++) {
            linkedLists[i] = new EmpLinkedList();
        }
    }
    /**添加员工链表*/
    public void add(Employee employee){
        /**linked_index : 通过哈希函数得到应该存放在哪条链表中*/
        int linked_index = this.hashFun(employee.id);
        linkedLists[linked_index].addEmp(employee);
    }
    /**遍历表中所中的链表*/
    public void list(){
        for (int i = 0; i < linkedLists.length; i++) {
            System.out.print("当前链表所属的数组位置 [ " + i + " ] ");
            linkedLists[i].show();
            System.out.println();
        }
    }
    /**根据id查找员工信息*/
    public Employee get(int id){
        int index  = this.hashFun(id);
        Employee employee = linkedLists[index].getEmp(id);
        if(null != employee){
            return employee;
        }
        return null;
    }
    /**根据id删除员工*/
    public void remove(int id){
        int index = this.hashFun(id);
        linkedLists[index].delEmp(id);
    }
    /**散列函数，根据id值，确定要放入hash table中的哪条链表中*/
    public int hashFun(int id){
        return id % this.size;
    }
}
/**员工类*/
class Employee {
    Integer id;
    String name;
    Employee next;
    public Employee(Integer id,String name){
        this.id = id;
        this.name = name;
    }
    public Employee(){}

    @Override
    public String toString() {
        return "Employee[ id= " + this.id + " name= " + this.name + " ]";
    }
}
/**创建一个EmployeeLinkedList 表示一条链表，用于存放员工类*/
class EmpLinkedList {
    /**头指针，该head是有效的，指向一个有效的员工类*/
    Employee head;
    /**添加雇员到链表中,默认添加到链表的最后,id是自增的*/
    public void addEmp(Employee employee){
        /**第一次添加员工类*/
        if(null == head){
            head = employee;
            return;
        }
        /**如果不是第一次添加，则使用一个辅助的指针*/
        Employee temp = head;
        while(true){
            if(null == temp.next){
                break;
            }
            temp = temp.next;
        }
        temp.next = employee;
    }
    /**遍历链表中的员工信息*/
    public void show(){
        /**判断是否是空链表*/
        if(null == head){
            System.out.println("当前链表为空，无法遍历");
            return;
        }
        /**定义一个临时节点*/
        Employee temp = head;
        while(true){
            if(null == temp){
                break;
            }
            else {
                System.out.print(" " + temp + " , ");
            }
            temp = temp.next;
        }
    }
    /**统计链表中员工个数*/
    public int size(){
        int count = 0;
        if(null == head){
            return 0;
        }
        Employee temp = head;
        while(true){
            if(null == temp){
                break;
            }
            else {
                count++;
            }
            temp = temp.next;
        }
        return count;
    }
    /**根据员工id查找员工信息*/
    public Employee getEmp(int id){
        if(null == head){
            System.out.println("当前链表为空，无法查询");
            return null;
        }
        Employee temp = head;
        while (true){
            if(id == temp.id){
                break;
            }
            /**查找到链表最后，还是没有找到，则置空，返回，并跳出循环*/
            if(null == temp.next){
                temp = null;
                break;
            }
            temp = temp.next;
        }
        return temp;
    }
    /**根据id删除员工信息*/
    public void delEmp(int id){
        if(null == head){
            System.out.println("链表为空，无法删除");
            return;
        }
        if(id == head.id){
            head = head.next;
            return;
        }
        Employee temp = head;
        while(true){
            if(null == temp){
                break;
            }
            if(id == temp.next.id){
                temp.next = temp.next.next;
                break;
            }
            temp = temp.next;
        }
    }
}