CREATE TABLE review (
   review_id INT PRIMARY KEY AUTO_INCREMENT,
   customer_id VARCHAR(15) NOT NULL,
   store_id INT NOT NULL,
   content VARCHAR(1000) NOT NULL,
   posted_date DATE NOT NULL,
   star INT NOT NULL,
   
   FOREIGN KEY(customer_id) REFERENCES customer(customer_id),
   FOREIGN KEY(store_id) REFERENCES store(store_id)
);
