package com.stack;

/**
 * 用数组来模拟栈操作
 */
public class StackDemo {
    public static void main(String[] args) {
        MyStack stack = new MyStack(6);
        stack.push(5);
        stack.push(7);
        stack.push(1);
        stack.push(4);
        stack.push(6);

        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println("-------------");
        stack.push(10);
        stack.show();
    }
}

class MyStack {
    int maxSize;
    /**栈顶位置,初始化为-1*/
    int top = -1;
    int[] myStack;

    public MyStack(int maxSize){
        this.maxSize = maxSize;
        myStack = new int[this.maxSize];
    }

    /**入栈*/
    public void push(int value){
        if(this.isFull()){
            throw new RuntimeException("栈已满，不法再存入数据");
        }
        top++;
        myStack[top] = value;
    }

    /**出栈*/
    public int pop(){
        if(this.isFull()){
            throw new RuntimeException("栈空,没有数据");
        }
        int value = myStack[top];
        top--;
        return value;
    }

    /**遍历栈元数*/
    public void show(){
        if(this.isFull()){
            throw new RuntimeException("栈空,没有数据");
        }
        for(int i = top;i >= 0;i--){
            System.out.println(myStack[i]);
        }
    }

    /**判断栈是否为空*/
    public boolean isFull(){
        return top == maxSize - 1;
    }

    /**判断栈是否为满了*/
    public boolean isEmpty(){
        if(-1 == top){
            return true;
        }
        return false;
    }
}