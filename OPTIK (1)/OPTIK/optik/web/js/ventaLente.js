let clientes;
let examenesVista;
let tipoMicas;
let armazones;
let tratamientos;
let materiales;
let indexTV = 0;

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
                    console.log(clientes);
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
                    console.log(examenesVista);
                    // Obtener el select del HTML
                    const selectExamen = document.getElementById("slcExamenVista");
                    
                    //Iterar sobre los objetos y agregar opciones al select 
                    examenesVista.forEach(examen =>{
                        const option = document.createElement("option");
                        option.value = JSON.stringify(examen);
                        option.text = examen.fecha;
                        selectExamen.appendChild(option);
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
                    console.log(tipoMicas);
                    //pon que quieres que haga con la lista de tipo micas
                    const selectMica = document.getElementById("slcTipoMica");
                    
                    tipoMicas.forEach(mica =>{
                        const option = document.createElement("option");
                        option.value = JSON.stringify(mica);
                        option.text = mica.nombre;
                        selectMica.appendChild(option);
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
                    console.log(armazones);
                     
                     const selectArmazon = document.getElementById("slcArmazon");
                     
                     armazones.forEach(armazon =>{
                         const option = document.createElement("option");
                         option.value = JSON.stringify(armazon);
                         option.text = armazon.producto.nombre;
                         selectArmazon.appendChild(option);
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
                    console.log(tratamientos);
                    
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
                    console.log(materiales);
                    const selectMaterial = document.getElementById("slcMaterial");
                    
                    materiales.forEach(material =>{
                        const option = document.createElement("option");
                        option.value = JSON.stringify(material);
                        option.text = material.nombre;
                        selectMaterial.appendChild(option);
                    });
                }
            });
}


