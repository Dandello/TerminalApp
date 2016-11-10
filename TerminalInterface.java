package com.company;
public interface TerminalInterface {

    double checkAccountStatus();
    double putMoney(int amount);
    double getMoney(int amount);
    boolean correctAmount (int amount);
}
