package com.example.mypoc.phonebook.repository;

import com.example.mypoc.phonebook.entity.PhoneBookEntity;
import org.springframework.data.cassandra.repository.AllowFiltering;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhoneBookRepository extends CassandraRepository<PhoneBookEntity, Integer> {
    @AllowFiltering
    PhoneBookEntity findBySurname(String surname);
}
