package com.example.demo.dao;

import com.example.demo.domain.RecommendationDTO;
import com.example.demo.domain.RecommendationResultVO;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

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

    @Select("select count(r1.recommendation_id) as 'recommendation_count', count(r2.recommendation_id) as 'recommendation_check' "
        + "from (select recommendation_id "
                + "from recommendation "
                + "where reference_type = #{referenceType} and reference_id = #{referenceId}) as r1, "
                + "(select recommendation_id "
                + "from recommendation "
                + "where reference_type = #{referenceType} and reference_id = #{referenceId} and user_id = #{userId}) as r2;")
    public RecommendationResultVO readRecommendation(RecommendationDTO dto);

    @Insert("insert into recommendation (reference_type, reference_id, user_id, posted_date) values (#{referenceType}, #{referenceId}, #{userId}, now())")
    public boolean createRecommendation(RecommendationDTO dto);

    @Delete("delete from recommendation where reference_type=#{referenceType} and reference_id = #{referenceId} and user_id = #{userId}")
    public boolean deleteRecommendation(RecommendationDTO dto);

    @Select("select count(recommendation_id) from recommendation where reference_type=#{referenceType} and reference_id = #{referenceId}")
    public int readRecommendationCount(RecommendationDTO dto);

    @Select("select count(*) from recommendation where reference_type=#{referenceType} and reference_id = #{referenceId} and user_id = #{userId}")
    public boolean checkRecommendation(RecommendationDTO dto);
}
