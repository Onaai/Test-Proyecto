document.addEventListener('DOMContentLoaded', function() {
    const token = localStorage.getItem('token');

    if (token != null) {
        //redirect("/home");
    }

    document.getElementById("login-form").addEventListener("submit", function(event) {
        event.preventDefault();
        login();
    });

    document.getElementById("register-form").addEventListener("submit", function(event) {
        event.preventDefault();
        register();
    });

    document.getElementById("checkAdmin").addEventListener("change", function(event) {
        event.preventDefault();
        const checkbox = document.getElementById("checkAdmin");
        const inputCode = document.getElementById("adminCode");
        if (checkbox.checked) {
            inputCode.hidden = false;
        } else {
            inputCode.hidden = true;
        }
    });
});
