//Para gusradar la lista de clientes al obtenerlos del REST
let clientes;
//Definir globalmente para saber que cliente esta seleccionado en todos lados
const selectClientes = document.getElementById('selectClientes');
//Aqui vamos a poner los examenes de vista del cliente seleccionado
let examenesVista;

//Seleccionamos el select donde colocaremos los examenes del cliente
const selectExamenesVista = document.getElementById('selectExamenesVista');

//Para guardar la lista de armazones al obtenerlos del REST
let armazones;
//Definir globalmente para saber que cliente esta seleccionado en todos lados
const selectArmazones = document.getElementById('selectArmazones');

//Para guardar la lista de materiales al obtenerlos del REST
let materiales;
//Definir globalmente para saber que materiales esta seleccionado en todos lados
const selectMateriales = document.getElementById('selectMateriales');

//Para guardar la lista de tipoMicas al obtenerlos del REST
let tipoMicas;
//Definir globalmente para saber que tipoMicas esta seleccionado en todos lados
const selectTipoMicas = document.getElementById('selectTipoMicas');

//Para guardar la lista de tratamientos al obtenerlos del REST
let tratamientos = [];

// Obtenemos la referencia al contenedor donde queremos agregar los checkboxes
const contenedorTratamientos = document.getElementById('contenedorTratamientos');

//esta es la lista donde vamos a meter toda las cosas
let ventaPresupuestoLCList = [];

//Boton que agrega los selects a la tabla de venta
const btnAgregar = document.getElementById('btnAgregar');

// Esta lista servira para saber que tratamientos han sido seleccionados
let tratamientosSeleccionados = [];



