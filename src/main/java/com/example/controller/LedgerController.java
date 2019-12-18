package com.example.controller;

import java.util.List;

import javax.validation.Valid;

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

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@RestController
@AllArgsConstructor
@NoArgsConstructor
public class LedgerController {
	

	private LedgerService ledgerService;
	
	@PostMapping("addledger/{id}")
	public ResponseEntity<Object> addLedger(@Valid @PathVariable int id,@RequestBody Ledger l)
	{
		System.out.println("ledger controller");
		Ledger l1=ledgerService.createledger(l,id);
		return new ResponseEntity<Object>(l1,HttpStatus.CREATED);
	}
	
	@GetMapping("getallledger")
	public ResponseEntity<Object> getAlledgerServicetudents()
	{
		List <Ledger> li=ledgerService.getall();
		return new ResponseEntity<Object>(li.toString(),HttpStatus.OK);
	}
	
	@GetMapping("search/{id}")
	public ResponseEntity<Object> getLedger(@PathVariable int id)
	{
		List <Ledger> li=ledgerService.searchbyId(id);
		return new ResponseEntity<Object>(li.toString(),HttpStatus.OK);
	}

	@GetMapping("/total/{id}")
	public ResponseEntity<Object>  total(@PathVariable int id)
	{
		Integer total=ledgerService.calcTotal(id);
		return new ResponseEntity<Object>(total,HttpStatus.OK);
	}
	
	@DeleteMapping("delete/{id}")
	public ResponseEntity<Object>  delete(@PathVariable int id)
	{
		String msg=ledgerService.delete(id);
		return new ResponseEntity<Object>(msg,HttpStatus.OK);
	}
	
	
	@GetMapping("/hello")
	public String gethello()
	{
		return "hello";
	}
	
}
