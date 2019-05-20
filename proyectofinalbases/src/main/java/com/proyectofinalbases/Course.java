/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectofinalbases;

/**
 *
 * @author Antonio
 */
public class Course {
    private String id;
    private String title;
    private String department;
    private int credits;
    
    public Course(){
        
    }
    
    public Course(String id, String title, String department, int credits) {
        this.id = id;
        this.title = title;
        this.department = department;
        this.credits = credits;
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
}
