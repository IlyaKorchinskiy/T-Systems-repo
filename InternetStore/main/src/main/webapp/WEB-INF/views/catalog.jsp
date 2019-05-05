<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Catalog category ${category.title}</title>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <c:set var="contextPath" value="${pageContext.request.getContextPath()}"/>

    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css"
          integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS" crossorigin="anonymous">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.2/css/all.css"
          integrity="sha384-fnmOCqbTlWIlj8LyTjo7mOUStjsKC4pOpQbqyi7RrhN7udi9RwhKkMHpvLbHG9Sr" crossorigin="anonymous">
    <link rel="stylesheet" href="${contextPath}/resources/style/main.css">

</head>
<body>
<div class="container-fluid">
    <div class="site-content">
        <jsp:include page="header.jsp"/>
        <div class="row site-padding">
            <jsp:include page="navigation.jsp"/>
            <div class="col content">
                <div class="row filter">
                    <div class="col">
                        <form id="filter" action="catalog" method="get">
                            <div class="form-row">
                                <div hidden class="form-group col">
                                    <input type="number" class="form-control" id="categoryId" name="id"
                                           value="${category.id}">
                                </div>
                                <div class="form-group col">
                                    <input type="number" class="form-control" id="minCostInput" name="minCost"
                                           value="${minCost}" aria-describedby="minCostHelp">
                                    <small id="minCostHelp" class="form-text text-muted">Min price</small>
                                </div>
                                <div class="form-group col">
                                    <input type="number" class="form-control" id="maxCostInput" name="maxCost"
                                           value="${maxCost}" aria-describedby="maxCostHelp">
                                    <small id="maxCostHelp" class="form-text text-muted">Max price</small>
                                </div>
                                <div class="form-group col">
                                    <select class="form-control" id="yearInput" name="year"
                                            aria-describedby="yearHelp">
                                        <option>...</option>
                                        <c:forEach items="${years}" var="year">
                                            <c:choose>
                                                <c:when test="${year == chosenYear}">
                                                    <option value="${year}" selected>${year}</option>
                                                </c:when>
                                                <c:otherwise>
                                                    <option value="${year}">${year}</option>
                                                </c:otherwise>
                                            </c:choose>
                                        </c:forEach>
                                    </select>
                                    <small id="yearHelp" class="form-text text-muted">Published</small>
                                </div>
                                <div class="col">
                                    <button type="submit" class="btn">Filter</button>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
                <div class="row justify-content-start product-list">
                    <%--<c:if test="${empty category}">--%>
                    <%--<c:forEach items="${products}" var="product">--%>
                    <%--<div class="col items">--%>
                    <%--<div class="card">--%>
                    <%--<a href="${contextPath}/catalog/product/${product.id}">--%>
                    <%--<img src="http://192.168.99.100:8190/${product.photoSm}" class="card-img-top"--%>
                    <%--alt="product_photo">--%>
                    <%--<div class="card-body">--%>
                    <%--<h5 class="card-title">${product.title}</h5>--%>
                    <%--<p class="card-text">${product.cost} <i class="fas fa-ruble-sign"></i></p>--%>
                    <%--</div>--%>
                    <%--</a>--%>
                    <%--</div>--%>
                    <%--</div>--%>
                    <%--</c:forEach>--%>
                    <%--</c:if>--%>
                    <c:if test="${not empty category}">
                        <c:forEach items="${category.products}" var="product">
                            <div class="col items">
                                <div class="card">
                                    <a id="productLink${product.id}" href="${contextPath}/catalog/product/${product.id}">
                                        <img src="http://192.168.99.100:8190/${product.photoSm}" class="card-img-top"
                                             alt="product_photo">
                                        <div class="card-body">
                                            <h5 class="card-title">${product.title}</h5>

                                        </div>
                                    </a>
                                    <div class="row">
                                        <div class="col price">
                                            <p class="card-text">${product.cost}
                                                <i class="fas fa-ruble-sign"></i></p>
                                        </div>
                                        <div class="col add-product-div">
                                            <button id="add-product-btn" type="button" class="btn"
                                                    data-toggle="modal" data-target="#addProductModal"
                                                    onclick="addToCart('${product.id}')">
                                                <i class="fas fa-cart-plus"></i>
                                            </button>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </c:forEach>
                    </c:if>
                </div>
            </div>
        </div>
        <!-- Modal -->
        <div class="modal fade" id="addProductModal" tabindex="-1" role="dialog" aria-labelledby="addProductModalTitle"
             aria-hidden="true">
            <div class="modal-dialog modal-dialog-centered" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="addProductModalTitle">Cart</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <p id="addProductMessage"></p>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Continue shopping</button>
                        <a type="button" class="btn btn-primary" href="${contextPath}/cart" role="button">Go to cart</a>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <jsp:include page="footer.jsp"/>
</div>

<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
        integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
        crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.6/umd/popper.min.js"
        integrity="sha384-wHAiFfRlMFy6i5SRaxvfOCifBUQy1xHdJ/yoi7FRNXMRBu5WHdZYu1hA6ZOblgut"
        crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/js/bootstrap.min.js"
        integrity="sha384-B0UglyR+jN6CkvvICOB2joaf5I4l3gm9GU6Hc1og6Ls7i6U/mkkaduKaBhlAXv9k"
        crossorigin="anonymous"></script>
<script>var contextPath = '${contextPath}'</script>

<script src="${contextPath}/resources/js/common.js"></script>
<script src="${contextPath}/resources/js/catalog.js"></script>

</body>
</html>
