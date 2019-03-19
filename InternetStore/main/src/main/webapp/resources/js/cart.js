'use strict';

window.onload = function () {
    console.log(document.cookie);
    getCart(contextPath);

    var deliveryType = document.getElementById('deliveryType' + deliveryTypeId);
    if (deliveryType !== null) deliveryType.checked = true;
    var paymentType = document.getElementById('paymentType' + paymentTypeId);
    if (paymentType !== null) paymentType.checked = true;
}

function showAddressInput() {
    var addressDiv = document.getElementById('addressDiv');
    addressDiv.classList.toggle('hidden');
    var pickupDiv = document.getElementById('pickupDiv');
    pickupDiv.classList.toggle('hidden');
}
