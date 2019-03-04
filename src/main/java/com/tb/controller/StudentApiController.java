package com.tb.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tb.model.Student;
import com.tb.service.DataService;

@RestController
@RequestMapping("student")
public class StudentApiController {

	@Autowired
	private DataService dataService;

	/* Adding a resource */
	@PostMapping("/")
	private int add(@RequestBody Student student) {
		return dataService.add(student);
	}

	/* Updating a resource */
	@PutMapping("/")
	private int update(@RequestBody Student student) {
		return dataService.update(student);
	}

	/* Getting a resource */
	@GetMapping("/{id}")
	private Student get(@PathVariable int id) {
		return dataService.get(id);
	}

	/* Getting a list of resources */
	@GetMapping("/")
	private Collection<Student> list() {
		return dataService.list();
	}

	/* Deleting a resource */
	@DeleteMapping("/{id}")
	private int delete(@PathVariable int id) {
		return dataService.delete(id);
	}

}
