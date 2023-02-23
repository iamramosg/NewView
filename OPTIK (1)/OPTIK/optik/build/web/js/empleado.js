let empleados;

function insertar(){
    let nombre = document.getElementById("txtnombre").value;
    let apePaterno = document.getElementById("txtapePaterno").value;
    let apeMaterno = document.getElementById("txtapeMaterno").value;
    let genero = document.getElementById("slcgenero").value;
    let fechaNacimiento = document.getElementById("txtfechaNacimiento").value;
    let calle = document.getElementById("txtcalle").value;
    let numero = document.getElementById("txtnumero").value;
    let colonia = document.getElementById("txtcolonia").value;
    let cp = document.getElementById("txtcp").value;
    let ciudad = document.getElementById("txtciudad").value;
    let estado = document.getElementById("txtestado").value;
    let telCasa = document.getElementById("txttelCasa").value;
    let telMovil = document.getElementById("txttelMovil").value;
    let email = document.getElementById("txtemail").value;
    let nombreUsuario = document.getElementById("txtnombreUsuario").value;
    let contrasenia = document.getElementById("txtcontrasenia").value;
    let rol = document.getElementById("txtrol").value;
    
    let usuario = {nombre:nombreUsuario,contrasenia:contrasenia,rol:rol};
    let persona = {nombre:nombre,apellidoPaterno:apePaterno,apellidoMaterno:apeMaterno,genero:genero,fechaNacimiento:fechaNacimiento,calle:calle,numero:numero,colonia:colonia,cp:cp,ciudad:ciudad,estado:estado,telCasa:telCasa,telMovil:telMovil,email:email};
    let em = {usuario:usuario,persona:persona};
    
    let empleado = {datosEmpleado:JSON.stringify(em)};
    
    
    let parametros = new URLSearchParams(empleado);
    
    fetch("http://localhost:8080/optik/api/restoptik/insertEmpleado",{method:"POST",body:parametros,headers:{'Content-Type':'application/x-www-form-urlencoded;charset=UTF-8'}})
            .then(response => response.json())
            .then(data => {
                Swal.fire({
                    position: 'center',
                    icon: 'success',
                    title: 'Empleado agregado exitosamente',
                    showConfirmButton: false,
                    timer: 1500
                });
    });
}

function actualizar(){
    let idEmpleado = document.getElementById("txtidEmpleado").value;
    let idPersona = document.getElementById("txtidPersona").value;
    let idUsuario = document.getElementById("txtidUsuario").value;
    let nombre = document.getElementById("txtnombre").value;
    let apePaterno = document.getElementById("txtapePaterno").value;
    let apeMaterno = document.getElementById("txtapeMaterno").value;
    let genero = document.getElementById("slcgenero").value;
    let fechaNacimiento = document.getElementById("txtfechaNacimiento").value;
    let calle = document.getElementById("txtcalle").value;
    let numero = document.getElementById("txtnumero").value;
    let colonia = document.getElementById("txtcolonia").value;
    let cp = document.getElementById("txtcp").value;
    let ciudad = document.getElementById("txtciudad").value;
    let estado = document.getElementById("txtestado").value;
    let telCasa = document.getElementById("txttelCasa").value;
    let telMovil = document.getElementById("txttelMovil").value;
    let email = document.getElementById("txtemail").value;
    let nombreUsuario = document.getElementById("txtnombreUsuario").value;
    let contrasenia = document.getElementById("txtcontrasenia").value;
    let rol = document.getElementById("txtrol").value;
    
    let usuario = {idUsuario:idUsuario,nombre:nombreUsuario,contrasenia:contrasenia,rol:rol};
    let persona = {idPersona:idPersona,nombre:nombre,apellidoPaterno:apePaterno,apellidoMaterno:apeMaterno,genero:genero,fechaNacimiento:fechaNacimiento,calle:calle,numero:numero,colonia:colonia,cp:cp,ciudad:ciudad,estado:estado,telCasa:telCasa,telMovil:telMovil,email:email};
    let em = {idEmpleado:idEmpleado,usuario:usuario,persona:persona};
    
    let empleado = {datosEmpleado:JSON.stringify(em)};
    
    
    let parametros = new URLSearchParams(empleado);
    
    fetch("http://localhost:8080/optik/api/restoptik/updateEmpleado",{method:"POST",body:parametros,headers:{'Content-Type':'application/x-www-form-urlencoded;charset=UTF-8'}})
            .then(response => response.json())
            .then(data => {
                Swal.fire({
                    position: 'center',
                    icon: 'success',
                    title: 'Empleado agregado exitosamente',
                    showConfirmButton: false,
                    timer: 1500
                });
            });
}


