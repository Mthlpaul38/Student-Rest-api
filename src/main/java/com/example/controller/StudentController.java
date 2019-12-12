package com.example.controller;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.dao.Studentdao;
import com.example.model.Student;

@RestController
@Transactional
public class StudentController {
	
	@Autowired
	Studentdao sd;
	
	
	@PostMapping("create")
	public ResponseEntity<Object> createStudentEntity(@RequestBody Student student)
	{
		sd.saveStudent(student);
		
		System.out.println("Student Function");
		return new ResponseEntity<Object>("Created",HttpStatus.CREATED);
	}
	@GetMapping("get/{id}")
	public ResponseEntity<Object> getStudenEntity(@PathVariable int id)
	{
		Student s;
		s=sd.getbyId(id);
		if(s!=null)
			return new ResponseEntity<Object>(s.toString(),HttpStatus.CREATED);
		else
			return new ResponseEntity<Object>("Not Present",HttpStatus.OK);
		
	}
}
