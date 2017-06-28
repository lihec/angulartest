package com.spbt.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p>
 * </p>
 * 
 * @version 1.0
 */
public final class UUIDUtil {
    /**记录日志*/
    private static Logger logger = LoggerFactory.getLogger(UUIDUtil.class);

    private static UIDFactory uuid = null;

    static {
        try {
            uuid = UIDFactory.getInstance("UUID");
        } catch (Exception unsex) {
            logger.error(unsex.toString(), unsex);
        }
    }

    /**
     * Constructor for the UUIDGener object
     */
    private UUIDUtil() {
    }

    /**
     * 获取uuid字符
     * 
     * @author lihe 2013-7-4 下午5:31:09
     * @return
     * @see
     * @since
     */
    public static String getUUID() {
        return uuid.getNextUID();
    }
}
