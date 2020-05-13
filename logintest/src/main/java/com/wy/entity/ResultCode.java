package com.wy.entity;

import com.fasterxml.jackson.annotation.JsonValue;

/**
 * 返回码的枚举
 * 
 * @author xuesinuo
 */
public enum ResultCode {
    // 成功 0~99
    // 一般错误 100~199
    // 身份、权限错误 200~299
    // 外部接口异常 300~399
    /**
     * 成功
     */
    SUCCESS(0), 
    /**
     * 一般错误
     */
    ERROR(100), 
    /**
     * 未登录
     */
    UNSIGN(200), 
    /**
     * （可能已登录但）无权限
     */
    NO_PERMISSION(201),
    /**
     * 微信接口报错
     */
    WECHAT_ERROR(300),
    /**
     * 传入参数有误，需要核验修改
     */
    PARAM_ERROR(400),
    /**
     * 传入数量过大，请改小
     */
    TOO_MANY_NUM(401);

    /**
     * <p>对应的int码</p>
     */
    @JsonValue
    private int code;

    ResultCode(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
