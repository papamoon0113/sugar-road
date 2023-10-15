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
								+ "user_name, nickname, user_email, image) "
								+ "values (#{userId}, #{userPassword}, #{userName}, #{nickname}, #{userEmail}, #{image})")
	public boolean createUser(UsersDTO dto);

	@Select("select user_id, user_password, user_name, nickname, user_email, image from users")
	public List<UsersDTO> readUser();

	@Update("update users set "
		+ "user_password = #{userPassword}, "
		+ "user_name = #{userName}, nickname = #{nickname}, user_email = #{userEmail}, image = #{image}"
		+ " where user_id = #{userId}")
	public boolean updateUserAll(UsersDTO dto);

	@Update("update users set "
			+ "user_password = #{up}"
			+ " where user_id = #{id}")
	public boolean updateUserPassword(@Param("up") String userPassword, @Param("id") String userId);

	@Update("update users set "
			+ "user_name = #{un}"
			+ " where user_id = #{id}")
	public boolean updateUserName(@Param("un") String userName, @Param("id") String userId);

	@Update("update users set "
			+ "nickname = #{un}"
			+ " where user_id = #{id}")
	public boolean updateUserNick(@Param("un") String userNick, @Param("id") String userId);

	@Update("update users set "
			+ "user_email = #{em}"
			+ " where user_id = #{id}")
	public boolean updateUserEmail(@Param("em") String email, @Param("id") String userId);

	@Delete("delete from users where user_id = #{userId}")
	public boolean deleteUser(String userId);

	@Select("select user_id, user_password, user_name, nickname, user_email, image from users where ${cn} = #{v}")
	public List<UsersDTO> readUserBy(@Param("cn") String columnName, @Param("v") String value);
}
