/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectofinal.mysqltomongo;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author JuanAntonio
 */
public class main {
    public static void main(String args[]){
        
        List <student> estudiante = new ArrayList();
        List <instructor> instructores = new ArrayList();
        List <course> cursos = new ArrayList();
        List <department> departamento = new ArrayList();
        List <classroom> salones = new ArrayList();
        List <prereq> prerequisitos = new ArrayList();
        List <section> seccion = new ArrayList();
        List <takes> materias = new ArrayList();
        List <teaches> imparte = new ArrayList();
        List <time_slot> horarios = new ArrayList();
        List <advisor> tutor = new ArrayList();
        
        try{
            //Parte de mysql
            Class.forName("com.mysql.cj.jdbc.Driver");  
            Connection con=DriverManager.getConnection( "jdbc:mysql://localhost:3306/university?serverTimezone=UTC","root","");
            
            //obtenemos la informacion de los estudiantes
            String query = "Select * from student";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);
            
            while(rs.next()){
                String id = rs.getNString("ID");
                String name = rs.getString("name");
                String dept = rs.getString("dept_name");
                int cred = rs.getInt("tot_cred");
                String advisor="";
                List takes = new ArrayList();
                
                //obtenemos el advisor de cada instructor
                String nquery = "Select name from instructor where ID=(Select i_ID from advisor where s_ID="+id+")";
                Statement nst = con.createStatement();
                ResultSet rsaux = nst.executeQuery(nquery);
                
                if(rsaux.next()){
                    advisor = rsaux.getString("name"); 
                }
                
                //obtenemos el nombre de las materias que toma el estudiante
                String query1 = "Select title from course where course_id in (Select course_id from takes where ID="+id+")";
                Statement st1 = con.createStatement();
                ResultSet rs1 = st1.executeQuery(query1);
                
                while(rs1.next()){
                    takes.add(rs1.getString("title"));
                }
                
                
                estudiante.add(new student(id,name,dept,cred, advisor, takes));
            }
            
            //obtenemos los instructores
            query="Select * from instructor";
            st=con.createStatement();
            rs=st.executeQuery(query);
            
            while(rs.next()){
                String id = rs.getString("ID");
                String name = rs.getString("name");
                String dept = rs.getString("dept_name");
                float salary = rs.getFloat("salary");
                List teaches = new ArrayList();
                
                //obtenemos las materias que imparte el profesor
                String query1="Select title from course where course_id in (Select course_id from teaches where ID="+id+")";
                Statement nst = con.createStatement();
                ResultSet rsaux = nst.executeQuery(query1);
                
                while(rsaux.next()){
                    teaches.add(rsaux.getString("title"));
                }
                
                instructores.add(new instructor(id, name, dept, salary, teaches));
            }
            
            //obtenemos la informacion de loa cursos
            query = "Select * from course";
            st = con.createStatement();
            rs = st.executeQuery(query);
            
            while(rs.next()){
               String id = rs.getString("course_id");
               String title = rs.getString("title");
               String dept = rs.getString("dept_name");
               int cred = rs.getInt("credits");
               String prereq="";
               
               String query1 = "select title from course where course_id=(Select prereq_id from prereq where course_id='"+id+"')";
               Statement nst = con.createStatement();
               ResultSet rsaux = nst.executeQuery(query1);
               
               if(rsaux.next()){
                   prereq=rsaux.getString("title");
               }
               
               cursos.add(new course(id, title, dept, cred, prereq));
            }
            
            //obtenemos la informacion de los departamentos
            query="Select * from department";
            st = con.createStatement();
            rs=st.executeQuery(query);
            
            while(rs.next()){
                String name = rs.getString("dept_name");
                String build = rs.getString("building");
                float budget = rs.getFloat("budget");
                
                departamento.add(new department(name,build,budget));
                
            }
            
            //obtenemos la informacion de los salones
            query="SELECT * from classroom";
            st=con.createStatement();
            rs=st.executeQuery(query);
            
            while(rs.next()){
                String building = rs.getString("building");
                String room = rs.getString("room_number");
                int capacity = rs.getInt("capacity");
                
                salones.add(new classroom(building,room,capacity));
            }
            
            //obtenemos los prerequisitos
            query="SELECT * from prereq";
            st=con.createStatement();
            rs=st.executeQuery(query);
            
            while(rs.next()){
                String curso = rs.getString("course_id");
                String prerequisito = rs.getString("prereq_id");
                
                prerequisitos.add(new prereq(curso, prerequisito));
            }
            
            //obtenemos las secciones
            query="SELECT * from section";
            st=con.createStatement();
            rs=st.executeQuery(query);
            
            while(rs.next()){
                String curso = rs.getString("course_id");
                String sec = rs.getString("sec_id");
                String semestre = rs.getString("semester");
                int año = rs.getInt("year");
                String edificio = rs.getString("building");
                String salon = rs.getString("room_number");
                String horario = rs.getString("time_slot_id");
                
                seccion.add(new section(curso,sec,semestre,año,edificio,salon,horario));
            }
            
            //obtenemos las materias que toma o ha tomado el alumno
            query="select * from takes";
            st=con.createStatement();
            rs=st.executeQuery(query);
            
            while(rs.next()){
                String id=rs.getString("ID");
                String curso=rs.getString("course_id");
                String sec=rs.getString("sec_id");
                String semestre=rs.getString("semester");
                int año=rs.getInt("year");
                String cal=rs.getString("grade");
                
                materias.add(new takes(id,curso,sec,semestre,año,cal));
            }
            
            //obtenemos las materias que imparte cada maestro
            query="Select * from teaches";
            st=con.createStatement();
            rs=st.executeQuery(query);
            
            while(rs.next()){
                String id=rs.getString("ID");
                String curso = rs.getString("course_id");
                String sec=rs.getString("sec_id");
                String semestre=rs.getString("semester");
                int año=rs.getInt("year");
                
                imparte.add(new teaches(id,curso,sec,semestre,año));
                
            }
            
            //obtenemos los horarios
            query="SELECT * from time_slot";
            st=con.createStatement();
            rs=st.executeQuery(query);
            
            while(rs.next()){
                String id=rs.getString("time_slot_id");
                String dia=rs.getString("day");
                int hinicio=rs.getInt("start_hr");
                int minicio=rs.getInt("start_min");
                int hfin=rs.getInt("end_hr");
                int mfin=rs.getInt("end_min");
                
                horarios.add(new time_slot(id,dia,hinicio,minicio,hfin,mfin));
            }
            
            //obtenemos los advisors
            query="Select * from advisor";
            st=con.createStatement();
            rs=st.executeQuery(query);
            
            while(rs.next()){
                String alumno = rs.getString("s_ID");
                String maestro = rs.getString("i_ID");
                
                tutor.add(new advisor(alumno,maestro));
            }
            
        }catch(ClassNotFoundException | SQLException e){
            System.out.println("Error: " + e.toString());
        }
        
