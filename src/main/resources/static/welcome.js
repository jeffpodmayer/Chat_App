document.addEventListener("DOMContentLoaded", function () {
    const joinForm = document.getElementById("joinForm");

    joinForm.addEventListener('submit', function (event) {
        event.preventDefault();

        //GETS THE VALUE FROM THE USERNAME INPUT & STORES IT IN SESSION
        const username = document.getElementById("username").value;
        sessionStorage.setItem("username", username);

        //INITIATES FORM SUBMIT
        joinForm.onsubmit();
    });
});