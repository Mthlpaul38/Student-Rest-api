package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dao.Studentdao;
import com.example.exceptions.UsernotFoundException;
import com.example.model.Student;

@Service
public class StudentService {

	@Autowired
	Studentdao sd;
	
	public Student saveStudent(Student student)
	{
		if(sd.getbyId(student.getStud_id())==null) {
		sd.saveStudent(student);
		return student;
		}
		else 
			throw new UsernotFoundException("User already exists");
	}
	public Student getbyId(int id)
	{
		Student s;
		s=sd.getbyId(id);
		if(s!=null)
			return s;
		else
			throw new UsernotFoundException("Usernot found");
	}
	
	public List<Student> getall()
	{
		return sd.getAll();
	}
}
