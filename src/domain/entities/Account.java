package domain.entities;
import domain.enums.AccountType;

public abstract class Account {

	private int number;
	private String holder;
	protected double balance;
	private double withDrawLimit;
	private AccountType type;
	

	// Constructors
	public Account() {

	}

	public Account(int number, String holder, double initialDeposit, double withDrawLimit,AccountType type) {

		this.number = number;
		this.holder = holder;
		this.balance = initialDeposit;
		this.withDrawLimit = withDrawLimit;
		this.type = type;

	}

	// Getters e setters
	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {

		this.number = number;
	}

	public String getHolder() {
		return holder;
	}

	public void setHolder(String holder) {

		this.holder = holder;
	}

	public double getBalance() {
		return balance;
	}

	public double getWithDrawLimit() {
		return withDrawLimit;
	}

	public void setWithDrawLimit(double amount) {
		this.withDrawLimit = amount;
	}
	
	public AccountType getType() {
		return type;
	}
	
	public void setType(AccountType type) {
		this.type = type;
	}

}
