package com.example.demo.controller;

import com.example.demo.dao.PostDAO;
import com.example.demo.dao.ReviewDAO;
import com.example.demo.dao.StoreDAO;
import com.example.demo.domain.PostDTO;
import com.example.demo.domain.ReviewDTO;
import com.example.demo.domain.StoreDTO;
import jakarta.servlet.http.HttpServletRequest;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/post")
public class PostController {
	@Autowired
	PostDAO postDAO;
	@Autowired
	ReviewDAO reviewDAO;
	@Autowired
	StoreDAO storeDAO;

	//byte to img(String)
	public String byteToString(PostDTO dto){
		byte[] byteEnc64 = Base64.getEncoder().encode(dto.getPostImage());
		String imgStr = null;
		try{
			imgStr = new String(byteEnc64, "UTF-8");
			dto.setImg(imgStr);
			System.out.println("실행");
		}catch(Exception e){
			System.out.println(e);
		}
		return imgStr;
	}
	@ModelAttribute("path")
	String getRequestServletPath(HttpServletRequest request){
		return request.getServletPath();
	}
	@GetMapping("")
	public ModelAndView readPost(@RequestParam(required = false) String search,
								 @RequestParam(required = false) String order){
		ModelAndView mav = new ModelAndView();
		//byte to img
		List<PostDTO> list = postDAO.readPost();
		for(PostDTO l: list){
			if(l.getPostImage()!=null)
				l.setImg(byteToString(l));
		}

		mav.addObject("list", list);

		mav.setViewName("post/index");
		return mav;
	}

	@GetMapping("/write")
	public void writePage(){
	}
	@PostMapping("/write")//userId session에서 받기
	public ModelAndView insertPost(PostDTO dto, MultipartFile image){
		ModelAndView mav = new ModelAndView();
		byte[] content = null;
		if(!image.isEmpty()) {
			try {
				content = image.getBytes();
				dto.setPostImage(content);
			} catch (IOException e) {
				System.out.println(e);
			}
		}
		if(postDAO.createPost(dto)){
			System.out.println("성공");
		}else{
			System.out.println("실패");
		}
		mav.addObject("dto", dto);
		mav.setViewName("post/detail");
		return mav;
	}
	@GetMapping("/detail")
	public ModelAndView readPostById(String id){
		ModelAndView mav = new ModelAndView();
		PostDTO dto = postDAO.readPostBy("post_id", id).get(0);
		System.out.println(dto.getPostId());
		if(dto.getPostImage()!=null)
			dto.setImg(byteToString(dto));

		mav.addObject("dto", dto);
		mav.setViewName("post/detail");
		return mav;
	}
	@PostMapping("/edit")
	public ModelAndView updatePost(PostDTO dto){
		ModelAndView mav = new ModelAndView();
		if(postDAO.updatePost(dto)){
			mav.addObject("msg", "변경되었습니다.");
			mav.addObject("list", postDAO.readPostBy("post_id", Integer.toString(dto.getPostId())));
		}else{
			mav.addObject("msg", "변경을 실패했습니다..");
		}
		mav.setViewName("post/detail");
		return mav;
	}
	@GetMapping("/delete")
	public ModelAndView deletePost(int id){
		ModelAndView mav = new ModelAndView();
		if(postDAO.deletePost(id)){
			mav.addObject("msg", "삭제되었습니다.");
			mav.addObject("list", postDAO.readPost());
		}else{
			mav.addObject("msg", "삭제에 실패했습니다.");
		}
		mav.setViewName("post/index");
		return mav;
	}



}
