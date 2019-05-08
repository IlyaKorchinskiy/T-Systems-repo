<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<html>
<head>
    <title>Order ${order.id}</title>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <c:set var="contextPath" value="${pageContext.request.getContextPath()}"/>

    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css"
          integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS" crossorigin="anonymous">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.2/css/all.css"
          integrity="sha384-fnmOCqbTlWIlj8LyTjo7mOUStjsKC4pOpQbqyi7RrhN7udi9RwhKkMHpvLbHG9Sr" crossorigin="anonymous">
    <link rel="stylesheet" href="${contextPath}/resources/style/main.css">
</head>
<body>
<div class="container-fluid admin">
    <div class="site-content">
        <jsp:include page="header.jsp"/>
        <jsp:include page="adminNav.jsp"/>
        <h2>Order ${order.id}</h2>
        <div class="row">
            <div class="col content">
                <div class="row">
                    <div class="col">
                        <div class="form-group row">
                            <label for="idInput" class="col-sm-4 col-form-label">Order ID</label>
                            <div class="col">
                                <input type="number" class="form-control" id="idInput" value="${order.id}" readonly>
                            </div>
                        </div>
                        <div class="form-group row">
                            <label for="clientInput" class="col-sm-4 col-form-label">Client name</label>
                            <div class="col">
                                <input type="text" class="form-control" id="clientInput"
                                       value="${order.user.name} ${order.user.lastname}" disabled>
                            </div>
                        </div>
                        <div class="form-group row">
                            <label for="paymentTypeInput" class="col-sm-4 col-form-label">Payment type</label>
                            <div class="input-group col">
                                <select id="paymentTypeInput" class="form-control" required disabled>
                                    <c:forEach items="${paymentTypes}" var="paymentType">
                                        <c:choose>
                                            <c:when test="${paymentType == order.paymentType}">
                                                <option value="${paymentType}"
                                                        selected>${paymentType}</option>
                                            </c:when>
                                            <c:otherwise>
                                                <option value="${paymentType}">${paymentType}</option>
                                            </c:otherwise>
                                        </c:choose>
                                    </c:forEach>
                                </select>
                                <div class="input-group-append">
                                    <button id="btnEditPaymentType" type="button" class="btn btn-secondary">
                                        <i class="far fa-edit"></i>
                                    </button>
                                </div>
                            </div>
                        </div>
                        <div class="form-group row">
                            <label for="deliveryTypeInput" class="col-sm-4 col-form-label">Delivery type</label>
                            <div class="input-group col">
                                <select id="deliveryTypeInput" class="form-control" required disabled>
                                    <c:forEach items="${deliveryTypes}" var="deliveryType">
                                        <c:choose>
                                            <c:when test="${deliveryType == order.deliveryType}">
                                                <option value="${deliveryType}" selected>${deliveryType}</option>
                                            </c:when>
                                            <c:otherwise>
                                                <option value="${deliveryType}">${deliveryType}</option>
                                            </c:otherwise>
                                        </c:choose>
                                    </c:forEach>
                                </select>
                                <div class="input-group-append">
                                    <button id="btnEditDeliveryType" type="button" class="btn btn-secondary">
                                        <i class="far fa-edit"></i>
                                    </button>
                                </div>
                            </div>

                        </div>
                        <div class="form-group row">
                            <label for="paymentStatusInput" class="col-sm-4 col-form-label">Payment status</label>
                            <div class="input-group col">
                                <select id="paymentStatusInput" class="form-control" required disabled>
                                    <c:forEach items="${paymentStatuses}" var="paymentStatus">
                                        <c:choose>
                                            <c:when test="${paymentStatus == order.paymentStatus}">
                                                <option value="${paymentStatus}" selected>${paymentStatus}</option>
                                            </c:when>
                                            <c:otherwise>
                                                <option value="${paymentStatus}">${paymentStatus}</option>
                                            </c:otherwise>
                                        </c:choose>
                                    </c:forEach>
                                </select>
                                <div class="input-group-append">
                                    <button id="btnEditPaymentStatus" type="button" class="btn btn-secondary">
                                        <i class="far fa-edit"></i>
                                    </button>
                                </div>
                            </div>
                        </div>
                        <div class="form-group row">
                            <label for="orderStatusInput" class="col-sm-4 col-form-label">Order status</label>
                            <div class="input-group col">
                                <select id="orderStatusInput" class="form-control" required disabled>
                                    <c:forEach items="${orderStatuses}" var="orderStatus">
                                        <c:choose>
                                            <c:when test="${orderStatus == order.orderStatus}">
                                                <option value="${orderStatus}" selected>${orderStatus}</option>
                                            </c:when>
                                            <c:otherwise>
                                                <option value="${orderStatus}">${orderStatus}</option>
                                            </c:otherwise>
                                        </c:choose>
                                    </c:forEach>
                                </select>
                                <div class="input-group-append">
                                    <button id="orderStatusBtnSave" type="button" class="btn btn-primary"
                                            onclick="saveField('orderStatus')" hidden>
                                        <i class="fas fa-check"></i>
                                    </button>
                                    <button id="orderStatusBtnEdit" type="button" class="btn btn-secondary"
                                            onclick="editField('orderStatus')">
                                        <i class="far fa-edit"></i>
                                    </button>
                                </div>
                            </div>
                        </div>
                        <p id="orderStatusMessage" class="confirm"></p>
                        <div class="form-group">
                            <label for="addressInput">Address</label>
                            <input type="number" class="form-control" id="addressInput" required disabled>
                        </div>
                    </div>
                    <div class="col">
                        <h4>Products</h4>
                        <table class="table">
                            <thead>
                            <tr>
                                <th scope="col">Title</th>
                                <th scope="col">Amount</th>
                                <th scope="col">Price</th>
                                <th scope="col">Sum</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${orderProducts}" var="orderProduct">
                                <tr>
                                    <td class="title">${orderProduct.product.title}</td>
                                    <td>${orderProduct.amount}</td>
                                    <td>${orderProduct.product.cost}</td>
                                    <td>${orderProduct.amount * orderProduct.cost}</td>
                                </tr>
                            </c:forEach>

                            </tbody>
                        </table>

                    </div>
                </div>
                <div class="row">
                    <div class="col">
                        <h4>Order history</h4>
                        <table class="table">
                            <thead>
                            <tr>
                                <th scope="col">OrderHistory ID</th>
                                <th scope="col">Payment type</th>
                                <th scope="col">Delivery type</th>
                                <th scope="col">Payment status</th>
                                <th scope="col">Order status</th>
                                <th scope="col">Sum</th>
                                <th scope="col">Address</th>
                                <th scope="col">Date</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${orderHistories}" var="orderHistory">
                                <tr>
                                    <td>${orderHistory.id}</td>
                                    <td>${orderHistory.paymentType}</td>
                                    <td>${orderHistory.deliveryType}</td>
                                    <td>${orderHistory.paymentStatus}</td>
                                    <td>${orderHistory.orderStatus}</td>
                                    <td>${orderHistory.sum}</td>
                                    <td>${orderHistory.address}</td>
                                    <td>${orderHistory.date}</td>
                                </tr>
                            </c:forEach>

                            </tbody>
                        </table>
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
<script>var productId = '${product.id}'</script>
<script src="${contextPath}/resources/js/common.js"></script>
<script src="${contextPath}/resources/js/adminOrderPage.js"></script>
<script src="${contextPath}/resources/js/admin.js"></script>
</body>
</html>



