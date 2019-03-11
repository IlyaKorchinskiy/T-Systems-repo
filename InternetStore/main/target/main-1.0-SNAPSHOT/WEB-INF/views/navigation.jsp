<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>

<div class="col-sm-3">
    <ul class="nav nav-pills flex-column menu">
        <li class="nav-item">
            <a class="nav-link disabled">Категории</a>
        </li>
        <c:forEach items="${mainCategories}" var="mainCategory">
            <li class="nav-item">
                <a class="nav-link ${(mainCategory.id == category.id || mainCategory.id == category.parentId) ? 'active' : ''}"
                   href="catalog?id=${mainCategory.id}">${mainCategory.title}</a>
                <c:if test="${mainCategory.id == category.id || mainCategory.id == category.parentId}">
                    <ul class="nav flex-column subMenu">
                        <c:forEach items="${subCategories}" var="subCategory">
                            <li class="nav-item">
                                <a class="nav-link ${subCategory.id == category.id ? 'active' : ''}"
                                   href="catalog?id=${subCategory.id}">${subCategory.title}</a>
                            </li>
                        </c:forEach>
                    </ul>
                </c:if>

            </li>
        </c:forEach>
    </ul>
</div>
