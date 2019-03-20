<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>

<html>
<head>
    <title>Логин</title>
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
            <div class="col-md-8 content">
                <c:if test="${param.get('error') == true}">
                    <p class="error">
                        Неверный логин или пароль
                    </p>
                </c:if>

                <c:url value="/j_spring_security_check" var="checkUrl"/>
                <form action="${checkUrl}" method="post">
                    <div class="form-group">
                        <label for="loginInput">Логин (e-mail)</label>
                        <input type="email" class="form-control" id="loginInput" name="j_username"
                               placeholder="E-mail" required autofocus>
                    </div>
                    <div class="form-group">
                        <label for="passwordInput">Пароль</label>
                        <input type="password" class="form-control" id="passwordInput" name="j_password"
                               placeholder="Пароль" required>
                    </div>
                    <a type="button" class="btn btn-secondary" href="${contextPath}/register">Регистрация</a>
                    <button type="submit" class="btn btn-primary">Войти</button>
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
<script src="${contextPath}/resources/js/common.js"></script>
<script src="${contextPath}/resources/js/login.js"></script>

</body>
</html>
