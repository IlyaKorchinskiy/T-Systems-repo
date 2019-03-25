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
        categoryItems += '<li>' + categories[i].title;
        if (categories[i].subcategories !== undefined) {
            var subcategories = categories[i].subcategories;
            categoryItems += '<ul>' + renderCategories(subcategories) + '</ul>';
        }
        categoryItems += '</li>';
    }
    return categoryItems;
}


