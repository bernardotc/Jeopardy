<%-- 
    Document   : confirmation
    Created on : Apr 3, 2015, 12:16:05 PM
    Author     : bernardot
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="header.html" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="Style/formStyle.css">
        <style>
            h3 {
                font-family: 'Open Sans', sans-serif;
                text-align: center;
                color: #77AAAA;
            }
            
            form {
                padding-left: 20px;
                width: 430px;
                display: inline-block;
            }
            
            img {
                margin-bottom: 50px;
                margin-left: 130px;
            }
            
        </style>
    </head>
    <body>
        <h3>Confirmar cuenta</h3>
        <form>
            Usuario <br><span></span><br>
            Contraseña recibida <br><input type="password" name="lPasw" required><br>
            Nueva contraseña <br><input type="password" name="nPaswd" required><br>
            Confirmar contraseña <br><input type="password" name="cNPaswd" required><br>
            <center><input type="submit" value="Confirmar"></center>
        </form>
        <img src="https://mrvaudrey.files.wordpress.com/2011/09/jeopardy_220x220.jpg">
    </body>
</html>
<%@include file="footer.html" %>

