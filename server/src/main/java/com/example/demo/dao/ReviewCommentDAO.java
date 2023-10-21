package com.example.demo.dao;

import com.example.demo.domain.UsersDTO;
import com.example.demo.domain.ReviewCommentDTO;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ReviewCommentDAO {
    @Select("select review_comment_id, content, posted_date, review_id, user_id, parent_comment from review_comment")
    public List<ReviewCommentDTO> readReviewComment();

    @Select("select review_comment_id, nickname, content, posted_date, review_id, c.user_id, parent_comment "
        + "from (select review_comment_id, content, posted_date, review_id, user_id, parent_comment "
        + "from review_comment "
        + "where ${cn} = #{v}) as c "
        + "join "
        + "(select user_id, nickname from users) as u "
        + "on u.user_id = c.user_id "
        + "order by ifnull(parent_comment, review_comment_id), posted_date;")
    public List<ReviewCommentDTO> readReviewCommentBy(@Param("cn") String columnName, @Param("v") String value);

    @Select("select review_comment_id, nickname, content, posted_date, review_id, c.user_id, parent_comment "
        + "from (select review_comment_id, content, posted_date, review_id, user_id, parent_comment "
        + "from review_comment "
        + "where ${cn} = #{v}) as c "
        + "join "
        + "(select user_id, nickname from users) as u "
        + "on u.user_id = c.user_id "
        + "order by ifnull(parent_comment, review_comment_id), posted_date "
        + "limit ${start}, ${count} ;")
    public List<ReviewCommentDTO> readReviewCommentByLimit(@Param("cn") String columnName, @Param("v") String value, @Param("start") int startPoint, @Param("count") int count);

    @Insert("insert into review_comment (content, review_id, user_id, posted_date) values(#{content}, #{reviewId}, #{userId}, now())")
    public boolean createReviewComment(ReviewCommentDTO dto);

    @Insert("insert into review_comment (content, review_id, user_id, parent_comment, posted_date) values(#{content}, #{reviewId}, #{userId}, #{parentComment}, now())")
    public boolean createReviewCommentChild(ReviewCommentDTO dto);

    @Update("update review_comment set content = #{content}, postedDate = now() where review_comment_id = #{reviewCommentId}")
    public boolean updateReviewComment(ReviewCommentDTO dto);

    @Delete("delete from review_comment where review_comment_id = #{id}")
    public boolean deleteReviewComment(int id);
}
