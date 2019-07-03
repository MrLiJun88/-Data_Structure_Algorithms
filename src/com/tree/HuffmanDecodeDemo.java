package com.tree;

import java.util.*;

/**
 * 对编码进行解码，转成对应的字符串
 * 对数据进行解码
 */
public class HuffmanDecodeDemo {
    public static void main(String[] args) {
        String str = "i like like like java do you like a java";
        byte[] huffmanCoding = HuffmanCodingDemo.generateHuffmanZip(str);
        /**对压缩后的结果： [-88, -65, -56 进行解码,转成补码形式的二进制字符串*/
        String[] codes = HuffmanDecodeDemo.decode(HuffmanCodingDemo.codingMap,huffmanCoding);
        for(String s : codes){
            System.out.print(s);
        }
    }
    /**
     *
     * @param codingMap 霍夫曼编码表map 如 a -> 100,b -> 0110
     * @param huffmanCodings 霍夫曼编码后得到的字节数组 如：压缩后的结果： [-88, -65, -56,
     * @return
     */
    public static String[] decode(Map<Character,String> codingMap,byte[] huffmanCodings){
        /**1.先得到huffmanCodings 对应的二进制字符串，如:10110011,01101100*/
        StringBuilder stringBuilder = new StringBuilder();
        /**将byte[]转成对应的二进制字符串*/
        for (int i = 0; i < huffmanCodings.length; i++) {
            /**如果是最后一个字节，我们不需要补高位,因为有可能没有达到8位*/
            if(i == huffmanCodings.length - 1){
                stringBuilder.append(HuffmanDecodeDemo.byteToBitString(false,huffmanCodings[i]));
            }
            else {
                stringBuilder.append(HuffmanDecodeDemo.byteToBitString(true,huffmanCodings[i]));
            }
        }
        /**把字符串按照指定的Huffman表进行解码
         * 先得到霍夫曼编码的反向编码 如:
         * map: {000=l, 01= , 100=a, 101=i, 11010=y, 0011=o, 1111=k, 11001=u, 1110=e
         * */
        Map<String,Character> reverseMap = new HashMap<>();
        for(Map.Entry<Character,String> entry : codingMap.entrySet()){
            reverseMap.put(entry.getValue(),entry.getKey());
        }
        /**根据倒置后的huffman编码表，查找到对应的value，并放入到list中*/
        List<Character> charList = new ArrayList<>();
        for (int i = 0; i < stringBuilder.length();) {
            /**count 计数器，用于向后取数*/
            int count = 1;
            boolean flag = true;
            String key = new String();
            while(flag){
                /**i不动，让count移动，直到匹配到一个字符,跳出*/
                key = stringBuilder.substring(i,i + count);
                if(reverseMap.containsKey(key)){
                    /**找到匹配的，则结束本次循环*/
                    flag = false;
                }
                /**还没有找到匹配的*/
                else {
                    count++;
                }
            }
            /**将找到的字符添加到集合中，将将i位置直接移动到count位置上*/
            charList.add(reverseMap.get(key));
            i += count;
        }
        /**把list中的数据，放入到数组中，并返回*/
        String[] str = new String[charList.size()];
        for (int i = 0; i < str.length; i++) {
            str[i] = charList.get(i) +"";
        }
        return str;
    }
    /**
     *功能：将一个byte转成一个二进制的字符串
     * @param flag 标识位，判断取到的 b 是否满足8位
     * @param b 对应二进制的字符串，（按补码返回）
     * @return
     */
    public static String byteToBitString(boolean flag,byte b){
        int temp = b;
        /**如果一正数，则要对高位进行补位*/
        if(flag){
            temp |= 256;
        }
        /**返回的是temp对应的二进行补码*/
        String str = Integer.toBinaryString(temp);
        if(flag){
            /**返回后8位*/
            return str.substring(str.length() - 8);
        }
        /**位数不满8位，直接返回*/
        else {
            return str;
        }
    }
}
