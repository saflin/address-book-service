package com.jscl.test.address_service;

import com.jscl.test.address_service.model.Gender;
import com.jscl.test.address_service.provider.AddressRecordProvider;
import com.jscl.test.address_service.provider.FileBasedAdressRecordProvider;
import com.jscl.test.address_service.service.AddressBookService;
import com.jscl.test.address_service.service.GumtreeAddressBookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.InputStream;
import java.net.URL;

/**
 * Created by saflinhussain.
 */
public class Application {

    private static final Logger LOGGER = LoggerFactory.getLogger(Application.class);


    public static void main(String[] args) {
        try {
            InputStream inputStream = Application.class.getClassLoader().getResourceAsStream("AddressBook.txt");
            AddressRecordProvider provider = new FileBasedAdressRecordProvider(inputStream);
            AddressBookService addressBookService = new GumtreeAddressBookService(provider);
            LOGGER.info("No of males in the address book        : " + addressBookService.getAddressRecordCountByGender(Gender.MALE));
            LOGGER.info("Oldest Person in the address book      : " + addressBookService.findOldestPerson());
            LOGGER.info("Age diffrerence between Bill and Paul  : " + addressBookService.findAgeDifferenceBetweenPerson("Bill McKnight","Paul Robinson"));
        } catch (Exception ex) {
            LOGGER.error("Exception while running address book service.",ex);
        }

    }
}
