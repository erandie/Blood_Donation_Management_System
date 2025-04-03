$(document).ready(function () {
    loadBloodTypes();
    getAllDetails(); // Load data on page load
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
            alert("Error loading blood types");
        }
    });
}

function saveDetails() {
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
            "bloodType": bloodType,
            "points": points
        }),
        success: function() {
            alert("Points added successfully!");
            getAllDetails();
            clearForm();
        },
        error: function(xhr) {
            alert("Error: " + (xhr.responseJSON?.message || "Failed to add points"));
            console.error(xhr);
        }
    });
}

function updateDetails() {
    let bloodBankId = $('#exampleFormControlInput9').val();
    let bloodType = $("#select-bloodType").val();
    let points = $('#exampleFormControlInput10').val();

    if (!bloodBankId || !bloodType || !points) {
        alert("Please select a record to update first!");
        return;
    }

    $.ajax({
        method: "PUT",
        contentType: "application/json",
        url: "http://localhost:8080/api/v1/bloodBank/update",
        data: JSON.stringify({
            "bloodBankId": bloodBankId,
            "bloodType": bloodType,
            "points": points
        }),
        success: function() {
            alert("Updated successfully!");
            getAllDetails();
            clearForm();
        },
        error: function(xhr) {
            alert("Error: " + (xhr.responseJSON?.message || "Failed to update"));
        }
    });
}

function deleteDetails() {
    let bloodBankId = $('#exampleFormControlInput9').val();

    if (!bloodBankId) {
        alert("Please select a record to delete first!");
        return;
    }

    $.ajax({
        method: "DELETE",
        url: "http://localhost:8080/api/v1/bloodBank/delete/" + bloodBankId,
        success: function() {
            alert("Deleted successfully!");
            getAllDetails();
            clearForm();
        },
        error: function(xhr) {
            alert("Error: " + (xhr.responseJSON?.message || "Failed to delete"));
        }
    });
}

function getAllDetails() {
    $.ajax({
        method: "GET",
        url: "http://localhost:8080/api/v1/bloodBank/get",
        success: function(data) {
            let tableBody = $("#bloodBankTable");
            tableBody.empty();

            // Add table headers
            tableBody.append(`
                <tr>
                    <th>Blood Type</th>
                    <th>Total Points</th>
                </tr>
            `);

            // Sum points by blood type
            const bloodGroups = {};
            data.forEach(bloodBank => {
                if (!bloodGroups[bloodBank.bloodType]) {
                    bloodGroups[bloodBank.bloodType] = 0;
                }
                bloodGroups[bloodBank.bloodType] += bloodBank.points;
            });

            // Add rows
            Object.entries(bloodGroups).forEach(([bloodType, points]) => {
                tableBody.append(`
                    <tr>
                        <td>${bloodType}</td>
                        <td>${points}</td>
                    </tr>
                `);
            });
        },
        error: function(xhr) {
            alert("Error loading blood bank data");
            console.error(xhr);
        }
    });
}

function clearForm() {
    $("#exampleFormControlInput9").val("");
    $("#select-bloodType").val("");
    $("#exampleFormControlInput10").val("");
}

$(document).on('click', '#bloodBankTable tr', function () {
    // Skip header row
    if ($(this).find('th').length > 0) return;

    let bloodType = $(this).find('td:eq(0)').text();
    let points = $(this).find('td:eq(1)').text();

    // For update/delete, you might want to fetch the full record from backend
    // since we're showing aggregated data
    $("#select-bloodType").val(bloodType);
    $("#exampleFormControlInput10").val(points);
});