$(document).ready(function () {
    loadBloodBankIDs();
});

function loadBloodBankIDs() {
    $.ajax({
        url: "http://localhost:8080/api/v1/bloodBank/get",
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
        async:true,
        data:JSON.stringify({
            "patientId":"",
            "name":name,
            "email":email,
            "address":address,
            "bloodBankId": bloodBank_id
        }),
        success:function (data) {
            alert("saved!")
            getAllPatient();
            $("#exampleFormControlInput15").val("");
            $("#exampleFormControlInput16").val("");
            $("#exampleFormControlInput17").val("");
            $("#exampleFormControlInput18").val("");
            $("#select-bloodBank").val("");

        },
        error: function (xhr, exception) {
            alert("Error!")
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
        headers: { "Accept": "application/json" },
        url:"http://localhost:8080/api/v1/patient/update",
        async:true,
        data:JSON.stringify({
            "patientId":patientId,
            "name":name,
            "email":email,
            "address":address,
            "bloodBankId": bloodBank_id
        }),

        success:function (data) {
            alert("updated!")
            getAllPatient();
            $("#exampleFormControlInput15").val("");
            $("#exampleFormControlInput16").val("");
            $("#exampleFormControlInput17").val("");
            $("#exampleFormControlInput18").val("");
            $("#select-bloodBank").val("");

        },
        error: function (xhr, exception) {
            alert("Error!")
        }
    })
}

function deletePatient(){
    let patientId = $('#exampleFormControlInput1').val();

    $.ajax({
        method:"DELETE",
        url:"http://localhost:8080/api/v1/patient/delete/"+patientId,
        async:true,
        success:function (data) {
            alert("deleted!")
            $("#exampleFormControlInput15").val("");
            $("#exampleFormControlInput16").val("");
            $("#exampleFormControlInput17").val("");
            $("#exampleFormControlInput18").val("");
            $("#select-bloodBank").val("");

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






