function getAllTicketP() {
    let datos = document.getElementById("txtMateria").value;
    let filtro = {filtro: datos};

    let parametros = new URLSearchParams(filtro);
    fetch("http://localhost:8080/optik/api/restoptik/getAllTicketP", {method: "POST", body: parametros, headers: {'Content-Type': 'application/x-www-form-urlencoded;charset=UTF-8'}})
            .then(response => response.json())
            .then(data => {
                if (data.error) {
                    alert(data.error);
                } else {
                    console.log(data);
                    generarTarjetasP(data);
                }
            });
}



function generarTarjetasP(tickets) {
    const tarjetasContainer = document.getElementById("tarjetas-container");
    tarjetasContainer.innerHTML = "";

    let colorFondo = "#FFFFFF";
    
    //Crear objeto para agrupar tickets por clave
    const ticketsPorClave = {};

    //Iterar a través de los tickets y agregarlos al objeto según su clave
    tickets.forEach((ticket) => {
        if (ticket.clave in ticketsPorClave) {
            ticketsPorClave[ticket.clave].push(ticket);
        } else {
            ticketsPorClave[ticket.clave] = [ticket];
        }
    });

    //Iterar sobre el objeto y crear una tarjeta por cada array de tickets que tenga
    for (const clave in ticketsPorClave) {
        let total = 0;

        //Crear la estructura HTML para la tarjeta 
        const tarjeta = document.createElement("div");
        tarjeta.className = "tarjeta";
        tarjeta.style.backgroundColor = colorFondo;
        tarjeta.style.padding = "20px";
        tarjeta.style.marginBottom = "20px";
        
        const imagen = document.createElement("img");
        imagen.setAttribute("src", "https://drive.google.com/uc?id=1VixhgP-KKrY3aXakLs59axh5rdzuQTap");
        imagen.style.display = "block";
        imagen.style.margin = "auto";
        tarjeta.appendChild(imagen);
        
        const title = document.createElement("h2");
        title.textContent = "New View";
        title.style.color = "black";        

        const c = document.createElement("p");
        c.textContent = clave;
        c.style.color = "#fe303a";
        

        const empleado = document.createElement("p");
        empleado.textContent = "Empleado: " + ticketsPorClave[clave][0].empleado + " " + ticketsPorClave[clave][0].apellidoPaterno + " " + ticketsPorClave[clave][0].apellidoMaterno;
        empleado.style.color = "black";

        const numeroUnico = document.createElement("p");
        numeroUnico.textContent = "Numero Empleado: " + ticketsPorClave[clave][0].numeroUnico;
        numeroUnico.style.color = "black";
        
        
        tarjeta.appendChild(empleado);
        tarjeta.appendChild(numeroUnico);
        
        //crear la tabla y encabezados
        const tabla = document.createElement("table");
        const encabezados = document.createElement("tr");
        
        const nombreEncabezado = document.createElement("th");
        nombreEncabezado.textContent = "Nombre";
        nombreEncabezado.style.color = "black";
        encabezados.appendChild(nombreEncabezado);
        
        const marcaEncabezado = document.createElement("th");
        marcaEncabezado.textContent = "Marca";
        marcaEncabezado.style.color = "black";
        encabezados.appendChild(marcaEncabezado);
        
        const cantidadEncabezado = document.createElement("th");
        cantidadEncabezado.textContent = "Cantidad";
        cantidadEncabezado.style.color = "black";
        encabezados.appendChild(cantidadEncabezado);
        
        const precioUnitarioEncabezado = document.createElement("th");
        precioUnitarioEncabezado.textContent = "Precio";
        precioUnitarioEncabezado.style.color = "black";
        encabezados.appendChild(precioUnitarioEncabezado);
        
        const descuentoEncabezado = document.createElement("th");
        descuentoEncabezado.textContent = "Descuento";
        descuentoEncabezado.style.color = "black";
        encabezados.appendChild(descuentoEncabezado);
        
        tabla.appendChild(encabezados);
        

        ticketsPorClave[clave].forEach((ticket) => {
            const subtotal = ticket.cantidad * ticket.precioUnitario;
            const des = subtotal * (ticket.descuento / 100);
            const totalTicket = subtotal - des;
            total += totalTicket;
            const fila = document.createElement("tr");         

            const nombre = document.createElement("td");
            nombre.textContent = ticket.nombre;
            nombre.style.color = "black";
            fila.appendChild(nombre);

            const marca = document.createElement("td");
            marca.textContent = ticket.marca;
            marca.style.color = "black";
            fila.appendChild(marca);

            const cantidad = document.createElement("td");
            cantidad.textContent = ticket.cantidad;
            cantidad.style.color = "black";
            fila.appendChild(cantidad);

            const precioUnitario = document.createElement("td");
            precioUnitario.textContent = "$" + ticket.precioUnitario.toFixed(2);
            precioUnitario.style.color = "black";
            fila.appendChild(precioUnitario);

            const descuento = document.createElement("td");
            descuento.textContent = ticket.descuento + "%";
            descuento.style.color = "black";
            fila.appendChild(descuento);
            
            
            
            tabla.appendChild(fila);
        });
        const t = document.createElement("p");
        t.textContent = "Total: $" + total;
        t.style.color = "#fe303a";
        
        const imagenC = document.createElement("img");
        imagenC.setAttribute("src", "https://drive.google.com/uc?id=1WXmWdwNNTIvM5i0CGwVevmsqzjrV0YgA");
        imagenC.style.display = "block";
        imagenC.style.width = "200px"; // Establece el ancho al 50%
        imagenC.style.height = "100px";
        imagenC.style.margin = "auto";

        tarjeta.insertBefore(title, tarjeta.firstChild);
        tarjeta.appendChild(tabla);
        tarjeta.appendChild(t);
        tarjeta.appendChild(imagenC);
        tarjeta.appendChild(c);

        //Agregar la tarjeta al contenedor de tarjetas
        tarjetasContainer.appendChild(tarjeta);
    }
}
