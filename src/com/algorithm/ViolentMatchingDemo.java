package com.algorithm;
/**
 * 用暴力匹配实现查找
 */
public class ViolentMatchingDemo {
    public static void main(String[] args) {
        String str1 = "硅硅谷 尚硅谷你尚硅 尚硅谷你尚硅谷你尚硅你好";
        String str2 = "尚硅谷你尚硅你";

        int result = ViolentMatchingDemo.violentMatching(str1,str2);
        System.out.println(result);
    }
    public static int violentMatching(String str1,String str2){
        char[] char1 = str1.toCharArray();
        char[] char2 = str2.toCharArray();
        int char1Len = char1.length;
        int char2Len = char2.length;
        /**i 指向char1索引，j 指向char2索引*/
        int i = 0;
        int j = 0;
        /**保证在匹配时不越界*/
        while(i < char1Len && j < char2Len){
            /**匹配成功，字符后移，继续匹配*/
            if(char1[i] == char2[j]){
                i++;
                j++;
            }
            else {
                i = i - (j - 1);
                j = 0;
            }
        }
        System.out.println("j " + j);
        System.out.println("i " + i);
        /**判断是否匹配成功*/
        if(j == char2Len){
            return i - j;
        }
        return -1;
    }
}
