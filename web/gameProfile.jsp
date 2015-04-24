<%-- 
    Document   : gameProfile
    Created on : Apr 22, 2015, 1:37:29 PM
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
        <link rel="stylesheet" type="text/css" href="Style/formStyle.css">
        <script src="Script/gameProfile.js"></script>
        <title>Prepare the Jeopardy game</title>
        <style>
            td {
                border-bottom: 1px solid #99BBBB;
            }
        </style>
    <header>
        ${user.getNombre()}
    </header>
    <body>
        <h3>¡Casi listo para jugar!</h3>
    <center>
        <p style="width: 95%">Selecciona los 6 temas que usarás en el juego. Luego la categoría y marca las 5 pistas a usar de la lista.</p>
        <form name="perfil" style="width: 100%;" action="control?do=saveProf" onsubmit="return (checarPistasCompleto());" method="post">
            Nombre del perfil: <br><input type="text" name="nombrePerfil" required placeholder="Puntos extras por ser un bonito proyecto" maxlength="60">
            <fieldset style="border-color: #4499FF">
                <legend><h2>Escoge el tema, la categoría y las pistas de la sección 1</h2></legend>
                <div>
                    <div>
                        <div style="float: left; width: 410px;">Tema:</div>
                        <div style="float: right; width: 410px;">Categoría:</div>
                    </div>
                    <br>
                    <div class="select-style" style="float: left; margin-left: 22px;">
                        <select class="temas" onchange="getCategorias(this)">
                            <% ArrayList temas = new ArrayList();
                                temas = (ArrayList) request.getAttribute("listaTemas");
                                out.print("<option value=\"-1\">Selecciona un tema</option>");
                                for (int x = 0; x < temas.size(); x++) {
                                    Tema aux = (Tema) temas.get(x);
                                    out.print("<option value=\"" + aux.getId() + "\">" + aux.getTema() + "</option>");
                                }%>
                        </select>
                    </div>
                    <div class="select-style" style="float: right; margin-right: 21px;">
                        <select id="1" class="categorias" onchange="getPistas(this)">

                        </select>
                    </div>
                    <table style="width: 95%;" id="pistas">
                        <thead>
                        <td>Redacción</td>
                        <td>Respuesta</td>
                        <td>Puntos</td>
                        <td>Categoría</td>
                        <td>Agregar</td>
                        </thead>
                        <tbody id="contenido">

                        </tbody>
                    </table>
                </div><br>
            </fieldset>
            <br>
            <fieldset style="border-color: #4499FF">
                <legend><h2>Escoge el tema, la categoría y las pistas de la sección 2</h2></legend>
                <div>
                    <div>
                        <div style="float: left; width: 410px;">Tema:</div>
                        <div style="float: right; width: 410px;">Categoría:</div>
                    </div>
                    <br>
                    <div class="select-style" style="float: left; margin-left: 22px;">
                        <select class="temas" onchange="getCategorias(this)">
                            <%
                                out.print("<option value=\"-1\">Selecciona un tema</option>");
                                for (int x = 0; x < temas.size(); x++) {
                                    Tema aux = (Tema) temas.get(x);
                                    out.print("<option value=\"" + aux.getId() + "\">" + aux.getTema() + "</option>");
                                }%>
                        </select>
                    </div>
                    <div class="select-style" style="float: right; margin-right: 21px;">
                        <select id="2" class="categorias" onchange="getPistas(this)">

                        </select>
                    </div>
                    <table style="width: 95%;" id="pistas">
                        <thead>
                        <td>Redacción</td>
                        <td>Respuesta</td>
                        <td>Puntos</td>
                        <td>Categoría</td>
                        <td>Agregar</td>
                        </thead>
                        <tbody id="contenido">

                        </tbody>
                    </table>
                </div><br>
            </fieldset>
            <br>
            <fieldset style="border-color: #4499FF">
                <legend><h2>Escoge el tema, la categoría y las pistas de la sección 3</h2></legend>
                <div>
                    <div>
                        <div style="float: left; width: 410px;">Tema:</div>
                        <div style="float: right; width: 410px;">Categoría:</div>
                    </div>
                    <br>
                    <div class="select-style" style="float: left; margin-left: 22px;">
                        <select class="temas" onchange="getCategorias(this)">
                            <%
                                out.print("<option value=\"-1\">Selecciona un tema</option>");
                                for (int x = 0; x < temas.size(); x++) {
                                    Tema aux = (Tema) temas.get(x);
                                    out.print("<option value=\"" + aux.getId() + "\">" + aux.getTema() + "</option>");
                                }%>
                        </select>
                    </div>
                    <div class="select-style" style="float: right; margin-right: 21px;">
                        <select id="3" class="categorias" onchange="getPistas(this)">

                        </select>
                    </div>
                    <table style="width: 95%;" id="pistas">
                        <thead>
                        <td>Redacción</td>
                        <td>Respuesta</td>
                        <td>Puntos</td>
                        <td>Categoría</td>
                        <td>Agregar</td>
                        </thead>
                        <tbody id="contenido">

                        </tbody>
                    </table>
                </div><br>
            </fieldset>
            <br>
            <fieldset style="border-color: #4499FF">
                <legend><h2>Escoge el tema, la categoría y las pistas de la sección 4</h2></legend>
                <div>
                    <div>
                        <div style="float: left; width: 410px;">Tema:</div>
                        <div style="float: right; width: 410px;">Categoría:</div>
                    </div>
                    <br>
                    <div class="select-style" style="float: left; margin-left: 22px;">
                        <select class="temas" onchange="getCategorias(this)">
                            <%
                                out.print("<option value=\"-1\">Selecciona un tema</option>");
                                for (int x = 0; x < temas.size(); x++) {
                                    Tema aux = (Tema) temas.get(x);
                                    out.print("<option value=\"" + aux.getId() + "\">" + aux.getTema() + "</option>");
                                }%>
                        </select>
                    </div>
                    <div class="select-style" style="float: right; margin-right: 21px;">
                        <select id="4" class="categorias" onchange="getPistas(this)">

                        </select>
                    </div>
                    <table style="width: 95%;" id="pistas">
                        <thead>
                        <td>Redacción</td>
                        <td>Respuesta</td>
                        <td>Puntos</td>
                        <td>Categoría</td>
                        <td>Agregar</td>
                        </thead>
                        <tbody id="contenido">

                        </tbody>
                    </table>
                </div><br>
            </fieldset>
            <br>
            <fieldset style="border-color: #4499FF">
                <legend><h2>Escoge el tema, la categoría y las pistas de la sección 5</h2></legend>
                <div>
                    <div>
                        <div style="float: left; width: 410px;">Tema:</div>
                        <div style="float: right; width: 410px;">Categoría:</div>
                    </div>
                    <br>
                    <div class="select-style" style="float: left; margin-left: 22px;">
                        <select class="temas" onchange="getCategorias(this)">
                            <%
                                out.print("<option value=\"-1\">Selecciona un tema</option>");
                                for (int x = 0; x < temas.size(); x++) {
                                    Tema aux = (Tema) temas.get(x);
                                    out.print("<option value=\"" + aux.getId() + "\">" + aux.getTema() + "</option>");
                                }%>
                        </select>
                    </div>
                    <div class="select-style" style="float: right; margin-right: 21px;">
                        <select id="5" class="categorias" onchange="getPistas(this)">

                        </select>
                    </div>
                    <table style="width: 95%;" id="pistas">
                        <thead>
                        <td>Redacción</td>
                        <td>Respuesta</td>
                        <td>Puntos</td>
                        <td>Categoría</td>
                        <td>Agregar</td>
                        </thead>
                        <tbody id="contenido">

                        </tbody>
                    </table>
                </div><br>
            </fieldset>
            <br>
            <fieldset style="border-color: #4499FF">
                <legend><h2>Escoge el tema, la categoría y las pistas de la sección 6</h2></legend>
                <div>
                    <div>
                        <div style="float: left; width: 410px;">Tema:</div>
                        <div style="float: right; width: 410px;">Categoría:</div>
                    </div>
                    <br>
                    <div class="select-style" style="float: left; margin-left: 22px;">
                        <select class="temas" onchange="getCategorias(this)">
                            <%
                                out.print("<option value=\"-1\">Selecciona un tema</option>");
                                for (int x = 0; x < temas.size(); x++) {
                                    Tema aux = (Tema) temas.get(x);
                                    out.print("<option value=\"" + aux.getId() + "\">" + aux.getTema() + "</option>");
                                }%>
                        </select>
                    </div>
                    <div class="select-style" style="float: right; margin-right: 21px;">
                        <select id="6" class="categorias" onchange="getPistas(this)">

                        </select>
                    </div>
                    <table style="width: 95%;" id="pistas">
                        <thead>
                        <td>Redacción</td>
                        <td>Respuesta</td>
                        <td>Puntos</td>
                        <td>Categoría</td>
                        <td>Agregar</td>
                        </thead>
                        <tbody id="contenido">

                        </tbody>
                    </table>
                </div><br>
            </fieldset>
            <br>
            <input type="submit" value="Guardar perfil">
        </form>
        <br>
    </center>
</body>
</html>
<%@include file="footer.html" %>
