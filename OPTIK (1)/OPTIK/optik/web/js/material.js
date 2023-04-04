let materiales;


function insertar() {
    if (localStorage.getItem("currentUser") === null) {
        Swal.fire({
            position: 'center',
            icon: 'error',
            title: 'Acceso Denegado',
            showConfirmButton: false,
            timer: 1500
        });
        window.location.href = "../optik/login.html";
    }
    if (localStorage.getItem("currentUser") !== "") {
        let mat = JSON.parse(localStorage.getItem("currentUser"));
        let lToken = mat.usuario.lastToken;
        let nombre = document.getElementById("txtNombre").value;
        let precioCompra = document.getElementById("txtPrecioCompra").value;
        let precioVenta = document.getElementById("txtPrecioVenta").value;
        
        
        
        nombre = sanitizar(nombre);
        nombre = normalizar(nombre);
        
        precioCompra = validateNumberInput(precioCompra);
        precioVenta = validateNumberInput(precioVenta);
        

        let m = {nombre: nombre, precioCompra: precioCompra, precioVenta: precioVenta};
        let material = {datosMaterial: JSON.stringify(m), lastToken: lToken};


        let parametros = new URLSearchParams(material);
        fetch("../optik/api/restoptik/insertarMaterial", {method: "POST", body: parametros, headers: {'Content-Type': 'application/x-www-form-urlencoded;charset=UTF-8'}})
                .then(response => response.json())
                .then(data => {
                    Swal.fire({
                        position: 'center',
                        icon: 'success',
                        title: 'Material agregado exitosamente',
                        showConfirmButton: false,
                        timer: 1500
                    });
                    limpiarForm();
                });
    }else{
        Swal.fire({
            position: 'center',
            icon: 'error',
            title: 'Acceso Denegado',
            showConfirmButton: false,
            timer: 1500
        });
        window.location.href= "../optik/login.html";
    }
}
function actualizar() {
    if (localStorage.getItem("currentUser") === null) {
        Swal.fire({
            position: 'center',
            icon: 'error',
            title: 'Acceso Denegado',
            showConfirmButton: false,
            timer: 1500
        });
        window.location.href = "../optik/login.html";
    }
    if (localStorage.getItem("currentUser") !== "") {
        let mat = JSON.parse(localStorage.getItem("currentUser"));
        let lToken = mat.usuario.lastToken;        
        let nombre = document.getElementById("txtNombre").value;
        let precioCompra = document.getElementById("txtPrecioCompra").value;
        let precioVenta = document.getElementById("txtPrecioVenta").value;
        let idMaterial = document.getElementById("txtIdMaterial").value;
        
        nombre = sanitizar(nombre);
        
        precioCompra = validateNumberInput(precioCompra);
        precioVenta = validateNumberInput(precioVenta);

        let m = {idMaterial: idMaterial, nombre: nombre, precioCompra: precioCompra, precioVenta: precioVenta};
        let material = {datosMaterial: JSON.stringify(m), lastToken: lToken};


        let parametros = new URLSearchParams(material);

        fetch("../optik/api/restoptik/updateMaterial", {method: "POST", body: parametros, headers: {'Content-Type': 'application/x-www-form-urlencoded;charset=UTF-8'}})
                .then(response => response.json())
                .then(data => {
                    Swal.fire({
                        position: 'center',
                        icon: 'success',
                        title: 'Material actualizado exitosamente',
                        showConfirmButton: false,
                        timer: 1500
                    });
                    limpiarForm();
                });
    }else{
        Swal.fire({
            position: 'center',
            icon: 'error',
            title: 'Acceso Denegado',
            showConfirmButton: false,
            timer: 1500
        });
        window.location.href= "../optik/login.html";        
    }
}
function getAllM() {
    if (localStorage.getItem("currentUser") === null) {
        Swal.fire({
            position: 'center',
            icon: 'error',
            title: 'Acceso Denegado',
            showConfirmButton: false,
            timer: 1500
        });
        window.location.href = "../optik/login.html";
    }
    if (localStorage.getItem("currentUser") !== "") {
        let mat = JSON.parse(localStorage.getItem("currentUser"));
        let lToken = mat.usuario.lastToken;
        let datos = {estatus: 1, lastToken: lToken};
        let parametros = new URLSearchParams(datos);

        fetch("../optik/api/restoptik/getAllMaterial", {method: "POST", body: parametros, headers: {'Content-Type': 'application/x-www-form-urlencoded;charset=UTF-8'}})
                .then(response => response.json())
                .then(data => {
                    //                   console.log(data);
                    //           alert(JSON.stringify(data));
                    if (data.error) {
                        alert(data.error);
                    } else {
                        cargarTablaMaterial(null, data);
                    }
                });
    } else {
        Swal.fire({
            position: 'center',
            icon: 'error',
            title: 'Acceso Denegado',
            showConfirmButton: false,
            timer: 1500
        });
        window.location.href= "../optik/login.html"; 
    }
}
function getAllInactivosM() {
    if (localStorage.getItem("currentUser") === null) {
        Swal.fire({
            position: 'center',
            icon: 'error',
            title: 'Acceso Denegado',
            showConfirmButton: false,
            timer: 1500
        });
        window.location.href = "../optik/login.html";
    }
    if (localStorage.getItem("currentUser") !== "") {
        let mat = JSON.parse(localStorage.getItem("currentUser"));
        let lToken = mat.usuario.lastToken;
        let datos = {estatus: 0, lastToken: lToken};
        let parametros = new URLSearchParams(datos);

        fetch("../optik/api/restoptik/getAllMaterial", {method: "POST", body: parametros, headers: {'Content-Type': 'application/x-www-form-urlencoded;charset=UTF-8'}})
                .then(response => response.json())
                .then(data => {
                    //                   console.log(data);
                    //           alert(JSON.stringify(data));
                    if (data.error) {
                        alert(data.error);
                    } else {
                        cargarTablaMaterial(null, data);
                    }
                });
    } else {
        Swal.fire({
            position: 'center',
            icon: 'error',
            title: 'Acceso Denegado',
            showConfirmButton: false,
            timer: 1500
        });
        window.location.href = "../optik/login.html";
    }
}
function cargarTablaMaterial(coincidencias, data) {
    if (coincidencias) {
        data = coincidencias;
    } else
        materiales = data;
    let contenido = "";
    data.forEach((material, index) => {
        contenido += "<tr>";
        contenido += "<td>" + material.nombre + "</td>";
        contenido += "<td>" + material.precioCompra + "</td>";
        contenido += "<td>" + material.precioVenta + "</td>";
        contenido += "<td><button type='button' class='btn btn-light m-3' onclick='cargarForm(" + index + ")'>Ver</button></td>";
        if (material.estatus === 1) {
            contenido += "<td><button type='button' class='btn btn-danger m-3' onclick='eliminar(" + material.idMaterial + ")'>Eliminar</button></td>";
        } else {
            contenido += "<td><button type='button' class='btn btn-success m-3' onclick='activar(" + material.idMaterial + ")'>Activar</button></td>";
        }
        contenido += "</tr>";
    });
    document.getElementById("tbMaterial").innerHTML = contenido;

}
function cargarForm(i) {
    document.getElementById("txtIdMaterial").value = materiales[i].idMaterial;
    document.getElementById("txtNombre").value = materiales[i].nombre;
    document.getElementById("txtPrecioCompra").value = materiales[i].precioCompra;
    document.getElementById("txtPrecioVenta").value = materiales[i].precioVenta;
}
function limpiarForm2() {
    document.getElementById("txtIdMaterial").value = "";
    document.getElementById("txtNombre").value = "";
    document.getElementById("txtPrecioCompra").value = "";
    document.getElementById("txtPrecioVenta").value = "";
    document.getElementById("myInput").value = "";
}
function limpiarTablaMaterial(data) {
    document.getElementById("tbMaterial").innerHTML = "";
}
function activar(idMaterial) {
    if (localStorage.getItem("currentUser") === null) {
        Swal.fire({
            position: 'center',
            icon: 'error',
            title: 'Acceso Denegado',
            showConfirmButton: false,
            timer: 1500
        });
        window.location.href = "../optik/login.html";
    }
    if (localStorage.getItem("currentUser") !== "") {
        let m = {idMaterial: idMaterial};

        let material = {datosMaterial: JSON.stringify(m)};
        let parametros = new URLSearchParams(material);
        fetch("../optik/api/restoptik/activarMaterial", {method: "POST", body: parametros, headers: {'Content-Type': 'application/x-www-form-urlencoded;charset=UTF-8'}})
                .then(response => response.json())
                .then(data => {
                    Swal.fire({
                        position: 'center',
                        icon: 'success',
                        title: 'Material activado exitosamente',
                        showConfirmButton: false,
                        timer: 1500
                    });
                    borrarTabla();
                });
    }else{
        Swal.fire({
            position: 'center',
            icon: 'error',
            title: 'Acceso Denegado',
            showConfirmButton: false,
            timer: 1500
        });
        window.location.href = "../optik/login.html";
    }
}
function eliminar(idMaterial) {
    if (localStorage.getItem("currentUser") === null) {
        Swal.fire({
            position: 'center',
            icon: 'error',
            title: 'Acceso Denegado',
            showConfirmButton: false,
            timer: 1500
        });
        window.location.href = "../optik/login.html";
    }
    if (localStorage.getItem("currentUser") !== "") {
        let m = {idMaterial: idMaterial};

        let material = {datosMaterial: JSON.stringify(m)};
        let parametros = new URLSearchParams(material);
        fetch("../optik/api/restoptik/eliminarMaterial", {method: "POST", body: parametros, headers: {'Content-Type': 'application/x-www-form-urlencoded;charset=UTF-8'}})
                .then(response => response.json())
                .then(data => {
                    Swal.fire({
                        position: 'center',
                        icon: 'success',
                        title: 'Material eliminado exitosamente',
                        showConfirmButton: false,
                        timer: 1500
                    });
                    borrarTabla();
                });
                
    } else {
        Swal.fire({
            position: 'center',
            icon: 'error',
            title: 'Acceso Denegado',
            showConfirmButton: false,
            timer: 1500
        });
        window.location.href = "../optik/login.html";
    }
}
function Rbusqueda() {
    if (localStorage.getItem("currentUser") === null) {
        Swal.fire({
            position: 'center',
            icon: 'error',
            title: 'Acceso Denegado',
            showConfirmButton: false,
            timer: 1500
        });
        window.location.href = "../optik/login.html";
    }
    if (localStorage.getItem("currentUser") !== "") {
        const busqueda = document.getElementById("myInput").value;
        const coincidencias = [];
        for (let i = 0; i < materiales.length; i++) {
            const material = materiales[i];

            if (material.nombre.toLowerCase().includes(busqueda.toLowerCase())) {
                coincidencias.push(material);
            }
        }
        console.table(coincidencias);
        cargarTablaMaterial(coincidencias, null);
    } else {
        Swal.fire({
            position: 'center',
            icon: 'error',
            title: 'Acceso Denegado',
            showConfirmButton: false,
            timer: 1500
        });
        window.location.href = "../optik/login.html";
    }
}

function normalizar(texto){
    texto = texto.toUpperCase();
    texto = texto.trim().replace(/\s+/g, ' ');
    for(var i=0;i<texto.length; i++){
        texto = texto.replace("Á","A");
        texto = texto.replace("É","E");
        texto = texto.replace("Í","I");
        texto = texto.replace("Ó","O");
        texto = texto.replace("Ú","U");
    }
    return texto;
}

function sanitizar(texto) {

    texto = texto.replace(/[^a-zA-Z0-9]+/g, '');
    texto = texto.replace(/\d/g, '');

    return texto;
}

function validateNumberInput(texto) {
  texto = texto.replace(/[a-zA-Z]/g, ' ');
  return texto;
}

function replaceLettersWithSpaces(texto) {
  return texto.replace(/[a-zA-Z]/g, " ");
}

function borrarTabla(){
    document.getElementById("tbMaterial").innerHTML = "";
}

function validar(){
        let nombre = document.getElementById("txtNombre").value;
        let precioCompra = document.getElementById("txtPrecioCompra").value;
        let precioVenta = document.getElementById("txtPrecioVenta").value;
        
        
};

