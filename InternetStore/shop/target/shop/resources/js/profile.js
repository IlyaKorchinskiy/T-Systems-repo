'use strict';

window.onload = function () {
    console.log(document.cookie);
    getCart(contextPath);
}

function editInfo() {
    var infoInputs = document.getElementById('userInfo').getElementsByTagName('input');
    for (var i = 0; i < infoInputs.length; i++) {
        infoInputs[i].disabled = false;
    }
    document.getElementById('nameInput').focus();
    document.getElementById('editInfoBtn').disabled = true;
    document.getElementById('updateInfoBtn').disabled = false;
}

function showAddressForm() {
    document.getElementById('addAddressForm').hidden = false;
    document.getElementById('addAddressBtn').hidden = true;
}

function deleteAddress(addressId) {
    var params = {
        addressId: addressId
    };
    sendAjaxRequestPost('/profile/deleteAddress', params, function () {
        document.getElementById('address' + addressId).remove();
        var message = JSON.parse(xhr.responseText);
        var pMessage = document.getElementById('addressMessage');
        pMessage.hidden = false;
        pMessage.innerText = message.confirms[0];
    });
}

function editAddress(addressId) {
    var addressInput = document.getElementById('addressInput' + addressId);
    addressInput.disabled = false;
    addressInput.focus();
    document.getElementById('editAddressBtn' + addressId).hidden = true;
    document.getElementById('updateAddressBtn' + addressId).hidden = false;
}

function updateAddress(addressId) {
    var address = document.getElementById('addressInput' + addressId);
    var params = {
        addressId: addressId,
        address: address.value
    };
    sendAjaxRequestPost('/profile/editAddress', params, function () {
        var message = JSON.parse(xhr.responseText);
        if (message.confirms.length !== 0) {
            document.getElementById('addressInput' + addressId).disabled = true;
            document.getElementById('editAddressBtn' + addressId).hidden = false;
            document.getElementById('updateAddressBtn' + addressId).hidden = true;
            var pMessage = document.getElementById('addressMessage');
            pMessage.hidden = false;
            pMessage.innerText = message.confirms[0];
        }
    });
}
