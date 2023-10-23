package com.example.demo.controller;

import com.example.demo.dao.*;
import com.example.demo.domain.PostDTO;
import com.example.demo.domain.RecommendationDTO;
import com.example.demo.domain.ReviewDTO;
import com.example.demo.domain.StoreDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/search")
public class SearchController {
    @Autowired
    PostDAO postDAO;
    @Autowired
    PostCommentDAO postCommentDAO;
    @Autowired
    RecommendationDAO recommendationDAO;
    @Autowired
    StoreDAO storeDAO;
    @Autowired
    ReviewDAO reviewDAO;

    @GetMapping("")
    public ModelAndView index() {
        ModelAndView mav = new ModelAndView();
        mav.addObject("nameList", storeDAO.readStoreName());
        mav.setViewName("search/index");
        return mav;
    }

    @GetMapping(value = "", params = {"search"})
    public ModelAndView search(String search) {
        ModelAndView mav = new ModelAndView();
        List<String> nameList = storeDAO.readStoreName();
        mav.addObject("nameList", nameList);
        List<PostDTO> postList = postDAO.readPostBySearch(search);

        if (!postList.isEmpty()) {
            for(PostDTO p:postList){
                p.setCommentCount(postCommentDAO.readPostCommentCount(p.getPostId()));
                p.setRecommendCount(recommendationDAO.readRecommendationCount(RecommendationDTO.builder().referenceType("p").referenceId(p.getPostId()).build()));
            }
            mav.addObject("postList", postList);
        }

        List<ReviewDTO> reviewList = reviewDAO.readReviewSearch(search);
        if (!reviewList.isEmpty())
            mav.addObject("reviewList", reviewList);

        List<StoreDTO> storeList = storeDAO.readStoreSearch(search);
        if (!storeList.isEmpty())
            mav.addObject("storeList", storeList);
        if (postList.isEmpty() && reviewList.isEmpty() && storeList.isEmpty())
            mav.addObject("noResult", "검색 결과 없음");
        else {
            mav.addObject("search", search);
        }
        mav.setViewName("search/index");

        return mav;
    }
}
