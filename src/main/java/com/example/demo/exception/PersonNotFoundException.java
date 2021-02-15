package com.example.demo.exception;

public class PersonNotFoundException extends Exception {

    public PersonNotFoundException(long pid) {
        super("Can't find person with " + pid);
    }
}