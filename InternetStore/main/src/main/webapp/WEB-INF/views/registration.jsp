<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<html>
<head>
    <title>Регистрация</title>
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
            <div class="col-lg-9 content">
                <h2>Signing up</h2>
                <sec:authorize access="isAuthenticated()">
                    <p>You already signed up...</p>
                </sec:authorize>
                <sec:authorize access="!isAuthenticated()">
                    <c:if test="${message.confirms.size() != 0}">
                        <c:forEach items="${message.confirms}" var="confirm">
                            <p class="confirm">${confirm}</p>
                        </c:forEach>
                    </c:if>
                    <c:if test="${message.errors.size() != 0}">
                        <c:forEach items="${message.errors}" var="error">
                            <p class="error">${error}</p>
                        </c:forEach>
                    </c:if>
                    <c:if test="${empty message || message.confirms.size() == 0}">
                        <form action="${contextPath}/register" method="post" modelAttribute="user">
                            <div class="form-group">
                                <label for="nameInput">Your name</label>
                                <input type="text" class="form-control" id="nameInput" name="name"
                                       value="${user.name}" placeholder="Name" required autofocus>
                            </div>
                            <div class="form-group">
                                <label for="lastnameInput">Last name</label>
                                <input type="text" class="form-control" id="lastnameInput" name="lastname"
                                       value="${user.lastname}" placeholder="Last name" required>
                            </div>
                            <div class="form-group">
                                <label for="phoneInput">Phone number</label>
                                <input type="text" class="form-control" id="phoneInput" name="phoneNumber"
                                       value="${user.phoneNumber}" placeholder="+79223556789" required>
                            </div>
                            <div class="form-group">
                                <label for="loginRegInput">Login (e-mail)</label>
                                <input type="email" class="form-control" id="loginRegInput" name="email"
                                       value="${user.email}" placeholder="E-mail" required>
                            </div>
                            <div class="form-group">
                                <label for="birthInput">Birthday</label>
                                <input type="date" class="form-control" id="birthInput" name="birthDay"
                                       value="${user.birthday}" placeholder="" required>
                            </div>
                            <div class="form-group">
                                <label for="passwordRegInput">Password</label>
                                <input type="password" class="form-control" id="passwordRegInput"
                                       name="password" placeholder="Password" required>
                            </div>
                            <button type="submit" class="btn btn-primary">Sign up</button>
                        </form>
                    </c:if>

                </sec:authorize>


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



