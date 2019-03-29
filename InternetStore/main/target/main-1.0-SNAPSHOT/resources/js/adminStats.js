'use strict';

window.onload = function () {
    console.log(document.cookie);
    getCart(contextPath);

    var monthOptions = document.getElementById('monthInput').getElementsByTagName('option');
    for (var i = 0; i < monthOptions.length; i++) {
        if (monthOptions[i].value == new Date().getMonth()) monthOptions[i].selected = true;
    }

    var yearOptions = document.getElementById('yearInput').getElementsByTagName('option');
    for (var i = 0; i < yearOptions.length; i++) {
        if (yearOptions[i].value == new Date().getFullYear()) yearOptions[i].selected = true;
    }
}

function changeMonth() {
    var monthId = document.getElementById('monthInput').value;
    var year = document.getElementById('yearInput').value;
    var url = window.location.href + '/products?month=' + monthId + '&year=' + year;
    sendAjaxRequest(url, 'GET')
        .then(function (productStatsList) {
            console.log(productStatsList);
            var table = document.getElementById('productsTopTable');
            var tableRows = table.getElementsByTagName('tbody')[0].getElementsByTagName('tr');
            for (var i = 0; i < productStatsList.length; i++) {
                tableRows[i].innerHTML = '<td>' + productStatsList[i].product.id + '</td>' +
                                         '<td class="title">' + productStatsList[i].product.title + '</td>' +
                                         '<td>' + productStatsList[i].amount + '</td>';
            }
        })
        .catch(function (reason) {
            console.log(reason);
        })
}