function getAll(){
    let datos = {estatus: 1};
    let parametros = new URLSearchParams(datos); // nuestro json lo convierte en un bloque de parametros, se usa para post

    fetch("http://localhost:8080/optik/api/restoptik/getAllEmpleado", {method: "POST", body: parametros, headers: {'Content-Type': 'application/x-www-form-urlencoded;charset=UTF-8'}})
            .then(response => response.json())
            .then(data => {
                    console.log(data);
            
                    if (data.error) {
                        alert(data.error);
                    }else{
                        cargarTablaEmpleado(null,data);
                    }
                });
    
}
function getAllInactivos(){
    let datos = {estatus: 0};
    let parametros = new URLSearchParams(datos); // nuestro json lo convierte en un bloque de parametros, se usa para post

    fetch("http://localhost:8080/optik/api/restoptik/getAllEmpleado", {method: "POST", body: parametros, headers: {'Content-Type': 'application/x-www-form-urlencoded;charset=UTF-8'}})
            .then(response => response.json())
            .then(data => {
                    console.log(data);
            
                    if (data.error) {
                        alert(data.error);
                    }else{
                        cargarTablaEmpleado(null, data);
                    }
                });
    
}

function cargarTablaEmpleado(coincidencias, data){
    if(coincidencias){
        data = coincidencias;
    }else
        
    empleados = data;
    let contenido = "";
    data.forEach((empleado, index) =>{
        const {persona, usuario} = empleado;
        let nc = empleado.persona.nombre+" "+empleado.persona.apellidoPaterno+" "+empleado.persona.apellidoMaterno;
        let dc = empleado.persona.calle+" "+empleado.persona.numero+" "+empleado.persona.colonia+" "+empleado.persona.cp+" "+empleado.persona.ciudad+" "+empleado.persona.estado;

        
        contenido += "<tr>";
        contenido+="<td>"+nc+"</td>";
        contenido+="<td>"+dc+"</td>";
        contenido+="<td>"+empleado.usuario.nombre+"</td>";
        contenido+="<td>"+empleado.persona.email+"</td>";
        contenido += "<td><button type='button' class='btn btn-light m-3' onclick='cargarForm("+index+")'>Ver</button></td>";
        if(empleado.estatus === 1){
            contenido += "<td><button type='button' class='btn btn-danger m-3' onclick='eliminar("+empleado.idEmpleado+")'>Eliminar</button></td>";
        }else{
           contenido += "<td><button type='button' class='btn btn-success m-3' onclick='activar("+empleado.idEmpleado+")'>Activar</button></td>";
        }
        contenido += "</tr>";
    });
    document.getElementById("tbEmpleado").innerHTML=contenido;
}

