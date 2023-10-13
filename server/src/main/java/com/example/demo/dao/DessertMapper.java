package com.example.demo.dao;

import com.example.demo.domain.DessertDTO;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface DessertMapper {

	@Insert("insert into dessert (dessert_id, dessert_desc) values (#{dessertId}, #{dessertDesc})")
	public boolean createDessert(DessertDTO dto);

	@Select("select dessert_id, dessert_desc from dessert")
	public List<DessertDTO> readDessert();

	@Update("update dessert set dessert_desc = #{dessertDesc} where dessert_id = #{dessertId}")
	public boolean updateDessert(DessertDTO dto);

	@Delete("delete from dessert where dessert_id = #{dessertId}")
	public boolean deleteDessert(int dessertId);

	@Select("select dessert_id, dessert_desc from dessert where ${cn} = #{v}")
	public List<DessertDTO> readDessertBy(@Param("cn") String columnName, @Param("v") String value);
}
