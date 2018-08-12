# SendGrid Lib

This is a trivial cn1lib for sending emails using [sendgrid](https://sendgrid.com/). It doesn't support most of the features of sendgrid but can support everything if we want to add it. It's trivial and based on the REST API.

## Usage

````java
SendGrid s = SendGrid.create(YOUR_SEND_GRID_API_TOKEN);
s.sendSync(toEmail, fromEmail, subject, body);
````

Notice you will need a [sendgrid](https://sendgrid.com/) account which is available for free with 100 free emails per day. The signup wizard provides a button to generate the API token.
