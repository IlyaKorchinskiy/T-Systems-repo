<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>

<!DOCTYPE html>
<html>
<head>
    <title>Category list</title>
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
        <div class="row">
            <div class="col content">
                <div id="categoryTree" class="category-tree"></div>
                <div class="add-new-cat">
                    <button id="btnNewCat" type="button" class="btn btn-primary" onclick="addNewCategory()">Add new
                        category
                    </button>
                </div>
            </div>
            <div id="formCol" class="col content">
                <h2 id="header" hidden></h2>
                <c:if test="${not empty message && message.confirms.size() != 0}">
                    <p id="message" class="confirm">${message.confirms.get(0)}</p>
                </c:if>
                <c:if test="${not empty message && message.errors.size() != 0}">
                    <c:forEach items="${message.errors}" var="error">
                        <p id="message" class="error">${error}</p>
                    </c:forEach>
                </c:if>
                <form id="categoryForm" action="${contextPath}/admin/categories/edit" method="post"
                      modelAttribute="category" hidden>
                    <div class="form-group">
                        <label for="categoryIdInput">Category ID</label>
                        <input type="number" class="form-control" id="categoryIdInput" name="id" readonly>
                    </div>
                    <div class="form-group">
                        <label for="titleInput">Title</label>
                        <input type="text" class="form-control" id="titleInput" name="title" placeholder="Title"
                               required disabled>
                    </div>
                    <div class="form-group">
                        <label for="parentInput">Parent category</label>
                        <select id="parentInput" name="parentId" class="form-control" disabled>
                            <option value="0">None</option>
                            <c:forEach items="${allCategories}" var="category">
                                <option value="${category.id}">${category.title}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <button id="btnEditCat" type="button" class="btn btn-primary" onclick="editCategory()">Edit</button>
                    <button id="btnSaveCat" type="submit" class="btn btn-primary" hidden>Save</button>
                    <button id="btnDeleteCat" type="button" class="btn btn-danger" data-toggle="modal"
                            data-target="#deleteCatModal">Delete
                    </button>
                </form>
                <!-- Modal -->
                <div class="modal fade" id="deleteCatModal" tabindex="-1" role="dialog"
                     aria-labelledby="deleteCatModalTitle"
                     aria-hidden="true">
                    <div class="modal-dialog modal-dialog-centered" role="document">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h5 class="modal-title" id="deleteCatModalTitle">Delete category</h5>
                                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                    <span aria-hidden="true">&times;</span>
                                </button>
                            </div>
                            <div class="modal-body">
                                <p>Are you sure?</p>
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-secondary" data-dismiss="modal">Cancel</button>
                                <a id="btnDeleteCatModal" type="button" class="btn btn-primary" href="#" role="button">Delete</a>
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
<script>var categoriesJSON = '${categories}'</script>
<script src="${contextPath}/resources/js/common.js"></script>
<script src="${contextPath}/resources/js/adminCategories.js"></script>

</body>
</html>
