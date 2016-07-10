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

}