const API_BASE_URL = 'http://localhost:8080/api';
let token = null;

// Registration
document.getElementById('registerForm').addEventListener('submit', async (e) => {
    e.preventDefault();
    const user = {
        username: document.getElementById('username').value,
        password: document.getElementById('password').value,
        role: document.getElementById('role').value
    };

    const response = await fetch(`${API_BASE_URL}/auth/register`, {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(user)
    });

    if (response.ok) {
        document.getElementById('registerMessage').textContent = 'Registration successful!';
    } else {
        document.getElementById('registerMessage').textContent = 'Registration failed.';
    }
});

// Login
document.getElementById('loginForm').addEventListener('submit', async (e) => {
    e.preventDefault();
    const loginRequest = {
        username: document.getElementById('loginUsername').value,
        password: document.getElementById('loginPassword').value
    };

    const response = await fetch(`${API_BASE_URL}/auth/login`, {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(loginRequest)
    });

    if (response.ok) {
        const data = await response.json();
        token = data.token; // Store the token
        document.getElementById('loginMessage').textContent = 'Login successful!';
        document.getElementById('claimSection').style.display = 'block'; // Show claim section
        loadClaims();
    } else {
        document.getElementById('loginMessage').textContent = 'Login failed.';
    }
});

// Submit a claim
document.getElementById('claimForm').addEventListener('submit', async (e) => {
    e.preventDefault();
    const claim = {
        vehicleInfo: document.getElementById('vehicleInfo').value,
        accidentDescription: document.getElementById('accidentDescription').value,
        userId: 1 // Replace with actual user ID (you can fetch this from the token)
    };

    const response = await fetch(`${API_BASE_URL}/claims`, {
        method: 'POST',
        headers: { 
            'Content-Type': 'application/json',
            'Authorization': `Bearer ${token}` // Include the token
        },
        body: JSON.stringify(claim)
    });

    if (response.ok) {
        alert('Claim submitted successfully!');
        loadClaims();
    }
});


// Example: Fetch claims by user ID
async function loadClaims() {
    const response = await fetch(`${API_BASE_URL}/claims/user/1`, {
        headers: { 
            'Authorization': `Bearer ${token}` // Include the token
        }
    });

    if (response.ok) {
        const claims = await response.json();
        const claimsList = document.getElementById('claimsList');
        claimsList.innerHTML = claims.map(claim => `
            <li>
                <strong>${claim.vehicleInfo}</strong><br>
                ${claim.accidentDescription}<br>
                Status: ${claim.claimStatus}
            </li>
        `).join('');
    } else {
        console.error('Failed to fetch claims:', response.statusText);
    }
}