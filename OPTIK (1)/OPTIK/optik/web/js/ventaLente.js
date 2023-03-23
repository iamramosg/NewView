let clientes;
let examenesVista;
let tipoMicas;
let armazones;
let tratamientos;
let materiales;
let indexTV = 0;
let examenSeleccionado;
let micaSeleccionada;
let materialSeleccionado;
let armazonSeleccionado;
// Esta lista servira para saber que tratamientos han sido seleccionados
let tratamientosSeleccionados = [];
//esta es la lista donde vamos a meter toda las cosas objetos
let ventaPresupuestoLCList = [];

// get All Cliente y cargar tabla cliente
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
                    clientes = data;
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
        contenido += `<td class='text-end'><button type='button' class='btn btn-outline-success m-3 ms-auto' onclick='getAllExamenVista(); getAllArmazon(); getAllTratamiento(); getAllMateriales(); getAllTipoMica();'>+</button></td>`;
        //cuando le das al boton de arriba manda a llamar a getAllExamenVista
        contenido += "</tr>";
    });
    document.getElementById("tbCliente").innerHTML = contenido;
}

//get All examen vista 
function getAllExamenVista() {
    const tbclientes = document.getElementById("tbCliente").getElementsByTagName("tr")[indexTV];
    const idCliente = Number(tbclientes.getElementsByTagName("td")[0].textContent);
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
                    examenesVista = data;
                    // Obtener el select del HTML
                    const selectExamen = document.getElementById("slcExamenVista");
                    selectExamen.innerHTML = "";

                    // Crear opción predeterminada con el primer examen
                    const defaultOption = document.createElement("option");
                    defaultOption.text = "Seleccionar Examen";
                    if (examenesVista.length > 0) {
                        defaultOption.value = JSON.stringify(examenesVista[0]);
                        examenSeleccionado = examenesVista[0];
                    }
                    selectExamen.appendChild(defaultOption);

                    //Iterar sobre los objetos y agregar opciones al select 
                    examenesVista.forEach(examen => {
                        const option = document.createElement("option");
                        option.value = JSON.stringify(examen);
                        option.text = examen.fecha;
                        selectExamen.appendChild(option);
                    });

                    //agregar un evento onchange al select 
                    selectExamen.addEventListener("change", () => {
                        //obtener el valor seleccionado 
                        examenSeleccionado = JSON.parse(selectExamen.value);
                        console.log("el examen seleccionado es " + JSON.stringify(examenSeleccionado));
                    });
                }
            });
}
//get All tipo micas
function getAllTipoMica() {
    fetch("http://localhost:8080/optik/api/restoptik/getAllMica", {
        method: "POST",
        headers: {'Content-Type': 'application/x-www-form-urlencoded;charset=UTF-8'}
    })
            .then(response => response.json())
            .then(data => {
                console.log(data);
                if (data.error) {
                    alert(data.error);
                } else {
                    tipoMicas = data;
                    //obtener el select del HTML
                    const selectMica = document.getElementById("slcTipoMica");
                    selectMica.innerHTML = "";

                    //Crear opcion predeterminada con el primer tipo mica
                    const defaultOption = document.createElement("option");
                    defaultOption.text = "Seleccionar Mica";
                    if (tipoMicas.length > 0) {
                        defaultOption.value = JSON.stringify(tipoMicas[0]);
                        micaSeleccionada = tipoMicas[0];
                    }
                    selectMica.appendChild(defaultOption);

                    //iterar sobre los objetos y agregar las opciones al select
                    tipoMicas.forEach(mica => {
                        const option = document.createElement("option");
                        option.value = JSON.stringify(mica);
                        option.text = mica.nombre;
                        selectMica.appendChild(option);
                    });

                    //agregar un evento onchange al select
                    selectMica.addEventListener("change", () => {
                        //obtener el valor seleccionado
                        micaSeleccionada = JSON.parse(selectMica.value);
                    });
                }
            });
}

//getAll armazon
function getAllArmazon() {
    let datos = {estatus: 1};
    let parametros = new URLSearchParams(datos);

    fetch('http://localhost:8080/optik/api/restoptik/getAllArmazon',
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
                    armazones = data;
                    //obtener el select del HTML
                    const selectArmazon = document.getElementById("slcArmazon");
                    selectArmazon.innerHTML = "";
                    //Crear opción predeterminada en el primer armazon
                    const defaultOption = document.createElement("option");
                    defaultOption.text = "Seleccionar Armazon";
                    if (armazones.length > 0) {
                        defaultOption.value = JSON.stringify(armazones[0]);
                        armazonSeleccionado = armazones[0];
                    }
                    selectArmazon.appendChild(defaultOption);

                    //Iterar sobre los objetos y agregar opciones al select
                    armazones.forEach(armazon => {
                        const option = document.createElement("option");
                        option.value = JSON.stringify(armazon);
                        option.text = armazon.producto.nombre;
                        selectArmazon.appendChild(option);
                    });

                    //agregar un evento onchange al select
                    selectArmazon.addEventListener("change", () => {
                        //obtener el valor seleccionado
                        armazonSeleccionado = JSON.parse(selectArmazon.value);
                    });
                }
            });
}

