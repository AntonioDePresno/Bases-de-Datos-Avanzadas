/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectofinalbases;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author Antonio
 */
@Controller
public class CourseController {
    @RequestMapping(value="/cursos",method=RequestMethod.GET)
    public String Cursos(Model model){
        List<Course> lista;
        
        lista=getCursos();
        model.addAttribute("cursos", lista);
        return "courses";
    }
    
    private List<Course> getCursos(){
        
        List<Course> lista = new ArrayList();
        
        
        MongoClient mongoClient = new MongoClient(new MongoClientURI("mongodb://localhost:27017"));
        DB db=mongoClient.getDB("university");
        DBCollection coleccion = db.getCollection("Courses");
        
        DBCursor cursor = coleccion.find();
        for(DBObject doc : cursor){
            Course curso  = new Course();
            curso.setId(doc.get("_id").toString());
            curso.setTitle(doc.get("Title").toString());
            curso.setDepartment(doc.get("Department").toString());
            curso.setCredits((int)doc.get("Credits"));
            
            lista.add(curso);
        }
        
        return lista;
    }
}
