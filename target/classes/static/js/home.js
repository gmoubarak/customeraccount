$(document).ready(function() {
    $.ajax({
        url: "/api/v1/customer/@count"
    }).then(function(data) {
       $('#customerCount').text(data);
    });
    $.ajax({
        url: "/api/v1/account/@count"
    }).then(function(data) {
       $('#accountCount').text(data);
    });
});
