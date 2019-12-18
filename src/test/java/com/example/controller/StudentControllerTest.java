package com.example.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.example.model.Student;
import com.example.service.StudentService;
import com.fasterxml.jackson.databind.ObjectMapper;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes= {com.example.controller.StudentController.class})
public class StudentControllerTest {

	@Mock
	StudentService ss;
	
	@InjectMocks
	private StudentController sc;
	
	
	private MockMvc mockMvc;
	
	
	@BeforeEach
	public void init()
	{
		System.out.println("Entry");
		MockitoAnnotations.initMocks(this);
		StudentController sc=new StudentController(ss);
		mockMvc=MockMvcBuilders.standaloneSetup(sc).build();
		System.out.println("Mock mvc"+mockMvc.toString());
	}
	
	
	
	@Test
	@Disabled
	public void testCreateStudentEntity() throws Exception {
		Student s=new Student();
		s.setClss("12");
		s.setFistname("harinder");
		s.setLastname("varma");
		when(ss.saveStudent(any(Student.class))).thenReturn(s);
		System.out.println(ss.saveStudent(s));
		MvcResult result=mockMvc.perform(post("/create").content(asJsonString(s))).andExpect(status().isCreated()).andReturn();
		
	}
	
	public static String asJsonString(final Object obj) {
	    try {
	        return new ObjectMapper().writeValueAsString(obj);
	    } catch (Exception e) {
	        throw new RuntimeException(e);
	    }
	}

	@Test
	public void testgetstudententity() throws Exception
	{
		Student s=new Student();
		s.setClss("12");
		s.setFistname("harinder");
		s.setLastname("varma");
		when(ss.getbyId(any(Integer.class))).thenReturn(s);
		MvcResult result=mockMvc.perform(get("/get/1").param("id","1")).andExpect(status().isOk()).andReturn();
	}
}
