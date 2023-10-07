CREATE TABLE review_comment(
   review_comment_id INT PRIMARY KEY AUTO_INCREMENT,
   customer_id VARCHAR(15) NOT NULL,
   review_id INT NOT NULL,
   content VARCHAR(100) NOT NULL,
   posted_date DATE NOT NULL,
   -- FOREIGN KEY(customer_id) REFERENCES customer(customer_id),
   FOREIGN KEY(review_id) REFERENCES review(review_id)
);