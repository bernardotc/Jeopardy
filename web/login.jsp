<%-- 
    Document   : login
    Created on : Apr 1, 2015, 3:42:48 PM
    Author     : bernardot
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="header.html" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-16">
        <link rel="stylesheet" type="text/css" href="Style/formStyle.css">
        <style>
            #login, #signin {
                width: 415px;
                padding-left: 15px;
                display: inline-block;
                margin-left: auto;
                margin-right: auto;
            }
            
            #login {
                float: left;
            }
            
            #signin {
                float: right;
                padding-left: 20px;
                border-left: solid blue 1px;
            }
            
            fieldset {
                border: none;
            }
            
            div h3 {
                font-family: 'Open Sans', sans-serif;
                text-align: center;
                color: #77AAAA;
            }

        </style>
    </head>
    <body>
        <fieldset>
            <div id="login">
                <h3>Iniciar sesión</h3>
                <form name="login" action="control?do=login" method="post">
                    Usuario <br><input type="text" name="userid" required autofocus><br>
                    Contraseña <br><input type="password" name="userpswd" required><br>
                    <center><input type="submit" value="Iniciar"></center>
                </form>
            </div>

            <div id="signin">
                <h3>Registrarse</h3>
                <form name="signin" action="control?do=signin" method="post">
                    Nombre(s) <br><input type="text" name="newFName" required><br>
                    Apellido(s) <br><input type="text" name="newLName" required><br>
                    Correo <br><input type="text" name="newMail" required><br>
                    Usuario <br><input type="text" name="newUser" required><br>
                    <center><input type="submit" value="Registrar"></center>
                </form>
            </div>
        </fieldset>
    </body>
</html>
<%@include file="footer.html" %>
