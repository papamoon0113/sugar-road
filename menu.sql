create table menu(
   menu_id int primary key auto_increment,
   menu_name varchar(20) not null,
   price int not null,
   menu_desc varchar(30) not null,
   image blob,
   dessert_id varchar(2),
   store_id int not null,
   foreign key(store_id) references store(store_id),
   foreign key(dessert_id) references dessert (dessert_id)
)