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
    /**获得栈顶元素*/
    public char getTop(){
        if(this.isEmpty()){
            throw new RuntimeException("栈空,没有数据");
        }
        return (char)myStack[top];
    }
    /**返回运算符的优先级,约定：数字越大，则优先级越高*/
    public int getPriority(char oper){
        char oper1 = '*';
        char oper2 = '/';
        char oper3 = '+';
        char oper4 = '-';
        if(oper1 == oper || oper2 == oper){
            return 1;
        }
        else if(oper3 == oper || oper4 == oper){
            return 0;
        }
        return -1;
    }
    /**判断是不是一个运算符*/
    public boolean isOperator(char oper){
        return '+' == oper || '-' == oper || '*' == oper || '/' == oper;
    }
    /**计算方法*/
    public int calculation(int num1,int num2,char oper){
        /**result用来存放运算结果*/
        int result = 0;
        switch (oper){
            case '+':{
                result = num1 + num2;
                break;
            }
            case '-':{
                result = num2 - num1;
                break;
            }
            case '*':{
                result = num1 * num2;
                break;
            }
            case '/':{
                result = num2 / num1;
                break;
            }
        }
        return result;
    }
}