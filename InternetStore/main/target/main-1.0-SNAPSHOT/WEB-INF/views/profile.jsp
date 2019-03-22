<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>

<html>
<head>
    <title>Личный кабинет</title>
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
            <div class="col-lg-8 content">
                <h2>Профиль ${user.name} ${user.lastname}</h2>
                <h4>Информация</h4>
                <form action="${contextPath}/profile/editInfo" method="post" modelAttribute="user">
                    <div class="form-group row">
                        <label for="nameInput" class="col-sm-3 col-form-label">Имя</label>
                        <div class="col">
                            <input type="text" class="form-control" id="nameInput" name="name"
                                   value="${user.name}" placeholder="Имя" required readonly>
                        </div>
                    </div>
                    <div class="form-group row">
                        <label for="lastnameInput" class="col-sm-3 col-form-label">Фамилия</label>
                        <div class="col">
                            <input type="text" class="form-control" id="lastnameInput" name="lastname"
                                   value="${user.lastname}" placeholder="Фамилия" required readonly>
                        </div>
                    </div>
                    <div class="form-group row">
                        <label for="phoneInput" class="col-sm-3 col-form-label">Телефон</label>
                        <div class="col">
                            <input type="text" class="form-control" id="phoneInput" name="phoneNumber"
                                   value="${user.phoneNumber}" placeholder="+79223556789" required readonly>
                        </div>
                    </div>
                    <div class="form-group row">
                        <label for="loginRegInput" class="col-sm-3 col-form-label">Логин (e-mail)</label>
                        <div class="col">
                            <input type="email" class="form-control" id="loginRegInput" name="email"
                                   value="${user.email}" placeholder="E-mail" required readonly>
                        </div>
                    </div>
                    <div class="form-group row">
                        <label for="birthInput" class="col-sm-3 col-form-label">День рождения</label>
                        <div class="col">
                            <input type="date" class="form-control" id="birthInput" name="birthDay"
                                   value="${user.birthday}" placeholder="" required readonly>
                        </div>
                    </div>
                    <button type="button" class="btn btn-primary">Изменить</button>
                    <button type="submit" class="btn btn-secondary" disabled>Сохранить</button>
                </form>
                <h4>Адреса</h4>
                <p id="addressMessage" class="confirm" hidden></p>
                <c:forEach items="${user.addresses}" var="address">
                    <div id="address${address.id}" class="form-group">
                        <input type="text" class="form-control" id="addressInput${address.id}" name="address"
                               value="${address.address}" placeholder="адрес" required disabled>
                        <button type="button" class="btn address-edit" id="editAddressBtn${address.id}"
                                onclick="editAddress('${address.id}')">Изменить
                        </button>
                        <button type="button" class="btn address-ok" id="updateAddressBtn${address.id}"
                                onclick="updateAddress('${address.id}')" hidden>Ок
                        </button>
                        <button type="button" class="btn address-delete" onclick="deleteAddress('${address.id}')">
                            Удалить
                        </button>
                    </div>
                </c:forEach>

                <div class="form-group">
                    <button id="addAddressBtn" type="button" class="btn btn-primary" onclick="showAddressForm()">
                        Добавить адрес
                    </button>
                </div>
                <form id="addAddressForm" action="${contextPath}/profile/addAddress" method="post" hidden>
                    <div class="form-group">
                        <label for="newAddressInput" class="form-label">Новый адрес</label>
                        <input type="text" class="form-control" id="newAddressInput" name="address"
                               placeholder="Введите адрес" required autofocus>
                    </div>
                    <button type="submit" class="btn btn-primary">Сохранить</button>
                </form>
                <h4>Заказы</h4>

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
