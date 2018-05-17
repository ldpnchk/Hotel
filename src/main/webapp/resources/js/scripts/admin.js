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
    var captured1 = /datefilter=([^&]+)/.exec(url)[1];
    dates = captured1 ? captured1 : null;
    if(dates != null){
        $("#datefilter").val(dates);
    }
});

var today = new Date();