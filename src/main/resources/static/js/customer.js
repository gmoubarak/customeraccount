$(document).ready(function() {
    //Enable number pattern input on Initial Value
    $('#initialValueInput').on('input', function(){
        filterNumberInput($('#initialValueInput'));
    });
    $('#addAccount').click(function(){
        //on click update create the account
        var customerID=$('#customerID').text();
        var description=$('#descriptionInput').val();
        var initialValue=parseFloat($('#initialValueInput').val())
        $.post( "/api/v1/account", { "customerID":customerID, "description":description,"initialValue":initialValue })
          .done(function( data ) {//on succcess refresh ajax the accounts table
            reloadAccounts();
            $('#addAccountDialog').modal('hide');//hide panel
            $('#descriptionInput').val('');//reset value
            $('#initialValueInput').val('');//reset value
          });
        });
});
// ajax call for reloading the accounts list table
function reloadAccounts(){
     var customerID=$('#customerID').text();
    $.get("/customer/"+customerID+"/reloadAccounts", function(fragment) {
        $("#accountsTable").replaceWith(fragment);
    });
}
function deleteAccount(accountID){
    $.ajax({
        type: 'DELETE',
        url: '/api/v1/account',
        data: { "accountID":accountID},
        success: function() {
            reloadAccounts();
        }
    });
}
