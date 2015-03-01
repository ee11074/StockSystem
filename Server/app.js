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
  host: '127.0.0.1',
  user: 'root',
  password: '',
  database: 'stocksystem'
});

connection.connect();

require('./routes/routes');

app.set('port', process.env.PORT || '8080');

http.createServer(app).listen(app.get('port'), function() {
  console.log('\n----------------------\nNode.js server listening on port ' + app.get('port'));
});