package com.example.dao;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
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
	void testSaveLedger() {
		
	}

	@Test
	void testGetAll() {
		
		System.out.println(lr.findAll().size());
		assertThat(lr.findAll()).hasSize(0);
	}

	@Test
	void testGetPrices() {
		fail("Not yet implemented");
	}

	@Test
	void testDelete() {
		fail("Not yet implemented");
	}

}
