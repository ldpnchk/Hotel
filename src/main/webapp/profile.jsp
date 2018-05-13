<%@include file="header.jsp"%>

<div class="modal-dialog" role="document">
    <div class="panel panel-default">
        <div class="modal-header">
            <h4 class="modal-title" id="myModal"><fmt:message key="update.profile"/></h4>
        </div>
        <form action="${pageContext.request.contextPath}/hotel/updateUser" method="post" >
            <div class="modal-body">
                <div class="form-group">
                    <label for="username"><fmt:message key="username"/></label>
                    <input type="text" class="form-control" id="username" name="username" value="${user.username}" required>
                </div>
                <div class="form-group">
                    <label for="email"><fmt:message key="email.address"/></label>
                    <input type="email" class="form-control" id="email" name="email" value="${user.email}" required>
                </div>
                <div class="form-group">
                    <label for="phonenumber"><fmt:message key="phone.number"/></label>
                    <input type="number" class="form-control" id="phonenumber" name="phonenumber" value="${user.phoneNumber}" required>
                </div>
                <div class="form-group">
                    <label for="firstname"><fmt:message key="first.name"/></label>
                    <input type="text" class="form-control" id="firstname" name="firstname" value="${user.firstName}" required>
                </div>
                <div class="form-group">
                    <label for="lastname"><fmt:message key="last.name"/></label>
                    <input type="text" class="form-control" id="lastname" name="lastname" value="${user.lastName}" required>
                </div>
                <div class="form-group">
                    <label for="patronymic"><fmt:message key="patronymic"/></label>
                    <input type="text" class="form-control" id="patronymic" name="patronymic" value="${user.patronymic}">
                </div>
            </div>
            <div class="modal-footer">
                <button type="submit" class="btn btn-success"><fmt:message key="update"/></button>
            </div>
        </form>
    </div>
</div>

<%@include file="footer.jsp"%>