DROP TABLE IF EXISTS `fabricationitem`,
                     `user`,
                     `leak`,
                     `fabrication`,
                     `formula`,
                     `transfer`,
                     `buy`,
                     `sale`,
                     `supplier`,
                     `client`,
                     `container`,
                     `item`;


CREATE TABLE `user`(id INTEGER NOT NULL PRIMARY KEY auto_increment, username varchar(120) NOT NULL, user_password varchar(120) NOT NULL);


CREATE TABLE `client`(id INTEGER NOT NULL PRIMARY KEY auto_increment, name varchar(120));


CREATE TABLE `supplier`(id INTEGER NOT NULL PRIMARY KEY auto_increment, name varchar(120));


CREATE TABLE `item`(code INTEGER NOT NULL PRIMARY KEY auto_increment, name varchar(120) NOT NULL, unit CHAR NOT NULL, degree FLOAT, return_rate FLOAT);


CREATE TABLE `container`(code INTEGER NOT NULL PRIMARY KEY auto_increment, item_code INTEGER, capacity FLOAT, amount FLOAT,
                         FOREIGN KEY fk_item(item_code) REFERENCES item(code));


CREATE TABLE `formula`(id INTEGER NOT NULL PRIMARY KEY auto_increment, code INTEGER NOT NULL, fabricated_item_code INTEGER , amount FLOAT, item INTEGER,
                       FOREIGN KEY fk_item(item) REFERENCES item(code),
                       FOREIGN KEY fk_fab(fabricated_item_code) REFERENCES item(code));


CREATE TABLE `fabrication`(id INTEGER NOT NULL PRIMARY KEY auto_increment, fabric_date DATE, fabric_item INTEGER);


CREATE TABLE `fabricationitem` (id INTEGER NOT NULL PRIMARY KEY auto_increment, fabric_id INTEGER NOT NULL, quantity FLOAT, item INTEGER NOT NULL, container_id INTEGER NOT NULL,
                                FOREIGN KEY fk_fab(fabric_id) REFERENCES fabrication(id),
                                FOREIGN KEY fk_it(item) REFERENCES item(code),
                                FOREIGN KEY fk_cont(container_id) REFERENCES container(code));


CREATE TABLE `transfer`(id INTEGER NOT NULL, transfer_date DATE, container INTEGER, quantity FLOAT, dest_container INTEGER, client INTEGER,
                        FOREIGN KEY fk_cont(container) REFERENCES container(code),
                        FOREIGN KEY fk_dest(dest_container) REFERENCES container(code),
                        FOREIGN KEY fk_cli(client) REFERENCES client(id));


CREATE TABLE `leak`(id INTEGER NOT NULL PRIMARY KEY auto_increment, leak_date DATE, item INTEGER , quantity FLOAT, container INTEGER ,
                    FOREIGN KEY fk_it(item) REFERENCES item(code),
                    FOREIGN KEY fk_cont(container) REFERENCES container(code));


CREATE TABLE `buy`(id INTEGER NOT NULL PRIMARY KEY auto_increment, buy_date DATE, item INTEGER, supplier INTEGER, quantity FLOAT, price FLOAT, dest_container INTEGER,
                   FOREIGN KEY fk_it(item) REFERENCES item(code),
                   FOREIGN KEY fk_sup(supplier) REFERENCES supplier(id),
                   FOREIGN KEY fk_dest(dest_container) REFERENCES container(code));


CREATE TABLE `buyinfo` (id INTEGER NOT NULL, quantity FLOAT, container INTEGER NOT NULL,
                        FOREIGN KEY fk_it(id) REFERENCES buy(id),
                        FOREIGN KEY fk_b(container) REFERENCES container(code));


CREATE TABLE `sale`(id INTEGER NOT NULL PRIMARY KEY auto_increment, sale_date DATE, item INTEGER, client INTEGER, container INTEGER, quantity FLOAT,
                    FOREIGN KEY fk_it(item) REFERENCES item(code),
                    FOREIGN KEY fk_cl(client) REFERENCES client(id),
                    FOREIGN KEY fk_cont(container) REFERENCES container(code));


CREATE TABLE `saleinfo` (id INTEGER NOT NULL, quantity FLOAT, container INTEGER,
                         FOREIGN KEY fk_it(id) REFERENCES sale(id),
                         FOREIGN KEY fk_b(container) REFERENCES container(code));
