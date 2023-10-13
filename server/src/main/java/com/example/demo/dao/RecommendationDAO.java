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
    @Select("select recommendation_id, reference_type, reference_id, user_id, posted_date from recommendation")
    public List<RecommendationDTO> selectRecommendation();
    @Select("select count(*) from recommendation where reference_type=#{type} and reference_id = #{referenceId}")
    public int readRecommendationCount(@Param("type")String type, @Param("referenceId") int referenceId);
    @Insert("insert into recommendation (reference_type, reference_id, user_id, posted_date) values (#{type}, #{referenceId}, #{customerId}, #{postedDate})")
    public boolean createRecommendation(RecommendationDTO dto);

    @Delete("delete from recommendation where recommendation_id = #{id}")
    public boolean delete(int id);

}
