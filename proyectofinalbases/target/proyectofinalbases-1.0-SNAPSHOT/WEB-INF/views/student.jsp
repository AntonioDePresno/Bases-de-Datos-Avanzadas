<%-- 
    Document   : student
    Created on : 16/05/2019, 03:56:33 PM
    Author     : Antonio
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Students</title>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    </head>
    <body>
       <div class="container">
           <div class="jumbotron" style="background-color: black">
            <h1 style="text-align: center; color: white">University</h1>
            <br>
            <p style="text-align: center; color: white">Lista de Estudiantes</p>
            <br>
            <p style=" text-align: center"><a href="" style="color: white">HOME</a></p>
           </div>
           <table class="table">
               <thead class="thead-dark">
                <tr>
                    <th>ID</th>
                    <th>Nombre</th>
                    <th>Departamento</th>
                    <th>Creditos</th>
                    <th>Tutor</th>
                </tr>
               </thead>
                <tbody>
                   <c:forEach items="${students}" var="Student"> 
			<tr>
                            <td>${Student.id}</td>
                            <td>${Student.name}</td>
                            <td>${Student.department}</td>
                            <td>${Student.credits}</td>
                            <td>${Student.advisor}</td>
			</tr>
                    </c:forEach>
               </tbody>
            </table>
      </div>
    </body>
</html>
