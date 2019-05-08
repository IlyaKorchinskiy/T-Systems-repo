'use strict';

function editField(field) {
    var input = document.getElementById(field + 'Input');
    input.disabled = false;
    input.focus();
    document.getElementById(field + 'BtnEdit').hidden = true;
    document.getElementById(field + 'BtnSave').hidden = false;
}

function saveField(field) {
    var input = document.getElementById(field + 'Input');
    var contentType = 'application/json';
    var data = {};
    switch (field) {
        case 'categories':
            var categories = [];
            for (let i = 0; i < input.selectedOptions.length; i++) {
                categories.push(input.selectedOptions[i].value);
            }
            data = {categories: categories};
            break;
        case 'orderStatus':
            data = input.value;
            break;
        default:
            data[field] = input.value;

    }
    var urlField = field[0].toUpperCase() + field.substring(1, field.length);
    sendAjaxRequest(window.location.href + '/update' + urlField, 'POST', contentType, data)
        .then(function (message) {
            console.log(message);
            var messageP = document.getElementById(field + 'Message');
            if (message.errors.length === 0) {
                messageP.classList.add('confirm');
                messageP.innerText = message.confirms[0];
                disableField(field);
            } else {
                messageP.classList.add('error');
                messageP.innerText = message.errors[0];
            }
        })
        .catch(function (reason) {
            console.log(reason);
        })

}

function disableField(field) {
    var input = document.getElementById(field + 'Input');
    input.disabled = true;
    document.getElementById(field + 'BtnEdit').hidden = false;
    document.getElementById(field + 'BtnSave').hidden = true;
}
