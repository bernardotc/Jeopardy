<%-- 
    Document   : modifyGameProfile
    Created on : Apr 23, 2015, 9:00:35 PM
    Author     : bernardot
--%>

<%@page import="beans.Categoria"%>
<%@page import="beans.Pista"%>
<%@page import="beans.PerfilJuego"%>
<%@page import="beans.Tema"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="header.jsp" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="Style/formStyle.css">
        <script src="Script/modifyGameProfile.js"></script>
        <title>Modifica un perfil de juego Jeopardy</title>
    </head>
    <body>
        <h3>Modifica tu perfil</h3>
    <center>
        <p style="width: 95%">Selecciona los 6 temas que usarás en el juego. Luego la categoría y marca las 5 pistas a usar de la lista.</p>
        <form name="perfil" style="width: 100%;" action="control?do=modifyProf" onsubmit="return (checarPistasCompleto());" method="post">
            <input type="number" name="idPerfil" value="${perfil.getId()}" hidden>
            Nombre del perfil: <br><input type="text" name="nombrePerfil" required placeholder="Puntos extras por ser un bonito proyecto" maxlength="60" value="${perfil.getName()}">
            <fieldset style="border-color: #4499FF">
                <legend><h2>Escoge el tema, la categoría y las pistas de la sección 1</h2></legend>
                <div>
                    <div>
                        <div style="float: left; width: 410px;">Tema:</div>
                        <div style="float: right; width: 410px;">Categoría:</div>
                    </div>
                    <br>
                    <div class="select-style" style="float: left; margin-left: 22px;">
                        <select id="1" class="temas" onchange="getCategorias(this)">
                            <% ArrayList temas = new ArrayList();
                                temas = (ArrayList) request.getAttribute("listaTemas");
                                ArrayList categorias = new ArrayList();
                                categorias = (ArrayList) request.getAttribute("listaCategorias");
                                PerfilJuego perfil = (PerfilJuego) request.getAttribute("perfil");
                                out.print("<option value=\"-1\">Selecciona un tema</option>");
                                ArrayList help = perfil.getSeccion1();
                                if (help.size() > 0) {
                                    Pista pis = (Pista) help.get(0);
                                    int ayuda = pis.getCategoria().getTema().getId();
                                    for (int x = 0; x < temas.size(); x++) {
                                        Tema aux = (Tema) temas.get(x);
                                        if (ayuda == aux.getId()) {
                                            out.print("<option value=\"" + aux.getId() + "\" selected>" + aux.getTema() + "</option>");
                                        } else {
                                            out.print("<option value=\"" + aux.getId() + "\">" + aux.getTema() + "</option>");
                                        }
                                    }
                                } else {
                                    for (int x = 0; x < temas.size(); x++) {
                                        Tema aux = (Tema) temas.get(x);
                                        out.print("<option value=\"" + aux.getId() + "\">" + aux.getTema() + "</option>");
                                    }
                                }%>
                        </select>
                    </div>
                    <div class="select-style" style="float: right; margin-right: 21px;">
                        <select id="1" class="categorias" onchange="getPistas(this)">
                            <%
                                if (help.size() > 0) {
                                    Pista pis = (Pista) help.get(0);
                                    int ayuda = pis.getCategoria().getId();
                                    for (int x = 0; x < categorias.size(); x++) {
                                        Categoria aux = (Categoria) categorias.get(x);
                                        if (ayuda == aux.getId()) {
                                            out.print("<option class=\"categoriaOption\" value=\"" + aux.getId() + "\" selected>" + aux.getNombre() + " - " + aux.getTema().getTema() + "</option>");
                                        } else {
                                            out.print("<option class=\"categoriaOption\" value=\"" + aux.getId() + "\">" + aux.getNombre() + " - " + aux.getTema().getTema() + "</option>");
                                        }
                                    }
                                } else {
                                    for (int x = 0; x < categorias.size(); x++) {
                                        Categoria aux = (Categoria) categorias.get(x);
                                        out.print("<option class=\"categoriaOption\" value=\"" + aux.getId() + "\">" + aux.getNombre() + " - " + aux.getTema().getTema() + "</option>");
                                    }
                                }
                            %>
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
                            <%
                                ArrayList pistas = new ArrayList();
                                pistas = (ArrayList) request.getAttribute("pistas1");
                                boolean imprime = true;
                                if (help.size() > 0) {
                                    for (int x = 0; x < pistas.size(); x++) {
                                        imprime = true;
                                        Pista aux = (Pista) pistas.get(x);
                                        for (int y = 0; y < help.size(); y++) {
                                            Pista auxHelp = (Pista) help.get(y);
                                            if (auxHelp.getId() == aux.getId()) {
                                                out.print("\t\t\t\t<tr>\n");
                                                out.print("\t\t\t\t\t<td id=\"Pista-redaccion-" + aux.getId() + "\">" + aux.getRedaccion() + "</a></td>\n");
                                                out.print("\t\t\t\t\t<td id=\"Pista-respuesta-" + aux.getId() + "\">" + aux.getRespuesta() + "</a></td>\n");
                                                out.print("\t\t\t\t\t<td id=\"Pista-puntos-" + aux.getId() + "\">" + aux.getPuntos() + "</a></td>\n");
                                                out.print("\t\t\t\t\t<td id=\"Pista-categoriaid-" + aux.getId() + "\">" + aux.getCategoria().getNombre() + "</a></td>\n");
                                                out.print("\t\t\t\t\t<td><input name=\"seccion-" + 1 + "\" type =\"checkbox\" value=\"" + aux.getId() + "\" multiple onclick=\"cuenta" + 1 + "(this)\" checked></td>\n");
                                                out.print("\t\t\t\t</tr>\n");
                                                imprime = false;
                                            }
                                        }
                                        if (imprime) {
                                            out.print("\t\t\t\t<tr>\n");
                                            out.print("\t\t\t\t\t<td id=\"Pista-redaccion-" + aux.getId() + "\">" + aux.getRedaccion() + "</a></td>\n");
                                            out.print("\t\t\t\t\t<td id=\"Pista-respuesta-" + aux.getId() + "\">" + aux.getRespuesta() + "</a></td>\n");
                                            out.print("\t\t\t\t\t<td id=\"Pista-puntos-" + aux.getId() + "\">" + aux.getPuntos() + "</a></td>\n");
                                            out.print("\t\t\t\t\t<td id=\"Pista-categoriaid-" + aux.getId() + "\">" + aux.getCategoria().getNombre() + "</a></td>\n");
                                            out.print("\t\t\t\t\t<td><input name=\"seccion-" + 1 + "\" type =\"checkbox\" value=\"" + aux.getId() + "\" multiple onclick=\"cuenta" + 1 + "(this)\"></td>\n");
                                            out.print("\t\t\t\t</tr>\n");
                                        }
                                    }
                                }
                            %>
                        </tbody>
                    </table>
                </div><br>
            </fieldset>
            <br>
            <fieldset style="border-color: #4499FF">
                <div>
                    <div>
                        <div style="float: left; width: 410px;">Tema:</div>
                        <div style="float: right; width: 410px;">Categoría:</div>
                    </div>
                    <br>
                    <div class="select-style" style="float: left; margin-left: 22px;">
                        <select id="2" class="temas" onchange="getCategorias(this)">
                            <%
                                out.print("<option value=\"-1\">Selecciona un tema</option>");
                                help = perfil.getSeccion2();
                                if (help.size() > 0) {
                                    Pista pis = (Pista) help.get(0);
                                    int ayuda = pis.getCategoria().getTema().getId();
                                    for (int x = 0; x < temas.size(); x++) {
                                        Tema aux = (Tema) temas.get(x);
                                        if (ayuda == aux.getId()) {
                                            out.print("<option value=\"" + aux.getId() + "\" selected>" + aux.getTema() + "</option>");
                                        } else {
                                            out.print("<option value=\"" + aux.getId() + "\">" + aux.getTema() + "</option>");
                                        }
                                    }
                                } else {
                                    for (int x = 0; x < temas.size(); x++) {
                                        Tema aux = (Tema) temas.get(x);
                                        out.print("<option value=\"" + aux.getId() + "\">" + aux.getTema() + "</option>");
                                    }
                                }%>
                        </select>
                    </div>
                    <div class="select-style" style="float: right; margin-right: 21px;">
                        <select id="2" class="categorias" onchange="getPistas(this)">
                            <%
                                if (help.size() > 0) {
                                    Pista pis = (Pista) help.get(0);
                                    int ayuda = pis.getCategoria().getId();
                                    for (int x = 0; x < categorias.size(); x++) {
                                        Categoria aux = (Categoria) categorias.get(x);
                                        if (ayuda == aux.getId()) {
                                            out.print("<option class=\"categoriaOption\" value=\"" + aux.getId() + "\" selected>" + aux.getNombre() + " - " + aux.getTema().getTema() + "</option>");
                                        } else {
                                            out.print("<option class=\"categoriaOption\" value=\"" + aux.getId() + "\">" + aux.getNombre() + " - " + aux.getTema().getTema() + "</option>");
                                        }
                                    }
                                } else {
                                    for (int x = 0; x < categorias.size(); x++) {
                                        Categoria aux = (Categoria) categorias.get(x);
                                        out.print("<option class=\"categoriaOption\" value=\"" + aux.getId() + "\">" + aux.getNombre() + " - " + aux.getTema().getTema() + "</option>");
                                    }
                                }
                            %>
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
                            <%
                                pistas = (ArrayList) request.getAttribute("pistas2");
                                if (help.size() > 0) {
                                    for (int x = 0; x < pistas.size(); x++) {
                                        imprime = true;
                                        Pista aux = (Pista) pistas.get(x);
                                        for (int y = 0; y < help.size(); y++) {
                                            Pista auxHelp = (Pista) help.get(y);
                                            if (auxHelp.getId() == aux.getId()) {
                                                out.print("\t\t\t\t<tr>\n");
                                                out.print("\t\t\t\t\t<td id=\"Pista-redaccion-" + aux.getId() + "\">" + aux.getRedaccion() + "</a></td>\n");
                                                out.print("\t\t\t\t\t<td id=\"Pista-respuesta-" + aux.getId() + "\">" + aux.getRespuesta() + "</a></td>\n");
                                                out.print("\t\t\t\t\t<td id=\"Pista-puntos-" + aux.getId() + "\">" + aux.getPuntos() + "</a></td>\n");
                                                out.print("\t\t\t\t\t<td id=\"Pista-categoriaid-" + aux.getId() + "\">" + aux.getCategoria().getNombre() + "</a></td>\n");
                                                out.print("\t\t\t\t\t<td><input name=\"seccion-" + 2 + "\" type =\"checkbox\" value=\"" + aux.getId() + "\" multiple onclick=\"cuenta" + 2 + "(this)\" checked></td>\n");
                                                out.print("\t\t\t\t</tr>\n");
                                                imprime = false;
                                            }
                                        }
                                        if (imprime) {
                                            out.print("\t\t\t\t<tr>\n");
                                            out.print("\t\t\t\t\t<td id=\"Pista-redaccion-" + aux.getId() + "\">" + aux.getRedaccion() + "</a></td>\n");
                                            out.print("\t\t\t\t\t<td id=\"Pista-respuesta-" + aux.getId() + "\">" + aux.getRespuesta() + "</a></td>\n");
                                            out.print("\t\t\t\t\t<td id=\"Pista-puntos-" + aux.getId() + "\">" + aux.getPuntos() + "</a></td>\n");
                                            out.print("\t\t\t\t\t<td id=\"Pista-categoriaid-" + aux.getId() + "\">" + aux.getCategoria().getNombre() + "</a></td>\n");
                                            out.print("\t\t\t\t\t<td><input name=\"seccion-" + 2 + "\" type =\"checkbox\" value=\"" + aux.getId() + "\" multiple onclick=\"cuenta" + 2 + "(this)\"></td>\n");
                                            out.print("\t\t\t\t</tr>\n");
                                        }
                                    }
                                }
                            %>
                        </tbody>
                    </table>
                </div><br>
            </fieldset>
            <br>
            <fieldset style="border-color: #4499FF">
                <div>
                    <div>
                        <div style="float: left; width: 410px;">Tema:</div>
                        <div style="float: right; width: 410px;">Categoría:</div>
                    </div>
                    <br>
                    <div class="select-style" style="float: left; margin-left: 22px;">
                        <select id="3" class="temas" onchange="getCategorias(this)">
                            <%
                                out.print("<option value=\"-1\">Selecciona un tema</option>");
                                help = perfil.getSeccion3();
                                if (help.size() > 0) {
                                    Pista pis = (Pista) help.get(0);
                                    int ayuda = pis.getCategoria().getTema().getId();
                                    for (int x = 0; x < temas.size(); x++) {
                                        Tema aux = (Tema) temas.get(x);
                                        if (ayuda == aux.getId()) {
                                            out.print("<option value=\"" + aux.getId() + "\" selected>" + aux.getTema() + "</option>");
                                        } else {
                                            out.print("<option value=\"" + aux.getId() + "\">" + aux.getTema() + "</option>");
                                        }
                                    }
                                } else {
                                    for (int x = 0; x < temas.size(); x++) {
                                        Tema aux = (Tema) temas.get(x);
                                        out.print("<option value=\"" + aux.getId() + "\">" + aux.getTema() + "</option>");
                                    }
                                }%>
                        </select>
                    </div>
                    <div class="select-style" style="float: right; margin-right: 21px;">
                        <select id="3" class="categorias" onchange="getPistas(this)">
                            <%
                                if (help.size() > 0) {
                                    Pista pis = (Pista) help.get(0);
                                    int ayuda = pis.getCategoria().getId();
                                    for (int x = 0; x < categorias.size(); x++) {
                                        Categoria aux = (Categoria) categorias.get(x);
                                        if (ayuda == aux.getId()) {
                                            out.print("<option class=\"categoriaOption\" value=\"" + aux.getId() + "\" selected>" + aux.getNombre() + " - " + aux.getTema().getTema() + "</option>");
                                        } else {
                                            out.print("<option class=\"categoriaOption\" value=\"" + aux.getId() + "\">" + aux.getNombre() + " - " + aux.getTema().getTema() + "</option>");
                                        }
                                    }
                                } else {
                                    for (int x = 0; x < categorias.size(); x++) {
                                        Categoria aux = (Categoria) categorias.get(x);
                                        out.print("<option class=\"categoriaOption\" value=\"" + aux.getId() + "\">" + aux.getNombre() + " - " + aux.getTema().getTema() + "</option>");
                                    }
                                }
                            %>
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
                            <%
                                pistas = (ArrayList) request.getAttribute("pistas3");
                                if (help.size() > 0) {
                                    for (int x = 0; x < pistas.size(); x++) {
                                        imprime = true;
                                        Pista aux = (Pista) pistas.get(x);
                                        for (int y = 0; y < help.size(); y++) {
                                            Pista auxHelp = (Pista) help.get(y);
                                            if (auxHelp.getId() == aux.getId()) {
                                                out.print("\t\t\t\t<tr>\n");
                                                out.print("\t\t\t\t\t<td id=\"Pista-redaccion-" + aux.getId() + "\">" + aux.getRedaccion() + "</a></td>\n");
                                                out.print("\t\t\t\t\t<td id=\"Pista-respuesta-" + aux.getId() + "\">" + aux.getRespuesta() + "</a></td>\n");
                                                out.print("\t\t\t\t\t<td id=\"Pista-puntos-" + aux.getId() + "\">" + aux.getPuntos() + "</a></td>\n");
                                                out.print("\t\t\t\t\t<td id=\"Pista-categoriaid-" + aux.getId() + "\">" + aux.getCategoria().getNombre() + "</a></td>\n");
                                                out.print("\t\t\t\t\t<td><input name=\"seccion-" + 3 + "\" type =\"checkbox\" value=\"" + aux.getId() + "\" multiple onclick=\"cuenta" + 3 + "(this)\" checked></td>\n");
                                                out.print("\t\t\t\t</tr>\n");
                                                imprime = false;
                                            }
                                        }
                                        if (imprime) {
                                            out.print("\t\t\t\t<tr>\n");
                                            out.print("\t\t\t\t\t<td id=\"Pista-redaccion-" + aux.getId() + "\">" + aux.getRedaccion() + "</a></td>\n");
                                            out.print("\t\t\t\t\t<td id=\"Pista-respuesta-" + aux.getId() + "\">" + aux.getRespuesta() + "</a></td>\n");
                                            out.print("\t\t\t\t\t<td id=\"Pista-puntos-" + aux.getId() + "\">" + aux.getPuntos() + "</a></td>\n");
                                            out.print("\t\t\t\t\t<td id=\"Pista-categoriaid-" + aux.getId() + "\">" + aux.getCategoria().getNombre() + "</a></td>\n");
                                            out.print("\t\t\t\t\t<td><input name=\"seccion-" + 3 + "\" type =\"checkbox\" value=\"" + aux.getId() + "\" multiple onclick=\"cuenta" + 3 + "(this)\"></td>\n");
                                            out.print("\t\t\t\t</tr>\n");
                                        }
                                    }
                                }
                            %>
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
                        <select id="4" class="temas" onchange="getCategorias(this)">
                            <%
                                out.print("<option value=\"-1\">Selecciona un tema</option>");
                                help = perfil.getSeccion4();
                                if (help.size() > 0) {
                                    Pista pis = (Pista) help.get(0);
                                    int ayuda = pis.getCategoria().getTema().getId();
                                    for (int x = 0; x < temas.size(); x++) {
                                        Tema aux = (Tema) temas.get(x);
                                        if (ayuda == aux.getId()) {
                                            out.print("<option value=\"" + aux.getId() + "\" selected>" + aux.getTema() + "</option>");
                                        } else {
                                            out.print("<option value=\"" + aux.getId() + "\">" + aux.getTema() + "</option>");
                                        }
                                    }
                                } else {
                                    for (int x = 0; x < temas.size(); x++) {
                                        Tema aux = (Tema) temas.get(x);
                                        out.print("<option value=\"" + aux.getId() + "\">" + aux.getTema() + "</option>");
                                    }
                                }%>
                        </select>
                    </div>
                    <div class="select-style" style="float: right; margin-right: 21px;">
                        <select id="4" class="categorias" onchange="getPistas(this)">
                            <%
                                if (help.size() > 0) {
                                    Pista pis = (Pista) help.get(0);
                                    int ayuda = pis.getCategoria().getId();
                                    for (int x = 0; x < categorias.size(); x++) {
                                        Categoria aux = (Categoria) categorias.get(x);
                                        if (ayuda == aux.getId()) {
                                            out.print("<option class=\"categoriaOption\" value=\"" + aux.getId() + "\" selected>" + aux.getNombre() + " - " + aux.getTema().getTema() + "</option>");
                                        } else {
                                            out.print("<option class=\"categoriaOption\" value=\"" + aux.getId() + "\">" + aux.getNombre() + " - " + aux.getTema().getTema() + "</option>");
                                        }
                                    }
                                } else {
                                    for (int x = 0; x < categorias.size(); x++) {
                                        Categoria aux = (Categoria) categorias.get(x);
                                        out.print("<option class=\"categoriaOption\" value=\"" + aux.getId() + "\">" + aux.getNombre() + " - " + aux.getTema().getTema() + "</option>");
                                    }
                                }
                            %>
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
                            <%
                                pistas = (ArrayList) request.getAttribute("pistas4");
                                if (help.size() > 0) {
                                    for (int x = 0; x < pistas.size(); x++) {
                                        imprime = true;
                                        Pista aux = (Pista) pistas.get(x);
                                        for (int y = 0; y < help.size(); y++) {
                                            Pista auxHelp = (Pista) help.get(y);
                                            if (auxHelp.getId() == aux.getId()) {
                                                out.print("\t\t\t\t<tr>\n");
                                                out.print("\t\t\t\t\t<td id=\"Pista-redaccion-" + aux.getId() + "\">" + aux.getRedaccion() + "</a></td>\n");
                                                out.print("\t\t\t\t\t<td id=\"Pista-respuesta-" + aux.getId() + "\">" + aux.getRespuesta() + "</a></td>\n");
                                                out.print("\t\t\t\t\t<td id=\"Pista-puntos-" + aux.getId() + "\">" + aux.getPuntos() + "</a></td>\n");
                                                out.print("\t\t\t\t\t<td id=\"Pista-categoriaid-" + aux.getId() + "\">" + aux.getCategoria().getNombre() + "</a></td>\n");
                                                out.print("\t\t\t\t\t<td><input name=\"seccion-" + 4 + "\" type =\"checkbox\" value=\"" + aux.getId() + "\" multiple onclick=\"cuenta" + 4 + "(this)\" checked></td>\n");
                                                out.print("\t\t\t\t</tr>\n");
                                                imprime = false;
                                            }
                                        }
                                        if (imprime) {
                                            out.print("\t\t\t\t<tr>\n");
                                            out.print("\t\t\t\t\t<td id=\"Pista-redaccion-" + aux.getId() + "\">" + aux.getRedaccion() + "</a></td>\n");
                                            out.print("\t\t\t\t\t<td id=\"Pista-respuesta-" + aux.getId() + "\">" + aux.getRespuesta() + "</a></td>\n");
                                            out.print("\t\t\t\t\t<td id=\"Pista-puntos-" + aux.getId() + "\">" + aux.getPuntos() + "</a></td>\n");
                                            out.print("\t\t\t\t\t<td id=\"Pista-categoriaid-" + aux.getId() + "\">" + aux.getCategoria().getNombre() + "</a></td>\n");
                                            out.print("\t\t\t\t\t<td><input name=\"seccion-" + 4 + "\" type =\"checkbox\" value=\"" + aux.getId() + "\" multiple onclick=\"cuenta" + 4 + "(this)\"></td>\n");
                                            out.print("\t\t\t\t</tr>\n");
                                        }
                                    }
                                }
                            %>
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
                        <select id="5" class="temas" onchange="getCategorias(this)">
                            <%
                                out.print("<option value=\"-1\">Selecciona un tema</option>");
                                help = perfil.getSeccion5();
                                if (help.size() > 0) {
                                    Pista pis = (Pista) help.get(0);
                                    int ayuda = pis.getCategoria().getTema().getId();
                                    for (int x = 0; x < temas.size(); x++) {
                                        Tema aux = (Tema) temas.get(x);
                                        if (ayuda == aux.getId()) {
                                            out.print("<option value=\"" + aux.getId() + "\" selected>" + aux.getTema() + "</option>");
                                        } else {
                                            out.print("<option value=\"" + aux.getId() + "\">" + aux.getTema() + "</option>");
                                        }
                                    }
                                } else {
                                    for (int x = 0; x < temas.size(); x++) {
                                        Tema aux = (Tema) temas.get(x);
                                        out.print("<option value=\"" + aux.getId() + "\">" + aux.getTema() + "</option>");
                                    }
                                }%>
                        </select>
                    </div>
                    <div class="select-style" style="float: right; margin-right: 21px;">
                        <select id="5" class="categorias" onchange="getPistas(this)">
                            <%
                                if (help.size() > 0) {
                                    Pista pis = (Pista) help.get(0);
                                    int ayuda = pis.getCategoria().getId();
                                    for (int x = 0; x < categorias.size(); x++) {
                                        Categoria aux = (Categoria) categorias.get(x);
                                        if (ayuda == aux.getId()) {
                                            out.print("<option class=\"categoriaOption\" value=\"" + aux.getId() + "\" selected>" + aux.getNombre() + " - " + aux.getTema().getTema() + "</option>");
                                        } else {
                                            out.print("<option class=\"categoriaOption\" value=\"" + aux.getId() + "\">" + aux.getNombre() + " - " + aux.getTema().getTema() + "</option>");
                                        }
                                    }
                                } else {
                                    for (int x = 0; x < categorias.size(); x++) {
                                        Categoria aux = (Categoria) categorias.get(x);
                                        out.print("<option class=\"categoriaOption\" value=\"" + aux.getId() + "\">" + aux.getNombre() + " - " + aux.getTema().getTema() + "</option>");
                                    }
                                }
                            %>
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
                            <%
                                pistas = (ArrayList) request.getAttribute("pistas5");
                                if (help.size() > 0) {
                                    for (int x = 0; x < pistas.size(); x++) {
                                        imprime = true;
                                        Pista aux = (Pista) pistas.get(x);
                                        for (int y = 0; y < help.size(); y++) {
                                            Pista auxHelp = (Pista) help.get(y);
                                            if (auxHelp.getId() == aux.getId()) {
                                                out.print("\t\t\t\t<tr>\n");
                                                out.print("\t\t\t\t\t<td id=\"Pista-redaccion-" + aux.getId() + "\">" + aux.getRedaccion() + "</a></td>\n");
                                                out.print("\t\t\t\t\t<td id=\"Pista-respuesta-" + aux.getId() + "\">" + aux.getRespuesta() + "</a></td>\n");
                                                out.print("\t\t\t\t\t<td id=\"Pista-puntos-" + aux.getId() + "\">" + aux.getPuntos() + "</a></td>\n");
                                                out.print("\t\t\t\t\t<td id=\"Pista-categoriaid-" + aux.getId() + "\">" + aux.getCategoria().getNombre() + "</a></td>\n");
                                                out.print("\t\t\t\t\t<td><input name=\"seccion-" + 5 + "\" type =\"checkbox\" value=\"" + aux.getId() + "\" multiple onclick=\"cuenta" + 5 + "(this)\" checked></td>\n");
                                                out.print("\t\t\t\t</tr>\n");
                                                imprime = false;
                                            }
                                        }
                                        if (imprime) {
                                            out.print("\t\t\t\t<tr>\n");
                                            out.print("\t\t\t\t\t<td id=\"Pista-redaccion-" + aux.getId() + "\">" + aux.getRedaccion() + "</a></td>\n");
                                            out.print("\t\t\t\t\t<td id=\"Pista-respuesta-" + aux.getId() + "\">" + aux.getRespuesta() + "</a></td>\n");
                                            out.print("\t\t\t\t\t<td id=\"Pista-puntos-" + aux.getId() + "\">" + aux.getPuntos() + "</a></td>\n");
                                            out.print("\t\t\t\t\t<td id=\"Pista-categoriaid-" + aux.getId() + "\">" + aux.getCategoria().getNombre() + "</a></td>\n");
                                            out.print("\t\t\t\t\t<td><input name=\"seccion-" + 5 + "\" type =\"checkbox\" value=\"" + aux.getId() + "\" multiple onclick=\"cuenta" + 5 + "(this)\"></td>\n");
                                            out.print("\t\t\t\t</tr>\n");
                                        }
                                    }
                                }
                            %>
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
                        <select id="6" class="temas" onchange="getCategorias(this)">
                            <%
                                out.print("<option value=\"-1\">Selecciona un tema</option>");
                                help = perfil.getSeccion6();
                                if (help.size() > 0) {
                                    Pista pis = (Pista) help.get(0);
                                    int ayuda = pis.getCategoria().getTema().getId();
                                    for (int x = 0; x < temas.size(); x++) {
                                        Tema aux = (Tema) temas.get(x);
                                        if (ayuda == aux.getId()) {
                                            out.print("<option value=\"" + aux.getId() + "\" selected>" + aux.getTema() + "</option>");
                                        } else {
                                            out.print("<option value=\"" + aux.getId() + "\">" + aux.getTema() + "</option>");
                                        }
                                    }
                                } else {
                                    for (int x = 0; x < temas.size(); x++) {
                                        Tema aux = (Tema) temas.get(x);
                                        out.print("<option value=\"" + aux.getId() + "\">" + aux.getTema() + "</option>");
                                    }
                                }%>
                        </select>
                    </div>
                    <div class="select-style" style="float: right; margin-right: 21px;">
                        <select id="6" class="categorias" onchange="getPistas(this)">
                            <%
                                if (help.size() > 0) {
                                    Pista pis = (Pista) help.get(0);
                                    int ayuda = pis.getCategoria().getId();
                                    for (int x = 0; x < categorias.size(); x++) {
                                        Categoria aux = (Categoria) categorias.get(x);
                                        if (ayuda == aux.getId()) {
                                            out.print("<option class=\"categoriaOption\" value=\"" + aux.getId() + "\" selected>" + aux.getNombre() + " - " + aux.getTema().getTema() + "</option>");
                                        } else {
                                            out.print("<option class=\"categoriaOption\" value=\"" + aux.getId() + "\">" + aux.getNombre() + " - " + aux.getTema().getTema() + "</option>");
                                        }
                                    }
                                } else {
                                    for (int x = 0; x < categorias.size(); x++) {
                                        Categoria aux = (Categoria) categorias.get(x);
                                        out.print("<option class=\"categoriaOption\" value=\"" + aux.getId() + "\">" + aux.getNombre() + " - " + aux.getTema().getTema() + "</option>");
                                    }
                                }
                            %>
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
                            <%
                                pistas = (ArrayList) request.getAttribute("pistas6");
                                if (help.size() > 0) {
                                    for (int x = 0; x < pistas.size(); x++) {
                                        imprime = true;
                                        Pista aux = (Pista) pistas.get(x);
                                        for (int y = 0; y < help.size(); y++) {
                                            Pista auxHelp = (Pista) help.get(y);
                                            if (auxHelp.getId() == aux.getId()) {
                                                out.print("\t\t\t\t<tr>\n");
                                                out.print("\t\t\t\t\t<td id=\"Pista-redaccion-" + aux.getId() + "\">" + aux.getRedaccion() + "</a></td>\n");
                                                out.print("\t\t\t\t\t<td id=\"Pista-respuesta-" + aux.getId() + "\">" + aux.getRespuesta() + "</a></td>\n");
                                                out.print("\t\t\t\t\t<td id=\"Pista-puntos-" + aux.getId() + "\">" + aux.getPuntos() + "</a></td>\n");
                                                out.print("\t\t\t\t\t<td id=\"Pista-categoriaid-" + aux.getId() + "\">" + aux.getCategoria().getNombre() + "</a></td>\n");
                                                out.print("\t\t\t\t\t<td><input name=\"seccion-" + 6 + "\" type =\"checkbox\" value=\"" + aux.getId() + "\" multiple onclick=\"cuenta" + 6 + "(this)\" checked></td>\n");
                                                out.print("\t\t\t\t</tr>\n");
                                                imprime = false;
                                            }
                                        }
                                        if (imprime) {
                                            out.print("\t\t\t\t<tr>\n");
                                            out.print("\t\t\t\t\t<td id=\"Pista-redaccion-" + aux.getId() + "\">" + aux.getRedaccion() + "</a></td>\n");
                                            out.print("\t\t\t\t\t<td id=\"Pista-respuesta-" + aux.getId() + "\">" + aux.getRespuesta() + "</a></td>\n");
                                            out.print("\t\t\t\t\t<td id=\"Pista-puntos-" + aux.getId() + "\">" + aux.getPuntos() + "</a></td>\n");
                                            out.print("\t\t\t\t\t<td id=\"Pista-categoriaid-" + aux.getId() + "\">" + aux.getCategoria().getNombre() + "</a></td>\n");
                                            out.print("\t\t\t\t\t<td><input name=\"seccion-" + 6 + "\" type =\"checkbox\" value=\"" + aux.getId() + "\" multiple onclick=\"cuenta" + 6 + "(this)\"></td>\n");
                                            out.print("\t\t\t\t</tr>\n");
                                        }
                                    }
                                }
                            %>
                        </tbody>
                    </table>
                </div><br>
            </fieldset>
            <br>
            <input type="submit" value="Guardar perfil">
            <input style="margin-left: 40px;" type="button" value="Cancelar" onclick="recargar()">
        </form>
        <br>
    </center>
</body>
</html>
<%@include file="footer.html" %>

