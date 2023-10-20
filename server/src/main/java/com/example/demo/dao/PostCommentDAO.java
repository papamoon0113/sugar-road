package com.example.demo.dao;

import com.example.demo.domain.PostCommentDTO;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface PostCommentDAO {
    @Insert("insert into post_comment ( content, posted_date, post_id, user_id, parent_comment) " +
            "values (#{content},#{postedDate},#{postId},#{userId},#{parentComment})")
    public boolean createPostCommentChild(PostCommentDTO dto);

    @Insert("insert into post_comment ( content, posted_date, post_id, user_id) " +
        "values (#{content},#{postedDate},#{postId},#{userId})")
    public boolean createPostComment(PostCommentDTO dto);

    @Select("select post_comment_id, content, posted_date, post_id, user_id, parent_comment from post_comment")
    public List<PostCommentDTO>  readPostComment();

    @Select("select count(*) from post_comment where post_id = #{postId}")
    public int readPostCommentCount(int postId);

    @Update("update post_comment set content = #{content},posted_date=#{postedDate}, " +
            "user_id=#{memberId}, parent_comment=#{parentComment} where post_comment_id = #{postCommentId}")
    public boolean updatePostComment(PostCommentDTO dto);

    @Delete("delete from post_comment where post_comment_id = #{postCommentId}")
    public boolean deletePostComment(int postCommentId);

    @Select("select post_comment_id, nickname, content, posted_date, post_id, c.user_id, parent_comment"
        + "from (select post_comment_id, content, posted_date, post_id, user_id, parent_comment "
        + "from post_comment "
        + "where ${cn} = #{v}) as c "
        + "join "
        + "(select user_id, nickname from users) as u "
        + "on u.user_id = c.user_id "
        + "order by ifnull(parent_comment, post_comment_id), posted_date;")
    public List<PostCommentDTO> readPostCommentBy(@Param("cn") String columnName, @Param("v") String value);
}