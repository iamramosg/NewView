let armazones;
var foto = "";

function insertar() {
    let modelo = document.getElementById("txtModelo").value;
    let color = document.getElementById("txtColor").value;
    let dimensiones = document.getElementById("txtDimensiones").value;
    let descripcion = document.getElementById("txtDescripcion").value;
    let fotografia = foto;
    let codigo = document.getElementById("txtCodigo").value;
    let nombre = document.getElementById("txtNom").value;
    let marca = document.getElementById("txtMarca").value;
    let precioCompra = document.getElementById("txtPrecioCompra").value;
    let precioVenta = document.getElementById("txtPrecioVenta").value;
    let existencias = document.getElementById("txtExistencias").value;
    let producto = {codigoBarras: codigo, nombre: nombre, marca: marca, precioCompra: precioCompra, precioVenta: precioVenta, existencias: existencias};
    // let armazon = {modelo: modelo, color: color, dimensiones: dimensiones, descripcion: descripcion, fotografia: fotografia};
    const armazon = {datosArmazon: JSON.stringify({producto: producto, modelo: modelo, color: color, dimensiones: dimensiones, descripcion: descripcion, fotografia: fotografia})};



    const url = new URLSearchParams(armazon);
    fetch('../optik/api/restoptik/guardarArmazon',
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
                    title: 'Armazón agregado exitosamente',
                    showConfirmButton: false,
                    timer: 1500
                });
                limpiarForm();
                getAll();
            });
    
    

}
function actualizar() {
    let idArmazon = document.getElementById("txtIdArm").value;
    let modelo = document.getElementById("txtModelo").value;
    let color = document.getElementById("txtColor").value;
    let dimensiones = document.getElementById("txtDimensiones").value;
    let descripcion = document.getElementById("txtDescripcion").value;
    let fotografia = foto;
    let codigo = document.getElementById("txtCodigo").value;
    let nombre = document.getElementById("txtNom").value;
    let marca = document.getElementById("txtMarca").value;
    let precioCompra = document.getElementById("txtPrecioCompra").value;
    let precioVenta = document.getElementById("txtPrecioVenta").value;
    let existencias = document.getElementById("txtExistencias").value;
    let producto = {codigoBarras: codigo, nombre: nombre, marca: marca, precioCompra: precioCompra, precioVenta: precioVenta, existencias: existencias};
    // let armazon = {modelo: modelo, color: color, dimensiones: dimensiones, descripcion: descripcion, fotografia: fotografia};
    const armazon = {datosArmazon: JSON.stringify({idArmazon: idArmazon, producto: producto, modelo: modelo, color: color, dimensiones: dimensiones, descripcion: descripcion, fotografia: fotografia})};

    let parametros = new URLSearchParams(armazon);
    fetch("../optik/api/restoptik/updateArmazon",
            {
                method: "POST",
                headers: {'Content-Type': 'application/x-www-form-urlencoded;charset=UTF-8'},
                body: parametros
            }).then(response => response.json())
            .then(data => {
                console.log(data);
                if (data.error) {
                    alert(data.error);
                } else if (data.idArmazon) {
                    //alert("Armazon actualizado con id: "+data.idArmazon);
                } else {
                    JSON.stringify(data);
                }
                JSON.stringify(data);
                Swal.fire({
                    position: 'center',
                    icon: 'success',
                    title: 'Armazón actualizado exitosamente',
                    showConfirmButton: false,
                    timer: 1500
                });
                limpiarForm();
                getAll();
            });

}

function eliminar(i) {
    let idArmazon = i;
    const armazon = {idArmazon};

    let parametros = new URLSearchParams(armazon);
    fetch("../optik/api/restoptik/eliminarArmazon",
            {
                method: "POST",
                headers: {'Content-Type': 'application/x-www-form-urlencoded;charset=UTF-8'},
                body: parametros
            }).then(response => response.json())
            .then(data => {
                console.log(data);
                if (data.error) {
                    alert(data.error);
                } else if (data.idArmazon) {
                    //alert("Armazon actualizado con id: "+data.idArmazon);
                } else {
                    JSON.stringify(data);
                }
                JSON.stringify(data);
            });

    Swal.fire({
        position: 'center',
        icon: 'success',
        title: 'Armazon eliminado exitosamente',
        showConfirmButton: false,
        timer: 1500
        
    });
     borrarTabla();
}

function agregar(i) {
    let idArmazon = i;
    const armazon = {idArmazon};

    let parametros = new URLSearchParams(armazon);
    fetch("../optik/api/restoptik/agregarArmazon",
            {
                method: "POST",
                headers: {'Content-Type': 'application/x-www-form-urlencoded;charset=UTF-8'},
                body: parametros
            }).then(response => response.json())
            .then(data => {
                console.log(data);
                if (data.error) {
                    alert(data.error);
                } else if (data.idArmazon) {
                    // alert("Empleado actualizado con id: "+data.idArmazon);
                } else {
                    JSON.stringify(data);
                }
                JSON.stringify(data);
            });

    Swal.fire({
        position: 'center',
        icon: 'success',
        title: 'Armazon agregada exitosamente',
        showConfirmButton: false,
        timer: 1500
    });
     borrarTabla();
}

