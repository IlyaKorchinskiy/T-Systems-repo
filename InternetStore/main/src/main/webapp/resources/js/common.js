'use strict';

function getCart(contextPath) {
    var xhr = new XMLHttpRequest();
    xhr.open('GET', contextPath + '/cart/getCart', true);
    xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
    xhr.onreadystatechange = function () {
        if (xhr.readyState !== 4) return;
        if (xhr.status !== 200) {
            console.log('Error', xhr.status, xhr.statusText);
        } else {
            console.log('Ok', xhr.statusText);

            if (xhr.responseText === '') return;
            var cart = JSON.parse(xhr.responseText);

            renderCart(cart);
        }
    }
    xhr.send();
}

function renderCart(cart) {
    var badge = document.getElementById('cart-badge');
    if (cart.length !== 0) {
        var amount = 0;
        for (var i = 0; i < cart.length; i++) {
            amount += cart[i].amount;
        }
        badge.innerText = amount;
    }

    var cartContent = document.getElementById('cart-content');
    if (cart.length === 0) {
        cartContent.innerHTML = '<p>Товаров нет</p>';
    } else {
        var productsHtml = '<p>Ваши товары:</p><ul>';
        for (var i = 0; i < cart.length; i++) {
            productsHtml += '<li>' + cart[i].product.title + ' - ' + cart[i].amount + ' шт</li>';
        }
        productsHtml += '</ul>';
        cartContent.innerHTML = productsHtml;
    }
}


