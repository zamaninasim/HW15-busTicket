package ir.maktab;

import ir.maktab.enums.Gender;
import ir.maktab.model.Address;
import ir.maktab.model.Admin;
import ir.maktab.model.builder.AdminBuilder;
import ir.maktab.service.AdminService;

import javax.persistence.criteria.CriteriaBuilder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

public class Main {
    static final Scanner scanner = new Scanner(System.in);
    static final AdminService adminService = new AdminService();

    public static void main(String[] args) throws ParseException {
        addAdmin();

        System.out.println("1)manager\n2)customer");
        int role = scanner.nextInt();
        switch (role) {
            case 1:
                adminLogin();
                break;
            case 2:
                break;
        }
    }

    private static void adminLogin() {
        System.out.println("username:");
        String username = scanner.next();
        Boolean repeat = true;
        do {
            System.out.println("password");
            String password = scanner.next();
            try {
                Admin admin = adminService.findByUsername(username);
                if (admin.getPassword().equals(password)) {
                    adminActs();
                    repeat = false;
                } else {
                    System.out.println("wrong password");
                }
            } catch (RuntimeException | ParseException e) {
                System.out.println(e.getMessage());
            }
        } while (repeat);
    }

    private static void adminActs() throws ParseException {
        System.out.println("""
                1)add new Admin
                2)add new Company
                3)add new Bus
                2)add new Ticket""");
        int choice = scanner.nextInt();
        switch (choice) {
            case 1:
                addAdmin();
                break;
            case 2:

        }
    }

    private static void addAdmin() throws ParseException {
        System.out.println("enter new admin info:(firstname,lastname,gender,email)");
        String userInfo = scanner.next();
        String[] splitInfo = userInfo.split(",");
        String firstname = splitInfo[0];
        String lastname = splitInfo[1];
        Gender gender = Gender.getValue(splitInfo[2].toUpperCase(Locale.ROOT));
        String email = splitInfo[3];
        System.out.println("enter birthdate like this:(2021-11-08)");
        Date birthdate = new SimpleDateFormat("yyyy-MM-dd").parse(scanner.next());

        System.out.println("enter new admin Address info:(country,city,postcode)");
        String addressInfo = scanner.next();
        String[] splitAddressInfo = addressInfo.split(",");
        String country = splitAddressInfo[0];
        String city = splitAddressInfo[1];
        Integer postcode = Integer.parseInt(splitAddressInfo[2]);
        Address address = new Address();
        address.setCountry(country);
        address.setCity(city);
        address.setPostcode(postcode);

        Admin admin = AdminBuilder.anAdmin()
                .withFirstname(firstname)
                .withLastname(lastname)
                .withEmail(email)
                .withBirthdate(birthdate)
                .withGender(gender)
                .withUsername("admin")
                .withPassword("admin")
                .withAddress()
                .build();

        admin.getAddress().add(address);
        adminService.save(admin);
    }
}
