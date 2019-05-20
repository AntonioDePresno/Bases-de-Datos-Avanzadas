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
public class TeacherController {
    @RequestMapping(value="/maestros",method=RequestMethod.GET)
    public String Maestros(Model model){
        List<Teacher> lista;
        
        lista=getMaestros();
        model.addAttribute("maestros", lista);
	return "instructor";
    }
    
    private List<Teacher> getMaestros(){
        List<Teacher> lista = new ArrayList();
        
        MongoClient mongoClient = new MongoClient(new MongoClientURI("mongodb://localhost:27017"));
        DB db=mongoClient.getDB("university");
        DBCollection coleccion = db.getCollection("Instructor");
        
        DBCursor cursor = coleccion.find();
        for(DBObject doc : cursor){
            Teacher maestro = new Teacher();
            maestro.setId(doc.get("_id").toString()) ;
            maestro.setName(doc.get("Name").toString());
            maestro.setDepartment(doc.get("Department").toString());
            maestro.setSalary((double) doc.get("Salary"));
            
            lista.add(maestro);
        }
        
        return lista;
    }
}
