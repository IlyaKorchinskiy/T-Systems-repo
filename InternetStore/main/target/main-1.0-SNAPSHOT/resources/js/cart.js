'use strict';

window.onload = function () {
    console.log(document.cookie);
    getCart(contextPath);

    document.getElementById('deliveryType' + deliveryTypeId).checked = true;
    document.getElementById('paymentType' + paymentTypeId).checked = true;
}

function showAddressInput() {
    var addressDiv = document.getElementById('addressDiv');
    addressDiv.classList.toggle('hidden');
    var pickupDiv = document.getElementById('pickupDiv');
    pickupDiv.classList.toggle('hidden');
}
