$(document).ready(function () {
    loadBloodTypes();
});
function loadBloodTypes() {
    $.ajax({
        url: "http://localhost:8080/api/v1/bloodTypes/get",
        method: "GET",
        success: function (bloodTypes) {
            const bloodTypeSelect = $("#select-bloodType");
            bloodTypeSelect.empty();
            bloodTypeSelect.append('<option value="">Select Blood Type</option>');

            bloodTypes.forEach(bloodType => {
                bloodTypeSelect.append(`<option value="${bloodType}">${bloodType.replace("_", " ")}</option>`);
            });
        },
        error: function (xhr, status, error) {
            console.error("Error loading blood types:", error);
        }
    });
}

function getAllDetails() {
    $.ajax({
        method: "GET",
        url: "http://localhost:8080/api/v1/bloodBank/get",
        success: function (data) {
            let tableBody = $("#bloodBankTable");
            tableBody.empty();

            const bloodGroups = {};
            data.forEach(bloodBank => {
                if (!bloodGroups[bloodBank.bloodType]) {
                    bloodGroups[bloodBank.bloodType] = { points: 0, bloodBankId: bloodBank.bloodBankId };
                }
                bloodGroups[bloodBank.bloodType].points += bloodBank.points;
            });

            Object.entries(bloodGroups).forEach(([bloodType, { points, bloodBankId }]) => {
                tableBody.append(`
                    <tr>
                        <td>${bloodBankId}</td>
                        <td>${bloodType}</td>
                        <td>${points}</td>
                    </tr>
                `);
            });
        },
        error: function () {
            alert("Error!");
        }
    });
}

function saveDetails() {
    let bloodBankId = parseInt($('#exampleFormControlInput9').val());
    let bloodType = $("#select-bloodType").val();
    let points = parseFloat($('#exampleFormControlInput10').val());

    if (!bloodType || isNaN(points)) {
        alert("Please select blood type and enter valid points!");
        return;
    }

    $.ajax({
        method: "POST",
        contentType: "application/json",
        url: "http://localhost:8080/api/v1/bloodBank/add-points",
        data: JSON.stringify({
            "bloodBankId": bloodBankId,
            "bloodType": bloodType,
            "points": points
        }),
        success: function () {
            alert("Saved!");
            getAllDetails();
            clearForm();
        },
        error: function () {
            alert("Error!");
        }
    });
}


function updateDetails(){
    let bloodBankId = $('#exampleFormControlInput9').val();
    let bloodType = $("#select-bloodType").val();
    let points = $('#exampleFormControlInput10').val();

    if (!bloodBankId || !bloodType || !points) {
        alert("Please select a record to update first!");
        return;
    }

    $.ajax({
        method: "PUT",
        contentType:"application/json",
        headers: { "Accept": "application/json" },
        url:"http://localhost:8080/api/v1/bloodBank/update",
        async:true,
        data:JSON.stringify({
            "bloodBankId":bloodBankId,
            "bloodType": bloodType,
            "points":points
        }),

        success:function (){
            alert("Updated!")
            getAllDetails();
            clearForm()
        },

        error: function (xhr, exception) {
            alert("Error!")
        }

    })

}

function deleteDetails(){
    let bloodBankId = $('#exampleFormControlInput9').val();

    $.ajax({
        method: "DELETE",
        contentType:"application/json",
        url:"http://localhost:8080/api/v1/bloodBank/delete/"+bloodBankId,
        async:true,

        success:function (){
            alert("deleted!")
            clearForm();
            getAllDetails();
        },

        error: function (xhr, exception) {
            alert("Error!")
        }

    })

}


function clearForm(){
    $("#exampleFormControlInput9").val("");
    $("#select-bloodType").val("");
    $("#exampleFormControlInput10").val("");
}

$(document).ready(function () {
    $(document).on('click', '#bloodBankTable tr', function () {
        var col0 = $(this).find('td:eq(0)').text();
        var col1 = $(this).find('td:eq(1)').text();
        var col2 = $(this).find('td:eq(2)').text();
        var col3 = $(this).find('td:eq(3)').text();

        $('#exampleFormControlInput9').val(col0);
        $('#select-bloodType').val(col1);
        $('#exampleFormControlInput10').val(col2);

    })
})
























