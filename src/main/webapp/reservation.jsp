<%@include file="header.jsp"%>

<div class="container">
    <div class="panel panel-default">
        <div class="panel-body">
            <div class="row">
                <div class="col-md-6 col-md-offset-3">
                    <form name='checkForm' action="<c:url value="${pageContext.request.contextPath}/hotel/reservation" />" method='GET'>
                        <div class="container-fluid">
                            <div class="input-group">
                                <div class="form-group has-feedback has-feedback-left">
                                    <label class="control-label">Select day of arrival and departure</label>
                                    <input id="datefilter" type="text" class="form-control" name="datefilter" required>
                                    <i class="form-control-feedback glyphicon glyphicon-calendar"></i>
                                </div>
                                <c:if test="${dateFormatError}">
                                    <p><font color="red">INCORRECT DATE FORMAT</font></p>
                                </c:if>
                            </div><br/>
                            <div class="input-group">
                                <label class="control-label">Select number of guests: </label>
                                <input id="capacity" type="number" min="1" class="form-control" name="capacity" required>
                            </div><br/>
                            <div class="top5">
                                <button type="submit" class="btn btn-success col-md-6 col-md-offset-3">Search</button>
                            </div>
                        </div>
                    </form>
                    <c:if test="${empty user && !empty options}">
                        <p><font color="blue">Please, log in to make order.</font></p>
                    </c:if>
                    <hr/>
                </div>
            </div>
            <div class="row">
                <div class="col-md-6 col-md-offset-3" id="optionsDiv">
                    <c:forEach items="${options}" var="option">
                        <div class="panel panel-default">
                            <div class="panel-body">
                                <div class="container-fluid">
                                    <p>Room type: ${option.name}, Capacity: ${option.capacity}</p>
                                    <p>Description: ${option.description}</p>
                                    <p>Price: ${option.price}</p>
                                    <c:if test="${user.getUserRole() eq 'CLIENT'}">
                                        <a onclick="setReservationData(${option.id});">
                                            <button type="button" class="btn btn-sm btn-success" data-toggle="modal" data-target="#makeOrder">
                                                <i class="fa fa-plus-circle" aria-hidden="true"></i>
                                                Make order
                                            </button>
                                        </a>
                                    </c:if>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </div>
        </div>
    </div>
</div>

<!-- Order modal-->
<div class="modal fade" id="makeOrder" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title" id="myModalLabel">Confirm your order</h4>
            </div>
            <form id="orderForm" action="<c:url value="${pageContext.request.contextPath}/hotel/createReservation" />" method='POST'>
                <div class="modal-body">
                    <div class="form-group">
                        <label for="comment">Comment (optional):</label>
                        <textarea class="form-control" rows="5" id="comment" name="comment"></textarea>
                    </div>
                    <div class="form-group">
                        <label for="startDate">Start Date:</label>
                        <input type="text" class="form-control" rows="5" id="startDate" name="startDate" readonly="readonly">
                    </div>
                    <div class="form-group">
                        <label for="endDate">End Date:</label>
                        <input type="text" class="form-control" rows="5" id="endDate" name="endDate" readonly="readonly">
                    </div>
                    <div class="form-group" style="display: none;"><input type="number" id="roomTypeId" name="roomTypeId" readonly="readonly"></div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
                    <button type="submit" class="btn btn-warning">Make order</button>
                </div>
            </form>
        </div>
    </div>
</div>

<script type="text/javascript">
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

    $(function() {
        $('input[name="datefilter"]').daterangepicker({
            minDate: today,
            autoUpdateInput: false,
            locale: {
                cancelLabel: 'Clear'
            }
        });
        $('input[name="datefilter"]').on('apply.daterangepicker', function(ev, picker) {
            $(this).val(picker.startDate.format('DD.MM.YYYY') + '-' + picker.endDate.format('DD.MM.YYYY'));
        });
        $('input[name="datefilter"]').on('cancel.daterangepicker', function(ev, picker) {
            $(this).val();
        });
    });

    function setReservationData(roomTypeId) {
        $("#startDate").val(dates.split('-')[0]);
        $("#endDate").val(dates.split('-')[1]);
        $("#roomTypeId").val(roomTypeId);
    }
</script>

<%@include file="footer.jsp"%>