//getAll tratamientos
function getAllTratamiento() {
    let datos = {estatus: 1};
    let parametros = new URLSearchParams(datos);

    fetch('http://localhost:8080/optik/api/restoptik/getAllTratamiento', {
        method: "POST",
        headers: {'Content-Type': 'application/x-www-form-urlencoded;charset=UTF-8'},
        body: parametros
    })
    .then(response => response.json())
    .then(data => {
        if (data.error) {
            alert(data.error);
        } else {
            let contenedorTratamientos = document.getElementById("contenedorTratamientos");
            contenedorTratamientos.innerHTML = "";
            tratamientos = data;

            // Iteramos sobre la lista de objetos para crear los checkboxes
            for (let i = 0; i < tratamientos.length; i++) {
                // Creamos el elemento input de tipo checkbox
                let checkbox = document.createElement('input');
                checkbox.classList.add('form-check-input');
                checkbox.type = 'checkbox';

                // Establecemos el valor del atributo "value" del checkbox como el nombre del objeto
                checkbox.value = tratamientos[i]['nombre'];

                // Creamos una etiqueta para el checkbox
                let label = document.createElement('label');
                label.classList.add('form-check-label');
                label.innerHTML = tratamientos[i]['nombre'];

                // Creamos un div para agrupar el checkbox y la etiqueta
                let div = document.createElement('div');
                div.classList.add('form-check', 'form-check-inline');
                
                // Agregamos el checkbox y la etiqueta al div
                div.appendChild(checkbox);
                div.appendChild(label);

                // Agregamos el div al contenedor de tratamientos
                contenedorTratamientos.appendChild(div);

                // Agregamos un evento "change" al checkbox para detectar cuando se selecciona o se deselecciona
                checkbox.addEventListener('change', function () {
                    // Si el checkbox está seleccionado, agregamos el tratamiento a la lista de tratamientos seleccionados
                    if (this.checked) {
                        tratamientosSeleccionados.push(tratamientos[i]);
                    } else {
                        // Si el checkbox está deseleccionado, removemos el tratamiento de la lista de tratamientos seleccionados
                        let index = tratamientosSeleccionados.indexOf(tratamientos[i]);
                        if (index > -1) {
                            tratamientosSeleccionados.splice(index, 1);
                        }
                    }

                });
            }
        }
    });
}


//getAll materiales
function getAllMateriales() {
    let datos = {estatus: 1};
    let parametros = new URLSearchParams(datos);

    fetch('http://localhost:8080/optik/api/restoptik/getAllM',
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
                    materiales = data;
                    const selectMaterial = document.getElementById("slcMaterial");
                    selectMaterial.innerHTML = "";
                    const defaultOption = document.createElement("option");
                    defaultOption.text = "Selecciona Material";

                    if (materiales.length > 0) {
                        defaultOption.value = JSON.stringify(materiales[0]);
                        materialSeleccionado = materiales[0];
                    }
                    selectMaterial.appendChild(defaultOption);
                    materiales.forEach(material => {
                        const option = document.createElement("option");
                        option.value = JSON.stringify(material);
                        option.text = material.nombre;
                        selectMaterial.appendChild(option);
                    });

                    //agregar un evento onchange al select
                    selectMaterial.addEventListener("change", () => {
                        //obtener el valor seleccionado
                        materialSeleccionado = JSON.parse(selectMaterial.value);
                    });
                }
            });
}




//  Funcion que agrega las cosas a una lista de una tabla
function agregarVenta() {

    try {
        let cantidad = document.getElementById("txtCantidad").value;
        let oblea = document.getElementById("txtOblea").value;
        let descuento = document.getElementById("txtDescuento").value;
//    let costo = document.getElementById("txtCosto").value;
        let precio = calcularPrecio();

        let contenidoTabla = "";

        contenidoTabla += "<tr>";
        contenidoTabla += "<td>" + clientes[0].persona.nombre + "</td>";
        contenidoTabla += "<td>" + oblea + "</td>";
        contenidoTabla += "<td>" + precio + "</td>";
        contenidoTabla += "<td>" + cantidad + "</td>";
        contenidoTabla += "<td>" + descuento + "</td>";
        contenidoTabla += "</tr>";
        document.getElementById("tbVenta").innerHTML += contenidoTabla;


        let input = document.getElementById("txtTotal");
        let valor = input.value.trim(); // Eliminamos los espacios en blanco al principio y al final del valor
        if (valor === "") {
            valor = 0; // Si el valor es una cadena vacía, lo establecemos como 0
        }
        let resultado = parseInt(valor) + precio; // Convertimos el valor a entero y lo sumamos con el número
        input.value = resultado; // Asignamos el resultado al valor del input



        if (guardarRegistro(precio)) {
            limpiar();
        }
    } catch (error) {
        console.log(error);
    }


}

