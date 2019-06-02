package com.queue;

/**
 * 数组模拟环形列表
 */
public class CircleQueueByArry {
    public static void main(String[] args) {

        CircleQueue circleQueue = new CircleQueue(5);
        circleQueue.addQueue(5);
        circleQueue.addQueue(0);
        circleQueue.addQueue(7);
        circleQueue.addQueue(1);

        System.out.println(circleQueue.getQueue());
        System.out.println(circleQueue.getQueue());
        System.out.println(circleQueue.getQueue());

        circleQueue.showQueue();

        System.out.println(circleQueue.headQueue());
    }
}

class CircleQueue {
    /**表示队列的最大容量*/
    private int maxSize;
    /**队列的头指针，是指向队列头的位置*/
    private int front;
    /**队列的尾指针，指向队列最后元素的下一个位置*/
    private int rear;
    /**用于模拟队列*/
    private int[] arr;

    public CircleQueue(int maxSize){
        this.maxSize = maxSize;
        this.arr = new int[this.maxSize];
        /**初始状态，头尾指针为0*/
        this.front = this.rear = 0;
    }

    /**判断队列是否满*/
    public boolean isFull(){
        return (rear + 1) % maxSize == front;
    }

    /**判断队列是否为空*/
    public boolean isEmpty(){
        return rear == front;
    }

    /**添加数据到队列*/
    public void addQueue(int n){
        /**判断队列是否满*/
        if(!isFull()){
            /**因为rear含义是指向最后一个元素的下一个位置，所以直接放入*/
            arr[rear] = n;
            /**因为是环形队列，所以rear必须考虑取模*/
            rear = (rear + 1) % maxSize;
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
        /**
         *这里需要分析出front是指向队列的第一个元素
         * 1.将front对应的值保存到一个临时变量中
         * 2.front位置向后移
         * 3.返回临时变量
         */
        int value = arr[front];
        front = (front + 1) % maxSize;
        return value;
    }

    /**显示队列的所有数据*/
    public void showQueue(){
        if(isEmpty()){
            System.out.println("队列为空，不可遍历");
            return;
        }
        /**从front开始遍历，遍历多少个元素*/
        for(int i = front;i < front + this.effectiveSize();i++){
            System.out.printf("arr[%d]=%d\n",i % maxSize,arr[i % maxSize]);
        }
    }

    /**求出当前队列有效数据的个数*/
    private int effectiveSize(){
        return (rear + maxSize - front) % maxSize;
    }

    /**显示队列的头数据*/
    public int headQueue(){
        if(isEmpty()){
            throw new RuntimeException("队列为空，没有数据");
        }
        return arr[front];
    }
}