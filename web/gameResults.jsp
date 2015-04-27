<%-- 
    Document   : gameResults
    Created on : Apr 27, 2015, 3:49:51 PM
    Author     : bernardot
--%>

<%@page import="beans.JugadorResultado"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="header.html" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Resultados de tu juego de Jeopardy</title>
    </head>
    <body>
        <h3>Resultados</h3>
    <center>
        <table>
            <thead>
            <td>Nombre del jugador</td>
            <td>Puntos</td>
            </thead>
            <tbody>
                <%
                    ArrayList resultados = (ArrayList) request.getAttribute("resultados");
                    for (int x = 0; x < resultados.size(); x++) {
                        JugadorResultado aux = (JugadorResultado) resultados.get(x);
                        out.print("<tr>\n<td>" + aux.getNombre() + "</td>\n<td>" + aux.getPuntos() + "</td>\n</tr>\n");
                    }
                %>
            </tbody>
        </table>
    </center>
</body>
</html>
<%@include file="footer.html" %>
