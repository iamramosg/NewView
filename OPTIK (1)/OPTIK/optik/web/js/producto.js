let productos;
let indexTV = 0;

function getAllProducto() {
  const busqueda = document.getElementById("myInput").value;
  let datos = {estatus: busqueda};
  let parametros = new URLSearchParams(datos);

  fetch("../optik/api/restoptik/getAllProducto", {
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
      cargarTablaProducto(data);
    }
  });
}

function cargarTablaProducto(data) {
  productos = data;
  let contenido = "";
  data.forEach((producto, indice) => {
    contenido += "<tr>";
    contenido += "<td>" + producto.codigoBarras + "</td>";
    contenido += "<td>" + producto.nombre + "</td>";
    contenido += "<td>" + producto.precioVenta + "</td>";
    contenido += `<td><button type='button' class='btn btn-light m-3' onclick='agregar(${indice})'>Agregar</button></td>`;
    contenido += "</tr>";
  });
  document.getElementById("tbProducto").innerHTML = contenido;
}

function limpiarTablaProducto() {
  document.getElementById("tbProducto").innerHTML = "";
}

function agregar(indice) {
  let producto = productos[indice];
  let existencias = producto.existencias;
  console.log("Cantidad Existente: " + existencias);
  let renglon = "";
  renglon += "<tr>";
  renglon += "<td>" + producto.nombre + "</td>";
  renglon += "<td>" + producto.precioVenta + "</td>";
  renglon += "<td><input type='number' value='1' id='txtCantidad" +indexTV+ "'onchange='validarCantidad(this.value, " + existencias + ") && calcularTotal()'></td>";
  renglon += "<td><input type='number' value='0' min='0' max='100' id='txtDescuento" + indexTV + "' onblur='validarDescuento(this.value) && calcularTotal()'>%</td>";
  renglon += "<td style='display:none;'>" + producto.idProducto + "</td>"; //1
  renglon += "<td style='display:none;'>" + producto.codigoBarras + "</td>"; //2
  renglon += "<td style='display:none;'>" + producto.nombre + "</td>"; //3
  renglon += "<td style='display:none;'>" + producto.marca + "</td>"; //4
  renglon += "<td style='display:none;'>" + producto.precioCompra + "</td>"; //5
  renglon += "<td style='display:none;'>" + producto.precioVenta + "</td>"; //6
  renglon += "<td style='display:none;'>" + producto.existencias + "</td>"; //7
  
  renglon += "</tr>";

  document.getElementById("tbProducto2").innerHTML += renglon;
  console.log("Producto agregado a tbProducto2: " + renglon);
  indexTV++;
  calcularTotal();
}

function calcularTotal() {
  let total = 0;
  const productos2 = document.getElementById("tbProducto2").getElementsByTagName("tr");
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
  console.log(valor);
  console.log(existencias);
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

function generarVenta() {
    let listaVP = [];
    const randomNum = Math.round(Math.random() * 100000000);
    let clave = "OQ-" + randomNum;
    var emp = JSON.parse(localStorage.getItem('currentUser'));
    let venta = {clave: clave, empleado: emp};


    // Agregar los productos a detalleVentaProducto
    const productos2 = document.getElementById("tbProducto2").getElementsByTagName("tr");
    for (let i = 0; i < productos2.length; i++) {
        const idProducto = Number(productos2[i].getElementsByTagName("td")[4].textContent);
        const codigoBarras = String(productos2[i].getElementsByTagName("td")[5].textContent);
        const nombre = String(productos2[i].getElementsByTagName("td")[6].textContent);
        const marca = String(productos2[i].getElementsByTagName("td")[7].textContent);
        const precioCompra = Number(productos2[i].getElementsByTagName("td")[8].textContent);
        const precioVenta2 = Number(productos2[i].getElementsByTagName("td")[9].textContent);
        const existencias = Number(productos2[i].getElementsByTagName("td")[10].textContent);
        const estatus = 0;
        
        
        const producto = {idProducto: idProducto, codigoBarras: codigoBarras, nombre: nombre, marca: marca, precioCompra: precioCompra, precioVenta: precioVenta2, existencias: existencias, estatus:estatus};
        console.log(producto);
        const cantidad = Number(document.getElementById(`txtCantidad${i}`).value);
        const precioVenta = Number(productos2[i].getElementsByTagName("td")[1].textContent);
        const descuento = Number(document.getElementById(`txtDescuento${i}`).value);

        let ventaP = {cantidad: cantidad, precioUnitario: precioVenta, descuento: descuento, producto: producto};
        listaVP.push(ventaP);
    }

    // Convertir detalleVentaProducto a JSON
    //const jsonVenta = JSON.stringify(detalleVentaProducto);
    const detalleVentaProducto = {datos: JSON.stringify({venta: venta, listaVP: listaVP})};

    let parametros = new URLSearchParams(detalleVentaProducto);
    fetch('../optik/api/restoptik/ventaProducto',
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
                    alert(data.result);
                } else {
                    JSON.stringify(data);
                }
            });

}

