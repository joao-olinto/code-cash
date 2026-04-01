package domain.enums;

public enum AccountType {
	
	SAVINGS("Savings account"),
	BUSINESS("Business account");
	
	private String accountType;
	
	private AccountType(String accountType){
		this.accountType = accountType;
	}
	
	
	public String getAccountType() {return  accountType;}
	
	

}
