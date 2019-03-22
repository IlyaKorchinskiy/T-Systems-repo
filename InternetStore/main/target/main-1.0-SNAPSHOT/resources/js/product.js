'use strict';

window.onload = function () {
    console.log(document.cookie);
    getCart(contextPath);
}

function addToCart(id) {
    var params = {
        id: id
    };
    sendAjaxRequestPost('/cart/addToCart', params, function () {
        console.log(xhr.responseText);
        if (xhr.responseText === '') return;
        var cart = JSON.parse(xhr.responseText);

        var date = new Date();
        date.setDate(date.getDate() + 30);
        document.cookie = "sessionId=" + cart[0].cart.sessionId + "; path=/; expires=" + date.toUTCString();

        renderCart(cart);
    });
}


