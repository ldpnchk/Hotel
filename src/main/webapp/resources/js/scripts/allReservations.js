var default_options = {
    "sScrollY": 375,
    "sScrollX": "100%",
    "sScrollXInner": "100%",
    "bJQueryUI": true,
    "sPaginationType": "full_numbers",
    "bProcessing": true,
    "order": [[0, "desc"]]
};
$('#reservationsTable').DataTable(default_options);

var dates, room, status;

$( document ).ready(function() {
    var url = window.location.href;
    try {
        var captured = /datefilter=([^&]+)/.exec(url)[1];
        dates = captured ? captured : "";
        if (dates != null) {
            $("#datefilter").val(dates);
        }
    } catch (err){}
    
    try {
        captured = /roomId=([^&]+)/.exec(url)[1];
        room = captured ? captured : "";
        if(room != null){
            $("#roomId").val(room);
        }
    }catch (err){}
    
    try {
        captured = /status=([^&]+)/.exec(url)[1];
        status = captured ? captured : "";
        if(status != null){
            $("#status").val(status);
        }
    }catch (err){}

});

var today = new Date();