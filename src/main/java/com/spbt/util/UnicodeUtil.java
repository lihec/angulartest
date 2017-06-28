package com.spbt.util;

/**
 * 描述：
 *
 * @author 赵贤成[of1142]
 * @date 2015/1/7
 */
public class UnicodeUtil {

    /**
     * 字符串转换unicode
     */
    public static String string2Unicode(String string) {

        StringBuffer unicode = new StringBuffer();

        for (int i = 0; i < string.length(); i++) {

            // 取出每一个字符
            char c = string.charAt(i);
            if(isChinese(c)){
                // 转换为unicode
                unicode.append("\\u" + Integer.toHexString(c));
                continue;
            }

            unicode.append(c);
        }

        return unicode.toString();
    }

    /**
     * unicode 转字符串
     */
    public static String unicode2String(String unicode) {

        StringBuffer string = new StringBuffer();

        String[] hex = unicode.split("\\\\u");
        string.append(hex[0]);
        if(hex.length>1) {
            for (int i = 1; i < hex.length; i++) {
                int len = hex[i].length();
                if (len > 4) {
                    // 转换出每一个代码点
                    int data = Integer.parseInt(hex[i].substring(0, 4), 16);
                    // 追加成string
                    string.append((char) data);
                    // 追加成string
                    string.append(hex[i].substring(4, len));
                } else {
                    int data = Integer.parseInt(hex[i], 16);
                    // 追加成string
                    string.append((char) data);
                }


            }
        }
        return string.toString();
    }

    // 根据Unicode编码完美的判断中文汉字和符号
    public static boolean isChinese(char c) {
        Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
        if (ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS || ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS
                || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_B
                || ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION || ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS
                || ub == Character.UnicodeBlock.GENERAL_PUNCTUATION) {
            return true;
        }
        return false;
    }

    public static void main(String[] args){
        System.out.println(string2Unicode("G-S008208-风声f月影.doc"));
        System.out.println(unicode2String(string2Unicode("G-S008208-风声f月影.doc")));
    }

}
