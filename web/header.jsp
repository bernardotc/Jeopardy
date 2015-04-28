<%-- 
    Document   : header
    Created on : Apr 27, 2015, 8:52:05 PM
    Author     : bernardot
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Juego de Jeopardy</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" type="text/css" href="Style/general.css">
        <style>
            #header {
                list-style-type: none;
                margin: 0;
                padding: 0;
                width: 900px;
                overflow: hidden;
            }
            #header a {
                display: block;
                width: 448px;
                font-size: 20px;
                color: white;
                text-decoration: none;
            }
            #header li {
                float: left;
                background-color: #485D5D;
                border: 1px solid #AFE2F4;
            }
            
            #header li:hover {
                background-color: #799D9D;
                border: 1px solid #AFE2F4;
            }

        </style>
    </head>
    <header>
        <h1>El Juego de Jeopardy</h1>
        ${user.getNombre()}
        <ul id="header">
            <li><a href="menu.jsp">Menu</a></li>
            <li><a href="control?do=salir">Salir</a></li>
        </ul>
    </header>
</html>

