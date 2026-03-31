package domain.entities;

public class BusinessAccount extends Account {
	
	private double loanLimit;
	
	////Call to the Super Class constructor 
	public BusinessAccount() {
		super();
	}
	
	public BusinessAccount(int number, String holder,double initialDeposit, double withDrawLimit, double loanLimit) {
		super(number,holder,initialDeposit,withDrawLimit);  //Passing parameters to the superclass constructor
		
		this.loanLimit = loanLimit;
	}
	
	//getters e setters
	
	public double getLoanLimit() {
		return loanLimit;
	}
	
	public void setLoanLimit(double loanLimit) {
	
		this.loanLimit = loanLimit;
	}
	
	
	
}
