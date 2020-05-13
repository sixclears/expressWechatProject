package com.wy.bean;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.lang.reflect.Type;

public class User {
    private String id;
    private String name;
    private String  type;
    @JsonIgnore
    private String wxOpenId;
    @JsonIgnore
    private Boolean isdelete;
    /**
     * 用于在本系统session从一同存放wxSessionKey
     */
    @JsonIgnore
    private String wxSessionKey;
    /**
     * 非数据库字段，用于登录身份
     */
    private String token;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getWxOpenId() {
        return wxOpenId;
    }

    public void setWxOpenId(String wxOpenId) {
        this.wxOpenId = wxOpenId;
    }

    public Boolean getIsdelete() {
        return isdelete;
    }

    public void setIsdelete(Boolean isdelete) {
        this.isdelete = isdelete;
    }

    public String getWxSessionKey() {
        return wxSessionKey;
    }

    public void setWxSessionKey(String wxSessionKey) {
        this.wxSessionKey = wxSessionKey;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", wxOpenId='" + wxOpenId + '\'' +
                ", isdelete=" + isdelete +
                ", wxSessionKey='" + wxSessionKey + '\'' +
                ", token='" + token + '\'' +
                '}';
    }
}
