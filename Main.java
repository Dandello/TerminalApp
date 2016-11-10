package com.company;

public class Main {

    public static void main(String[] args) throws AccountIsLockedException {
        TerminalServer terminalServer = new TerminalServer();
        PinValidator pinValidator = new PinValidator();
        Terminal terminal = new Terminal(terminalServer, pinValidator);
        try {
            terminal.login("1234");
            terminal.getMoney(4000);
            System.out.print(terminal.checkAccountStatus());

        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
