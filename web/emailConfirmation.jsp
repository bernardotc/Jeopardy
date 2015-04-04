<%-- 
    Document   : emailConfirmation
    Created on : Apr 4, 2015, 1:42:09 PM
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
            h2, h3, h5 {
                color: #4499FF;
            }
            
            h2 {
                text-align: center;
            }
            
            h3 {
                text-decoration: underline;
            }
            
            form {
                width: 300px;
                margin-top:-20px;
            }
            
            #enviarCorreo {
                float: left;
                margin-left: 10px;
            }
            
            #regresarInicio {
                float: right;
            }
            
            fieldset {
                border: none;
            }
        </style>
    </head>
    <body>
        <h2>Un correo electrónico ha sido mandado a tu cuenta.</h2>
        <h3>Sea tan amable de esperar unos minutos para confirmar su cuenta.</h3>
        <h5>En caso de que no llegue un correo, se puede enviar otra vez:</h5>
        <fieldset>
            <form id="enviarCorreo">
                <input type="submit" value="Enviar correo nuevamente">
            </form>
            <form id="regresarInicio">
                <input type="submit" value="Regresar a inicio de sesión">
            </form>
        </fieldset>
    </body>
</html>
<%@include file="footer.html" %>

