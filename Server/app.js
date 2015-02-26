var express = require('express');
var http = require('http');
var app = module.exports = express();
var bodyParser = require('body-parser');
var mysql = require('mysql');

app.use(bodyParser.json()); // for parsing application/json
app.use(bodyParser.urlencoded({
  extended: true
})); // for parsing application/x-www-form-urlencoded

var connection = module.exports.db = mysql.createConnection({
  host: 'sql3.freemysqlhosting.net',
  user: 'sql368676',
  password: 'rQ2*hW3*'
});

connection.connect();

require('./routes/routes');

app.set('port', process.env.PORT || '80');

http.createServer(app).listen(app.get('port'), function() {
  console.log('\n----------------------\nNode.js server listening on port ' + app.get('port'));
});