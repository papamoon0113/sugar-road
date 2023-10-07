CREATE TABLE dessert (
   dessert_id VARCHAR(2) PRIMARY KEY ,
   dessert_desc VARCHAR(20) NOT NULL
);
/* 디저트 정보 테이블 생성 */

/* 디저트 정보 데모 데이터 삽입 */
INSERT INTO dessert VALUES ('01', '와플Demo');
INSERT INTO dessert VALUES ('02', '크로와상Demo');
INSERT INTO dessert VALUES ('03', '마카롱Demo');
INSERT INTO dessert VALUES ('04', '핫케이크Demo');
INSERT INTO dessert VALUES ('05', '쉬폰케이크Demo');