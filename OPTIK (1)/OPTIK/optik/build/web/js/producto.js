let productos;

function getAllProducto(){
    const busqueda = document.getElementById("myInput").value;
    let datos = {estatus: busqueda};
    let parametros = new URLSearchParams(datos); // nuestro json lo convierte en un bloque de parametros, se usa para post

    fetch("http://localhost:8080/optik/api/restoptik/getAllProducto", {method: "POST", body: parametros, headers: {'Content-Type': 'application/x-www-form-urlencoded;charset=UTF-8'}})
            .then(response => response.json())
            .then(data => {
                    console.log(data);
            
                    if (data.error) {
                        alert(data.error);
                    }else{
                        cargarTablaProducto(data);
                    }
                });
    
}

function cargarTablaProducto(data){
    productos = data;
    let contenido = "";
    
    data.forEach((producto) =>{
        

        
        contenido += "<tr>";
        contenido+="<td>"+producto.codigoBarras+"</td>";
        contenido+="<td>"+producto.nombre+"</td>";
        contenido+="<td>"+producto.precioVenta+"</td>";
        contenido += "<td><button type='button' class='btn btn-light m-3' ><input type='number'></button></td>";
        contenido += "</tr>";
    });
    document.getElementById("tbProducto").innerHTML=contenido;
}
function limpiarTablaProducto(data){
    document.getElementById("tbProducto").innerHTML="";
}


