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
        method: "GET",
        headers: getAuthHeaders(),
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


function saveEmployee(){
    let name = $('#exampleFormControlInput2').val();
    let contact = $('#exampleFormControlInput3').val();
    let address = $('#exampleFormControlInput4').val();
    let bloodBank_id = $('#select-bloodBank').val();

    console.log("blood_bank_id:", bloodBank_id);

    $.ajax({
        method:"POST",
        contentType:"application/json",
        url:"http://localhost:8080/api/v1/employee/save",
        headers: getAuthHeaders(),
        async:true,
        data:JSON.stringify({
            "empId":"",
            "name":name,
            "contact":contact,
            "address":address,
            "bloodBankId": bloodBank_id
        }),
        success:function (res) {
            if (res.code === 201) {
                alert("saved!")
                getAllEmployee();
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

function updateEmployee() {
    let empId = $('#exampleFormControlInput1').val();
    let name = $('#exampleFormControlInput2').val();
    let contact = $('#exampleFormControlInput3').val();
    let address = $('#exampleFormControlInput4').val();
    let bloodBank_id = $('#select-bloodBank').val();

    if (!empId) {
        alert("Please select an employee to update!");
        return;
    }

    $.ajax({
        method: "PUT",
        contentType: "application/json",
        headers: getAuthHeaders(),
        url: "http://localhost:8080/api/v1/employee/update",
        async: true,
        data: JSON.stringify({
            "empId": empId,
            "name": name,
            "contact": contact,
            "address": address,
            "bloodBankId": bloodBank_id
        }),
        success: function (res) {
            if (res.code === 200) {
                alert("Updated!");
                getAllEmployee();
                clearForm();
            } else {
                alert("Failed to update: " + res.message);
            }
        },

        /*error: function (xhr) {
            let errMsg = xhr.responseJSON?.message || "Something went wrong!";
            alert("Error: " + errMsg);
        }*/
        error: function (xhr) {
            let response = xhr.responseJSON;
            let errMsg = "";

            if (typeof response === "object") {
                for (let field in response) {
                    errMsg += response[field] + " ";
                }
            } else {
                errMsg = "Something went wrong!";
            }

            alert("Error: " + errMsg.trim());
        }
    });
}


function deleteEmployee(){
    let empID = $('#exampleFormControlInput1').val();

    $.ajax({
        method:"DELETE",
        url:"http://localhost:8080/api/v1/employee/delete/"+empID,
        headers: getAuthHeaders(),
        async:true,
        success:function (data) {
            alert("deleted!")
            clearForm()
            getAllEmployee()
        },
        error: function (xhr, exception) {
            alert("Error!")
        }
    })
}

function getAllEmployee(){

    $.ajax({
        method:"GET",
        url:"http://localhost:8080/api/v1/employee/get",
        headers: getAuthHeaders(),
        success:function (data) {
            let tableBody = $("#EmployeeTable");
            tableBody.empty();
            data.forEach(employee => {
                tableBody.append(`
                    <tr>
                        <td>${employee.empId}</td>
                        <td>${employee.name}</td>
                        <td>${employee.contact}</td>
                        <td>${employee.address}</td>
                        <td>${employee.bloodBankId}</td>
                    </tr>
                `);
            })
        },
        error: function (xhr, exception) {
            alert("Error!")
        }
    })
}

function clearForm() {
    $("#exampleFormControlInput1").val("");
    $("#exampleFormControlInput2").val("");
    $("#exampleFormControlInput3").val("");
    $("#exampleFormControlInput4").val("");
    $("#select-bloodBank").val("");
}


$(document).ready(function () {
    $(document).on('click', '#EmployeeTable tr', function () {
        var col0 = $(this).find('td:eq(0)').text();
        var col1 = $(this).find('td:eq(1)').text();
        var col2 = $(this).find('td:eq(2)').text();
        var col3 = $(this).find('td:eq(3)').text();
        var col4 = $(this).find('td:eq(4)').text();

        $('#exampleFormControlInput1').val(col0);
        $('#exampleFormControlInput2').val(col1);
        $('#exampleFormControlInput3').val(col2);
        $('#exampleFormControlInput4').val(col3);
        $('#select-bloodBank').val(col4);

    })
})

$(document).ready(function (){
    let timeout = null;
    $('#searchInput').on('input', function (){
        clearTimeout(timeout);
        timeout = setTimeout(function (){
            const query = $('#searchInput').val().trim();
            searchEmployees(query);
        }, 300);
    });

    $('#searchInput').on('keypress', function (e){
        if (e.which === 13){
            e.preventDefault();
            const query = $('#searchInput').val().trim();
            searchEmployees(query);
        }
    });
});

function searchEmployees(name){
    $.ajax({
        method: "GET",
        url: "http://localhost:8080/api/v1/employee/search?name=" + encodeURIComponent(name),
        headers:getAuthHeaders(),
        success:function (data){
            const tableBody = $("#EmployeeTable");
            tableBody.empty();
            data.data.forEach(employee => {
                tableBody.append(`
                    <tr>
                        <td>${employee.empId}</td>
                        <td>${employee.name}</td>
                        <td>${employee.contact}</td>
                        <td>${employee.address}</td>
                        <td>${employee.bloodBankId}</td>
                    </tr>
                `);
            });
        },

        error: function (xhr){
            alert("Search Failed!")
        }

    })
}








































