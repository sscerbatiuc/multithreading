package com.isd.threads.bank;

public class BankAccount {

    protected double balance;
    public BankAccount() {
        balance = 0.0;
    }

    public BankAccount getAccount() {
        return new BankAccount();
    }

    public void deposit(double amount) {
        System.out.println("------------- Trying to deposit: " + amount + ". Balance: " + balance);
        balance += amount;
        System.out.println("Deposited: " + amount);
        System.out.println("Balance after deposit: " + balance);
    }

    public void withdraw(double amount) {
        System.out.println("------------- Trying to withdraw: " + amount + ". Balance: " + balance);
        if (balance >= amount) {
            balance -= amount;
            System.out.println("Withdrawn: " + amount);
            System.out.println("Balance after withdrawal: " + balance);
        } else {
            System.out.println("Try to Withdraw: " + amount + ". Balance: " + balance);
            System.out.println("Insufficient funds.");
        }
    }

    public void execute() {
        Thread depositThread1 = new Thread(() -> this.deposit(1000), "Deposit Thread (1000)");
        Thread depositThread2 = new Thread(() -> this.deposit(300), "Deposit Thread (300)");
        Thread withdrawalThread1 = new Thread(() -> this.withdraw(150), "Withdrawal Thread (150)");
        Thread withdrawalThread2 = new Thread(() -> this.withdraw(1200), "Withdrawal Thread (1200)");

        depositThread1.start();
        depositThread2.start();

        withdrawalThread1.start();
        withdrawalThread2.start();
    }
}
