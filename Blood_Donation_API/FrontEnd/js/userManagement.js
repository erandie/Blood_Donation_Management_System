$(document).ready(function () {
    loadRoles();
});

function loadRoles(){
    $.ajax({
        url: "http://localhost:8080/api/v1/role/get",
        method: "GET",
        success: function (roles){
            const selectRole = $("#select-role");
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

function saveUser() {
    let userId = $('#userId').val();
    let name = $('#userName').val();
    let role = $('#select-role').val();
    let email = $('#userEmail').val();
    let phone = $('#userMobile').val();
    let address = $('#userAddress').val();
    let password = $('#userPassword').val();
    let confirmPassword = $('#userPassword-ii').val();

    if (password !== confirmPassword) {
        alert("Passwords do not match!");
        return;
    }

    $.ajax({
        method: "POST",
        url: "http://localhost:8080/api/v1/user/save",
        contentType: "application/json",
        dataType: "json",  // ✅ Ensure the response is parsed as JSON
        data: JSON.stringify({
            userId: userId,
            name: name,
            role: role,
            email: email,
            mobile: phone,
            address: address,
            password: password
        }),
        success: function (data) {
            alert("Saved!");
            getAllUsers();
            clearFields();
        },
        error: function (xhr) {
            alert("Error: " + xhr.responseText);  // ✅ Show the actual error message
        }
    });
}


function updateUser(){
    let userId = $('#userId').val()
    let name = $('#userName').val();
    let role = $('#select-role').val();
    let email = $('#userEmail').val();
    let phone = $('#userMobile').val();
    let address = $('#userAddress').val();
    let password = $('#userPassword').val();
    let confirmPassword = $('#userPassword-ii').val();

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
            getAllUsers();
        },
        error: function (xhr, exception) {
            alert("Error!")
        }
    })
}

function deleteUser(){
    let userId = $('#userId').val();

    $.ajax({
        method:"DELETE",
        url:"http://localhost:8080/api/v1/user/delete/"+userId,
        async:true,
        success:function (data) {
            alert("deleted!")
            clearFields();
            getAllUsers();
        },

        error: function (xhr, exception) {
            alert("Error!")
        }
    })
}

function getAllUsers(){
    $.ajax({
        method:"GET",
        url:"http://localhost:8080/api/v1/user/get",
        success:function (data) {
            let tableBody = $("#userTable");
            tableBody.empty();
            data.forEach(user => {
                tableBody.append(`
                    <tr>
                        <td>${user.userId}</td>
                        <td>${user.name}</td>
                        <td>${user.role}</td>
                        <td>${user.email}</td>
                        <td>${user.mobile}</td>
                        <td>${user.address}</td>
                        <td id="status-${user.id}">${user.active ? "✅ Active" : "❌ Inactive"}</td>
                        <td>
                                <button class="toggle-btn" data-id="${user.id}" data-status="${user.active}">
                                    ${user.active ? "Deactivate" : "Activate"}
                                </button>
                            </td>
                    </tr>
                `);
            })
        },
        error: function (xhr, exception) {
            alert("Error!")
        }
    })
}

function clearFields(){
    $("#userId").val("");
    $("#userName").val("");
    $("#select-role").val("");
    $("#userEmail").val("");
    $("#userMobile").val("");
    $("#userAddress").val("");
    $("#userPassword").val("");
    $("#userPassword-ii").val("");
}

$(document).ready(function () {
    $(document).on('click', '#userTable tr', function () {
        var col0 = $(this).find('td:eq(0)').text();
        var col1 = $(this).find('td:eq(1)').text();
        var col2 = $(this).find('td:eq(2)').text();
        var col3 = $(this).find('td:eq(3)').text();
        var col4 = $(this).find('td:eq(4)').text();
        var col5 = $(this).find('td:eq(5)').text();

        $('#userId').val(col0);
        $('#userName').val(col1);
        $('#select-role').val(col2);
        $('#userEmail').val(col3);
        $('#userMobile').val(col4);
        $('#userAddress').val(col5);

    })
})

$(document).on("click", ".toggle-btn", function () {
    let userId = $(this).data("userId");
    let currentStatus = $(this).data("status");
    let newStatus = !currentStatus; // Toggle status

    $.ajax({
        url: `http://localhost:8080/api/v1/user/update/${userId}/status`,
        type: "PUT",
        contentType: "application/json",
        data: JSON.stringify({ active: newStatus }),
        success: function () {
            // Update UI instantly
            $(`#status-${userId}`).html(newStatus ? "✅ Active" : "❌ Inactive");
            let button = $(`button[data-id='${userId}']`);
            button.data("status", newStatus);
            button.text(newStatus ? "Deactivate" : "Activate");
        },
        error: function () {
            alert("Failed to update user status.");
        }
    });
});






























