/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectofinalbases;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import java.util.ArrayList;
import java.util.List;
import javax.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author Antonio
 */
@Controller
public class StudentController {
    @RequestMapping(value="/alumnos", method=RequestMethod.GET)
    public String Alumnos(Model model){
        List<Student> lista;
        
        lista=getAlumnos();
        model.addAttribute("students", lista);
        return "student";
    }
    
    private List<Student> getAlumnos(){
        List<Student> lista = new ArrayList();
        
        
        MongoClient mongoClient = new MongoClient(new MongoClientURI("mongodb://localhost:27017"));
        DB db=mongoClient.getDB("university");
        DBCollection coleccion = db.getCollection("Student");
        
        DBCursor cursor = coleccion.find();
        for(DBObject doc : cursor){
            
            Student estudiante = new Student();
            estudiante.setId(doc.get("_id").toString());
            estudiante.setName(doc.get("Name").toString());
            estudiante.setDepartment(doc.get("Department").toString());
            estudiante.setCredits((int) doc.get("Total credits"));
            String advisor = (String) doc.get("Advisor");
            
            if(advisor == null){
                estudiante.setAdvisor("N/A");
            }else{
                estudiante.setAdvisor(advisor);
            }
 
            lista.add(estudiante);
        }
        
        return lista;
    }
    
}
