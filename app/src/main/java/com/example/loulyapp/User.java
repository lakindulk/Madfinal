package com.example.loulyapp;

public class User {

    private String ID;
    private String Name;
    private Long PhoneNumber;
    private Integer Age;
    private String Gender;
    private String Area;
    private String PW;


    public User(String ID, String name, Long PhoneNumber, Integer age, String gender, String area, String PW) {
        this.ID = ID;
        Name = name;
        this.PhoneNumber = PhoneNumber;
        Age = age;
        Gender = gender;
        Area = area;
        this.PW = PW;
    }

    public User() {

    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public Long getPhoneNumber() {
        return PhoneNumber;
    }

    public void setPhoneNumber(Long phoneNumber) {
        PhoneNumber = phoneNumber;
    }

    public Integer getAge() {
        return Age;
    }

    public void setAge(Integer age) {
        Age = age;
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String gender) {
        Gender = gender;
    }

    public String getArea() {
        return Area;
    }

    public void setArea(String area) {
        Area = area;
    }

    public String getPW() {
        return PW;
    }

    public void setPW(String PW) {
        this.PW = PW;
    }
}
