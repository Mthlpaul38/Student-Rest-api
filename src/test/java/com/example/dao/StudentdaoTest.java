package com.example.dao;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.example.model.Student;
import com.example.repository.StudentRepositoy;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = { com.example.dao.Studentdao.class })
class StudentdaoTest {

	@Mock
	private StudentRepositoy sr;
	
	@InjectMocks
	private Studentdao sd;
	
	@Before
	public void init()
	{
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	void testGetbyId() {
		Student s = new Student();
		s.setStud_id(1);
		s.setClss("12");
		s.setFistname("harinder");
		s.setLastname("varma");
		Mockito.doReturn(s).when(sr).findById(Mockito.any(Integer.class));
		Student expected=sd.getbyId(1);
		assertEquals(expected, sd.getbyId(2));
		
	}

	@Test
	void testGetAll() {
		Student s = new Student();
		s.setStud_id(1);
		s.setClss("12");
		s.setFistname("harinder");
		s.setLastname("varma");
		Student s1 = new Student();
		s1.setStud_id(2);
		s1.setClss("11");
		s1.setFistname("Lokesh");
		s1.setLastname("kanth");
		List<Student> li = new ArrayList<Student>();
		li.add(s1);
		li.add(s);
		Mockito.doReturn(li).when(sr).findAll();
		assertAll(()->assertThat(sd.getAll()).hasSize(2),
				()->assertThat(sd.getAll()).contains(s1),
				()->assertThat(sd.getAll()).contains(s));
	}

}
