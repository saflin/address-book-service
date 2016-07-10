package com.jscl.test.address_service.model;

import org.joda.time.LocalDate;

/**
 * Created by saflinhussain.
 */
public class Person {

    private Gender gender;

    private String name;

    private LocalDate dateOfBirth;

    public Person(final String name, final LocalDate dateOfBirth, Gender gender) {
        setName(name);
        setDateOfBirth(dateOfBirth);
        setGender(gender);
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender genderArg) {
        if (genderArg == null) throw new IllegalArgumentException("gender cannot be null");
        this.gender = genderArg;
    }

    public String getName() {
        return name;
    }

    public void setName(String nameArg) {
        if (nameArg == null) throw new IllegalArgumentException("name cannot be null");
        this.name = nameArg;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirthArg) {
        if (dateOfBirthArg == null) throw new IllegalArgumentException("Date of birth cannot be null");
        this.dateOfBirth = dateOfBirthArg;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Person person = (Person) o;

        if (gender != person.gender) return false;
        if (!name.equals(person.name)) return false;
        return dateOfBirth.equals(person.dateOfBirth);

    }

    @Override
    public int hashCode() {
        int result = gender.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + dateOfBirth.hashCode();
        return result;
    }
}