        //Parte de MongoDB
            MongoClient mongoClient = new MongoClient("localhost");
            mongoClient.dropDatabase("university");
            DB university = mongoClient.getDB("university");
            
            //insertamos en la nueva coleccion a los estudiantes
            DBCollection coleccion = university.getCollection("Student");
            int i=0;
            while(i<estudiante.size()){
                
                DBObject document = new BasicDBObject();
                document.put("_id", estudiante.get(i).getId());
                document.put("Name", estudiante.get(i).getName());
                document.put("Department", estudiante.get(i).getDepartment());
                document.put("Total credits",estudiante.get(i).getCredit());
                
                if(estudiante.get(i).getAdvisor().equals("")){
                    
                }else{
                    document.put("Advisor",estudiante.get(i).getAdvisor());
                }
                
                if(!estudiante.get(i).getTakes().isEmpty()){
                    document.put("Takes",estudiante.get(i).getTakes());
                }
                
                coleccion.insert(document);
                
                i++;
            }
            
            //insertamos en la nueva coleccion a los instructores
            coleccion = university.getCollection("Instructor");
            i=0;
            while(i<instructores.size()){
                DBObject document = new BasicDBObject();
                document.put("_id",instructores.get(i).getId());
                document.put("Name",instructores.get(i).getName());
                document.put("Department",instructores.get(i).getDepartment());
                document.put("Salary",instructores.get(i).getSalary());
                
                
                if(!instructores.get(i).getTeaches().isEmpty()){
                    document.put("Teaches",instructores.get(i).getTeaches());
                }
                coleccion.insert(document);
                
                i++;
            }
            
