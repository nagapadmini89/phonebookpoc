package com.example.mypoc.phonebook.mapper;

import com.example.mypoc.phonebook.entity.PhoneBookEntity;
import com.example.mypoc.phonebook.model.PhoneBook;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface PhoneBookMapper {

    PhoneBookMapper mapper = Mappers.getMapper(PhoneBookMapper.class);

    PhoneBookEntity toPhoneBook(PhoneBook phoneBook);

    PhoneBook fromPhoneBook(PhoneBookEntity phoneBookEntity);

    List<PhoneBook> listOfPhoneBook(List<PhoneBookEntity> phoneBookEntityList);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE, nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
    void toPhoneBookForUpdate(PhoneBook phoneBook, @MappingTarget PhoneBookEntity phoneBookEntity);
}
