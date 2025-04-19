$("#bloodBank-nav").on("click", function () {
    $("#main-content").load("bloodBank.html", function () {
        initBloodBankForm();
    });});
function initBloodBankForm() {
    loadBloodTypes();
    getAllDetails();
    $("#btnSaveBloodBank").on("click", saveDetails);
    $("#btnUpdateBloodBank").on("click", updateDetails);
    $("#btnDeleteBloodBank").on("click", deleteDetails);
    setupBloodBankSearch();
    function setupBloodBankSearch() {
        let timeOut = null;
        $('#searchInput').on('input', function () {
            clearTimeout(timeOut);
            timeOut = setTimeout(function () {
                const query = $('#searchInput').val().trim();
                searchBloodTypes(query);
            }, 300);});
        $('#searchInput').on('keypress', function (e){
            if (e.which === 13){
                e.preventDefault();
                const query = $('#searchInput').val().trim();
                searchBloodTypes(query);
            }});}
    function getAuthHeaders() {
        return {
            "Authorization": localStorage.getItem("token"),
            "Content-Type": "application/json"
        };}
    function loadBloodTypes() {
        $.ajax({
            url: "http://localhost:8080/api/v1/bloodTypes/get",
            method: "GET",
            headers: getAuthHeaders(),
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
            }});}
    function getAllDetails() {
        $.ajax({
            method: "GET",
            url: "http://localhost:8080/api/v1/bloodBank/get",
            headers: getAuthHeaders(),
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
                `);});},
            error: function () {
                alert("Error!");
            }});}
    function saveDetails() {
        let bloodType = $("#select-bloodType").val();
        let points = parseFloat($('#exampleFormControlInput10').val());

        $.ajax({
            method: "POST",
            contentType: "application/json",
            url: "http://localhost:8080/api/v1/bloodBank/save",
            headers: getAuthHeaders(),
            data: JSON.stringify({
                "bloodType": bloodType,
                "points": points
            }),
            success: function (res) {
                if (res.code === 201) {
                    alert("Saved!");
                    getAllDetails();
                    clearForm();
                }  else {
                    alert("Failed to save: " + res.message);
                }},
            error: function (xhr) {
                let errMsg = xhr.responseJSON?.message || "Something went wrong!";
                alert("Error: " + errMsg);
            }});}
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
            headers: getAuthHeaders(),
            url:"http://localhost:8080/api/v1/bloodBank/update",
            async:true,
            data:JSON.stringify({
                "bloodBankId":bloodBankId,
                "bloodType": bloodType,
                "points":points
            }),
            success: function (res) {
                if (res.code === 200) {
                    alert("Updated!");
                    getAllDetails();
                    clearForm();
                } else {
                    alert("Failed to update: " + res.message);
                }},
            error: function (xhr) {
                let errMsg = xhr.responseJSON?.message || "Something went wrong!";
                alert("Error: " + errMsg);
            }})}
    function deleteDetails(){
        let bloodBankId = $('#exampleFormControlInput9').val();

        $.ajax({
            method: "DELETE",
            contentType:"application/json",
            url:"http://localhost:8080/api/v1/bloodBank/delete/"+bloodBankId,
            headers: getAuthHeaders(),
            async:true,

            success:function (){
                alert("deleted!")
                clearForm();
                getAllDetails();
            },
            error: function (xhr, exception) {
                alert("Error!")
            }})}
    function clearForm(){
        $("#exampleFormControlInput9").val("");
        $("#select-bloodType").val("");
        $("#exampleFormControlInput10").val("");
    }
    function searchBloodTypes(bloodType){
        $.ajax({
            method: "GET",
            url: "http://localhost:8080/api/v1/bloodBank/search?bloodType=" + encodeURIComponent(bloodType),
            headers: getAuthHeaders(),
            success:function (data) {
                const tableBody = $("#bloodBankTable");
                tableBody.empty();
                data.forEach(bloodBank => {
                    tableBody.append(`
                    <tr>
                        <td>${bloodBank.bloodBankId}</td>
                        <td>${bloodBank.bloodType}</td>
                        <td>${bloodBank.points}</td>
                    </tr>
                `);});},
            error: function (xhr) {
                alert("Search Failed!")
            }})}}
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








































