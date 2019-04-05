'use strict';

window.onload = function () {
    console.log(document.cookie);
    getCart(contextPath);

    var deliveryTypeInput = document.getElementById('deliveryType-' + deliveryType);
    if (deliveryTypeInput !== null) deliveryTypeInput.checked = true;
    var paymentTypeInput = document.getElementById('paymentType-' + paymentType);
    if (paymentTypeInput !== null) paymentTypeInput.checked = true;
}

function showAddressInput() {
    var addressDiv = document.getElementById('addressDiv');
    addressDiv.classList.toggle('hidden');
    var pickupDiv = document.getElementById('pickupDiv');
    pickupDiv.classList.toggle('hidden');
}
