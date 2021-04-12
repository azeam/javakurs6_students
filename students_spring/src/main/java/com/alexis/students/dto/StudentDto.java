package com.alexis.students.dto;

import java.io.Serializable;

public class StudentDto implements Serializable {
    private static final long serialVersionUID = 8906566600256227911L;
    private long id;
    private String name;
    private String sid;
    private String lastName;
    private int age;
    private boolean present;

    public boolean isPresent() {
        return this.present;
    }

    public void setPresent(boolean present) {
        this.present = present;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSid() {
        return this.sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public int getAge() {
        return this.age;
    }

    public void setAge(int age) {
        this.age = age;
    }

}
