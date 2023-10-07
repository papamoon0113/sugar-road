CREATE TABLE post (
   post_id INT PRIMARY KEY AUTO_INCREMENT,
   content VARCHAR(1000) NOT NULL,
   title VARCHAR(20) NOT NULL,
   post_date DATE NOT NULL,
   customer_id VARCHAR(20) NOT NULL,
   post_category_id VARCHAR(2) NOT NULL,

   FOREIGN KEY(customer_id) REFERENCES customer(customer_id),
   FOREIGN KEY(post_category_id) REFERENCES post_category(post_category_id)
);