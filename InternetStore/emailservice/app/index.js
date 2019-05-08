const nodemailer = require("nodemailer");
const hbs = require('nodemailer-express-handlebars');

let transporter = nodemailer.createTransport({
    host: "smtp.yandex.ru",
    port: 465,
    auth: {
        user: 'b0nline',
        pass: 'booksOnline'
    }
});

transporter.use('compile', hbs({
    viewEngine: {
        partialsDir: 'templates/'
    },
    viewPath: 'templates/',
    extName: '.hbs'
}));

const express = require("express");
const server = express();

let bodyParser = require("body-parser");
server.use(bodyParser.urlencoded({ extended: true }));
server.use(bodyParser.json());

server.post("/send-email", (req, res, next) => {
    console.log(req.body);

    let mailOptions = {
        from: 'b0nline@yandex.ru',
        to: 'korchinskiy.ilya@yandex.ru',
        subject: 'Your order on BooksOnline',
        template: 'order',
        context: {
            name: req.body.userName,
            id: req.body.orderId
        }
    };

    transporter.sendMail(mailOptions, function (error, info) {
        if (error) {
            console.log(error);
            res.end('error');
        } else {
            console.log('Email sent: ' + info.response);
            res.end('success');
        }
    });
});

server.listen(3000, () => {
    console.log("Server running on port 3000");
});
