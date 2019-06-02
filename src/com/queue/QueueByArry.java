package com.queue;

/**
 * 用数组来模拟单向队列
 */
public class QueueByArry {
    public static void main(String[] args) {
        ArrayQueue queue = new ArrayQueue(4);
        queue.addQueue(5);
        queue.addQueue(0);
        queue.addQueue(4);
        queue.addQueue(7);
        System.out.println(queue.getQueue());
        System.out.println(queue.getQueue());
        queue.showQueue();

    }
}
/**用数组来模拟队列*/
class ArrayQueue {
    /**表示队列的最大容量*/
    private int maxSize;
    /**队列的头指针，是指向队列头的前一个位置*/
    private int front;
    /**队列的尾指针，指向队列尾的那个数据，即就是队列最后一个数据*/
    private int rear;
    /**用于模拟队列*/
    private int[] arr;

    /**创建队列的构造器*/
    public ArrayQueue(int maxSize){
        this.maxSize = maxSize;
        this.arr = new int[this.maxSize];
        /**初始状态，头尾指针为-1*/
        this.front = this.rear = -1;
    }

    /**判断队列是否满*/
    public boolean isFull(){
        return rear == maxSize - 1;
    }

    /**判断队列是否为空*/
    public boolean isEmpty(){
        return rear == front;
    }

    /**添加数据到队列*/
    public void addQueue(int n){
        /**判断队列是否满*/
        if(!isFull()){
            //让rear尾指针后移一位
            rear++;
            arr[rear] = n;
        }
        else {
            System.out.println("队列已满，不能再添加数据");
            return;
        }
    }
    /**取数据*/
    public int getQueue(){
        /**判断队列是否为空*/
        if(isEmpty()){
            throw new RuntimeException("队列为空，没有数据可取");
        }
       return arr[++front];
    }

    /**显示队列的所有数据*/
    public void showQueue(){
       if(isEmpty()){
           System.out.println("队列为空，不可遍历");
           return;
       }
       for(int i = 0;i < arr.length;i++){
           System.out.printf("arr[%d]=%d\n",i,arr[i]);
       }
    }

    /**显示队列的头数据*/
    public int headQueue(){
        if(isEmpty()){
            throw new RuntimeException("队列为空，没有数据");
        }
        return arr[front + 1];
    }
}