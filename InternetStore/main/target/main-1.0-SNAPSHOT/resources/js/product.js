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



