let clientes;
let examenes;
let examenSeleccionado;
let lenteSeleccionado;
let productos;
let lentes;
let indexTV = 0;

function getAllCliente() {
    const busqueda = document.getElementById("myInput").value;
    let datos = {filtro: busqueda};
    let parametros = new URLSearchParams(datos);

    fetch("http://localhost:8080/optik/api/restoptik/getAllCliente2", {
        method: "POST",
        body: parametros,
        headers: {'Content-Type': 'application/x-www-form-urlencoded;charset=UTF-8'}
    })
            .then(response => response.json())
            .then(data => {
                console.log(data);
                if (data.error) {
                    alert(data.error);
                } else {
                    cargarTablaClientes(data);
                }
            });
}

function cargarTablaClientes(data) {
    clientes = data;
    let contenido = "";
    data.forEach((cliente, indice) => {
        let nc = cliente.persona.nombre + " " + cliente.persona.apellidoPaterno + " " + cliente.persona.apellidoMaterno;
        let dc = cliente.persona.calle + " " + cliente.persona.numero + " " + cliente.persona.colonia + " " + cliente.persona.cp + " " + cliente.persona.ciudad + " " + cliente.persona.estado;

        contenido += "<tr>";
        contenido += "<td style='display:none;'>" + cliente.idCliente + "</td>"; //5
        contenido += "<td>" + nc + "</td>";
        contenido += "<td>" + dc + "</td>";
        contenido += "<td>" + cliente.persona.email + "</td>";
        contenido += `<td class='text-end'><button type='button' class='btn btn-outline-success m-3 ms-auto' onclick='getAllExamenVista()'>Agregar</button></td>`;
        contenido += "</tr>";
    });
    document.getElementById("tbProducto").innerHTML = contenido;
}

function limpiarTablaProducto() {
    document.getElementById("tbProducto").innerHTML = "";
}
function getAllExamenVista() {
    const productos2 = document.getElementById("tbProducto").getElementsByTagName("tr")[indexTV];
    const idCliente = Number(productos2.getElementsByTagName("td")[0].textContent);
    let datos = {id: idCliente};
    let parametros = new URLSearchParams(datos);

    fetch("http://localhost:8080/optik/api/restoptik/getAllExamenVista", {
        method: "POST",
        body: parametros,
        headers: {'Content-Type': 'application/x-www-form-urlencoded;charset=UTF-8'}
    })
            .then(response => response.json())
            .then(data => {
                console.log(data);
                if (data.error) {
                    alert(data.error);
                } else {
                    cargarTablaExamen(data);
                }
            });
}


function cargarTablaExamen(data) {
    examenes = data;
    let contenido = "";
    data.forEach((examen, indice) => {

        contenido += "<tr>";
        contenido += "<td>" + examen.clave + "</td>";
        contenido += "<td>" + examen.fecha + "</td>";
        contenido += "<td style='display:none;'>" + examen.empleado.idEmpleado + "</td>";
        contenido += "<td style='display:none;'>" + examen.Cliente.idCliente + "</td>";
        contenido += "<td style='display:none;'>" + examen.graduacion.idGraduacion + "</td>";
        contenido += "<td style='display:none;'>" + examen.idExamenVista + "</td>";
        contenido += `<td class='text-end'><button type='button' class='btn btn-outline-success m-3 ms-auto' onclick='getAllLenteContacto(); obtenerExamen(${indice})'>Seleccionar</button></td>`;
        contenido += "</tr>";
    });
    document.getElementById("tbProducto2").innerHTML = contenido;
}

function getAllLenteContacto() {
    fetch("http://localhost:8080/optik/api/restoptik/getAllLenteContacto", {
        method: "POST",
        headers: {'Content-Type': 'application/x-www-form-urlencoded;charset=UTF-8'}
    })
            .then(response => response.json())
            .then(data => {
                console.log(data);
                if (data.error) {
                    alert(data.error);
                } else {
                    cargarTablaLenteContacto(data);
                }
            });
}


