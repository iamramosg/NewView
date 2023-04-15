function generarTarjetasL(tickets) {
    const tarjetasContainer = document.getElementById("tarjetas-container");
    tarjetasContainer.innerHTML = "";

    let colorFondo = "#FFFFFF";

    //Crear objeto para agrupar tickets por clave
    const ticketsPorClave = {};

    //Iterar a través de los tickets y agregarlos al objeto según su clave
    tickets.forEach((ticket) => {
        if (ticket.claveV in ticketsPorClave) {
            ticketsPorClave[ticket.claveV].push(ticket);
        } else {
            ticketsPorClave[ticket.claveV] = [ticket];
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
        empleado.textContent = "Empleado: " + ticketsPorClave[clave][0].nombreE + " " + ticketsPorClave[clave][0].apellidoPaternoE + " " + ticketsPorClave[clave][0].apellidoMaternoE;
        empleado.style.color = "black";

        const numeroUnicoE = document.createElement("p");
        numeroUnicoE.textContent = "Numero Empleado: " + ticketsPorClave[clave][0].numeroUnicoE;
        numeroUnicoE.style.color = "black";

        const cliente = document.createElement("p");
        cliente.textContent = "Cliente: " + ticketsPorClave[clave][0].nombreC + " " + ticketsPorClave[clave][0].apellidoPaternoC + " " + ticketsPorClave[clave][0].apellidoMaternoC;
        cliente.style.color = "black";

        const numeroUnicoC = document.createElement("p");
        numeroUnicoC.textContent = "Numero Cliente: " + ticketsPorClave[clave][0].numeroUnicoC;
        numeroUnicoC.style.color = "black";


        tarjeta.appendChild(empleado);
        tarjeta.appendChild(numeroUnicoE);
        tarjeta.appendChild(cliente);
        tarjeta.appendChild(numeroUnicoC);

        //crear la tabla y encabezados
        const tabla = document.createElement("table");
        const encabezados = document.createElement("tr");

        const ArmazonEncabezado = document.createElement("th");
        ArmazonEncabezado.textContent = "Armazon";
        ArmazonEncabezado.style.color = "black";
        encabezados.appendChild(ArmazonEncabezado);

        const materialEncabezado = document.createElement("th");
        materialEncabezado.textContent = "Material";
        materialEncabezado.style.color = "black";
        encabezados.appendChild(materialEncabezado);

        const tipoMicaEncabezado = document.createElement("th");
        tipoMicaEncabezado.textContent = "Mica";
        tipoMicaEncabezado.style.color = "black";
        encabezados.appendChild(tipoMicaEncabezado);
        
        const tratamientoEncabezado = document.createElement("th");
        tratamientoEncabezado.textContent = "Tratamiento";
        tratamientoEncabezado.style.color = "black";
        encabezados.appendChild(tratamientoEncabezado);

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

            const armazon = document.createElement("td");
            armazon.textContent = ticket.marca + " " + ticket.nombreA;
            armazon.style.color = "black";
            fila.appendChild(armazon);

            const material = document.createElement("td");
            material.textContent = ticket.nombreM;
            material.style.color = "black";
            fila.appendChild(material);
            
            const tipoMica = document.createElement("td");
            tipoMica.textContent = ticket.nombreTP;
            tipoMica.style.color = "black";
            fila.appendChild(tipoMica);    
            
            const tratamiento = document.createElement("td");
            tratamiento.textContent = ticket.nombreT;
            tratamiento.style.color = "black";
            fila.appendChild(tratamiento); 

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
