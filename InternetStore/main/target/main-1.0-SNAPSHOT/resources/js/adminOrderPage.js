'use strict';

window.onload = function () {
    console.log(document.cookie);
    getCart(contextPath);
}

function editField(field) {
    var input = document.getElementById('input' + field);
    input.disabled = false;
    input.focus();
    document.getElementById('btnEdit' + field).hidden = true;
    document.getElementById('btnSave' + field).hidden = false;
}

var messageP = document.getElementById('message');

function saveField(field) {
    var input = document.getElementById('input' + field);
    sendAjaxRequest(window.location.href + '/update' + field, 'POST', {
        value: input.value
    })
        .then(function (message) {
            disableField(field);
            console.log(message);
            if (message.errors.length === 0) messageP.innerText = message.confirms[0];
        })
        .catch(function (reason) {
            console.log(reason);
        })

}

function disableField(field) {
    var input = document.getElementById('input' + field);
    input.disabled = true;
    document.getElementById('btnEdit' + field).hidden = false;
    document.getElementById('btnSave' + field).hidden = true;
}
