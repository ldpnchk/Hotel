function checkStatus() {
    if($("#status").val() == 'APPROVED'){
        $('#chooseRoom').attr('style','');
    }else{
        $('#chooseRoom').attr('style','display: none;');
    }
}