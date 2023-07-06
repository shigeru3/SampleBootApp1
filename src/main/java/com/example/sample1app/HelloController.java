package com.example.sample1app;

import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HelloController {
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView index(ModelAndView mav) {
		mav.addObject("msg", "Write your name");
		mav.setViewName("index");
		return mav;
	}

	@RequestMapping(value="/", method = RequestMethod.POST)
	public ModelAndView form(@RequestParam("text1") String str, ModelAndView mav) {
		mav.addObject("msg", "Hello " + str);
		mav.addObject("value", str);
		mav.setViewName("index");
		return mav;
	}
}
