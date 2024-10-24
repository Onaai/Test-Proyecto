// Manejo de la interacción con las estrellas de puntuación
document.querySelectorAll('.rating i').forEach(star => {
    star.addEventListener('click', function() {
        const value = this.getAttribute('data-value');
        document.querySelector('#puntuacion').value = value; // Asigna la puntuación seleccionada
        updateStars(value); // Actualiza las estrellas visualmente
    });
});

function updateStars(rating) {
    document.querySelectorAll('.rating i').forEach(star => {
        if (star.getAttribute('data-value') <= rating) {
            star.classList.add('selected');
        } else {
            star.classList.remove('selected');
        }
    });
}

// Manejo de la interacción con las estrellas de puntuación
document.querySelectorAll('.rating i').forEach(star => {
    star.addEventListener('click', function() {
        const value = this.getAttribute('data-value');
        document.querySelector('#puntuacion').value = value; // Asigna la puntuación seleccionada
        updateStars(value); // Actualiza las estrellas visualmente
    });
});

document.addEventListener('DOMContentLoaded', function () {
    // Recupera la puntuación del input oculto
    const puntuacionGuardada = document.querySelector('#puntuacion').value;
    updateStars(puntuacionGuardada);
});

// Manejo de la interacción con las estrellas de puntuación
document.querySelectorAll('.rating i').forEach(star => {
    star.addEventListener('click', function () {
        const value = this.getAttribute('data-value');
        document.querySelector('#puntuacion').value = value; // Asigna la puntuación seleccionada
        updateStars(value); // Actualiza las estrellas visualmente
    });
});

function updateStars(rating) {
    document.querySelectorAll('.rating i').forEach(star => {
        if (star.getAttribute('data-value') <= rating) {
            star.classList.add('selected');
        } else {
            star.classList.remove('selected');
        }
    });
}


// Tema likes
// Tema likes
document.addEventListener('DOMContentLoaded', function () {
    const token = localStorage.getItem('token');  // Obtener el token del localStorage

    if (!token) {
        // Si no hay token, redirigir a la página de inicio de sesión
        window.location.href = "/login";
        return;
    }

    // Seleccionar todos los formularios de like y agregar el evento 'submit'
    document.querySelectorAll('#like-form').forEach(form => {
        form.addEventListener('submit', function (event) {
            event.preventDefault();  // Evita que el formulario se envíe de manera tradicional

            const actionUrl = this.getAttribute('action');  // Obtiene la URL de acción del formulario

            enviarLike(actionUrl, token, this);  // Llama a la función para enviar el 'like'
        });
    });
});

// Función para manejar el "Like"
function enviarLike(actionUrl, token, form) {
    fetch(actionUrl, {
        method: 'POST',
        headers: {
            'Authorization': 'Bearer ' + token,
            'Content-Type': 'application/x-www-form-urlencoded'
        }
    })
    .then(response => response.json())  // Procesar la respuesta como JSON
    .then(data => {
        const likeButton = form.querySelector('button');
        const likesCountElement = form.querySelector('.likes-count');  // Asumiendo que tienes un elemento para mostrar los likes

        // Actualizar el número de likes
        likesCountElement.textContent = data.likesCount;

        // Actualizar el botón de like/unlike y su clase
        if (data.liked) {
            likeButton.textContent = 'Unlike';  // Cambiar a 'Unlike' si ha dado like
            likeButton.classList.remove('btn-like');  // Quitar la clase 'like'
            likeButton.classList.add('btn-unlike');  // Agregar la clase 'unlike'
        } else {
            likeButton.textContent = 'Like';  // Cambiar a 'Like' si ha quitado el like
            likeButton.classList.remove('btn-unlike');  // Quitar la clase 'unlike'
            likeButton.classList.add('btn-like');  // Agregar la clase 'like'
        }
    })
    .catch(error => {
        console.error("Error al dar like:", error);
    });
}


