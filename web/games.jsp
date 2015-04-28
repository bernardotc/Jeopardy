<%-- 
    Document   : games
    Created on : Apr 26, 2015, 10:17:00 AM
    Author     : bernardot
--%>

<%@page import="beans.PerfilJuego"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="header.jsp" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Todos tus perfiles de Juego Jeopardy</title>
    </head>
    <body>
        <h3>Selecciona el que quieres jugar</h3>
        <center>
            <p style="width:90%;">A continuación se te presentan tus perfiles creados hasta el momento. Dale click al nombre de alguno para jugarlo.</p>
        <ul>
        <%
            ArrayList perfiles = (ArrayList) request.getAttribute("perfiles");
            for (int x = 0; x < perfiles.size(); x++) {
                PerfilJuego aux = (PerfilJuego) perfiles.get(x);
            out.print("<li><a style=\"margin-right: 30px;\" href=\"control?do=startGame&id=" + aux.getId() + "\">" + aux.getName() + "</a></li><br>\n");
        }
            %>
        </ul>
        <a href="control?do=prepareG">O crea un perfil de Jeopardy</a><br>
        </center>
    </body>
</html>
<%@include file="footer.html" %>
