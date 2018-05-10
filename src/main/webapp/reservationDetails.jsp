<%@include file="header.jsp"%>

<div class="container">
    <div class="col-md-6 col-md-offset-3">
        <div class="panel panel-default">
            <div class="panel-body">
                <form name='checkForm' action="<c:url value="${pageContext.request.contextPath}/hotel/updateReservation" />" method='POST'>
                    <div class="container-fluid">
                        <div class="input-group">
                            <label class="control-label">Date of creation: ${reservation.reservationDate}</label>
                        </div>
                        <hr/>
                        <div class="input-group">
                            <label class="control-label">Start date: ${reservation.startDate}</label><br/>
                        </div>
                        <div class="input-group">
                            <label class="control-label">End date: ${reservation.endDate}</label><br/>
                        </div>
                        <hr/>
                        <c:choose>
                            <c:when test="${reservation.reservationStatus eq 'NEW' && user.getUserRole() eq 'CLIENT'}">
                                <label class="control-label">Edit your comment: </label><br/>
                                <textarea class="form-control" rows="5" id="comment" name="comment" required>${reservation.clientComment}</textarea>
                            </c:when>
                            <c:otherwise>
                                <label class="control-label">Comment: </label><br/>
                                <textarea class="form-control" rows="5" id="comment" name="comment" readonly="readonly">${reservation.clientComment}</textarea>
                            </c:otherwise>
                        </c:choose>
                        <hr/>
                        <div class="input-group">

                            <c:choose>
                                <c:when test="${user.getUserRole() eq 'ADMINISTRATOR'}">
                                    <label class="control-label">Current status: ${reservation.reservationStatus}</label><br/>
                                    <label class="control-label">Change status</label><br/>
                                    <select id="status" name="status" required>
                                        <option value="${reservation.reservationStatus}">${reservation.reservationStatus}</option>
                                        <c:forEach items="${statuses}" var="s">
                                            <c:if test="${s ne reservation.reservationStatus}">
                                                <option value="${s}">${s}</option>
                                            </c:if>
                                        </c:forEach>
                                    </select>
                                </c:when>
                                <c:otherwise>
                                    <div class="input-group">
                                        <label class="control-label">Status: ${reservation.reservationStatus}</label><br/>
                                    </div>
                                </c:otherwise>
                            </c:choose>

                            <c:if test="${reservation.reservationStatus eq 'DENIED' && user.getUserRole() eq 'CLIENT'}">
                                <label class="control-label">Admin comment: ${reservation.administratorComment}</label>
                            </c:if><br/>
                            <c:if test="${user.getUserRole() eq 'ADMINISTRATOR'}">
                                <label class="control-label">Edit your comment: </label><br/>
                                <textarea class="form-control" rows="5" cols="35" id="adminComment" name="adminComment" required>${reservation.administratorComment}</textarea>
                            </c:if>

                        </div><br/>
                        <div class="row">
                            <c:if test="${reservation.reservationStatus eq 'NEW' && user.getUserRole() eq 'CLIENT'}">
                                <div class="col-md-4 col-md-offset-8">
                                    <button type="submit" class="btn btn-md btn-warning">Update comment</button>
                                </div>
                            </c:if>
                            <c:if test="${user.getUserRole() eq 'ADMINISTRATOR'}">
                                <div class="col-md-3 col-md-offset-9">
                                    <button type="submit" class="btn btn-md btn-warning">Update</button>
                                </div>
                            </c:if>
                            <div class="form-group" style="display: none;"><textarea id="reservationId" name="reservationId">${reservation.id}</textarea></div>
                        </div>
                    </div>
                </form>
                <hr/>
                <div class="row">
                    <c:if test="${reservation.reservationStatus eq 'NEW' && user.getUserRole() eq 'CLIENT'}">
                        <div class="col-md-4">
                            <button type="button" class="btn btn-md btn-danger" data-toggle="modal" data-target="#deleteReservation">Delete reservation</button>
                        </div>
                    </c:if>
                </div>
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
                <h4 class="modal-title" id="myModalLabel">Confirm deleting the order</h4>
            </div>
            <form name='checkForm' action="<c:url value="${pageContext.request.contextPath}/hotel/deleteReservation" />" method='POST'>
                <div class="panel-body">
                    <div class="container-fluid">
                        <div class="input-group">
                            <label class="control-label">Start date: ${reservation.startDate}</label><br/>
                        </div>
                        <div class="input-group">
                            <label class="control-label">End date: ${reservation.endDate}</label><br/>
                        </div>
                        <hr/>
                        <div class="form-group" style="display: none;"><textarea id="reservId" name="reservId">${reservation.id}</textarea></div>
                        <c:if test="${reservation.reservationStatus eq 'NEW'}">
                            <div class="col-md-3 col-md-offset-9">
                                <button type="submit" class="btn btn-md btn-danger">Delete</button>
                            </div>
                        </c:if>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>

<%@include file="footer.jsp"%>