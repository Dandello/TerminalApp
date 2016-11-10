package com.company;

public class Account {
    private String pinCode;
    private double currentAmount;
    public boolean locked;
    public long timeToUnlock;
    private int failCount;

    Account(String pinCode, double currentAmount) {
        this.pinCode = pinCode;
        this.currentAmount = currentAmount;
    }

    public String getPinCode() {
        return pinCode;
    }
    public void addAmount(int amount) {
            currentAmount += amount;
    }

    public void lock() {
        locked = true;
        timeToUnlock = System.currentTimeMillis() + 5000;
    }

    public double getCurrentAmount() {
        return currentAmount;
    }

    public double getTimeToUnlock() {
        if(!locked)
            return 0;
        return timeToUnlock;
    }
    public boolean isLocked() {
        if(locked)
            if(timeToUnlock - System.currentTimeMillis() > 0)
                return true;
            else {
                failCount = 0;
                locked = false;
            }
        return false;
    }

    public int failCountInc() {
        return ++failCount;
    }
}
