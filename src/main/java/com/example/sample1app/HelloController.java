package com.example.sample1app;

import java.io.IOException;
import java.io.Writer;

import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HelloController {
	@RequestMapping(value = "/")
	public ModelAndView index(ModelAndView mav) {
		mav.addObject("title", "Groovy templates");
		mav.addObject("msg", "This is sample message");
		mav.setViewName("index");
		return mav;
	}
}
