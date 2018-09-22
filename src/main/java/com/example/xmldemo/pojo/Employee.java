package com.example.xmldemo.pojo;

import java.util.List;

/**
 * @Author: bo zhang
 * @Description:
 * @Date:Create：in 2018-09-22 18:48
 * @Modified By：暂无
 */
public class Employee {
    private String name;
    private String gender;
    private int age;
    private String role;
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getGender() {
        return gender;
    }
    public void setGender(String gender) {
        this.gender = gender;
    }
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public String getRole() {
        return role;
    }
    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "Employee:: Name=" + this.name + " Age=" + this.age + " Gender=" + this.gender +
                " Role=" + this.role;
    }

}