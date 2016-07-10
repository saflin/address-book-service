package com.jscl.test.address_service.provider;

import com.jscl.test.address_service.exception.AddressRecordParseException;
import com.jscl.test.address_service.model.AddressRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Set;

import static java.lang.String.format;
import static com.jscl.test.address_service.model.AddressRecordFactory.createRecord;

/**
 * Created by saflinhussain.
 */
public class FileBasedAdressRecordProvider implements AddressRecordProvider {

    private static final Logger LOGGER = LoggerFactory.getLogger(FileBasedAdressRecordProvider.class);

    private final Set<AddressRecord> records = new HashSet<>();

    public FileBasedAdressRecordProvider(final InputStream fileInputStream) throws IOException {
        if (fileInputStream == null)
            throw new IllegalArgumentException("File input steam cannot be null");
        parseFile(fileInputStream);
    }

    private void parseFile(final InputStream inputStream) throws IOException {
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream))) {
            bufferedReader.lines().forEach(line -> {
                LOGGER.debug(format("Parsing record -> %s", line));
                String tmp[] = line.split(",");
                if (tmp.length != 3)
                    throw new AddressRecordParseException("Invalid address book record.Expecting 3 data, found " + tmp.length);

                AddressRecord addressRecord = createRecord(tmp[0].trim(), tmp[1].trim(), tmp[2].trim());
                records.add(addressRecord);
            });
        } catch (Exception ex) {
            LOGGER.error("Exception while reading input stream ", ex);
            throw ex;
        }
    }


    public Set<AddressRecord> provideAddressRecords() {
        return records;
    }
}
