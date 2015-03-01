app = require('../app');
db = require('../app').db;

app.get('/', function(req, res, next) {
  res.send('vilares');
});

app.get('/stock', function(req, res, next) {
  res.send('vilares');
});

app.post('/new_item', function(req, res, next) {
  product_info = req.body.data;
  if (req.body.type === "fabric") {
    db.query('INSERT INTO fabrication SET ?', product_info, function(err, result) {
      if (err) throw err;
    });
  } else if (req.body.type === "buy") {
    db.query('INSERT INTO item SET ?', product_info, function(err, result) {
      if (err) throw err;
    });
  } else {
    res.send("{'error':'invalid'}");
  }
});