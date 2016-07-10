package com.jscl.test.address_service.provider;

import com.jscl.test.address_service.model.AddressRecord;

import java.util.Set;

/**
 * Created by saflinhussain.
 */
public interface AddressRecordProvider {
    /**
     * Provide set of address book entries.
     * @return set of AddressRecords.
     */
    Set<AddressRecord> provideAddressRecords();
}
