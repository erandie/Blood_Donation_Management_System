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
        async:true,
        data:JSON.stringify({
            "empId":"",
            "name":name,
            "contact":contact,
            "address":address,
            "bloodBankId": bloodBank_id
        }),
        success:function (data) {
            alert("saved!")
            getAllEmployee();
            $("#exampleFormControlInput1").val("");
            $("#exampleFormControlInput2").val("");
            $("#exampleFormControlInput3").val("");
            $("#exampleFormControlInput4").val("");
            $("#select-bloodBank").val("");

        },
        error: function (xhr, exception) {
            alert("Error!")
        }
    })
}

function updateEmployee(){
    let empId = $('#exampleFormControlInput1').val();
    let name = $('#exampleFormControlInput2').val();
    let contact = $('#exampleFormControlInput3').val();
    let address = $('#exampleFormControlInput4').val();
    let bloodBank_id = $('#select-bloodBank').val();

    $.ajax({
        method:"PUT",
        contentType:"application/json",
        headers: { "Accept": "application/json" },
        url:"http://localhost:8080/api/v1/employee/update",
        async:true,
        data:JSON.stringify({
            "empId":empId,
            "name":name,
            "contact":contact,
            "address":address,
            "bloodBankId": bloodBank_id
        }),

        success:function (data) {
            alert("updated!")
            getAllEmployee();
            $("#exampleFormControlInput1").val("");
            $("#exampleFormControlInput2").val("");
            $("#exampleFormControlInput3").val("");
            $("#exampleFormControlInput4").val("");
            $("#select-bloodBank").val("");

        },
        error: function (xhr, exception) {
            alert("Error!")
        }
    })
}

function deleteEmployee(){
    let empID = $('#exampleFormControlInput1').val();

    $.ajax({
        method:"DELETE",
        url:"http://localhost:8080/api/v1/employee/delete/"+empID,
        async:true,
        success:function (data) {
            alert("deleted!")
            $("#exampleFormControlInput1").val("");
            $("#exampleFormControlInput2").val("");
            $("#exampleFormControlInput3").val("");
            $("#exampleFormControlInput4").val("");
            $("#select-bloodBank").val("");

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






