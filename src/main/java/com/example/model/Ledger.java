package com.example.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
@Entity
public class Ledger {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	int led_id;
	
	
	String item;

	Date date_of_purchase;
	

	int price;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "stud_id",nullable = false)
	Student student;

	public int getLed_id() {
		return led_id;
	}

	public void setLed_id(int led_id) {
		this.led_id = led_id;
	}

	public String getItem() {
		return item;
	}

	public void setItem(String item) {
		this.item = item;
	}

	public Date getDate_of_purchase() {
		return date_of_purchase;
	}

	public void setDate_of_purchase(Date date_of_purchase) {
		this.date_of_purchase = date_of_purchase;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}
	
	
	
	

}
