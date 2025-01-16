const joinBtn = document.querySelector('.subBtn');


joinBtn.onclick = () => {
    // Reset error messages
    const userIdError = document.getElementById("userIdError");
    const passwordError = document.getElementById("passwordError");

    // Validate userId
    const userId = document.getElementById("userId").value;
    if (userId.trim() === "") {
        userIdError.style.display = "block";
        return;
    }

    // Validate password match
    const password = document.getElementById("password").value;
    const confirmPassword = document.getElementById("confirmPassword").value;
    if (password !== confirmPassword) {
        passwordError.style.display = "block";
        return;
    }
}