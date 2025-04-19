$(document).ready(function () {
    loadBloodBankIDs();
});

function getAuthHeaders() {
    return {
        "Authorization": localStorage.getItem("token"),
        "Content-Type": "application/json"
    };
}

function loadBloodBankIDs() {
    $.ajax({
        url: "http://localhost:8080/api/v1/bloodBank/get",
        headers: getAuthHeaders(),
        method: "GET",
        success: function (bloodBanks) {
            const selectBloodBank = $("#select-bloodBank");
            selectBloodBank.empty();

            bloodBanks.forEach(bloodBank => {
                selectBloodBank.append(`<option value="${bloodBank.bloodBankId}">${bloodBank.bloodBankId}</option>`);
            });
        },
        error: function (err) {
            console.error("Error loading blood banks:", err);
        }
    });
}

function savePatient(){
    let name = $('#exampleFormControlInput16').val();
    let email = $('#exampleFormControlInput17').val();
    let address = $('#exampleFormControlInput18').val();
    let bloodBank_id = $('#select-bloodBank').val();

    $.ajax({
        method:"POST",
        contentType:"application/json",
        url:"http://localhost:8080/api/v1/patient/save",
        headers: getAuthHeaders(),
        async:true,
        data:JSON.stringify({
            "patientId":"",
            "name":name,
            "email":email,
            "address":address,
            "bloodBankId": bloodBank_id
        }),
        success:function (res) {
            if (res.code === 201) {
                alert("saved!")
                getAllPatient();
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

function updatePatient(){
    let patientId = $('#exampleFormControlInput15').val();
    let name = $('#exampleFormControlInput16').val();
    let email = $('#exampleFormControlInput17').val();
    let address = $('#exampleFormControlInput18').val();
    let bloodBank_id = $('#select-bloodBank').val();

    $.ajax({
        method:"PUT",
        contentType:"application/json",
        headers: getAuthHeaders(),
        url:"http://localhost:8080/api/v1/patient/update",
        async:true,
        data:JSON.stringify({
            "patientId":patientId,
            "name":name,
            "email":email,
            "address":address,
            "bloodBankId": bloodBank_id
        }),

        success: function (res) {
            if (res.code === 200) {
                alert("Updated!");
                getAllPatient();
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

function deletePatient(){
    let patientId = $('#exampleFormControlInput15').val();

    $.ajax({
        method:"DELETE",
        url:"http://localhost:8080/api/v1/patient/delete/"+patientId,
        headers: getAuthHeaders(),
        async:true,
        success:function (data) {
            alert("deleted!")
            clearForm();
            getAllPatient()
        },
        error: function (xhr, exception) {
            alert("Error!")
        }
    })
}

function getAllPatient(){

    $.ajax({
        method:"GET",
        url:"http://localhost:8080/api/v1/patient/get",
        headers: getAuthHeaders(),
        success:function (data) {
            let tableBody = $("#patientTable");
            tableBody.empty();
            data.forEach(patient => {
                tableBody.append(`
                    <tr>
                        <td>${patient.patientId}</td>
                        <td>${patient.name}</td>
                        <td>${patient.email}</td>
                        <td>${patient.address}</td>
                        <td>${patient.bloodBankId}</td>
                    </tr>
                `);
            })
        },
        error: function (xhr, exception) {
            alert("Error!")
        }
    })
}

function setupPatientSearch() {
    let timeout = null;
    $('#searchInput').on('input', function (){
        clearTimeout(timeout);
        timeout = setTimeout(function (){
            const query = $('#searchInput').val().trim();
            searchPatients(query);
        }, 300);
    });

    $('#searchInput').on('keypress', function (e){
        if (e.which === 13){
            e.preventDefault();
            const query = $('#searchInput').val().trim();
            searchPatients(query);
        }
    });
}

function searchPatients(name){
    $.ajax({
        method: "GET",
        url: "http://localhost:8080/api/v1/patient/search?name=" + encodeURIComponent(name),
        headers:getAuthHeaders(),
        success:function (data){
            const tableBody = $("#patientTable");
            tableBody.empty();
            data.data.forEach(patient => {
                tableBody.append(`
                    <tr>
                        <td>${patient.patientId}</td>
                        <td>${patient.name}</td>
                        <td>${patient.email}</td>
                        <td>${patient.address}</td>
                        <td>${patient.bloodBankId}</td>
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
    $("#exampleFormControlInput15").val("");
    $("#exampleFormControlInput16").val("");
    $("#exampleFormControlInput17").val("");
    $("#exampleFormControlInput18").val("");
    $("#select-bloodBank").val("");
}

$(document).ready(function () {
    $(document).on('click', '#patientTable tr', function () {
        var col0 = $(this).find('td:eq(0)').text();
        var col1 = $(this).find('td:eq(1)').text();
        var col2 = $(this).find('td:eq(2)').text();
        var col3 = $(this).find('td:eq(3)').text();
        var col4 = $(this).find('td:eq(4)').text();

        $('#exampleFormControlInput15').val(col0);
        $('#exampleFormControlInput16').val(col1);
        $('#exampleFormControlInput17').val(col2);
        $('#exampleFormControlInput18').val(col3);
        $('#select-bloodBank').val(col4);

    })
})









































