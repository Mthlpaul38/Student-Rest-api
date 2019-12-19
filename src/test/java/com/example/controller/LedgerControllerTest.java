package com.example.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.example.model.Ledger;
import com.example.model.Student;
import com.example.service.LedgerService;
import com.fasterxml.jackson.databind.ObjectMapper;

@ExtendWith(SpringExtension.class)
@WebMvcTest(com.example.controller.LedgerController.class)
public class LedgerControllerTest {
	
	@InjectMocks
	LedgerController ledgerController;
	
	
	private MockMvc mockMvc;
	
	@Mock
	private LedgerService ledgerService;
	
	
	@Before
	public void init()
	{
		MockitoAnnotations.initMocks(this);
		LedgerController ledgerController=new LedgerController(ledgerService);
		mockMvc=MockMvcBuilders.standaloneSetup(ledgerController).build();
		System.out.println("Mock mvc"+mockMvc.toString());
	}
	
	
	
	@Test
	public void testGethello() throws Exception {
		
		  MvcResult result=mockMvc.perform(get("/hello")).andReturn();
		  assertEquals("hello", result.getResponse().getContentAsString());
		 	
	}
	
	@Test
	public void testaddledger()throws Exception{
		Student s=new Student();
		s.setClss("12");
		s.setFistname("harinder");
		s.setLastname("varma");
		Ledger l=new Ledger();
		l.setDate_of_purchase(new Date());
		l.setItem("book");
		l.setPrice(120);
		l.setStudent(s);
		//doReturn(l).when(ledgerService).createledger(Mockito.any(Ledger.class),1);
		when(ledgerService.createledger(any(Ledger.class),any(Integer.class))).thenReturn(l);
		MvcResult result=mockMvc.perform(post("/addledger/1").contentType(MediaType.APPLICATION_JSON).content(asJsonString(l))).andExpect(status().isCreated()).andReturn();
		 System.out.println("Result:"+result.getResponse().getContentAsString());
		  assertEquals(result.getResponse().getContentAsString(),asJsonString(l));
		 }
	
	public static String asJsonString(final Object obj) {
	    try {
	        return new ObjectMapper().writeValueAsString(obj);
	    } catch (Exception e) {
	        throw new RuntimeException(e);
	    }
	}
	
	@Test
	public void testTotal() throws Exception{
		when(ledgerService.calcTotal(9)).thenReturn(9);
		MvcResult result=mockMvc.perform(get("/total/9").accept(MediaType.APPLICATION_JSON)).andReturn();
		System.out.println("Result is:"+result.getResponse().getContentAsString());
		  assertEquals("9",result.getResponse().getContentAsString());
	}
	
	@Test
	public void testsearch() throws Exception
	{
		Ledger l=new Ledger();
		l.setDate_of_purchase(new Date());
		l.setItem("Pen");
		l.setPrice(15);
		Ledger l2=new Ledger();
		l2.setDate_of_purchase(new Date());
		l2.setItem("Pencil");
		l2.setPrice(5);
		List <Ledger> ledgerList=Stream.of(l,l2).collect(Collectors.toList());
		when(ledgerService.searchbyId(any(Integer.class))).thenReturn(ledgerList);
		MvcResult result=mockMvc.perform(get("/search/9")).andReturn();
		assertEquals(result.getResponse().getContentAsString(), ledgerList.toString());
	}
	
	@Test
	public void testgetallledger() throws  Exception
	{
		Ledger l=new Ledger();
		l.setDate_of_purchase(new Date());
		l.setItem("Pen");
		l.setPrice(15);
		Ledger l2=new Ledger();
		l2.setDate_of_purchase(new Date());
		l2.setItem("Pencil");
		l2.setPrice(5);
		List <Ledger> ledgerList=Stream.of(l,l2).collect(Collectors.toList());
		when(ledgerService.getall()).thenReturn(ledgerList);
		MvcResult result=mockMvc.perform(get("/getallledger")).andReturn();
		assertEquals(result.getResponse().getContentAsString(), ledgerList.toString());
	}

}

