<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
    <title>Cart</title>
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
        <div class="row cart-products site-padding justify-content-center">
            <div class="col">
                <c:if test="${message.errors.size() != 0}">
                    <c:forEach items="${message.errors}" var="error">
                        <p class="error">${error}</p>
                    </c:forEach>
                </c:if>
                <table class="table">
                    <thead class="thead-dark">
                    <tr>
                        <th scope="col">Title</th>
                        <th scope="col">Amount</th>
                        <th scope="col">Price</th>
                        <th scope="col">Sum</th>
                        <th></th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${cart.cartProducts}" var="cartProduct">
                        <tr>
                            <td class="title">${cartProduct.product.title}</td>
                            <td>${cartProduct.amount}</td>
                            <td>${cartProduct.product.cost}</td>
                            <td>${cartProduct.sum}</td>
                            <td>
                                <a href="${contextPath}/cart/removeFromCart?id=${cartProduct.product.id}"
                                   class="remove-product-link"><i class="far fa-times-circle"></i>
                                </a>
                            </td>
                        </tr>
                    </c:forEach>

                    </tbody>
                </table>
                <c:if test="${cart.cartProducts.size() == 0 || empty cart}">
                    <p>Cart is empty</p>
                </c:if>
            </div>
        </div>
        <c:if test="${cart.cartProducts.size() != 0}">
            <sec:authorize access="!isAuthenticated()">
                <div class="row site-padding auth justify-content-center">
                    <div class="col-md-6">
                        Please
                        <button id="login-btn" type="button" class="btn btn-primary" data-toggle="modal"
                                data-target="#loginModalForm">
                            Log in
                        </button>
                        to place order
                    </div>
                </div>
            </sec:authorize>
            <sec:authorize access="isAuthenticated()">
                <c:if test="${cart.cartProducts.size() != 0 && not empty cart}">
                    <div class="row site-padding order-params">
                        <div class="col">
                            <form id="orderForm" action="${contextPath}/order" method="post" modelAttribute="order">
                                <div class="row">
                                    <div class="col">
                                        <div class="form-group param delivery">
                                            <div class="form-group">
                                                <legend class="col-form-label">Delivery type</legend>
                                                <c:forEach items="${deliveryTypes}" var="deliveryType">
                                                    <div class="form-check">
                                                        <input class="form-check-input" type="radio" name="deliveryType"
                                                               id="deliveryType-${deliveryType}" value="${deliveryType}"
                                                               onchange="showAddressInput()">
                                                        <label class="form-check-label"
                                                               for="deliveryType-${deliveryType}">${deliveryType}</label>
                                                    </div>
                                                </c:forEach>
                                            </div>
                                            <div id="addressDiv" class="form-group">
                                                <legend class="col-form-label">Your addresses:</legend>
                                                <c:forEach items="${cart.user.addresses}" var="address">
                                                    <div class="form-check">
                                                        <input class="form-check-input" type="radio" name="address"
                                                               id="addressInput${address.id}" value="${address.address}">
                                                        <label class="form-check-label" for="addressInput${address.id}">
                                                                ${address.address}
                                                        </label>
                                                    </div>
                                                </c:forEach>
                                                <c:if test="${empty cart.user.addresses}">
                                                    <p class="error">You don't have any addresses</p>
                                                    <button id="addAddressBtn" type="button" class="btn btn-primary"
                                                            onclick="showAddressForm()">
                                                        Add new address
                                                    </button>
                                                </c:if>
                                            </div>
                                            <div id="pickupDiv" class="form-group pickup-input hidden">
                                                <label for="pickupInput">Pickup address</label>
                                                <select id="pickupInput" class="form-control" name="pickupAddress">
                                                    <option value="" selected>Choose...</option>
                                                    <c:forEach items="${pickupAddresses}" var="address">
                                                        <option>${address.address}</option>
                                                    </c:forEach>
                                                </select>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col">
                                        <div class="form-group param">
                                            <legend class="col-form-label">Payment type</legend>
                                            <c:forEach items="${paymentTypes}" var="paymentType">
                                                <div class="form-check">
                                                    <input class="form-check-input" type="radio" name="paymentType"
                                                           id="paymentType-${paymentType}" value="${paymentType}">
                                                    <label class="form-check-label"
                                                           for="paymentType-${paymentType}">${paymentType}</label>
                                                </div>
                                            </c:forEach>
                                        </div>
                                        <div class="form-group confirm param">
                                            <p class="sum">Total: <span>${sum} <i class="fas fa-ruble-sign"></i></span>
                                            </p>
                                            <input type="number" name="sum" value="${sum}" hidden>
                                            <button id="confirm-order-btn" type="submit" class="btn btn-primary">Confirm
                                                order
                                            </button>
                                        </div>
                                    </div>
                                </div>
                            </form>
                            <form id="addAddressForm" action="${contextPath}/profile/addAddress" method="post" hidden>
                                <div class="row">
                                    <div class="form-group col-md-6">
                                        <label for="newAddressInput" class="form-label">New address</label>
                                        <input type="text" class="form-control" id="newAddressInput" name="address"
                                               placeholder="Address" required autofocus>
                                    </div>
                                </div>
                                <button type="submit" class="btn btn-primary">Save</button>
                            </form>
                        </div>
                    </div>
                </c:if>
            </sec:authorize>
        </c:if>

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
<script>
    var contextPath = '${contextPath}';
    var deliveryType = '${deliveryTypes[0]}';
    var paymentType = '${paymentTypes[0]}';
</script>
<script src="${contextPath}/resources/js/cart.js"></script>
<script src="${contextPath}/resources/js/common.js"></script>

</body>
</html>
