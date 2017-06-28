package com.spbt.util;

import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * ip地址工具类
 * 
 * @author lihe 2013-7-4 下午5:26:13
 * @see
 * @since
 */
public final class IPUtil {
    /**
     * 记录日志
     */
    private static Logger logger = LoggerFactory.getLogger(IPUtil.class);

    /**
     * 私有构造
     * 
     * @author 李贺[of253]
     * @date 2013-12-6 下午4:55:34
     */
    private IPUtil() {
    }

    /**
     * 判断是否是欧飞内部ip
     * 
     * @param ip
     * @return
     * @author lihe
     * @date 2012-8-24上午10:30:25
     */
    public static boolean validateIsInnerIp(String ip) {
        boolean isInner = false;
        if (ip.startsWith("127.0.0.1") || ip.startsWith("192.168.") || ip.equals("202.102.53.136")
                || ip.equals("202.102.53.154") || ip.equals("202.102.53.155") || ip.equals("202.102.53.148")
                || ip.equals("202.102.53.149") || ip.equals("202.102.53.158")) {
            // 认证ip通过
            isInner = true;
        }
        return isInner;
    }

    /**
     * 获取客户端ip
     */
    public static String getIp(HttpServletRequest request) {

        String ip = request.getHeader("X-Real-IP");
        String xip = request.getHeader("x-forwarded-for");

        ip = getRealIp(ip, request, "REMOTE-HOST");
        ip = getRealIp(ip, request, "x-forwarded-for");
        ip = getRealIp(ip, request, "Proxy-Client-IP");
        ip = getRealIp(ip, request, "WL-Proxy-Client-IP");
        ip = getRealIp(ip, request, "HTTP_CLIENT_IP");
        ip = getRealIp(ip, request, "HTTP_X_FORWARDED_FOR");

        if (!checkIP(ip)) {
            ip = request.getRemoteAddr();
        }

        // 多级反向代理
        if (null != ip && !"".equals(ip.trim())) {
            StringTokenizer st = new StringTokenizer(ip, ",");
            if (st.countTokens() > 1) {
                logger.error("getip StringTokenizer  " + ip);
                return st.nextToken();
            }
        }

        if (null != ip && !ip.equals(xip)) {
            logger.error("getipne   X-Real-IP  " + ip + "    ,x-forwarded-for " + xip);
        }

        return ip;
    }

    /**
     * 根据header获取真实ip
     * 
     * @author 李贺[of253]
     * @date 2013-12-10 下午3:38:20
     * @param ip
     * @param request
     * @param header
     * @return
     */
    private static String getRealIp(String ip, HttpServletRequest request, String header) {
        if (!checkIP(ip)) {
            return request.getHeader(header);
        }
        return ip;
    }

    /**
     * 验证ip地址格式是否正确
     * 
     * @author lihe 2013-7-4 下午5:26:26
     * @param ip
     * @return
     * @see
     * @since
     */
    private static boolean checkIP(String ip) {
        final int ipLen = 4;
        if (StringUtils.isNotBlank(ip) && ip.split("\\.").length == ipLen) {
            return true;
        }
        return false;
    }

}