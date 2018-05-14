<%@include file="header.jsp"%>

<div class="row">
    <div class="container">
        <div class="col-md-8 col-md-offset-2">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h1 class="panel-title"><strong><fmt:message key="reservations"/></strong></h1>
                </div>
                <div class="panel-body">
                    <c:forEach items="${reservations}" var="reservation">
                        <div class="panel panel-default" style="padding: 15px !important;">
                            <a href="${pageContext.request.contextPath}/hotel/reservationDetails?reservationId=${reservation.id}">
                                <p><fmt:message key="creation.date"/>: ${reservation.reservationDate.toLocalDate()} ${reservation.reservationDate.toLocalTime()}</p>
                            </a>
                            <p><fmt:message key="status"/>: ${reservation.reservationStatus}</p>
                            <p><fmt:message key="start.date"/>: ${reservation.startDate}</p>
                            <p><fmt:message key="end.date"/>: ${reservation.endDate}</p>
                        </div>
                    </c:forEach>
                </div>
            </div>
        </div>
    </div>
</div>

<%@include file="footer.jsp"%>