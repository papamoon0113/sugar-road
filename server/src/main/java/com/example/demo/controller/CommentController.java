package com.example.demo.controller;

import com.example.demo.dao.PostCommentDAO;
import com.example.demo.dao.ReviewCommentDAO;
import com.example.demo.domain.PostCommentDTO;
import com.example.demo.domain.ReviewCommentDTO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class CommentController {
	@Autowired
	PostCommentDAO postCommentDAO;

	@Autowired
	ReviewCommentDAO reviewCommentDAO;

	@PostMapping("/comment/post/write")
	@ResponseBody
	public boolean writePostComment(@RequestBody PostCommentDTO dto, HttpSession session){
		dto.setUserId(session.getAttribute("nowLogin")!=null?session.getAttribute("nowLogin").toString():null);
		return postCommentDAO.createPostComment(dto);
	}

	@PostMapping("/comment/post/child/write")
	@ResponseBody
	public boolean writePostCommentChild(@RequestBody PostCommentDTO dto, HttpSession session){
		dto.setUserId(session.getAttribute("nowLogin")!=null?session.getAttribute("nowLogin").toString():null);
		return postCommentDAO.createPostCommentChild(dto);
	}

	@GetMapping("/comment/post")
	@ResponseBody
	public List<PostCommentDTO> readPostComment(@RequestParam("id") int postId, @RequestParam("startPoint") int startPoint, @RequestParam("count") int count){
		return postCommentDAO.readPostCommentByLimit("post_id", String.valueOf(postId), startPoint, count);
	}

	@GetMapping("/comment/post/delete")
	@ResponseBody
	public boolean deletePostComment(@RequestParam("id") int id){
		return postCommentDAO.deletePostComment(id);
	}

	@PostMapping("/comment/post/edit")
	@ResponseBody
	public boolean editPostComment(@RequestBody PostCommentDTO dto, HttpSession session){
		dto.setUserId(session.getAttribute("nowLogin")!=null?session.getAttribute("nowLogin").toString():null);
		return postCommentDAO.updatePostComment(dto);
	}

	@PostMapping("/comment/review/write")
	@ResponseBody
	public boolean writeReviewComment(@RequestBody ReviewCommentDTO dto, HttpSession session){
		dto.setUserId(session.getAttribute("nowLogin")!=null?session.getAttribute("nowLogin").toString():null);
		return reviewCommentDAO.createReviewComment(dto);
	}

	@PostMapping("/comment/review/child/write")
	@ResponseBody
	public boolean writeReviewCommentChild(@RequestBody ReviewCommentDTO dto, HttpSession session){
		dto.setUserId(session.getAttribute("nowLogin")!=null?session.getAttribute("nowLogin").toString():null);
		return reviewCommentDAO.createReviewCommentChild(dto);
	}

	@GetMapping("/comment/review")
	@ResponseBody
	public List<ReviewCommentDTO> readReviewComment(@RequestParam("id") int reviewId, @RequestParam("startPoint") int startPoint, @RequestParam("count") int count){
		System.out.println(String.format("%d, %d", startPoint,count));
		return reviewCommentDAO.readReviewCommentByLimit("review_id", String.valueOf(reviewId), startPoint, count);
	}

	@GetMapping("/comment/review/delete")
	@ResponseBody
	public boolean deleteReviewComment(@RequestParam("id") int id){
		return reviewCommentDAO.deleteReviewComment(id);
	}

	@PostMapping("/comment/review/edit")
	@ResponseBody
	public boolean editReviewComment(@RequestBody ReviewCommentDTO dto, HttpSession session){
		dto.setUserId(session.getAttribute("nowLogin")!=null?session.getAttribute("nowLogin").toString():null);
		return reviewCommentDAO.updateReviewComment(dto);
	}
}
