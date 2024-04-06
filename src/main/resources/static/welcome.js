document.addEventListener("DOMContentLoaded", function() {
    const joinForm = document.getElementById("joinForm")

    joinForm.addEventListener('submit', function(event) {
        event.preventDefault()
        const username = document.getElementById('username').value
        sessionStorage.setItem('username', username)
        window.location.href = '/channels'
    })
})