package com.tree;

import java.io.*;
import java.util.Map;
/**
 * 通过Huffman编码来压缩文件
 */
public class FileCompressionByHuffman {
    public static void main(String[] args) {
        String scrFile = "G:\\abc.xml";
        String objFile = "G:\\ppp.zip";
        FileCompressionByHuffman.zipFile(scrFile,objFile);
        String destFile = "G:\\abc2.xml";
        FileCompressionByHuffman.decompressionFile(objFile,destFile);
    }
    /**
     * 对文件进行压缩
     * @param srcFile 要压缩文件的全路径
     * @param objFile 将压缩文件放置在哪个目录下
     */
    public static void zipFile(String srcFile,String objFile) {
        /**创建文件的输入流*/
        /**创建文件的输出流*/
        /**创建一个和文件输出流相关联的ObjectOutputStream*/
       try(BufferedInputStream inputStream = new BufferedInputStream(new FileInputStream(new File(srcFile)));
           BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(new File(objFile)));
           ObjectOutputStream oos = new ObjectOutputStream(outputStream))
       {
           byte[] buffer = new byte[inputStream.available()];
           /**将原文件中数据读入buffer字节数组中*/
           inputStream.read(buffer);
           /**对源文件进行压缩,将byte[]转成String去压缩*/
           byte[] huffmanCodes = HuffmanCodingDemo.generateHuffmanZip(new String(buffer));
           /**将压缩后的数据以对象流的方式写入huffman编码，以便于我们恢复原文件时使用*/
           oos.writeObject(huffmanCodes);
           /**将对应的huffman编码表也存入*/
           oos.writeObject(HuffmanCodingDemo.codingMap);
       }
       catch (IOException e){
           System.out.println(e.getMessage());
       }
    }
    /**
     * 对上面已经压缩后的文件进行解压
     * @param zipFile 将要解压的文件
     * @param destFile 解压到哪个路径
     */
    public static void decompressionFile(String zipFile,String destFile){
        try(BufferedInputStream bis = new BufferedInputStream(new FileInputStream(new File(zipFile)));
            ObjectInputStream ois = new ObjectInputStream(bis);
            BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(new File(destFile))))
        {
            /**以对象流的方法读出来*/
            byte[] huffmanCodes = (byte[])ois.readObject();
            Map<Character,String> codingMap = (Map<Character,String>)ois.readObject();
            String[] resultStr = HuffmanDecodeDemo.decode(codingMap,huffmanCodes);
            /**将resultStr写入到指定的文件中*/
            for (int i = 0; i < resultStr.length; i++) {
                bos.write(resultStr[i].getBytes());
            }
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
