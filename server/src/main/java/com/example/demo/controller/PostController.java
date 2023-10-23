package com.example.demo.controller;

import com.example.demo.dao.*;
import com.example.demo.domain.*;
import com.example.demo.util.ImageUtil;
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

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
    @Autowired
    RecommendationDAO recommendationDAO;
    @Autowired
    ImageUtil imageUtil;

    boolean checkLongin(HttpSession session) {
        return session.getAttribute("nowLogin") != null;//
    }

    //이미지 저장 method
    List<PostImageDTO> saveImage(PostDTO dto) {
        System.out.println("saveImage 실행");
//        String path = "/images/post";
//        String abPath = "C:/git/sugar-road/server/src/main/resources/static/images/post";
//        File isDir = new File(abPath);
//        if (!isDir.isDirectory()) {
//            isDir.mkdirs();
//        }
        List<PostImageDTO> imageList = new ArrayList<>();
        for (MultipartFile mfile : dto.getUploadImages()) {
            String postImagePath = imageUtil.writeImage(mfile);
            PostImageDTO postImageDTO = PostImageDTO.builder().postImagePath(postImagePath).build();
            imageList.add(postImageDTO);
//            postImageDTO.setPostImagePath(path + "/" + fileName);
//            String uuid = UUID.randomUUID().toString();
//            String fileName = uuid + mfile.getOriginalFilename();
//            PostImageDTO postImageDTO = new PostImageDTO();
//            try {
//                File f = new File(abPath + "/" + fileName);
//                mfile.transferTo(f);
//                postImageDTO.setPostImagePath(path + "/" + fileName);
//                imageList.add(postImageDTO);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
        }
        return imageList;
    }

    @ModelAttribute("path")
    String getRequestServletPath(HttpServletRequest request) {
        return request.getServletPath();
    }

    @GetMapping("")
    public ModelAndView readPost(@RequestParam(required = false) String search,
                                 @RequestParam(required = false) String cn,
                                 @RequestParam(required = false) String order) {

        ModelAndView mav = new ModelAndView();
        System.out.println("index 실행"+search+cn);
        List<PostDTO> list = null;
        if(cn != null && cn.equals("recommendation")) {
             list = postDAO.readPostByRecommendation();
        }
        else if(cn != null && cn.equals("posted_date")) {
            order = "desc";
            list = postDAO.readPostOrderBy(search, cn, order);
        }
        else {
            list = postDAO.readPostOrderBy(search, cn, order);
        }

        for (PostDTO p : list) {//이미지 및 댓글 수 처리
            int id = p.getPostId();
            RecommendationDTO recommendationDTO = RecommendationDTO.builder().referenceType("P").referenceId(id).build();
            p.setRecommendCount(recommendationDAO.readRecommendationCount(recommendationDTO));
            System.out.println("카운트:" + p.getRecommendCount());
            p.setCommentCount(postCommentDAO.readPostCommentCount(id));
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
    public String writePage(HttpSession session, Model model) {
        if (!checkLongin(session)) {
            model.addAttribute("msg", "로그인이 필요합니다");
            model.addAttribute("url", "/users/login");
            return "alert";
//            return "redirect:/users/login.html";
        }

        return "post/write";
    }

    @PostMapping("/write")//userId session에서 받기
    public String insertPost(PostDTO dto, HttpSession session) {
        System.out.println("파일 개수:" + dto.getUploadImages().length);

        List<PostImageDTO> imageList = new ArrayList<>();

        if (!dto.getUploadImages()[0].isEmpty()) { //파일 유무 검사
            imageList = saveImage(dto);//이미지 저장 및 dto 경로 저장
        } else {
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
    public ModelAndView updatePost(String id, HttpSession session) {
        ModelAndView mav = new ModelAndView();
        if (!checkLongin(session)) {
            mav.setViewName("redirect:/users/login.html");
            return mav;
        }

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
        if (dto.getPostImage() != null) {
            System.out.println("실행됨");
            for (String s : dto.getPostImage()) {
                System.out.println("src:" + s);
                if (postImageDAO.deletePostImage("post_image_path", s)) {
                    System.out.println("삭제 완료");
                } else {
                    System.out.println("삭제 안됨");
                }
            }
        } else {
            System.out.println("이미지 수정 실행안됨");
        }
        //이미지 저장
        List<PostImageDTO> imageList = new ArrayList<>();

        if (!dto.getUploadImages()[0].isEmpty()) {//파일 유무 검사
            imageList = saveImage(dto);//이미지 저장
        } else {
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
    public String deletePost(String id, HttpSession session) {
//        if (!checkLongin(session)) {
//            System.out.println("로그인 필요");
//            return "redirect:/users/login.html";
//        }
        if (postDAO.deletePost(id)) {
            System.out.println("성공");
        } else {
            System.out.println("실패");
        }
        return "redirect:/post";
    }


}
