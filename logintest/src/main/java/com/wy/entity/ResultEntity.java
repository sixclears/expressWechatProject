package com.wy.entity;

/**
 * ,用于统一返回结果
 * ,使用静态方法对象
 * @author xuesinuo
 * @param <T> data type 查询接口请指定泛型
 */
public class ResultEntity<T> {
    private ResultCode code;
    private String message;
    private T data;

    private ResultEntity() {
    }

    public ResultCode getCode() {
        return code;
    }

    public ResultEntity<T> setCode(ResultCode code) {
        this.code = code;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public ResultEntity<T> setMessage(String message) {
        this.message = message;
        return this;
    }

    public T getData() {
        return data;
    }

    public ResultEntity<T> setData(T data) {
        this.data = data;
        return this;
    }

    public static <T> ResultEntity<T> success() {
        return new ResultEntity<T>().setCode(ResultCode.SUCCESS).setMessage("success");
    }

    public static <T> ResultEntity<T> success(T data) {
        return new ResultEntity<T>().setCode(ResultCode.SUCCESS).setMessage("success").setData(data);
    }

    public static <T> ResultEntity<T> error() {
        return new ResultEntity<T>().setCode(ResultCode.ERROR).setMessage("error");
    }

    public static <T> ResultEntity<T> error(ResultCode code, String message) {
        return new ResultEntity<T>().setCode(code).setMessage(message);
    }
    
    public static <T> ResultEntity<T> error(String message) {
        return new ResultEntity<T>().setCode(ResultCode.ERROR).setMessage(message);
    }

    public static <T> ResultEntity<T> error(T data) {
        return new ResultEntity<T>().setCode(ResultCode.ERROR).setMessage("error").setData(data);
    }

    public static <T> ResultEntity<T> error(ResultCode code, String message, T data) {
        return new ResultEntity<T>().setCode(code).setMessage(message).setData(data);
    }
}
