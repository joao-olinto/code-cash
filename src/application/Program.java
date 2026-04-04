package application;

import java.util.InputMismatchException;

import java.util.Locale;
import java.util.Scanner;
import domain.Exception.DomainException;
import domain.entities.Account;
import domain.entities.BusinessAccount;
import domain.entities.SavingsAccount;
import domain.enums.AccountType;
import domain.service.AccountService;

public class Program {

	public static void main(String[] args) {
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);

		// Exception handling throughout the entire program execution.
		try {
			AccountService service = new AccountService();
			int option = 0;
			do {
				option = showMenu(sc);

				switch (option) {

				case 1:
					createAccount(sc, service);
					break;

				case 2:
					deposit(sc, service);
					break;
				case 3:
					withDraw(sc, service);
					break;
				case 4:
					showAccounts(service);
					break;
				case 0:
					System.out.println("Thank you for using CodeCash!");
					break;
				}

			} while (option != 0);

			// exceptions related to business rules and invalid states.
		} catch (DomainException e) {
			System.out.println(e.getMessage());

			// exceptions related to invalid input.
		} catch (InputMismatchException e) {
			sc.nextLine();

			System.out.println("Input error: " + e.getMessage());

			// exceptions not directly addressed.
		} catch (RuntimeException e) {
			System.out.println("Unexpected error ocurred.");
		} finally {
			sc.close();
		}

	}

	// displays the program menu.
	public static int showMenu(Scanner sc) {

		System.out.println("======= CODECASH BANK =========");
		System.out.println("1 - Create account");
		System.out.println("2 - Deposit ");
		System.out.println("3 - WithDraw");
		System.out.println("4 - List accounts ");
		System.out.println("5 - Remove account");
		System.out.println("0 - exit");
		System.out.println();
		System.out.print("Choose an option: ");
		// return integer number
		return sc.nextInt();
	}

	// Creates accounts according to the type chosen by the user.
	public static void createAccount(Scanner sc, AccountService service) {

		// menu option variable
		int optionAccount = 0;

		// polymorphic reference variable.
		Account acc;

		System.out.println("Choose account: ");
		System.out.println("1 - Savings account: ");
		System.out.println("2 - BusinessAcount: ");
		System.out.print(": ");
		optionAccount = sc.nextInt();
		System.out.println();

		System.out.print("Account number: ");
		sc.nextLine();
		String accountNumber = sc.nextLine();
		System.out.print("Account holder: ");
		String accountHolder = sc.nextLine();
		System.out.print("Initial deposit: ");
		double initialDeposit = sc.nextDouble();
		System.out.print("With draw limit: ");
		double withDrawLimit = sc.nextDouble();

		// if you choose a savings account.
		if (optionAccount == 1) {
			AccountType type = AccountType.SAVINGS;
			System.out.print("Interest rate: ");
			double interestRate = sc.nextDouble();
			acc = new SavingsAccount(accountNumber, accountHolder, initialDeposit, withDrawLimit, type, interestRate);
			/*
			 * This is where polymorphism occurs: the method is invoked through a reference
			 * to the base class, but the behavior executed is that of the concrete class of
			 * the object (the actual instance).
			 */
			service.addAccount(acc);

			// if you choose a business account.
		} else if (optionAccount == 2) {
			AccountType type = AccountType.BUSINESS;
			System.out.print("Loan limit: ");
			double loanLimit = sc.nextDouble();
			acc = new BusinessAccount(accountNumber, accountHolder, initialDeposit, withDrawLimit, type, loanLimit);
			service.addAccount(acc);

		}

	}

	// method that performs the deposit through the service object.
	public static void deposit(Scanner sc, AccountService service) {

		System.out.print("Enter account number: ");
		String accountNumber = sc.nextLine();
		System.out.print("Enter deposit amount: ");
		double amount = sc.nextDouble();
		service.deposit(accountNumber, amount);
		System.out.println("Deposit successful! ");

		// Use this method to find the account using its number.
		Account acc = service.findAccountByNumber(accountNumber);
		System.out.println("holder: " + acc.getHolder());
		System.out.println("Account number: " + acc.getNumber());
		System.out.printf("Current balance: $%.2f%n", acc.getBalance());
	}

	// method that performs the withdrawl through the service object.
	public static void withDraw(Scanner sc, AccountService service) {

		System.out.print("Enter account number: ");
		String accountNumber = sc.nextLine();
		System.out.print("Enter withdraw amount: ");
		double amount = sc.nextDouble();
		service.withDraw(accountNumber, amount);
		System.out.println("Withdraw successful! ");
		Account acc = service.findAccountByNumber(accountNumber);
		System.out.println("holder: " + acc.getHolder());
		System.out.println("Account number: " + acc.getNumber());
		System.out.printf("Current balance: $%.2f%n", acc.getBalance());

	}

	//method that shows all created accounts
	public static void showAccounts(AccountService service) {

		System.out.println("======= ACCOUNT LIST =======");

		//Instead of creating the list of accounts directly in the main method, we use it through the service object.
		for (Account acc : service.getAccounts()) {
			System.out.println();
			System.out.println(acc);
		}
	}

	//A method that removes an item using the service object.
	public static void RemoveAccount(Scanner sc, AccountService service) {
		System.out.print("Enter account number: ");
		String accountNumber = sc.nextLine();
		service.removeAccount(accountNumber);
		System.out.println("Account removed sucessfully.");
	}
}
