package com.example.demo.dao;

import com.example.demo.domain.ReviewCommentDTO;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ReviewCommentDAO {
    @Select("select review_comment_id, content, posted_date, review_id, member_id, parent_comment from review_comment")
    public List<ReviewCommentDTO> readReviewComment();

    @Select("<script>select review_comment_id, content, posted_date, review_id, member_id, parent_comment from review_comment " +
            "<where>" +
            "<if test='memberId ! =0'> member_id = #{memberId}</if>" +
            "<if test='reviewId != 0'> review_id = #{reviewId}</if>" +
            "<if test='parentComment != 0'>parent_comment = #{parentComment}</if>" +
            "</where></script>")
    public ReviewCommentDTO redaReviewCommentBy(ReviewCommentDTO dto);
    @Insert("insert into review_comment (content, review_id, member_id, parent_comment) values(#{content}, #{reviewId}, #{memberId}, #{parentComment})")
    public boolean createReviewComment(ReviewCommentDTO dto);

    @Update("update review_comment set content = #{content} where review_comment_id = #{reviewCommentId}")
    public boolean updateReviewComment(ReviewCommentDTO dto);

    @Delete("delete from review_comment where review_comment_id = #{id}")
    public boolean deleteReviewComment(String id);
}
