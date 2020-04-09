package com.example.mypoc.phonebook.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import javax.validation.constraints.NotNull;
import java.math.BigInteger;


@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(makeFinal = false, level = AccessLevel.PRIVATE)
public class PhoneBook {

    Integer phonebookId;
    @NotNull
    String surname;

    @NotNull
    String firstName;

    @NotNull
    BigInteger phoneNumber;

    String address;

}
