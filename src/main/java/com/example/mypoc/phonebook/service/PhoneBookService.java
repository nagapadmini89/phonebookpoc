package com.example.mypoc.phonebook.service;

import com.example.mypoc.phonebook.model.PhoneBook;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;


@Component
public interface PhoneBookService {
    Optional<PhoneBook> createPhoneEntry(PhoneBook phoneBook);

    Optional<List<PhoneBook>> getAll();

    Optional<PhoneBook> findBySurname(String surname);

    Optional<PhoneBook> updatePhoneBook(PhoneBook phoneBook);

    Optional<String> deleteEntry(int id);
}
