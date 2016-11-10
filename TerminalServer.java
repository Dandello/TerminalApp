package com.company;

import java.util.HashMap;
import java.util.StringJoiner;

public class TerminalServer {
    Account account;
    TerminalServer() {
        account = new Account("1234", 3500);
    }

    public double put(int amount) {
        account.addAmount(amount);
        return account.getCurrentAmount();

    }

    public double get(int amount) {
        if(account.getCurrentAmount() - amount < 0)
            throw new IllegalStateException("You do not have enough money for this operation!");
        account.addAmount(-amount);
        return account.getCurrentAmount();
    }
    public double getCurrentAmount() {
        return account.getCurrentAmount();
    }

    boolean checkPin(String pinCode) throws AccountIsLockedException {
        int failCount = 0;
        if(account.isLocked())
            throw new AccountIsLockedException("Your account is locked! PLease, try again after " +
                    (account.getTimeToUnlock() - System.currentTimeMillis()) / 1000 + " seconds!");
        if(account.getPinCode().equals(pinCode))
            return true;
        failCount = account.failCountInc();
        if (failCount < 3)
            throw new AccountIsLockedException("PIN code is not correct! You have " + (3 - failCount) +
                        " attempts to enter it!");
        else {
            account.lock();
            throw new AccountIsLockedException("You entered wrong PIN code 3 time in a row. " +
                        "Your account is locked for 5 seconds");
        }


    }
}
