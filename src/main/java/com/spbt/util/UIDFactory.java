package com.spbt.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetAddress;
import java.security.MessageDigest;
import java.util.Random;

/**
 * <p>
 * </p>
 *
 * @version 1.0
 */
public abstract class UIDFactory {
    /**记录日志*/
    private static Logger logger = LoggerFactory.getLogger(UIDFactory.class);
    /**
     * Global Unified Identifier
     */
    public static final String UID_GUID = "GUID";

    /**
     * United Unified Identifier
     */
    public static final String UID_UUID = "UUID";

    /**
     * Current Epoch millis SEED
     */
    protected static final long EPOCH = System.currentTimeMillis();

    /**
     * JVM Hashcode
     */
    protected static final long JVMHASH = Integer.MIN_VALUE;

    /**
     * Epoch has millisecond
     */
    protected static final long MACHINEID = getMachineID();

    /**
     * Random by seed
     */
    protected static final Random M_RANDOM = new Random(EPOCH);

    /**
     * MD5 Instance
     */
    private static MessageDigest md5;

    /* Initialize MD5 factory */
    static {
        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (java.security.NoSuchAlgorithmException ex) {
            logger.error(ex.toString(), ex);
        }
    }

    /**
     * MD5 flag
     */
    private boolean isMd5 = false;

    /**
     * Get Default UIDFactory.
     *
     * @return UIDFactory UID manager object
     */
    public static UIDFactory getDefault() {
        return UUID.getInstance();
    }

    /**
     * Get Specified UIDFactory.
     *
     * @param uidfactory Description of the Parameter
     * @return UIDFactory
     * @throws Exception Description of the Exception
     */
    public static UIDFactory getInstance(String uidfactory) {
        if (uidfactory.equalsIgnoreCase(UID_UUID)) {
            return UUID.getInstance();
        }
        return null;
    }

    /**
     * Get next UID.
     *
     * @return String Storagable UID
     */
    public abstract String getNextUID();

    /**
     * Get current UID.
     *
     * @return String Storagable UID
     */
    public abstract String getUID();

    /**
     * Is MD5 switch ON.
     *
     * @return ON is true.
     */
    public boolean isMD5() {
        return isMd5;
    }

    /**
     * Set MD5 output.
     *
     * @param flag MD5 switch
     */
    public void setMD5(boolean flag) {
        isMd5 = flag;
    }

    /**
     * Set current UID.
     *
     * @param uid Object uid
     * @throws Exception Description of the Exception
     */
    public abstract void setUID(String uid);

    /**
     * Return Printable ID String.
     *
     * @return String
     */
    public abstract String toPrintableString();

    /**
     * Convert bytes to MD5 bytes.
     *
     * @param bytes Description of the Parameter
     * @return
     */
    protected static byte[] toMD5(byte[] bytes) {
        return md5.digest(bytes);
    }

    /**
     * Gets the machineID attribute of the GUID class
     *
     * @return The machineID value
     */
    private static long getMachineID() {
        long i = 0;

        try {
            InetAddress inetaddress = InetAddress.getLocalHost();
            byte[] abyte0 = inetaddress.getAddress();

            i = toInt(abyte0);
        } catch (Exception ex) {
            logger.error(ex.toString(), ex);
        }
        return i;
    }

    /**
     * Convert bytes to int utils.
     *
     * @param abyte0 Object bytes array
     * @return Result int
     */
    private static int toInt(byte[] abyte0) {
        final int twentyFour = 24;
        final int sixteen = 16;
        final int eight = 8;
        final int b1 = 0xff000000;
        final int b2 = 0xff0000;
        final int b3 = 0xff00;
        final int b4 = 0xff;
        final int three = 3;
        int i = ((abyte0[0] << twentyFour) & b1) | ((abyte0[1] << sixteen) & b2) | ((abyte0[2] << eight) & b3)
                | (abyte0[three] & b4);
        return i;
    }

}
