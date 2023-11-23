package com.isd.threads.bank;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BankAccountWithReentrantLock extends BankAccount {
    private final Lock lock;

    public BankAccountWithReentrantLock() {
        balance = 0.0;
        lock = new ReentrantLock();
    }

    public void deposit(double amount) {
        lock.lock();
        try {
          super.deposit(amount);
        } finally {
            lock.unlock();
        }
    }

    public void withdraw(double amount) {
        lock.lock();
        try {
           super.withdraw(amount);
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        BankAccountWithReentrantLock account = new BankAccountWithReentrantLock();
        account.execute();
    }
}
