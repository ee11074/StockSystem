DROP TABLE IF EXISTS `Item`,`Container`,`Formula`;

CREATE TABLE `Item`(
    code INTEGER NOT NULL PRIMARY KEY,
    name VARCHAR NOT NULL,
    unit CHAR NOT NULL,
    degree FLOAT,
    return_rate FLOAT
);

CREATE TABLE `Container`(
  code INTEGER NOT NULL PRIMARY KEY,
  item_code INTEGER FOREIGN KEY REFERENCES Item(code),
  capacity FLOAT,
  amount FLOAT
);

CREATE TABLE `Formula`(
  id INTEGER NOT NULL PRIMARY KEY,
  code INTEGER NOT NULL,
  fabricated_item_code INTEGER FOREIGN KEY REFERENCES Item(code),
  amount FLOAT,
  item INTEGER FOREIGN KEY REFERENCES Item(code)
);

CREATE TABLE `Fabrication`(
  id INTEGER NOT NULL,
  fabric_date DATE,
  fabric_item INTEGER
);

CREATE TABLE `FabricationItem` (
  id INTEGER NOT NULL  FOREIGN KEY REFERENCES Fabrication(code),
  quantity FLOAT,
  item INTEGER NOT NULL  FOREIGN KEY REFERENCES Item(id)
);

CREATE TABLE `Transfer`(
  id INTEGER NOT NULL,
  transfer_date DATE,
  container INTEGER FOREIGN KEY REFERENCES Container(code),
  quantity FLOAT,
  dest_container INTEGER FOREIGN KEY REFERENCES Container(code),
  client INTEGER FOREIGN KEY REFERENCES Client(id)
);

CREATE TABLE `Leak`(
  id INTEGER NOT NULL PRIMARY KEY,
  leak_date DATE,
  item INTEGER FOREIGN KEY REFERENCES Item(code),
  quantity FLOAT,
  container INTEGER FOREIGN KEY REFERENCES Container(code)
);

CREATE TABLE `Buy`(
  id INTEGER NOT NULL PRIMARY KEY,
  buy_date DATE,
  item INTEGER FOREIGN KEY REFERENCES Item(code),
  supplier INTEGER FOREIGN KEY REFERENCES Supplier(id),
  quantity FLOAT,
  dest_container INTEGER FOREIGN KEY REFERENCES Container(code)
);

CREATE TABLE `Sale`(
  id INTEGER NOT NULL PRIMARY KEY,
  sale_date DATE,
  item INTEGER FOREIGN KEY REFERENCES Item(code),
  quantity FLOAT,
  client INTEGER FOREIGN KEY REFERENCES Client(id),
  container INTEGER FOREIGN KEY REFERENCES Container(code),
  quantity FLOAT
);

CREATE TABLE `Client`(
  id INTEGER NOT NULL PRIMARY KEY,
  name VARCHAR
);

CREATE TABLE `Supplier`(
  id INTEGER NOT NULL PRIMARY KEY,
  name VARCHAR
);
