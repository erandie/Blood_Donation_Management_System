$(document).ready(function (){
    loadDonorCount();
    loadDonationCount();
    loadBloodStock();
})

function getAuthHeaders() {
    return {
        "Authorization": localStorage.getItem("token"),
        "Content-Type": "application/json"
    };
}


function loadDonorCount(){
    $.ajax({
        url: "http://localhost:8080/api/v1/donor/donorCount",
        headers: getAuthHeaders(),
        method: "GET",
        header: {
            "Content-Type": "application/json",
            "login": localStorage.getItem("token")
        },
        success: function (response){
            $('#donorCount').text(response.data);
        },

        error: function (xhr, exception) {
            alert("Error loading donor count!")
        }

    })
}

function loadDonationCount(){
    $.ajax({
        url: "http://localhost:8080/api/v1/donation/donationCount",
        headers: getAuthHeaders(),
        method: "GET",
        success: function (response){
            $('#donationCount').text(response.data);
        },

        error: function (xhr, exception) {
            alert("Error loading donation count!")
        }

    })
}

function loadBloodStock(){
    $.ajax({
        url:"http://localhost:8080/api/v1/bloodBank/bloodStock",
        headers: getAuthHeaders(),
        method: "GET",
        success: function (response){
            $('#bloodStock').text(response.data + " ml");
        },

        error: function (xhr, exception) {
            alert("Error loading donation count!")
        }

    })
}























