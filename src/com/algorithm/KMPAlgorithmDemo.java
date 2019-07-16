package com.algorithm;

/**
 * 实现KMP算法
 */
public class KMPAlgorithmDemo {
    public static void main(String[] args) {
        String str1= "BBC ABCDAB ABCDABCDABDE";
        String str2 = "ABCDABD";

//        int[] next = KMPAlgorithmDemo.partialMatchingValue(str2);
//        int index = KMPAlgorithmDemo.kmpSearch(str1,str2,next);
//        System.out.println(str2 + " 对应的部分匹配表 " + Arrays.toString(next));
//        System.out.println(index);
        int index = KMPAlgorithmDemo.kmp(str1,str2);
        System.out.println(index);
    }
    /**
     * kmp搜索算法*
     * @param str1 源字符串
     * @param str2 子串
     * @param next 部分匹配表(子串对应的部分匹配表)
     * @return 返回第一个匹配的位置，否则返回 -1
     */
    public static int kmpSearch(String str1,String str2,int[] next){
        for (int i = 0,j = 0; i < str1.length(); i++) {
            /**当n不等于m时*/
            while(j > 0 && str1.charAt(i) != str2.charAt(j)){
                j = next[j - 1];
            }
            /**当n等于m时*/
            if(str1.charAt(i) == str2.charAt(j)){
                j++;
            }
            if(j == str2.length()){
                return i - j + 1;
            }
        }
        return -1;
    }
    /**获取字符串（子串）的部分匹配值表*/
    public static int[] partialMatchingValue(String str){
        int[] next = new int[str.length()];
        /**如果字符串长度为1，则对应的部分匹配值为0*/
        next[0] = 0;
        for (int i = 1,j = 0; i < str.length(); i++) {
            /**如果n ！= m*/
            while(j > 0 && str.charAt(i) != str.charAt(j)){
                j = next[j - 1];
            }
            /**若n == m满足时，部分匹配值就+1*/
            if(str.charAt(i) == str.charAt(j)){
                j++;
            }
            next[i] = j;
        }
        return next;
    }
    /**使用kmp算法对文档内容进行查找*/
    public static int kmp(String str1,String str2){
        /**根据子串得到对应的部分匹配值表*/
        int[] next = KMPAlgorithmDemo.partialMatchingValue(str2);
        int index = KMPAlgorithmDemo.kmpSearch(str1,str2,next);
        return index;
    }
}
