function accesar(){
    let user = document.getElementById("usuario").value;
    let contrasenia = document.getElementById("contrasenia").value;
    
    let usuario = {datosAcceso: JSON.stringify({nombre: user, contrasenia: contrasenia})};
    
    const url = new URLSearchParams(usuario);
    fetch('http://localhost:8080/optik/api/restoptik/acceder',
            {
                method: "POST",
                headers: {'Content-Type': 'application/x-www-form-urlencoded;charset=UTF-8'},
                body: url
            })
            .then(response => response.json())
            .then(data => {
                if(data.error){
                    alert(data.error);
                }else if(data.idEmpleado){
                    Swal.fire({
                        position: 'center',
                        icon: 'success',
                        title: 'Acceso Concedido' + data.persona.nombre,
                        showConfirmButton: false,
                        timer: 1500
                    });
                    setTimeout(() =>{
                         console.log("2 segundos esperado")
                     }, 2000);
                     
                     window.location.href = "http://localhost:8080/optik/principal.html";
                     
                }else{
                    Swal.fire({
                        position: 'center',
                        icon: 'error',
                        title: 'uyyyy...',
                        text: 'a ocurrido un error'
                    });
                }
                JSON.stringify(data);
                limpiarForm();
            });
}

function limpiarForm(){
    document.getElementById("usuario").value="";
    document.getElementById("contrasenia").value="";
}

async function encriptar(texto) {
    const encoder = new TextEncoder(); //Invocamos la clase q convierte un String en bytes
    const data = encoder.encode(texto);//Hace la conversión
    const hash = await crypto.subtle.digest('SHA-256', data); //crypto toma los bytes y los encripta, devuelve un buffer
    const hashArray = Array.from(new Uint8Array(hash)); // convierte el buffer en un arreglo de bytes
    const hashHex = hashArray.map((b) => b.toString(16).padStart(2, '0')).join(''); // convierte los bytes en string
    return hashHex;
}

function validarAcceso()
{
    let username = document.getElementById("usuario").value;
    let password = document.getElementById("contrasenia").value;

 

    encriptar(password).then((textoEncriptado) => {
        
        alert(textoEncriptado.toString());
        
        let usuario = {"usuario": JSON.stringify({"nombre": username, "contrasenia": textoEncriptado.toString()})};

 

        let params = new URLSearchParams(usuario);

 

        fetch("http://localhost:8080/optik/api/restoptik/in",
                {method: "POST",
                    headers: {'Content-Type': 'application/x-www-form-urlencoded;charset=UTF-8'},
                    body: params
                })
                .then(response => {
                    return response.json();
                })
                .then(function (data)
                {
                    if (data.exception != null)
                    {
                        Swal.fire('', 'Error interno del servidor. Intente nuevamente más tarde.', 'error');
                        return;
                    }

 

                    if (data.error != null)
                    {
                        Swal.fire('', data.error, 'warning');
                        return;
                    }

 

                    if (data.usuario.rol === 'Administrador')
                    {
                        localStorage.setItem('currentUser', JSON.stringify(data));
                        window.location.href = "http://localhost:8080/optik/principal.html";
                    } else
                        Swal.fire('', 'Por el momento, solo los administradores tienen acceso al sistema.', 'warning');
                });

 

    });

 

}

function accesar2() {
    let user = document.getElementById("usuario").value;
    let contrasenia = document.getElementById("contrasenia").value;


    encriptar(contrasenia).then((textoEncriptado) => {
        alert(textoEncriptado.toString());
        let usuario = {datosAcceso: JSON.stringify({nombre: user, contrasenia: textoEncriptado.toString()})};

        const url = new URLSearchParams(usuario);
        fetch('http://localhost:8080/optik/api/restoptik/acceder',
                {
                    method: "POST",
                    headers: {'Content-Type': 'application/x-www-form-urlencoded;charset=UTF-8'},
                    body: url
                })
                .then(response => response.json())
                .then(data => {
                    if (data.error) {
                        alert(data.error);
                    } else if (data.idEmpleado) {
                        localStorage.setItem('currentUser',JSON.stringify(data));
                        Swal.fire({
                            position: 'center',
                            icon: 'success',
                            title: 'Acceso Concedido' + data.persona.nombre,
                            showConfirmButton: false,
                            timer: 1500
                        });
                        setTimeout(() => {
                            console.log("2 segundos esperado");
                        }, 2000);

                        window.location.href = "http://localhost:8080/optik/principal.html";

                    } else {
                        Swal.fire({
                            position: 'center',
                            icon: 'error',
                            title: 'uyyyy...',
                            text: 'a ocurrido un error'
                        });
                    }
                    JSON.stringify(data);
                    limpiarForm();
                });
    });

}


function logOut(){
    let token = localStorage.getItem("currentUser");
    localStorage.setItem('currentUser', "");
    let usuario = {empleado:token};
    
    const url = new URLSearchParams(usuario);
          fetch('http://localhost:8080/optik/api/restoptik/out',
                {
                    method: "POST",
                    headers: {'Content-Type': 'application/x-www-form-urlencoded;charset=UTF-8'},
                    body: url
                })
                .then(response => response.json())
                .then(data => {
                    if (data.error) {
                        alert(data.error);
                    } else if (data.idEmpleado) {
                        setTimeout(() => {
                            console.log("2 segundos esperado");
                        }, 2000);

                    } else {
//                        Swal.fire({
//                            position: 'center',
//                            icon: 'error',
//                            title: 'uyyyy...',
//                            text: 'a ocurrido un error'
//                        });
                    }
                    JSON.stringify(data);
                });

}

