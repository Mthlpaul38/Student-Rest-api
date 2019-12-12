package com.example.dao;

import java.util.Optional;
import java.util.function.Consumer;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.model.Student;
import com.example.repository.StudentRepositoy;

@Repository
@Transactional
public class Studentdao  {


	//private EntityManager entityManager;
	@Autowired
	StudentRepositoy srepo;
	
	public void saveStudent(Student student)
	{
		System.out.println("Savestudent");
		System.out.println(student);

		srepo.save(student);
		
	}
	
	public Student getbyId(int id)
	{
		Student s=null;
		return srepo.findById(id).orElse(s);
		
	}
}
