package com.example.model;

public class Passenger {
    private String name;
    private int age;
    private String contactInfo;

    public Passenger(String name, int age, String contactInfo) {
        this.name = name;
        this.age = age;
        this.contactInfo = contactInfo;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getContactInfo() {
        return contactInfo;
    }
}

