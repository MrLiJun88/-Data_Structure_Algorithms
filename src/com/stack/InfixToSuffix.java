package com.stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 用代码实现将中缀表达式转成后缀表达式
 *
 * 逻辑思路：
 * 1. 初始化两个栈：运算符栈s1和储存中间结果的栈s2；
 * 2. 从左至右扫描中缀表达式；
 * 3. 遇到操作数时，将其压s2；
 * 4. 遇到运算符时，比较其与s1栈顶运算符的优先级：
 *     4.1 如果s1为空，或栈顶运算符为左括号“(”，则直接将此运算符入栈；
 *     4.2 否则，若优先级比栈顶运算符的高，也将运算符压入s1；
 *     4.3 否则，将s1栈顶的运算符弹出并压入到s2中，再次转到(4-1)与s1中新的栈顶运算符相比较；
 * 5. 遇到括号时：
 *     (1) 如果是左括号“(”，则直接压入s1
 *     (2) 如果是右括号“)”，则依次弹出s1栈顶的运算符，并压入s2，直到遇到左括号为止， 此时将这一对括号丢弃
 * 6. 重复步骤2至5，直到表达式的最右边
 * 7. 将s1中剩余的运算符依次弹出并压入s2
 * 8. 依次弹出s2中的元素并输出，结果的逆序即为中缀表达式对应的后缀表达式
 *
 * 实现思路：
 * 因为直接对表达式进行操作，不方便，因此，先将中缀表达式转成对应的list类型
 */
public class InfixToSuffix {
    public static void main(String[] args) {

        String expression = "11*((5+3)*2)+10";
        List<String> list = InfixToSuffix.toList(expression);
        System.out.println(list);
        List<String> list2 = InfixToSuffix.parseInfixToSuffix(list);
        System.out.println("suffixExpression: " + list2);
        int result = InfixToSuffix.calculator(list2);
        System.out.println("计算结果: "  + expression + " = " + result);
    }

    /**将字符串转换成list类型*/
    public static List<String> toList(String expression){
        List<String> list = new ArrayList<>();
        String buffer = "";
        for (int i = 0; i < expression.length(); i++) {
            char ch = expression.charAt(i);
            /**如果不符合下面条件就不是数字*/
            if(ch < 48 || ch > 57){
                list.add(ch + "");
            }
            /**如果是一个数，需要考虑多位数的问题*/
            else {
                buffer += ch;
                /**如果取到的数是最后一个字符，则不用判断下一个，直接存入*/
                if(i == expression.length() - 1){
                    list.add(buffer);
                }
                /**否则判断当前下一个是不是符号，是则将之前的数存入，不是则向后取数*/
                else if(expression.charAt(i + 1) < '0' || expression.charAt(i + 1) > '9'){
                    list.add(buffer);
                    buffer = "";
                }
            }
        }
        return list;
    }

    public static List<String> parseInfixToSuffix(List<String> list){

        Stack<String> operStack = new Stack<>();
        List<String> store = new ArrayList<>();

        for (int i = 0; i < list.size(); i++) {
            String str = list.get(i);
            /**若是数字则直接将数字放入数栈中*/
            if(str.matches("\\d+")){
                store.add(str);
            }
            /**如果是左括号则直接将操作数栈*/
            else if("(".equals(str)){
                operStack.push(str);
            }
            /**如果遇到右括号，则将操作数栈中的元素依次放入数栈中，并直到遇到左括号为止*/
            else if(")".equals(str)){
                while(!("(".equals(operStack.peek()))){
                   store.add(operStack.pop());
                }
                /**将对应的括号（ 弹出*/
                operStack.pop();
            }
            else {
                /**
                 * 如果当前运算符比符号栈顶的运算符优先级高，则直接将当前运算符压入到符号栈中
                 */
                while(operStack.size() != 0 && InfixToSuffix.getPriority(operStack.peek()) >= InfixToSuffix.getPriority(str)){
                    store.add(operStack.pop());
                }
                operStack.push(str);
            }
        }
        /**将s1中剩余的运算符依次弹出并压入s2*/
        while(!operStack.empty()){
            store.add(operStack.pop());
        }
        return store;
    }

    /**返回运算符的优先级,约定：数字越大，则优先级越高*/
    public static int getPriority(String oper){
        if("*".equals(oper) || "/".equals(oper)){
            return 1;
        }
        else if("+".equals(oper) || "-".equals(oper)){
            return 0;
        }
        return -1;
    }

    public static int calculator(List<String> list){
        Stack<String> numStack = new Stack<>();
        for (int i = 0; i < list.size(); i++) {
            String str = list.get(i);
            if(str.matches("\\d+")){
                numStack.push(str);
            }
            else {
                String oper = str;
                int num1 = Integer.parseInt(numStack.pop());
                int num2 = Integer.parseInt(numStack.pop());
                int result = 0;
                if("+".equals(oper)){
                    result = num1 + num2;
                }
                else if("-".equals(oper)){
                    result = num2 - num1;
                }
                else if("*".equals(oper)){
                    result = num1 * num2;
                }
                else if("/".equals(oper)){
                    result = num2 / num1;
                }
                numStack.push(result + "");
            }
        }
        return Integer.parseInt(numStack.pop());
    }
}
