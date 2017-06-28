package com.spbt.util;

/**
 * <p>
 * </p>
 *
 * @version 1.0
 */
public class UUID extends UIDFactory {
    /**
     * Length bits
     */
    private static final int BITS8 = 8;

    /**4*/
    private static final int FOUR = 4;

    /**
     * Length byte
     */
    private static final int BYTELEN = 16;

    /**
     * Low order 8bits mask
     */
    private static final int LO8BITMASK = 255;

    /**
     * Low order mask
     */
    private static final int LOMASK = 15;

    /** Epoch has millisecond */
    /**
     * High order tag
     */
    private long mHiTag;

    /**
     * Low order tag
     */
    private long mLoTag;

    /**
     * UUID Cache
     */
    private String mUuid;

    /**注释*/
    private static final long JTL = 4294967296L;
    /**长度*/
    private static final long ULEN = 32;
    /**十六进制15*/
    private static final byte NIBBLE = 0xf;

    /**
     * Construct overpass user data.
     *
     * @param highTag High order tag
     * @param loTag   Low order tag
     */
    public UUID(long highTag, long loTag) {
        mHiTag = highTag;
        mLoTag = loTag;
        mUuid = toString(this.toByteArray());
    }

    /**
     * Construct default.
     */
    public UUID() {
        next();
        mUuid = toString(this.toByteArray());
    }

    /**
     * Return high order byte.
     *
     * @param b Object byte
     * @return Result byte
     */
    private static byte hiNibble(byte b) {
        return (byte) ((b >> FOUR) & NIBBLE);
    }

    /**
     * Return low order byte.
     *
     * @param b Object byte
     * @return Result byte
     */
    private static byte loNibble(byte b) {
        return (byte) (b & NIBBLE);
    }

    /**
     * Equals UUID.
     *
     * @param obj Object UUID
     * @return Ture if equal
     */
    public boolean equals(Object obj) {
        try {
            if (obj == null) {
                return false;
            } else {
                UUID uuid = (UUID) obj;
                boolean flag = (uuid.mHiTag == mHiTag) && (uuid.mLoTag == mLoTag);

                return flag;
            }
        } catch (ClassCastException cce) {
            return false;
        }
    }

    /**
     * hashCode
     */
    public int hashCode() {
        return mUuid.hashCode();
    }

    /**
     * Get back next new uid.
     *
     * @return java.lang.String
     */
    public String getNextUID() {
        next();

        return mUuid;
    }

    /**
     * Get back current uid.
     *
     * @return java.lang.String
     */
    public String getUID() {
        return mUuid;
    }

    /**
     * Set current UID.
     *
     * @param uidStr The new uID value
     * @throws Exception Bad string format
     */
    public void setUID(String uidStr) {
        long loTag = 0L;
        long hiTag = 0L;
        int len = uidStr.length();

        if (ULEN != len) {
            return;
        }

        int i = 0;
        int idx = 0;

        for (; i < 2; i++) {
            loTag = 0L;

            for (int j = 0; j < (len / 2); j++) {
                String s = uidStr.substring(idx++, idx);
                int val = Integer.parseInt(s, BYTELEN);

                loTag <<= FOUR;
                loTag |= val;
            }

            if (i == 0) {
                hiTag = loTag;
            }
        }

        mHiTag = hiTag;
        mLoTag = loTag;
        mUuid = toString(this.toByteArray());
    }

    /**
     * Get printable String.
     *
     * @return java.lang.String
     */
    public String toPrintableString() {
        byte[] bytes = toByteArray();

        if (BYTELEN != bytes.length) {
            return "** Bad UUID Format/Value **";
        }

        StringBuffer buf = new StringBuffer();
        int i;

        for (i = 0; i < FOUR; i++) {
            buf.append(Integer.toHexString(hiNibble(bytes[i])));
            buf.append(Integer.toHexString(loNibble(bytes[i])));
        }
        final int ten = 10;
        while (i < ten) {
            buf.append('-');

            int j = 0;

            while (j < 2) {
                buf.append(Integer.toHexString(hiNibble(bytes[i])));
                buf.append(Integer.toHexString(loNibble(bytes[i++])));
                j++;
            }
        }
        buf.append('-');
        for (; i < BYTELEN; i++) {
            buf.append(Integer.toHexString(hiNibble(bytes[i])));
            buf.append(Integer.toHexString(loNibble(bytes[i])));
        }

        return buf.toString();
    }

    /**
     * Return UID String.
     *
     * @return UID String
     */
    public String toString() {
        return mUuid;
    }

    /**
     * Get new UUID instance.
     *
     * @return UUID
     */
    public static UIDFactory getInstance() {
        return new UUID();
    }

    /**
     * Overpass a bytes array generator UID String.
     *
     * @param bytes Object bytes array
     * @return UID String
     */
    public final String toString(byte[] bytes) {
        if (BYTELEN != bytes.length) {
            return "** Bad UUID Format/Value **";
        }

        StringBuffer buf = new StringBuffer();

        for (int i = 0; i < BYTELEN; i++) {
            buf.append(Integer.toHexString(hiNibble(bytes[i])));
            buf.append(Integer.toHexString(loNibble(bytes[i])));
        }

        return buf.toString();
    }

    /**
     * Generator & get back a UUID & cache String.
     */
    public final void next() {
        mHiTag = (System.currentTimeMillis() + (JVMHASH * JTL)) ^ MACHINEID;
        mLoTag = EPOCH + Math.abs(M_RANDOM.nextLong());
        mUuid = toString(this.toByteArray());
    }

    /**
     * Overpass high order tag & low order tag convert to array bytes.
     *
     * @return Array bytes
     */
    public final byte[] toByteArray() {
        byte[] bytes = new byte[BYTELEN];
        int idx = LOMASK;
        long val = mLoTag;

        for (int i = 0; i < BITS8; i++) {
            bytes[idx--] = (byte) (int) (val & (long) LO8BITMASK);
            val >>= BITS8;
        }

        val = mHiTag;

        for (int i = 0; i < BITS8; i++) {
            bytes[idx--] = (byte) (int) (val & (long) LO8BITMASK);
            val >>= BITS8;
        }

        if (!this.isMD5()) {
            return bytes;
        } else {
            return toMD5(bytes);
        }
    }

    public long getmHiTag() {
        return mHiTag;
    }

    public long getmLoTag() {
        return mLoTag;
    }

    public String getmUuid() {
        return mUuid;
    }
}
