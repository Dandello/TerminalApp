package com.company;

import static org.junit.Assert.*;

public class TerminalTest {
    TerminalServer terminalServer;
    PinValidator pinValidator;
    Terminal terminal;
    @org.junit.Before
    public void setUp() throws Exception {
        terminalServer = new TerminalServer();
        pinValidator = new PinValidator();
        terminal = new Terminal(terminalServer,pinValidator);
    }

    @org.junit.After
    public void tearDown() throws Exception {

    }

    @org.junit.Test
    public void login() throws Exception {
        assertTrue("login", terminalServer.checkPin("1234"));
    }

    @org.junit.Test
    public void checkAccountStatus() throws Exception {
        terminal.login("1234");
        assertEquals(3500, terminal.checkAccountStatus(), 2);
    }

    @org.junit.Test
    public void putMoney() throws Exception {
        terminal.login("1234");
        assertEquals(3600, terminal.putMoney(100), 2);
    }

    @org.junit.Test
    public void getMoney() throws Exception {
        terminal.login("1234");
        assertEquals(3400, terminal.getMoney(100), 2);

    }

    @org.junit.Test(expected = AccountIsLockedException.class)
    public void incorrectPin() throws Exception{
        terminal.login("7777");
    }

    @org.junit.Test (expected = AccountIsLockedException.class)
    public void timeToUnlock1() throws Exception{
        try{
            terminal.login("7777");
        } catch(AccountIsLockedException e) {System.out.println(e.getMessage());}
        try{
            terminal.login("7777");
        } catch(AccountIsLockedException e) {System.out.println(e.getMessage());}
        try{
            terminal.login("7777");
        } catch(AccountIsLockedException e) {System.out.println(e.getMessage());}
        Thread.sleep(3000);
        terminal.login("1234");

    }

    @org.junit.Test
    public void timeToUnlock2() throws Exception{
        try{
            terminal.login("7777");
        } catch(AccountIsLockedException e) {System.out.println(e.getMessage());}
        try{
            terminal.login("7777");
        } catch(AccountIsLockedException e) {System.out.println(e.getMessage());}
        try{
            terminal.login("7777");
        } catch(AccountIsLockedException e) {System.out.println(e.getMessage());}
        Thread.sleep(5000);
        assertTrue(terminalServer.checkPin("1234"));

    }

}