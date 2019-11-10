$(document).ready(function() {
    //Enable number pattern input on Initial Value
    $('#initialValueInput').on('input', function(){
        filterNumberInput($('#initialValueInput'));
    });
    $('#addAccount').click(function(){
        //on click update create the account
        var customerID=$('#customerID').text();
        var description=$('#descriptionInput').val();
        if(description===""){
            alert('Please fill in the account description');
            return;
        }
        var ivstr=$('#initialValueInput').val();
        var initialValue=0;
        if(ivstr!==null && ivstr.trim()!=="" && ivstr.trim()!=="." && ivstr.trim()!=="-"){
            initialValue=parseFloat(ivstr);
        }
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
