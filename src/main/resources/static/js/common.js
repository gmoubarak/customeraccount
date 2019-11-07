//binds input to decimal value.
function filterNumberInput(ele){
    var text=ele.val();
    text=text.replace(/[^0-9.]/g, '');
    arr=text.split('.');
    if(arr.length>0){
      if(arr.length==1){
        text=arr[0];
      }else{
        text=arr[0]+'.'+arr[1];
      }
    }
    ele.val(text);
}