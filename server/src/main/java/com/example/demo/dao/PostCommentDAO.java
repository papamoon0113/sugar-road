package com.example.demo.dao;

import com.example.demo.domain.PostCommentDTO;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface PostCommentDAO {
    @Insert("insert into post_comment ( content, posted_date, post_id, user_id, parent_comment) " +
            "values (#{content},#{postedDate},#{postId},#{memberId},#{parentComment})")
    public boolean createPostComment(PostCommentDTO dto);

    @Select("select post_comment_id, content, posted_date, post_id, user_id, parent_comment from post_comment")
    public List<PostCommentDTO>  readPostComment();

    @Update("update post_comment set content = #{content},posted_date=#{postedDate}, " +
            "user_id=#{memberId}, parent_comment=#{parentComment} where post_comment_id = #{postCommentId}")
    public boolean updatePostComment(PostCommentDTO dto);

    @Delete("delete from post_comment where post_comment_id = #{postCommentId}")
    public boolean deletePostComment(int postCommentId);

    @Select("select post_comment_id, content, posted_date, post_id, user_id, parent_comment  from post_comment where  ${cn} = #{v}")
    public List<PostCommentDTO> readPostCommentBy(@Param("cn") String columnName, @Param("v") String value);
}