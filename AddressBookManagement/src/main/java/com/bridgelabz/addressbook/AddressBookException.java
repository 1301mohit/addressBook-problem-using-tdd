package com.bridgelabz.addressbook;

public class AddressBookException extends RuntimeException {

    public enum ExceptionType {
        NOT_FOUND, INCORRECT_PATH, FIRST_NAME_MISSING, LAST_NAME_MISSING, PHONE_NUMBER_MISSING, CITY_MISSING, State_MISSING, ZIP_MISSING, ALREADY_EXIST, NOT_EXIST;
    }

    ExceptionType type;

    public AddressBookException(String s, ExceptionType type) {
        super(s);
        this.type = type;
    }

}
