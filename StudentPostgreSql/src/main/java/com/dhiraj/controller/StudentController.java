package com.dhiraj.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dhiraj.exception.NotFoundException;
import com.dhiraj.exception.ResourceNotFoundException;
import com.dhiraj.model.Student;
import com.dhiraj.repo.StudentRepo;

@RestController
@RequestMapping("/students")
public class StudentController {

	@Autowired
	private StudentRepo studentRepo;

	@PostMapping
	public String saveStudent(@RequestBody Student student) {
		studentRepo.save(student);
		return "Created Successfully";
	}

	@GetMapping
	public List<Student> getStudents() {
		return studentRepo.findAll();
	}

	@GetMapping("/{id}")
	public Student getById(@PathVariable("id") int id) {
		return studentRepo.findById(id).orElseThrow(() -> new NotFoundException("Id not found"));
	}

	@DeleteMapping("/{id}")
	public void deleteById(@PathVariable("id") int id) {
		studentRepo.delete(getById(id));
	}

	@DeleteMapping
	public void deleteAll() {
		studentRepo.deleteAll();
	}

	@PutMapping("/{id}")
	public String updateStudent(@RequestBody Student student, @PathVariable("id") int id)
			throws ResourceNotFoundException {

		Student s = studentRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Id not found"));
		s.setName(student.getName());
		s.setAge(student.getAge());
		studentRepo.save(s);
		return "Updated successfully";
	}
}
