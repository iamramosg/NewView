let graduaciones;

function insertar() {
    let esferaod = document.getElementById("txtEsferaod").value;
    let esferaoi = document.getElementById("txtEsferaoi").value;
    let cilindrood = document.getElementById("txtCilindrood").value;
    let cilindrooi = document.getElementById("txtCilindrooi").value;
    let ejeod = document.getElementById("txtEjeod").value;
    let ejeoi = document.getElementById("txtEjeoi").value;
    let dip = document.getElementById("txtDip").value;
    const graduacion = {datosGraduacion: JSON.stringify({esferaod: esferaod, esferaoi: esferaoi, cilindrood: cilindrood, cilindrooi: cilindrooi, ejeod: ejeod, ejeoi: ejeoi, dip: dip})};

    const url = new URLSearchParams(graduacion);
    fetch('http://localhost:8080/optik/api/restoptik/guardarGraduacion',
            {
                method: "POST",
                headers: {'Content-Type': 'application/x-www-form-urlencoded;charset=UTF-8'},
                body: url
            })
            .then(response => response.json())
            .then(data => {
                Swal.fire({
                    position: 'center',
                    icon: 'success',
                    title: 'Graduacion agregada exitosamente',
                    showConfirmButton: false,
                    timer: 1500
                });
                limpiarForm();
                getAll();
            });

}

function actualizar() {
    let idGraduacion = document.getElementById("txtIdGraduacion").value;
    let esferaod = document.getElementById("txtEsferaod").value;
    let esferaoi = document.getElementById("txtEsferaoi").value;
    let cilindrood = document.getElementById("txtCilindrood").value;
    let cilindrooi = document.getElementById("txtCilindrooi").value;
    let ejeod = document.getElementById("txtEjeod").value;
    let ejeoi = document.getElementById("txtEjeoi").value;
    let dip = document.getElementById("txtDip").value;
    const graduacion = {datosGraduacion: JSON.stringify({idGraduacion: idGraduacion, esferaod: esferaod, esferaoi: esferaoi, cilindrood: cilindrood, cilindrooi: cilindrooi, ejeod: ejeod, ejeoi: ejeoi, dip: dip})};

    let parametros = new URLSearchParams(graduacion);
    fetch("http://localhost:8080/optik/api/restoptik/updateGraduacion",
            {
                method: "POST",
                headers: {'Content-Type': 'application/x-www-form-urlencoded;charset=UTF-8'},
                body: parametros
            }).then(response => response.json())
            .then(data => {
                console.log(data);
                if (data.error) {
                    alert(data.error);
                } else if (data.idGraduacion) {
                    //alert("Armazon actualizado con id: "+data.idArmazon);
                } else {
                    JSON.stringify(data);
                }
                JSON.stringify(data);
                Swal.fire({
                    position: 'center',
                    icon: 'success',
                    title: 'Graduacion actualizada exitosamente',
                    showConfirmButton: false,
                    timer: 1500
                });
                limpiarForm();
                getAll();
            });

}

function getAll() {
    let datos = {estatus: 1};
    let parametros = new URLSearchParams(datos);

    fetch('http://localhost:8080/optik/api/restoptik/getAllGraduacion',
            {
                method: "POST",
                headers: {'Content-Type': 'application/x-www-form-urlencoded;charset=UTF-8'},
                body: parametros
            })
            .then(response => response.json())
            .then(data => {

                if (data.error) {
                    alert(data.error);
                } else {
                    cargarTablaGraduacion(null,data);
                }
            });
}

function getAllInactivos() {
    let datos = {estatus: 0};
    let parametros = new URLSearchParams(datos);

    fetch('http://localhost:8080/optik/api/restoptik/getAllGraduacion',
            {
                method: "POST",
                headers: {'Content-Type': 'application/x-www-form-urlencoded;charset=UTF-8'},
                body: parametros
            })
            .then(response => response.json())
            .then(data => {

                if (data.error) {
                    alert(data.error);
                } else {
                    cargarTablaGraduacion(null, data);
                }
            });
}

