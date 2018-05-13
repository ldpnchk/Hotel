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
                                    <label class="control-label"><fmt:message key="select.days"/></label>
                                    <input id="datefilter" type="text" class="form-control" name="datefilter" required>
                                    <i class="form-control-feedback glyphicon glyphicon-calendar"></i>
                                </div>
                                <c:if test="${dateFormatError}">
                                    <p><font color="red"><fmt:message key="incorrect.date.format"/></font></p>
                                </c:if>
                            </div><br/>
                            <div class="input-group">
                                <label class="control-label"><fmt:message key="select.number.guests"/>:</label>
                                <input id="capacity" type="number" min="1" class="form-control" name="capacity" required>
                            </div><br/>
                            <div class="top5">
                                <button type="submit" class="btn btn-success col-md-6 col-md-offset-3"><fmt:message key="search"/></button>
                            </div>
                        </div>
                    </form>
                    <c:if test="${empty user && !empty options}">
                        <p><font color="blue"><fmt:message key="please.login.to.make.order"/></font></p>
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
                                    <p><fmt:message key="room.type"/>: ${option.name}, <fmt:message key="capacity"/>: ${option.capacity}</p>
                                    <p><fmt:message key="description"/>: ${option.description}</p>
                                    <p><fmt:message key="price"/>: ${option.price}</p>
                                    <c:if test="${user.getUserRole() eq 'CLIENT'}">
                                        <a onclick="setReservationData(${option.id});">
                                            <button type="button" class="btn btn-sm btn-success" data-toggle="modal" data-target="#makeOrder">
                                                <i class="fa fa-plus-circle" aria-hidden="true"></i>
                                                <fmt:message key="make.order"/>
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
                <h4 class="modal-title" id="myModalLabel"><fmt:message key="confirm.order"/></h4>
            </div>
            <form id="orderForm" action="<c:url value="${pageContext.request.contextPath}/hotel/createReservation" />" method='POST'>
                <div class="modal-body">
                    <div class="form-group">
                        <label for="comment"><fmt:message key="comment.optional"/>:</label>
                        <textarea class="form-control" rows="5" id="comment" name="comment"></textarea>
                    </div>
                    <div class="form-group">
                        <label for="startDate"><fmt:message key="start.date"/>:</label>
                        <input type="text" class="form-control" rows="5" id="startDate" name="startDate" readonly="readonly">
                    </div>
                    <div class="form-group">
                        <label for="endDate"><fmt:message key="end.date"/>:</label>
                        <input type="text" class="form-control" rows="5" id="endDate" name="endDate" readonly="readonly">
                    </div>
                    <div class="form-group" style="display: none;"><input type="number" id="roomTypeId" name="roomTypeId" readonly="readonly"></div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal"><fmt:message key="cancel"/></button>
                    <button type="submit" class="btn btn-success"><fmt:message key="make.order"/></button>
                </div>
            </form>
        </div>
    </div>
</div>

<script type="text/javascript">
    $(function() {
        if('${language}' == 'uk_UA'){
            moment.locale('uk');
        }else{
            moment.locale('en');
        }
        $('input[name="datefilter"]').daterangepicker({
            minDate: today,
            autoUpdateInput: false,
            locale: {
                cancelLabel: "<fmt:message key='cancel'/>",
                applyLabel: "<fmt:message key='apply'/>",
            }
        });
        $('input[name="datefilter"]').on('apply.daterangepicker', function(ev, picker) {
            $(this).val(picker.startDate.format('DD.MM.YYYY') + '-' + picker.endDate.format('DD.MM.YYYY'));
        });
        $('input[name="datefilter"]').on('cancel.daterangepicker', function(ev, picker) {
            $(this).val();
        });
    });
</script>

<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/scripts/reservation.js"></script>

<%@include file="footer.jsp"%>