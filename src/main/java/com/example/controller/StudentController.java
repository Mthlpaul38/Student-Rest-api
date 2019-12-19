package com.example.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.Student;
import com.example.service.StudentService;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@RestController
@AllArgsConstructor
@NoArgsConstructor
public class StudentController {

	
	private StudentService sd;

	@PostMapping("/create")
	public ResponseEntity<Object> createStudentEntity(@Valid @RequestBody Student student) {
		 Student s=sd.saveStudent(student);
		return new ResponseEntity<Object>(s, HttpStatus.CREATED);
	}

	@GetMapping("get/{id}")
	public ResponseEntity<Object> getStudenEntity(@PathVariable int id) {
		Student s;
		s = sd.getbyId(id);
		return new ResponseEntity<Object>(s.toString(), HttpStatus.OK);

	}

	@GetMapping("/getallstudents")
	public ResponseEntity<Object> getAllStudents() {
		List<Student> li = sd.getall();
		return new ResponseEntity<Object>(li.toString(), HttpStatus.OK);
	}
	
	@PutMapping("update/{id}")
	public ResponseEntity<Object> updateStudent(@PathVariable int id,@RequestBody Student student)
	{
		Student s=sd.updateStudent(id,student);
		return new ResponseEntity<Object>(s.toString(), HttpStatus.OK);
		
	}
}
