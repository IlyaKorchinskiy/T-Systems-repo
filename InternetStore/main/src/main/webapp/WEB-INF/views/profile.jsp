<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>

<html>
<head>
    <title>Profile</title>
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
        <div class="row site-padding justify-content-center">
            <div class="col-lg-9 profile content">
                <h2>Profile ${user.name} ${user.lastname}</h2>
                <h4>Info</h4>
                <c:if test="${not empty infoMessage && infoMessage.confirms.size() != 0}">
                    <p id="infoMessage" class="confirm">${infoMessage.confirms.get(0)}</p>
                </c:if>
                <form:form id="userInfo" action="${contextPath}/profile/editUserInfo" method="post"
                           modelAttribute="user">
                    <form:errors cssClass="error"/>
                    <form:input type="number" class="form-control" path="id" value="${user.id}" hidden="true"/>
                    <div class="form-group row">
                        <label for="nameInput" class="col-sm-3 col-form-label">Name</label>
                        <div class="col">
                            <form:input type="text" class="form-control" id="nameInput" path="name"
                                        value="${user.name}" placeholder="Name" disabled="true"/>
                            <form:errors path="name" cssClass="error"/>
                        </div>
                    </div>
                    <div class="form-group row">
                        <label for="lastnameInput" class="col-sm-3 col-form-label">Last name</label>
                        <div class="col">
                            <form:input type="text" class="form-control" id="lastnameInput" path="lastname"
                                        value="${user.lastname}" placeholder="Last name" disabled="true"/>
                            <form:errors path="lastname" cssClass="error"/>
                        </div>
                    </div>
                    <div class="form-group row">
                        <label for="phoneInput" class="col-sm-3 col-form-label">Phone number</label>
                        <div class="col">
                            <form:input type="tel" pattern="\+\d{11}" class="form-control" id="phoneInput"
                                        path="phoneNumber" value="${user.phoneNumber}" placeholder="+79223556789"
                                        disabled="true"/>
                            <form:errors path="phoneNumber" cssClass="error"/>
                        </div>
                    </div>
                    <div class="form-group row">
                        <label for="loginRegInput" class="col-sm-3 col-form-label">Login (e-mail)</label>
                        <div class="col">
                            <form:input type="email" class="form-control" id="loginRegInput" path="email"
                                        value="${user.email}" placeholder="E-mail" disabled="true"/>
                            <form:errors path="email" cssClass="error"/>
                        </div>
                    </div>
                    <div class="form-group row">
                        <label for="birthInput" class="col-sm-3 col-form-label">Birthday</label>
                        <div class="col">
                            <form:input type="date" class="form-control" id="birthInput" path="birthday"
                                        value="${user.birthday}" disabled="true"/>
                            <form:errors path="birthday" cssClass="error"/>
                        </div>
                    </div>
                    <div class="form-group row">
                        <label for="passwordInput" class="col-sm-3 col-form-label">Password</label>
                        <div class="col">
                            <form:input type="password" class="form-control" id="passwordInput" path="password"
                                        value="${user.password}" disabled="true"/>
                            <form:errors path="password" cssClass="error"/>
                        </div>
                    </div>
                    <div class="form-group row">
                        <label for="matchingPasswordInput" class="col-sm-3 col-form-label">Repeat password</label>
                        <div class="col">
                            <form:input type="password" class="form-control" id="matchingPasswordInput"
                                        path="matchingPassword" value="${user.password}" disabled="true"/>
                            <form:errors path="matchingPassword" cssClass="error"/>
                        </div>
                    </div>

                    <button id="editInfoBtn" type="button" class="btn btn-primary" onclick="editInfo()">Edit
                    </button>
                    <button id="updateInfoBtn" type="submit" class="btn btn-primary" disabled>Save</button>
                </form:form>
                <h4>Addresses</h4>
                <p id="addressMessage" class="confirm" hidden>${addressMessage.confirms.get(0)}</p>
                <c:forEach items="${user.addresses}" var="address">
                    <div id="address${address.id}" class="form-group">
                        <input type="text" class="form-control" id="addressInput${address.id}" name="address"
                               value="${address.address}" placeholder="Address" required disabled>
                        <button type="button" class="btn address-edit" id="editAddressBtn${address.id}"
                                onclick="editAddress('${address.id}')">Edit
                        </button>
                        <button type="button" class="btn address-ok" id="updateAddressBtn${address.id}"
                                onclick="updateAddress('${address.id}')" hidden>Ok
                        </button>
                        <button type="button" class="btn address-delete" onclick="deleteAddress('${address.id}')">
                            Delete
                        </button>
                    </div>
                </c:forEach>

                <div class="form-group">
                    <button id="addAddressBtn" type="button" class="btn btn-primary" onclick="showAddressForm()">
                        Add address
                    </button>
                </div>
                <form id="addAddressForm" action="${contextPath}/profile/addAddress" method="post" hidden>
                    <div class="form-group">
                        <label for="newAddressInput" class="form-label">New address</label>
                        <input type="text" class="form-control" id="newAddressInput" name="address"
                               placeholder="Address" required autofocus>
                    </div>
                    <button type="submit" class="btn btn-primary">Save</button>
                </form>
                <h4>Orders</h4>
                <table class="table">
                    <thead>
                    <tr>
                        <th scope="col">Order ID</th>
                        <th scope="col">Sum</th>
                        <th scope="col">Order status</th>
                        <th scope="col">Order date</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${user.orders}" var="order">
                        <tr>
                            <td>${order.id}</td>
                            <td>${order.sum}</td>
                            <td>${order.orderStatus}</td>
                            <td>${order.date}</td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
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
<script src="${contextPath}/resources/js/profile.js"></script>
</body>
</html>
