<%-- 
    Document   : jeopardyGame
    Created on : Apr 26, 2015, 10:31:23 AM
    Author     : bernardot
--%>

<%@page import="beans.Jugador"%>
<%@page import="beans.Pista"%>
<%@page import="beans.PerfilJuego"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="header.jsp" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="Style/formStyle.css">
        <title>Tu juego de Jeopardy</title>
        <script>
            function mostrar(pregunta, respuesta) {
                alert(pregunta);
                alert(respuesta);
            }
            
            function validar() {
                selects = document.getElementsByTagName("select");
                for (x = 0; x < selects.length; x++) {
                    if(selects[x].value == -1) {
                        alert("Hay pistas sin contestar.");
                        return false
                    } 
                }
                return true;
            }
            
        </script>
        <style>
            tbody td {
                border: 1px solid cornflowerblue;
                color: darkblue;
                font-size: 10px;
            }

            .select-style { 
                width: 140px;
                padding: .05em .05em .05em .05em;
            }

            .select-style select { 
                width: 150%;
            }
        </style>
    </head>
    <body>
        <p style="width: 100%">Para ver la pista haz click al botón de la pregunta que quieras. La respuesta de la pista saldrá
            después de que le des OK al mensaje de la pista. Por lo tanto, espera un tiempo hasta que un jugador
            conteste la pista para darle OK. Luego selecciona el nombre del jugador que se sacó bien la respuesta
            en la lista inferior al botón.</p>
        <form onsubmit="return validar();" action="control?do=saveGame" method="post">
            <table style="width: 100%;">
                <thead>
                    <%
                        PerfilJuego perfil = (PerfilJuego) session.getAttribute("perfil");
                        ArrayList jugadores = (ArrayList) session.getAttribute("jugadores");
                        ArrayList auxList = perfil.getSeccion1();
                        Pista aux = (Pista) auxList.get(0);
                        out.print("<td>" + aux.getCategoria().getNombre() + "</td>\n");
                        
                        auxList = perfil.getSeccion2();
                        aux = (Pista) auxList.get(0);
                        out.print("<td>" + aux.getCategoria().getNombre() + "</td>\n");
                        
                        auxList = perfil.getSeccion3();
                        aux = (Pista) auxList.get(0);
                        out.print("<td>" + aux.getCategoria().getNombre() + "</td>\n");
                        
                        auxList = perfil.getSeccion4();
                        aux = (Pista) auxList.get(0);
                        out.print("<td>" + aux.getCategoria().getNombre() + "</td>\n");
                        
                        auxList = perfil.getSeccion5();
                        aux = (Pista) auxList.get(0);
                        out.print("<td>" + aux.getCategoria().getNombre() + "</td>\n");
                        
                        auxList = perfil.getSeccion6();
                        aux = (Pista) auxList.get(0);
                        out.print("<td>" + aux.getCategoria().getNombre() + "</td>\n");
                        %>
                </thead>
                <tbody>
                    <%
                        ArrayList sec1 = perfil.getSeccion1();
                        ArrayList sec2 = perfil.getSeccion2();
                        ArrayList sec3 = perfil.getSeccion3();
                        ArrayList sec4 = perfil.getSeccion4();
                        ArrayList sec5 = perfil.getSeccion5();
                        ArrayList sec6 = perfil.getSeccion6();
                        out.print("<tr>\n");
                        for (int y = 0; y < sec1.size(); y++) {
                            out.print("<tr>\n");

                            Pista aux1 = (Pista) sec1.get(y);
                            out.print("<td>\n");
                            out.print("<input type=\"button\" onclick=\"mostrar('Pista: " + aux1.getRedaccion() + "', 'Respuesta: " + aux1.getRespuesta() + "');\" value=\"" + aux1.getPuntos() + "\"><br>\n");
                            out.print("¿Quién la obtuvo bien?");
                            out.print("<div class=\"select-style\">\n");
                            out.print("<select name=\"" + (y+1) + "\">\n");
                            out.print("<option value=\"-1\">Jugador</option>\n");
                            for (int x = 0; x < jugadores.size(); x++) {
                                Jugador jug = (Jugador) jugadores.get(x);
                                out.print("<option value=\"" + jug.getId() + "-" + aux1.getId() + "\">" + jug.getNombre() + "</option>\n");
                            }
                            out.print("</select>\n");
                            out.print("</div>\n");
                            out.print("</td>\n");

                            Pista aux2 = (Pista) sec2.get(y);
                            out.print("<td>\n");
                            out.print("<input type=\"button\" onclick=\"mostrar('Pista: " + aux2.getRedaccion() + "', 'Respuesta: " + aux2.getRespuesta() + "');\" value=\"" + aux2.getPuntos() + "\"><br>\n");
                            out.print("¿Quién la obtuvo bien?");
                            out.print("<div class=\"select-style\">\n");
                            out.print("<select name=\"" + (y+6) + "\">\n");
                            out.print("<option value=\"-1\">Jugador</option>\n");
                            for (int x = 0; x < jugadores.size(); x++) {
                                Jugador jug = (Jugador) jugadores.get(x);
                                out.print("<option value=\"" + jug.getId() + "-" + aux2.getId() + "\">" + jug.getNombre() + "</option>\n");
                            }
                            out.print("</select>\n");
                            out.print("</div>\n");
                            out.print("</td>\n");

                            Pista aux3 = (Pista) sec3.get(y);
                            out.print("<td>\n");
                            out.print("<input type=\"button\" onclick=\"mostrar('Pista: " + aux3.getRedaccion() + "', 'Respuesta: " + aux3.getRespuesta() + "');\" value=\"" + aux3.getPuntos() + "\"><br>\n");
                            out.print("¿Quién la obtuvo bien?");
                            out.print("<div class=\"select-style\">\n");
                            out.print("<select name=\"" + (y+11) + "\">\n");
                            out.print("<option value=\"-1\">Jugador</option>\n");
                            for (int x = 0; x < jugadores.size(); x++) {
                                Jugador jug = (Jugador) jugadores.get(x);
                                out.print("<option value=\"" + jug.getId() + "-" + aux3.getId() + "\">" + jug.getNombre() + "</option>\n");
                            }
                            out.print("</select>\n");
                            out.print("</div>\n");
                            out.print("</td>\n");

                            Pista aux4 = (Pista) sec4.get(y);
                            out.print("<td>\n");
                            out.print("<input type=\"button\" onclick=\"mostrar('Pista: " + aux4.getRedaccion() + "', 'Respuesta: " + aux4.getRespuesta() + "');\" value=\"" + aux4.getPuntos() + "\"><br>\n");
                            out.print("¿Quién la obtuvo bien?");
                            out.print("<div class=\"select-style\">\n");
                            out.print("<select name=\"" + (y+16) + "\">\n");
                            out.print("<option value=\"-1\">Jugador</option>\n");
                            for (int x = 0; x < jugadores.size(); x++) {
                                Jugador jug = (Jugador) jugadores.get(x);
                                out.print("<option value=\"" + jug.getId() + "-" + aux4.getId() + "\">" + jug.getNombre() + "</option>\n");
                            }
                            out.print("</select>\n");
                            out.print("</div>\n");
                            out.print("</td>\n");

                            Pista aux5 = (Pista) sec5.get(y);
                            out.print("<td>\n");
                            out.print("<input type=\"button\" onclick=\"mostrar('Pista: " + aux5.getRedaccion() + "', 'Respuesta: " + aux5.getRespuesta() + "');\" value=\"" + aux5.getPuntos() + "\"><br>\n");
                            out.print("¿Quién la obtuvo bien?");
                            out.print("<div class=\"select-style\">\n");
                            out.print("<select name=\"" + (y+21) + "\">\n");
                            out.print("<option value=\"-1\">Jugador</option>\n");
                            for (int x = 0; x < jugadores.size(); x++) {
                                Jugador jug = (Jugador) jugadores.get(x);
                                out.print("<option value=\"" + jug.getId() + "-" + aux5.getId() + "\">" + jug.getNombre() + "</option>\n");
                            }
                            out.print("</select>\n");
                            out.print("</div>\n");
                            out.print("</td>\n");

                            Pista aux6 = (Pista) sec6.get(y);
                            out.print("<td>\n");
                            out.print("<input type=\"button\" onclick=\"mostrar('Pista: " + aux6.getRedaccion() + "', 'Respuesta: " + aux6.getRespuesta() + "');\" value=\"" + aux6.getPuntos() + "\"><br>\n");
                            out.print("¿Quién la obtuvo bien?");
                            out.print("<div class=\"select-style\">\n");
                            out.print("<select name=\"" + (y+26) + "\">\n");
                            out.print("<option value=\"-1\">Jugador</option>\n");
                            for (int x = 0; x < jugadores.size(); x++) {
                                Jugador jug = (Jugador) jugadores.get(x);
                                out.print("<option value=\"" + jug.getId() + "-" + aux6.getId() + "\">" + jug.getNombre() + "</option>\n");
                            }
                            out.print("</select>\n");
                            out.print("</div>\n");
                            out.print("</td>\n");

                            out.print("</tr>\n");
                        }
                    %>
                </tbody>
            </table>
                <center>
                    <br>
                    <input type="submit" value="Terminar juego">
                </center>
        </form>
        <br>
    </body>
</html>
<%@include file="footer.html" %>