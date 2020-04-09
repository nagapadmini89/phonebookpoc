package com.example.mypoc.phonebook.service.impl;

import com.example.mypoc.phonebook.entity.PhoneBookEntity;
import com.example.mypoc.phonebook.mapper.PhoneBookMapper;
import com.example.mypoc.phonebook.model.PhoneBook;
import com.example.mypoc.phonebook.repository.PhoneBookRepository;
import com.example.mypoc.phonebook.service.PhoneBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class PhoneBookServiceImpl implements PhoneBookService {

    @Autowired
    private PhoneBookRepository phoneBookRepository;

    @Autowired
    private Environment environment;

    @Override
    public Optional<PhoneBook> createPhoneEntry(PhoneBook phoneBook) {
        PhoneBookEntity entity = PhoneBookMapper.mapper.toPhoneBook(phoneBook);
        entity.setPhonebookId(new Random().nextInt(Integer.parseInt(environment.getProperty("bound"))));
        PhoneBookEntity savedData = phoneBookRepository.save(entity);
        if (null != savedData) {
            return Optional.ofNullable(PhoneBookMapper.mapper.fromPhoneBook(savedData));
        }
        return Optional.empty();
    }

    @Override
    public Optional<List<PhoneBook>> getAll() {
        List<PhoneBookEntity> getAll = phoneBookRepository.findAll();
        if (null != getAll) {
            return Optional.ofNullable(PhoneBookMapper.mapper.listOfPhoneBook(getAll));
        }
        return Optional.empty();
    }

    @Override
    public Optional<PhoneBook> findBySurname(String surname) {
        PhoneBookEntity getPhoneEntry = phoneBookRepository.findBySurname(surname);
        if (null != getPhoneEntry) {
            return Optional.ofNullable(PhoneBookMapper.mapper.fromPhoneBook(getPhoneEntry));
        }
        return Optional.empty();
    }

    @Override
    public Optional<PhoneBook> updatePhoneBook(PhoneBook phoneBook) {
        Optional<PhoneBookEntity> datafromDB = phoneBookRepository.findById(phoneBook.getPhonebookId());
        PhoneBookMapper.mapper.toPhoneBookForUpdate(phoneBook,datafromDB.get());
        PhoneBookEntity updatedData = phoneBookRepository.save(datafromDB.get());
        if (null != updatedData) {
            return Optional.ofNullable(PhoneBookMapper.mapper.fromPhoneBook(updatedData));
        }
        return Optional.empty();
    }

    @Override
    public Optional<String> deleteEntry(int id) {
        Optional<PhoneBookEntity> dataToDelete = phoneBookRepository.findById(id);
        if(dataToDelete.isPresent()){
            phoneBookRepository.delete(dataToDelete.get());
            return Optional.ofNullable("Deleted Successfully");
        }
        return Optional.empty();
    }
}
