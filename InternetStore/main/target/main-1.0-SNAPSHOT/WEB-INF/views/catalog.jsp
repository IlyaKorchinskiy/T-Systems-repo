<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Catalog</title>
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
                                <div class="col">
                                    <button type="submit" class="btn">Filter</button>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
                <div class="row justify-content-start product-list">
                    <c:forEach items="${category.products}" var="product">
                        <div class="col">
                            <div class="card">
                                <a href="catalog/product/${product.id}">
                                    <img src="${contextPath}/resources/img/${product.photoSm}" class="card-img-top" alt="product_photo">
                                    <div class="card-body">
                                        <h5 class="card-title">${product.title}</h5>
                                        <p class="card-text">${product.cost} <i class="fas fa-ruble-sign"></i></p>
                                    </div>
                                </a>
                            </div>
                        </div>
                    </c:forEach>
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