function getAll() {
    let datos = {estatus: 1};
    let parametros = new URLSearchParams(datos);

    fetch('../optik/api/restoptik/getAllArmazon',
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
                    cargarTablaArmazon(null,data);
                }
            });
}
function getAllInactivos() {
    let datos = {estatus: 0};
    let parametros = new URLSearchParams(datos);

    fetch('../optik/api/restoptik/getAllArmazon',
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
                    cargarTablaArmazon(null,data);
                }
            });
}

function cargarTablaArmazon(coincidencias, data) {
    if(coincidencias){
        data = coincidencias;
    }else
    armazones = data;
    let contenido = "";
    data.forEach((armazon, index) =>{
        let nc = armazon.modelo + " " + armazon.color;
        let pr = armazon.producto.nombre + " " + armazon.producto.marca + " " + armazon.producto.existencias;
        contenido += "<tr>";
        contenido += "<td>" + nc + "</td>";
        contenido += "<td>" + pr + "</td>";
        contenido += "<td>" + armazon.descripcion + "</td>";
        contenido += "<td>" + armazon.dimensiones + "</td>";
        contenido += "<td><button type='button' class='btn btn-light m-3' onclick='cargarForm(" + index + ");'> VER </button></td>";
        if(armazon.estatus === 1){
            contenido += "<td><button type='button' class='btn btn-danger m-3' onclick='eliminar(" + armazon.idArmazon + ")'> Eliminar </button></td>";
        }else{
            contenido += "<td><button type='button' class='btn btn-success m-3' onclick='agregar(" + armazon.idArmazon + ")'> Activar </button></td>";
        }
        
        contenido += "<td><button type='button' class='btn btn-info m-3' onclick='loadFoto(" + index + ");'> Imagen </button></td>";
        contenido += "</tr>";
    });

    document.getElementById("tbArmazon").innerHTML = contenido;
}



function cargarForm(i) {

    document.getElementById("txtIdProducto").value = armazones[i].producto.idProducto;
    document.getElementById("txtIdArm").value = armazones[i].idArmazon;
    document.getElementById("txtModelo").value = armazones[i].modelo;
    document.getElementById("txtColor").value = armazones[i].color;
    document.getElementById("txtDimensiones").value = armazones[i].dimensiones;
    document.getElementById("txtDescripcion").value = armazones[i].descripcion;
    // document.getElementById("fileInput").value = armazones[i].fotografia;
    document.getElementById("txtCodigo").value = armazones[i].producto.codigoBarras;
    document.getElementById("txtNom").value = armazones[i].producto.nombre;
    document.getElementById("txtMarca").value = armazones[i].producto.marca;
    document.getElementById("txtPrecioCompra").value = armazones[i].producto.precioCompra;
    document.getElementById("txtPrecioVenta").value = armazones[i].producto.precioVenta;
    document.getElementById("txtExistencias").value = armazones[i].producto.existencias;

    loadFoto(armazones[i].fotografia);

}

function limpiarForm() {
    document.getElementById("txtIdProducto").value = "";
    document.getElementById("txtIdArm").value = "";
    document.getElementById("txtModelo").value = "";
    document.getElementById("txtColor").value = "";
    document.getElementById("txtDimensiones").value = "";
    document.getElementById("txtDescripcion").value = "";
    document.getElementById("fileInput").value = "";
    document.getElementById("txtCodigo").value = "";
    document.getElementById("txtNom").value = "";
    document.getElementById("txtMarca").value = "";
    document.getElementById("txtPrecioCompra").value = "";
    document.getElementById("txtPrecioVenta").value = "";
    document.getElementById("txtExistencias").value = "";
    document.getElementById("img").src = "";

}

function borrarTabla(){
    document.getElementById("tbArmazon").innerHTML = "";
}

// Converitr imagen a base64 para guardar en base de datos
const fileInput = document.getElementById("fileInput");

fileInput.addEventListener("change", e => {
    const file = fileInput.files[0];
    const reader = new FileReader();

    reader.addEventListener("load", () => {
        foto = reader.result;
        console.log(reader.result);
    });
    reader.readAsDataURL(file);
});

// intentar cargar la imagen

function loadFoto(i) {
    var image = new Image();
    image.src = armazones[i].fotografia;

    var w = window.open("");
    w.document.write(image.outerHTML);
}

function Rbusqueda(){
    const busqueda = document.getElementById("myInput").value;
    const coincidencias = [];
    
    for(let i=0; i < armazones.length; i++){
        const armazon = armazones[i];
        
        if(armazon.descripcion.toLowerCase().includes(busqueda.toLowerCase()) ||
                armazon.dimensiones.toLowerCase().includes(busqueda.toLowerCase())){
            coincidencias.push(armazon);
        }
    }
    console.table(coincidencias);
    cargarTablaArmazon(coincidencias, null);
}

