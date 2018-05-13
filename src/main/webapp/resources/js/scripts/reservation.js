var dates, capacity;

$( document ).ready(function() {
    var url = window.location.href;
    var captured1 = /datefilter=([^&]+)/.exec(url)[1];
    dates = captured1 ? captured1 : null;
    var captured2 = /capacity=([^&]+)/.exec(url)[1];
    capacity = captured2 ? captured2 : null;
    if(dates != null && capacity != null){
        $("#datefilter").val(dates);
        $("#capacity").val(capacity);
    }
});

var today = new Date();

function setReservationData(roomTypeId) {
    $("#startDate").val(dates.split('-')[0]);
    $("#endDate").val(dates.split('-')[1]);
    $("#roomTypeId").val(roomTypeId);
}