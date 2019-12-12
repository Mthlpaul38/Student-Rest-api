package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.Student;
import com.example.service.StudentService;

@RestController
public class StudentController {
	
	@Autowired
	StudentService sd;
	
	
	@PostMapping("create")
	public ResponseEntity<Object> createStudentEntity(@RequestBody Student student)
	{
		String msg=sd.saveStudent(student);
		return new ResponseEntity<Object>(msg,HttpStatus.CREATED);
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
	
	
	@GetMapping("getallstudents")
	public ResponseEntity<Object> getAllStudents()
	{
		List <Student> li=sd.getall();
		return new ResponseEntity<Object>(li.toString(),HttpStatus.OK);
	}
	
}
