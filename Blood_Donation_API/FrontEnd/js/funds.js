$(document).ready(function () {
    loadPaymentMethods();
    loadDonorIDs();
    loadPatientIDs();
});

function getAuthHeaders() {
    return {
        "Authorization": localStorage.getItem("token"),
        "Content-Type": "application/json"
    };
}

function loadPaymentMethods() {
    $.ajax({
        url: "http://localhost:8080/api/v1/paymentMethod/get",
        headers: getAuthHeaders(),
        method: "GET",
        success: function (paymentMethods) {
            console.log("Payment Methods Data:", paymentMethods);
            const selectPaymentMethod = $("#select-paymentMethode");
            selectPaymentMethod.empty();

            paymentMethods.forEach(paymentMethod => {
                selectPaymentMethod.append(`<option value="${paymentMethod}">${paymentMethod.replace("_", " ")}</option>`);
            });
        },
        error: function (xhr, status, error) {
            console.error("Error Loading Payment Methods!", error);
        }
    });
}

function loadDonorIDs(){
    $.ajax({
        url:"http://localhost:8080/api/v1/donor/get",
        headers: getAuthHeaders(),
        method: "GET",
        success: function(donors) {
            const selectDonorId = $("#select-donorID");
            selectDonorId.empty();

            donors.forEach(donor => {
                selectDonorId.append(`<option value="${donor.donorId}">${donor.donorId}</option>`);
            });
        },

        error:function (err) {
            console.error("error loading donor IDs : ", err)
        }

    })
}

function loadPatientIDs(){
    $.ajax({
        url: "http://localhost:8080/api/v1/patient/get",
        headers: getAuthHeaders(),
        method: "GET",
        success: function (patients) {
            const selectPatientsId = $("#select-patientID");
            selectPatientsId.empty();

            patients.forEach(patient => {
                selectPatientsId.append(`<option value="${patient.patientId}">${patient.patientId}</option>`);
            });
        },

        error:function (err) {
            console.error("error Loading patient IDs : ", err)
        }

    })
}

function saveFunds(){
    let fundsId = parseInt($('#exampleFormControlInput19').val());
    let description = $("#exampleFormControlInput20").val();
    let amount = $("#exampleFormControlInput21").val();
    let paymentMethod = $("#select-paymentMethode").val();
    let donor_id = $("#select-donorID").val();
    let patient_id = $("#select-patientID").val();

    $.ajax({
        method: "POST",
        contentType: "application/json",
        url: "http://localhost:8080/api/v1/funds/save",
        headers: getAuthHeaders(),
        async:true,
        data:JSON.stringify({
            "fundId":fundsId,
            "description":description,
            "amount":amount,
            "paymentMethod":paymentMethod,
            "donorId": donor_id,
            "patientId": patient_id
        }),

        success:function (res) {
            if (res.code === 201) {
                alert("saved!")
                getAllFunds();
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

function updateFunds(){
    let fundsId = $('#exampleFormControlInput19').val();
    let description = $("#exampleFormControlInput20").val();
    let amount = $("#exampleFormControlInput21").val();
    let paymentMethod = $("#select-paymentMethode").val();
    let donor_id = $("#select-donorID").val();
    let patient_id = $("#select-patientID").val();

    $.ajax({
        method: "PUT",
        contentType: "application/json",
        headers: getAuthHeaders(),
        url: "http://localhost:8080/api/v1/funds/update",
        async:true,
        data:JSON.stringify({
            "fundId":fundsId,
            "description":description,
            "amount":amount,
            "paymentMethod":paymentMethod,
            "donorId": donor_id,
            "patientId": patient_id
        }),

        success: function (res) {
            if (res.code === 200) {
                alert("Updated!");
                getAllFunds();
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

function deleteFunds(){
    let fundsId = $('#exampleFormControlInput19').val();

    $.ajax({
        method: "DELETE",
        contentType: "application/json",
        url: "http://localhost:8080/api/v1/funds/delete/"+fundsId,
        headers: getAuthHeaders(),
        async:true,

        success:function () {
            alert("Deleted!")
            $("#exampleFormControlInput19").val("");
            $("#exampleFormControlInput20").val("");
            $("#exampleFormControlInput21").val("");
            $("#select-paymentMethode").val("");
            $("#select-donorID").val("");
            $("#select-patientID").val("");
            getAllFunds();

        },

        error: function (xhr, exception){
            alert("Error!")
        }
    })
}

function getAllFunds(){
    $.ajax({
        method: "GET",
        contentType: "application/json",
        url: "http://localhost:8080/api/v1/funds/get",
        headers: getAuthHeaders(),
        success:function (data) {
            let tableBody = $("#fundsTable");
            tableBody.empty();
            data.forEach(funds => {
                tableBody.append(`
                    <tr>
                        <td>${funds.fundId}</td>
                        <td>${funds.description}</td>
                        <td>${funds.amount}</td>
                        <td>${funds.paymentMethod}</td>
                        <td>${funds.donorId}</td>
                        <td>${funds.patientId}</td>
                </tr>
               `)
            });
        },

        error: function (xhr, exception){
            alert("Error!")
        }
    })
}

function setupFundsSearch() {
    let timeOut = null;
    $('#searchInput').on('input', function () {
        clearTimeout(timeOut);
        timeOut = setTimeout(function () {
            const query = $('#searchInput').val().trim();
            searchFunds(query);
            }, 300 );
        });

    $('#searchInput').on('keypress', function (e) {
        if (e.which === 13) {
            e.preventDefault();
            const query = $('#searchInput').val().trim();
            searchFunds(query);
        }
    });
}

function searchFunds(description) {
    $.ajax({
        method: "GET",
        url: "http://localhost:8080/api/v1/funds/search?description=" + encodeURIComponent(description),
        headers: getAuthHeaders(),
        success: function (data) {
            const tableBody = $("#fundsTable");
            tableBody.empty();
            data.data.forEach(funds => {
                tableBody.append(`
                    <tr>
                        <td>${funds.fundId}</td>
                        <td>${funds.description}</td>
                        <td>${funds.amount}</td>
                        <td>${funds.paymentMethod}</td>
                        <td>${funds.donorId}</td>
                        <td>${funds.patientId}</td>
                </tr>
               `)
            });
        },

        error: function (xhr){
            alert("Search Failed!")
        }

    })
}

function clearForm(){
    $("#exampleFormControlInput19").val("")
    $("#exampleFormControlInput20").val("")
    $("#exampleFormControlInput21").val("")
    $("#select-paymentMethode").val("")
    $("#select-donorID").val("")
    $("#select-patientID").val("")
}

$(document).ready(function () {
    $(document).on('click', '#fundsTable tr', function () {
        var col0 = $(this).find('td:eq(0)').text();
        var col1 = $(this).find('td:eq(1)').text();
        var col2 = $(this).find('td:eq(2)').text();
        var col3 = $(this).find('td:eq(3)').text();
        var col4 = $(this).find('td:eq(4)').text();
        var col5 = $(this).find('td:eq(5)').text();

        $('#exampleFormControlInput19').val(col0);
        $('#exampleFormControlInput20').val(col1);
        $('#exampleFormControlInput21').val(col2);
        $('#select-paymentMethode').val(col3);
        $('#select-donorID').val(col4);
        $('#select-patientID').val(col5);

    })
})


















