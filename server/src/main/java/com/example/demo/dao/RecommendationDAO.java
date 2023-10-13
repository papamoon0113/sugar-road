package com.example.demo.dao;

import com.example.demo.domain.RecommendationDTO;
import org.apache.ibatis.annotations.*;

import java.util.List;
//CRD
//
//
//
//
@Mapper
public interface RecommendationDAO {
    @Select("select recommendation_id, type, reference_id, customer_id from recommendation")
    public List<RecommendationDTO> selectRecommendation();
    @Select("select count(*) from recommendation where type=#{type} and reference_id = #{referenceId}")
    public int readRecommendationCount(@Param("type")String type, @Param("referenceId") int referenceId);
    @Insert("insert into recommendation (type, reference_id, customer_id) values (#{type}, #{referenceId}, #{customerId})")
    public boolean createRecommendation(RecommendationDTO dto);

    @Delete("delete from recommendation where recommendation_id = #{id}")
    public boolean delete(int id);

}