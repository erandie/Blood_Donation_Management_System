document.getElementById("loginBtn").addEventListener("click", async () => {
    const email = document.getElementById("username").value;
    const password = document.getElementById("password").value;

    try{
        const res = await fetch("http://localhost:8080/api/v1/auth/login", {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify({ email, password })
        });

        const data = await res.json();

        console.log(data);

        if (res.ok) {
            const token = data.data.token;
            const role = data.data.role;

            localStorage.setItem("token", "Bearer " + token);
            console.log("Token saved:", localStorage.getItem("token"));

            if (role === "ADMIN") {
                window.location.href = "adminDashboard.html";
            } else if (role === "USER") {
                window.location.href = "userDashboard.html";
            } else {
                alert("Unknown role: " + role)
            }
        } else {
            alert("Login Failed: " + data.message);
        }
    } catch (err){
        console.error(err);
        alert("Something went wrong!")
    }
});



























