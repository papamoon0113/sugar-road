CREATE TABLE customer (
   customer_id VARCHAR(20) PRIMARY KEY,
   customer_password VARCHAR(20) NOT NULL,
   customer_name VARCHAR(20) NOT NULL,
   nickname VARCHAR(20) NOT NULL,
   email VARCHAR(40) NOT NULL,
   image BLOB
);

CREATE TABLE dessert (
   dessert_id VARCHAR(2) PRIMARY KEY ,
   dessert_desc VARCHAR(20) NOT NULL
);

CREATE TABLE post_category (
   post_category_id VARCHAR(2) PRIMARY KEY,
   post_category_desc VARCHAR(20) NOT NULL
);

CREATE TABLE store(
   store_id INT PRIMARY KEY AUTO_INCREMENT,
   store_name VARCHAR(20) NOT NULL,
   address VARCHAR(40) NOT NULL,
   phone_number VARCHAR(20) NOT NULL,
   store_desc VARCHAR(100) NOT NULL,
   latitude FLOAT NOT NULL, /* 위도 */
   longitude FLOAT NOT NULL, /* 경도 */
   image BLOB
);
   
CREATE TABLE menu(
   menu_id INT PRIMARY KEY AUTO_INCREMENT,
   menu_name VARCHAR(20) NOT NULL,
   price INT NOT NULL,
   menu_desc VARCHAR(30) NOT NULL,
   image BLOB,
   dessert_id VARCHAR(2),
   store_id INT NOT NULL,
   
   FOREIGN KEY(store_id) REFERENCES store(store_id) ON DELETE CASCADE,
   FOREIGN KEY(dessert_id) REFERENCES dessert (dessert_id)
);

CREATE TABLE post (
   post_id INT PRIMARY KEY AUTO_INCREMENT,
   content VARCHAR(1000) NOT NULL,
   title VARCHAR(20) NOT NULL,
   posted_date DATE NOT NULL,
   customer_id VARCHAR(20) NOT NULL,
   post_category_id VARCHAR(2) NOT NULL,

   FOREIGN KEY(customer_id) REFERENCES customer(customer_id),
   FOREIGN KEY(post_category_id) REFERENCES post_category(post_category_id)
);

CREATE TABLE post_comment (
   post_comment_id INT PRIMARY KEY AUTO_INCREMENT,
   customer_id VARCHAR(15) NOT NULL,
   post_id INT NOT NULL,
   content VARCHAR(100) NOT NULL,
   posted_date DATE NOT NULL,
   
   FOREIGN KEY(customer_id) REFERENCES customer(customer_id),
   FOREIGN KEY(post_id) REFERENCES post(post_id) ON DELETE CASCADE
);

CREATE TABLE review (
   review_id INT PRIMARY KEY AUTO_INCREMENT,
   customer_id VARCHAR(15) NOT NULL,
   store_id INT NOT NULL,
   content VARCHAR(1000) NOT NULL,
   posted_date DATE NOT NULL,
   star INT NOT NULL,
   
   FOREIGN KEY(customer_id) REFERENCES customer(customer_id),
   FOREIGN KEY(store_id) REFERENCES store(store_id) ON DELETE CASCADE
);

CREATE TABLE review_comment(
   review_comment_id INT PRIMARY KEY AUTO_INCREMENT,
   customer_id VARCHAR(15) NOT NULL,
   review_id INT NOT NULL,
   content VARCHAR(100) NOT NULL,
   posted_date DATE NOT NULL,

   FOREIGN KEY(customer_id) REFERENCES customer(customer_id),
   FOREIGN KEY(review_id) REFERENCES review(review_id) ON DELETE CASCADE
);

CREATE TABLE recommendation(
   recommendation_id INT PRIMARY KEY AUTO_INCREMENT,
   reference_type VARCHAR(1) NOT NULL,
   customer_id VARCHAR(20) NOT NULL,
   reference_id INT NOT NULL
);