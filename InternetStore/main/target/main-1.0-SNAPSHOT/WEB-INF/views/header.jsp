<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<div class="row header">
    <div class="col">
        <div class="row header-menu site-padding">

            <div class="col">
                <ul class="nav justify-content-end">
                    <sec:authorize access="hasAnyRole('ADMIN', 'SUPER_ADMIN')">
                    <li class="nav-item">
                    <a class="nav-link" href="<c:url value="/admin"/>">Админ</a>
                    </li>
                    </sec:authorize>
                    <sec:authorize access="!isAuthenticated()">
                    <li class="nav-item">
                        <a class="nav-link" data-toggle="modal" data-target="#loginModalForm" href="#">Вход</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="#">Регистрация</a>
                    </li>
                    </sec:authorize>
                    <sec:authorize access="isAuthenticated()">
                        <li class="nav-item">
                            <a class="nav-link" href="#">${sessionScope.get("user")}</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="<c:url value="/logout"/>">Выход</a>
                        </li>
                    </sec:authorize>

                </ul>
            </div>
        </div>
        <div class="row align-items-center header-content site-padding">
            <div class="col-sm-3 logo">
                <a href="<c:url value="index.jsp"/>"><img src="resources\img\logo.png" alt="logo"></a>
            </div>
            <div class="col-sm-5">
                <div class="input-group mb-3">
                    <input type="text" class="form-control" placeholder="Введите название или автора"
                           aria-describedby="button-addon2">
                    <div class="input-group-append">
                        <button class="btn" type="button" id="button-addon2">Найти</button>
                    </div>
                </div>
            </div>
            <div class="col"></div>
        </div>
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
                                       placeholder="Enter email" required autofocus>
                            </div>
                            <div class="form-group">
                                <label for="passwordInput">Пароль</label>
                                <input type="password" class="form-control" id="passwordInput" name="j_password"
                                       placeholder="Password" required>
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
    </div>
</div>
