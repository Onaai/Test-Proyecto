document.addEventListener('DOMContentLoaded', function() {
    const token = localStorage.getItem('token');
    if (token == null) {
        redirect("/login");
    }

    cargarOrdenes();
});

async function cargarOrdenes() {
    const table = document.getElementById("ordenesTableBody");
    table.innerHTML = "";

    try {
        const response = await fetch('/orden/get-ordenes', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            }
        });

        if (!response.ok) {
            throw new Error("Error de red");
        }

        const data = await response.json();

        for (let i = 0; i < data.length; i++) {
            const orden = data[i];
            const row = document.createElement("tr");
            row.innerHTML = `
                <td>${orden.id}</td>
                <td>${orden.emailSocio}</td>
                <td>${orden.fechaCreacion}</td>
                <td>${orden.estado}</td>
                <td><span>$ </span>${orden.monto}</td>
            `;

            if (orden.estado == "PENDIENTE APROBACION") {
                row.innerHTML += `
                    <td><button id="approve ${orden.id}" onclick="aprobarOrden(${orden.id})" title="Aprobar Orden" class="btn btn-approve"><i class="fas fa-check"></i></button></td>
                    <td><button id="approve ${orden.id}" onclick="desaprobarOrden(${orden.id})" title="Desaprobar Orden" class="btn btn-decline"><i class="fas fa-trash"></i></button></td>
                `;
            } else {
                row.innerHTML += `
                    <td></td>
                    <td></td>
                `;
            }

            table.appendChild(row);
        }
    } catch (error) {
        alert(error);
    }
}

function aprobarOrden(id) {
    fetch('/orden/aprobar-orden?id=' + id, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        }
    })
    .catch(error => {
        if (!error instanceof TypeError) {
            alert(error);
        }
    });

    redirect("/ordenes");
}

function desaprobarOrden(id) {
    fetch('/orden/desaprobar-orden?id=' + id, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        }
    })
    .catch(error => {
        if (!error instanceof TypeError) {
            alert(error);
        }
    });

    redirect("/ordenes");
}
