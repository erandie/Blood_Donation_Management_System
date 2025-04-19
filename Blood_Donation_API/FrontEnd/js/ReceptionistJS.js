function getAuthHeaders() {
    return {
        "Authorization": localStorage.getItem("token"),
        "Content-Type": "application/json"
    };
}

function saveReceptionist(){
    let id = $('#exampleFormControlInput1').val();
    let name = $('#exampleFormControlInput2').val();
    let email = $('#exampleFormControlInput3').val();
    let address = $('#exampleFormControlInput4').val();

    $.ajax({
        method:"POST",
        contentType:"application/json",
        url:"http://localhost:8080/api/v1/receptionist/save",
        headers: getAuthHeaders(),
        async:true,
        data:JSON.stringify({
            "receptionistId":id,
            "name":name,
            "email":email,
            "address":address
        }),
        success:function (res) {
            if (res.code === 201) {
                alert("saved!");
                getAllReceptionists();
                clearForm();
            } else {
                alert("Failed to save: " + res.message);
            }
        },

        error: function (xhr) {
            let errMsg = xhr.responseJSON?.message || "Something went wrong!";
            alert("Error: " + errMsg);
        }
    })
}

function updateReceptionist(){
    let recId = $('#exampleFormControlInput1').val();
    let name = $('#exampleFormControlInput2').val();
    let email = $('#exampleFormControlInput3').val();
    let address = $('#exampleFormControlInput4').val();

    $.ajax({
        method:"PUT",
        contentType:"application/json",
        headers: getAuthHeaders(),
        url:"http://localhost:8080/api/v1/receptionist/update",
        async:true,
        data:JSON.stringify({
            "receptionistId":recId,
            "name":name,
            "email":email,
            "address":address
        }),

        success: function (res) {
            if (res.code === 200) {
                alert("Updated!");
                getAllReceptionists();
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

function deleteReceptionist(){
    let recId = $('#exampleFormControlInput1').val();

    $.ajax({
        method:"DELETE",
        url:"http://localhost:8080/api/v1/receptionist/delete/"+recId,
        headers: getAuthHeaders(),
        async:true,
        success:function (data) {
            alert("deleted!")
            clearForm();

            getAllReceptionists()
        },
        error: function (xhr, exception) {
            alert("Error!")
        }
    })
}

function getAllReceptionists(){

    $.ajax({
        method:"GET",
        url:"http://localhost:8080/api/v1/receptionist/get",
        headers: getAuthHeaders(),
        success:function (data) {
            let tableBody = $("#receptionistTable");
            tableBody.empty();
            data.forEach(receptionist => {
                tableBody.append(`
                    <tr>
                        <td>${receptionist.receptionistId}</td>
                        <td>${receptionist.name}</td>
                        <td>${receptionist.email}</td>
                        <td>${receptionist.address}</td>
                    </tr>
                `);
            })
        },
        error: function (xhr, exception) {
            alert("Error!")
        }
    })
}

function setupReceptionistSearch() {
    let timeout = null;
    $('#searchInput').on('input', function (){
        clearTimeout(timeout);
        timeout = setTimeout(function (){
            const query = $('#searchInput').val().trim();
            searchReceptionist(query);
        }, 300);
    });

    $('#searchInput').on('keypress', function (e){
        if (e.which === 13){
            e.preventDefault();
            const query = $('#searchInput').val().trim();
            searchReceptionist(query);
        }
    });
}

function searchReceptionist(name){
    $.ajax({
        method: "GET",
        url: "http://localhost:8080/api/v1/receptionist/search?name=" + encodeURIComponent(name),
        headers:getAuthHeaders(),
        success:function (data){
            const tableBody = $("#receptionistTable");
            tableBody.empty();
            data.data.forEach(receptionist => {
                tableBody.append(`
                    <tr>
                        <td>${receptionist.receptionistId}</td>
                        <td>${receptionist.name}</td>
                        <td>${receptionist.email}</td>
                        <td>${receptionist.address}</td>
                    </tr>
                `);
            });
        },

        error: function (xhr){
            alert("Search Failed!")
        }

    })
}



function clearForm(){
    $("#exampleFormControlInput1").val("");
    $("#exampleFormControlInput2").val("");
    $("#exampleFormControlInput3").val("");
    $("#exampleFormControlInput4").val("");
}

$(document).ready(function () {
    $(document).on('click', '#receptionistTable tr', function () {
        var col0 = $(this).find('td:eq(0)').text();
        var col1 = $(this).find('td:eq(1)').text();
        var col2 = $(this).find('td:eq(2)').text();
        var col3 = $(this).find('td:eq(3)').text();

        $('#exampleFormControlInput1').val(col0);
        $('#exampleFormControlInput2').val(col1);
        $('#exampleFormControlInput3').val(col2);
        $('#exampleFormControlInput4').val(col3);

    })
})






