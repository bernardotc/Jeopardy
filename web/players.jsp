<%-- 
    Document   : players
    Created on : Apr 26, 2015, 9:19:10 AM
    Author     : bernardot
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="header.html" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="Style/formStyle.css">
        <title>Define los jugadores</title>
        <style>
            input[type="text"] {
                margin-right: 56px;
            }
        </style>
        <script>
            var x=1;
            function agregarJugador() {
                numero = document.getElementById("number").value;
                form = document.getElementById("jugadores");
                
                submit = document.getElementById("submit");
                
                form.removeChild(submit);
                
                for (; x <= numero; x++) {
                    var input = document.createElement("input");
                    input.type = "text";
                    input.name = "jugador" + x;
                    input.placeholder = "Nombre";
                    var text = document.createTextNode("Jugador " + x + ":");
                    form.appendChild(text);
                    form.appendChild(input); // put it into the DOM
                    form.appendChild(document.createElement("br"));
                }
                
                for (; numero < x-1; x--) {
                    form.removeChild(form.lastChild);
                    form.removeChild(form.lastChild);
                    form.removeChild(form.lastChild);
                }
                
                form.appendChild(submit);
                document.getElementById("submit").style.visibility = "visible";
            }
        </script>
    </head>
    <body>
        <h3>Pon los nombres de los jugadores que jugarán este juego.</h3>
    <center>
        <form id="jugadores" action="control?do=putPlayers" method="post">
            Número de jugadores: <br><input type="number" id="number" name="number" min="1" required onchange="agregarJugador()"><br>
            <input id="submit" type="submit" value="Empezar el juego" style="visibility:hidden">
        </form>
        <br>
    </center>
    </body>
</html>
<%@include file="footer.html" %>

