package com.example.demo.controller;

import com.example.demo.entity.Person;
import com.example.demo.exception.PersonNotFoundException;
import com.example.demo.exception.PersonWithThisPidExistException;
import com.example.demo.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/persons")
public class PersonController {

    PersonService personService;

    @Autowired
    public PersonController (PersonService personService){
        this.personService = personService;
    }

    @GetMapping(produces = "application/json")
    public ResponseEntity<List<Person>> getPerson(){
        List<Person> list = personService.getAll();
        return new ResponseEntity<List<Person>>(list, HttpStatus.OK);
    }

    @GetMapping("{pid}")
    public ResponseEntity<Person> getPersonByPid(@PathVariable("pid") Long pid) {

        try {
            Person person = personService.getByID(pid);
            return new ResponseEntity<Person>(person, HttpStatus.OK);
        } catch (PersonNotFoundException ex) {

            return ResponseEntity.notFound().build();
        }

    }

    @DeleteMapping("{pid}")
    public ResponseEntity<Person> deletePersonByPid(@PathVariable("pid") Long pid) {
        try {
            personService.deleteByID(pid);
            return ResponseEntity.ok().build();
        } catch (PersonNotFoundException e) {
            //log.error("deletePerson", e);
            return ResponseEntity.notFound().build();

        }

    }
    @PostMapping
    public ResponseEntity<Person> addPerson(@RequestBody Person person) {
        try {
            personService.create(person);
            return ResponseEntity.ok().build();
        } catch (PersonWithThisPidExistException e) {
            //log.error("deletePerson", e);
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("{pid}")
    public ResponseEntity<Person> updatePerson(@RequestBody Person person, @PathVariable("pid") Long pid) {
        try {
            personService.updateByID(person,pid);
            return ResponseEntity.ok().build();
        } catch (PersonNotFoundException e) {
            //log.error("deletePerson", e);
            return ResponseEntity.badRequest().build();
        }
    }




}
