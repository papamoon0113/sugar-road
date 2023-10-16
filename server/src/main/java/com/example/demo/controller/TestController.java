package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class TestController {
	@GetMapping("/test")
	public ModelAndView test(){
		ModelAndView mav = new ModelAndView();
		mav.setViewName("test");
		return mav;
	}
}
