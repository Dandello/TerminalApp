package com.company;


public class Terminal implements TerminalInterface {
    private final TerminalServer server;
    private final PinValidator pinValidator;
    private boolean isLogined;

    Terminal(TerminalServer server, PinValidator pinValidator) {
        this.pinValidator = pinValidator;
        this.server = server;
    }
    public void login(String pinCode) throws AccountIsLockedException {
        if(pinValidator.isValid(pinCode)) {
            server.checkPin(pinCode);
            isLogined = true;
        }
        else
            throw new IllegalStateException("PIN code is not valid. Enter 4 digits and try again!");

    }
    public double checkAccountStatus() {
        if(!isLogined)
            throw new IllegalStateException("PIN code is not entered. This operation is not allowed!");
        return server.getCurrentAmount();
    }

    public double putMoney(int amount) {
        if(!correctAmount(amount))
            throw new IllegalStateException("This sum is not correct. Please, try again!");
        if(!isLogined)
            throw new IllegalStateException("PIN code is not entered. This operation is not allowed!");
        return server.put(amount);
    }

    public double getMoney(int amount) {
        if(!correctAmount(amount))
            throw new IllegalStateException("This sum is not correct. Please, try again!");
        if(!isLogined)
            throw new IllegalStateException("PIN code is not entered. This operation is not allowed!");
        return server.get(amount);
    }

    public boolean correctAmount (int amount) {
        if (amount % 100 == 0)
            return true;
        return false;
    }

}
