CREATE TABLE users (
   user_id VARCHAR(20) PRIMARY KEY,
   user_password VARCHAR(20) NOT NULL,
   user_name VARCHAR(20) NOT NULL,
   nickname VARCHAR(20) NOT NULL,
   user_email VARCHAR(20) NOT NULL,
   user_image_path varchar(255)
);