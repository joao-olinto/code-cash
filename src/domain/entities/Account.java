package domain.entities;

public abstract class Account {

	private int number;
	private String holder;
	protected double balance;
	private double withDrawLimit;

	// Constructors
	public Account() {

	}

	public Account(int number, String holder, double initialDeposit, double withDrawLimit) {

		this.number = number;
		this.holder = holder;
		this.balance = initialDeposit;
		this.withDrawLimit = withDrawLimit;

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

}
