document.getElementById('registerForm').addEventListener('submit', async (e) => {
    e.preventDefault();
    const user = {
        username: document.getElementById('username').value,
        password: document.getElementById('password').value,
        role: document.getElementById('role').value
    };

    const response = await fetch('http://localhost:8080/api/auth/register', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(user)
    });

    if (response.ok) {
        document.getElementById('message').textContent = 'Registration successful!';
    } else {
        document.getElementById('message').textContent = 'Registration failed.';
    }
});