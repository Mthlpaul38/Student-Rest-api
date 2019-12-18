package com.example.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.example.model.Ledger;
import com.example.repository.LedgerRepository;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Repository
@Transactional
@AllArgsConstructor
@NoArgsConstructor
public class LedgerDao {

	
	
	private LedgerRepository lr;
	
	public void saveLedger(Ledger ledger)
	{
		
		lr.save(ledger);
		
	}

	public List<Ledger> getAll() {
		// 
		return lr.findAll();
	}
	
	public List<Integer> getPrices(int id)
	{
		return lr.getAllPrices(id);
	}

	public void delete(int id) {
		lr.deleteBystud_id(id);
		
	}
	
}
