'use strict';

window.onload = function () {
    console.log(document.cookie);
    getCart(contextPath);

    if (productId === '') {
        activateForm();
    }
}

var productForm = document.getElementById('productForm');
var btnSave = document.getElementById('btnSave');
var btnEdit = document.getElementById('btnEdit');

function activateForm() {
    var inputs = productForm.getElementsByTagName('input');
    for (var i = 0; i < inputs.length; i++) {
        if (inputs[i].id !== 'idInput') inputs[i].disabled = false;
    }
    var textAreas = productForm.getElementsByTagName('textarea');
    for (var i = 0; i < textAreas.length; i++) {
        textAreas[i].disabled = false;
    }
    var selects = productForm.getElementsByTagName('select');
    for (var i = 0; i < selects.length; i++) {
        selects[i].disabled = false;
    }
    btnSave.hidden = false;
    btnEdit.hidden = true;
}
