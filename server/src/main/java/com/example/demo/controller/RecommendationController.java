package com.example.demo.controller;

import com.example.demo.dao.RecommendationDAO;
import com.example.demo.domain.RecommendationDTO;
import com.example.demo.domain.RecommendationResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class RecommendationController {

	@Autowired
	RecommendationDAO recommendationDAO;

	@GetMapping("/recommendation/read")
	@ResponseBody
	public RecommendationResultVO readRecommendation(RecommendationDTO dto){
		return recommendationDAO.readRecommendation(dto);
	}

	@GetMapping("/recommendation/count")
	@ResponseBody
	public int readRecommendationCount(RecommendationDTO dto){
		return recommendationDAO.readRecommendationCount(dto);
	}

	@GetMapping("/recommendation/check")
	@ResponseBody
	public RecommendationResultVO checkRecommendation(RecommendationDTO dto){
		return RecommendationResultVO.builder()
			.count(recommendationDAO.readRecommendationCount(dto))
			.check(recommendationDAO.checkRecommendation(dto))
			.build();
	}

	@GetMapping("/recommendation/write")
	@ResponseBody
	public RecommendationResultVO writeRecommendation(RecommendationDTO dto){
		System.out.println(dto.toString());
		return RecommendationResultVO.builder()
			.check(recommendationDAO.createRecommendation(dto))
			.count(recommendationDAO.readRecommendationCount(dto))
			.build();
	}

	@GetMapping("/recommendation/delete")
	@ResponseBody
	public RecommendationResultVO deleteRecommendation(RecommendationDTO dto){
		System.out.println(dto.toString());
		return RecommendationResultVO.builder()
			.check(!recommendationDAO.deleteRecommendation(dto))
			.count(recommendationDAO.readRecommendationCount(dto))
			.build();
	}

}
