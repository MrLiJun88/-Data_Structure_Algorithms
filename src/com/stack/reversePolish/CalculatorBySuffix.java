package com.stack.reversePolish;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 用后缀表达式（逆波兰表达式）来实现栈的计算器操作
 * 输入一个逆波兰表达式(后缀表达式)，使用栈(Stack), 计算其结果
 * 支持小括号和多位数整数，因为这里我们主要讲的是数据结构，因此计算器进行简化，只支持对整数的计算。
 * 思路分析:
 * 例如: (3+4)×5-6 对应的后缀表达式就是 3 4 + 5 × 6 - , 针对后缀表达式求值步骤如下:
 * 1.从左至右扫描，将3和4压入堆栈；
 * 2.遇到+运算符，因此弹出4和3（4为栈顶元素，3为次顶元素），计算出3+4的值，得7，再将7入栈；
 * 3.将5入栈；
 * 4.接下来是×运算符，因此弹出5和7，计算出7×5=35，将35入栈；
 * 5.将6入栈；
 * 6.最后是-运算符，计算出35-6的值，即29，由此得出最终结果
 */
public class CalculatorBySuffix {
    public static void main(String[] args) {
        /**(3+4)×5-6 --> 3 4 + 5 × 6 -
         * 这个方便计算，表达式的数字和符号使用空格隔开
         * */
        CalculatorBySuffix calculator = new CalculatorBySuffix();
        String suffixExpression = "3 4 + 5 * 6 -";
        /**
         * 思路：先将suffixExpression = "3 4 + 5 × 6 - "放到ArrayList中
         * 将ArrayList传入一个方法中，配合栈来计算
         * */
        List<String> list = CalculatorBySuffix.getArrayList(suffixExpression);
        int result = calculator.calculator(list);
        System.out.println("exression= " + suffixExpression + " = " + result);

    }
    /**将一个逆波兰表达式的字符串放入到ArrayList中*/
    public static List<String> getArrayList(String expression){
        String[] split = expression.split(" ");
        List<String> list = new ArrayList<>();
        for (int i = 0; i < split.length; i++) {
            list.add(split[i]);
        }
        return list;
    }

    public int calculator(List<String> list){
        /**创建一个栈*/
        Stack<String> stack = new Stack();

        for (int i = 0; i < list.size(); i++) {
            /**这里使用正则表达式来判断*/
            if(list.get(i).matches("\\d+")){
                stack.push(list.get(i));
            }
            /**如果是一个运算符，则进行取数运算*/
            else {
                String oper = list.get(i);
                int num1 = Integer.parseInt(stack.pop());
                int num2 = Integer.parseInt(stack.pop());
                int result = 0;
                if("+".equals(oper)){
                    result = num1 + num2;
                }
                else if("-".equals(oper)){
                    result = num2 - num1;
                }
                else if("*".equals(oper)){
                    result = num2 * num1;
                }
                else {
                    result = num2 / num1;
                }
                /**将运算结果再压入数栈中*/
                stack.push(String.valueOf(result));
            }
        }
        /**最后留在栈中的数就是运算结果 */
        return Integer.parseInt(stack.pop());
    }
}
