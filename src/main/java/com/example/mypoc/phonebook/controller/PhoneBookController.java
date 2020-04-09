package com.example.mypoc.phonebook.controller;

import com.example.mypoc.phonebook.model.PhoneBook;
import com.example.mypoc.phonebook.service.PhoneBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/phonebook", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
public class PhoneBookController {

    @Autowired
    private PhoneBookService phoneBookService;

    @GetMapping(value = "/list")
    public ResponseEntity<List<PhoneBook>> getAllFromPhoneBook() throws Exception {
        Optional<List<PhoneBook>> phoneBookData = phoneBookService.getAll();
        if (!phoneBookData.isPresent()) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(phoneBookData.get(), HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<PhoneBook> createPhoneEntry(@Valid @RequestBody PhoneBook phoneBook) throws Exception {
        Optional<PhoneBook> addData = phoneBookService.createPhoneEntry(phoneBook);
        if (!addData.isPresent()) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(addData.get(), HttpStatus.CREATED);
    }

    @GetMapping(value = "/search/{surname}")
    public ResponseEntity<PhoneBook> searchBySurname(@PathVariable String surname) throws Exception {
        Optional<PhoneBook> findBySurname = null;
        if (!StringUtils.isEmpty(surname)) {
            findBySurname = phoneBookService.findBySurname(surname);
            if (!findBySurname.isPresent()) {
                return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        return new ResponseEntity<>(findBySurname.get(), HttpStatus.OK);
    }

    @PutMapping(value = "/update")
    public ResponseEntity<PhoneBook> updatePhoneBook(@RequestBody PhoneBook phoneBook) throws Exception {
        Optional<PhoneBook> updateData = phoneBookService.updatePhoneBook(phoneBook);
        if (!updateData.isPresent()) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(updateData.get(), HttpStatus.OK);
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<String> deletePhoneEntry(@PathVariable("id") int id) throws Exception {
        Optional<String> deleteData = phoneBookService.deleteEntry(id);
        if (!deleteData.isPresent()) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(deleteData.get(), HttpStatus.OK);
    }
}
