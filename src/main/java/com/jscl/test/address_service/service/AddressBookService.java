package com.jscl.test.address_service.service;

import com.jscl.test.address_service.model.Gender;

/**
 * Created by saflinhussain.
 */
public interface AddressBookService {
    long getAddressRecordCountByGender(Gender gender);
    String findOldestPerson();
    int findAgeDifferenceBetweenPerson(final String firtPersonName,final String secondPersonName);
}
