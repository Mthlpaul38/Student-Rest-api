package com.example.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.example.model.Student;
import com.example.repository.StudentRepositoy;

import lombok.AllArgsConstructor;

@Repository
@Transactional
@AllArgsConstructor
public class Studentdao  {



	StudentRepositoy srepo;
	
	public void saveStudent(Student student)
	{
		
		srepo.save(student);
		
	}
	
	public Student getbyId(int id)
	{
		Student s=null;
		return srepo.findById(id).orElse(s);
		
	}
	public List<Student> getAll()
	{
		
		return srepo.findAll();
	}
}
