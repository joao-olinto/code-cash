package domain.entities;

import domain.Exception.DomainException;
import domain.enums.AccountType;

public class BusinessAccount extends Account {

	// Specific fee for withdrawals by companies.
	public static final double COMPANYFEE = 15.00;

	private double loanLimit;

	//// Call to the Super Class constructor
	public BusinessAccount() {
		super();
	}

	public BusinessAccount(String number, String holder, double initialDeposit, double withDrawLimit, AccountType type,
			double loanLimit) {
		super(number, holder, initialDeposit, withDrawLimit, type); // Passing parameters to the superclass constructor

		if (loanLimit < 0.0) {
			throw new DomainException("Account error: The loan limit cannot be less than zero");
		}

		this.loanLimit = loanLimit;
	}

	// getters e setters

	public double getLoanLimit() {
		return loanLimit;
	}

	public void setLoanLimit(double loanLimit) {

		if (loanLimit < 0.0) {
			throw new DomainException("Account error: The loan limit cannot be less than zero");
		}

		this.loanLimit = loanLimit;
	}

	public void deposit(double amount) {

		if (amount <= 0.0) {
			throw new DomainException("Deposit error: the amount cannot be less than or equal to zero.");
		}

		balance += amount;
	}

	@Override
	public void withDraw(double amount) {

		validateWithDraw(amount);
		withDrawLimit -= amount;
		balance -= (amount + COMPANYFEE);

	}

	// method to Validate withdrawal method
	private void validateWithDraw(double amount) throws DomainException {

		if (amount <= 0.0) {
			throw new DomainException("With draw error: the amount cannot be less than or equal to zero.");
		}

		if (withDrawLimit < (amount + COMPANYFEE)) {
			throw new DomainException("With draw eror: The amount exceeds withdraw limit.");
		}

		if (balance < (amount + COMPANYFEE)) {
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
