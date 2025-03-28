function saveReceptionist(){
    let name = $('#exampleFormControlInput6').val();
    let email = $('#exampleFormControlInput7').val();
    let address = $('#exampleFormControlInput8').val();

    $.ajax({
        method:"POST",
        contentType:"application/json",
        url:"http://localhost:8080/api/v1/receptionist/save",
        async:true,
        data:JSON.stringify({
            "receptionistId":"",
            "name":name,
            "email":email,
            "address":address
        }),
        success:function (data) {
            alert("saved!")
            getAllReceptionists();
            $("#exampleFormControlInput5").val("");
            $("#exampleFormControlInput6").val("");
            $("#exampleFormControlInput7").val("");
            $("#exampleFormControlInput8").val("");

        },
        error: function (xhr, exception) {
            alert("Error!")
        }
    })
}

function updateReceptionist(){
    let recId = $('#exampleFormControlInput5').val();
    let name = $('#exampleFormControlInput6').val();
    let email = $('#exampleFormControlInput7').val();
    let address = $('#exampleFormControlInput8').val();

    $.ajax({
        method:"PUT",
        contentType:"application/json",
        headers: { "Accept": "application/json" },
        url:"http://localhost:8080/api/v1/receptionist/update",
        async:true,
        data:JSON.stringify({
            "receptionistId":recId,
            "name":name,
            "email":email,
            "address":address
        }),

        success:function (data) {
            alert("updated!")
            getAllReceptionists();
            $("#exampleFormControlInput5").val("");
            $("#exampleFormControlInput6").val("");
            $("#exampleFormControlInput7").val("");
            $("#exampleFormControlInput8").val("");

        },
        error: function (xhr, exception) {
            alert("Error!")
        }
    })
}

function deleteReceptionist(){
    let recId = $('#exampleFormControlInput1').val();

    $.ajax({
        method:"DELETE",
        url:"http://localhost:8080/api/v1/receptionist/delete/"+recId,
        async:true,
        success:function (data) {
            alert("deleted!")
            $("#exampleFormControlInput5").val("");
            $("#exampleFormControlInput6").val("");
            $("#exampleFormControlInput7").val("");
            $("#exampleFormControlInput8").val("");

            getAllReceptionists()
        },
        error: function (xhr, exception) {
            alert("Error!")
        }
    })
}

function getAllReceptionists(){

    $.ajax({
        method:"GET",
        url:"http://localhost:8080/api/v1/receptionist/get",
        success:function (data) {
            let tableBody = $("#receptionistTable");
            tableBody.empty();
            data.forEach(receptionist => {
                tableBody.append(`
                    <tr>
                        <td>${receptionist.receptionistId}</td>
                        <td>${receptionist.name}</td>
                        <td>${receptionist.email}</td>
                        <td>${receptionist.address}</td>
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
    $(document).on('click', '#receptionistTable tr', function () {
        var col0 = $(this).find('td:eq(0)').text();
        var col1 = $(this).find('td:eq(1)').text();
        var col2 = $(this).find('td:eq(2)').text();
        var col3 = $(this).find('td:eq(3)').text();

        $('#exampleFormControlInput5').val(col0);
        $('#exampleFormControlInput6').val(col1);
        $('#exampleFormControlInput7').val(col2);
        $('#exampleFormControlInput8').val(col3);

    })
})






