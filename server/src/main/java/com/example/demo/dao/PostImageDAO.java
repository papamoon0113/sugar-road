package com.example.demo.dao;

import com.example.demo.domain.PostImageDTO;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface PostImageDAO {
    @Insert("insert into post_image (post_image_path, post_id) values (#{postImagePath}, #{postId})")
    public boolean createPostImage(PostImageDTO dto);
    @Select("select post_image_path from post_image where post_id = #{id}")
    public List<String> readPostImage(int id);
    @Update("update post_image set post_image_path = #{postImagePath}")
    public boolean updatePostImage(PostImageDTO dto);
    @Delete("delete from post_image where ${cn} = #{v}")
    public boolean deletePostImage(@Param("cn") String columnName, @Param("v") String value);

}
