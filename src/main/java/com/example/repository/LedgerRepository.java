package com.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.example.model.Ledger;


public interface LedgerRepository extends JpaRepository<Ledger, Integer> {

	
	@Query("select price from Ledger where stud_id=?1")
	List<Integer> getAllPrices(int id);
	
	@Modifying
	@Query("delete from Ledger where stud_id=?1")
	void deleteBystud_id(int id);
}
