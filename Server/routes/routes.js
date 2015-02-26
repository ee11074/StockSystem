app = require('../app');
db = require('../app').db;

app.get('/', function(req, res, next) {
  res.send('vilares');
});

app.get('/stock', function(req, res, next) {
  res.send('vilares');
});

app.post('/stock', function(req, res, next) {
  db.query('SELECT 1 + 1 AS solution', function(err, rows, fields) {
    if (err) throw err;
    console.log('The solution is: ', rows[0].solution);
  });
  console.log(req.body.var);
  res.send('vilares');
});