function login() {
    const email = document.getElementById("email").value;
    const pass = document.getElementById("pwd").value;

    fetch('/auth/login', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({ email: email, password: pass })
    })
    .then(response => response.json())
    .then(data => {
        if (data.token) {
            localStorage.setItem('token', data.token);
            setCookie('auth_token', data.token, 7);
            redirect("/home");

            localStorage.setItem("usuarioActual", email);
        }
    })
    .catch(error => {
        alert("Inicio de sesión fallido");
    });
}

function register() {
    const nombre = document.getElementById("registerNombre").value;
    const apellido = document.getElementById("registerApellido").value;
    const email = document.getElementById("registerEmail").value;
    const pass = document.getElementById("registerPwd").value;

    const adminCheck = document.getElementById("checkAdmin").checked;
    const adminCode = document.getElementById("adminCode").value;

    fetch('/auth/register', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({
            email: email,
            password: pass,
            name: nombre,
            lastname: apellido,
            adminCheck: adminCheck,
            adminCode: adminCode
        })
    })
    .then(response => response.json())
    .then(data => {
        if (data.token) {
            alert("Usuario creado");
            redirect("/login");
        }
    })
    .catch(error => {
        alert("Registro fallido");
    });
}

function setCookie(name, value, days) {
    let expires = "";
    if (days) {
        const date = new Date();
        date.setTime(date.getTime() + (days * 24 * 60 * 60 * 1000));
        expires = "; expires=" + date.toUTCString();
    }
    document.cookie = name + "=" + (value || "") + expires + "; path=/";
}

function deleteCookie(name) {
    document.cookie = name + '=; Max-Age=-99999999;';
}

function cerrarSesion() {
    localStorage.removeItem("token");
    deleteCookie('auth_token');
    redirect("/login");
}

function redirect(url) {
    window.location.href = url;
}