function cargarTablaLenteContacto(data) {
    lentes = data;
    console.log(lentes);
    let contenido = "";
    data.forEach((lente, indice) => {

        contenido += "<tr>";
        contenido += "<td>" + lente.producto.nombre + "</td>";
        contenido += "<td>" + lente.producto.precioVenta + "</td>";
        contenido += "<td style='display:none;'>" + lente.idLenteContacto + "</td>";
        contenido += "<td style='display:none;'>" + lente.producto.idProducto + "</td>";
        contenido += "<td style='display:none;'>" + lente.keratometria + "</td>";
        contenido += "<td style='display:none;'>" + lente.fotografia + "</td>";
        contenido += `<td class='text-end'><button type='button' class='btn btn-outline-success m-3 ms-auto' onclick='agregar(${indice}); obtenerLente(${indice})'>Agregar</button></td>`;
        contenido += "</tr>";
    });
    document.getElementById("tbProducto3").innerHTML = contenido;
}

//funciones para venta 
function agregar(indice) {
    let lente = lentes[indice];
    let existencias = lente.producto.existencias;
    let renglon = "";
    renglon += "<tr>";
    renglon += "<td>" + lente.producto.nombre + "</td>";
    renglon += "<td>" + lente.producto.precioVenta + "</td>";
    renglon += "<td><input type='number' value='1' id='txtCantidad" + indexTV + "'onchange='validarCantidad(this.value, " + existencias + ") && calcularTotal()'></td>";
    renglon += "<td><input type='number' value='0' min='0' max='100' id='txtDescuento" + indexTV + "' onblur='validarDescuento(this.value) && calcularTotal()'>%</td>";
    renglon += "<td style='display:none;'>" + lente.producto.idProducto + "</td>"; //1
    renglon += "<td style='display:none;'>" + lente.producto.codigoBarras + "</td>"; //2
    renglon += "<td style='display:none;'>" + lente.producto.nombre + "</td>"; //3
    renglon += "<td style='display:none;'>" + lente.producto.marca + "</td>"; //4
    renglon += "<td style='display:none;'>" + lente.producto.precioCompra + "</td>"; //5
    renglon += "<td style='display:none;'>" + lente.precioVenta + "</td>"; //6
    renglon += "<td style='display:none;'>" + lente.existencias + "</td>"; //7

    renglon += "</tr>";

    document.getElementById("tbProducto4").innerHTML += renglon;
    console.log("Producto agregado a tbProducto4: " + renglon);
    indexTV++;
    calcularTotal();
}


function calcularTotal() {
    let total = 0;
    const productos2 = document.getElementById("tbProducto4").getElementsByTagName("tr");
    for (let i = 0; i < productos2.length; i++) {
        const precioVenta = Number(productos2[i].getElementsByTagName("td")[1].textContent);
        const cantidad = Number(document.getElementById(`txtCantidad${i}`).value);
        const descuento = Number(document.getElementById(`txtDescuento${i}`).value);
        const descuentoDecimal = descuento / 100;
        total += precioVenta * cantidad * (1 - descuentoDecimal);
    }
    document.getElementById("txtTotal").value = total;
}

function validarCantidad(cantidad, existencias) {
    const valor = parseInt(cantidad);
    if (isNaN(valor) || valor <= 0) {
        alert("La cantidad debe ser un número entero y positivo.");
        return false;
    }
    if (valor > existencias) {
        alert("La cantidad no puede ser mayor que la cantidad existente del producto.");
        return false;
    }
    return true;
}

function validarDescuento(descuento) {
    const valor = parseInt(descuento);
    if (isNaN(valor) || valor < 0 || valor > 100) {
        alert("El descuento debe ser un número entre 0 y 100.");
        return false;
    }
    return true;
}

