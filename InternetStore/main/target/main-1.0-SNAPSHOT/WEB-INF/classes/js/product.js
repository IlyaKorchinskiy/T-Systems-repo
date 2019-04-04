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

            var date = new Date();
            date.setDate(date.getDate() + 30);
            var cookie = getCookie('sessionId');
            if (cookie === undefined) {
                document.cookie = "sessionId=" + sessionId + "; path=/; expires=" + date.toUTCString();
            } else {
                document.cookie = "sessionId=" + cookie + "; path=/; expires=" + date.toUTCString();
            }
            getCart(contextPath);
        }


    });
}

function getCookie(name) {
    var matches = document.cookie.match(new RegExp(
        "(?:^|; )" + name.replace(/([\.$?*|{}\(\)\[\]\\\/\+^])/g, '\\$1') + "=([^;]*)"
    ));
    return matches ? decodeURIComponent(matches[1]) : undefined;
}


