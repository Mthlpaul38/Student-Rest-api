package com.example.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
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

import com.example.dao.LedgerDao;
import com.example.dao.Studentdao;
import com.example.model.Ledger;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes= {com.example.service.LedgerService.class})
class LedgerServiceTest {
	
	
	@Mock
	LedgerDao ledgerdao;
	
	@Mock
	Studentdao studentdao;
	
	@InjectMocks
	LedgerService ledgerservice;
	
	@Before
	public void init()
	{
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testCreateledger() {
		Ledger l=new Ledger();
		l.setDate_of_purchase(new Date());
		l.setItem("book");
		l.setPrice(120);
		Mockito.doNothing().when(ledgerdao).saveLedger(Mockito.any(Ledger.class));
		Ledger expected=ledgerservice.createledger(l, 9);
		assertEquals(expected.toString(),l.toString());
	}

	@Test
	public void testGetall() {
		Ledger l=new Ledger();
		l.setDate_of_purchase(new Date());
		l.setItem("Pen");
		l.setPrice(15);
		Ledger l2=new Ledger();
		l2.setDate_of_purchase(new Date());
		l2.setItem("Pencil");
		l2.setPrice(5);
		List <Ledger> ledgerList=Stream.of(l,l2).collect(Collectors.toList());
		when(ledgerdao.getAll()).thenReturn(ledgerList);
		List<Ledger> expected=ledgerdao.getAll();
		assertThat(expected).hasSize(2);
		
	}


	@Test
	public void testCalcTotal() {
			List<Integer> li=new ArrayList<Integer>();
			li.add(1);
			li.add(45);
			li.add(56);
			when(ledgerdao.getPrices(9)).thenReturn(li);
			Integer sum=li.stream().mapToInt(a->a).sum();
			System.out.println(sum);
			assertEquals(ledgerservice.calcTotal(9),sum);
		
	}


}
