package com.example.demo.controller;

<<<<<<< HEAD
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class SearchController {
    @RequestMapping("/search")
    public ModelAndView searching(String searchWord) {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("/search/index");

        //받아온 키워드로 조회
        searchWord = "";

        //리뷰(review) 검색 결과

        //가게(store) 검색 결과

        //게시글 검색 결과

        String searchResult = "대충 검색결과";

        if (searchResult == null) { //검색결과가 없으면
            mav.addObject("msg", "검색 결과가 없습니다");
        }
=======
import com.example.demo.dao.PostDAO;
import com.example.demo.dao.ReviewDAO;
import com.example.demo.dao.StoreDAO;
import com.example.demo.domain.PostDTO;
import com.example.demo.domain.ReviewDTO;
import com.example.demo.domain.StoreDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
    StoreDAO storeDAO;
    @Autowired
    ReviewDAO reviewDAO;

    @GetMapping("")
    public String index(){
        return "search/index";
    }
    @GetMapping(value = "", params = {"search"})
    public ModelAndView search(String search){
        ModelAndView mav = new ModelAndView();
        //자동완성
        List<String> nameList = storeDAO.readStoreName();
        mav.addObject("nameList", nameList);
        List<PostDTO> postList= postDAO.readPostBySearch(search);
        if(!postList.isEmpty())
            mav.addObject("postList", postList);
        
        List<ReviewDTO> reviewList = reviewDAO.readReviewSearch(search);
        if(!reviewList.isEmpty())
            mav.addObject("reviewList", reviewList);
        
        List<StoreDTO> storeList = storeDAO.readStoreSearch(search);
        if(!storeList.isEmpty())
            mav.addObject("storeList", storeList);
        
        if(postList.isEmpty()&&reviewList.isEmpty()&&storeList.isEmpty())
            mav.addObject("search", "검색 결과 없음");
        
        mav.setViewName("search/index");
>>>>>>> 3254908c4460ac75288f1e2ee252cd9fefa419ef

        return mav;
    }
}
