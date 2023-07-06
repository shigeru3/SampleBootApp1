package com.example.sample1app;

import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HelloController {
	@RequestMapping(value = "/{month}")
	public ModelAndView index(@PathVariable int month, ModelAndView mav) {
		mav.addObject("msg", "month: " + month);
		mav.addObject("month", month);
		mav.setViewName("index");
		return mav;
	}
}
