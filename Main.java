package com.company;

public class Main {

    public static void main(String[] args) throws AccountIsLockedException {
        TerminalServer terminalServer = new TerminalServer();
        PinValidator pinValidator = new PinValidator();
        Terminal terminal = new Terminal(terminalServer, pinValidator);

        terminal.login("1234");
        terminal.putMoney(100);
        System.out.print(terminal.checkAccountStatus());

    }
}
