CREATE TABLE menu(
   menu_id INT PRIMARY KEY AUTO_INCREMENT,
   menu_name VARCHAR(20) NOT NULL,
   price INT NOT NULL,
   menu_desc VARCHAR(30) NOT NULL,
   image BLOB,
   dessert_id VARCHAR(2),
   store_id INT NOT NULL,
   FOREIGN KEY(store_id) REFERENCES store(store_id),
   FOREIGN KEY(dessert_id) REFERENCES dessert (dessert_id)
)