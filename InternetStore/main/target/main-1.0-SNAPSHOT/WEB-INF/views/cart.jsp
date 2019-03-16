<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Корзина</title>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <c:set var="contextPath" value="<%=request.getContextPath()%>"/>

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
        <div class="row cart-products site-padding">
            <div class="col">
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
        <div class="row site-padding order-params">
            <div class="col">
                <form id="orderForm" method="post">
                    <div class="form-row">
                        <div class="form-group col-md-4">
                            <h6>Способ оплаты</h6>
                            <div class="form-check">
                                <input class="form-check-input" type="radio" name="paymentType" id="paymentType1"
                                       value="option1" checked>
                                <label class="form-check-label" for="paymentType1">Наличные</label>
                            </div>
                            <div class="form-check">
                                <input class="form-check-input" type="radio" name="paymentType" id="paymentType2"
                                       value="option2">
                                <label class="form-check-label" for="paymentType2">Банковская карта</label>
                            </div>
                        </div>
                        <div class="form-group col-md-4">
                            <h6>Способ доставки</h6>
                            <div class="form-check">
                                <input class="form-check-input" type="radio" name="deliveryType" id="deliveryType1"
                                       value="option1" checked onchange="showAddressInput()">
                                <label class="form-check-label" for="deliveryType1">Доставка</label>
                            </div>
                            <div class="form-check">
                                <input class="form-check-input" type="radio" name="deliveryType" id="deliveryType2"
                                       value="option2" onchange="showAddressInput()">
                                <label class="form-check-label" for="deliveryType2">Самовывоз</label>
                            </div>
                            <div id="addressDiv" class="form-group address-input">
                                <label for="addressInput">Адрес доставки</label>
                                <input type="text" class="form-control" id="addressInput" placeholder="Введите">
                            </div>
                            <div id="pickupDiv" class="form-group pickup-input hidden">
                                <label for="pickupInput">Адрес самовывоза</label>
                                <select id="pickupInput" class="form-control">
                                    <option selected>Выберите...</option>
                                    <%--<c:forEach items="${pickupAddresses}" var="address">--%>
                                        <%--<option>${address.title}</option>--%>
                                    <%--</c:forEach>--%>
                                </select>
                            </div>
                        </div>
                        <div class="form-group col-md-4 confirm">
                            <p class="sum">Итого: <span>${sum} <i class="fas fa-ruble-sign"></i></span></p>
                            <button type="submit" class="btn btn-primary">Подтвердить заказ</button>
                        </div>
                    </div>

                </form>
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
<script src="${contextPath}/resources/js/cart.js"></script>
<script src="${contextPath}/resources/js/common.js"></script>
</body>
</html>
