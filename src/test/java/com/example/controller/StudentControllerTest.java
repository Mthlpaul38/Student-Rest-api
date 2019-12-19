package com.example.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.example.model.Student;
import com.example.service.StudentService;
import com.fasterxml.jackson.databind.ObjectMapper;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = {com.example.controller.StudentController.class})
public class StudentControllerTest {

	@Mock
	StudentService ss;

	@InjectMocks
	private StudentController sc;

	private MockMvc mockMvc;

	@BeforeEach
	public void init() {
		System.out.println("Entry");
		MockitoAnnotations.initMocks(this);
		StudentController sc = new StudentController(ss);
		mockMvc = MockMvcBuilders.standaloneSetup(sc).build();
		System.out.println("Mock mvc" + mockMvc.toString());
	}

	@Test
	public void testCreateStudentEntity() throws Exception {
		Student s = new Student();
		s.setClss("12");
		s.setFistname("harinder");
		s.setLastname("varma");
		when(ss.saveStudent(any(Student.class))).thenReturn(s);
		System.out.println(ss.saveStudent(s));
		mockMvc.perform(post("/create").contentType(MediaType.APPLICATION_JSON).content(asJsonString(s)))
				.andExpect(status().isCreated()).andExpect(content().json(asJsonString(s)));
	}

	public static String asJsonString(final Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Test
	public void testgetstudententity() throws Exception {
		Student s = new Student();
		s.setClss("12");
		s.setFistname("harinder");
		s.setLastname("varma");
		System.out.println(asJsonString(s));
		when(ss.getbyId(any(Integer.class))).thenReturn(s);
		MvcResult Result=mockMvc.perform(get("/get/1").param("id", "1")).andExpect(status().isOk())
				.andReturn();
		assertEquals(Result.getResponse().getContentAsString(),s.toString());
	}
	@Test
	public void testupdate() throws Exception
	{
		Student s = new Student();
		s.setClss("12");
		s.setFistname("harinder");
		s.setLastname("varma");
		when(ss.updateStudent(any(Integer.class),any(Student.class))).thenReturn(s);
		MvcResult Result=mockMvc.perform(put("/update/1").param("id", "1").contentType(MediaType.APPLICATION_JSON).content(asJsonString(s))).andExpect(status().isOk())
				.andReturn();
		assertEquals(Result.getResponse().getContentAsString(),s.toString());
	}

	@Test
	public void testgetallstudents() throws Exception
	{
		Student s = new Student();
		s.setClss("12");
		s.setFistname("harinder");
		s.setLastname("varma");
		List<Student> li =Arrays.asList(s);
		when(ss.getall()).thenReturn(li);
		MvcResult Result=mockMvc.perform(get("/getallstudents")).andExpect(status().isOk()).andReturn();
		assertEquals(Result.getResponse().getContentAsString(), li.toString());;
	}
}
