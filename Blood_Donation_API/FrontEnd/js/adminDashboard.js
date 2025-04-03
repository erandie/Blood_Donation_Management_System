$(document).ready(function (){
    loadDonorCount();
    loadDonationCount();
    loadBloodStock();
})

function loadDonorCount(){
    $.ajax({
        url: "http://localhost:8080/api/v1/donor/donorCount",
        method: "GET",
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
        method: "GET",
        success: function (response){
            $('#bloodStock').text(response.data + " ml");
        },

        error: function (xhr, exception) {
            alert("Error loading donation count!")
        }

    })
}























