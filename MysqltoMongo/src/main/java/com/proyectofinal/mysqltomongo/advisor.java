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
public class advisor {
    private String s_ID;
    private String i_ID;

    public advisor(String s_ID, String i_ID) {
        this.s_ID = s_ID;
        this.i_ID = i_ID;
    }

    public String getS_ID() {
        return s_ID;
    }

    public void setS_ID(String s_ID) {
        this.s_ID = s_ID;
    }

    public String getI_ID() {
        return i_ID;
    }

    public void setI_ID(String i_ID) {
        this.i_ID = i_ID;
    }
    
}
