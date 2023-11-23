package com.isd.threads.bank;

public class BankAccountNonSynchronized extends BankAccount {

    public static void main(String[] args) {
        BankAccountNonSynchronized account = new BankAccountNonSynchronized();
        account.execute();
    }


}
