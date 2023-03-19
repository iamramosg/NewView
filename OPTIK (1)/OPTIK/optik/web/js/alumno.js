function insertarE() {
    let materia = document.getElementById("txtMateria").value;
    let calificacion = document.getElementById("txtCalificacion").value;
    let fecha = document.getElementById("txtFecha").value;
    let comentario = document.getElementById("txtComentario").value;
    let seleccion = parseInt(document.getElementById("slcAlumno").value);
    
        // Validar campos obligatorios
    if (!materia || !calificacion || !fecha || isNaN(seleccion)) {
        Swal.fire({
            icon: 'error',
            title: 'Error',
            text: 'Complete los campos obligatorios',
        });
        return; // Salir de la función sin enviar la petición
    }


    let e = {materia: materia, calificacion: calificacion, fecha: fecha, comentario: comentario, seleccion: seleccion};
    console.log(e);
    let evaluacion = {datosEvaluacion: JSON.stringify(e)};


    let parametros = new URLSearchParams(evaluacion);

    fetch("http://localhost:8080/optik/api/restoptik/insertEvaluacion", {method: "POST", body: parametros, headers: {'Content-Type': 'application/x-www-form-urlencoded;charset=UTF-8'}})
            .then(response => response.json())
            .then(data => {
                Swal.fire({
                    position: 'center',
                    icon: 'success',
                    title: 'Evaluacion agregada exitosamente',
                    showConfirmButton: false,
                    timer: 1500
                });
            });

}
function getAllA() {
    let datos = parseInt(document.getElementById("slcAlumno").value);
    let matricula = {matricula: datos};

    let parametros = new URLSearchParams(matricula);

    fetch("http://localhost:8080/optik/api/restoptik/getAllAlumno", {method: "POST", body: parametros, headers: {'Content-Type': 'application/x-www-form-urlencoded;charset=UTF-8'}})
            .then(response => response.json())
            .then(data => {
//                    console.log(data);
//            alert(JSON.stringify(data));
                if (data.error) {
                    alert(data.error);
                } else {
                    console.log(data);
                    generarTarjetas(data);
                }
            });

}

function generarTarjetas(alumnos) {
  const tarjetasContainer = document.getElementById("tarjetas-container");
  tarjetasContainer.innerHTML = "";

  let colorFondo = "#FFFFFF";

  // Iterar a través de la lista de alumnos y crear una tarjeta para cada uno de ellos
  alumnos.forEach((alumno) => {
    // Crear la estructura HTML para la tarjeta
    const tarjeta = document.createElement("div");
    tarjeta.className = "tarjeta";
    tarjeta.style.backgroundColor = colorFondo;
    tarjeta.style.padding = "20px";
    tarjeta.style.marginBottom = "20px";

    const nombre = document.createElement("h2");
    nombre.textContent = "Nombre: " + alumno.nombre;
    nombre.style.color = "#00c6ff";

    const matricula = document.createElement("p");
    matricula.textContent = "Matricula: " + alumno.matricula;
    matricula.style.color = "#00c6ff";

    const fechaNacimiento = document.createElement("p");
    fechaNacimiento.textContent = "Fecha de nacimiento: " + alumno.fechaNacimiento;
    fechaNacimiento.style.color = "#00c6ff";

    const materia = document.createElement("p");
    materia.textContent = "Materia: " + alumno.evaluacion.materia;
    materia.style.color = "black";

    const calificacion = document.createElement("p");
    calificacion.textContent = "Calificacion: " + alumno.evaluacion.calificacion;
    calificacion.style.color = "black";

    const fechaEvaluacion = document.createElement("p");
    fechaEvaluacion.textContent = "Fecha de evaluacion: " + alumno.evaluacion.fecha;
    fechaEvaluacion.style.color = "black";

    tarjeta.appendChild(nombre);
    tarjeta.appendChild(matricula);
    tarjeta.appendChild(fechaNacimiento);
    tarjeta.appendChild(materia);
    tarjeta.appendChild(calificacion);
    tarjeta.appendChild(fechaEvaluacion);

    if (alumno.evaluacion.comentario !== undefined) {
      const comentario = document.createElement("p");
      comentario.textContent = "Comentario: " + alumno.evaluacion.comentario;
      comentario.style.color = "black";
      tarjeta.appendChild(comentario);
    }

    // Agregar la tarjeta al contenedor de tarjetas
    tarjetasContainer.appendChild(tarjeta);

  });
}
