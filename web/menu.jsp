<%-- 
    Document   : menu
    Created on : Apr 13, 2015, 8:37:17 PM
    Author     : bernardot
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="header.html" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Menu Jeopardy</title>
    </head>
    <body>
        <h3>¡Escoge con lo que te quieras divertir!</h3>
        <a href="control?do=prepareG">Crear un perfil de Jeopardy</a><br>
        <a href="control?do=create">Crear temas, categorías y pistas</a><br>
        <a href="control?do=showT">Mostrar todos los temas</a><br>
        <a href="control?do=showC">Mostrar todas las categorías</a><br>
        <a href="control?do=showP">Mostrar todas las pistas</a><br>
        <a href="control?do=showProf">Mostrar todos tus perfiles de juego</a><br>
    </body>
</html>
<%@include file="footer.html" %>
