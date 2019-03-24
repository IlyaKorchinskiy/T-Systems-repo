<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="contextPath" value="${pageContext.request.getContextPath()}"/>
<c:set var="currentPath" value="${requestScope['javax.servlet.forward.request_uri']}"/>

<div class="row header">
    <div class="col">
        <div class="row header-menu site-padding">
            <div class="col">
                <ul class="nav justify-content-end">
                    <%--<sec:authorize access="hasAnyRole('ADMIN', 'SUPER_ADMIN')">--%>
                        <li class="nav-item">
                            <a class="nav-link ${fn:contains(currentPath, 'admin') ? 'active' : ''}"
                               href="${contextPath}/admin/orders">Админ</a>
                        </li>
                    <%--</sec:authorize>--%>
                    <sec:authorize access="!isAuthenticated()">
                        <li class="nav-item">
                            <a class="nav-link" data-toggle="modal" data-target="#loginModalForm" href="#">Вход</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="${contextPath}/register">Регистрация</a>
                        </li>
                    </sec:authorize>
                    <sec:authorize access="isAuthenticated()">
                        <li class="nav-item">
                            <a class="nav-link ${fn:contains(currentPath, 'profile') ? 'active' : ''}"
                               href="${contextPath}/profile">Профиль ${sessionScope.get("user").getName()}</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="${contextPath}/logout">Выход</a>
                        </li>
                    </sec:authorize>
                </ul>
            </div>
        </div>
        <div class="row align-items-center header-content site-padding">
            <div class="col-sm-3 logo">
                <a href="${contextPath}"><img src="${contextPath}/resources/img/logo.png" alt="logo"></a>
            </div>
            <div class="col search">
                <div class="input-group">
                    <input type="text" class="form-control" placeholder="Введите название или автора"
                           aria-describedby="button-addon2">
                    <div class="input-group-append">
                        <button class="btn" type="button" id="button-addon2">Найти</button>
                    </div>
                </div>
            </div>
            <div class="col-sm-2 cart">
                <a href="${contextPath}/cart">
                    <i class="fas fa-shopping-cart"></i>
                    <span id="cart-badge" class="badge badge-primary">${cart.products.size()}</span>
                    <div id="cart-content" class="cart-content"></div>
                </a>
            </div>
        </div>
        <jsp:include page="loginModalForm.jsp"/>
    </div>
</div>
