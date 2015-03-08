app = require('../app');
db = require('../app').db;

app.get('/', function(req, res) {
  //console.log(req.session);
  if (req.session.isLogged === true) {
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
  } else {
    res.redirect("/login");
  }
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

app.post('/login', function(req, res) {
  //console.log(req.body);
  db.query('SELECT * FROM user', function(err, result, fields) {
    if (err) throw err;
    if (req.body.username === result[0].username && req.body.password === result[0].user_password) {
      console.log("LOGGED IN AS: " + req.body.username);
      req.session.isLogged = true;
      req.session.save(function(err) {
        // session saved
      });
      res.send({
        'success': true
      });
    } else {
      console.log("COULD NOT LOG IN");
      req.session.isLogged = false;
      req.session.save(function(err) {
        // session saved
      });
      res.send({
        'success': false
      });
    }
  });
});