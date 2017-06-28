package com.spbt.util;

import java.util.Random;

import org.apache.commons.lang.StringUtils;

/**
 * 随机字符工具类
 * 
 * @author lihe 2013-7-4 下午5:28:40
 * @see
 * @since
 */
public class RandomUtils {
    public static final String ALLCAHR = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    public static final String LETTERCHAR = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    public static final String NUMBERCHAR = "0123456789";
    public static final String BIGLETTERPASSCHAR = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    public static final String BIGLETTERNUMBERPASSCHAR = "A2B3C4E5F6G7H8K9MNPRSTUVWXYZ";

    /**
     * 根据密码生成策略返回一个定长的随机字符串
     */
    public static String generatePass(String cpassmode, int length) {
        StringBuffer sb = new StringBuffer();
        Random random = new Random();
        final int charNumLen = 27;
        final int charLen = 25;
        final int numLen = 9;

        if (StringUtils.isEmpty(cpassmode) || "0".equals(cpassmode)) {
            // 纯大写字母+数字模式
            for (int i = 0; i < length; i++) {
                sb.append(BIGLETTERNUMBERPASSCHAR.charAt(random.nextInt((int) (Math.random() * charNumLen + 1))));
            }
        } else if ("1".equals(cpassmode)) {
            // 纯大写字母模式
            for (int i = 0; i < length; i++) {
                sb.append(BIGLETTERPASSCHAR.charAt(random.nextInt((int) (Math.random() * charLen + 1))));
            }
        } else if ("2".equals(cpassmode)) {
            // 纯数字模式
            for (int i = 0; i < length; i++) {
                sb.append(NUMBERCHAR.charAt(random.nextInt((int) (Math.random() * numLen + 1))));
            }
        } else {
            // "生成密码字符串发生异常!"
            return null;
        }
        return sb.toString();
    }

    /**
     * 生成cardname
     * 
     * @author lihe 2013-7-4 下午5:28:59
     * @param num
     * @param fixdlenth
     * @param chead
     * @param cardnamecode
     * @return
     * @see
     * @since
     */
    public String generateCardName(long num, int fixdlenth, String chead, String cardnamecode) {
        StringBuffer sb = new StringBuffer();
        String strNum = String.valueOf(num);
        if (fixdlenth - strNum.length() >= 0) {
            sb.append(chead).append(cardnamecode).append(generateZeroString(fixdlenth - strNum.length()));
        } else {
            // "将数字" + num + "转化为长度为" + fixdlenth + "的字符串发生异常!"
            return null;
        }
        sb.append(strNum);
        return sb.toString();
    }

    /**
     * 返回一个定长的随机纯字母字符串(只包含大小写字母)
     * 
     * @param length
     *            随机字符串长度
     * @return 随机字符串
     */
    public static String generateMixString(int length) {
        StringBuffer sb = new StringBuffer();
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            sb.append(ALLCAHR.charAt(random.nextInt(LETTERCHAR.length())));
        }
        return sb.toString();
    }

    /**
     * 返回一个定长的随机纯大写字母字符串
     * 
     * @param length
     *            随机字符串长度
     * @return 随机字符串
     */
    public static String generateLowerString(int length) {
        return generateMixString(length).toLowerCase();
    }

    /**
     * 返回一个定长的随机纯小写字母字符串
     * 
     * @param length
     *            随机字符串长度
     * @return 随机字符串
     */
    public static String generateUpperString(int length) {
        return generateMixString(length).toUpperCase();
    }

    /**
     * 生成一个定长的纯0字符串
     * 
     * @param length
     *            字符串长度
     * @return 纯0字符串
     */
    public String generateZeroString(int length) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            sb.append('0');
        }
        return sb.toString();
    }

    /**
     * 根据数字生成一个定长的字符串，长度不够前面补0
     * 
     * @param num
     *            数字
     * @param fixdlenth
     *            字符串长度
     * @return 定长的字符串
     */
    public String toFixdLengthString(long num, int fixdlenth) {
        StringBuffer sb = new StringBuffer();
        String strNum = String.valueOf(num);
        if (fixdlenth - strNum.length() >= 0) {
            sb.append(generateZeroString(fixdlenth - strNum.length()));
        } else {
            // "将数字" + num + "转化为长度为" + fixdlenth + "的字符串发生异常！"
            return null;
        }
        sb.append(strNum);
        return sb.toString();
    }

    /**
     * 根据数字生成一个定长的字符串，长度不够前面补0
     * 
     * @param num
     *            数字
     * @param fixdlenth
     *            字符串长度
     * @return 定长的字符串
     */
    public String toFixdLengthString(int num, int fixdlenth) {
        StringBuffer sb = new StringBuffer();
        String strNum = String.valueOf(num);
        if (fixdlenth - strNum.length() >= 0) {
            sb.append(generateZeroString(fixdlenth - strNum.length()));
        } else {
            // "将数字" + num + "转化为长度为" + fixdlenth + "的字符串发生异常！"
            return null;
        }
        sb.append(strNum);
        return sb.toString();
    }
}