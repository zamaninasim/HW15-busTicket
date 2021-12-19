package ir.makatb.model;

import ir.makatb.enums.Gender;

import java.util.Date;

public class Customer {
    private Integer id;
    private String firstname;
    private String lastname;
    private Gender gender;
    private Date birthdate;
    private Address address;
    private String email;
    private String password;
}
