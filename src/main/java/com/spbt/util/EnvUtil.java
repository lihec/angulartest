package com.spbt.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;

/**
 * @类功能说明：运行时环境
 * @公司名称：江苏欧飞
 * @作者：of487
 * @创建时间：2014-03-04
 */
public class EnvUtil {
    private static Logger logger = LoggerFactory.getLogger(EnvUtil.class);

    /**
     * 功能: 获取主机地址
     *
     * @return InetAddress
     */
    public static InetAddress getInetAddress() {
        try {
            return InetAddress.getLocalHost();
        } catch (UnknownHostException e) {
            logger.error("unknown host!", e);
        }

        return null;
    }

    /**
     * 功能: 获取主机ip
     *
     *
     * @return String
     */
    public static String getHostIp() {
        InetAddress netAddress = getInetAddress();
        if (null == netAddress) {
            return null;
        }
        String ip = netAddress.getHostAddress();
        return ip;
    }

    /**
     * 功能: 获取主机ip
     *
     * @param netAddress ip地址信息
     *
     * @return String
     */
    public static String getHostIp(InetAddress netAddress) {
        if (null == netAddress) {
            return null;
        }
        String ip = netAddress.getHostAddress();

        return ip;
    }

    /**
     * 功能: 获取主机名称
     *
     * @param netAddress ip地址信息
     *
     * @return String
     */
    public static String getHostName(InetAddress netAddress) {
        if (null == netAddress) {
            return null;
        }

        String name = netAddress.getHostName();

        return name;
    }

    /**
     * 功能: 获取进程信息
     *
     * @return Map<String, String>
     */
    public static Map<String, String> getProcessInfo() {
        Map<String, String> processMap = new HashMap<String, String>();
        final RuntimeMXBean runtime = ManagementFactory.getRuntimeMXBean();
        String name = runtime.getName();
        String[] keyValues = name.split("@");

        processMap.put("process-num", keyValues[0]);
        processMap.put("process-host", keyValues[1]);

        return processMap;
    }

    public static void main(String[] args) {
        System.out.println(EnvUtil.getHostIp());

    }

}
