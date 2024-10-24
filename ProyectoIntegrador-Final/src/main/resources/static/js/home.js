document.addEventListener('DOMContentLoaded', function() {
    let ultimoUsuario = localStorage.getItem("ultimoUsuario") || "";
    let usuarioActual = localStorage.getItem("usuarioActual") || "";
    let carritoActual;
    const cartCounter = document.getElementById("cartCount");

    if (ultimoUsuario != usuarioActual) {
        carritoActual = [];
        localStorage.setItem("ultimoUsuario", usuarioActual);
        cartCounter.textContent = 0;
        localStorage.setItem("carrito", JSON.stringify(carritoActual));
    } else {
        carritoActual = JSON.parse(localStorage.getItem("carrito")) || [];
        try {
            cartCounter.textContent = carritoActual.length;
        } catch {
            // handle error
        }
    }

    try {
        const cartIcon = document.getElementById("cartIcon");
        cartIcon.addEventListener('click', function(event) {
            armarCarrito();
            showModal('carritoModal');
        });
    } catch {
        // handle error
    }

    const token = localStorage.getItem('token');
    if (token == null) {
        redirect("/login");
    }

    document.getElementById("addPeliculaForm").addEventListener("submit", function(event) {
        event.preventDefault();
        addPelicula();
    });

    document.getElementById("modifyPeliculaForm").addEventListener("submit", function(event) {
        event.preventDefault();
        modifyPelicula();
    });

    document.getElementById("cancelarOrdenForm").addEventListener("submit", function(event) {
        event.preventDefault();
        cancelarOrden();
    });

    document.getElementById("solicitarOrdenForm").addEventListener("submit", function(event) {
        event.preventDefault();
        solicitarOrden();
    });

    document.getElementById("codigoCupon").addEventListener("change", function(event) {
        actualizarTotal();
    });
});

function addPelicula() {
    const token = localStorage.getItem('token'); // Modificación: Añadir token
    const titulo = document.getElementById("addTitulo").value;
    const sitio = document.getElementById("addSitio").value;
    const img = document.getElementById("addImg").value;
    const precio = document.getElementById("addPrecio").value;

    var generos = [];
    var select = document.getElementById("addGeneros");
    var elegidos = select.selectedOptions;

    for (var i = 0; i < elegidos.length; i++) {
        generos.push(elegidos[i].value);
    }

    fetch('/pelicula/add', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
            'Authorization': 'Bearer ' + token  // Modificación: Añadir token
        },
        body: JSON.stringify({
            titulo: titulo,
            img: img,
            url: sitio,
            precio: precio,
            generos: generos
        })
    })
    .then(response => {
        if (response.status == "201") {
            alert("Pelicula creada");
            redirect("/home");
        }
    })
    .catch(error => {
        alert("Registro de pelicula fallido");
    });
}

async function openModifyPelicula(id) {
    const data = await fetchPelicula(id);

    document.getElementById("modifyModalLabel").innerHTML = "Modificar pelicula (" + data.id + ")";
    document.getElementById("modifyTitulo").value = data.titulo;
    document.getElementById("modifySitio").value = data.url;
    document.getElementById("modifyImg").value = data.img;
    document.getElementById("modifyPrecio").value = data.precio;

    var generos = data.generos;
    var select = document.getElementById("modifyGeneros");

    for (let i = 0; i < select.options.length; i++) {
        let option = select.options[i];
        if (generos.includes(option.value)) {
            option.selected = true;
        } else {
            option.selected = false;
        }
    }

    showModal("modifyPeliculaModal");
}

async function openDetailPelicula(id) {
    const data = await fetchPelicula(id);

    document.getElementById("detailModalLabel").innerText = data.titulo;
    document.getElementById("detailSitio").innerText = data.url;
    document.getElementById("detailImg").src = data.img;
    document.getElementById("detailMonto").innerText = "Precio: $" + data.precio;

    var generos = data.generos;
    document.getElementById("detailGeneros").innerText = generos.join(", ");

    showModal("detailPeliculaModal");
}

