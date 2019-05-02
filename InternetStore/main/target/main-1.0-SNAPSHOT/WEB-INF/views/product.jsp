<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Product ${product.title}</title>
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
            <div class="col-auto product-photo">
                <a href="#"><img src="http://192.168.99.100:8190/${product.photoMd}" alt="product_photo"></a>
            </div>
            <div class="col product-info">
                <h2>${product.title}</h2>
                <p class="description">${product.description}</p>
                <span>Genres:</span>
                <ul class="nav genres">
                    <c:forEach items="${product.categories}" var="category">
                        <li class="nav-item">
                            <a class="nav-link" href="${contextPath}/catalog?id=${category.id}">${category.title}</a>
                        </li>
                    </c:forEach>
                </ul>
                <p>Author: ${product.author}</p>
                <p>Published: ${product.year}</p>
                <p>Stock amount: ${product.amount}</p>
                <div class="row price-buy">
                    <div class="col-lg-6 price">Price: <span>${product.cost} <i class="fas fa-ruble-sign"></i></span>
                    </div>
                    <div class="col button">
                        <button id="add-product-btn" type="button" class="btn" data-toggle="modal"
                                data-target="#addProductModal" onclick="addToCart('${product.id}')">Add to cart
                        </button>
                    </div>
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
<script>var sessionId = '${pageContext.session.getId()}'</script>
<script src="${contextPath}/resources/js/product.js"></script>
<script src="${contextPath}/resources/js/common.js"></script>

</body>
</html>
