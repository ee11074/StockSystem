app = require('../app');
db = require('../app').db;

app.get('/', function(req, res) {
  partial = {
    head: 'partials/header',
    scripts: 'partials/scripts',
    content: 'partials/sidebar',
    body: 'pages/new_item'
  };
  res.render(
    'index', {
      partials: partial
    }
  );
});

app.get('/login', function(req, res) {
  partial = {
    head: 'partials/header',
    scripts: 'partials/scripts',
    content: 'pages/login'
  };
  res.render(
    'index', {
      partials: partial
    }
  );
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