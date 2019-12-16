package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dao.LedgerDao;
import com.example.dao.Studentdao;
import com.example.model.Ledger;
import com.example.model.Student;

@Service
public class LedgerService {
	
	@Autowired
	Studentdao sd;
	
	@Autowired
	LedgerDao ld;
	
	public Ledger createledger(Ledger l, int id)
	{
		Student s=sd.getbyId(id);
		l.setStudent(s);
		ld.saveLedger(l);
		return l;
	}

	public List<Ledger> getall() {
		List<Ledger>l=ld.getAll();
		return l;
		
	}

	public List<Ledger> searchbyId(int id) {
	Student s=sd.getbyId(id);
	List<Ledger> l=s.getLedger();
		return l;
	}

	public Integer calcTotal(int id) {
		List<Integer> li=ld.getPrices(id);
		Integer total=li.stream().reduce(0,(a,b)->a+b);
		return total;
		
	}

	public String delete(int id) {
		ld.delete(id);
		String msg="deleted";
		return msg;
		
	}
}
