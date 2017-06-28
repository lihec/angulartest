package com.spbt.domain;

import java.io.Serializable;

/**
 *
 * @author 李贺[of253]
 * @date 2015/8/10 10:41
 */
public class User implements Serializable{

    private static final long serialVersionUID = -4311093102101270437L;

    private String usercode;

    private String password;

    public String getUsercode() {
        return usercode;
    }

    public void setUsercode(String usercode) {
        this.usercode = usercode;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("User{");
        sb.append("usercode='").append(usercode).append('\'');
        sb.append(", password='").append(password).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
