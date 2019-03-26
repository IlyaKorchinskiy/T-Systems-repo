'use strict';

var xhr = new XMLHttpRequest();

function getCart(contextPath) {
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
        cartContent.innerHTML = '<p>Cart is empty</p>';
    } else {
        var productsHtml = '<p>Your products:</p><ul>';
        for (var i = 0; i < cart.length; i++) {
            productsHtml += '<li>' + cart[i].product.title + ' - ' + cart[i].amount + ' шт</li>';
        }
        productsHtml += '</ul>';
        cartContent.innerHTML = productsHtml;
    }
}

function sendAjaxRequestPost(url, params, func) {
    var body = '';
    for (var key in params) {
        body += key + '=' + encodeURIComponent(params[key]) + '&';
    }
    xhr.open('POST', contextPath + url, true);
    xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
    xhr.onreadystatechange = function () {
        if (xhr.readyState !== 4) return;
        if (xhr.status !== 200) {
            console.log('Error', xhr.status, xhr.statusText);
        } else {
            console.log('Ok', xhr.statusText);
            console.log(xhr.responseText);
            func();
        }
    }
    xhr.send(body);
}

function ajaxGetData(url) {
    return fetch(url)
        .then(function (response) {
            return response.json();
        });
}



