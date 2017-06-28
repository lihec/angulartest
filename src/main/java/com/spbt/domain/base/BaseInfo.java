package com.spbt.domain.base;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import java.io.Serializable;

/**
 * pojo基础类，序列化和toString
 *
 * @author 李贺[of253]
 * @date 2013-12-7 下午4:22:51
 *
 */
public abstract class BaseInfo implements Serializable {

    /**
     * default serial version uid.
     */
    private static final long serialVersionUID = 1L;

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }

}