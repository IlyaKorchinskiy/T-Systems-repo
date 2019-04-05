'use strict';

window.onload = function () {
    console.log(document.cookie);
    getCart(contextPath);

    selectCurrentDates();
}

function selectCurrentDates() {
    var productsMonthOptions = document.getElementById('productsMonthInput').getElementsByTagName('option');
    for (var i = 0; i < productsMonthOptions.length; i++) {
        if (productsMonthOptions[i].value == new Date().getMonth()) productsMonthOptions[i].selected = true;
    }
    var usersMonthOptions = document.getElementById('usersMonthInput').getElementsByTagName('option');
    for (var i = 0; i < usersMonthOptions.length; i++) {
        if (usersMonthOptions[i].value == new Date().getMonth()) usersMonthOptions[i].selected = true;
    }
    var productsYearOptions = document.getElementById('productsYearInput').getElementsByTagName('option');
    for (var i = 0; i < productsYearOptions.length; i++) {
        if (productsYearOptions[i].value == new Date().getFullYear()) productsYearOptions[i].selected = true;
    }
    var usersYearOptions = document.getElementById('usersYearInput').getElementsByTagName('option');
    for (var i = 0; i < usersYearOptions.length; i++) {
        if (usersYearOptions[i].value == new Date().getFullYear()) usersYearOptions[i].selected = true;
    }
    var totalMonthOptions = document.getElementById('totalMonthInput').getElementsByTagName('option');
    for (var i = 0; i < totalMonthOptions.length; i++) {
        if (totalMonthOptions[i].value == new Date().getMonth()) totalMonthOptions[i].selected = true;
    }
    var totalYearOptions = document.getElementById('totalYearInput').getElementsByTagName('option');
    for (var i = 0; i < totalYearOptions.length; i++) {
        if (totalYearOptions[i].value == new Date().getFullYear()) totalYearOptions[i].selected = true;
    }
}

function changePeriod(object) {
    var monthId = document.getElementById(object + 'MonthInput').value;
    var year = document.getElementById(object + 'YearInput').value;
    var url = window.location.href + '/' + object + '?month=' + monthId + '&year=' + year;
    sendAjaxRequest(url, 'GET')
        .then(function (statsList) {
            var table = document.getElementById(object + 'Table');
            var tbody = table.getElementsByTagName('tbody')[0];
            tbody.innerHTML = '';
            console.log(statsList);
            for (var i = 0; i < statsList.length; i++) {
                if (object === 'products') {
                    tbody.innerHTML += '<tr><td>' + statsList[i].product.id + '</td>' +
                        '<td class="title">' + statsList[i].product.title + '</td>' +
                        '<td>' + statsList[i].amount + '</td></tr>';
                }
                if (object === 'users') {
                    tbody.innerHTML += '<tr><td>' + statsList[i].user.id + '</td>' +
                        '<td class="title">' + statsList[i].user.name + ' ' + statsList[i].user.lastname + '</td>' +
                        '<td>' + statsList[i].sum + '</td></tr>';
                }
                if(object === 'total') {
                    tbody.innerHTML += '<tr><td class="title">' + statsList[i].name + '</td>' +
                        '<td>' + statsList[i].value + '</td></tr>';
                }
            }
        })
        .catch(function (reason) {
            console.log(reason);
        })
}
