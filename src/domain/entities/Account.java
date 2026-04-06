package domain.entities;

import domain.enums.AccountType;
import domain.exception.DomainException;

public abstract class Account {

	private String number;
	private String holder;
	protected double balance;
	protected double withDrawLimit;
	private AccountType type;

	// Constructors
	public Account() {

	}

	public Account(String number, String holder, double initialDeposit, double withDrawLimit, AccountType type) {

		//call to the validator method
		validateRules(number, holder, initialDeposit, withDrawLimit);

		this.number = number;
		this.holder = holder;
		this.balance = initialDeposit;
		this.withDrawLimit = withDrawLimit;
		this.type = type;

	}

	// Getters e setters
	public String getNumber() {

		return number;
	}

	public void setNumber(String number) {

		if (number.isBlank() || !number.trim().matches("^\\d{4}$")) {
			throw new DomainException("Account Error: account number must contain exactly 4 digits.");
		}

		this.number = number;
	}

	public String getHolder() {
		return holder;
	}

	public void setHolder(String holder) {

		if (holder.isBlank() || !holder.matches("^[\\p{L}]+(?:[ '\\p{L}]+)*$")) {
			throw new DomainException("Account Error: Name must contain only letters, spaces or apostrophes.");
		}

		this.holder = holder;
	}

	// The balance attribute only has a getter method, as its modifications are
	// controlled via encapsulation.
	public double getBalance() {
		return balance;
	}

	public double getWithDrawLimit() {
		return withDrawLimit;
	}
	
	public void setWithDrawLimit(double amount) {
		
		if (withDrawLimit < 0.0) {
			throw new DomainException("Account Error: the with drawal limit cannot be less than zero.");
		}
		
		this.withDrawLimit = amount;
		
	}

	public AccountType getType() {
		return type;
	}

	public void setType(AccountType type) {
		this.type = type;
	}

	public void deposit(double amount) {

		if (amount < 0.0) {
			throw new DomainException("Account Error: the amount cannot be less than zero");
		}

		balance += amount;
	}

	// Abstract method of implementations for subclasses
	public abstract void withDraw(double amount);

	//method that validates the constructor
	private void validateRules(String number, String holder, double balance, double withDrawLimit)
			throws DomainException {

		if (number.isBlank() || !number.trim().matches("^\\d{4}$")) {
			throw new DomainException("Account Error: account number must contain exactly 4 digits.");
		}

		if (holder.isBlank() || !holder.matches("^[\\p{L}]+(?:[ '\\p{L}]+)*$")) {
			throw new DomainException("Account Error: Name must contain only letters, spaces or apostrophes.");
		}

		if (balance < 0.0) {
			throw new DomainException("Account Error: the balance cannot be less than zero.");
		}

		if (withDrawLimit < 0.0) {
			throw new DomainException("Account Error: the with drawal limit cannot be less than zero.");
		}
	}

}
