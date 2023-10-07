create table store(
   store_id int primary key auto_increment,
   store_name varchar(20) not null,
   address varchar(40) not null,
   phone_number varchar(12) not null,
   store_desc varchar(100) not null,
   latitude float not null, -- 위도
   longitude float not null,-- 경도
   image blob
   );