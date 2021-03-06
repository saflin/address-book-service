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

    @Test
    public void itShouldFindOldestPerson(){

        when(addressRecordProvider.provideAddressRecords()).thenReturn(getTestAddressRecords());
        GumtreeAddressBookService gumtreeAddressBookService = new GumtreeAddressBookService(addressRecordProvider);
        assertEquals("Carole",gumtreeAddressBookService.findOldestPerson());

        when(addressRecordProvider.provideAddressRecords()).thenReturn(getTestAddressRecordsAllMale());
        gumtreeAddressBookService = new GumtreeAddressBookService(addressRecordProvider);
        assertEquals("John Peter",gumtreeAddressBookService.findOldestPerson());

        when(addressRecordProvider.provideAddressRecords()).thenReturn(getTestAddressRecordsAllFemale());
        gumtreeAddressBookService = new GumtreeAddressBookService(addressRecordProvider);
        assertEquals("Sarah Stone",gumtreeAddressBookService.findOldestPerson());


        when(addressRecordProvider.provideAddressRecords()).thenReturn(new HashSet<AddressRecord>());
        gumtreeAddressBookService = new GumtreeAddressBookService(addressRecordProvider);
        assertNull(gumtreeAddressBookService.findOldestPerson());

    }


    @Test
    public void itShouldFindAgeDifference(){

        when(addressRecordProvider.provideAddressRecords()).thenReturn(getTestAddressRecords());
        GumtreeAddressBookService gumtreeAddressBookService = new GumtreeAddressBookService(addressRecordProvider);

        assertEquals(0,gumtreeAddressBookService.findAgeDifferenceBetweenPerson("John Peter","John Peter"));

        assertEquals(2862,gumtreeAddressBookService.findAgeDifferenceBetweenPerson("John Peter","Fernando Louise"));

        assertEquals(-2862,gumtreeAddressBookService.findAgeDifferenceBetweenPerson("Fernando Louise", "John Peter"));

    }

    @Test
    public void itShouldThrowExceptionIfItCantAddressRecords(){
        try{
            when(addressRecordProvider.provideAddressRecords()).thenReturn(getTestAddressRecords());
            GumtreeAddressBookService gumtreeAddressBookService = new GumtreeAddressBookService(addressRecordProvider);
            gumtreeAddressBookService.findAgeDifferenceBetweenPerson("A","B");
            fail("expecting illegal argument exception");
        }catch (IllegalArgumentException ex){
            assertEquals("Unable to find address record with name : A",ex.getMessage());
        }


        try{
            when(addressRecordProvider.provideAddressRecords()).thenReturn(getTestAddressRecords());
            GumtreeAddressBookService gumtreeAddressBookService = new GumtreeAddressBookService(addressRecordProvider);
            gumtreeAddressBookService.findAgeDifferenceBetweenPerson("John Peter","B");
            fail("expecting illegal argument exception");
        }catch (IllegalArgumentException ex){
            assertEquals("Unable to find address record with name : B",ex.getMessage());
        }
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