package com.example.demo.dao;

import com.example.demo.domain.PostCategoryDTO;
import com.example.demo.domain.PostDTO;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface PostCategoryDAO {
    @Insert("insert into post_category (post_category_id, post_desc) values (#{postCategoryId}, #{postDesc})")
    public boolean createPostCategory();
    @Select("select post_category_id, post_desc  from post_category")
    public List<PostCategoryDTO>  readPostCategory();
    @Update("update post set post_category_id = #{postCategoryId}, post_desc = #{postDesc}")
    public boolean updatePostCategory(PostCategoryDTO dto);
    @Delete("delete from post_category where post_category_id = #{postCategoryId}")
    public boolean deletePostCategory(int postCategoryId);
    @Select("select post_category_id, post_desc  from post_category where  ${cn} = #{v}")
    public List<PostCategoryDAO> readPostCategoryBy(@Param("cn") String columnName, @Param("v") String value);
}