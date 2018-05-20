<%@include file="header.jsp"%>

<div class="row">
    <div class="container">
        <div class="col-md-8 col-md-offset-2">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h1 class="panel-title"><strong><fmt:message key="reservations"/></strong></h1>
                    <nav aria-label="pavigation"><ul class="pagination">
                        <c:choose>
                            <c:when test="${total == 0}">
                                <fmt:message key="no.reservations"/>
                            </c:when>
                            <c:otherwise>
                                <c:choose>
                                    <c:when test="${page == 1}">
                                        <li class="page-item disabled"><a class="page-link" href="${pageContext.request.contextPath}/hotel/clientReservations?page=1"><<</a></li>
                                    </c:when>
                                    <c:otherwise>
                                        <li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/hotel/clientReservations?page=1"><<</a></li>
                                    </c:otherwise>
                                </c:choose>
                                <c:if test="${page != 1}">
                                    <li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/hotel/clientReservations?page=${page - 1}">${page - 1}</a></li>
                                </c:if>
                                <li class="page-item active"><a class="page-link" href="${pageContext.request.contextPath}/hotel/clientReservations?page=${page}">${page}</a></li>
                                <c:if test="${page != total}">
                                    <li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/hotel/clientReservations?page=${page + 1}">${page + 1}</a></li>
                                </c:if>
                                <c:choose>
                                    <c:when test="${page == total}">
                                        <li class="page-item disabled"><a class="page-link" href="${pageContext.request.contextPath}/hotel/clientReservations?page=${total}">>></a></li>
                                    </c:when>
                                    <c:otherwise>
                                        <li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/hotel/clientReservations?page=${total}">>></a></li>
                                    </c:otherwise>
                                </c:choose>
                            </c:otherwise>
                        </c:choose>
                    </ul></nav>
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