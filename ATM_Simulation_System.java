import java.util.Scanner;

class BankAccount {
    private double balance;

    public BankAccount(double initialBalance) {
        this.balance = initialBalance;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("₹" + amount + " deposited successfully.");
        } else {
            System.out.println("Invalid deposit amount.");
        }
    }

    public void withdraw(double amount) {
        if (amount <= 0) {
            System.out.println("Invalid withdrawal amount.");
        } else if (amount > balance) {
            System.out.println("Insufficient Balance!");
        } else {
            balance -= amount;
            System.out.println("Please collect your cash: ₹" + amount);
        }
    }
}

class ATM {
    private final int PIN = 1234;
    private boolean authenticated = false;
    private BankAccount account;
    private Scanner sc;

    public ATM(BankAccount account) {
        this.account = account;
        this.sc = new Scanner(System.in);
    }

    public void start() {
        System.out.println("=================================");
        System.out.println("       WELCOME TO ATM");
        System.out.println("=================================");

        if (verifyPIN()) {
            authenticated = true;
            showMenu();
        } else {
            System.out.println("Too many incorrect attempts.");
            System.out.println("ATM Session Terminated.");
        }
    }

    private boolean verifyPIN() {
        int attempts = 3;

        while (attempts > 0) {
            System.out.print("Enter 4-Digit PIN: ");
            int enteredPIN = sc.nextInt();

            if (enteredPIN == PIN) {
                System.out.println("Login Successful!");
                return true;
            }

            attempts--;
            System.out.println("Incorrect PIN.");
            System.out.println("Attempts Remaining: " + attempts);
        }

        return false;
    }

    private void showMenu() {

        while (authenticated) {

            System.out.println("\n========== ATM MENU ==========");
            System.out.println("1. Balance Inquiry");
            System.out.println("2. Cash Withdrawal");
            System.out.println("3. Cash Deposit");
            System.out.println("4. Logout");
            System.out.print("Enter Choice: ");

            int choice = sc.nextInt();

            switch (choice) {

                case 1:
                    checkBalance();
                    break;

                case 2:
                    withdrawCash();
                    break;

                case 3:
                    depositCash();
                    break;

                case 4:
                    logout();
                    break;

                default:
                    System.out.println("Invalid Choice!");
            }
        }
    }

    private void checkBalance() {
        System.out.println("Available Balance: ₹" +
                account.getBalance());
    }

    private void withdrawCash() {
        System.out.print("Enter Amount to Withdraw: ₹");
        double amount = sc.nextDouble();

        account.withdraw(amount);
    }

    private void depositCash() {
        System.out.print("Enter Amount to Deposit: ₹");
        double amount = sc.nextDouble();

        account.deposit(amount);
    }

    private void logout() {
        authenticated = false;
        System.out.println("Session Ended Successfully.");
        System.out.println("Thank You for Using Our ATM.");
    }
}

public class ATM_Simulation_System {
    public static void main(String[] args) {

        BankAccount account = new BankAccount(10000);

        ATM atm = new ATM(account);

        atm.start();
    }
}