package com.example.dao;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.any;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.when;

import java.util.Arrays;
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

import com.example.repository.LedgerRepository;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = {com.example.dao.LedgerDao.class})
class LedgerDaoTest {

	@Mock
	LedgerRepository lr;
	
	
	
	@InjectMocks
	LedgerDao ld;
	
	@Before
	public void init()
	{
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	void testGetAll() {
		
		System.out.println(lr.findAll().size());
		assertThat(lr.findAll()).hasSize(0);
	}

	@Test
	void testGetPrices() {
		List<Integer> li =Arrays.asList(1,2,3,5,6);
		when(lr.getAllPrices(Mockito.anyInt())).thenReturn(li);
		assertThat(ld.getPrices(1)).hasSize(5);
	}

	

}