//Funcion para poner los clientes en un select al cargar la vista(Esta funcion debe de ir dentro de la etiqueta <body onload="initializeTodoALV()">)
function initializeTodoALV() {

    //-----Cargar Select Cliente---------------------------------------------------------------------------------------------------

    //Consumir REST para obtener los clientes y colocarlos en el selectClientes al cargar pag
    let datos = {estatus: 1};
    let parametros = new URLSearchParams(datos);

    fetch('http://localhost:8080/OpticaWeb/api/cliente/getAllCliente',
            {
                method: "POST",
                headers: {'Content-Type': 'application/x-www-form-urlencoded;charset=UTF-8'},
                body: parametros
            })
            .then(response => response.json())
            .then(dataC => {

                if (dataC.error) {
                    alert(dataC.error);
                } else {
                    //Igualamos la variable clientes con los clientes que devuelve el servicio
                    clientes = dataC;
                    //Agregamos los clientes al select con un for
                    for (var i = 0; i < dataC.length; i++) {
                        var optionC = document.createElement("option");
                        optionC.text = dataC[i].persona.nombre;
                        //Le colocamos a cada opcion un indice que va a ubicar a cada cliente en la lista de clientes
                        optionC.value = i;
                        selectClientes.add(optionC);
                    }
                }
            });
    //-----Cargar Select Armazon---------------------------------------------------------------------------------------------------

    parametros = new URLSearchParams({estatus: 1}); // Reutilizamos la variable parametros

    fetch('http://localhost:8080/OpticaWeb/api/armazon/getAllArmazon',
            {
                method: "POST",
                headers: {'Content-Type': 'application/x-www-form-urlencoded;charset=UTF-8'},
                body: parametros
            })
            .then(response => response.json())
            .then(dataA => {

                if (dataA.error) {
                    alert(dataA.error);
                } else {
                    //Igualamos la variable armazones con los armazones que devuelve el servicio
                    armazones = dataA;
                    //Agregamos los armazones al select con un for
                    for (var i = 0; i < dataA.length; i++) {
                        var optionA = document.createElement("option");
                        optionA.text = dataA[i].persona.nombre;
                        //Le colocamos a cada opcion un indice que va a ubicar a cada armazon en la lista de ARMAZONES
                        optionA.value = i;
                        selectArmazones.add(optionA);
                    }
                }
            });

    //-----Cargar Select tipoMicas---------------------------------------------------------------------------------------------------

    let url = 'http://localhost:8080/reddit/api/micas/getTipoMicas';
    fetch(url)
            .then(response => response.json())
            .then(dataTP => {
                if (dataTP.error) {
                    alert(dataTP.error);
                } else {
                    //Igualamos la variable materiales con los armazones que devuelve el servicio
                    tipoMicas = dataTP;
                    //Agregamos los materiales al select con un for
                    for (var i = 0; i < dataTP.length; i++) {
                        var optionTP = document.createElement("option");
                        optionTP.text = dataTP[i].persona.nombre;
                        //Le colocamos a cada opcion un indice que va a ubicar a cada materiales en la lista de materiales
                        optionTP.value = i;
                        selectTipoMicas.add(optionTP);
                    }
                }

            });

    //-----Cargar Checkboxs TRATAMIENTOS---------------------------------------------------------------------------------------------------

    parametros = new URLSearchParams({estatus: 1}); // Reutilizamos la variable parametros

    fetch('http://localhost:8080/OpticaWeb/api/tratamientos/getAllTratamientos',
            {
                method: "POST",
                headers: {'Content-Type': 'application/x-www-form-urlencoded;charset=UTF-8'},
                body: parametros
            })
            .then(response => response.json())
            .then(dataT => {

                if (dataT.error) {
                    alert(dataT.error);
                } else {
                    tratamientos = dataT;

                    // Iteramos sobre la lista de objetos para crear los checkboxes
                    for (let i = 0; i < tratamientos.length; i++) {
                        // Creamos el elemento input de tipo checkbox
                        let checkbox = document.createElement('input');
                        checkbox.type = 'checkbox';

                        // Establecemos el valor del atributo "value" del checkbox como el nombre del objeto
                        checkbox.value = tratamientos[i]['nombre'];

                        // Creamos una etiqueta para el checkbox
                        let label = document.createElement('label');
                        label.innerHTML = tratamientos[i]['nombre'];

                        // Agregamos el checkbox y la etiqueta al contenedor
                        contenedorTratamientos.appendChild(checkbox);
                        contenedorTratamientos.appendChild(label);

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

//Al cambiar el valor del select cliente se cargaran los examenes de vista de ese cliente
selectClientes.addEventListener('change', () => {


    //Seleccionamos el indice de cliente para saber a que cliente de la lista queremos
    let indiceCliente = selectClientes.value;
    //Seleccionamos el id del cliente que queremos con el indice ya que clientes es una lista
    let idCliente = clientes[indiceCliente].idCliente;

    let parametros = new URLSearchParams(idCliente);

    fetch("http://localhost:8080/OpticaWeb/api/examenVista/getExamenVista",
            {
                method: "POST",
                headers: {'Content-Type': 'application/x-www-form-urlencoded;charset=UTF-8'},
                body: parametros
            }).then(response => response.json())
            .then(dataE => {
                console.log(dataE);
                if (dataE.error) {
                    alert(dataE.error);
                } else {
                    //Hacemos que la lista de examenes de vista que regrese se ponga en nuestra lista examenesVista
                    examenesVista = dataE;

                    for (var i = 0; i < dataE.length; i++) {
                        var option = document.createElement("option");
                        option.text = dataE[i].clave;
                        //Le colocamos a cada opcion un indice que va a ubicar a cada examen en la lista de examenes
                        option.value = i;
                        selectExamenesVista.add(option);
                    }
                }
            });

});

//  Funcion que agrega las cosas a una lista de una tabla
function agregarVenta() {

    let cantidad = document.getElementById("txtCantidad").value;
    let costo = document.getElementById("txtCosto").value;
    let contenidoTabla = "";

    contenidoTabla += "<tr>";
    contenidoTabla += "<td>" + clientes[selectClientes.value].persona.nombre + "</td>";
    contenidoTabla += "<td>" + armazones[selectArmazones.value].modelo + "</td>";
    contenidoTabla += "<td>" + cantidad + "</td>";
    contenidoTabla += "<td>" + costo + "</td>";
    contenidoTabla += "<td>" + examenesVista[selectExamenesVista.value].clave + "</td>";
    contenidoTabla += "</tr>";
    document.getElementById("tbVenta").innerHTML += contenidoTabla;

}



//Funcion para meter cada campo a un objeto y el objeto agregarlo a la lista de objs
btnAgregar.addEventListener('click', function () {

    //-------Conseguimos objeto examenVista (Solo campos importantes)-------------------------------------------
    var idExamenVista = examenesVista[selectExamenesVista.value].idExamenVista;
    var graduacion = examenesVista[selectExamenesVista.value].graduacion;

    let examenVista = {idExamenVista: idExamenVista, graduacion: graduacion};

    //-------Conseguimos objeto presupuesto -------------------------------------------
    var clave = "OQ-" + randomNum;
    let presupuesto = {examenVista: examenVista, clave: clave};

    //-------Conseguimos objeto presupuestoLentes -------------------------------------------
    var alturaOblea = document.getElementById("txtAlturaOblea").value;

    let presupuestoLente = {alturaOblea: alturaOblea, presupuesto: presupuesto, tipoMica: tipoMicas[selectTipoMicas.value], material: materiales[selectMateriales.value], armazon: armazones[selectArmazones.value], tratamientos: tratamientosSeleccionados};

    //-------Conseguimos objeto VentaPresupuestoLC -------------------------------------------
    var cantidad = document.getElementById("txtCantidad").value;
    var precioUnitario = document.getElementById("txtPrecioUnitario").value;
    var descuento = document.getElementById("txtDescuento").value;

    let ventaPresupuestoLC = {presupuestoLente: presupuestoLente, cantidad: cantidad, precioUnitario: precioUnitario, descuento: descuento};

    ventaPresupuestoLCList.push(ventaPresupuestoLC);
});

//Realizar la venta y llamar al rest para que la registre
function realizarVenta() {

    //-------Conseguimos objeto venta-------------------------------------------
    var randomNum = Math.round(Math.random() * 100000000);
    var clave = "OQ-" + randomNum;
    var emp = JSON.parse(localStorage.getItem('currentUser'));

    let venta = {clave: clave, empleado: emp};

    //-------Hacer objeto DetalleVentaPresupuesto -------------------------------------------
    let detalleVentaPresupuesto = {datosDVPL: JSON.stringify({venta: venta, ventaPresupuestoLCList: ventaPresupuestoLCList})};

    let parametros = new URLSearchParams(detalleVentaPresupuesto);
    fetch("http://localhost:8080/OpticaWeb/api/detalleVentaPresupuesto/insertDetalleVentaPresupuesto",
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
                   document.getElementById("tbVenta").innerHTML = ""; 
                   limpiar();
                   ventaPresupuestoLCList = [];
                }
                
            });

}

function limpiar(){
    document.getElementById("txtCantidad").value = "";
    document.getElementById("txtDescuento").value = "";
    document.getElementById("txtPrecioUnitario").value = "";
    document.getElementById("txtAlturaOblea").value = "";
    
}










