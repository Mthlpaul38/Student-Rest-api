package com.example.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
public class Student {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	int stud_id;

	@Size(min=4,message="Name should atleast 4")
	String fistname;
	
	
	@Override
	public String toString() {
		return "\nStudent [stud_id=" + stud_id + ", fistname=" + fistname + ", lastname=" + lastname + ", clss=" + clss+ "]"
				;
	}


	@NotNull(message = "Lastname shoukd not be null")
	String lastname;
	
	
	@Min(value = 1,message = "class should be  betweeen 1 and 12")
	@Max(value=12,message = "class should be between 1 and 12")
	String clss;
	
	
	@OneToMany(mappedBy = "student")
	@JsonIgnore
	private  List<Ledger> ledger;
	
	public int getStud_id() {
		return stud_id;
	}

	public void setStud_id(int stud_id) {
		this.stud_id = stud_id;
	}


	public String getFistname() {
		return fistname;
	}


	public void setFistname(String fistname) {
		this.fistname = fistname;
	}


	public String getLastname() {
		return lastname;
	}


	public void setLastname(String lastname) {
		this.lastname = lastname;
	}


	public String getClss() {
		return clss;
	}


	public void setClss(String clss) {
		this.clss = clss;
	}


	public List<Ledger> getLedger() {
		return ledger;
	}


	public void setLedger(List<Ledger> ledger) {
		this.ledger = ledger;
	}




	
}
