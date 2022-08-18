package com.example.calendar;

public class modelclass {
    String Date;
    String name;
    public modelclass(){

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public modelclass(String Date, String name){
        this.Date=Date;
        this.name=name;
    }
}
