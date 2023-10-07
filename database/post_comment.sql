create table post_comment (
   post_comment_id int primary key AUTO_INCREMENT,
   customer_id varchar(15) not null,
   post_id int not null,
   content varchar(100) not null,
   posted_date date not null,
   
   FOREIGN KEY(customer_id) REFERENCES customer(customer_id),
   FOREIGN KEY(post_id) REFERENCES post(post_id)
);