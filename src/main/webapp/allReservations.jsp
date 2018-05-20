<%@include file="header.jsp"%>

<div class="col-md-10 col-md-offset-1 col-sm-12 ">
    <div class="panel panel-default" style="padding: 10px !important; margin-bottom: 0px !important;">
        <div class="row">
            <div class="col-sm-12" align="center">
                <form action="<c:url value="${pageContext.request.contextPath}/hotel/allReservations" />" method='GET'>
                    <div class="col-sm-6 col-md-4">
                        <div class="input-group">
                            <div class="form-group has-feedback has-feedback-left">
                                <label class="control-label"><fmt:message key="select.days"/></label>
                                <input type="text" class="form-control" id="datefilter"  name="datefilter">
                                <i class="form-control-feedback glyphicon glyphicon-calendar"></i>
                            </div>
                        </div>
                    </div>
                    <div class="col-sm-6 col-md-3">
                        <div class="input-group">
                            <label class="control-label"><fmt:message key="room"/></label>
                            <select class="form-control" id="roomId" name="roomId">
                                <option></option>
                                <c:forEach items="${rooms}" var="room">
                                    <option value="${room.id}">${room.roomNumber}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <div class="col-sm-6 col-md-3">
                        <div class="input-group">
                            <label class="control-label"><fmt:message key="status"/></label>
                            <select class="form-control" id="status" name="status">
                                <option></option>
                                <c:forEach items="${statuses}" var="status">
                                    <option value="${status}">${status}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <div class="col-sm-6 col-md-2">
                        <div class="input-group">
                            <button type="submit" class="btn btn-lg btn-success" style="margin: 5px !important;"><fmt:message key="search"/></button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="container-fluid">
            <h3><fmt:message key="reservations"/> </h3>
            <hr style="border-color: lightblue !important;"/>
            <div class="panel panel-default">
                <div class="panel-body">
                    <table id="reservationsTable" class="table table-striped table-hover table-bordered" class="width-100p">
                        <thead class="panel-center">
                        <tr style="background-color: #BDBDBD;;">
                            <td><fmt:message key="creation.date"/></td>
                            <td><fmt:message key="start.date"/></td>
                            <td><fmt:message key="end.date"/></td>
                            <td><fmt:message key="status"/></td>
                            <td><fmt:message key="room.type"/></td>
                            <td><fmt:message key="room"/></td>
                            <td style="width: 10%" data-orderable="false"><fmt:message key="manage"/></td>
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
                                        <button class="btn btn-sm btn-primary"><i class="glyphicon glyphicon-cog" aria-hidden="true"></i> <fmt:message key="manage"/></button>
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

<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/scripts/allReservations.js"></script>

<script type="text/javascript">
    var default_options = {
        "sScrollY": 375,
        "sScrollX": "100%",
        "sScrollXInner": "100%",
        "bJQueryUI": true,
        "sPaginationType": "full_numbers",
        "bProcessing": true,
        "order": [[0, "asc"]],
        "oLanguage": {
            "sLengthMenu": "<fmt:message key='slm1'/> _MENU_ <fmt:message key='slm2'/>",
            "sSearch": "<fmt:message key='s.search'/>:",
            "sZeroRecords": "<fmt:message key='s.zero.records'/>.",
            "sInfo": "<fmt:message key='si1'/> _START_ <fmt:message key='si2'/> _END_ <fmt:message key='si3'/> _TOTAL_ <fmt:message key='si4'/>",
            "sInfoEmpty": "<fmt:message key='sie'/>",
            "sInfoFiltered": "(<fmt:message key='sf1'/> _MAX_ <fmt:message key='sf2'/>)",
            "oPaginate": {
                "sFirst": "<fmt:message key='opsf'/>",
                "sLast":"<fmt:message key='opsl'/>",
                "sNext":"<fmt:message key='opsn'/>",
                "sPrevious":"<fmt:message key='opsp'/>",
            }
        }
    };
    $('#reservationsTable').DataTable(default_options);

    $(function() {
        if('${language}' == 'uk_UA'){
            moment.locale('uk');
        }else{
            moment.locale('en');
        }
        $('input[name="datefilter"]').daterangepicker({
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
            $(this).val('');
        });
    });
</script>

<%@include file="footer.jsp"%>