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
public class prereq {
    private String course_id;
    private String prereq_id;

    public prereq(String course_id, String prereq_id) {
        this.course_id = course_id;
        this.prereq_id = prereq_id;
    }

    public String getCourse_id() {
        return course_id;
    }

    public void setCourse_id(String course_id) {
        this.course_id = course_id;
    }

    public String getPrereq_id() {
        return prereq_id;
    }

    public void setPrereq_id(String prereq_id) {
        this.prereq_id = prereq_id;
    }
    
    
}
