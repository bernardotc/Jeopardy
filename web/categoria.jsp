<%-- 
    Document   : categoria
    Created on : Apr 13, 2015, 8:32:32 PM
    Author     : bernardot
--%>

<%@page import="beans.Categoria"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="header.html" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="Script/modificarTemaCategoriaPista.js"></script>
        <title>Categorías de Jeopardy</title>
    </head>
    <body>
        <h3>Listado de categorías de Jeopardy</h3>
        <center>
        <p style="width: 95%;">A continuación se presenta todas las categorías que contiene el juego de Jeopardy. Se puede <span style="color:#5588FF;"><b>modificar</b></span>
            los campos directamente haciendo <span style="color:#5588FF;"><b>doble click</b></span> sobre el valor que se desea modificar. Al presionar Enter
        o seleccionar otra cosa, el valor ingresado se guardará en el sistema.</p>
        <table style="width: 95%;">
            <thead>
            <td>Nombre</td>
            <td>Descripción</td>
            <td>Tema</td>
            <td>Borrar</td>
            </thead>
            <tbody>
                <%
                    ArrayList categorias = (ArrayList) request.getAttribute("listaCategorias");
                    for (int x = 0; x < categorias.size(); x++) {
                        Categoria aux = (Categoria) categorias.get(x);
                        out.print("\t\t\t\t<tr>\n");
                        out.print("\t\t\t\t\t<td id=\"Categoria-nombre-" + aux.getId() + "\" class=\"special\" ondblclick=\"modificar(this)\">" + aux.getNombre() + "</a></td>\n");
                        out.print("\t\t\t\t\t<td id=\"Categoria-descripcion-" + aux.getId() + "\" class=\"special\" ondblclick=\"modificar(this)\">" + aux.getDescripcion() + "</a></td>\n");
                        out.print("\t\t\t\t\t<td id=\"Categoria-temaid-" + aux.getId() + "\" class=\"special\" ondblclick=\"modificarTema(this)\">" + aux.getTema().getTema() + "</a></td>\n");
                        out.print("\t\t\t\t\t<td class=\"button\"><input id=\"Categoria-" + aux.getId() + "\" class=\"specialButton\" type =\"button\" value=\"Borrar fila\"</td>\n");
                        out.print("\t\t\t\t</tr>\n");
                    }
                %>
            </tbody>
        </table>
    </center>
    </body>
</html>
<%@include file="footer.html" %>
