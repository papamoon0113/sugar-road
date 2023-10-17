package com.example.demo.controller;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
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
