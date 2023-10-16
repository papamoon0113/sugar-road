package com.example.demo.dao;

import com.example.demo.domain.PostDTO;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface PostDAO {
    @Insert("insert into post (content, title, posted_date, user_id, post_category_id) values (#{content}, #{title},now(),#{userId}, #{postCategoryId})")
    public boolean createPost(PostDTO dto);

    @Select("select post_id, content, title, posted_date, user_id, post_category_id postCategoryId from post")
    public List<PostDTO> readPost();

    @Select("select post_id, content, title, posted_date, user_id, post_category_id postCategoryId from post order by ${cn} ${order}")
    public List<PostDTO> readPostOrderBY(@Param("cn") String columnName, @Param("order") String order);

    @Update("update post set content = #{content}, title = #{title}, posted_date = now(), user_id = #{userId}, post_category_id = #{postCategoryId}"
            + "where post_id = #{postId}")
    public boolean updatePost(PostDTO dto);

    @Delete("delete from post where post_id = #{postId}")
    public boolean deletePost(String postId);

    @Select("select post_id , content, title, posted_date , user_id , post_category_id from post where  ${cn} = #{v}")
    public List<PostDTO> readPostBy(@Param("cn") String columnName, @Param("v") String value);

    @Select("select max(post_id) from post")
    public int readMaxPost();
}