            //insertamos en la nueva coleccion los cursos
            coleccion = university.getCollection("Courses");
            i=0;
            while(i<cursos.size()){
                DBObject document = new BasicDBObject();
                document.put("_id", cursos.get(i).getId());
                document.put("Title", cursos.get(i).getTitle());
                document.put("Department",cursos.get(i).getDepartment());
                document.put("Credits",cursos.get(i).getCredits());
                
                if(cursos.get(i).getPrereq().equals("")){
                    
                }else{
                    document.put("Prereq",cursos.get(i).getPrereq());
                }
                
                coleccion.insert(document);
                
                i++;
            }
            
            //insertamos los departamentos
            coleccion=university.getCollection("Department");
            i=0;
            while(i<departamento.size()){
                DBObject document = new BasicDBObject();
                document.put("Name",departamento.get(i).getName());
                document.put("Building",departamento.get(i).getBuilding());
                document.put("Budget",departamento.get(i).getBudget());
                
                coleccion.insert(document);
                
                i++;
            }
            
            //insertamos los salones
            coleccion=university.getCollection("Classroom");
            i=0;
            while(i<salones.size()){
                DBObject document = new BasicDBObject();
                document.put("Building", salones.get(i).getBuilding());
                document.put("Room",salones.get(i).getRoom_number());
                document.put("Capacity",salones.get(i).getCapacity());
                
                coleccion.insert(document);
                
                i++;
            }
            
            //insertamos la coleccion prerequisitos
            coleccion=university.getCollection("Prereq");
            i=0;
            while(i<prerequisitos.size()){
                DBObject document = new BasicDBObject();
                document.put("Course",prerequisitos.get(i).getCourse_id());
                document.put("Prereq",prerequisitos.get(i).getPrereq_id());
                
                coleccion.insert(document);
                
                i++;
            }
            
            //insertamos la coleccion section
            coleccion=university.getCollection("Section");
            i=0;
            while(i<seccion.size()){
                DBObject document = new BasicDBObject();
                document.put("Section id",seccion.get(i).getSec_id());
                document.put("Course",seccion.get(i).getCourse_id());
                document.put("Semester",seccion.get(i).getSemester());
                document.put("Year",seccion.get(i).getYear());
                document.put("Building",seccion.get(i).getBuilding());
                document.put("Room Number",seccion.get(i).getRoom_number());
                document.put("Time Slot",seccion.get(i).getTime_slot_id());
                
                coleccion.insert(document);
                
                i++;
            }
            
            //insertamos la coleccion takes
            coleccion=university.getCollection("Takes");
            i=0;
            while(i<materias.size()){
                DBObject document = new BasicDBObject();
                document.put("Student id", materias.get(i).getId());
                document.put("Course id", materias.get(i).getCourse_id());
                document.put("Section id",materias.get(i).getSec_id());
                document.put("Semester",materias.get(i).getSemester());
                document.put("Year",materias.get(i).getYear());
                document.put("Grade",materias.get(i).getGrade());
                
                coleccion.insert(document);
                
                i++;
            }
            
            //insertamos la coleccion teaches
            coleccion=university.getCollection("Takes");
            i=0;
            while(i<imparte.size()){
                DBObject document = new BasicDBObject();
                document.put("Teacher id",imparte.get(i).getId());
                document.put("Course id", imparte.get(i).getCourse_id());
                document.put("Section id",imparte.get(i).getSec_id());
                document.put("Semester",imparte.get(i).getSemester());
                document.put("Year",imparte.get(i).getYear());
                
                coleccion.insert(document);
                
                i++;
            }
            
            //insertamos la coleccion time slot
            coleccion=university.getCollection("TimeSlot");
            i=0;
            while(i<horarios.size()){
                DBObject document = new BasicDBObject();
                document.put("id",horarios.get(i).getTime_slot_id());
                document.put("Day",horarios.get(i).getDay());
                document.put("Start hr",horarios.get(i).getStart_hr());
                document.put("Start min",horarios.get(i).getStart_min());
                document.put("End hr",horarios.get(i).getEnd_hr());
                document.put("End min",horarios.get(i).getEnd_min());
                
                coleccion.insert(document);
                
                i++;
            }
            
            //insertamos la coleccion advisor
            coleccion=university.getCollection("Advisor");
            i=0;
            while(i<tutor.size()){
                DBObject document = new BasicDBObject();
                document.put("Student id",tutor.get(i).getS_ID());
                document.put("Teacher id",tutor.get(i).getS_ID());
                
                coleccion.insert(document);
                
                i++;
            }
       
    }
}
