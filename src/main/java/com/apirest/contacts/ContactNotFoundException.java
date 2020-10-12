package com.apirest.contacts;

public class ContactNotFoundException extends RuntimeException{

    public ContactNotFoundException (String message) {
        super(message);
    }

    public ContactNotFoundException (String message, Throwable cause) {
        super(message, cause);
    }
}
