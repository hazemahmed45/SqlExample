package com.example.hazem.sqlexample;

import java.io.Serializable;

/**
 * Created by Hazem on 2/1/2018.
 */

public class Student implements Serializable {
    private String ID,Name;
    private int Age,GraduateYear;

    public Student () {
    }

    public Student (String ID, String name, int age, int graduateYear) {
        this.ID = ID;
        Name = name;
        Age = age;
        GraduateYear = graduateYear;
    }

    public String getID () {
        return ID;
    }

    public void setID (String ID) {
        this.ID = ID;
    }

    public String getName () {
        return Name;
    }

    public void setName (String name) {
        Name = name;
    }

    public int getAge () {
        return Age;
    }

    public void setAge (int age) {
        Age = age;
    }

    public int getGraduateYear () {
        return GraduateYear;
    }

    public void setGraduateYear (int graduateYear) {
        GraduateYear = graduateYear;
    }
}
