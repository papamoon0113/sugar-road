package com.example.demo.controller;

import com.example.demo.dao.*;
import com.example.demo.domain.*;
import jakarta.servlet.http.HttpServletRequest;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
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
    PostImageDAO postImageDAO;
    @Autowired
    PostCommentDAO postCommentDAO;

    @ModelAttribute("path")
    String getRequestServletPath(HttpServletRequest request) {
        return request.getServletPath();
    }

    @GetMapping("")
    public ModelAndView readPost(@RequestParam(required = false) String search,
                                 @RequestParam(required = false) String order) {
        ModelAndView mav = new ModelAndView();
        System.out.println("index 실행");
        //byte to img
        List<PostDTO> list = postDAO.readPost();
        //image경로 받기
        for (PostDTO p : list) {
            int id = p.getPostId();
            List<String> iList = postImageDAO.readPostImage(id);
            if (iList != null) {
                p.setPostImage(iList.toArray(new String[0]));
            }
        }

        mav.addObject("list", list);

        mav.setViewName("post/index");
        return mav;
    }

    @GetMapping("/write")
    public void writePage() {
    }

    @PostMapping("/write")//userId session에서 받기
    public String insertPost(PostDTO dto) {
        System.out.println("파일 개수:"+dto.getUploadImages().length);
        String path = "/images/post";
        List<PostImageDTO> imageList = new ArrayList<>();

        if(!dto.getUploadImages()[0].isEmpty()) {//파일 유무 검사
            System.out.println("실행됨");
            for (MultipartFile mfile : dto.getUploadImages()) {
                String uuid = UUID.randomUUID().toString();
                String fileName = uuid + mfile.getOriginalFilename();
                System.out.println();
                PostImageDTO postImageDTO = new PostImageDTO();
                try {
                    File f = new File("C:/git/sugar-road/server/src/main/resources/static/images/post/" + fileName);
                    mfile.transferTo(f);
                    postImageDTO.setPostImagePath(path + "/" + fileName);
                    imageList.add(postImageDTO);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        else{
            System.out.println("실행 안됨");
        }
        if (postDAO.createPost(dto)) {
            System.out.println("성공");
            int id = postDAO.readMaxPost();
            for (PostImageDTO i : imageList) {
                i.setPostId(id);
                if (postImageDAO.createPostImage(i)) {
                    System.out.println("PostImage 저장");
                }
            }
            return "redirect:/post/detail?id=" + id;
        } else {
            System.out.println("실패");
            return "redirect:/post";
        }

    }

    @GetMapping("/detail")
    public ModelAndView readPostById(String id) {
        ModelAndView mav = new ModelAndView();
        PostDTO dto = postDAO.readPostBy("post_id", id).get(0);
        System.out.println(dto.getPostId());
        int postId = dto.getPostId();
		//이미지 정렬
        List<String> iList = postImageDAO.readPostImage(postId);
        if (iList != null) {
            dto.setPostImage(iList.toArray(new String[0]));
        }

        mav.addObject("dto", dto);
        mav.setViewName("post/detail");
        return mav;
    }

    @GetMapping("/edit")
    public ModelAndView updatePost(String id) {
        ModelAndView mav = new ModelAndView();
        PostDTO dto = postDAO.readPostBy("post_id", id).get(0);
        List<String> iList = postImageDAO.readPostImage(Integer.parseInt(id));
        if (iList != null) {
            dto.setPostImage(iList.toArray(new String[0]));
        }

        mav.addObject("dto", dto);
        mav.setViewName("/post/write");

        return mav;
    }

    @PostMapping("/edit")
    public String updatePost(PostDTO dto) {
        //이미지 수정
        if(dto.getPostImage() != null) {
            System.out.println("실행됨");
            for (String s : dto.getPostImage()) {
                System.out.println("src:" + s);
                if (postImageDAO.deletePostImage("post_image_path", s)) {
                    System.out.println("삭제 완료");
                } else {
                    System.out.println("삭제 안됨");
                }
            }
        }else{
            System.out.println("실행안됨");
        }
        //이미지 저장
        String path = "/images/post";
        List<PostImageDTO> imageList = new ArrayList<>();

        if(!dto.getUploadImages()[0].isEmpty()) {//파일 유무 검사
            System.out.println("실행됨");
            for (MultipartFile mfile : dto.getUploadImages()) {
                String uuid = UUID.randomUUID().toString();
                String fileName = uuid + mfile.getOriginalFilename();
                System.out.println();
                PostImageDTO postImageDTO = new PostImageDTO();
                try {
                    File f = new File("C:/git/sugar-road/server/src/main/resources/static/images/post/" + fileName);
                    mfile.transferTo(f);
                    postImageDTO.setPostImagePath(path + "/" + fileName);
                    imageList.add(postImageDTO);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        else{
            System.out.println("실행 안됨");
        }
        if (postDAO.updatePost(dto)) {
            System.out.println("성공");
            int id = dto.getPostId();
            for (PostImageDTO i : imageList) {
                i.setPostId(id);
                if (postImageDAO.createPostImage(i)) {
                    System.out.println("PostImage 저장");
                }
            }
        } else {
            System.out.println("실패");
        }
        return "redirect:/post/detail?id=" + dto.getPostId();
    }

    @GetMapping("/delete")
    public String deletePost(String id) {
        ModelAndView mav = new ModelAndView();
        if (postDAO.deletePost(id)) {
            System.out.println("성공");
        } else {
            System.out.println("실패");
        }
        return "redirect:/post";
    }


}
