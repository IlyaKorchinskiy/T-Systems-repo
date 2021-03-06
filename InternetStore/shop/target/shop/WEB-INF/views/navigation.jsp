<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<c:set var="contextPath" value="${pageContext.request.getContextPath()}"/>

<div class="col-sm-3 navigation">
    <ul class="nav nav-pills flex-column menu">
        <li class="nav-item">
            <a class="nav-link disabled">Categories</a>
        </li>
        <c:forEach items="${mainCategories}" var="mainCategory">
            <li class="nav-item">
                <a class="nav-link category ${(mainCategory.id == category.id || mainCategory.id == category.parentId) ? 'active' : ''}"
                   href="${contextPath}/catalog?id=${mainCategory.id}">${mainCategory.title}</a>
                <c:if test="${mainCategory.id == category.id || mainCategory.id == category.parentId}">
                    <ul class="nav flex-column subMenu">
                        <c:forEach items="${subCategories}" var="subCategory">
                            <li class="nav-item">
                                <a class="nav-link ${subCategory.id == category.id ? 'active' : ''}"
                                   href="${contextPath}/catalog?id=${subCategory.id}">${subCategory.title}</a>
                            </li>
                        </c:forEach>
                    </ul>
                </c:if>

            </li>
        </c:forEach>
    </ul>
</div>
