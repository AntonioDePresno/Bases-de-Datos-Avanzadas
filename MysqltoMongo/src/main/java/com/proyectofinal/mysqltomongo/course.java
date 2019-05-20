/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectofinal.mysqltomongo;

/**
 *
 * @author JuanAntonio
 */
public class course {
    private String id;
    private String title;
    private String department;
    private int credits;
    private String prereq;
    
    
   
    public course(String id, String title, String department,int credits, String prereq) {
        this.id = id;
        this.title = title;
        this.department = department;
        this.credits = credits;
        this.prereq=prereq;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }
    
    public String getPrereq() {
        return prereq;
    }

    public void setPrereq(String prereq) {
        this.prereq = prereq;
    }
    
}
