package com.alexis.students.entity;

import java.io.Serializable;

import javax.persistence.*;

@Entity(name="student")
public class StudentEntity implements Serializable {
   
    private static final long serialVersionUID = 5923385817945218499L;

    @Id
    @GeneratedValue
    private long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String lastName;

    @Column(length = 255, nullable = false)
    private String sid;

    @Column(nullable = false, columnDefinition = "TINYINT", length = 1)
    private boolean present;

    @Column
    private int age;

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
