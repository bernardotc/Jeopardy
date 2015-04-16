<%-- 
    Document   : categoria
    Created on : Apr 13, 2015, 8:32:32 PM
    Author     : bernardot
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="header.html" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="Style/formStyle.css">
        <title>Crea tu categoría de Jeopardy</title>
    </head>
    <body>
        <h1>Crea tu nueva categoría de Jeopardy</h1>
        <form>
            Nombre <br><input type="text" name="nombre" required><br>
            Descripción<textarea name="descripcion" autofocus required>Escribe tu descripción aquí sobre la categoría.</textarea>
        </form>
    </body>
</html>
<%@include file="footer.html" %>
