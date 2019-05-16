<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>

<!DOCTYPE html>
<html>
<head>
    <title>Stats</title>
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
        <h2>Statistics</h2>
        <div class="row">
            <div class="col content stats">
                <h4>Product TOP-10 sale</h4>
                <div class="form-group row">
                    <label for="productsMonthInput" class="col-sm-3 col-form-label">Month</label>
                    <div class="col-sm-3">
                        <select id="productsMonthInput" class="form-control" onchange="changePeriod('products')">
                            <c:forEach items="${months}" var="month">
                                <option value="${month.id}">${month}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <label for="productsYearInput" class="col-sm-3 col-form-label">Year</label>
                    <div class="col-sm-3">
                        <select id="productsYearInput" class="form-control" onchange="changePeriod('products')">
                            <c:forEach items="${years}" var="year">
                                <option value="${year}">${year}</option>
                            </c:forEach>
                        </select>
                    </div>
                </div>
                <table id="productsTable" class="table">
                    <thead>
                    <tr>
                        <th scope="col">ID</th>
                        <th scope="col">Title</th>
                        <th scope="col">Amount</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${productStatsList}" var="productStats">
                        <tr>
                            <td>${productStats.product.id}</td>
                            <td class="title">${productStats.product.title}</td>
                            <td>${productStats.amount}</td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
            <div class="col content stats">
                <h4>User TOP-10 sale</h4>
                <div class="form-group row">
                    <label for="usersMonthInput" class="col-sm-3 col-form-label">Month</label>
                    <div class="col-sm-3">
                        <select id="usersMonthInput" class="form-control" onchange="changePeriod('users')">
                            <c:forEach items="${months}" var="month">
                                <option value="${month.id}">${month}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <label for="usersYearInput" class="col-sm-3 col-form-label">Year</label>
                    <div class="col-sm-3">
                        <select id="usersYearInput" class="form-control" onchange="changePeriod('users')">
                            <c:forEach items="${years}" var="year">
                                <option value="${year}">${year}</option>
                            </c:forEach>
                        </select>
                    </div>
                </div>
                <table id="usersTable" class="table">
                    <thead>
                    <tr>
                        <th scope="col">ID</th>
                        <th scope="col">User name</th>
                        <th scope="col">Sum</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${userStatsList}" var="userStats">
                        <tr>
                            <td>${userStats.user.id}</td>
                            <td class="title">${userStats.user.name} ${userStats.user.lastname}</td>
                            <td>${userStats.sum}</td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="col content stats">
            <h4>Total</h4>
            <div class="form-group row">
                <label for="productsMonthInput" class="col-sm-3 col-form-label">Month</label>
                <div class="col-sm-3">
                    <select id="totalMonthInput" class="form-control" onchange="changePeriod('total')">
                        <c:forEach items="${months}" var="month">
                            <option value="${month.id}">${month}</option>
                        </c:forEach>
                    </select>
                </div>
                <label for="productsYearInput" class="col-sm-3 col-form-label">Year</label>
                <div class="col-sm-3">
                    <select id="totalYearInput" class="form-control" onchange="changePeriod('total')">
                        <c:forEach items="${years}" var="year">
                            <option value="${year}">${year}</option>
                        </c:forEach>
                    </select>
                </div>
            </div>
            <table id="totalTable" class="table">
                <thead>
                <tr>
                    <th scope="col">Indicator</th>
                    <th scope="col">Value</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${indicators}" var="indicator">
                    <tr>
                        <td class="title">${indicator.name}</td>
                        <td>${indicator.value}</td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
        <div class="col content stats">

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
<script src="${contextPath}/resources/js/adminStats.js"></script>
</body>
</html>
