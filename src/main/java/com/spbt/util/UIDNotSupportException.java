package com.spbt.util;

/**
 * <p>
 * </p>
 *
 * @version 1.0
 */
public class UIDNotSupportException extends ClassNotFoundException {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    /**
     * Constructor for the UIDNotSupportException object
     *
     * @param s Description of the Parameter
     */
    public UIDNotSupportException(String s) {
        super(s, null);
    }
}
