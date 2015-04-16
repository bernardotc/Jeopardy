/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
var request;
function getRequestObject() {
    if (window.ActiveXObject) {
        return(new ActiveXObject("Microsoft.XMLHTTP"));
    } else if (window.XMLHttpRequest) {
        return(new XMLHttpRequest());
    } else {
        return(null);
    }
}

function crearTema() {
    request = getRequestObject();
    request.onreadystatechange = handleResponseTema;
    request.open("POST", "control", true);
    tema = document.getElementById("temaN").value;
    tema = tema.toString();
    request.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    request.send("do=insertTema&tema=" + tema);
}

function handleResponseTema() {
    if (request.readyState == 4) {
        if (request.responseText === "error") {
            document.getElementById("respuestaCategoria").innerHTML = "No se pudo crear el tema, intente después o contacte al administrador.";
            setTimeout(function () {
                document.getElementById("respuestaCategoria").innerHTML = "";
            }, 3000);
        } else {
            document.getElementById("temaN").value = "";
            document.getElementById("temas").innerHTML = request.responseText;
            document.getElementById("respuestaTema").innerHTML = "Tema creado exitosamente.";
            setTimeout(function () {
                document.getElementById("respuestaTema").innerHTML = "";
            }, 3000);
        }
    }
}

function crearCategoria() {
    request = getRequestObject();
    request.onreadystatechange = handleResponseCategoria;
    request.open("POST", "control", true);
    categoria = document.getElementById("categoriaN").value.toString();
    descripcion = document.getElementById("descripcionC").value.toString();
    x = document.getElementById("temas").selectedIndex;
    temaid = document.getElementsByTagName("option")[x].value;
    request.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    request.send("do=insertCategoria&categoria=" + categoria + "&descripcion=" + descripcion + "&temaid=" + temaid);
}

function handleResponseCategoria() {
    if (request.readyState == 4) {
        if (request.responseText === "error") {
            document.getElementById("respuestaCategoria").innerHTML = "No se pudo crear la categoría, intente después o contacte al administrador.";
            setTimeout(function () {
                document.getElementById("respuestaCategoria").innerHTML = "";
            }, 3000);
        } else {
            document.getElementById("categoriaN").value = "";
            document.getElementById("descripcionC").value = "";
            x = document.getElementById("temas").selectedIndex;
            document.getElementsByTagName("option")[x].selected = false;
            document.getElementById("categorias").innerHTML = request.responseText;
            document.getElementById("respuestaCategoria").innerHTML = "Categoria creada exitosamente.";
            setTimeout(function () {
                document.getElementById("respuestaCategoria").innerHTML = "";
            }, 3000);
        }
    }
}

function crearPista() {
    request = getRequestObject();
    request.onreadystatechange = handleResponsePista;
    request.open("POST", "control", true);
    pista = document.getElementById("redaccion").value.toString();
    respuesta = document.getElementById("respuesta").value.toString();
    puntos = document.getElementById("puntos").value;
    x = document.getElementById("categorias").selectedIndex;
    categoriaid = document.getElementsByTagName("option")[x].value;
    request.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    request.send("do=insertPista&redaccion=" + pista + "&respuesta=" + respuesta + "&puntos=" + puntos + "&categoriaid=" + categoriaid);
}

function handleResponsePista() {
    if (request.readyState == 4) {
        if (request.responseText === "error") {
            document.getElementById("respuestaPista").innerHTML = "No se pudo crear la pista, intente después o contacte al administrador.";
            setTimeout(function () {
                document.getElementById("respuestaPista").innerHTML = "";
            }, 3000);
        } else {
            document.getElementById("redaccion").value = "";
            document.getElementById("respuesta").value = "";
            document.getElementById("puntos").value = "";
            x = document.getElementById("categorias").selectedIndex;
            document.getElementsByTagName("option")[x].selected = false;
            document.getElementById("categorias").innerHTML = request.responseText;
            document.getElementById("respuestaPista").innerHTML = "Pista creada exitosamente.";
            setTimeout(function () {
                document.getElementById("respuestaPista").innerHTML = "";
            }, 3000);
        }
    }
}