function cargarForm(i){
    document.getElementById("txtnombre").value = empleados[i].persona.nombre;
    document.getElementById("txtapePaterno").value = empleados[i].persona.apellidoPaterno;
    document.getElementById("txtapeMaterno").value = empleados[i].persona.apellidoMaterno;
    if(empleados[i].persona.genero === 'F'){
        document.querySelector('#slcgenero').value = 'F';
    }else if(empleados[i].persona.genero === 'M'){
        document.querySelector('#slcgenero').value = 'M';
    }else{
        document.querySelector('#slcgenero').value = 'O';
    }
    document.getElementById("txtidEmpleado").value = empleados[i].idEmpleado;
    document.getElementById("txtidPersona").value = empleados[i].persona.idPersona;
    document.getElementById("txtidUsuario").value = empleados[i].usuario.idUsuario;
    document.getElementById("txtfechaNacimiento").value = empleados[i].persona.fechaNacimiento;
    document.getElementById("txtcalle").value = empleados[i].persona.calle;
    document.getElementById("txtnumero").value = empleados[i].persona.numero;
    document.getElementById("txtcolonia").value = empleados[i].persona.colonia;
    document.getElementById("txtcp").value = empleados[i].persona.cp;
    document.getElementById("txtciudad").value = empleados[i].persona.ciudad;
    document.getElementById("txtestado").value = empleados[i].persona.estado;
    document.getElementById("txttelCasa").value = empleados[i].persona.telCasa;
    document.getElementById("txttelMovil").value = empleados[i].persona.telMovil;
    document.getElementById("txtemail").value = empleados[i].persona.email;
    document.getElementById("txtnombreUsuario").value = empleados[i].usuario.nombre;
    document.getElementById("txtcontrasenia").value = empleados[i].usuario.contrasenia;
    document.getElementById("txtrol").value = empleados[i].usuario.rol;
}
function limpiarForm(){
    document.getElementById("txtidEmpleado").value = "";
    document.getElementById("txtidPersona").value = "";
    document.getElementById("txtidUsuario").value = "";
    document.getElementById("txtnombre").value = "";
    document.getElementById("txtapePaterno").value = "";
    document.getElementById("txtapeMaterno").value = "";
    document.getElementById("slcgenero").value = "D";
    
    document.getElementById("txtfechaNacimiento").value = "";
    document.getElementById("txtcalle").value = "";
    document.getElementById("txtnumero").value = "";
    document.getElementById("txtcolonia").value = "";
    document.getElementById("txtcp").value = "";
    document.getElementById("txtciudad").value = "";
    document.getElementById("txtestado").value = "";
    document.getElementById("txttelCasa").value = "";
    document.getElementById("txttelMovil").value = "";
    document.getElementById("txtemail").value = "";
    document.getElementById("txtnombreUsuario").value = "";
    document.getElementById("txtcontrasenia").value = "";
    document.getElementById("txtrol").value = "";
}
function limpiarTablaEmpleado(data){
    document.getElementById("tbEmpleado").innerHTML="";
}
function eliminar(idEmpleado){
//    let idEmpleado = document.getElementById("txtidEmpleado").value;
    let em = {idEmpleado: idEmpleado};

    let empleado = {datosEmpleado: JSON.stringify(em)};
    
    
    let parametros = new URLSearchParams(empleado);
        fetch("http://localhost:8080/optik/api/restoptik/eliminarEmpleado",{method:"POST",body:parametros,headers:{'Content-Type':'application/x-www-form-urlencoded;charset=UTF-8'}})
            .then(response => response.json())
            .then(data => {
                Swal.fire({
                    position: 'center',
                    icon: 'success',
                    title: 'Empleado eliminado exitosamente',
                    showConfirmButton: false,
                    timer: 1500
                });
            });
    
}
function activar(idEmpleado){
//    let idEmpleado = document.getElementById("txtidEmpleado").value;
    let em = {idEmpleado: idEmpleado};
    
    let empleado = {datosEmpleado: JSON.stringify(em)};
    
    let parametros = new URLSearchParams(empleado);
        fetch("http://localhost:8080/optik/api/restoptik/activarEmpleado",{method:"POST",body:parametros,headers:{'Content-Type':'application/x-www-form-urlencoded;charset=UTF-8'}})
            .then(response => response.json())
            .then(data => {
                Swal.fire({
                    position: 'center',
                    icon: 'success',
                    title: 'Empleado activado exitosamente',
                    showConfirmButton: false,
                    timer: 1500
                });
            });   
}
function Rbusqueda(){
    const busqueda = document.getElementById("myInput").value;
    const coincidencias = [];
    for(let i=0; i < empleados.length; i++){
        const empleado = empleados[i];
        
        if(empleado.persona.nombre.toLowerCase().includes(busqueda.toLowerCase()) ||
                empleado.persona.apellidoPaterno.toLowerCase().includes(busqueda.toLowerCase()) ||
                empleado.persona.apellidoMaterno.toLowerCase().includes(busqueda.toLowerCase()) ||
                empleado.persona.telCasa.includes(busqueda) ||
                empleado.persona.telMovil.includes(busqueda) ||
                empleado.persona.calle.toLowerCase().includes(busqueda.toLowerCase()) ||
                empleado.persona.colonia.toLowerCase().includes(busqueda.toLowerCase()) ||
                empleado.persona.ciudad.toLowerCase().includes(busqueda.toLowerCase()) ||
                empleado.persona.email.toLowerCase().includes(busqueda.toLowerCase()) ||
                empleado.usuario.nombre.toLowerCase().includes(busqueda.toLowerCase()) ||
                empleado.usuario.rol.toLowerCase().includes(busqueda.toLowerCase()) ||
                empleado.persona.genero.toLowerCase().includes(busqueda.toLowerCase())
                ){
            coincidencias.push(empleado);
                }
    }
    console.table(coincidencias);
    cargarTablaEmpleado(coincidencias,null);
}

