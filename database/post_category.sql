CREATE TABLE post_category (
   post_category_id VARCHAR(2) PRIMARY KEY,
   post_desc VARCHAR(20) NOT NULL
);
/* 가게 카테고리 테이블 생성 */

/* 가게 카테고리 데모 데이터 삽입 */
INSERT INTO post_category VALUES ('01', '빵집Demo');
INSERT INTO post_category VALUES ('02', '카페Demo');
INSERT INTO post_category VALUES ('03', '빵과차Demo');
INSERT INTO post_category VALUES ('04', '테이크아웃커피Demo');
INSERT INTO post_category VALUES ('05', '과자집Demo');