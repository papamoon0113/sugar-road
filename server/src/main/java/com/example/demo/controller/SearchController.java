package com.example.demo.controller;

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

        return mav;
    }
}
