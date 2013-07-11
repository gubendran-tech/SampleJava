package com.gubs.testDrools;

public class Account {
	
	private int Id;
	private String name;
	private long balance;
	
	
	
	public Account(int id, String name, long balance) {
		super();
		Id = id;
		this.name = name;
		this.balance = balance;
	}
	
	public int getId() {
		return Id;
	}
	public void setId(int id) {
		Id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public long getBalance() {
		return balance;
	}
	public void setBalance(long balance) {
		this.balance = balance;
	}
	
	

}
