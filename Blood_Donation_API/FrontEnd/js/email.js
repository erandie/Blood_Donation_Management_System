function getAuthHeaders() {
    return {
        "Authorization": localStorage.getItem("token"),
        "Content-Type": "application/json"
    };
}

$(document).ready(function () {
    $('#emailForm').on('submit', function (e) {
        e.preventDefault();

        let email = $('#email').val();
        let subject = $('#subject').val();
        let message = $('#message').val();

        $('.loading').show();
        $('.error-message').hide();
        $('.sent-message').hide();

        $.ajax({
            method: "POST",
            url: "http://localhost:8080/api/v1/email/send",
            headers: getAuthHeaders(),
            contentType: "application/json",
            data: JSON.stringify({
                to: email, 
                subject: subject,
                message: message
            }),
            success: function (res) {
                $('.loading').hide();
                $('.sent-message').show();
                $('.error-message').hide();
                $('#emailForm')[0].reset();
                console.log("✅ Success:", res);
            },
            error: function (xhr) {
                $('.loading').hide();
                $('.sent-message').hide();
                $('.error-message').text("Failed to send message :(").show();
                console.error("❌ Error:", xhr.responseText);
            }
        });
    });
});
