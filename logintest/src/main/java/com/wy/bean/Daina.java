package com.wy.bean;

public class Daina {
    private String id;
    private String userId;
    private String name;
    private String expressNumber;
    private String tip;
    private String address;
    private String myAddress;
    private String expressType;
    private Integer salary;
    private String who;
    private String tel;
    private Integer status;

    public Daina() {
    }

    public Daina(String id, String userId, String name, String expressNumber, String tip, String address, String myAddress, String expressType, Integer salary, String who, String tel, Integer status) {
        this.id = id;
        this.userId = userId;
        this.name = name;
        this.expressNumber = expressNumber;
        this.tip = tip;
        this.address = address;
        this.myAddress = myAddress;
        this.expressType = expressType;
        this.salary = salary;
        this.who = who;
        this.tel = tel;
        this.status = status;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMyAddress() {
        return myAddress;
    }

    public void setMyAddress(String myAddress) {
        this.myAddress = myAddress;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public String getExpressNumber() {
        return expressNumber;
    }

    public String getTip() {
        return tip;
    }

    public String getAddress() {
        return address;
    }

    public String getExpressType() {
        return expressType;
    }

    public Integer getSalary() {
        return salary;
    }

    public String getWho() {
        return who;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setExpressNumber(String expressNumber) {
        this.expressNumber = expressNumber;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setExpressType(String expressType) {
        this.expressType = expressType;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
    }

    public void setWho(String who) {
        this.who = who;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    @Override
    public String toString() {
        return "Daina{" +
                "id='" + id + '\'' +
                ", userId='" + userId + '\'' +
                ", name='" + name + '\'' +
                ", expressNumber='" + expressNumber + '\'' +
                ", tip='" + tip + '\'' +
                ", address='" + address + '\'' +
                ", myAddress='" + myAddress + '\'' +
                ", expressType='" + expressType + '\'' +
                ", salary=" + salary +
                ", who='" + who + '\'' +
                ", tel='" + tel + '\'' +
                ", status=" + status +
                '}';
    }
}
