<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

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
        <h2>Product ${product.id}</h2>
        <div class="row">
            <div class="col content">
                <c:if test="${not empty message}">
                    <p class="confirm">${message.confirms.get(0)}</p>
                </c:if>
                <div class="row">
                    <div class="col">
                        <div class="form-group">
                            <label for="idInput">Product ID</label>
                            <input type="number" class="form-control" id="idInput" name="id" value="${product.id}"
                                   readonly>
                        </div>
                        <div class="form-group">
                            <label for="titleInput">Product title</label>
                            <div class="input-group">
                                <input type="text" class="form-control" id="titleInput" name="title"
                                       value="${product.title}" placeholder="Title" disabled>
                                <div class="input-group-append">
                                    <button id="titleBtnSave" type="button" class="btn btn-primary"
                                            onclick="saveField('title')" hidden>
                                        <i class="fas fa-check"></i>
                                    </button>
                                    <button id="titleBtnEdit" type="button" class="btn btn-secondary"
                                            onclick="editField('title')">
                                        <i class="far fa-edit"></i>
                                    </button>
                                </div>
                            </div>
                            <p id="messageTitle"></p>
                        </div>
                        <div class="form-group">
                            <label for="authorInput">Author</label>
                            <div class="input-group">
                                <input type="text" class="form-control" id="authorInput" name="author"
                                       value="${product.author}" placeholder="Author" disabled>
                                <div class="input-group-append">
                                    <button id="authorBtnSave" type="button" class="btn btn-primary"
                                            onclick="saveField('author')" hidden>
                                        <i class="fas fa-check"></i>
                                    </button>
                                    <button id="authorBtnEdit" type="button" class="btn btn-secondary"
                                            onclick="editField('author')">
                                        <i class="far fa-edit"></i>
                                    </button>
                                </div>
                            </div>
                            <p id="messageAuthor"></p>
                        </div>
                        <div class="form-group">
                            <label for="yearInput">Published</label>
                            <div class="input-group">
                                <input type="text" class="form-control" id="yearInput" name="year"
                                       value="${product.year}" placeholder="Year" disabled>
                                <div class="input-group-append">
                                    <button id="yearBtnSave" type="button" class="btn btn-primary"
                                            onclick="saveField('year')" hidden>
                                        <i class="fas fa-check"></i>
                                    </button>
                                    <button id="yearBtnEdit" type="button" class="btn btn-secondary"
                                            onclick="editField('year')">
                                        <i class="far fa-edit"></i>
                                    </button>
                                </div>
                            </div>
                            <p id="messageYear"></p>
                        </div>
                        <div class="form-group">
                            <label for="costInput">Cost</label>
                            <input type="number" class="form-control" id="costInput" name="cost"
                                   value="${product.cost}" disabled>
                        </div>
                        <div class="form-group">
                            <label for="amountInput">Stock amount</label>
                            <input type="number" class="form-control" id="amountInput" name="amount"
                                   value="${product.amount}" disabled>
                        </div>
                        <div class="form-group">
                            <label for="descriptionInput">Description</label>
                            <textarea class="form-control" id="descriptionInput" name="description"
                                      placeholder="Description" rows="18" disabled>${product.description}</textarea>
                            <button id="descriptionBtnSave" type="button" class="btn btn-primary edit"
                                    onclick="saveField('description')" hidden>Save
                            </button>
                            <button id="descriptionBtnEdit" type="button" class="btn btn-secondary edit"
                                    onclick="editField('description')">Edit
                            </button>
                            <p id="messageDescription"></p>
                        </div>
                    </div>
                    <div class="col">
                        <form:form class="product-form" action="#" modelAttribute="product">
                            <div class="form-group">
                                <label for="categoriesInput">Categories</label>
                                <form:select id="categoriesInput" path="categories" items="${allCategories}"
                                             multiple="true" class="form-control" itemLabel="title" itemValue="id"
                                             size="14"/>
                                <button id="categoriesBtnSave" type="button" class="btn btn-primary edit"
                                        onclick="saveField('categories')" hidden>Save
                                </button>
                                <button id="categoriesBtnEdit" type="button" class="btn btn-secondary edit"
                                        onclick="editField('categories')">Edit
                                </button>
                                <p id="messageCategories"></p>
                            </div>
                        </form:form>

                        <div class="row align-items-end">
                            <div class="col">
                                <label for="photoMdInput">Medium photo</label>
                                <div class="photo">
                                    <img src="http://192.168.99.100:8190/${product.photoMd}" alt="product_photo">
                                </div>
                                <form id="photoMdForm" class="photo-form"
                                      action="${contextPath}/admin/products/${product.id}/updatePhotoMd"
                                      method="post" enctype="multipart/form-data">
                                    <div class="form-group photo-input">
                                        <input type="file" class="form-control-file" id="photoMdInput"
                                               name="photoMd" disabled>
                                        <button id="photoMdBtnSave" type="submit" class="btn btn-primary edit"
                                                hidden>Save
                                        </button>
                                        <button id="photoMdBtnEdit" type="button" class="btn btn-secondary edit"
                                                onclick="editField('photoMd')">Edit
                                        </button>
                                    </div>

                                </form>
                            </div>
                            <div class="col">
                                <label for="photoSmInput">Small photo</label>
                                <div class="photo">
                                    <img src="http://192.168.99.100:8190/${product.photoSm}" alt="product_photo">
                                </div>
                                <form id="photoSmForm" class="photo-form"
                                      action="${contextPath}/admin/products/${product.id}/updatePhotoSm"
                                      method="post" enctype="multipart/form-data">
                                    <div class="form-group photo-input">
                                        <input type="file" class="form-control-file" id="photoSmInput"
                                               name="photoSm" disabled>
                                        <button id="photoSmBtnSave" type="submit" class="btn btn-primary edit"
                                                hidden>Save
                                        </button>
                                        <button id="photoSmBtnEdit" type="button" class="btn btn-secondary edit"
                                                onclick="editField('photoSm')">Edit
                                        </button>
                                    </div>

                                </form>
                            </div>
                        </div>
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
<script>var productId = '${product.id}'</script>
<script src="${contextPath}/resources/js/common.js"></script>
<script src="${contextPath}/resources/js/adminProduct.js"></script>
<script src="${contextPath}/resources/js/admin.js"></script>
</body>
</html>



