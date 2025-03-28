$(document).ready(function () {
    loadDetailsToProfileInfo(6);
    loadDetailsToProfileForm(6);
    loadRoles();
});

function loadDetailsToProfileInfo(userId) {
    $.ajax({
        url: `http://localhost:8080/api/v1/user/get/${userId}`,
        method: "GET",
        success: function (response) {
            const userName = $("#userName");
            const userRole = $("#userRole");
            const email = $("#userEmail");
            const mobile = $("#userPhone");
            const address = $("#userAddress");

            userName.text(response.data.name);
            userRole.text(response.data.role);
            email.text(response.data.email);
            mobile.text(response.data.mobile);
            address.text(response.data.address);
        },
        error: function (err) {
            console.error("Error Loading User Details: ", err);
        }
    });
}

function loadRoles(){
    $.ajax({
        url: "http://localhost:8080/api/v1/role/get",
        method: "GET",
        success: function (roles){
            const selectRole = $("#role");
            selectRole.empty();

            roles.forEach(role => {
                selectRole.append(`<option value="${role}">${role}</option>`);
            });
        },
        error:function (xhr, status, error){
            console.error("Error loading roles:", error)
        }
    })
}

function loadDetailsToProfileForm(userId) {
    $.ajax({
        url: `http://localhost:8080/api/v1/user/get/${userId}`,
        method: "GET",
        success: function (response) {
            const user = response.data;

            $("#name").val(user.name);
            $("#role").empty().append(`<option value="${user.role}">${user.role}</option>`);
            $("#email").val(user.email);
            $("#phone").val(user.mobile);
            $("#address").val(user.address);
        },
        error: function (err) {
            console.error("Error Loading User Details: ", err);
        }
    });
}

function updateUser(){
    let userId = $('#id').val()
    let name = $('#name').val();
    let role = $('#role').val();
    let email = $('#email').val();
    let phone = $('#phone').val();
    let address = $('#address').val();
    let password = $('#password').val();
    let confirmPassword = $('#confirmPassword').val();

    if (password !== confirmPassword) {
        alert("Passwords do not match!");
        return;
    }

    $.ajax({
        method:"PUT",
        contentType:"application/json",
        headers: { "Accept": "application/json" },
        url: `http://localhost:8080/api/v1/user/update/${userId}`,
        async:true,
        data:JSON.stringify({
            "userId": userId,
            "name":name,
            "role":role,
            "email":email,
            "mobile":phone,
            "address":address,
            "password":password
        }),
        success: function (data) {
            alert("User updated successfully!");
            loadDetailsToProfileForm(userId);
        },
        error: function (xhr, exception) {
            alert("Error!")
        }
    })
}




























