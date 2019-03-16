'use strict';

window.onload = function () {
    console.log(document.cookie);
    getCart(contextPath);
}

function addToCart(id) {
    var xhr = new XMLHttpRequest();
    var body = 'id=' + encodeURIComponent(id);
    xhr.open('POST', contextPath + '/cart/addToCart', true);
    xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
    xhr.onreadystatechange = function () {
        if (xhr.readyState !== 4) return;
        if (xhr.status !== 200) {
            console.log('Error', xhr.status, xhr.statusText);
        } else {
            console.log('Ok', xhr.statusText);

            console.log(xhr.responseText);
            if (xhr.responseText === '') return;
            var cart = JSON.parse(xhr.responseText);
            console.log(cart);

            var date = new Date();
            date.setDate(date.getDate() + 30);
            document.cookie = "sessionId=" + cart[0].cart.sessionId + "; path=/; expires=" + date.toUTCString();

            renderCart(cart);

        }
    }
    xhr.send(body);
}


