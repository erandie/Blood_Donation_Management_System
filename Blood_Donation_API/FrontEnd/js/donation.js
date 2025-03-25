$(document).ready(function () {
    loadBloodTypes();
    loadDonorIDs();
    loadEmployeeIDs();
});

function loadBloodTypes() {
    $.ajax({
        url: "http://localhost:8080/api/v1/bloodTypes/get",
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

function loadDonorIDs(){
    $.ajax({
        url:"http://localhost:8080/api/v1/donor/get",
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

function loadEmployeeIDs(){
    $.ajax({
        url:"http://localhost:8080/api/v1/employee/get",
        method:"GET",
        success: function (employees){
            const selectEmpId = $("#select-empID");
            selectEmpId.empty();

            employees.forEach(employee => {
                selectEmpId.append(`<option value="${employee.empId}">${employee.empId}</option>`)
            });
        },

        error: function (err) {
            console.error("error Loading employee IDs : ", err)
        }
    })
}

function saveDetails(){
    let donorId = $("#select-donorID").val();
    let points = parseFloat($('#exampleFormControlInput1').val());
    let bloodTypes = $("#select-bloodType").val();
    let empId = $("#select-empID").val();
    let date = $('#datePicker').val();

    $.ajax({
        method: "POST",
        contentType:"application/json",
        url:"http://localhost:8080/api/v1/donation/save",
        async:true,
        data:JSON.stringify({
            "donationId":"",
            "donorId":donorId,
            "bloodPoints":points,
            "bloodType":bloodTypes,
            "empId":empId,
            "selectedDate":date
        }),

        success:function (data) {
            alert("Saved!")
            getAllDonations();
            $("#donationID").val("")
            $("#select-donorID").val("")
            $("#exampleFormControlInput1").val("")
            $("#select-bloodType").val("")
            $("#select-empID").val("")
            $("#datePicker").val("")


        },

        error: function (xhr, exception) {
            alert("Error!")
        }

    })
}

function updateDetails(){
    let donationId = $("#donationID").val();
    let donorId = $("#select-donorID").val();
    let points = parseFloat($('#exampleFormControlInput1').val());
    let bloodTypes = $("#select-bloodType").val();
    let empId = $("#select-empID").val();
    let date = $('#datePicker').val();

    $.ajax({
        method: "PUT",
        contentType:"application/json",
        headers: { "Accept": "application/json" },
        url:"http://localhost:8080/api/v1/donation/update",
        async:true,
        data:JSON.stringify({
            "donationId":donationId,
            "donorId":donorId,
            "bloodPoints":points,
            "bloodType":bloodTypes,
            "empId":empId,
            "selectedDate":date
        }),

        success:function (data) {
            alert("Updated!")
            getAllDonations();
            $("#donationID").val("")
            $("#select-donorID").val("")
            $("#exampleFormControlInput1").val("")
            $("#select-bloodType").val("")
            $("#select-empID").val("")
            $("#datePicker").val("")
        },

        error: function (xhr, exception) {
            alert("Error!")
        }
    })
}

function deleteDetails() {
    let donationId = $('#donationID').val();

    $.ajax({
        method: "DELETE",
        url: "http://localhost:8080/api/v1/donation/delete/" + donationId,
        async: true,

        success: function (data) {
            alert("deleted!")
            $("#donationID").val("")
            $("#select-donorID").val("")
            $("#exampleFormControlInput1").val("")
            $("#select-bloodType").val("")
            $("#select-empID").val("")
            $("#datePicker").val("")

            getAllDonations();
        },

        error: function (xhr, exception) {
            alert("Error!")
        }
    })
}

function getAllDonations(){
    $.ajax({
        method: "GET",
        url:"http://localhost:8080/api/v1/donation/get",
        success:function (data) {
            let tableBody = $("#donationTable");
            tableBody.empty();
            data.forEach(donation => {
                tableBody.append(`
                <tr>
                    <td>${donation.donationId}</td>
                    <td>${donation.donorId}</td>
                    <td>${donation.bloodPoints}</td>
                    <td>${donation.bloodType}</td>
                    <td>${donation.empId}</td>
                    <td>${donation.selectedDate}</td>
                    
                    
                </tr>
                `)
            });
        },

        error: function (xhr, exception) {
            alert("Error!")
        }
    })
}

$(document).ready(function () {
    $(document).on('click', '#donationTable tr', function () {
        var col0 = $(this).find('td:eq(0)').text();
        var col1 = $(this).find('td:eq(1)').text();
        var col2 = $(this).find('td:eq(2)').text();
        var col3 = $(this).find('td:eq(3)').text();
        var col4 = $(this).find('td:eq(4)').text();
        var col5 = $(this).find('td:eq(5)').text();

        $('#donationID').val(col0);
        $('#exampleFormControlInput1').val(col1);
        $('#select-bloodType').val(col2);
        $('#datePicker').val(col3);
        $('#select-donorID').val(col4);
        $('#select-empID').val(col5);

    })
})






























