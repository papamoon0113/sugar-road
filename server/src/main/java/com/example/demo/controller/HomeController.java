package com.example.demo.controller;


import com.example.demo.dao.PostDAO;
import com.example.demo.dao.PostImageDAO;
import com.example.demo.dao.RecommendationDAO;
import com.example.demo.dao.StoreDAO;
import com.example.demo.domain.PostDTO;
import com.example.demo.domain.RecommendationDTO;

import com.example.demo.domain.StoreDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class HomeController {
    @Autowired
    StoreDAO dao;

    @Autowired
    PostDAO postDAO;
    @Autowired
    PostImageDAO postImageDAO;
    @Autowired
    RecommendationDAO recommendationDAO;

    ModelAndView mav = new ModelAndView();
    @GetMapping("/home")
    public ModelAndView main(){

        List<StoreDTO> list = dao.readStore();
        for (StoreDTO s : list) {//이미지 및 댓글 수 처리
            int id = s.getStoreId();
            RecommendationDTO recommendationDTO = RecommendationDTO.builder().referenceType("S").referenceId(id).build();
            s.setRecommendCount(recommendationDAO.readRecommendationCount(recommendationDTO));
            System.out.println("추천수"+s.getRecommendCount());
        }
       mav.addObject("list", list);


       List<PostDTO> postList = postDAO.readPostByRecommendation();
       for(PostDTO p : postList){
           int id = p.getPostId();
           List<String> iList = postImageDAO.readPostImage(id);
           if (iList != null) {
               p.setPostImage(iList.toArray(new String[0]));
           }
       }

       mav.addObject("postList", postList);


        mav.setViewName("home/home");
        return mav;
    }
}

