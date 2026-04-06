package domain.entities;

import domain.enums.AccountType;
import domain.exception.DomainException;

public class SavingsAccount extends Account {

	private double interestRate;

	// Call to the Super Class constructor
	public SavingsAccount() {
		super();
	}

	public SavingsAccount(String number, String holder, double balance, double withDrawLimit, AccountType type,
			double interesetRate) {
		
		super(number, holder, balance, withDrawLimit, type); // Passing parameters to the superclass constructor

		if (interesetRate < 0.0) {
			throw new DomainException("Account error: The interest rate cannot be lower than zero.");
		}

		this.interestRate = interesetRate;
	}

	// Getters e setters
	public double getInterestRate() {
		return interestRate;
	}

	public void setInteresetRate(double interesetRate) {

		if (interesetRate < 0.0) {
			throw new DomainException("Account error: The interest rate cannot be lower than zero.");
		}

		this.interestRate = interesetRate;
	}

	public void deposit(double amount) {
		if (amount <= 0.0) {
			throw new DomainException("Deposit error: the amount cannot be less than or equal to zero.");
		}

		balance += amount;
	}

	// Implement a method inherited from the generic class Account.
	@Override
	public void withDraw(double amount) {

		validateWithDraw(amount);
		withDrawLimit -= amount;
		balance -= amount;
	}

	// method to Validate withdrawal method
	public void validateWithDraw(double amount) throws DomainException {

		if (withDrawLimit < amount) {
			throw new DomainException("Withdraw error: The amount exceeds withdraw limit.");
		}

		if (balance < amount) {
			throw new DomainException("Withdraw error: Not enough balance.");
		}
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Holder: ").append(getHolder()).append(", ");
		sb.append("Account Number: ").append(getNumber()).append("\n");
		sb.append("Balance: $").append(String.format("%.2f", balance)).append("\n");
		sb.append("Account Type: ").append(getType().getAccountType());
		return sb.toString();
	}
}
