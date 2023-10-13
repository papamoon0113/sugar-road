package com.example.demo.controller;

import com.example.demo.dao.PostDAO;
import com.example.demo.dao.ReviewDAO;
import com.example.demo.dao.StoreDAO;
import com.example.demo.domain.PostDTO;
import com.example.demo.domain.ReviewDTO;
import com.example.demo.domain.StoreDTO;
import jakarta.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class PostController {
	@Autowired
	PostDAO postDAO;
	@Autowired
	ReviewDAO reviewDAO;
	@Autowired
	StoreDAO storeDAO;
	@GetMapping("/post")
	public ModelAndView readPost(@RequestParam(required = false) String search,
								 @RequestParam(required = false) String order){
		ModelAndView mav = new ModelAndView();

		List<PostDTO> list = postDAO.readPost();
		System.out.println(list);
		mav.addObject("list", list);

		mav.setViewName("postView");
		return mav;
	}
	@PostMapping("/post/write")
	public String insertPost(PostDTO dto){
		postDAO.createPost(dto);
		return "postView";
	}

}
