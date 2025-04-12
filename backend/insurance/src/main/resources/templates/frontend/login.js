document.getElementById('loginForm').addEventListener('submit', async (e) => {
    e.preventDefault();
    const user = {
        username: document.getElementById('username').value,
        password: document.getElementById('password').value
    };

    const response = await fetch('http://localhost:8080/api/auth/login', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(user)
    });

    if (response.ok) {
        const token = await response.text();
        localStorage.setItem('token', token); // Save the token
        window.location.href = 'index.html'; // Redirect to claims page
    } else {
        document.getElementById('message').textContent = 'Login failed.';
    }
});