package com.example.demo.dao;

import com.example.demo.domain.UserDTO;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface UserDAO {

	@Insert("insert into users (user_id, user_password, "
								+ "user_name, nickname, email, image) "
								+ "values (#{userId}, #{userPassword}, #{userName}, #{nickname}, #{email}, #{image})")
	public boolean createUser(UserDTO dto);

	@Select("select user_id, user_password, user_name, nickname, email, image from users")
	public List<UserDTO> readUser();

	@Update("update users "
		+ "set user_id = #{userId}, user_password = #{userPassword},"
		+ "user_name = #{userName}, nickname = #{nickname}, email = #{email}, image = #{image}"
		+ "where user_id = #{userId}")
	public boolean updateUser(UserDTO dto);

	@Delete("delete from users where user_id = #{userId}")
	public boolean deleteUser(int userId);

	@Select("select user_id, user_password, user_name, nickname, email, image from users where ${cn} = #{v}")
	public List<UserDTO> readUserBy(@Param("cn") String columnName, @Param("v") String value);
}
