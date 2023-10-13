package com.example.demo.dao;

import com.example.demo.domain.PostDTO;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface PostDAO {
    @Insert("insert into post (content, title, posted_date, user_id, post_category_id, post_image) values (#{content}, #{title},now(),#{memberId}, #{postCategoryId}, #{postImage})")
    public boolean createPost(PostDTO dto);

    @Select("select post_id, content, title, posted_date, user_id, post_category_id, post_image from post")
    public List<PostDTO> readPost();

    @Update("update post set content = #{content}, title = #{title}, postedDate = now(), user_id = #{memberId}, post_category_id = #{post_category_id}, post_image = #{postImage}"
            + "where post_id = #{postId}")
    public boolean updatePost(PostDTO dto);

    @Delete("delete from post where post_id = #{postId}")
    public boolean deletePost(int post_id);

    @Select("select post_id, content, title, posted_date, user_id, post_category_id, post_image  from post where  ${cn} = #{v}")
    public List<PostDTO> readPostBy(@Param("cn") String columnName, @Param("v") String value);

}