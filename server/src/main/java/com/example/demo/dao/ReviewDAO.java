package com.example.demo.dao;

import com.example.demo.domain.ReviewResultVO;
import java.util.List;
import com.example.demo.domain.ReviewDTO;
import org.apache.ibatis.annotations.*;

@Mapper
public interface ReviewDAO {
    //R 다 불러 오는 거
    @Select("select review_id, user_id, store_id, content, posted_date, star from review")
    public List<ReviewDTO> readReview();

    //R 유동적 불러오기 (where절 컬럼명 = 값)
    @Select("select review_id, r.user_id as 'user_id', nickname, content, posted_date, star, review_image_path, store_id "
        + "from (select review_id, content, posted_date, star, review_image_path, user_id, store_id "
        + "from review "
        + "where ${cn} = #{v}) as r "
        + "join "
        + "(select user_id, nickname from users) as u "
        + "on u.user_id = r.user_id")
    public List<ReviewResultVO> readReviewBy(@Param("cn") String columnName, @Param("v") String value);

    //R 검색어에 맞게 불러오기
    @Select("select review_id, user_id, store_id, content, posted_date, star from review where content like '%${search}%' ")
    public List<ReviewDTO> readReviewSearch(String search);
    //U id 기준으로 리뷰 글 수정 (글내용, 별점, 리뷰 대상 가게id)
    @Update("update review set content = #{content}, star = #{star}  where review_id = #{reviewId}")
    public boolean updateReview(ReviewDTO dto);

    //C 리뷰 글 생성 (유저id, 리뷰 대상 가게id, 글내용, 작성날짜(now), 별점)
    @Insert("insert into review (user_id, store_id, content, posted_date, star, review_image_path) values (#{userId}, #{storeId}, #{content}, now(), ${star}, #{reviewImagePath})")
    public boolean createReview(ReviewDTO dto);

    //D id 기준으로 리뷰 글 삭제
    @Delete("delete from review where review_id = ${id}")
    public boolean deleteReview(int id);

    @Select("select review_id, r.user_id, nickname, content, posted_date, star, review_image_path "
        + "from (select review_id, content, posted_date, star, review_image_path, user_id "
                + "from review "
                + "where store_id = ${storeId}) as r "
        + "join "
        + "(select user_id, nickname from users) as u "
        + "on u.user_id = r.user_id "
        + "order by posted_date;")
    public List<ReviewResultVO> readReviewOfStore(int storeId);
}
