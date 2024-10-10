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

// Función para agregar un libro a la lista de "Leído"
function addToWatchlist(id) {
    fetch('/watchlist/add', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({ libroId: id }) // Enviar el ID del libro
    }).then(response => {
        if (response.ok) {
            const btnLeido = document.getElementById('btnLeido');
            btnLeido.classList.add('leido'); // Cambiar el color a verde
            btnLeido.innerHTML = '<i class="fas fa-check"></i> Marcado como leído'; // Cambiar el ícono y el texto
        } else {
            alert('Error al marcar el libro como "Leído"');
        }
    });
}
