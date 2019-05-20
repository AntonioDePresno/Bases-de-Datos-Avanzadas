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
public class student {
    private String id;
    private String name;
    private String department;
    private int credit;
    private String advisor;
    private List takes;

    
    public student(String id, String name, String department, int credit, String advisor, List takes) {
        this.id = id;
        this.name = name;
        this.department = department;
        this.credit = credit;
        this.advisor=advisor;
        this.takes=takes;
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

    public int getCredit() {
        return credit;
    }

    public void setCredit(int credit) {
        this.credit = credit;
    }
    public String getAdvisor() {
        return advisor;
    }

    public void setAdvisor(String advisor) {
        this.advisor = advisor;
    }
    
    public List getTakes(){
        return takes;
    }
    
    public void setTakes(List takes){
        this.takes=takes;
    }
}
