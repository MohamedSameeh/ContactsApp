package com.example.contactsapp;

public class Contacts {
    String name,number;
    int id;

    public Contacts(int id,String name, String number ) {
        this.name = name;
        this.number = number;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Contacts(String name, String number) {
        this.name = name;
        this.number = number;
    }
}
