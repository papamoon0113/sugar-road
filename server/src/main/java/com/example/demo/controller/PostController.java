package com.example.demo.controller;

import com.example.demo.domain.PostDTO;
import jakarta.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class PostController {

	@GetMapping("/post")
	public ModelAndView readPost(HttpServletRequest req){
		ModelAndView mav = new ModelAndView();

		List<PostDTO> dtos = new ArrayList<>();
		dtos.add(
			PostDTO.builder()
				.postId(1)
				.content("테스트입니다.")
				.title("테스트")
				.postDate(LocalDateTime.of(2023, 8, 17, 9, 0))
				.customerId("papamoon0113")
				.postCategoryId("_D")
				.build()
		);

		mav.addObject("title", "GET /post");
		mav.addObject("dtos", dtos);
		mav.setViewName("ControllerTest");
		return mav;
	}
}
