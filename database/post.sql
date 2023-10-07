CREATE TABLE post (
   post_id INT PRIMARY KEY AUTO_INCREMENT,
   content VARCHAR(1000) NOT NULL,
   title VARCHAR(20) NOT NULL,
   post_date DATE NOT NULL,
   customer_id INT NOT NULL,
   category_id VARCHAR(2),

   FOREIGN KEY(customer_id) REFERENCES customer(customer_id),
   FOREIGN KEY(category_id) REFERENCES category(category_id)
);