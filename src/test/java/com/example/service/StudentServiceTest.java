package com.example.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.example.dao.Studentdao;
import com.example.model.Student;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = { com.example.service.StudentService.class })
class StudentServiceTest {

	@Mock
	Studentdao studentdao;

	@InjectMocks
	StudentService studentservice;

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	void testSaveStudent() {
		Student s = new Student();
		s.setClss("12");
		s.setFistname("harinder");
		s.setLastname("varma");
		Mockito.doNothing().when(studentdao).saveStudent(Mockito.any(Student.class));
		Student expected = studentservice.saveStudent(s);
		assertEquals(expected.toString(), s.toString());
	}

	@Test
	void testGetbyId() {
		Student s = new Student();
		s.setStud_id(1);
		s.setClss("12");
		s.setFistname("harinder");
		s.setLastname("varma");
		Mockito.doReturn(s).when(studentdao).getbyId(Mockito.any(Integer.class));
		Student expected = studentservice.getbyId(1);
		assertThat(expected).isEqualTo(s);
	}

	@Test
	void testGetall() {
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
		List<Student> li = Stream.of(s1,s).collect(Collectors.toList());
		Mockito.doReturn(li).when(studentdao).getAll();
		assertAll("List returned", () -> assertThat(studentservice.getall()).hasSize(2),
								   () -> assertThat(studentservice.getall()).isEqualTo(li));

	}

}
