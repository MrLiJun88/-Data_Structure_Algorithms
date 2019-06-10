package com.stack;

/**
 * 用栈来实现多位数计算器的操作:
 * 1.通过一个index值（索引），来遍历表达式
 * 2.如果是一个数字，就直接入栈
 * 3.如果是一个符号，就分情况讨论
 * 4.如果当前的符号栈主空，就直接入栈
 * 5.如果符号栈有操作符，就进行比较，如果当前的操作符的优先级小于或者等于
 * 栈中的操作符，就需要要从数栈中pop出两个数，再从符号栈中pop出一个符号
 * 进行运算，将得到结果，入数栈，然后将当前的操作符入符号栈
 * 5.1:如果当前操作符的优先级大于栈中的操作符，就直接入符号栈
 * 6.当表达式扫描完毕，就顺序的从数栈和符号栈中pop出相应的数和符号，并运行
 * 7.最后在数栈中只有一个数字，就是表达式的结果
 */
public class StackByCalculator {

    public static void main(String[] args) {
        String expression = "14+2*6-5";
        MyStack numStack = new MyStack(20);
        MyStack operStack = new MyStack(20);
        /**用于扫描*/
        int num1 = 0;
        int num2 = 0;
        char oper = ' ';
        char ch = ' ';
        int result = 0;
        String keepNum = "";
        /**依次扫描表达式，进行判断*/
        for(int i = 0;i < expression.length();i++){
            ch = expression.charAt(i);
            /**如果是运算符*/
            if(operStack.isOperator(ch)){
                /**判断当前的符号栈是否为空*/
                if(operStack.isEmpty()){
                    /**如果为空则直接入栈*/
                    operStack.push(ch);
                }
                else {
                    /**如果当前的操作符的优先级
                     * 小于或者等于栈中的操作符*/
                    if(operStack.getPriority(ch) <= operStack.getPriority(operStack.getTop())){
                        num1 = numStack.pop();
                        num2 = numStack.pop();
                        oper = (char)operStack.pop();
                        result = numStack.calculation(num1,num2,oper);
                        /**把运算的结果入数栈*/
                        numStack.push(result);
                        /**将当前的运算符再入符号栈*/
                        operStack.push(ch);
                    }
                    /**如果当前操作符的优先级大于栈中的操作符，就直接入符号栈*/
                    else {
                        operStack.push(ch);
                    }
                }
            }
            /**如果是数，不是操作符，则直接入数栈*/
            else {
                /**存放数值时，char类型要减48才可以是int类型*/
                keepNum += ch;
                if(i == expression.length() - 1){
                    numStack.push(Integer.parseInt(keepNum));
                }
                else if(operStack.isOperator(expression.charAt(i + 1))){
                    numStack.push(Integer.parseInt(keepNum));
                    keepNum = "";
                }
            }
        }
        /**
         * 当表达式扫描完毕，就顺序的从数栈和符号栈中pop出相应的数和符号
         * 并运行最后在数栈中只有一个数字，就是表达式的结果
         */
        while(true){
            if(operStack.isEmpty()){
                break;
            }
            num1 = numStack.pop();
            num2 = numStack.pop();
            oper = (char)operStack.pop();
            result = numStack.calculation(num1,num2,oper);
            numStack.push(result);
        }
        System.out.println("expression: " + expression + " = " + numStack.pop());
    }
}
