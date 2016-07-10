package com.jscl.test.address_service.service;

import com.jscl.test.address_service.model.AddressRecord;
import com.jscl.test.address_service.model.Gender;
import com.jscl.test.address_service.model.Person;
import com.jscl.test.address_service.provider.AddressRecordProvider;
import org.joda.time.Days;

import java.time.temporal.ChronoUnit;

/**
 * Created by saflinhussain.
 */
public class GumtreeAddressBookService implements AddressBookService {

    private AddressRecordProvider addressRecordProvider;

    public GumtreeAddressBookService(final AddressRecordProvider addressRecordProvider) {
        this.addressRecordProvider = addressRecordProvider;
    }

    public long getAddressRecordCountByGender(Gender gender) {
        return addressRecordProvider.provideAddressRecords()
                .stream()
                .filter(addressRecord -> addressRecord.getPerson().getGender() == gender)
                .count();

    }

    @Override
    public String findOldestPerson() {
        Person person = addressRecordProvider.provideAddressRecords()
                .stream()
                .map(addressRecord -> addressRecord.getPerson()).
                        reduce((person1, person2) -> person1.getDateOfBirth().isBefore(person2.getDateOfBirth()) ? person1 : person2).
                        orElse(null);
        return person != null ? person.getName() : null;
    }

    @Override
    public int findAgeDifferenceBetweenPerson(String firtPersonName, String secondPersonName) {
        Person person1 = null;
        Person person2 = null;
        for (AddressRecord addressRecord : addressRecordProvider.provideAddressRecords()) {
            if (person1 == null && addressRecord.getPerson().getName().equals(firtPersonName)) {
                person1 = addressRecord.getPerson();
            }

            if (person2 == null && addressRecord.getPerson().getName().equals(secondPersonName)) {
                person2 = addressRecord.getPerson();
            }

            if (person1 != null && person2 != null)
                break;
        }
        if(person1 == null) throw new IllegalArgumentException("Unable to find address record with name : "+firtPersonName);

        if(person2 == null) throw new IllegalArgumentException("Unable to find address record with name : "+secondPersonName);

        //Since the DOB is in dd/MM/yy format, current implementation wont be able to find age diff between some
        // on who is born in and after year 2000.
        return Days.daysBetween(person1.getDateOfBirth(), person2.getDateOfBirth()).getDays();
    }

}
