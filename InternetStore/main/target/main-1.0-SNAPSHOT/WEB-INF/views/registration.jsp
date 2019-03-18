<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Регистрация</title>
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
        <div class="row site-padding">
            <jsp:include page="navigation.jsp"/>
            <div class="col content">
                <div class="row justify-content-center">
                    <div class="col-md-8">
                        <form action="${contextPath}/register" method="post" modelAttribute="user">
                            <div class="form-group">
                                <label for="nameInput">Ваше имя</label>
                                <input type="text" class="form-control" id="nameInput" name="name"
                                       placeholder="Имя" required autofocus>
                            </div>
                            <div class="form-group">
                                <label for="lastnameInput">Фамилия</label>
                                <input type="text" class="form-control" id="lastnameInput" name="lastname"
                                       placeholder="Фамилия">
                            </div>
                            <div class="form-group">
                                <label for="phoneInput">Телефон</label>
                                <input type="text" class="form-control" id="phoneInput" name="phoneNumber"
                                       placeholder="+79223556789" required>
                            </div>
                            <div class="form-group">
                                <label for="loginRegInput">Логин (e-mail)</label>
                                <input type="email" class="form-control" id="loginRegInput" name="email"
                                       placeholder="E-mail" required>
                            </div>
                            <div class="form-group">
                                <label for="birthInput">День рождения</label>
                                <input type="date" class="form-control" id="birthInput" name="birthDay"
                                       placeholder="">
                            </div>
                            <div class="form-group">
                                <label for="passwordRegInput">Пароль</label>
                                <input type="password" class="form-control" id="passwordRegInput" name="password"
                                       placeholder="Пароль" required>
                            </div>
                            <button type="submit" class="btn btn-primary">Зарегистрироваться</button>
                        </form>
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
<script src="${contextPath}/resources/js/registration.js"></script>

</body>
</html>



