package com.company;

public class PinValidator {
    public boolean isValid(String pinCode) {
        return pinCode.matches("\\d{4}");
    }
}
