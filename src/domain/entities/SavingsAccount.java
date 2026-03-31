package domain.entities;

import domain.enums.AccountType;

public class SavingsAccount extends Account { 

	private double interestRate;
	
	//Call to the Super Class constructor 
	public SavingsAccount() {
		super();
	}
	
	public SavingsAccount(int number, String holder, double balance, double withDrawLimit, AccountType type, double interesetRate) {
		super(number,holder,balance,withDrawLimit,type); //Passing parameters to the superclass constructor
		
		this.interestRate = interesetRate;
	}
	
	//Getters e setters
	public double getInterestRate() {
		return interestRate;
	}
	
	public void setInteresetRate(double interesetRate) {
		this.interestRate = interesetRate;
	}
	
}
