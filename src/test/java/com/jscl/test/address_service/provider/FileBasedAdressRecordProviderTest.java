package com.jscl.test.address_service.provider;

import com.jscl.test.address_service.exception.AddressRecordParseException;
import com.jscl.test.address_service.model.AddressRecord;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;
import static com.jscl.test.address_service.model.AddressRecordFactory.createRecord;

/**
 * Created by saflinhussain.
 */
public class FileBasedAdressRecordProviderTest {

    @Test
    public void itShouldThrowExceptionWhenFilePathIsNull(){
        try {
            FileBasedAdressRecordProvider underTest = new FileBasedAdressRecordProvider(null);
            fail("Expecting exception to be thrown");
        }catch (IllegalArgumentException ex){
            assertEquals("File input steam cannot be null",ex.getMessage());
        }catch (Exception ex){
            fail("Excpecting IllegalArgumentException but got "+ex.getClass().getName());
        }
    }

    @Test
    public void itShouldGetAddressRecords() throws Exception {
        FileBasedAdressRecordProvider underTest = new FileBasedAdressRecordProvider(getRecordFileAsStream("AddressRecordsTest.txt"));
        Set<AddressRecord> expectedRecords = testRecords();
        Set<AddressRecord> actualRecords  = underTest.provideAddressRecords();
        assertEquals(expectedRecords.size(),actualRecords.size());
        assertTrue(actualRecords.containsAll(expectedRecords));

    }

    @Test
    public void itShouldHandleEmptyRecordFile() throws Exception {
        FileBasedAdressRecordProvider underTest = new FileBasedAdressRecordProvider(getRecordFileAsStream("EmptyAddressRecords.txt"));
        assertTrue(underTest.provideAddressRecords().isEmpty());
    }

    @Test
    public void itThrowsAddressRecordParseException() throws Exception {
        try {
            FileBasedAdressRecordProvider underTest = new FileBasedAdressRecordProvider(getRecordFileAsStream("InvalidAddressRecords.txt"));
            fail("expecting address record parse exception.");
        }catch (AddressRecordParseException ex){
           assertEquals("Invalid address book record.Expecting 3 data, found 2",ex.getMessage());
        }
    }

    private Set<AddressRecord> testRecords(){
        Set<AddressRecord> testRecords = new HashSet<>();
        testRecords.add(createRecord("Bill McKnight", "Male", "16/03/77"));
        testRecords.add(createRecord("Paul Robinson", "Male", "15/01/85"));
        testRecords.add(createRecord("Gemma Lane", "Female", "20/11/91"));
        testRecords.add(createRecord("Sarah Stone", "Female", "20/09/80"));
        testRecords.add(createRecord("Wes Jackson", "Male", "14/08/74"));
        return testRecords;
    }

    private InputStream getRecordFileAsStream(final String fileName){
        return this.getClass().getClassLoader().getResourceAsStream(fileName);
    }

}