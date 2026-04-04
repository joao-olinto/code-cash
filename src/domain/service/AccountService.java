package domain.service;

import java.util.ArrayList;
import java.util.List;

import domain.Exception.DomainException;
import domain.entities.Account;

public class AccountService {

	// List of polymorphic accounts
	private List<Account> accounts = new ArrayList<>();

	// method for adding accounts to the list
	public void addAccount(Account acc) {

		// Check if an account with the same number already exists.
		// If it exists, throw the error; otherwise, add it to the list of accounts.
		accounts.stream().filter(account -> account.getNumber().equals(acc.getNumber())).findFirst()
				.ifPresentOrElse(account -> {
					throw new DomainException("Service Error: There is an account with that number.");
				}, () -> {
					accounts.add(acc);
				});
	}

	// method for remove accounts to the list
	public void removeAccount(String number) {

		// If the account exists, it removes it from the list; otherwise, it throws an
		// exception.
		accounts.stream().filter(acc -> acc.getNumber().equals(number)).findFirst().ifPresentOrElse(acc -> {
			accounts.remove(acc);
		}, () -> {
			throw new DomainException("Service error: Account does not exist or invalid number.");
		});
	}

	// method to find account by number
	public Account findAccountByNumber(String number) {

		// If the account exists, it will be returned; otherwise, it throws an
		// exception.
		return accounts.stream().filter(acc -> acc.getNumber().equals(number)).findFirst()
				.orElseThrow(() -> new DomainException("Service error: Account does not exist or invalid number"));

	}

	// method to make a deposit
	public void deposit(String number, double amount) {
		Account acc = findAccountByNumber(number);
		acc.deposit(amount);
	}

	// method to make a with draw
	public void withDraw(String number, double amount) {
		Account acc = findAccountByNumber(number);
		acc.withDraw(amount);
	}

	// returns a shallow copy of the list
	public List<Account> getAccounts() {
		return List.copyOf(accounts);
	}
}
