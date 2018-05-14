<%@include file="header.jsp"%>

<div class="col-md-10 col-md-offset-1">
    <div class="row">
        <div class="container-fluid">
            <h3>All Reservations </h3>
            <hr style="border-color: lightblue !important;"/>
            <div class="panel panel-default">
                <div class="panel-body">
                    <table id="reservationsTable" class="table table-striped table-hover table-bordered" class="width-100p">
                        <thead class="panel-center">
                        <tr style="background-color: #BDBDBD;;">
                            <td>Date of order</td>
                            <td>Start Date</td>
                            <td>End Date</td>
                            <td>Status</td>
                            <td>Room Type</td>
                            <td>Room number</td>
                            <td style="width: 10%" data-orderable="false">Manage</td>
                        </tr>
                        </thead>
                        <tbody class="panel-center">
                        <c:forEach items="${reservations}" var="r">
                            <tr>
                                <td>
                                    <p>${r.reservationDate.toLocalDate()} ${r.reservationDate.toLocalTime()}</p>
                                </td>
                                <td>
                                    <p>${r.startDate}</p>
                                </td>
                                <td>
                                    <p>${r.endDate}</p>
                                </td>
                                <td>
                                    <p>${r.reservationStatus}</p>
                                </td>
                                <td>
                                    <p>${r.roomType.name}</p>
                                </td>
                                <td>
                                    <p>${r.room.roomNumber}</p>
                                </td>
                                <td>
                                    <a href="${pageContext.request.contextPath}/hotel/reservationDetails?reservationId=${r.id}">
                                        <button class="btn btn-sm btn-primary"><i class="glyphicon glyphicon-cog" aria-hidden="true"></i> Mange</button>
                                    </a>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>

<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/scripts/admin.js"></script>

<%@include file="footer.jsp"%>