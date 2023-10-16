CREATE TABLE users (
   user_id VARCHAR(20) PRIMARY KEY,
   user_password VARCHAR(20) NOT NULL,
   user_name VARCHAR(20) NOT NULL,
   nickname VARCHAR(20) NOT NULL,
   user_email VARCHAR(40) NOT NULL,
   user_image_path VARCHAR(255) 
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
   latitude FLOAT, /* 위도 */
   longitude FLOAT, /* 경도 */
   store_image_path VARCHAR(255),
   user_id VARCHAR(255),
   
   FOREIGN KEY(user_id) REFERENCES users(user_id)
);
   
CREATE TABLE menu(
   menu_id INT PRIMARY KEY AUTO_INCREMENT,
   menu_name VARCHAR(20) NOT NULL,
   price INT,
   menu_desc VARCHAR(30),
   menu_image_path VARCHAR(255),
   dessert_id VARCHAR(2),
   store_id INT NOT NULL,
   
   FOREIGN KEY(store_id) REFERENCES store(store_id) ON DELETE CASCADE
);

CREATE TABLE post (
   post_id INT PRIMARY KEY AUTO_INCREMENT,
   content VARCHAR(1000) NOT NULL,
   title VARCHAR(20) NOT NULL,
   posted_date DATE NOT NULL,
   user_id VARCHAR(20) NOT NULL,
   post_category_id VARCHAR(2) NOT NULL,

   FOREIGN KEY(user_id) REFERENCES users(user_id)
);

CREATE TABLE post_image (
   post_image_id INT PRIMARY KEY AUTO_INCREMENT,
   post_image_path VARCHAR(255),
   post_id INT NOT NULL,
   
   FOREIGN KEY(post_id) REFERENCES post(post_id) ON DELETE CASCADE
);

CREATE TABLE post_comment (
   post_comment_id INT PRIMARY KEY AUTO_INCREMENT,
   user_id VARCHAR(15) NOT NULL,
   post_id INT NOT NULL,
   content VARCHAR(100) NOT NULL,
   posted_date DATE NOT NULL,
   parent_comment INT NOT NULL,
   
   FOREIGN KEY(user_id) REFERENCES users(user_id),
   FOREIGN KEY(post_id) REFERENCES post(post_id) ON DELETE CASCADE
);

ALTER TABLE post_comment
ADD FOREIGN KEY(parent_comment) REFERENCES post_comment(post_comment_id);

CREATE TABLE review (
   review_id INT PRIMARY KEY AUTO_INCREMENT,
   user_id VARCHAR(15) NOT NULL,
   store_id INT NOT NULL,
   content VARCHAR(1000) NOT NULL,
   posted_date DATE NOT NULL,
   star INT NOT NULL,
   review_image_path VARCHAR(255),
   
   FOREIGN KEY(user_id) REFERENCES users(user_id),
   FOREIGN KEY(store_id) REFERENCES store(store_id) ON DELETE CASCADE
);

CREATE TABLE review_comment(
   review_comment_id INT PRIMARY KEY AUTO_INCREMENT,
   user_id VARCHAR(15) NOT NULL,
   review_id INT NOT NULL,
   content VARCHAR(100) NOT NULL,
   posted_date DATE NOT NULL,
   parent_comment INT NOT NULL,

   FOREIGN KEY(user_id) REFERENCES users(user_id),
   FOREIGN KEY(review_id) REFERENCES review(review_id) ON DELETE CASCADE
);

ALTER TABLE review_comment
ADD FOREIGN KEY(parent_comment) REFERENCES review_comment(review_comment_id);

CREATE TABLE recommendation(
   recommendation_id INT PRIMARY KEY AUTO_INCREMENT,
   reference_type VARCHAR(1) NOT NULL,
   user_id VARCHAR(20) NOT NULL,
   reference_id INT NOT NULL,
   posted_date DATE NOT NULL,
   
   FOREIGN KEY(user_id) REFERENCES users(user_id)
);