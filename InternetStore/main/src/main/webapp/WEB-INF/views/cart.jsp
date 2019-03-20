<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<html>
<head>
    <title>Корзина</title>
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
                        <th scope="col">Название</th>
                        <th scope="col">Количество</th>
                        <th scope="col">Цена</th>
                        <th scope="col">Сумма</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${cartProducts}" var="cartProduct">
                        <tr>
                            <td class="title">${cartProduct.product.title}</td>
                            <td>${cartProduct.amount}</td>
                            <td>${cartProduct.product.cost}</td>
                            <td>${cartProduct.sum}</td>
                        </tr>
                    </c:forEach>

                    </tbody>
                </table>
            </div>
        </div>
        <sec:authorize access="!isAuthenticated()">
            <div class="row site-padding auth justify-content-center">
                <div class="col-md-6">
                    <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#loginModalForm">
                        Войдите
                    </button>
                    чтобы оформить заказ
                </div>
            </div>
        </sec:authorize>
        <sec:authorize access="isAuthenticated()">
            <div class="row site-padding order-params">
                <div class="col">
                    <form id="orderForm" action="${contextPath}/order" method="post" modelAttribute="order">
                        <div class="form-row">
                            <div class="form-group col param">
                                <h6>Способ доставки</h6>
                                <c:forEach items="${deliveryTypes}" var="deliveryType">
                                    <div class="form-check">
                                        <input class="form-check-input" type="radio" name="deliveryTypeId"
                                               id="deliveryType${deliveryType.id}" value="${deliveryType.id}"
                                               onchange="showAddressInput()" checked>
                                        <label class="form-check-label"
                                               for="deliveryType${deliveryType.id}">${deliveryType.deliveryType}</label>
                                    </div>
                                </c:forEach>
                                <div id="addressDiv" class="form-group address-input">
                                    <label for="addressInput">Адрес доставки</label>
                                    <input type="text" class="form-control" id="addressInput" name="address"
                                           placeholder="Введите">
                                </div>
                                <div id="pickupDiv" class="form-group pickup-input hidden">
                                    <label for="pickupInput">Адрес самовывоза</label>
                                    <select id="pickupInput" class="form-control" name="pickupAddress">
                                        <option value="" selected>Выберите...</option>
                                            <%--<c:forEach items="${pickupAddresses}" var="address">--%>
                                            <%--<option>${address.title}</option>--%>
                                            <%--</c:forEach>--%>
                                    </select>
                                </div>
                            </div>
                            <div class="form-group col param">
                                <h6>Способ оплаты</h6>
                                <c:forEach items="${paymentTypes}" var="paymentType">
                                    <div class="form-check">
                                        <input class="form-check-input" type="radio" name="paymentTypeId"
                                               id="paymentType${paymentType.id}" value="${paymentType.id}">
                                        <label class="form-check-label"
                                               for="paymentType${paymentType.id}">${paymentType.paymentType}</label>
                                    </div>
                                </c:forEach>
                            </div>
                            <div class="form-group col confirm param">
                                <p class="sum">Итого: <span>${sum} <i class="fas fa-ruble-sign"></i></span></p>
                                <button type="submit" class="btn btn-primary">Подтвердить заказ</button>
                            </div>
                        </div>

                    </form>
                </div>
            </div>
        </sec:authorize>
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
    var deliveryTypeId = '${deliveryTypes.get(0).id}';
    var paymentTypeId = '${paymentTypes.get(0).id}';
</script>
<script src="${contextPath}/resources/js/cart.js"></script>
<script src="${contextPath}/resources/js/common.js"></script>

</body>
</html>
