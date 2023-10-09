CREATE TABLE customer (
   customer_id VARCHAR(20) PRIMARY KEY,
   customer_password VARCHAR(20) NOT NULL
);

CREATE TABLE dessert (
   dessert_id VARCHAR(2) PRIMARY KEY ,
   dessert_desc VARCHAR(20) NOT NULL
);

CREATE TABLE post_category (
   post_category_id VARCHAR(2) PRIMARY KEY,
   post_category_desc VARCHAR(20) NOT NULL
);

create table store(
   store_id int primary key auto_increment,
   store_name varchar(20) not null,
   address varchar(40) not null,
   phone_number varchar(20) not null,
   store_desc varchar(100) not null,
   latitude float not null, /* 위도 */
   longitude float not null, /* 경도 */
   image blob
);
   
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
);

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

CREATE TABLE post_comment (
   post_comment_id INT PRIMARY KEY AUTO_INCREMENT,
   customer_id VARCHAR(15) NOT NULL,
   post_id INT NOT NULL,
   content VARCHAR(100) NOT NULL,
   posted_date DATE NOT NULL,
   
   FOREIGN KEY(customer_id) REFERENCES customer(customer_id),
   FOREIGN KEY(post_id) REFERENCES post(post_id)
);

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

CREATE TABLE review_comment(
   review_comment_id INT PRIMARY KEY AUTO_INCREMENT,
   customer_id VARCHAR(15) NOT NULL,
   review_id INT NOT NULL,
   content VARCHAR(100) NOT NULL,
   posted_date DATE NOT NULL,

   FOREIGN KEY(customer_id) REFERENCES customer(customer_id),
   FOREIGN KEY(review_id) REFERENCES review(review_id)
);