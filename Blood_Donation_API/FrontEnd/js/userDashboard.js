$(document).ready(function () {
    loadUserName();
});

function getAuthHeaders() {
    return {
        "Authorization": localStorage.getItem("token"),
        "Content-Type": "application/json"
    };
}


function loadUserName(){
    $.ajax({
        url: "http://localhost:8080/api/v1/user/info",
        method: "GET",
        headers: getAuthHeaders(),
        success: function (res){
            console.log(res)
            $("#name").text(res.name);
        },
        error: function (err){
            console.error("Failed to load user name! ")
        }

    })
}


































