package com.example.common.api;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 统一返回结果
 * @since 2023/1/9 14:30
 */
@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResultEntity<T> {
    /**
     * 状态码
     * 200 成功
     * 300 重定向
     * 400 请求错误
     * 500 服务器错误
     */
    private Integer status;

    /**
     * 响应信息
     */
    private String message;

    /**
     * 响应数据
     */
    private T data;

    /**
     * 扩展字段1
     */
    private String extend;

    /**
     * 扩展字段2
     */
    private String extendUserCount;


    public ResultEntity(Integer status, String message, T data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    private ResultEntity(ResultCode resultCode, T data) {
        this(resultCode.getStatus(), resultCode.getMessage(), data);
    }

    private ResultEntity(Integer status, String message) {
        this.status = status;
        this.message = message;
    }

    private ResultEntity(ResultCode resultCode) {
        this(resultCode.getStatus(), resultCode.getMessage());
    }

    public static <T> ResultEntity<T> success() {
        return new ResultEntity<>(ResultCode.SUCCESS);
    }

    public static <T> ResultEntity<T> success(T data) {
        return new ResultEntity<>(ResultCode.SUCCESS, data);
    }

    public static <T> ResultEntity<T> failure() {
        return new ResultEntity<>(ResultCode.ERROR);
    }

    public static <T> ResultEntity<T> failure(String msg) {
        return new ResultEntity<>(ResultCode.ERROR.getStatus(), msg);
    }

    public static <T> ResultEntity<T> failure(ResultCode resultCode) {
        return new ResultEntity<>(resultCode);
    }

    public static <T> ResultEntity<T> failure(ResultCode resultCode, T data) {
        return new ResultEntity<>(resultCode, data);
    }

    public static <T> ResultEntity<T> failure(Integer status, String msg) {
        return new ResultEntity<>(status, msg);
    }

    //@Override
    //public String toString() {
    //    return JsonUtil.objectToString(this);
    //}
}
