package com.example.sample1app;

import com.example.sample1app.repositories.PersonRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SampleBootApp1Application implements CommandLineRunner {

	@PersistenceContext
	private EntityManager entityManager;

	@Autowired
	PersonRepository repository;

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(SampleBootApp1Application.class);
		app.setBannerMode(Banner.Mode.OFF);
		app.run(args);
	}

	@Override
	public void run(String... args) throws Exception {
		init();
		Person person = entityManager.find(Person.class, 1);
		System.out.println("+---+");
		System.out.println(person.getName() + ", " + person.getMail() + ", " + person.getAge());
		System.out.println("+---+");
	}

	public void init() {
		Person p1 = new Person();
		p1.setName("Nakano");
		p1.setAge(27);
		p1.setMail("takumu@hanshin.tigers");
		repository.saveAndFlush(p1);
	}
}
