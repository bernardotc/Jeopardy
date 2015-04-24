/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var request;
var objeto;

function getRequestObject() {
    if (window.ActiveXObject) {
        return(new ActiveXObject("Microsoft.XMLHTTP"));
    } else if (window.XMLHttpRequest) {
        return(new XMLHttpRequest());
    } else {
        return(null);
    }
}

function trim(value) {
    var temp = value;
    var obj = /^(\s*)([\W\w]*)(\b\s*$)/;
    if (obj.test(temp)) {
        temp = temp.replace(obj, '$2');
    }
    var obj = /  /g;
    while (temp.match(obj)) {
        temp = temp.replace(obj, " ");
    }
    return temp;
}

//Funcion para calcular el largo en pixels det texto dado
function getTextWidth(texto)
{
    //Valor por default : 150 pixels
    var ancho = 150;

    if (trim(texto) == "")
    {
        return ancho;
    }

    //Creaci�n de un span escondido que se puedr� medir 
    var span = document.createElement("span");
    span.style.visibility = "hidden";
    span.style.position = "absolute";

    //Se agrega el texto al span y el span a la p�gina
    span.appendChild(document.createTextNode(texto));
    document.getElementsByTagName("body")[0].appendChild(span);

    //tama�o del texto
    ancho = span.offsetWidth;

    //Eliminaci�n del span
    document.getElementsByTagName("body")[0].removeChild(span);
    span = null;

    return ancho;
}

//Funcion de modificacion del elemento seleccionado mediante doble-click
function modificar(obj)
{
    var str = obj.id.split('-');
    var id = str[2];
    var tipo = str[1];
    var DB = str[0];

    //Objeto que sirve para editar el valor en la pagina 
    var input = null;

    input = document.createElement("input");


    //Asignar en la caja el valor de la casilla
    if (obj.innerText)
        input.value = obj.innerText;
    else
        input.value = obj.textContent;
    input.value = trim(input.value);

    //a la caja INPUT se la asigna un tama�o un poco mayor que el texto a modificar
    input.style.width = getTextWidth(input.value) + 30 + "px";

    //Se remplaza el texto por el objeto INPUT
    obj.replaceChild(input, obj.firstChild);

    //Se selecciona el elemento y el texto a modificar
    input.focus();
    input.select();

    //Asignaci�n de los 2 eventos que provocar�n la escritura en la base de datos 

    //Salida de la INPUT
    input.onblur = function salir()
    {
        salvarMod(obj, input.value, id, tipo, DB);
        delete input;
    };

    //La tecla Enter
    input.onkeydown = function keyDown(event)
    {
        if (event.keyCode == 13)
        {
            salvarMod(obj, input.value, id, tipo, DB);
            delete input;
        }
    };
}

function modificarTema(obj) {
    request = getRequestObject();
    request.onreadystatechange = handleResponseModT;
    request.open("POST", "control?do=getT", true);
    request.send();
    objeto = obj;
}

function handleResponseModT() {
    if (request.readyState == 4) {
        var str = objeto.id.split('-');
        var id = str[2];
        var tipo = str[1];
        var DB = str[0];

        var input = null;

        input = document.createElement("select");
        input.innerHTML = request.responseText;
        for (x = 0; x < input.options.length; x++) {
            if (input.options[x].innerText == objeto.innerText) {
                input.options.selectedIndex = x;
                break;
            }
        }
        objeto.replaceChild(input, objeto.firstChild);

        input.focus();

        input.onchange = function seleccion()
        {
            var x = input.selectedIndex;
            var valor = document.getElementsByTagName("option")[x].value;
            var texto = document.getElementsByTagName("option")[x].innerText;
            salvarModT(objeto, valor, id, tipo, DB, texto);
            delete input;
        };

        /*setTimeout(function () {
         var x = input.selectedIndex;
         var valor = document.getElementsByTagName("option")[x].value;
         var texto = document.getElementsByTagName("option")[x].innerText;
         salvarModT(objeto, valor, id, tipo, DB, texto);
         delete input;
         }, 10000);*/
    }
}

function modificarCategoria(obj) {
    request = getRequestObject();
    request.onreadystatechange = handleResponseModC;
    request.open("POST", "control?do=getC", true);
    request.send();
    objeto = obj;
}

function handleResponseModC() {
    if (request.readyState == 4) {
        var str = objeto.id.split('-');
        var id = str[2];
        var tipo = str[1];
        var DB = str[0];

        var input = null;

        input = document.createElement("select");
        input.innerHTML = request.responseText;
        for (x = 0; x < input.options.length; x++) {
            if (input.options[x].innerText.split(" - ")[0] == objeto.innerText) {
                input.options.selectedIndex = x;
                break;
            }
        }
        objeto.replaceChild(input, objeto.firstChild);

        input.focus();

        input.onchange = function seleccion()
        {
            var x = input.selectedIndex;
            var valor = document.getElementsByClassName("categoriaOption")[x].value;
            var t = document.getElementsByClassName("categoriaOption")[x].innerText.split(" - ");
            var texto = t[0];
            salvarModC(objeto, valor, id, tipo, DB, texto, t[1]);
            delete input;
        };
    }
}

function salvarModT(obj, valor, id, tipo, DB, texto)
{
    request = getRequestObject();
    request.onreadystatechange = handleResponse;
    request.open("POST", "control?do=change", true);
    request.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    request.send("id=" + id + "&tipo=" + tipo + "&valor=" + valor + "&db=" + DB);
    obj.replaceChild(document.createTextNode(texto), obj.firstChild);
}

function salvarModC(obj, valor, id, tipo, DB, texto, t)
{
    request = getRequestObject();
    request.onreadystatechange = handleResponse;
    request.open("POST", "control?do=change", true);
    request.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    request.send("id=" + id + "&tipo=" + tipo + "&valor=" + valor + "&db=" + DB);
    i = document.getElementById("temas").selectedIndex;
    tema = document.getElementsByTagName("option")[i].innerText;
    if (t == tema) {
        obj.replaceChild(document.createTextNode(texto), obj.firstChild);
    } else {
        document.getElementById("pistas").deleteRow(obj.parentNode.rowIndex);
    }
}

//Salvando las modificaciones
function salvarMod(obj, valor, id, tipo, DB)
{
    request = getRequestObject();
    request.onreadystatechange = handleResponse;
    request.open("POST", "control?do=change", true);
    request.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    request.send("id=" + id + "&tipo=" + tipo + "&valor=" + valor + "&db=" + DB);
    obj.replaceChild(document.createTextNode(valor), obj.firstChild);

}

function handleResponse() {
    if (request.readyState == 4) {
    }
}

function getPistas()
{
    request = getRequestObject();
    request.onreadystatechange = handleResponsePonerP;
    request.open("POST", "control?do=getP", true);
    x = document.getElementById("temas").selectedIndex;
    temaid = document.getElementsByTagName("option")[x].value;
    request.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    request.send("temaid=" + temaid);
}

function handleResponsePonerP() {
    if (request.readyState == 4) {
        document.getElementById("contenido").innerHTML = request.responseText;
    }
}

function borrar(obj)
{
    var idOrig = obj.id;
    var aux = idOrig.split("-");
    var DB = aux[0];
    var id = aux[1];
    request = getRequestObject();
    request.onreadystatechange = handleResponse;
    request.open("POST", "control?do=erase", true);
    request.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    request.send("id=" + id + "&db=" + DB);
    var i = obj.parentNode.parentNode.rowIndex;
    document.getElementsByTagName("table")[0].deleteRow(i);
}