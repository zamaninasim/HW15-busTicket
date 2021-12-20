package ir.maktab.model.builder;

import ir.maktab.enums.Gender;
import ir.maktab.model.Admin;

import java.util.Date;

public final class AdminBuilder {
    private String password;
    private Integer id;
    private String firstname;
    private String lastname;
    private String phoneNumber;
    private String nationalCode;
    private Gender gender;
    private Date birthdate;

    private AdminBuilder() {
    }

    public static AdminBuilder anAdmin() {
        return new AdminBuilder();
    }

    public AdminBuilder withPassword(String password) {
        this.password = password;
        return this;
    }

    public AdminBuilder withId(Integer id) {
        this.id = id;
        return this;
    }

    public AdminBuilder withFirstname(String firstname) {
        this.firstname = firstname;
        return this;
    }

    public AdminBuilder withLastname(String lastname) {
        this.lastname = lastname;
        return this;
    }

    public AdminBuilder withPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public AdminBuilder withNationalCode(String nationalCode) {
        this.nationalCode = nationalCode;
        return this;
    }

    public AdminBuilder withGender(Gender gender) {
        this.gender = gender;
        return this;
    }

    public AdminBuilder withBirthdate(Date birthdate) {
        this.birthdate = birthdate;
        return this;
    }

    public Admin build() {
        Admin admin = new Admin();
        admin.setPassword(password);
        admin.setId(id);
        admin.setFirstname(firstname);
        admin.setLastname(lastname);
        admin.setPhoneNumber(phoneNumber);
        admin.setNationalCode(nationalCode);
        admin.setGender(gender);
        admin.setBirthdate(birthdate);
        return admin;
    }
}
