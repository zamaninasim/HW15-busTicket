package ir.maktab.model.builder;

import ir.maktab.enums.Gender;
import ir.maktab.model.Customer;
import ir.maktab.model.Reservation;

import java.util.Date;
import java.util.List;

public final class CustomerBuilder {
    private List<Reservation> reservations;
    private Integer id;
    private String firstname;
    private String lastname;
    private String phoneNumber;
    private String nationalCode;
    private Gender gender;
    private Date birthdate;

    private CustomerBuilder() {
    }

    public static CustomerBuilder aCustomer() {
        return new CustomerBuilder();
    }

    public CustomerBuilder withReservations(List<Reservation> reservations) {
        this.reservations = reservations;
        return this;
    }

    public CustomerBuilder withId(Integer id) {
        this.id = id;
        return this;
    }

    public CustomerBuilder withFirstname(String firstname) {
        this.firstname = firstname;
        return this;
    }

    public CustomerBuilder withLastname(String lastname) {
        this.lastname = lastname;
        return this;
    }

    public CustomerBuilder withPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public CustomerBuilder withNationalCode(String nationalCode) {
        this.nationalCode = nationalCode;
        return this;
    }

    public CustomerBuilder withGender(Gender gender) {
        this.gender = gender;
        return this;
    }

    public CustomerBuilder withBirthdate(Date birthdate) {
        this.birthdate = birthdate;
        return this;
    }

    public Customer build() {
        Customer customer = new Customer();
        customer.setId(id);
        customer.setFirstname(firstname);
        customer.setLastname(lastname);
        customer.setPhoneNumber(phoneNumber);
        customer.setNationalCode(nationalCode);
        customer.setGender(gender);
        customer.setBirthdate(birthdate);
        return customer;
    }
}
