//@Page load get the count of the customers, accounts, and transactions in the system.
// initially it starts with 5 customers and 0 accounts and transactions.
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
    $.ajax({
        url: "/api/v1/transaction/@count"
    }).then(function(data) {
       $('#transactionCount').text(data);
    });
});
