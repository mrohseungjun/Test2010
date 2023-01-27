package com.example.test2010;

public class Member {
    String name;
    String age;
    String tel;
    String addr;
    String eamil;

    public Member(){

    }

    public Member(String name, String age, String tel, String addr, String eamil) {
        this.name = name;
        this.age = age;
        this.tel = tel;
        this.addr = addr;
        this.eamil = eamil;
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

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public String getEamil() {
        return eamil;
    }

    public void setEamil(String eamil) {
        this.eamil = eamil;
    }
}
