<%-- 
    Document   : pista
    Created on : Apr 13, 2015, 8:22:31 PM
    Author     : bernardot
--%>

<%@page import="beans.Tema"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="header.html" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="Script/modificarTemaCategoriaPista.js"></script>
        <script>
            window.onload = function cargarPistas() {
                request = getRequestObject();
                request.onreadystatechange = handleResponsePonerP;
                request.open("POST", "control?do=getP", true);
                x = document.getElementById("temas").selectedIndex;
                temaid = document.getElementsByTagName("option")[x].value;
                request.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
                request.send("temaid=" + temaid);
            }
        </script>
        <style>
            .select-style {
                border: 1px solid aquamarine;
                width: 380px;
                padding: .5em .5em .5em .5em;
                border-radius: 3px;
                overflow: hidden;
                background: #88CCCC url("http://www.javascriptsource.com/css/arrowd.gif") no-repeat 98% 50%;
            }

            .select-style select {
                color: #5566FF;
                font-size: 16px;
                padding: 5px 8px;
                width: 108%;
                border: none;
                box-shadow: none;
                background: transparent;
                background-image: none;
                -webkit-appearance: none;
            }

            .select-style select:focus {
                outline: none;
            }
        </style>
        <title>Pistas de Jeopardy</title>
    </head>
    <body>
        <h3>Listado de pistas de Jeopardy</h3>
    <center>
        <p style="width: 95%;">A continuación se presenta todas las pistas que contiene el juego de Jeopardy. Se puede <span style="color:#5588FF;"><b>modificar</b></span>
            los campos directamente haciendo <span style="color:#5588FF;"><b>doble click</b></span> sobre el valor que se desea modificar. Al presionar Enter
            o seleccionar otra cosa, el valor ingresado se guardará en el sistema.</p>
        <div class="select-style">
            <select id="temas" onchange="getPistas()">
                <%
                    ArrayList temas = (ArrayList) request.getAttribute("listaTemas");
                    for (int x = 0; x < temas.size(); x++) {
                        Tema aux = (Tema) temas.get(x);
                        out.print("<option value=\"" + aux.getId() + "\">" + aux.getTema() + "</option>");
                    }
                %>
            </select>
        </div><br>
        <table style="width: 95%;" id="pistas">
            <thead>
            <td>Redacción</td>
            <td>Respuesta</td>
            <td>Puntos</td>
            <td>Categoría</td>
            <td>Borrar</td>
            </thead>
            <tbody id="contenido">

            </tbody>
        </table>
    </center>
</body>
</html>
<%@include file="footer.html" %>
