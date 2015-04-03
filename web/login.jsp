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
        <title>JSP Page</title>
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
            
            form {
                font-family: 'Open Sans', sans-serif;
                font-size: 12px;
                color: darkblue;
            }
            
            div h3 {
                font-family: 'Open Sans', sans-serif;
                text-align: center;
                color: #77AAAA;
            }
            
            input[type="text"], input[type="password"] {
                font-family: 'Open Sans', sans-serif;
                width: 360px;
                padding: 1em 1em 1em 1em;
                color: #5566FF;
                font-size: 16px;
                outline: none;
                background: 5px 7px #99DDDD;
                border: none;
                font-weight: 100;
                margin-bottom: 1em;
            }
            
            input[type="text"]:hover, input[type="password"]:hover {
                background-color:#55DDDD;
            }
            
            input[type="text"]:focus, input[type="password"]:focus {
                background-color: #BBFFFF;
            }
            
            input[type="submit"] {
                background: #34b8d9;
                background-image: -webkit-linear-gradient(top, #34b8d9, #2980b9);
                background-image: -moz-linear-gradient(top, #34b8d9, #2980b9);
                background-image: -ms-linear-gradient(top, #34b8d9, #2980b9);
                background-image: -o-linear-gradient(top, #34b8d9, #2980b9);
                background-image: linear-gradient(to bottom, #34b8d9, #2980b9);
                -webkit-border-radius: 6;
                -moz-border-radius: 6;
                border-radius: 6px;
                color: #ffffff;
                font-size: 20px;
                padding: 10px 20px 10px 20px;
                text-decoration: none;
            }

            input[type="submit"]:hover {
                background: #2aa4bf;
                background-image: -webkit-linear-gradient(top, #2aa4bf, #42c7af);
                background-image: -moz-linear-gradient(top, #2aa4bf, #42c7af);
                background-image: -ms-linear-gradient(top, #2aa4bf, #42c7af);
                background-image: -o-linear-gradient(top, #2aa4bf, #42c7af);
                background-image: linear-gradient(to bottom, #2aa4bf, #42c7af);
                text-decoration: none;
            }

        </style>
    </head>
    <body>
        <fieldset>
            <div id="login">
                <h3>Iniciar sesión</h3>
                <form>
                    Usuario <br><input type="text" name="userid" required autofocus><br>
                    Contraseña <br><input type="password" name="userpswd" required><br>
                    <center><input type="submit" value="Iniciar"></center>
                </form>
            </div>

            <div id="signin">
                <h3>Registrarse</h3>
                <form>
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
