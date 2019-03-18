<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>

<!-- Modal -->
<div class="modal fade" id="loginModalForm" tabindex="-1" role="dialog" aria-labelledby="loginModalFormTitle"
     aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="loginModalFormTitle">Вход</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <c:url value="/j_spring_security_check" var="checkUrl"/>
            <form action="${checkUrl}" method="post">
                <div class="modal-body">
                    <div class="form-group">
                        <label for="loginInput">Логин (e-mail)</label>
                        <input type="email" class="form-control" id="loginInput" name="j_username"
                               placeholder="E-mail" required autofocus>
                    </div>
                    <div class="form-group">
                        <label for="passwordInput">Пароль</label>
                        <input type="password" class="form-control" id="passwordInput" name="j_password"
                               placeholder="Пароль" required>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Отмена</button>
                    <button type="submit" class="btn btn-primary">Войти</button>
                </div>
            </form>
        </div>
    </div>
</div>
