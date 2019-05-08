<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
    <title>Add product</title>
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
        <c:if test="${empty product.id}">
            <h2>Add new product</h2>
        </c:if>
        <c:if test="${not empty product.id}">
            <h2>Product ${product.id}</h2>
        </c:if>
        <div class="row">
            <div class="col content">
                <form:form id="productForm" class="product-form" action="${contextPath}/admin/products/add"
                           method="post" modelAttribute="product" enctype="multipart/form-data">
                    <div class="row">
                        <div class="col">
                            <div class="form-group">
                                <label for="idInput">Product ID</label>
                                <form:input type="number" class="form-control" id="idInput" path="id" readonly="true"/>
                            </div>
                            <div class="form-group">
                                <label for="inputTitle">Product title</label>
                                <form:input type="text" class="form-control" id="inputTitle" path="title"
                                            placeholder="Title"/>
                                <form:errors path="title" cssClass="error"/>
                            </div>
                            <div class="form-group">
                                <label for="inputAuthor">Author</label>
                                <form:input type="text" class="form-control" id="inputAuthor" path="author"
                                            placeholder="Author"/>
                                <form:errors path="author" cssClass="error"/>
                            </div>
                            <div class="form-group">
                                <label for="inputYear">Published</label>
                                <form:input type="text" class="form-control" id="inputYear" path="year"
                                            placeholder="Year"/>
                                <form:errors path="year" cssClass="error"/>
                            </div>
                            <div class="form-group">
                                <label for="inputCost">Cost</label>
                                <form:input type="number" class="form-control" id="inputCost" path="cost"/>
                                <form:errors path="cost" cssClass="error"/>
                            </div>
                            <div class="form-group">
                                <label for="inputAmount">Stock amount</label>
                                <form:input type="number" class="form-control" id="inputAmount" path="amount"/>
                                <form:errors path="amount" cssClass="error"/>
                            </div>
                            <div class="form-group">
                                <label for="inputDescription">Description</label>
                                <form:textarea class="form-control" id="inputDescription" path="description"
                                               placeholder="Description" rows="13"/>
                                <form:errors path="description" cssClass="error"/>
                            </div>
                        </div>
                        <div class="col">
                            <div class="form-group">
                                <label for="inputCategories">Categories</label>
                                <form:select id="inputCategories" path="categories" items="${allCategories}"
                                             multiple="true" class="form-control" itemLabel="title" itemValue="id"
                                             size="20"/>
                                <form:errors path="categories" cssClass="error"/>
                            </div>
                            <div class="row align-items-end">
                                <div class="col">
                                    <div class="form-group photo-input">
                                        <label for="inputPhotoMd">Medium photo</label>
                                        <form:input type="file" class="form-control-file" id="inputPhotoMd"
                                                    path="photoMd"/>
                                        <form:errors path="photoMd" cssClass="error"/>
                                    </div>
                                </div>
                                <div class="col">

                                    <div class="form-group photo-input">
                                        <label for="inputPhotoSm">Small photo</label>
                                        <form:input type="file" class="form-control-file" id="inputPhotoSm"
                                                    path="photoSm"/>
                                        <form:errors path="photoSm" cssClass="error"/>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col btns">
                            <button id="btnSave" type="submit" class="btn btn-primary">Save</button>
                        </div>
                    </div>
                </form:form>
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
<script src="${contextPath}/resources/js/adminProductForm.js"></script>
</body>
</html>



