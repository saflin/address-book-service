package com.jscl.test.address_service.model;

import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;


/**
 * Created by saflinhussain.
 */
public class AddressRecordFactory {

    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormat.forPattern("dd/MM/yy");

    /**
     * Create address record from set of information.
     * @param name Name of the person
     * @param dateOfBirth Date of birth of the person
     * @param genderVal Gender of the person
     * @return AddressRecord
     */
    public static AddressRecord createRecord(final String name, final String genderVal, final String dateOfBirth) {
        LocalDate dob = LocalDate.parse(dateOfBirth,DATE_TIME_FORMATTER);
        Gender gender = Gender.valueOf(genderVal.toUpperCase());
        Person person = new Person(name, dob, gender);
        return new AddressRecord(person);
    }
}
