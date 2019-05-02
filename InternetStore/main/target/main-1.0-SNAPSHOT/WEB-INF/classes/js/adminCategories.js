'use strict';

window.onload = function () {
    console.log(document.cookie);
    getCart(contextPath);

    renderCategoryTree(categoriesJSON);

}

var form = document.getElementById('categoryForm');
var categoryTree = document.getElementById('categoryTree');

function renderCategoryTree(categoriesJSON) {
    var categories = JSON.parse(categoriesJSON);
    categoryTree.innerHTML = '<ul>' + renderCategories(categories) + '</ul>';
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

var categoryIdInput = document.getElementById('categoryIdInput');
var titleInput = document.getElementById('titleInput');
var parentInput = document.getElementById('parentInput');
var parentOptions = parentInput.getElementsByTagName('option');
var btnEditCat = document.getElementById('btnEditCat');
var btnSaveCat = document.getElementById('btnSaveCat');
var btnDeleteCatModal = document.getElementById('btnDeleteCatModal');

function showCategory(categoryId) {
    var active = categoryTree.getElementsByClassName('active');
    if (active.length !== 0) active[0].classList.remove('active');
    document.getElementById('btnCat' + categoryId).classList.add('active');
    var message = document.getElementById('message');
    if (message) message.remove();
    var url = contextPath + '/admin/categories/' + categoryId;
    sendAjaxRequest(url, 'GET')
        .then(function (category) {
            form.hidden = false;
            form.setAttribute('action', contextPath + '/admin/categories/edit');
            categoryIdInput.value = category.id;
            titleInput.value = category.title;
            disableFormFields();
            for (var i = 0; i < parentOptions.length; i++) {
                if (parentOptions[i].value == category.parentId) parentOptions[i].selected = true;
            }
            btnDeleteCatModal.setAttribute('href', contextPath +
                '/admin/categories/delete/' + category.id);
            header2.innerText = 'Current category';
            header2.hidden = false;
        })
        .catch(function (reason) {
            console.log(reason);
        })
}

function disableFormFields() {
    titleInput.disabled = true;
    parentInput.disabled = true;
    btnEditCat.hidden = false;
    btnSaveCat.hidden = true;
    btnDeleteCatModal.hidden = false;
}

function editCategory() {
    btnEditCat.hidden = true;
    btnSaveCat.hidden = false;
    titleInput.disabled = false;
    parentInput.disabled = false;
    titleInput.focus();
}

var header2 = document.getElementById('header');

function addNewCategory() {
    var active = categoryTree.getElementsByClassName('active');
    if (active.length !== 0) active[0].classList.remove('active');
    var message = document.getElementById('message');
    if (message) message.remove();
    form.hidden = false;
    form.setAttribute('action', contextPath + '/admin/categories/add');
    editCategory();
    btnDeleteCatModal.hidden = true;
    categoryIdInput.value = '';
    titleInput.value = '';
    for (var i = 0; i < parentOptions.length; i++) {
        if (parentOptions[i].value == 0) parentOptions[i].selected = true;
    }
    header2.innerText = 'Add new category';
    header2.hidden = false;
}