function generarVentaPresupuesto() {
    let listaVP = [];

    //armamos el objeto de venta
    const randomNum = Math.round(Math.random() * 100000000);
    let clave = "OQ-" + randomNum;
    let claveP = "OQ-" + randomNum;
    let claveL = "OQ-" + randomNum;
    var emp = JSON.parse(localStorage.getItem('currentUser'));
    let venta = {clave: clave, empleado: emp};

    //armamos un objeto de presupuesto 
    let presupuesto = {examenVista: examenSeleccionado, clave: claveP};

    //armamos un objeto de presupuestoLenteContacto
    let presupuestoLenteContacto = {LenteContacto: lenteSeleccionado, clave: claveL, presupuesto: presupuesto};
    console.log(presupuestoLenteContacto);


    const ventaPresupuesto = document.getElementById("tbProducto4").getElementsByTagName("tr");
    for (let i = 0; i < ventaPresupuesto.length; i++) {
        const cantidad = Number(document.getElementById(`txtCantidad${i}`).value);
        const precioUnitario = Number(ventaPresupuesto[i].getElementsByTagName("td")[1].textContent);
        const descuento = Number(document.getElementById(`txtDescuento${i}`).value);
        //armamos un objeto venta presupuesto lentes contacto 
        let listaVentaPresupuestoLC = {presupuestoLenteContacto: presupuestoLenteContacto, cantidad: cantidad, precioUnitario: precioUnitario, descuento: descuento};
        listaVP.push(listaVentaPresupuestoLC);
    }
    const detalleVentaPresupuesto = {datos: JSON.stringify({venta: venta, listaVentaPresupuestoLC: listaVP})};

    let parametros = new URLSearchParams(detalleVentaPresupuesto);
    fetch('http://localhost:8080/optik/api/restoptik/ventaLC',
            {
                method: "POST",
                headers: {'Content-Type': 'application/x-www-form-urlencoded;charset=UTF-8'},
                body: parametros
            })
            .then(response => response.json())
            .then(data => {
                if (data.error) {
                    alert(data.error);
                } else if (data.result) {
                    limpiarE();
                    Swal.fire({
                        position: 'center',
                        icon: 'success',
                        title: 'Venta Realizada exitosamente',
                        showConfirmButton: false,
                        timer: 1500
                    });
                } else {
                    JSON.stringify(data);
                }
            });




}

function obtenerExamen(indice) {
    let fila = document.getElementById("tbProducto2").getElementsByTagName("tr")[indice];
    let celdas = fila.getElementsByTagName("td");
    let clave = String(celdas[0].innerHTML);
    let fecha = String(celdas[1].innerHTML);
    let idEmpleado = Number(celdas[2].innerHTML);
    let idCliente = Number(celdas[3].innerHTML);
    let idGraduacion = Number(celdas[4].innerHTML);
    let idExamenVista = Number(celdas[5].innerHTML);
    let emp = {idEmpleado: idEmpleado};
    let cli = {idCliente: idCliente};
    let gra = {idGraduacion: idGraduacion};
    let examenVista = {idExamenVista: idExamenVista, clave: clave, empleado: emp, Cliente: cli, graduacion: gra, fecha: fecha};
    examenSeleccionado = examenVista;

}

function obtenerLente(indice) {
    let fila = document.getElementById("tbProducto3").getElementsByTagName("tr")[indice];
    let celdas = fila.getElementsByTagName("td");
    let idLenteContacto = Number(celdas[2].innerHTML);
    let idProducto = Number(celdas[3].innerHTML);
    let keratometria = Number(celdas[4].innerHTML);
    let fotografia = String(celdas[5].innerHTML);
    let pro = {idProducto: idProducto};
    console.log("el id lenteContacto es " + idLenteContacto);
    console.log("el id producto lenteContacto es " + idProducto);
    console.log("la keratometria lenteContacto es " + keratometria);
    console.log("la fotografia es " + fotografia);
    let LenteContacto = {idLenteContacto: idLenteContacto, producto: pro, keratometria: keratometria, fotografia: fotografia};

    lenteSeleccionado = LenteContacto;
    console.log(LenteContacto);

}

window.addEventListener('load', function () {
    document.getElementById('txtTotal').value = '';
});

function limpiarE() {
    document.getElementById("tbProducto").innerHTML = "";
    document.getElementById("tbProducto2").innerHTML = "";
    document.getElementById("tbProducto3").innerHTML = "";
    document.getElementById("tbProducto4").innerHTML = "";
    clientes = null;
    examenes = null;
    examenSeleccionado = null;
    lenteSeleccionado = null;
    productos = null;
    lentes = null;
    indexTV = 0;
    document.getElementById("txtTotal").value = "";
    document.getElementById("myInput").value = "";

}
