package com.example.mypoc.phonebook.entity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import java.io.Serializable;
import java.math.BigInteger;


@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(makeFinal = false, level = AccessLevel.PRIVATE)
@Table("phone_book")
public class PhoneBookEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @PrimaryKey("phonebook_id")
    int phonebookId;

    @Column("surname")
    String surname;

    @Column("first_name")
    String firstName;

    @Column("phone_number")
    BigInteger phoneNumber;

    @Column("address")
    String address;

}
