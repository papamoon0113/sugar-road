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

	@GetMapping("/recommendation/readCount")
	public int readRecommendationCount(RecommendationDTO dto){
		return recommendationDAO.readRecommendationCount(dto);
	}

	@PostMapping("/recommendation/write")
	@ResponseBody
	public Boolean writeRecommendation(RecommendationDTO dto){
		System.out.println(dto.toString());
		return recommendationDAO.createRecommendation(dto);
	}

	@GetMapping("/recommendation/delete")
	@ResponseBody
	public Boolean deleteRecommendation(@RequestParam("id") int id){
		return recommendationDAO.deleteRecommendation(id);
	}

}
