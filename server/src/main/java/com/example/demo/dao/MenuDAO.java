package com.example.demo.dao;

import com.example.demo.domain.MenuDTO;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface MenuDAO {

	@Insert("insert into menu (store_id, "
								+ "menu_name, price, menu_desc, dessert_id, image) "
								+ "values (#{storeId}, #{menuName}, #{price}, #{menuDesc}, #{dessertId}, #{image})")
	public boolean createMenu(MenuDTO dto);

	@Select("select menu_id, store_id, menu_name, price, menu_desc, dessert_id, image from menu")
	public List<MenuDTO> readMenu();

	@Update("update menu "
		+ "set store_id = #{storeId},"
		+ "menu_name = #{menuName}, price = #{price}, menu_desc = #{menuDesc}, dessert_id = #{dessertId}, image = #{image}"
		+ "where menu_id = #{menuId}")
	public boolean updateMenu(MenuDTO dto);

	@Delete("delete from menu where menu_id = #{menuId}")
	public boolean deleteMenu(int menuId);

	@Select("select menu_id, store_id, menu_name, price, menu_desc, dessert_id, image from menu where ${cn} = #{v}")
	public List<MenuDTO> readMenuBy(@Param("cn") String columnName, @Param("v") String value);
}
