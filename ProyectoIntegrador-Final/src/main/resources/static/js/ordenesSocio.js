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
            },
            body: JSON.stringify({
                email: localStorage.getItem("usuarioActual")
            })
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
                <td>${orden.fechaCreacion}</td>
                <td>${orden.estado}</td>
                <td><span>$ </span>${orden.monto}</td>
            `;

            table.appendChild(row);
        }
    } catch (error) {
        alert(error);
    }
}
