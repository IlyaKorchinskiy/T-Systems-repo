'use strict';

var xhr = new XMLHttpRequest();

var cartContent = document.getElementById('cart-content');

function getCart(contextPath) {
    xhr.open('GET', contextPath + '/cart/getCart', true);
    xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
    xhr.onreadystatechange = function () {
        if (xhr.readyState !== 4) return;
        if (xhr.status !== 200) {
            console.log('Error', xhr.status, xhr.statusText);
        } else {
            console.log('Ok', xhr.statusText);
            if (xhr.responseText === '') {
                cartContent.innerHTML = '<p>Cart is empty</p>';
                return;
            }
            var cart = JSON.parse(xhr.responseText);
            renderCart(cart);
        }
    }
    xhr.send();
}

function renderCart(cart) {
    var badge = document.getElementById('cart-badge');
    if (cart.cartProducts.length !== 0) {
        var amount = 0;
        for (var i = 0; i < cart.cartProducts.length; i++) {
            amount += cart.cartProducts[i].amount;
        }
        badge.innerText = amount;
        var productsHtml = '<p>Your products:</p><ul>';
        for (var i = 0; i < cart.cartProducts.length; i++) {
            productsHtml += '<li>' + cart.cartProducts[i].product.title + ' - ' + cart.cartProducts[i].amount + ' items</li>';
        }
        productsHtml += '</ul>';
        cartContent.innerHTML = productsHtml;
    } else {
        cartContent.innerHTML = '<p>Cart is empty</p>';
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

function sendAjaxRequest(url, method, contentType, data) {
    return fetch(url, {
        method: method,
        headers: {
            'Content-Type': contentType
        },
        body: JSON.stringify(data)
    })
        .then(function (response) {
            console.log(response);
            return response.json();
        });
}

function addToCart(id) {
    var params = {
        id: id
    };
    sendAjaxRequestPost('/cart/addToCart', params, function () {
        if (xhr.responseText === '') return;
        var message = JSON.parse(xhr.responseText);
        var messageP = document.getElementById('addProductMessage');
        if (message.errors.length !== 0) {
            for (var i = 0; i < message.errors.length; i++) {
                messageP.innerText += messageP.innerText + message.errors[i] + '\n';
            }
        } else {
            messageP.innerText = message.confirms[0];
            getCart(contextPath);
        }
    });
}



