package com.tree;

/**
 * 对编码进行解码，转成对应的字符串
 */
public class HuffmanDecodeDemo {
    public static void main(String[] args) {
        String str = "i like like like java do you like a java";
        byte[] huffmanCoding = HuffmanCodingDemo.generateHuffmanZip(str);
        String value = HuffmanDecodeDemo.decode(true,(byte)-2);
        System.out.println(value);
    }

    /**
     *功能：将一个byte转成一个二进制的字符串
     * @param flag 标志位，判断取到的 b 是否满足8位
     * @param b 将b转成int
     * @return
     */
    public static String decode(boolean flag,byte b){
        int temp = b;
        System.out.println("temp1: " + temp);
        if(flag){
            temp |= 256;
        }
        String str = Integer.toBinaryString(temp);
        if(flag){
            /**返回后8位*/
            return str.substring(str.length() - 8);
        }
        else {
            return str;
        }
    }
}
