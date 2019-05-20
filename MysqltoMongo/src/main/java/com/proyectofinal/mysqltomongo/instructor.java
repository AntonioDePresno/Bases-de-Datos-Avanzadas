/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectofinal.mysqltomongo;

import java.util.List;

/**
 *
 * @author JuanAntonio
 */
public class instructor {
    private String id;
    private String name;
    private String department;
    private float salary;
    private List teaches;

    public instructor(String id, String name, String department, float salary, List teaches) {
        this.id = id;
        this.name = name;
        this.department = department;
        this.salary = salary;
        this.teaches = teaches;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public float getSalary() {
        return salary;
    }

    public void setSalary(float salary) {
        this.salary = salary;
    }

    public List getTeaches() {
        return teaches;
    }
    
    public void setTeaches(List teaches){
        this.teaches=teaches;
    }
}
