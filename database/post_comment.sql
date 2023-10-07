CREATE TABLE post_comment (
   post_comment_id INT PRIMARY KEY AUTO_INCREMENT,
   customer_id VARCHAR(15) NOT NULL,
   post_id INT NOT NULL,
   content VARCHAR(100) NOT NULL,
   posted_date DATE NOT NULL,
   
   FOREIGN KEY(customer_id) REFERENCES customer(customer_id),
   FOREIGN KEY(post_id) REFERENCES post(post_id)
);