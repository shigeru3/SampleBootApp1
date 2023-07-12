package com.example.sample1app;

import java.sql.PseudoColumnUsage;
import java.util.List;
import java.util.Optional;

import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import com.example.sample1app.repositories.PersonRepository;

import jakarta.transaction.Transactional;

@Controller
public class HelloController {

	@Autowired
	PersonRepository repository;

	@Autowired
	PersonDAOPersonImpl dao;

	@RequestMapping(value = "/")
	public ModelAndView index(
			@ModelAttribute("formModel") Person Person,
			ModelAndView mav) {
		mav.addObject("title", "Hello page");
		mav.addObject("msg", "This is JPA sample data");
		List<Person> list = dao.getAll();
		mav.addObject("data", list);
		mav.setViewName("index");
		return mav;
	}

	@RequestMapping(value = "/", method = RequestMethod.POST)
	@Transactional
	public ModelAndView form(
			@ModelAttribute("formModel") @Validated Person Person,
			BindingResult result,
			ModelAndView mav) {
		ModelAndView res = null;
		System.out.println(result.getFieldError());
		if (!result.hasErrors()) {
			repository.saveAndFlush(Person);
			res = new ModelAndView("redirect:/");
		} else {
			mav.addObject("msg", "sorry, error is occurred.");
			Iterable<Person> list = repository.findAll();
			mav.addObject("data", list);
			mav.setViewName("index");
			res = mav;
		}
		return res;
	}

	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public ModelAndView edit(
			@ModelAttribute Person Person,
			@PathVariable int id,
			ModelAndView mav) {
		mav.addObject("title", "edit Person");
		Optional<Person> data = repository.findById((long)id);
		mav.addObject("formModel", data.get());
		mav.setViewName("edit");
		return mav;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	@Transactional
	public ModelAndView update(
			@ModelAttribute Person Person,
			ModelAndView mav) {
		repository.saveAndFlush(Person);
		return new ModelAndView("redirect:/");
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public ModelAndView delete(
			@PathVariable int id,
			ModelAndView mav) {
		mav.addObject("title", "Delete person");
		mav.addObject("msg", "Can I delete this record?");
		Optional<Person> data = repository.findById((long) id);
		mav.addObject("formModel", data.get());
		mav.setViewName("delete");
		return mav;
	}

	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public ModelAndView remove(
			@RequestParam long id,
			ModelAndView mav) {
		repository.deleteById(id);
		return new ModelAndView("redirect:/");
	}

	@RequestMapping(value = "/find", method = RequestMethod.GET)
	public ModelAndView index(ModelAndView mav) {
		mav.addObject("title", "Find person");
		mav.addObject("msg", "Sample person");
		Iterable<Person> list = dao.getAll();
		mav.addObject("data", list);
		mav.setViewName("find");
		return mav;
	}

	@RequestMapping(value = "/find", method = RequestMethod.POST)
	public ModelAndView search(
			HttpServletRequest request,
			ModelAndView mav) {
		String param = request.getParameter("find_str");
		if (param == "") {
			mav = new ModelAndView("redirect:/find");
		} else {
			mav.addObject("title", "Find result");
			mav.addObject("msg", "[" + param + "] search result");
			mav.addObject("value", param);
			String[] params = param.split(",");
			List<Person> list = dao.find(param);
			mav.addObject("data", list);
		}
		mav.setViewName("find");
		return mav;
	}

	@RequestMapping(value = "/page/{page}", method = RequestMethod.GET)
	public ModelAndView index(ModelAndView mav, @PathVariable int page) {
		mav.addObject("msg", "Person paging sample");
		int num = 2;
		Iterable<Person> list = dao.getPage(page, num);
		mav.addObject("data", list);
		mav.setViewName("find");
		return mav;
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
