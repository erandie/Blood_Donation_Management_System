$(document).ready(function () {
    loadBloodTypes();
});

function getAuthHeaders() {
    return {
        "Authorization": localStorage.getItem("token"),
        "Content-Type": "application/json"
    };
}


function loadBloodTypes() {
    $.ajax({
        url: "http://localhost:8080/api/v1/bloodTypes/get",
        headers: getAuthHeaders(),
        method: "GET",
        success: function (bloodTypes) {
            const bloodTypeSelect = $("#select-bloodType");
            bloodTypeSelect.empty();

            bloodTypes.forEach(bloodType => {
                bloodTypeSelect.append(`<option value="${bloodType}">${bloodType.replace("_", " ")}</option>`);
            });
        },
        error: function (xhr, status, error) {
            console.error("Error loading blood types:", error);
        }
    });
}

function saveDonor(){
    let name = $('#exampleFormControlInput2').val();
    let email = $('#exampleFormControlInput3').val();
    let address = $('#exampleFormControlInput4').val();
    let bloodType = $("#select-bloodType").val();

    $.ajax({
        method:"POST",
        contentType:"application/json",
        url:"http://localhost:8080/api/v1/donor/save",
        headers: getAuthHeaders(),
        async:true,
        data:JSON.stringify({
            "donorId":"",
            "name":name,
            "email":email,
            "address":address,
            "bloodType":bloodType
        }),
        success:function (res) {
            if (res.code === 201) {
                alert("saved!")
                getAllDonor();
                clearForm()
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

function updateDonor(){
    let donorId = $('#exampleFormControlInput1').val();
    let name = $('#exampleFormControlInput2').val();
    let email = $('#exampleFormControlInput3').val();
    let address = $('#exampleFormControlInput4').val();
    let bloodType = $("#select-bloodType").val();

    $.ajax({
        method:"PUT",
        contentType:"application/json",
        headers: getAuthHeaders(),
        url:"http://localhost:8080/api/v1/donor/update",
        async:true,
        data:JSON.stringify({
            "donorId":donorId,
            "name":name,
            "email":email,
            "address":address,
            "bloodType":bloodType
        }),

        success: function (res) {
            if (res.code === 200) {
                alert("Updated!");
                getAllDonor();
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

function deleteDonor(){
    let donorId = $('#exampleFormControlInput1').val();

    $.ajax({
        method:"DELETE",
        url:"http://localhost:8080/api/v1/donor/delete/"+donorId,
        headers: getAuthHeaders(),
        async:true,
        success:function (data) {
            alert("deleted!")
            clearForm();

            getAllDonor()
        },
        error: function (xhr, exception) {
            alert("Error!")
        }
    })
}

function getAllDonor(){
    $.ajax({
        method:"GET",
        url:"http://localhost:8080/api/v1/donor/get",
        headers: getAuthHeaders(),
        success:function (data) {
            let tableBody = $("#DonorTable");
            tableBody.empty();
            data.forEach(donor => {
                tableBody.append(`
                    <tr>
                        <td>${donor.donorId}</td>
                        <td>${donor.name}</td>
                        <td>${donor.email}</td>
                        <td>${donor.address}</td>
                        <td>${donor.bloodType}</td>
                    </tr>
                `);
            })
        },
        error: function (xhr, exception) {
            alert("Error!")
        }
    })
}

function setupDonorSearch() {
    let timeout = null;
    $('#searchInput').on('input', function (){
        clearTimeout(timeout);
        timeout = setTimeout(function (){
            const query = $('#searchInput').val().trim();
            searchDonors(query);
        }, 300);
    });

    $('#searchInput').on('keypress', function (e){
        if (e.which === 13){
            e.preventDefault();
            const query = $('#searchInput').val().trim();
            searchDonors(query);
        }
    });
}

function searchDonors(name){
    $.ajax({
        method: "GET",
        url: "http://localhost:8080/api/v1/donor/search?name=" + encodeURIComponent(name),
        headers:getAuthHeaders(),
        success:function (data){
            const tableBody = $("#DonorTable");
            tableBody.empty();
            data.data.forEach(donor => {
                tableBody.append(`
                    <tr>
                        <td>${donor.donorId}</td>
                        <td>${donor.name}</td>
                        <td>${donor.email}</td>
                        <td>${donor.address}</td>
                        <td>${donor.bloodType}</td>
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
    $("#select-bloodBank").val("");
}

$(document).ready(function () {
    $(document).on('click', '#DonorTable tr', function () {
        var col0 = $(this).find('td:eq(0)').text();
        var col1 = $(this).find('td:eq(1)').text();
        var col2 = $(this).find('td:eq(2)').text();
        var col3 = $(this).find('td:eq(3)').text();
        var col4 = $(this).find('td:eq(4)').text();

        $('#exampleFormControlInput1').val(col0);
        $('#exampleFormControlInput2').val(col1);
        $('#exampleFormControlInput3').val(col2);
        $('#exampleFormControlInput4').val(col3);
        $("#select-bloodBank").val(col4);

    })
})






