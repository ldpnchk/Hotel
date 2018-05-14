<%@include file="header.jsp"%>

<div class="container">
    <div class="col-md-6 col-md-offset-3">
        <div class="panel panel-default">
            <div class="panel-body">
                <form name='checkForm' action="<c:url value="${pageContext.request.contextPath}/hotel/updateReservation" />" method='POST'>
                    <div class="container-fluid">
                        <div class="input-group">
                            <label class="control-label"><fmt:message key="creation.date"/>: ${reservation.reservationDate.toLocalDate()} ${reservation.reservationDate.toLocalTime()}</label>
                        </div>
                        <hr/>
                        <div class="input-group">
                            <label class="control-label"><fmt:message key="start.date"/>: ${reservation.startDate}</label><br/>
                        </div>
                        <div class="input-group">
                            <label class="control-label"><fmt:message key="end.date"/>: ${reservation.endDate}</label><br/>
                        </div>
                        <hr/>
                        <c:choose>
                            <c:when test="${reservation.reservationStatus eq 'NEW' && user.getUserRole() eq 'CLIENT'}">
                                <label class="control-label"><fmt:message key="edit.comment"/>: </label><br/>
                                <textarea class="form-control" rows="3" id="comment" name="comment" required>${reservation.clientComment}</textarea>
                            </c:when>
                            <c:otherwise>
                                <label class="control-label"><fmt:message key="comment"/>: </label><br/>
                                <textarea class="form-control" rows="3" id="comment" name="comment" readonly="readonly">${reservation.clientComment}</textarea>
                            </c:otherwise>
                        </c:choose>
                        <hr/>
                        <div class="input-group">
                            <c:choose>
                                <c:when test="${user.getUserRole() eq 'ADMINISTRATOR'}">
                                    <label class="control-label"><fmt:message key="status"/>: ${reservation.reservationStatus}</label><br/>
                                    <label class="control-label"><fmt:message key="room"/>: ${reservation.room.roomNumber}</label><br/>
                                    <div class="row">
                                        <div class="col-md-7">
                                            <label class="control-label" for="status"><fmt:message key="change.status"/></label>
                                            <select id="status" name="status" required onchange="checkStatus();">
                                                <option value="${reservation.reservationStatus}">${reservation.reservationStatus}</option>
                                                <c:forEach items="${statuses}" var="s">
                                                    <c:if test="${s ne reservation.reservationStatus}">
                                                        <option value="${s}">${s}</option>
                                                    </c:if>
                                                </c:forEach>
                                            </select>
                                        </div>
                                        <div class="col-md-5">
                                            <div id="chooseRoom" style="display:none;">
                                                <label class="control-label" for="room"><fmt:message key="choose.room"/></label><br/>
                                                <select id="room" name="room" required>
                                                    <c:forEach items="${rooms}" var="r">
                                                        <option value="${r.id}">${r.roomNumber}</option>
                                                    </c:forEach>
                                                </select>
                                            </div>
                                        </div>
                                    </div>
                                </c:when>
                                <c:otherwise>
                                    <div class="input-group">
                                        <label class="control-label"><fmt:message key="status"/>: ${reservation.reservationStatus}</label><br/>
                                    </div>
                                </c:otherwise>
                            </c:choose>
                        </div><br/>

                        <c:if test="${reservation.reservationStatus eq 'DENIED' && user.getUserRole() eq 'CLIENT'}">
                            <label class="control-label"><fmt:message key="admin.comment"/>: ${reservation.administratorComment}</label>
                        </c:if><br/>
                        <c:if test="${user.getUserRole() eq 'ADMINISTRATOR'}">
                            <label class="control-label" for="adminComment"><fmt:message key="edit.comment"/>: </label><br/>
                            <textarea class="form-control" rows="3" id="adminComment" name="adminComment">${reservation.administratorComment}</textarea><br/>
                        </c:if>

                        <div class="row">
                            <c:if test="${reservation.reservationStatus eq 'NEW' && user.getUserRole() eq 'CLIENT'}">
                                <div class="col-md-4 col-md-offset-8">
                                    <button type="submit" class="btn btn-md btn-warning"><fmt:message key="update.comment"/></button>
                                </div>
                            </c:if>
                            <c:if test="${user.getUserRole() eq 'ADMINISTRATOR'}">
                                <div class="modal-footer" style="padding: 7px !important;">
                                    <button type="submit" class="btn btn-md btn-warning"><fmt:message key="update"/></button>
                                </div>
                            </c:if>
                            <div class="form-group" style="display: none;"><textarea id="reservationId" name="reservationId">${reservation.id}</textarea></div>
                        </div>
                    </div>
                </form>

                <c:if test="${reservation.reservationStatus eq 'NEW' && user.getUserRole() eq 'CLIENT'}">
                    <hr/>
                    <div class="row">
                        <div class="col-md-4">
                            <button type="button" class="btn btn-md btn-danger" data-toggle="modal" data-target="#deleteReservation"><fmt:message key="delete.reservation"/></button>
                        </div>
                    </div>
                </c:if>



                <c:if test="${user.getUserRole() eq 'ADMINISTRATOR'}">
                    <c:if test="${reservation.reservationStatus eq 'APPROVED'}">

                        <!-- ADD PAYMENT INFO -->
                        <form name='checkForm' action="<c:url value="${pageContext.request.contextPath}/hotel/addReservationPayment" />" method='POST'>
                            <div class="container-fluid">
                                <div class="input-group">
                                    <label class="control-label" for="amount"><fmt:message key="payment"/> </label>
                                    <input type="number" class="form-control" id="amount" name="amount" step="0.01" required />
                                </div>
                                <div class="input-group">
                                    <label class="control-label" for="method"><fmt:message key="payment.method"/> </label>
                                    <select id="method" name="method" class="form-control" required>
                                        <c:forEach var="t" items="${paymentTypes}">
                                            <option value="${t}">${t}</option>
                                        </c:forEach>
                                    </select>
                                </div><br/>
                                <div class="form-group" style="display: none;"><textarea id="reservationId" name="reservationId">${reservation.id}</textarea></div>
                                <div class="modal-footer">
                                    <button type="submit" class="btn btn-md btn-warning"><fmt:message key="update"/></button>
                                </div>
                            </div>
                        </form>

                    </c:if>
                    <c:if test="${reservation.reservationStatus eq 'PAYED'}">

                        <!-- SHOW PAYMENT INFO AND DELETE BUTTON -->
                        <div class="row" style="padding: 25px !important;">
                            <p><fmt:message key="payment"/>: ${reservation.payment.total / 100.0} <fmt:message key="currency"/>, (${reservation.payment.date.toLocalDate()} ${reservation.payment.date.toLocalTime()})</p>
                            <p><fmt:message key="payment.method"/>: ${reservation.payment.paymentMethod}</p>
                            <form name='checkForm' action="<c:url value="${pageContext.request.contextPath}/hotel/deleteReservationPayment" />" method='POST'>
                                <div class="modal-footer">
                                    <div class="form-group" style="display: none;"><textarea id="reservationId" name="reservationId">${reservation.id}</textarea></div>
                                    <button type="submit" class="btn btn-md btn-danger"><fmt:message key="delete"/></button>
                                </div>
                            </form>
                        </div>

                    </c:if>
                </c:if>

            </div>
        </div>
    </div>
</div>

<!-- Submit deleting modal-->
<div class="modal fade" id="deleteReservation" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title" id="myModalLabel"><fmt:message key="confirm.reservation.delete"/></h4>
            </div>
            <form name='checkForm' action="<c:url value="${pageContext.request.contextPath}/hotel/deleteReservation" />" method='POST'>
                <div class="panel-body">
                    <div class="container-fluid">
                        <div class="input-group">
                            <label class="control-label"><fmt:message key="start.date"/>: ${reservation.startDate}</label><br/>
                        </div>
                        <div class="input-group">
                            <label class="control-label"><fmt:message key="end.date"/>: ${reservation.endDate}</label><br/>
                        </div>
                        <hr/>
                        <div class="form-group" style="display: none;"><textarea id="reservationId" name="reservationId">${reservation.id}</textarea></div>
                        <c:if test="${reservation.reservationStatus eq 'NEW'}">
                            <div class="col-md-3 col-md-offset-9">
                                <button type="submit" class="btn btn-md btn-danger"><fmt:message key="delete"/></button>
                            </div>
                        </c:if>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>

<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/scripts/reservationDetails.js"></script>

<%@include file="footer.jsp"%>