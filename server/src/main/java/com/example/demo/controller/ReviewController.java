package com.example.demo.controller;

import com.example.demo.dao.RecommendationDAO;
import com.example.demo.dao.ReviewDAO;
import com.example.demo.domain.ReviewDTO;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ReviewController {

	@Autowired
	ReviewDAO reviewDAO;

	@Autowired
	RecommendationDAO recommendationDAO;

	@GetMapping("/review")
	@ResponseBody
	public List<ReviewDTO> readReviewByStoreId(@RequestParam("store_id") int storeId) {
		return reviewDAO.readReviewBy("store_id", String.valueOf(storeId));
	}

	@GetMapping("/review/detail")
	public ModelAndView detailReview(@RequestParam("id") int id) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("dtos", reviewDAO.readReviewBy("review_id", String.valueOf(id)));
		mav.setViewName("review/detail.html");
		return mav;
	}

	@PostMapping("/review/write")
	@ResponseBody
	public Boolean writeReview(ReviewDTO dto) {
		System.out.println(dto.toString());
		return reviewDAO.createReview(dto);
	}

	@GetMapping("/review/write")
	public ModelAndView writeReview() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("review/write.html");
		return mav;
	}

	@GetMapping("/review/edit")
	public ModelAndView editReview(@RequestParam("id") int id) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("reviewDTO", reviewDAO.readReviewBy("review_id", String.valueOf(id)).get(0));
		mav.setViewName("review/edit.html");
		return mav;
	}

	@PostMapping("/review/edit")
	@ResponseBody
	public Boolean editReview(ReviewDTO dto) {
		return reviewDAO.updateReview(dto);
	}

	@GetMapping("/review/delete")
	@ResponseBody
	public Boolean deleteReview(@RequestParam("id") int id) {
		return reviewDAO.deleteReview(id);
	}

}
