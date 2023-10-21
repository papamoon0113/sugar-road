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

	@Insert("insert into menu (store_id, menu_name,  menu_image_path) "
								+ "values (#{storeId}, #{menuName}, #{menuImagePath})")
	public boolean createMenu(MenuDTO dto);

	@Select("select menu_id, store_id, menu_name, price, menu_desc,  image from menu")
	public List<MenuDTO> readMenu();

	// store_id 추가??!!
	@Update("update menu "
		+ "set menu_name = #{menuName}, price = #{price}, menu_desc = #{menuDesc}, menu_image_path = #{menuImagePath}"
		+ "where menu_id = #{menuId}")
	public boolean updateMenu(MenuDTO dto);

	@Delete("delete from menu where menu_id = #{menuId}")
	public boolean deleteMenu(int menuId);

	@Select("select menu_id, menu_name, price, menu_desc, menu_image_path from menu where store_id = #{storeId}")
	public List<MenuDTO> readMenuBy(int storeId);

	@Select("select menu_id, menu_name, price, menu_desc, menu_image_path from menu where menu_id = #{menuId}")
	public MenuDTO readMenuById(int menuId);
}
