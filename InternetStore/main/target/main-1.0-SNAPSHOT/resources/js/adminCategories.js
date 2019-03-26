'use strict';

window.onload = function () {
    console.log(document.cookie);
    getCart(contextPath);

    renderCategoryTree(categoriesJSON);

}

function renderCategoryTree(categoriesJSON) {
    var categories = JSON.parse(categoriesJSON);
    var categoriesUl = '<ul>' + renderCategories(categories) + '</ul>';
    document.getElementById('categoryTree').innerHTML = categoriesUl;
}

function renderCategories(categories) {
    var categoryItems = '';
    for (var i = 0; i < categories.length; i++) {
        categoryItems += '<li><button id="btnCat' + categories[i].id + '" class="btn" onclick="showCategory('
            + categories[i].id + ')">' + categories[i].title + '</button>';
        if (categories[i].subcategories !== undefined) {
            var subcategories = categories[i].subcategories;
            categoryItems += '<ul>' + renderCategories(subcategories) + '</ul>';
        }
        categoryItems += '</li>';
    }
    return categoryItems;
}

function showCategory(categoryId) {
    var active = document.getElementById('categoryTree').getElementsByClassName('active');
    if (active.length !== 0) active[0].classList.remove('active');
    document.getElementById('btnCat' + categoryId).classList.add('active');
    var url = contextPath + '/admin/categories/' + categoryId;
    ajaxGetData(url)
        .then(function (category) {
            document.getElementById('categoryForm').hidden = false;
            document.getElementById('categoryIdInput').value = category.id;
            document.getElementById('titleInput').value = category.title;
            var parentOptions = document.getElementById('parentInput').getElementsByTagName('option');
            for (var i = 0; i < parentOptions.length; i++) {
                if (parentOptions[i].value == category.parentId) parentOptions[i].selected = true;
            }
        })
        .catch(function (reason) {
            console.log(reason);
        })
}

function editCategory() {
    document.getElementById('btnEditCat').hidden = true;
    document.getElementById('btnSaveCat').hidden = false;
    var titleInput = document.getElementById('titleInput');
    titleInput.disabled = false;
    titleInput.focus();
    document.getElementById('parentInput').disabled = false;
}


