<%-- 
    Document   : crearTemaCategoriaPista
    Created on : Apr 13, 2015, 8:40:28 PM
    Author     : bernardot
--%>

<%@page import="beans.Categoria"%>
<%@page import="beans.Tema"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="header.html" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="Style/formStyle.css">
        <script src="Script/crearTemaCategoriaPista.js"></script>
        <title>Crea la diversión Jeopardy</title>
        <style>
            form {
                width: 415px;
            }
            #pista {
                float: right;
            }
            #tema {
                margin-left: 5px;
                float: left;
                border-right: aqua 1px solid;
            }

            #titulo {
                color: darkmagenta;
                text-align: center;
            }

            h3 {
                font-family: 'Open Sans', sans-serif;
                text-align: center;
                color: #77AAAA;
            }
        </style>
    </head>
    <body>
        <h2 id="titulo">Crea tu tema, categoría o pista</h2>
        <fieldset style="border: none;">
            <div id="tema">
                <form style="margin-bottom: 10px;">
                    <h3>Crea tu nuevo tema</h3>
                    Nombre <br><input id="temaN" type="text" name="tema" required>
                    <div>
                        <p id="respuestaTema"></p>
                    </div>
                    <center><input type="button" value="Crear tema" onclick="crearTema()"></center>
                </form>
                <form style="border-top: aqua 1px solid;">
                    <h3>Crea tu nueva categoría</h3>
                    Nombre <br><input id="categoriaN" type="text" name="nombre" required><br>
                    Descripción<br><textarea id="descripcionC" name="descripcionC" rows="3" required placeholder="Escribe tu descripción aquí sobre la categoría."></textarea>
                    Tema <br>
                    <div class="select-style">
                        <select id="temas" required>
                            <% ArrayList temas = new ArrayList();
                                temas = (ArrayList) request.getAttribute("listaTemas");
                                for (int x = 0; x < temas.size(); x++) {
                                    Tema aux = (Tema) temas.get(x);
                                    out.print("<option value=\"" + aux.getId() + "\">" + aux.getTema() + "</option>");
                                }%>
                        </select>
                    </div><br>
                    <div>
                        <p id="respuestaCategoria"></p>
                    </div>
                    <center><input type="button" value="Crear categoría" onclick="crearCategoria()"></center>
                </form>
            </div>
            <div id="pista">
                <form>
                    <h3>Crea tu nueva pista</h3>
                    Redacción o descripción de la pista <br><textarea id="redaccion" name="descripcion" rows="3" required placeholder="Escribe tu descripción aquí."></textarea>
                    Respuesta <br><textarea id="respuesta" name="respuesta" rows="3" required placeholder="Escribe tu respuesta aquí."></textarea><br>
                    Valor del premio <br><input id="puntos" type="number" name="puntos" required><br>
                    Categoria <br>
                    <div class="select-style">
                        <select id="categorias" required>
                            <% ArrayList categorias = new ArrayList();
                                categorias = (ArrayList) request.getAttribute("listaCategorias");
                                for (int x = 0; x < categorias.size(); x++) {
                                    Categoria aux = (Categoria) categorias.get(x);
                                    out.print("<option value=\"" + aux.getId() + "\">" + aux.getNombre() + "</option>");
                                }%>
                        </select>
                    </div><br>
                    <div>
                        <p id="respuestaPista"></p>
                    </div>
                    <center><input type="button" value="Crear pista" onclick="crearPista()"></center>
                </form>
            </div>
        </fieldset>
    </body>
</html>
<%@include file="footer.html" %>