function cargarTablaGraduacion(coincidencias, data) {
    if(coincidencias){
        data = coincidencias;
    }else

    graduaciones = data;
    let contenido = "";
    data.forEach((graduacion, index) => {
        let es = "Ojo derecho: " + graduacion.esferaod + " / Ojo izquierdo: " + graduacion.esferaoi;
        let ci = "Ojo derecho: " + graduacion.cilindrood + " / Ojo izquierdo: " + graduacion.cilindrooi;
        let ej = "Ojo derecho: " + graduacion.ejeod + " / Ojo izquierdo: " + graduacion.ejeoi;
        contenido += "<tr>";
        contenido += "<td>" + es + "</td>";
        contenido += "<td>" + ci + "</td>";
        contenido += "<td>" + ej + "</td>";
        contenido += "<td>" + graduacion.dip + "</td>";
        contenido += "<td><button type='button' class='btn btn-light m-3' onclick='cargarForm(" +index+ ");'> VER </button></td>";
        if(graduacion.estatus === 1){
            contenido += "<td><button type='button' class='btn btn-danger m-3'  onclick='eliminar(" + graduacion.idGraduacion + ")'> Eliminar </button></td>";
        }else{
            contenido += "<td><button type='button' class='btn btn-success m-3' onclick='agregar(" + graduacion.idGraduacion + ")'> Activar </button></td>";
        }
        contenido += "</tr>";
    });
    document.getElementById("tbGraduacion").innerHTML = contenido;
}

function cargarForm(i) {

    document.getElementById("txtIdGraduacion").value = graduaciones[i].idGraduacion;
    document.getElementById("txtEsferaod").value = graduaciones[i].esferaod;
    document.getElementById("txtEsferaoi").value = graduaciones[i].esferaoi;
    document.getElementById("txtCilindrood").value = graduaciones[i].cilindrood;
    document.getElementById("txtCilindrooi").value = graduaciones[i].cilindrooi;
    document.getElementById("txtEjeod").value = graduaciones[i].ejeod;
    document.getElementById("txtEjeoi").value = graduaciones[i].ejeoi;
    document.getElementById("txtDip").value = graduaciones[i].dip;

}

function borrarTabla() {
    document.getElementById("tbGraduacion").innerHTML = "";
}

function eliminar(i) {
    let idGraduacion = i;
    const graduacion = {idGraduacion};

    let parametros = new URLSearchParams(graduacion);
    fetch("http://localhost:8080/optik/api/restoptik/eliminarGraduacion",
            {
                method: "POST",
                headers: {'Content-Type': 'application/x-www-form-urlencoded;charset=UTF-8'},
                body: parametros
            }).then(response => response.json())
            .then(data => {
                console.log(data);
                if (data.error) {
                    alert(data.error);
                } else if (data.idGraduacion) {
                    //alert("Armazon actualizado con id: "+data.idArmazon);
                } else {
                    JSON.stringify(data);
                }
                JSON.stringify(data);
            });

    Swal.fire({
        position: 'center',
        icon: 'success',
        title: 'Graduacion eliminada exitosamente',
        showConfirmButton: false,
        timer: 1500
        
    });
     borrarTabla();
}

function agregar(i) {
    let idGraduacion = i;
    const graduacion = {idGraduacion};

    let parametros = new URLSearchParams(graduacion);
    fetch("http://localhost:8080/optik/api/restoptik/agregarGraduacion",
            {
                method: "POST",
                headers: {'Content-Type': 'application/x-www-form-urlencoded;charset=UTF-8'},
                body: parametros
            }).then(response => response.json())
            .then(data => {
                console.log(data);
                if (data.error) {
                    alert(data.error);
                } else if (data.idGraduacion) {
                    // alert("Empleado actualizado con id: "+data.idArmazon);
                } else {
                    JSON.stringify(data);
                }
                JSON.stringify(data);
            });

    Swal.fire({
        position: 'center',
        icon: 'success',
        title: 'Graduacion agregada exitosamente',
        showConfirmButton: false,
        timer: 1500
    });
     borrarTabla();
}

function Rbusqueda(){
    const busqueda = document.getElementById("myInput").value;
    const coincidencias = [];
    
    for(let i=0; i < graduaciones.length; i++){
        const graduacion = graduaciones[i];
        
        if(graduacion.dip.includes(busqueda)){
            coincidencias.push(graduacion);
        }
    }
    
    console.table(coincidencias);
    cargarTablaGraduacion(coincidencias,null);
    
}
function limpiarForm() {
    document.getElementById("txtIdGraduacion").value = "";
    document.getElementById("txtEsferaod").value = "";
    document.getElementById("txtEsferaoi").value = "";
    document.getElementById("txtCilindrood").value = "";
    document.getElementById("txtCilindrooi").value = "";
    document.getElementById("txtEjeod").value = "";
    document.getElementById("txtEjeoi").value = "";
    document.getElementById("txtDip").value = "";
}
