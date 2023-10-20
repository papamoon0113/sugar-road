package com.example.demo.controller;

import com.example.demo.dao.StoreDAO;
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
    ModelAndView mav = new ModelAndView();
    @GetMapping("/home")
    public ModelAndView main(){
        List<StoreDTO> list = dao.readStore();
       mav.addObject("list", list);
        mav.setViewName("home/home");
        return mav;
    }
}

