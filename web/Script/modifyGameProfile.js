/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
var request;
var objeto;
var cunta1 = 5;
var cunta2 = 5;
var cunta3 = 5;
var cunta4 = 5;
var cunta5 = 5;
var cunta6 = 5;

function recargar() {
    location.reload();
}

function getRequestObject() {
    if (window.ActiveXObject) {
        return(new ActiveXObject("Microsoft.XMLHTTP"));
    } else if (window.XMLHttpRequest) {
        return(new XMLHttpRequest());
    } else {
        return(null);
    }
}

function handleResponsePonerC() {
    if (request.readyState == 4) {
        var selects = document.getElementsByClassName("categorias");
        for (x = 0; x < selects.length; x++) {
            selects[x].innerHTML = request.responseText;
        }
    }
}

function handleResponsePonerP() {
    if (request.readyState == 4) {
        var tbodys = document.getElementsByTagName("tbody");
        for (x = 0; x < tbodys.length; x++) {
            tbodys[x].innerHTML = request.responseText;
        }
    }
}

function handleResponsePonerPUno() {
    if (request.readyState == 4) {
        objeto.innerHTML = request.responseText;
    }
}

function handleResponsePonerCUno() {
    if (request.readyState == 4) {
        objeto.innerHTML = request.responseText;
        getPistas(objeto);
    }
}

function cuenta1(obj) {
    if (obj.checked) {
        cunta1++;
    } else {
        cunta1--;
    }
    if (cunta1 > 5) {
        alert("Por favor solo selecciona 5 pistas");
        obj.checked = false;
        cunta1--;
    }
}

function cuenta2(obj) {
    if (obj.checked) {
        cunta2++;
    } else {
        cunta2--;
    }
    if (cunta2 > 5) {
        alert("Por favor solo selecciona 5 pistas");
        obj.checked = false;
        cunta2--;
    }
}

function cuenta3(obj) {
    if (obj.checked) {
        cunta3++;
    } else {
        cunta3--;
    }
    if (cunta3 > 5) {
        alert("Por favor solo selecciona 5 pistas");
        obj.checked = false;
        cunta3--;
    }
}

function cuenta4(obj) {
    if (obj.checked) {
        cunta4++;
    } else {
        cunta4--;
    }
    if (cunta4 > 5) {
        alert("Por favor solo selecciona 5 pistas");
        obj.checked = false;
        cunta4--;
    }
}

function cuenta5(obj) {
    if (obj.checked) {
        cunta5++;
    } else {
        cunta5--;
    }
    if (cunta5 > 5) {
        alert("Por favor solo selecciona 5 pistas");
        obj.checked = false;
        cunta5--;
    }
}

function cuenta6(obj) {
    if (obj.checked) {
        cunta6++;
    } else {
        cunta6--;
    }
    if (cunta6 > 5) {
        alert("Por favor solo selecciona 5 pistas");
        obj.checked = false;
        cunta6--;
    }
}

function checarPistasCompleto() {
    if ((cunta1 + cunta2 + cunta3 + cunta4 + cunta5 + cunta6) < 30) {
        alert("No has seleccionado las 30 pistas");
        return false;
    }
}

function getPistas(obj)
{
    if(obj.id == "1") {
        cunta1 = 0;
    } else if(obj.id == "2") {
        cunta2 = 0;
    } else if(obj.id == "3") {
        cunta3 = 0;
    } else if(obj.id == "4") {
        cunta4 = 0;
    } else if(obj.id == "5") {
        cunta5 = 0;
    } else if(obj.id == "6") {
        cunta6 = 0;
    }
    
    if (obj.value != "") {
        request = getRequestObject();
        request.onreadystatechange = handleResponsePonerPUno;
        request.open("POST", "control?do=getPAg", true);
        objeto = obj.parentNode.parentNode.childNodes[9].childNodes[3];
        categoriaid = obj.value;
        request.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
        request.send("categoriaid=" + categoriaid + "&id=" + obj.id);
    } else {
        objeto = obj.parentNode.parentNode.childNodes[9].childNodes[3];
        objeto.innerHTML = "";
    }
}

function getCategorias(obj)
{
    request = getRequestObject();
    request.onreadystatechange = handleResponsePonerCUno;
    request.open("POST", "control?do=getCAg", true);
    objeto = obj.parentNode.parentNode.childNodes[7].childNodes[1];
    temaid = obj.value;
    if(obj.id == "1") {
        cunta1 = 0;
    } else if(obj.id == "2") {
        cunta2 = 0;
    } else if(obj.id == "3") {
        cunta3 = 0;
    } else if(obj.id == "4") {
        cunta4 = 0;
    } else if(obj.id == "5") {
        cunta5 = 0;
    } else if(obj.id == "6") {
        cunta6 = 0;
    }
    request.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    request.send("temaid=" + temaid);
}