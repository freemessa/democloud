package com.example.common.utils;


import com.example.common.api.ResultCode;

/**
 * 自定义异常
 *
 * @author chengqi
 * @since 2021-2
 */
public class ComException extends RuntimeException {
    /**
     * 错误码
     */
    private final Integer errorCode;

    /**
     * 错误信息
     */
    private final String errorMsg;

    public Integer getErrorCode() {
        return errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public ComException(String errorMsg) {
        this(ResultCode.ERROR.getStatus(), errorMsg);
    }

    //FIXME 这里作用域为private，意味其他人没法扩展这个Exception。
    public ComException(Integer errorCode, String errorMsg) {
        super(errorMsg);
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    public ComException(ResultCode resultCode) {
        super(resultCode.getMessage());
        this.errorCode = resultCode.getStatus();
        this.errorMsg = resultCode.getMessage();
    }

    public static void throwEx(ResultCode resultCode) {
        throw new ComException(resultCode);
    }

    //FIXME 如果这里errorCode可以随意定义，那么ResultCode就毫无意义 ~~
    public static void throwEx(Integer errorCode, String errorMsg) {
        throw new ComException(errorCode, errorMsg);
    }
}
