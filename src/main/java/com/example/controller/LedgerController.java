package com.example.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.Ledger;
import com.example.service.LedgerService;

@RestController
public class LedgerController {
	
	@Autowired
	LedgerService ls;
	
	@PostMapping("addledger/{id}")
	public ResponseEntity<Object> addLedger(@Valid @PathVariable int id,@RequestBody Ledger l)
	{
		System.out.println("ledger controller");
		String msg=ls.createledger(l,id);
		return new ResponseEntity<Object>(msg,HttpStatus.CREATED);
	}
	
	@GetMapping("getallledger")
	public ResponseEntity<Object> getAllStudents()
	{
		List <Ledger> li=ls.getall();
		return new ResponseEntity<Object>(li.toString(),HttpStatus.OK);
	}
	
	@GetMapping("search/{id}")
	public ResponseEntity<Object> getLedger(@PathVariable int id)
	{
		List <Ledger> li=ls.searchbyId(id);
		return new ResponseEntity<Object>(li.toString(),HttpStatus.OK);
	}
	
	@GetMapping("total/{id}")
	public ResponseEntity<Object>  total(@PathVariable int id)
	{
		Integer total=ls.calcTotal(id);
		return new ResponseEntity<Object>(id +":"+total,HttpStatus.OK);
	}
	
	@DeleteMapping("delete/{id}")
	public ResponseEntity<Object>  delete(@PathVariable int id)
	{
		String msg=ls.delete(id);
		return new ResponseEntity<Object>(msg,HttpStatus.OK);
	}
	
}
