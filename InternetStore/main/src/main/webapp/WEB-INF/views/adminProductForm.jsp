<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<html>
<head>
    <title>Product ${product.title}</title>
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
        <div class="row">
            <div class="col content">
                <form id="productForm" class="product-form" action="${contextPath}/admin/products/addProduct"
                      method="post" modelAttribute="product" enctype="multipart/form-data">
                    <div class="row">
                        <div class="col">
                            <div class="form-group">
                                <label for="idInput">Product ID</label>
                                <input type="number" class="form-control" id="idInput" name="id" readonly>
                            </div>
                            <div class="form-group">
                                <label for="titleInput">Product title</label>
                                <input type="text" class="form-control" id="titleInput" name="title" placeholder="Title"
                                       required disabled>
                            </div>
                            <div class="form-group">
                                <label for="authorInput">Author</label>
                                <input type="text" class="form-control" id="authorInput" name="author"
                                       placeholder="Author" required disabled>
                            </div>
                            <div class="form-group">
                                <label for="costInput">Cost</label>
                                <input type="number" class="form-control" id="costInput" name="cost" placeholder=""
                                       required disabled>
                            </div>
                            <div class="form-group">
                                <label for="amountInput">Stock amount</label>
                                <input type="number" class="form-control" id="amountInput" name="amount" placeholder=""
                                       required disabled>
                            </div>
                            <div class="form-group">
                                <label for="descriptionInput">Description</label>
                                <textarea class="form-control" id="descriptionInput" name="description"
                                          placeholder="Description" rows="4" required disabled></textarea>
                            </div>
                        </div>
                        <div class="col">
                            <div class="form-group">
                                <label for="categoryInput">Category</label>
                                <select id="categoryInput" name="categoryId" class="form-control" required disabled>
                                    <option value="0">Choose...</option>
                                    <c:forEach items="${allCategories}" var="category">
                                        <option value="${category.id}">${category.title}</option>
                                    </c:forEach>
                                </select>
                            </div>
                            <div class="form-group">
                                <label for="smPhotoInput">Upload small photo</label>
                                <input type="file" class="form-control-file" id="smPhotoInput" name="smPhotoFile"
                                       required disabled>
                            </div>
                            <div class="form-group">
                                <label for="mdPhotoInput">Upload medium photo</label>
                                <input type="file" class="form-control-file" id="mdPhotoInput" name="mdPhotoFile"
                                       required disabled>
                            </div>

                        </div>
                    </div>
                    <div class="row">
                        <div class="col btns">
                            <button id="btnSave" type="submit" class="btn btn-primary" hidden>Save</button>
                            <button id="btnEdit" type="button" class="btn btn-primary">Edit</button>
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
<script src="${contextPath}/resources/js/adminProductForm.js"></script>
</body>
</html>



