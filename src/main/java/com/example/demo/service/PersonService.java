package com.example.demo.service;

import com.example.demo.entity.Person;
import com.example.demo.exception.PersonNotFoundException;
import com.example.demo.exception.PersonWithThisPidExistException;

import java.util.List;

public interface PersonService   {
    List<Person> getAll();
    Person getByID(long pid) throws PersonNotFoundException;
    void deleteByID(long pid) throws PersonNotFoundException;
    void updateByID(Person person, long pid) throws PersonNotFoundException;
    void create(Person person) throws PersonWithThisPidExistException;
}
