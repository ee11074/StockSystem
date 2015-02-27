DROP TABLE IF EXISTS `User`,`Item`,`Container`,`Formula`,`Fabrication`,`FabricationItem`,`Transfer`,`Leak`,`Buy`,`Sale`,`Supplier`,`Client`;

CREATE TABLE `User`(
  id INTEGER NOT NULL PRIMARY KEY,
  username VARCHAR(120) NOT NULL,
  user_password VARCHAR(120) NOT NULL
);

CREATE TABLE `Client`(
  id INTEGER NOT NULL PRIMARY KEY,
  name VARCHAR(120)
);

CREATE TABLE `Supplier`(
  id INTEGER NOT NULL PRIMARY KEY,
  name VARCHAR(120)
);


CREATE TABLE `Item`(
  code INTEGER NOT NULL PRIMARY KEY,
  name VARCHAR(120) NOT NULL,
  unit CHAR NOT NULL,
  degree FLOAT,
  return_rate FLOAT
);

CREATE TABLE `Container`(
  code INTEGER NOT NULL PRIMARY KEY,
  item_code INTEGER,
  capacity FLOAT,
  amount FLOAT,
  FOREIGN KEY fk_item(item_code) REFERENCES Item(code)
);

CREATE TABLE `Formula`(
  id INTEGER NOT NULL PRIMARY KEY,
  code INTEGER NOT NULL,
  fabricated_item_code INTEGER ,
  amount FLOAT,
  item INTEGER,
  FOREIGN KEY fk_item(item) REFERENCES Item(code),
  FOREIGN KEY fk_fab(fabricated_item_code) REFERENCES Item(code)
);

CREATE TABLE `Fabrication`(
  id INTEGER NOT NULL PRIMARY KEY,
  fabric_date DATE,
  fabric_item INTEGER
);

CREATE TABLE `FabricationItem` (
  id INTEGER NOT NULL PRIMARY KEY,
  fabric_id INTEGER NOT NULL,
  quantity FLOAT,
  item INTEGER NOT NULL,
  container_id INTEGER NOT NULL,
  FOREIGN KEY fk_fab(fabric_id) REFERENCES Fabrication(id),
  FOREIGN KEY fk_it(item) REFERENCES Item(code),
  FOREIGN KEY fk_cont(container_id) REFERENCES Container(code)
);

CREATE TABLE `Transfer`(
  id INTEGER NOT NULL,
  transfer_date DATE,
  container INTEGER,
  quantity FLOAT,
  dest_container INTEGER,
  client INTEGER,
  FOREIGN KEY fk_cont(container) REFERENCES Container(code),
  FOREIGN KEY fk_dest(dest_container) REFERENCES Container(code),
  FOREIGN KEY fk_cli(client) REFERENCES Client(id)

);

CREATE TABLE `Leak`(
  id INTEGER NOT NULL PRIMARY KEY,
  leak_date DATE,
  item INTEGER ,
  quantity FLOAT,
  container INTEGER ,
  FOREIGN KEY fk_it(item) REFERENCES Item(code),
  FOREIGN KEY fk_cont(container) REFERENCES Container(code)
);

CREATE TABLE `Buy`(
  id INTEGER NOT NULL PRIMARY KEY,
  buy_date DATE,
  item INTEGER,
  supplier INTEGER,
  quantity FLOAT,
  price FLOAT,
  dest_container INTEGER,
  FOREIGN KEY fk_it(item) REFERENCES Item(code),
  FOREIGN KEY fk_sup(supplier) REFERENCES Supplier(id),
  FOREIGN KEY fk_dest(dest_container) REFERENCES Container(code)
);

CREATE TABLE `BuyInfo` (
	id INTEGER NOT NULL,
	quantity FLOAT,
	container INTEGER NOT NULL ,
  FOREIGN KEY fk_it(id) REFERENCES Buy(id),
  FOREIGN KEY fk_b(container) REFERENCES Container(code)
);

CREATE TABLE `Sale`(
  id INTEGER NOT NULL PRIMARY KEY,
  sale_date DATE,
  item INTEGER,
  client INTEGER,
  container INTEGER,
  quantity FLOAT,
  FOREIGN KEY fk_it(item) REFERENCES Item(code),
  FOREIGN KEY fk_cl(client) REFERENCES Client(id),
  FOREIGN KEY fk_cont(container) REFERENCES Container(code)
);

CREATE TABLE `SaleInfo` (
	id INTEGER NOT NULL,
	quantity FLOAT,
	container INTEGER,
  FOREIGN KEY fk_it(id) REFERENCES Sale(id),
  FOREIGN KEY fk_b(container) REFERENCES Container(code)
);
