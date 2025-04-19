$(document).ready(function () {
    loadBloodTypes();
    loadDonorIDs();
    loadEmployeeIDs();
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
        method: "GET",
        headers: getAuthHeaders(),
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

function loadEmployeeIDs(){
    $.ajax({
        url:"http://localhost:8080/api/v1/employee/get",
        headers: getAuthHeaders(),
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
        headers: getAuthHeaders(),
        async:true,
        data:JSON.stringify({
            "donationId":"",
            "donorId":donorId,
            "bloodPoints":points,
            "bloodType":bloodTypes,
            "empId":empId,
            "selectedDate":date
        }),

        success:function (res) {
            if (res.code === 201) {
                alert("saved!")
                getAllDonations_ii();
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
        headers: getAuthHeaders(),
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

        success: function (res) {
            if (res.code === 200) {
                alert("Updated!");
                getAllDonations_ii();
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

function deleteDetails() {
    let donationId = $('#donationID').val();

    $.ajax({
        method: "DELETE",
        url: "http://localhost:8080/api/v1/donation/delete/" + donationId,
        headers: getAuthHeaders(),
        async: true,

        success: function (data) {
            alert("deleted!")
            $("#donationID").val("")
            $("#select-donorID").val("")
            $("#exampleFormControlInput1").val("")
            $("#select-bloodType").val("")
            $("#select-empID").val("")
            $("#datePicker").val("")

            getAllDonations_ii();
        },

        error: function (xhr, exception) {
            alert("Error!")
        }
    })
}

function getAllDonations_ii(){
    $.ajax({
        method: "GET",
        url:"http://localhost:8080/api/v1/donation/get",
        headers: getAuthHeaders(),
        success:function (data) {
            console.log(data);
            let tableBody = $("#donationTable");
            tableBody.empty();
            data.forEach(donation => {
                tableBody.append(`
                <tr>
                    <td>${donation.donationId}</td>
                    <td>${donation.donorId}</td>
                    <td>${donation.bloodPoints + "milliliters"}</td>
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

function clearForm(){
    $("#donationID").val("")
    $("#select-donorID").val("")
    $("#exampleFormControlInput1").val("")
    $("#select-bloodType").val("")
    $("#select-empID").val("")
    $("#datePicker").val("")
}

function setupDonationSearch() {
    let timeout = null;
    $('#searchInput').on('input', function (){
        clearTimeout(timeout);
        timeout = setTimeout(function (){
            const query = $('#searchInput').val().trim();
            searchDonations(query);
        }, 300);
    });

    $('#searchInput').on('keypress', function (e){
        if (e.which === 13){
            e.preventDefault();
            const query = $('#searchInput').val().trim();
            searchDonations(query);
        }
    });
}

function searchDonations(donationId){
    console.log("Searching for donationId:", donationId);
    $.ajax({
        method: "GET",
        url: "http://localhost:8080/api/v1/donation/search?donationId=" + encodeURIComponent(donationId),
        headers:getAuthHeaders(),
        success:function (data){
            console.log("Response:", data);
            const tableBody = $("#donationTable");
            tableBody.empty();
            const donation = data.data || data;

            const donations = Array.isArray(donation) ? donation : [donation];

            donations.forEach(donation => {
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

        error: function (xhr){
            alert("Search Failed!")
        }

    })
}


$(document).ready(function () {
    $(document).on('click', '#donationTable tr', function () {
        let col0 = $(this).find('td:eq(0)').text();
        let col1 = $(this).find('td:eq(1)').text();
        let col2 = $(this).find('td:eq(2)').text();
        let col3 = $(this).find('td:eq(3)').text();
        let col4 = $(this).find('td:eq(4)').text();
        let col5 = $(this).find('td:eq(5)').text();

        $('#donationID').val(col0);
        $('#select-donorID').val(col1);
        $('#exampleFormControlInput1').val(col2);
        $('#select-bloodType').val(col3);
        $('#select-empID').val(col4);
        $('#datePicker').val(col5);
    });
})






















































