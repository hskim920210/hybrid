package com.tje.networkappwithjson;

public class Member {
    private String name;
    private String age;
    private String tel;
    private String imageUri;

    public Member(){}

    public Member(String name, String age, String tel, String imageUri) {
        this.name = name;
        this.age = age;
        this.tel = tel;
        this.imageUri = imageUri;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getImageUri() {
        return imageUri;
    }

    public void setImageUri(String imageUri) {
        this.imageUri = imageUri;
    }
}
