package com.spbt.util;

import java.util.regex.Pattern;

/**
 * 验证帮助类
 * 
 * @author lihe 2013-7-4 下午5:31:23
 * @see
 * @since
 */
public final class ValidUtil {

    /**
     * 私有构造
     * 
     * @author 李贺[lihe]
     * @date 2013-12-6 下午4:49:01
     */
    private ValidUtil() {
    }

    /**
     * 手机号码:13333333333
     */
    public static final String MOBILE = "^(((14[0-9]{1})|(13[0-9]{1})|(15[0-9]{1})|(18[0-9]{1}))+\\d{8})$";

    /**
     * QQ
     * 必须是数字，且首位不能为0，最短4位，最长15位,可以为空
     */
    public static final String QQ = "([1-9][0-9]{4,13})?";

    /**
     * 邮箱:service@ofpay.com
     */
    public static final String EMAIL = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    /**
     * 邮箱前缀
     */
    public static final String EMAILPRE = "[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*";

    /**
     * 固定电话:025-88888888
     */
    public static final String TELL = "^\\d{3,4}-\\d{7,8}$";

    /**
     * 身份证号码:15 或 17为数字 + 字母
     */
    public static final String IDNO = "^(\\d{15}|\\d{17}[A-Za-z0-9])$";

    /**
     * 判断是手机号
     */
    public static final String PRICEMODULE = "5|6|7";

    /**
     * 是否是数字
     */
    public static final String NUMBER = "\\d*";

    /**
     * 是否是IP地址段
     */
    public static final String SHORTIP = "25[0-5]|2[0-4]\\d|((1\\d{2})|([1-9]?\\d))";

    public static final String NUMWITHPOINT = "^\\d+[\\.]?\\d*$";

    /**
     * 最多三位小数的正实数
     */
    public static final String NUMWITH3POINT = "^[0-9]+(.[0-9]{1,3})?$";

    /**
     * 手机号码或者为空
     */
    public static final String MOBILEOREMPTY = "^(((14[0-9]{1})|(13[0-9]{1})|(15[0-9]{1})|(18[0-9]{1}))+\\d{8})+|$";

    /**
     * 邮箱:service@ofpay.com或者为空
     */
    public static final String EMAILOREMPTY = "^([_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,}))+|$";

    public static final String PWDSTRING = "[A-Za-z]";

    public static final String PWDNUM = "[0-9]";

    public static final String SPECIALSTRING = "[`~!@#$%^&*()\\-_+=\\\\|{}':;'\",\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";

    public static final String SECURITYQ1 = "[1-5]{1}";
    public static final String SECURITYQ2 = "[6-9]{1}||10";
    public static final String SECURITYQ3 = "1[1-5]{1}";

    /**
     * 是否是MAC地址
     */
    public static final String MAC = "[0-9A-F]{12}";

    /**
     * 是否是手机号码
     * 
     * @author 李贺[lihe]
     * @date 2013-12-9 上午10:36:24
     * @param str
     * @return
     */
    public static boolean isMobile(String str) {
        final int mobLen = 11;
        if (str.trim().length() != mobLen) {
            return false;
        }

        String regex = "^(((14[0-9]{1})|(13[0-9]{1})|(15[0-9]{1})|(18[0-9]{1}))+\\d{8})$";
        return str.trim().matches(regex);
    }

    /**
     * 判断密码是否是字母和数字的组合
     * 
     * @param str
     * @return
     */
    public static boolean isPwd(String str) {
        Pattern p1 = Pattern.compile(PWDNUM);
        Pattern p2 = Pattern.compile(PWDSTRING);
        return (p1.matcher(str).find() && p2.matcher(str).find());
    }

    /**
     * 判断密码是否包含特殊字符
     * 
     * @param str
     * @return
     */
    public static boolean isSpecialString(String str) {
        Pattern p = Pattern.compile(SPECIALSTRING);
        return p.matcher(str).find();
    }

    /**
     * 短信验证码
     */
    public static final String MOBILECODE = "\\d{6}";

    /**
     * 邮箱验证码
     */
    public static final String EMAILCODE = "\\d{6}";

    /**
     * 移动密保序列号
     */
    public static final String TOKENNO = "\\d{13}";

    /**
     * 移动密保序口令
     */
    public static final String TOKENCODE = "\\d{6}";

    /**
     * 安全策略类型
     */
    public static final String PLOYTYPE = "1|2|3";

    /**
     * 安全策略值
     */
    public static final String PLOYSTAT = "0|1";
    /**
     * 金额
     */
    public static final String CREDIT = "^\\d+[\\.]?\\d*$||''";

    public static final String CREDIT2 = "^\\d+[\\.]?\\d*$||-\\d+[\\.]?\\d*$||''";

    /**
     * 日期格式 ：yyyy-MM-dd HH:mm:ss
     * */
    public static final String DATEFORMAT = "^(\\d{4})-(0\\d{1}|1[0-2])-(0\\d{1}|[12]\\d{1}|3[01])\\s(0\\d{1}|1\\d{1}|2[0-3]):[0-5]\\d{1}:([0-5]\\d{1})$";

    /**
     * 只是验证日期格式，不验证其正确性。
     * 基本格式：yyyy-MM-dd HH:mm:ss
     * */
    public static boolean isDateSimpleFormat(String dateTime) {
        Pattern pattern = Pattern.compile(DATEFORMAT);
        return pattern.matcher(dateTime).find();
    }
}
