package com.example.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.example.dao.LedgerDao;
import com.example.repository.LedgerRepository;
import com.example.service.LedgerService;

@RunWith(SpringRunner.class)
@WebMvcTest(LedgerController.class)
public class LedgerControllerTest {

	@MockBean
	LedgerDao lr;

	@MockBean
	LedgerRepository ld;

	@InjectMocks
	LedgerService ls;

	@Autowired
	private MockMvc mockmvc;

	@Test
	void testTotal() throws Exception {
		when(ld.getAllPrices(9)).thenReturn(Arrays.asList(1, 2, 4, 5, 67));
		System.out.println("Running test");
		List<Integer> li = ld.getAllPrices(9);
		System.out.println(li.get(2));
		MvcResult result = mockmvc.perform(get("/total/9"))
				.andExpect(status().isOk()).andReturn();
		String res = result.getResponse().getContentAsString();
		assertEquals(9 + ":" + 79, res);
	}

}
