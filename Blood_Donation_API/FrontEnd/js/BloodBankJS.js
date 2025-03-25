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

            bloodTypes.forEach(bloodType => {
                bloodTypeSelect.append(`<option value="${bloodType}">${bloodType.replace("_", " ")}</option>`);
            });
        },
        error: function (xhr, status, error) {
            console.error("Error loading blood types:", error);
        }
    });
}


function saveDetails(){
    let bloodBankId = parseInt($('#exampleFormControlInput9').val());
    let bloodType = $("#select-bloodType").val();
    let points = parseFloat($('#exampleFormControlInput10').val());

    $.ajax({
        method: "POST",
        contentType:"application/json",
        url:"http://localhost:8080/api/v1/bloodBank/save",
        async:true,
        data:JSON.stringify({
            "bloodBankId":bloodBankId,
            "bloodType": bloodType,
            "points":points
        }),

        success:function (){
            alert("Saved!")
            getAllDetails();
            $("#exampleFormControlInput9").val("");
            $("#select-bloodType").val("");
            $("#exampleFormControlInput10").val("");
        },

        error: function (xhr, exception) {
            alert("Error!")
        }

    })

}

function updateDetails(){
    let bloodBankId = $('#exampleFormControlInput9').val();
    let bloodType = $("#select-bloodType").val();
    let points = $('#exampleFormControlInput10').val();

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
            $("#exampleFormControlInput9").val("");
            $("#select-bloodType").val("");
            $("#exampleFormControlInput10").val("");
        },

        error: function (xhr, exception) {
            alert("Error!")
        }

    })

}

function deleteDetails(){
    let bloodBankId = $('#exampleFormControlInput1').val();

    $.ajax({
        method: "DELETE",
        contentType:"application/json",
        url:"http://localhost:8080/api/v1/bloodBank/delete/"+bloodBankId,
        async:true,

        success:function (){
            alert("deleted!")
            $("#exampleFormControlInput9").val("");
            $("#select-bloodType").val("");
            $("#exampleFormControlInput10").val("");
            getAllDetails();
        },

        error: function (xhr, exception) {
            alert("Error!")
        }

    })

}

function getAllDetails(){
    $.ajax({
        method: "GET",
        url:"http://localhost:8080/api/v1/bloodBank/get",
        success:function (data) {
            let tableBody = $("#bloodBankTable");
            tableBody.empty();
            data.forEach(bloodBank => {
                tableBody.append(`
                <tr>
                    <td>${bloodBank.bloodBankId}</td>
                    <td>${bloodBank.bloodType}</td>
                    <td>${bloodBank.points}</td>
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























