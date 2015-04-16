<%-- 
    Document   : pista
    Created on : Apr 13, 2015, 8:22:31 PM
    Author     : bernardot
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="header.html" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="Style/formStyle.css">
        <title>Crea tu pista de Jeopardy</title>
    </head>
    <body>
        <h1>Crea tu nueva pista de Jeopardy</h1>
        <form>
            Redacción o descripción de la pista <br><textarea name="descripcion" autofocus required>Escribe tu descripción aquí.</textarea><br>
            Valor del premio <input type="number" name="puntos" required><br>
            Respuesta <br><textarea name="respuesta" autofocus required>Escribe tu respuesta aquí.</textarea><br>
        </form>
    </body>
</html>
<%@include file="footer.html" %>
