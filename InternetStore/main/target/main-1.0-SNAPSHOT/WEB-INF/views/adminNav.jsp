<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="contextPath" value="${pageContext.request.getContextPath()}"/>
<c:set var="currentPath" value="${requestScope['javax.servlet.forward.request_uri']}"/>

<div class="row admin-nav">
    <div class="col">
        <ul class="nav nav-tabs">
            <li class="nav-item">
                <a class="nav-link ${fn:contains(currentPath, 'orders') ? 'active' : ''}"
                   href="${contextPath}/admin/orders">Orders</a>
            </li>
            <li class="nav-item">
                <a class="nav-link ${fn:contains(currentPath, 'products') ? 'active' : ''}"
                   href="${contextPath}/admin/products">Products</a>
            </li>
            <li class="nav-item">
                <a class="nav-link ${fn:contains(currentPath, 'categories') ? 'active' : ''}"
                   href="${contextPath}/admin/categories">Categories</a>
            </li>
            <li class="nav-item">
                <a class="nav-link ${fn:contains(currentPath, 'stats') ? 'active' : ''}"
                   href="${contextPath}/admin/stats">Stats</a>
            </li>
        </ul>
    </div>

</div>
