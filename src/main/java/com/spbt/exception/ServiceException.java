package com.spbt.exception;

import org.apache.commons.lang3.StringUtils;

/**
 * 业务异常
 */
public class ServiceException extends RuntimeException {

    private static final long serialVersionUID = -6202759933628287239L;

    private String errorCode;

    private String errorMsg;

    public ServiceException(String errorMsg) {
        super(errorMsg);
        this.errorMsg = errorMsg;
    }

    public ServiceException(String errorCode, String errorMsg) {
        super(errorMsg);
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    public ServiceException(Throwable cause, String errorMsg) {
        super(cause);
        this.errorMsg = errorMsg;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    @Override
    public String getMessage() {
        return StringUtils.isEmpty(this.errorMsg) ? super.getMessage() : this.errorMsg;
    }
}
