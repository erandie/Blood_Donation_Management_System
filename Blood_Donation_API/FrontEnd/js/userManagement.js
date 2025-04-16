$(document).ready(function () {
    loadRoles();
});

function getAuthHeaders() {
    return {
        "Authorization": localStorage.getItem("token"),
        "Content-Type": "application/json"
    };
}

function loadRoles(){
    $.ajax({
        url: "http://localhost:8080/api/v1/role/get",
        headers: getAuthHeaders(),
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
        headers: getAuthHeaders(),
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
        success:function (res) {
            if (res.code === 201) {
                alert("saved!")
                getAllUsers();
                clearForm();
            } else {
                alert("Failed to save: " + res.message);
            }
        },

        error: function (xhr) {
            let errMsg = xhr.responseJSON?.message || "Something went wrong!";
            alert("Error: " + errMsg);
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
        headers: getAuthHeaders(),
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
        success: function (res) {
            if (res.code === 200) {
                alert("Updated!");
                getAllUsers();
                clearForm();
            } else {
                alert("Failed to update: " + res.message);
            }
        },

        error: function (xhr) {
            let errMsg = xhr.responseJSON?.message || "Something went wrong!";
            alert("Error: " + errMsg);
        }
    })
}

function deleteUser(){
    let userId = $('#userId').val();

    $.ajax({
        method:"DELETE",
        url:"http://localhost:8080/api/v1/user/delete/"+userId,
        headers: getAuthHeaders(),
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
        headers: getAuthHeaders(),
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

$(document).ready(function (){
    let timeout = null;
    $('#searchInput').on('input', function (){
        clearTimeout(timeout);
        timeout = setTimeout(function (){
            const query = $('#searchInput').val().trim();
            searchUsers(query);
        }, 300);
    });

    $('#searchInput').on('keypress', function (e){
        if (e.which === 13){
            e.preventDefault();
            const query = $('#searchInput').val().trim();
            searchUsers(query);
        }
    });
});

function searchUsers(name){
    $.ajax({
        method: "GET",
        url: "http://localhost:8080/api/v1/user/search?name=" + encodeURIComponent(name),
        headers:getAuthHeaders(),
        success:function (data){
            const tableBody = $("#userTable");
            tableBody.empty();
            data.data.forEach(user => {
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
            });
        },

        error: function (xhr){
            alert("Search Failed!")
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
        headers: getAuthHeaders(),
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






























