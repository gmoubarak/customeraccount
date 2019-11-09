$(document).ready(function() {
    //Enable number pattern input on Initial Value
    $('#amountInput').on('input', function(){
        filterNumberInput($('#amountInput'));
    });
    $('#createTransaction').click(function(){
        //on click update create the account
        var accountID=$('#accountID').text();
        var description=$('#descriptionInput').val();
        var amount=parseFloat($('#amountInput').val());
        $.post( "/api/v1/transaction", { "accountID":accountID, "description":description,"amount":amount })
          .done(function( data ) {//on succcess refresh ajax the accounts table
            reloadTransactions();
            $('#addTransactionDialog').modal('hide');//hide panel
            $('#descriptionInput').val('');//reset value
            $('#amountInput').val('');//reset value
          });
        });
});
// ajax call for reloading the accounts list table
function reloadTransactions(){
     var accountID=$('#accountID').text();
    $.get("/account/"+accountID+"/reloadTransactions", function(fragment) {
        $("#transactionsTable").replaceWith(fragment);
    });
}
function deleteAccount(transactionID){
    $.ajax({
        type: 'DELETE',
        url: '/api/v1/transaction',
        data: { "transactionID":transactionID},
        success: function() {
            reloadTransactions();
        }
    });
}
