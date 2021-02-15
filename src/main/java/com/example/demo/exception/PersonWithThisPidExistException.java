package com.example.demo.exception;



public class PersonWithThisPidExistException extends Exception{

    public PersonWithThisPidExistException(long pid) {
        super("Person with this PID exist " + pid);
    }
}
