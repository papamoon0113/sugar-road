package com.example.demo.controller;

import com.example.demo.dao.RecommendationDAO;
import com.example.demo.dao.ReviewDAO;
import com.example.demo.domain.ReviewDTO;
import com.example.demo.domain.ReviewResultVO;
import com.example.demo.util.ImageUtil;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ReviewController {

	@Autowired
	ReviewDAO reviewDAO;

	@Autowired
	ImageUtil imageUtil;
	@Autowired
	RecommendationDAO recommendationDAO;

	@GetMapping("/review")
	@ResponseBody
	public List<ReviewResultVO> readReviewByStoreId(@RequestParam("storeId") int storeId) {
		return reviewDAO.readReviewOfStore(storeId);
	}

	@GetMapping("/review/detail")
	public ModelAndView detailReview(@RequestParam("id") int id, HttpSession session) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("reviewDTO", reviewDAO.readReviewBy("review_id", String.valueOf(id)).get(0));
		mav.setViewName("review/detail.html");
		return mav;
	}

	@PostMapping("/review/write")
	public String writeReview(ReviewDTO dto) {
		System.out.println(dto.toString());
		MultipartFile imageFile = dto.getUploadImage();
		if (!imageFile.isEmpty()){
			String reviewImagePath = imageUtil.writeImage(imageFile);
			dto.setReviewImagePath(reviewImagePath);
		}
		reviewDAO.createReview(dto);
		return "redirect:/store/detail?storeId=" + dto.getStoreId();
	}

	@GetMapping("/review/write")
	public ModelAndView writeReview(@RequestParam("storeId") int storeId) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("storeId", storeId);
		mav.setViewName("review/write");
		return mav;
	}

	@GetMapping("/review/edit")
	public ModelAndView editReview(@RequestParam("id") int id) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("reviewDTO", reviewDAO.readReviewBy("review_id", String.valueOf(id)).get(0));
		mav.setViewName("review/edit");
		return mav;
	}

	@PostMapping("/review/edit")
	@ResponseBody
	public Boolean editReview(ReviewDTO dto) {
		System.out.println(dto.toString());
		MultipartFile imageFile = dto.getUploadImage();
		String reviewImagePath = imageUtil.writeImage(imageFile);
		dto.setReviewImagePath(reviewImagePath);
		return reviewDAO.updateReview(dto);
	}

	@GetMapping("/review/delete")
	@ResponseBody
	public Boolean deleteReview(@RequestParam("id") int id) {
		return reviewDAO.deleteReview(id);
	}

}
