package com.example.demo.dao;

import com.example.demo.domain.PostDTO;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface PostDAO {
    @Insert("insert into post (content, title, posted_date, user_id, post_category_id) values (#{content}, #{title},now(),#{userId}, #{postCategoryId})")
    public boolean createPost(PostDTO dto);

    @Select("select post_id, content, title, posted_date, user_id, post_category_id from post order by posted_date desc;")
    public List<PostDTO> readPost();

    @Select("select post_id, content, title, posted_date, user_id, post_category_id from post order by posted_date desc limit ${start}, ${end};")
    public List<PostDTO> readPostLimit(int start, int end);

//    @Select("select post_id, content, title, posted_date, user_id, post_category_id postCategoryId from post order by ${cn} ${order}")
//    public List<PostDTO> readPostOrderBY(@Param("cn") String columnName, @Param("order") String order);

    @Select("<script>select post_id, content, title, posted_date, user_id, post_category_id from post " +
            "<where>" +
            "<if test='search != null'> content like concat('%',#{search},'%') or title like concat('%',#{search},'%')</if>" +
            "</where>" +
            "<if test='cn != null'> order by ${cn} </if>" +
            "<if test='order != null'> ${order} </if> " +
            "</script>")
    public List<PostDTO> readPostOrderBy(@Param("search") String search, @Param("cn")String columnName,@Param("order") String order);

    @Select("select post_id, content, title, post.posted_date, post.user_id, post_category_id, count(*) recommendCount from post " +
            "join recommendation " +
            "on post.post_id = recommendation.reference_id " +
            "where reference_type = 'p' " +
            "group by post_id " +
            "order by recommendCount limit 5")
    public List<PostDTO> readPostByRecommendation();
    @Select("select post_id, content, title, posted_date, user_id, post_category_id postCategoryId from post where content like '%${search}%' or title like '%${search}%'")
    public List<PostDTO> readPostBySearch(String search);

    @Update("update post set content = #{content}, title = #{title}, posted_date = now(), user_id = #{userId}, post_category_id = #{postCategoryId} "
            + "where post_id = #{postId}")
    public boolean updatePost(PostDTO dto);

    @Delete("delete from post where post_id = #{postId}")
    public boolean deletePost(String postId);

    @Select("select post_id , content, title, posted_date , user_id , post_category_id from post where  ${cn} = #{v}")
    public List<PostDTO> readPostBy(@Param("cn") String columnName, @Param("v") String value);

    @Select("select max(post_id) from post")
    public int readMaxPost();
}