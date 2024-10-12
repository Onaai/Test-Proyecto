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
