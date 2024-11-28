function loginWithGoogle() {
    google.accounts.id.initialize({
        client_id: "1013728767709-o6q514dv1kk6h8eh3asqsalkdi3tbfud.apps.googleusercontent.com",  // Thay bằng Client ID của bạn
        callback: handleCredentialResponse
    });

    // Hiển thị hộp thoại đăng nhập Google
    google.accounts.id.prompt(); 
}

function handleCredentialResponse(response) {
    console.log("Encoded JWT ID token: " + response.credential);
    if (response.credential) {
        // Gửi token đến server để xác thực
        window.location.href = '/authentication/auth/google?token=' + response.credential;
    } else {
        console.log("No credential received.");
    }
}
