package com.example.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.model.Ledger;
import com.example.repository.LedgerRepository;

@Repository
@Transactional
public class LedgerDao {

	
	@Autowired
	LedgerRepository lr;
	
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
