package ir.maktab.model.builder;

import ir.maktab.enums.Gender;
import ir.maktab.model.Address;
import ir.maktab.model.Admin;

import java.util.Date;
import java.util.List;

public final class AdminBuilder {
    private String username;
    private String password;
    private Integer id;
    private String firstname;
    private String lastname;
    private Gender gender;
    private Date birthdate;
    private List<Address> address;
    private String email;

    private AdminBuilder() {
    }

    public static AdminBuilder anAdmin() {
        return new AdminBuilder();
    }

    public AdminBuilder withUsername(String username) {
        this.username = username;
        return this;
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

    public AdminBuilder withGender(Gender gender) {
        this.gender = gender;
        return this;
    }

    public AdminBuilder withBirthdate(Date birthdate) {
        this.birthdate = birthdate;
        return this;
    }

    public AdminBuilder withAddress(List<Address> address) {
        this.address = address;
        return this;
    }

    public AdminBuilder withEmail(String email) {
        this.email = email;
        return this;
    }

    public Admin build() {
        Admin admin = new Admin();
        admin.setUsername(username);
        admin.setPassword(password);
        admin.setId(id);
        admin.setFirstname(firstname);
        admin.setLastname(lastname);
        admin.setGender(gender);
        admin.setBirthdate(birthdate);
        admin.setAddress(address);
        admin.setEmail(email);
        return admin;
    }
}
