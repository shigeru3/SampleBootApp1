package com.example.sample1app;

import java.util.List;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.example.sample1app.repositories.PersonRepository;

import jakarta.transaction.Transactional;

@Controller
public class HelloController {

	@Autowired
	PersonRepository repository;

	@RequestMapping(value = "/")
	public ModelAndView index(
			@ModelAttribute("formModel") Person Person,
			ModelAndView mav) {
		mav.addObject("title", "Hello page");
		mav.addObject("msg", "This is JPA sample data");
		List<Person> list = repository.findAll();
		mav.addObject("data", list);
		mav.setViewName("index");
		return mav;
	}

	@RequestMapping(value = "/", method = RequestMethod.POST)
	@Transactional
	public ModelAndView form(
			@ModelAttribute("formModel") Person Person,
			ModelAndView mav) {
		repository.saveAndFlush(Person);
		return new ModelAndView("redirect:/");
	}

	@PostConstruct
	public void init() {
		Person p1 = new Person();
		p1.setName("Ohyama");
		p1.setAge(29);
		p1.setMail("yusuke@hanshin.tigers");
		p1.setMemo("4th Hitter First Baseman #3");
		repository.saveAndFlush(p1);

		Person p2 = new Person();
		p2.setName("Sato");
		p2.setAge(24);
		p2.setMail("teru@hanshin.tigers");
		p2.setMemo("5th Hitter Third Baseman #8");
		repository.saveAndFlush(p2);

		Person p3 = new Person();
		p3.setName("Ito");
		p3.setAge(27);
		p3.setMail("masashi@hanshin.tigers");
		p3.setMemo("Starter Pitcher #27");
		repository.saveAndFlush(p3);
	}
}
