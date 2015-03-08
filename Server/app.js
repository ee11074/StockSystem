var express = require('express');
var http = require('http');
var path = require('path');
var bodyParser = require('body-parser');
var mysql = require('mysql');
var hoganexpress = require('hogan-express');
var session = module.exports.session = require('express-session');

var app = module.exports = express();
app.engine('html', hoganexpress);
app.enable('view cache');

app.set('port', process.env.PORT || '80');
app.set('views', path.join(__dirname, 'views'));
app.set('view engine', 'html');
app.use(express.static(path.join(__dirname, 'public')));
app.use(session({
  secret: 'supernova',
  saveUninitialized: true,
  resave: true
}));


app.use(bodyParser.json()); // for parsing application/json
app.use(bodyParser.urlencoded({
  extended: true
})); // for parsing application/x-www-form-urlencoded

app.engine('html', require('hogan-express'));
app.set('view engine', 'html');
app.set('view cache lifetime', 1000 * 3600 * 6); //6 hours, default: 1 hour

var connection = module.exports.db = mysql.createConnection({
  host: '127.0.0.1',
  user: 'root',
  password: '',
  database: 'stocksystem'
});

connection.connect();

require('./routes/routes');

app.set('port', process.env.PORT || '80');

http.createServer(app).listen(app.get('port'), function() {
  console.log('\n----------------------\nNode.js server listening on port ' + app.get('port'));
});