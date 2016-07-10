package com.jscl.test.address_service.service;

import com.jscl.test.address_service.model.AddressRecord;
import com.jscl.test.address_service.model.Gender;
import com.jscl.test.address_service.provider.AddressRecordProvider;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.HashSet;
import java.util.Set;

import static com.jscl.test.address_service.model.AddressRecordFactory.createRecord;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * Created by saflinhussain.
 */
@RunWith(MockitoJUnitRunner.class)
public class GumtreeAddressBookServiceTest {

    @Mock
    private AddressRecordProvider addressRecordProvider;

    @Test
    public void itShouldReturnCountByGender(){
        when(addressRecordProvider.provideAddressRecords()).thenReturn(getTestAddressRecords());
        GumtreeAddressBookService gumtreeAddressBookService = new GumtreeAddressBookService(addressRecordProvider);
        assertEquals(3,gumtreeAddressBookService.getAddressRecordCountByGender(Gender.MALE));
        assertEquals(2,gumtreeAddressBookService.getAddressRecordCountByGender(Gender.FEMALE));
    }

    @Test
    public void itShouldReturnCountByGenderZero(){

        when(addressRecordProvider.provideAddressRecords()).thenReturn((new HashSet<AddressRecord>()));
        GumtreeAddressBookService gumtreeAddressBookService = new GumtreeAddressBookService(addressRecordProvider);
        assertEquals(0,gumtreeAddressBookService.getAddressRecordCountByGender(Gender.MALE));

        when(addressRecordProvider.provideAddressRecords()).thenReturn(getTestAddressRecordsAllMale());
        gumtreeAddressBookService = new GumtreeAddressBookService(addressRecordProvider);
        assertEquals(3,gumtreeAddressBookService.getAddressRecordCountByGender(Gender.MALE));
        assertEquals(0,gumtreeAddressBookService.getAddressRecordCountByGender(Gender.FEMALE));

        when(addressRecordProvider.provideAddressRecords()).thenReturn(getTestAddressRecordsAllFemale());
        gumtreeAddressBookService = new GumtreeAddressBookService(addressRecordProvider);
        assertEquals(2,gumtreeAddressBookService.getAddressRecordCountByGender(Gender.FEMALE));
        assertEquals(0,gumtreeAddressBookService.getAddressRecordCountByGender(Gender.MALE));

    }



    private Set<AddressRecord> getTestAddressRecords(){
        Set<AddressRecord> testRecords = new HashSet<>();
        testRecords.add(createRecord("John Peter", "Male", "16/03/77"));
        testRecords.add(createRecord("Fernando Louise", "Male", "15/01/85"));
        testRecords.add(createRecord("Robert Shield", "Male", "20/11/91"));
        testRecords.add(createRecord("Sarah Stone", "Female", "20/09/80"));
        testRecords.add(createRecord("Carole", "Female", "14/08/74"));
        return testRecords;
    }

    private Set<AddressRecord> getTestAddressRecordsAllMale(){
        Set<AddressRecord> testRecords = new HashSet<>();
        testRecords.add(createRecord("John Peter", "Male", "16/03/77"));
        testRecords.add(createRecord("Fernando Louise", "Male", "15/01/85"));
        testRecords.add(createRecord("Robert Shield", "Male", "20/11/91"));
        return testRecords;
    }

    private Set<AddressRecord> getTestAddressRecordsAllFemale(){
        Set<AddressRecord> testRecords = new HashSet<>();
        testRecords.add(createRecord("Sarah Stone", "Female", "20/09/67"));
        testRecords.add(createRecord("Carole", "Female", "14/08/74"));
        return testRecords;
    }
}