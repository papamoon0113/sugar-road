package com.example.demo.dao;

import com.example.demo.domain.UsersDTO;
import com.example.demo.domain.ReviewCommentDTO;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ReviewCommentDAO {
    @Select("select review_comment_id, content, posted_date, review_id, user_id, parent_comment from review_comment")
    public List<ReviewCommentDTO> readReviewComment();

//    @Select("<script>select review_comment_id, content, posted_date, review_id, user_id, parent_comment from review_comment " +
//            "<where>" +
//            "<if test='memberId ! =0'> user_id = #{memberId}</if>" +
//            "<if test='reviewId != 0'> review_id = #{reviewId}</if>" +
//            "<if test='parentComment != 0'>parent_comment = #{parentComment}</if>" +
//            "</where></script>")
//    public ReviewCommentDTO readReviewCommentBy(ReviewCommentDTO dto);
    @Select("select review_comment_id, content, posted_date, review_id, user_id, parent_comment from review_comment where ${cn} = #{v}")
    public List<UsersDTO> readReviewCommentBy(@Param("cn") String columnName, @Param("v") String value);
    @Insert("insert into review_comment (content, review_id, user_id, parent_comment, posted_date) values(#{content}, #{reviewId}, #{memberId}, #{parentComment}, #{postedDate})")
    public boolean createReviewComment(ReviewCommentDTO dto);

    @Update("update review_comment set content = #{content} where review_comment_id = #{reviewCommentId}")
    public boolean updateReviewComment(ReviewCommentDTO dto);

    @Delete("delete from review_comment where review_comment_id = #{id}")
    public boolean deleteReviewComment(String id);
}
