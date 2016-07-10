package com.jscl.test.address_service.model;

import org.joda.time.LocalDate;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by saflinhussain.
 */
public class AddressRecordFactoryTest {

    @Test
    public void itShouldCreateAddressRecords(){
        AddressRecord addressRecord = AddressRecordFactory.createRecord("Ben Johnson","Male","30/09/75");
        assertNotNull(addressRecord);
        assertNotNull(addressRecord.getPerson());
        assertEquals("Ben Johnson",addressRecord.getPerson().getName());
        assertEquals(Gender.MALE,addressRecord.getPerson().getGender());
        assertEquals(new LocalDate(1975,9,30),addressRecord.getPerson().getDateOfBirth());
    }
}