package com.wy.bean;


public class Address {
    private String id;
    private String userId;
    private String name;
    private String tel;
    private String address;
    private String area;


    public Address() {
    }

    public Address(String id, String userId, String name, String tel, String address, String area) {
        this.id = id;
        this.userId = userId;
        this.name = name;
        this.tel = tel;
        this.address = address;
        this.area = area;
    }

    public String getId() {
        return id;
    }

    public String getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public String getTel() {
        return tel;
    }

    public String getAddress() {
        return address;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    @Override
    public String toString() {
        return "Address{" +
                "id='" + id + '\'' +
                ", userId='" + userId + '\'' +
                ", name='" + name + '\'' +
                ", tel='" + tel + '\'' +
                ", address='" + address + '\'' +
                ", area='" + area + '\'' +
                '}';
    }
}
