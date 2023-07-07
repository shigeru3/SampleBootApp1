package com.example.sample1app;

import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HelloController {
	private boolean flag = false;
	@RequestMapping(value = "/")
	public ModelAndView index(ModelAndView mav) {
		MyData[] data = new MyData[] {
				new MyData("Taro", 39),
				new MyData("Jiro", 37),
				new MyData("Saburo", 33)
		};
		mav.addObject("data", data);
		mav.setViewName("index");
		return mav;
	}
}

class MyData {
	public String name;
	public int age;

	public MyData(String name, int age) {
		this.name = name;
		this.age = age;
	}

	public String toString() {
		return String.format("{Name: %s, age: %s}", name, age);
	}
}
