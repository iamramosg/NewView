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
        let dc = cliente.persona.calle+" "+cliente.persona.numero+" "+cliente.persona.colonia+" "+cliente.persona.cp+" "+cliente.persona.ciudad+" "+cliente.persona.estado;
        
        contenido += "<tr>";
        contenido += "<td style='display:none;'>" + cliente.idCliente + "</td>"; //5
        contenido += "<td>" + nc + "</td>";
        contenido += "<td>" + dc + "</td>";
        contenido += "<td>" + cliente.persona.email + "</td>";
        contenido += `<td class='text-end'><button type='button' class='btn btn-outline-success m-3 ms-auto' onclick='getAllExamenVista(); getAllArmazon(); getAllMateriales(); getAllTipoMica();'>Agregar</button></td>`;
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
                    
      // Crear opción predeterminada con el primer examen
      const defaultOption = document.createElement("option");
      defaultOption.text = "Seleccionar Examen";
      if (examenesVista.length > 0) {
        defaultOption.value = JSON.stringify(examenesVista[0]);
        examenSeleccionado = examenesVista[0];
      }
      selectExamen.appendChild(defaultOption);
                    
      //Iterar sobre los objetos y agregar opciones al select 
      examenesVista.forEach(examen =>{
        const option = document.createElement("option");
        option.value = JSON.stringify(examen);
        option.text = examen.fecha;
        selectExamen.appendChild(option);
      });
                    
      //agregar un evento onchange al select 
      selectExamen.addEventListener("change",() =>{
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
                    
                    //Crear opcion predeterminada con el primer tipo mica
                    const defaultOption = document.createElement("option");
                    defaultOption.text = "Seleccionar Mica";
                    if(tipoMicas.length >0){
                        defaultOption.value = JSON.stringify(tipoMicas[0]);
                        micaSeleccionada = tipoMicas[0];
                    }
                    selectMica.appendChild(defaultOption);
                    
                    //iterar sobre los objetos y agregar las opciones al select
                    tipoMicas.forEach(mica =>{
                        const option = document.createElement("option");
                        option.value = JSON.stringify(mica);
                        option.text = mica.nombre;
                        selectMica.appendChild(option);
                    });
                    
                    //agregar un evento onchange al select
                    selectMica.addEventListener("change",() =>{
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
                     //Crear opción predeterminada en el primer armazon
                     const defaultOption = document.createElement("option");
                     defaultOption.text = "Seleccionar Armazon";
                     if(armazones.length > 0){
                         defaultOption.value = JSON.stringify(armazones[0]);
                         armazonSeleccionado = armazones[0];
                     }
                     selectArmazon.appendChild(defaultOption);
                     
                     //Iterar sobre los objetos y agregar opciones al select
                     armazones.forEach(armazon =>{
                         const option = document.createElement("option");
                         option.value = JSON.stringify(armazon);
                         option.text = armazon.producto.nombre;
                         selectArmazon.appendChild(option);
                     });
                     
                     //agregar un evento onchange al select
                     selectArmazon.addEventListener("change",() =>{
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

    fetch('http://localhost:8080/optik/api/restoptik/getAllTratamiento',
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
                    tratamientos = data;
                    
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
                    const defaultOption  = document.createElement("option");
                    defaultOption.text = "Selecciona Material";
                    
                    if(materiales.length > 0){
                        defaultOption.value = JSON.stringify(materiales[0]);
                        materialSeleccionado = materiales[0];
                    }
                    selectMaterial.appendChild(defaultOption);
                    materiales.forEach(material =>{
                        const option = document.createElement("option");
                        option.value = JSON.stringify(material);
                        option.text = material.nombre;
                        selectMaterial.appendChild(option);
                    });
                    
                    //agregar un evento onchange al select
                    selectMaterial.addEventListener("change",() =>{
                         //obtener el valor seleccionado
                         materialSeleccionado = JSON.parse(selectMaterial.value);
                     });
                }
            });
}


function generarVentaLente() {
    //creamos las claves 
    const randomNum = Math.round(Math.random() * 100000000);
    let clave = "OQ-" + randomNum;
    let claveP = "OQ-" + randomNum;
    
    
    //armamos el objeto de venta
    var emp = JSON.parse(localStorage.getItem('currentUser'));
    let venta = {clave: clave, empleado: emp};
    
     //armamos un objeto de presupuesto 
     let presupuesto = {examenVista: examenSeleccionado, clave: claveP};
     console.log(JSON.stringify(presupuesto));
}