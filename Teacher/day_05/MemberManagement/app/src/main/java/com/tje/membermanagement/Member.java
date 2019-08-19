package com.tje.membermanagement;

import java.io.Serializable;

public class Member implements Serializable {
    private String id;
    private String password;
    private String name;
    private int age;
    private String phone;
    private String address;
    private String registDate;

    public Member() {}

    public Member(String id, String password, String name, int age, String phone, String address, String registDate) {
        this.id = id;
        this.password = password;
        this.name = name;
        this.age = age;
        this.phone = phone;
        this.address = address;
        this.registDate = registDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getRegistDate() {
        return registDate;
    }

    public void setRegistDate(String registDate) {
        this.registDate = registDate;
    }
}
