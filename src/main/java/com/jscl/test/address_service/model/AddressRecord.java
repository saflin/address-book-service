package com.jscl.test.address_service.model;

/**
 * Created by saflinhussain.
 */
public class AddressRecord {

    private Person person;

    public AddressRecord(Person person) {
        if (person == null)
            throw new IllegalArgumentException("Person cannot be null for address book");
        this.person = person;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AddressRecord that = (AddressRecord) o;

        return person.equals(that.person);

    }

    @Override
    public int hashCode() {
        return person.hashCode();
    }
}
