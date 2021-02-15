package com.example.demo.service;

import com.example.demo.entity.Person;
import com.example.demo.exception.PersonNotFoundException;
import com.example.demo.exception.PersonWithThisPidExistException;
import com.example.demo.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class PersonServiceImpl implements PersonService{


    private final PersonRepository personRepository;

    @Autowired
    public PersonServiceImpl (PersonRepository personRepository){
        this.personRepository = personRepository;
    }

    @Override
    public List<Person> getAll() {
        return personRepository.findAll();
    }

    @Override
    public Person getByID(long pid) throws PersonNotFoundException {
        return personRepository.findById(pid).orElseThrow(() -> new PersonNotFoundException(pid));

    }

    @Override
    public void deleteByID(long pid) throws PersonNotFoundException {
        Person person = personRepository.findById(pid).orElseThrow(() -> new PersonNotFoundException(pid));
        personRepository.delete(person);
    }

    @Override
    public void updateByID(Person person, long pid) throws PersonNotFoundException {
        Person oldPerson = personRepository.findById(pid).orElseThrow(() -> new PersonNotFoundException(pid));
        personRepository.delete(oldPerson);
        person.setPid(pid);
        personRepository.save(person);

    }

    @Override
    public void create(Person person) throws PersonWithThisPidExistException {
        if (personRepository.findById(person.getPid()).isPresent()){
            throw new PersonWithThisPidExistException(person.getPid());
        }else {
            personRepository.save(person);
        }
    }
}
