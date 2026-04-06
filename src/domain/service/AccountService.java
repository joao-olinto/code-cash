package domain.service;

import java.util.ArrayList;
import java.util.List;

import domain.entities.Account;
import domain.exception.DomainException;

public class AccountService {

	// List of polymorphic accounts
	private final List<Account> accounts = new ArrayList<>();

	// method for adding accounts to the list
	public void addAccount(Account acc) {

		// Check if an account with the same number already exists.
		// If it exists, throw the error; otherwise, add it to the list of accounts.
		if(accounts.stream().anyMatch(account -> account.getNumber().equals(acc.getNumber()))) {
			throw new DomainException("Service Error: There is an account with that number.");
		}
		
		accounts.add(acc);
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
		
		//exception if there are no elements in the list.
		if(accounts.isEmpty()) {
			throw new DomainException("Service error: There are no accounts listed.");
		}
		return List.copyOf(accounts);
	}
}
