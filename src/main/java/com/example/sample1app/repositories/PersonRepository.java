package com.example.sample1app.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.sample1app.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
	public Optional<Person> findById(Long id);

	@Query("SELECT d FROM Person d ORDER BY d.name")
	List<Person> findAllOderByName();
}
