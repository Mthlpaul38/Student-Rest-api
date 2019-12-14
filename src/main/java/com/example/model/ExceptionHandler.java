package com.example.model;

public class ExceptionHandler {
String message;
String details;
public String getMessage() {
	return message;
}
public void setMessage(String message) {
	this.message = message;
}
public String getDetails() {
	return details;
}
public void setDetails(String details) {
	this.details = details;
}
public ExceptionHandler(String message, String details) {
	super();
	this.message = message;
	this.details = details;
}
}
