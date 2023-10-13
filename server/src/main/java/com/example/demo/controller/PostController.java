package com.example.demo.controller;

import com.example.demo.domain.PostDTO;
import jakarta.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.springframework.http.ResponseEntity;
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
				.postedDate(LocalDateTime.of(2023, 8, 17, 9, 0))
				.memberId("papamoon0113")
				.postCategoryId("_D")
				.build()
		);
		dtos.add(
			PostDTO.builder()
				.postId(2)
				.content("우헤헤 한글날인데 강의 듣는다")
				.title("우헤헤 오늘 빨간날인데")
				.postedDate(LocalDateTime.of(2023, 10, 9, 9, 0))
				.memberId("sejong king")
				.postCategoryId("_D")
				.build()
		);

		mav.addObject("title", "GET /post");
		mav.addObject("dtos", dtos);
		mav.setViewName("ControllerTest");
		return mav;
	}

	@GetMapping("/post_dto")
	public ResponseEntity<?> readPostDTO(HttpServletRequest req){
		ModelAndView mav = new ModelAndView();
		System.out.println("HTTP 호출 : " + req.getHeader("referer"));
		List<PostDTO> dtos = new ArrayList<>();
		dtos.add(
			PostDTO.builder()
				.postId(1)
				.content("테스트입니다.")
				.title("테스트")
				.postedDate(LocalDateTime.of(2023, 8, 17, 9, 0))
				.memberId("papamoon0113")
				.postCategoryId("_D")
				.build()
		);
		dtos.add(
			PostDTO.builder()
				.postId(2)
				.content("우헤헤 한글날인데 강의 듣는다")
				.title("우헤헤 오늘 빨간날인데")
				.postedDate(LocalDateTime.of(2023, 10, 9, 9, 0))
				.memberId("sejong king")
				.postCategoryId("_D")
				.build()
		);

		return ResponseEntity.ok().body(dtos);
	}
}
