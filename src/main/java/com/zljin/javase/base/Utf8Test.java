package com.zljin.javase.base;

import java.io.UnsupportedEncodingException;

/**
 * java常用字符编码 UTF-8
 */
public class Utf8Test {
    public static void main(String[] args) throws Exception{
        // 英文字符 (1字节)
        String englishChar = "A";
        // 欧洲字符 (2字节)
        String europeanChar = "é";
        // 中文字符 (3字节)
        String chineseChar = "中";

        System.out.println(getUTF8BytesLength(englishChar));
        System.out.println(getUTF8BytesLength(europeanChar));
        System.out.println(getUTF8BytesLength(chineseChar));
    }


    /**
     * 获取字符串在 UTF-8 编码下的字节长度
     */
    public static int getUTF8BytesLength(String text) throws UnsupportedEncodingException {
        return text.getBytes("UTF-8").length;
    }
}
