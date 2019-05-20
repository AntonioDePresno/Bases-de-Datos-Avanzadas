<%-- 
    Document   : index
    Created on : 16/05/2019, 01:13:21 PM
    Author     : Antonio
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>HOME</title>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    </head>
    <body>
       <div class="container">
           <div class="jumbotron" style="background-color: black">
            <h1 style="text-align: center; color: white">University</h1>
           </div>
            <a href="alumnos" style="text-decoration: none; color: white; position: absolute; top: 50%;"><button type="button" class="btn btn-primary">ALUMNOS</button></a>
            <a href="maestros" style="text-decoration: none; color:white; position: absolute; top: 50%; left: 45%;"><button type="button" class="btn btn-primary">MAESTROS</button></a>
            <a href="cursos" style="text-decoration: none; color: white; position: absolute; top: 50%; right: 15%;"><button type="button" class="btn btn-primary">CURSOS</button></a>
      </div>
    </body>
</html>
