package ir.maktab.model.builder;

import ir.maktab.enums.Gender;
import ir.maktab.model.Owner;

import java.util.Date;

public final class OwnerBuilder {
    private Integer id;
    private String firstname;
    private String lastname;
    private String phoneNumber;
    private String nationalCode;
    private Gender gender;
    private Date birthdate;

    private OwnerBuilder() {
    }

    public static OwnerBuilder anOwner() {
        return new OwnerBuilder();
    }

    public OwnerBuilder withId(Integer id) {
        this.id = id;
        return this;
    }

    public OwnerBuilder withFirstname(String firstname) {
        this.firstname = firstname;
        return this;
    }

    public OwnerBuilder withLastname(String lastname) {
        this.lastname = lastname;
        return this;
    }

    public OwnerBuilder withPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public OwnerBuilder withNationalCode(String nationalCode) {
        this.nationalCode = nationalCode;
        return this;
    }

    public OwnerBuilder withGender(Gender gender) {
        this.gender = gender;
        return this;
    }

    public OwnerBuilder withBirthdate(Date birthdate) {
        this.birthdate = birthdate;
        return this;
    }

    public Owner build() {
        Owner owner = new Owner();
        owner.setId(id);
        owner.setFirstname(firstname);
        owner.setLastname(lastname);
        owner.setPhoneNumber(phoneNumber);
        owner.setNationalCode(nationalCode);
        owner.setGender(gender);
        owner.setBirthdate(birthdate);
        return owner;
    }
}
