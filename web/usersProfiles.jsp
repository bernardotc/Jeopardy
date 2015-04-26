<%-- 
    Document   : usersProfiles
    Created on : Apr 23, 2015, 9:01:42 PM
    Author     : bernardot
--%>

<%@page import="beans.PerfilJuego"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="header.html" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Todos tus perfiles de Juego Jeopardy</title>
    </head>
    <body>
        <h3>Selecciona el que quieres modificar</h3>
        <p>A continuación se te presentan tus perfiles creados hasta el momento. Si quieres modificar alguno, dale click al nombre. Por el contrario, si quieres borrarlo, presiona el botón de borrar.</p>
        <ul>
        <%
            ArrayList perfiles = (ArrayList) request.getAttribute("perfiles");
            for (int x = 0; x < perfiles.size(); x++) {
                PerfilJuego aux = (PerfilJuego) perfiles.get(x);
            out.print("<li><a style=\"margin-right: 30px;\" href=\"control?do=changeProf&id=" + aux.getId() + "\">" + aux.getName() + "</a><input class=\"specialButton\" type=\"button\" value=\"Borrar perfil\"></li><br>\n");
        }
            %>
        </ul>
    </body>
</html>
<%@include file="footer.html" %>