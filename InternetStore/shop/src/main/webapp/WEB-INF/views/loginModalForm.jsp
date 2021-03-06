<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<c:set var="contextPath" value="${pageContext.request.getContextPath()}"/>

<!-- Modal -->
<div class="modal fade" id="loginModalForm" tabindex="-1" role="dialog" aria-labelledby="loginModalFormTitle"
     aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="loginModalFormTitle">Sign in</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <form action="${contextPath}/j_spring_security_check" method="post">
                <div class="modal-body">
                    <div class="form-group">
                        <label for="modalLoginInput">Login (e-mail)</label>
                        <input type="email" class="form-control" id="modalLoginInput" name="j_username"
                               placeholder="E-mail" required autofocus>
                    </div>
                    <div class="form-group">
                        <label for="modalPasswordInput">Password</label>
                        <input type="password" class="form-control" id="modalPasswordInput" name="j_password"
                               placeholder="Password" required>
                    </div>
                </div>
                <div class="modal-footer">
                    <a type="button" class="btn btn-secondary" href="${contextPath}/register">Sign up</a>
                    <button id="login-submit-btn" type="submit" class="btn btn-primary">Log in</button>
                </div>
            </form>
        </div>
    </div>
</div>
