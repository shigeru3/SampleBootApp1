package com.example.sample1app;

import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HelloController {
	@RequestMapping(value = "/")
	public ModelAndView index(ModelAndView mav) {
		mav.addObject("msg", "display data");
		String[] data = new String[] {"One", "Two", "Three"};
		mav.addObject("data", data);
		mav.setViewName("index");
		return mav;
	}
}