function modifyPelicula() {
    const token = localStorage.getItem('token'); // Modificación: Añadir token
    const idTexto = document.getElementById("modifyModalLabel").innerHTML;
    const inicio = idTexto.indexOf("(");
    const fin = idTexto.indexOf(")", inicio);
    const id = idTexto.substring(inicio + 1, fin);

    const titulo = document.getElementById("modifyTitulo").value;
    const sitio = document.getElementById("modifySitio").value;
    const img = document.getElementById("modifyImg").value;
    const precio = document.getElementById("modifyPrecio").value;

    var generos = [];
    var select = document.getElementById("modifyGeneros");
    var elegidos = select.selectedOptions;

    for (var i = 0; i < elegidos.length; i++) {
        generos.push(elegidos[i].value);
    }

    fetch('/pelicula/modify', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
            'Authorization': 'Bearer ' + token  // Modificación: Añadir token
        },
        body: JSON.stringify({
            id: id,
            titulo: titulo,
            img: img,
            url: sitio,
            precio: precio,
            generos: generos
        })
    })
    .then(response => {
        if (response.status == "200") {
            alert("Pelicula modificada");
            redirect("/home");
        }
    })
    .catch(error => {
        alert("Registro de pelicula fallido");
    });
}

function deletePelicula(id) {
    const token = localStorage.getItem('token'); // Modificación: Añadir token
    fetch('/pelicula/delete', {
        method: 'DELETE',
        headers: {
            'Content-Type': 'application/json',
            'Authorization': 'Bearer ' + token  // Modificación: Añadir token
        },
        body: JSON.stringify({ id: id })
    })
    .then(response => {
        if (response.status == "200") {
            alert("Pelicula eliminada");
            redirect("/home");
        }
    })
    .catch(error => {
        alert("No se pudo eliminar la pelicula");
    });
}

function addPeliculatoCart(id) {
    let carritoActual = JSON.parse(localStorage.getItem("carrito")) || [];

    if (carritoActual.indexOf(+id) == -1) {
        carritoActual.push(+id);
    }
    localStorage.setItem("carrito", JSON.stringify(carritoActual));

    const cartCounter = document.getElementById("cartCount");
    cartCounter.textContent = carritoActual.length;
}

function removePeliculaFromCart(id) {
    let carritoActual = JSON.parse(localStorage.getItem("carrito")) || [];
    let indice = carritoActual.indexOf(id);
    carritoActual.splice(indice, 1);

    localStorage.setItem("carrito", JSON.stringify(carritoActual));

    const cartCounter = document.getElementById("cartCount");
    cartCounter.textContent = carritoActual.length;
    armarCarrito();
}

async function armarCarrito() {
    let carritoActual = JSON.parse(localStorage.getItem("carrito"));
    let subtotal = 0;
    const subtotalText = document.getElementById("subtotalValor");
    const totalText = document.getElementById("totalValor");
    document.getElementById("codigoCupon").value = "";

    const carritoTableBody = document.getElementById("carritoTableBody");
    carritoTableBody.innerHTML = '';

    for (let i = 0; i < carritoActual.length; i++) {
        const pelicula = await fetchPelicula(carritoActual[i]);
        const row = document.createElement('tr');
        row.innerHTML = `
            <td><img src="${pelicula.img}" width="25px" alt="${pelicula.titulo}"></td>
            <td>${pelicula.titulo}</td>
            <td><span>$ </span>${pelicula.precio}</td>
            <td>
                <button class="btn btn-danger" onclick="removePeliculaFromCart(${pelicula.id})">Eliminar</button>
            </td>
        `;
        carritoTableBody.appendChild(row);

        subtotal += pelicula.precio;
    }

    subtotalText.innerText = subtotal;
    totalText.innerText = subtotal;
}

async function actualizarTotal() {
    const token = localStorage.getItem('token'); // Modificación: Añadir token
    const codigo = document.getElementById("codigoCupon").value;
    const cuponTexto = document.getElementById("cuponTexto");
    const data = await getCupon(codigo);
    const totalText = document.getElementById("totalValor");
    const subtotalValor = document.getElementById("subtotalValor").innerText;

    if (data != null) {
        totalText.innerText = Math.round((parseFloat(subtotalValor) * (1 - data.porcentaje) * 100)) / 100;
        cuponTexto.innerText = "";
    } else {
        totalText.innerText = subtotalValor;
        cuponTexto.innerText = "El cupón '" + codigo + "' no existe.";
    }
}

