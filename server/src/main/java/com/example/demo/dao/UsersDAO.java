package com.example.demo.dao;

import com.example.demo.domain.UsersDTO;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface UsersDAO {

	@Insert("insert into users (user_id, user_password, "
								+ "user_name, nickname, user_email, user_image_path) "
								+ "values (#{userId}, #{userPassword}, #{userName}, #{nickname}, #{userEmail}, #{userImagePath})")
	public boolean createUser(UsersDTO dto);

	@Select("select user_id, user_password, user_name, nickname, user_email, user_image_path from users")
	public List<UsersDTO> readUser();

	@Update("update users set "
		+ "user_password = #{userPassword}, "
		+ "user_name = #{userName}, nickname = #{nickname}, user_email = #{userEmail}, user_image_path = #{userImagePath}"
		+ " where user_id = #{userId}")
	public boolean updateUserAll(UsersDTO dto);

	@Delete("delete from users where user_id = #{userId}")
	public boolean deleteUser(String userId);

	@Select("select user_id, user_password, user_name, nickname, user_email, user_image_path from users where ${cn} = #{v}")
	public List<UsersDTO> readUserBy(@Param("cn") String columnName, @Param("v") String value);
}
