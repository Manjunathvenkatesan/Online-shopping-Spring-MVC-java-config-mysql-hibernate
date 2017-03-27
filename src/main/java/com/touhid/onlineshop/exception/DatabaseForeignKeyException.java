package com.touhid.onlineshop.exception;

public class DatabaseForeignKeyException extends Exception{

	
	private static final long serialVersionUID = 1L;
	
	public DatabaseForeignKeyException(String message) {
		super(message);
	}
	
	public DatabaseForeignKeyException() {
		super();
	}
	

}
