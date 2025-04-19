function getAuthHeaders() {
    return {
        "Authorization": localStorage.getItem("token"),
        "Content-Type": "application/json"
    };
}

$(document).ready(function () {
    $('#emailForm').on('submit', function (e) {
        e.preventDefault();

        let name = $('#name').val();
        let email = $('#email').val();
        let subject = $('#subject').val();
        let message = $('#message').val();

        $.ajax({
            method: "POST",
            url: "http://localhost:8080/api/v1/email/send",
            headers: getAuthHeaders(),
            contentType: "application/json",
            data:JSON.stringify({
                name: name,
                email: email,
                subject: subject,
                message: message
            }),

            success: function (res){
                $('#emailForm')[0].reset();
                $('.loading').hide();
                $('.sent-message').show().text(res);
                console.log("success", res)
            },
            error:function (xhr){
                $('.loading').hide();
                $('.error-message').show().text("Error: " + xhr.responseText);
            }

        })

    })
})