function calcularPrecio() {

    let input = document.getElementById("txtDescuento");
    let c = document.getElementById("txtCantidad");
    let valor = input.value; // Eliminamos los espacios en blanco al principio y al final del valor
    if (valor === "") {
        valor = 0; // Si el valor es una cadena vacía, lo establecemos como 0
    }
    valor = (100 - parseInt(valor))/100;

    // Suma de tipomica, material, armazón, tratamiento segun jonathan
    let result = materialSeleccionado.precioVenta + micaSeleccionada.precioVenta + armazonSeleccionado.producto.precioVenta;

    for (var i = 0; i < tratamientosSeleccionados.length; i++) {
        result += tratamientosSeleccionados[i].precioVenta;
    }
    
    let valor2 = c.value; // Eliminamos los espacios en blanco al principio y al final del valor
    if (valor2 === "") {
        valor2 = 1; // Si el valor es una cadena vacía, lo establecemos como 0
    }
    
    result = (result * valor)*parseInt(valor2);
    return result;
}

function guardarRegistro(precio) {

    let r = false;
    try {
        var randomNum = Math.round(Math.random() * 100000000);
        var clave = "OQ-" + randomNum;

        //-------Conseguimos objeto examenVista (Solo campos importantes)-------------------------------------------
        var idExamenVista = examenSeleccionado.idExamenVista;
        var graduacion = examenSeleccionado.graduacion;

        let examenVista = {idExamenVista: idExamenVista, graduacion: graduacion};

        //-------Conseguimos objeto presupuesto -------------------------------------------
        let presupuesto = {examenVista: examenVista, clave: clave};

        //-------Conseguimos objeto presupuestoLentes -------------------------------------------
        var alturaOblea = document.getElementById("txtOblea").value;

        let presupuestoLente = {alturaOblea: alturaOblea, presupuesto: presupuesto, tipoMica: micaSeleccionada, material: materialSeleccionado, armazon: armazonSeleccionado, listTratamientos: tratamientosSeleccionados};

        //-------Conseguimos objeto VentaPresupuestoLC -------------------------------------------
        var cantidad = document.getElementById("txtCantidad").value;
        var precioUnitario = precio;
        var descuento = document.getElementById("txtDescuento").value;

        let ventaPresupuestoLC = {presupuestoLente: presupuestoLente, cantidad: cantidad, precioUnitario: precioUnitario, descuento: descuento};

        ventaPresupuestoLCList.push(ventaPresupuestoLC);

        r = true;
    } catch (error) {
        console.log(error);
        return r;
    }
    return r;
}

function limpiar() {
    clientes = null;
    examenesVista = null;
    tipoMicas = null;
    armazones = null;
    tratamientos = null;
    materiales = null;
    indexTV = 0;
    examenSeleccionado = null;
    micaSeleccionada = null;
    materialSeleccionado = null;
    armazonSeleccionado = null;
// Esta lista servira para saber que tratamientos han sido seleccionados
    tratamientosSeleccionados = [];

    document.getElementById("txtCantidad").value = "";
    document.getElementById("txtDescuento").value = "";
    document.getElementById("txtOblea").value = "";

    document.getElementById("tbCliente").innerHTML = "";
    document.getElementById("contenedorTratamientos").innerHTML = "";
    document.getElementById("myInput").value = "";
    document.getElementById("slcExamenVista").innerHTML = "";
    document.getElementById("slcTipoMica").innerHTML = "";
    document.getElementById("slcMaterial").innerHTML = "";
    document.getElementById("slcArmazon").innerHTML = "";

}

function realizarVenta() {
    //-------Conseguimos objeto venta-------------------------------------------
    var randomNum = Math.round(Math.random() * 100000000);
    var clave = "OQ-" + randomNum;
    var emp = JSON.parse(localStorage.getItem('currentUser'));

    let venta = {clave: clave, empleado: emp};

    //-------Hacer objeto DetalleVentaPresupuesto -------------------------------------------
    let detalleVentaPresupuesto = {datos: JSON.stringify({venta: venta, listaVentaPresupuestoL: ventaPresupuestoLCList})};

    let parametros = new URLSearchParams(detalleVentaPresupuesto);
    fetch("http://localhost:8080/optik/api/restoptik/ventaLente",
            {
                method: "POST",
                headers: {'Content-Type': 'application/x-www-form-urlencoded;charset=UTF-8'},
                body: parametros
            }).then(response => response.json())
            .then(data => {
                console.log(data);
                if (data.error) {
                    alert(data.error);
                } else {
                    document.getElementById("txtTotal").value = "";
                    Swal.fire({
                        position: 'center',
                        icon: 'success',
                        title: 'Venta Realizada exitosamente',
                        showConfirmButton: false,
                        timer: 1500
                    });
                    document.getElementById("tbVenta").innerHTML = "";
                    limpiar();
                    ventaPresupuestoLCList = [];
                }

            });
}

function validarDescuento(input) {
  const valor = parseInt(input.value);
  if (isNaN(valor) || valor < 0 || valor > 100) {
    alert("El descuento debe ser un número entre 0 y 100.");
    input.value = "";
  }
}


function validarEntero(input) {
  var valor = input.value;
  if (!/^\d+$/.test(valor)) {
    alert("Por favor ingrese un número entero.");
    input.value = "";
  }
}
