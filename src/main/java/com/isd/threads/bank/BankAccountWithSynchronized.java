package com.isd.threads.bank;

public class BankAccountWithSynchronized extends BankAccount {
    private final Object lock = new Object();

    public BankAccountWithSynchronized() {
        balance = 0.0;
    }

    public void deposit(double amount) {
        synchronized (lock) {
            super.deposit(amount);
        }
    }

    public void withdraw(double amount) {
        synchronized (lock) {
            super.withdraw(amount);
        }
    }

    public static void main(String[] args) {
        BankAccountWithSynchronized account = new BankAccountWithSynchronized();
        account.execute();
    }
}
