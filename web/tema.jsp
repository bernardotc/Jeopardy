<%-- 
    Document   : tema
    Created on : Apr 13, 2015, 8:32:21 PM
    Author     : bernardot
--%>

<%@page import="beans.Tema"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="header.jsp" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="Script/modificarTemaCategoriaPista.js"></script>
        <title>Temas de Jeopardy</title>
    </head>
    <body>
        <h3>Listado de temas de Jeopardy</h3>
    <center>
        <p>A continuación se presenta todos los temas que contiene el juego de Jeopardy. Se puede <span style="color:#5588FF;"><b>modificar</b></span>
            los campos directamente haciendo <span style="color:#5588FF;"><b>doble click</b></span> sobre el valor que se desea modificar. Al presionar Enter
        o seleccionar otra cosa, el valor ingresado se guardará en el sistema.</p>
        <table>
            <thead>
            <td>Tema</td>
            <td>Borrar</td>
            </thead>
            <tbody>
                <%
                    ArrayList temas = (ArrayList) request.getAttribute("listaTemas");
                    for (int x = 0; x < temas.size(); x++) {
                        Tema aux = (Tema) temas.get(x);
                        out.print("\t\t\t\t<tr>\n");
                        out.print("\t\t\t\t\t<td id=\"Tema-tema-" + aux.getId() + "\" class=\"special\" ondblclick=\"modificar(this)\">" + aux.getTema() + "</a></td>\n");
                        out.print("\t\t\t\t\t<td class=\"button\"><input id=\"Tema-" + aux.getId() + "\" class=\"specialButton\" type =\"button\" value=\"Borrar fila\" onclick=\"borrar(this)\"></td>\n");
                        out.print("\t\t\t\t</tr>\n");
                    }
                %>
            </tbody>
        </table>
    </center>
    </body>
</html>
<%@include file="footer.html" %>