async function getCupon(codigo) {
    const token = localStorage.getItem('token'); // Modificación: Añadir token
    try {
        const response = await fetch('/cupon/get-cupon', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                'Authorization': 'Bearer ' + token  // Modificación: Añadir token
            },
            body: JSON.stringify({ codigo: codigo })
        });
        if (!response.ok) {
            throw new Error('Network response was not ok');
        }

        const data = await response.json();
        return data;
    } catch (error) {
        return null;
    }
}

async function fetchPelicula(id) {
    const token = localStorage.getItem('token');  // Modificación: Añadir token
    try {
        const response = await fetch('/pelicula/get-pelicula', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                'Authorization': 'Bearer ' + token  // Modificación: Añadir token
            },
            body: JSON.stringify({ id: id })
        });
        if (!response.ok) {
            throw new Error('Network response was not ok');
        }

        const data = await response.json();
        return data;
    } catch (error) {
        alert(error);
    }
}

function cancelarOrden() {
    let carritoActual;
    const cartCounter = document.getElementById("cartCount");

    carritoActual = [];
    cartCounter.textContent = 0;
    localStorage.setItem("carrito", JSON.stringify(carritoActual));

    redirect("/home");
}

async function solicitarOrden() {
    const token = localStorage.getItem('token'); // Modificación: Añadir token
    let carritoActual = JSON.parse(localStorage.getItem("carrito")) || [];
    const cartCounter = document.getElementById("cartCount").textContent;

    if (cartCounter == 0) {
        alert("Su carrito se encuentra vacio.");
    } else {
        try {
            const response = await fetch('/orden/add', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                    'Authorization': 'Bearer ' + token  // Modificación: Añadir token
                },
                body: JSON.stringify({
                    email: localStorage.getItem("usuarioActual"),
                    cuponCodigo: document.getElementById("codigoCupon").value,
                    peliculas: carritoActual
                })
            });
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }

            alert("¡Gracias por tu compra!");
        } catch (error) {
            alert(error);
        }

        carritoActual = [];
        cartCounter.textContent = 0;
        localStorage.setItem("carrito", JSON.stringify(carritoActual));
        redirect("/home");
    }
}

document.addEventListener('DOMContentLoaded', function () {
    const hasSeenNovedades = document.getElementById('hasSeenNovedades').value;

    // Si el usuario no ha visto las novedades, muestra el modal
    if (hasSeenNovedades === "false") {
        var novedadesModal = new bootstrap.Modal(document.getElementById("novedadesModal"));
        novedadesModal.show();

        // Cerrar modal cuando se hace clic en "Aceptar"
        document.getElementById("acceptNovedades").addEventListener("click", function() {
            // Llamamos al backend para marcar las novedades como vistas
            fetch('/novedades/seen', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                    'Authorization': 'Bearer ' + token  // Modificación: Añadir token
                }
            }).then(response => {
                if (response.ok) {
                    novedadesModal.hide(); // Cierra el modal
                } else {
                    console.error("Error al marcar las novedades como vistas");
                }
            }).catch(error => {
                console.error("Error:", error);
            });
        });
    }
});

document.addEventListener('DOMContentLoaded', function() {
    const token = localStorage.getItem('token'); // Modificación: Añadir token
    let notificationCount = document.getElementById("notificationCount");

    // Llamar al endpoint para obtener el número de notificaciones no leídas
    fetch('/notificaciones/contar', {
        headers: {
            'Authorization': 'Bearer ' + token  // Modificación: Añadir token
        }
    })
    .then(response => response.json())
    .then(data => {
        notificationCount.textContent = data.count; // Actualiza el contador con las notificaciones no leídas
    }).catch(error => {
        console.error("Error al obtener el conteo de notificaciones:", error);
    });

    // Puedes agregar un evento para mostrar las notificaciones al hacer clic en la campanita
    document.getElementById("notificationIcon").addEventListener("click", function() {
        // Aquí puedes abrir un modal o desplegar una lista de notificaciones
        alert("Mostrar las notificaciones...");
    });
});
