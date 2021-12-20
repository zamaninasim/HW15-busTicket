package ir.maktab;

import ir.maktab.enums.BusType;
import ir.maktab.enums.Gender;
import ir.maktab.enums.SeatType;
import ir.maktab.model.*;
import ir.maktab.model.builder.AdminBuilder;
import ir.maktab.service.AdminService;
import ir.maktab.service.BusService;
import ir.maktab.service.CompanyService;
import ir.maktab.service.SeatService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Main {
    static final Scanner scanner = new Scanner(System.in);
    static final AdminService adminService = new AdminService();
    static final CompanyService companyService = new CompanyService();
    static final BusService busService = new BusService();
    static final SeatService seatService = new SeatService();
    public static void main(String[] args) throws ParseException {
        //addAdmin();

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
                    System.out.println("wrong password:");
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
                4)add new Ticket
                5)show reports
                """);
        int choice = scanner.nextInt();
        switch (choice) {
            case 1:
                addAdmin();
                break;
            case 2:
                addCompany();
                break;
            case 3:
                addBus();
                break;
            case 4:
                System.out.println("enter ticket info:(date,time,Price");
                Ticket ticket = new Ticket();
                //ticket.setDate();

                break;
        }
    }

    private static void addBus() {
        System.out.println("enter bus info:(plaque,type,availableSeat,companyName)");
        String busInfo = scanner.next();
        String[] splitInfo = busInfo.split(",");
        String plaque = splitInfo[0];
        BusType type = BusType.getValue(splitInfo[1]);
        int availableSeat = Integer.parseInt(splitInfo[2]);
        String companyName = splitInfo[3];
        Company company = companyService.findByName(companyName);
        Bus bus = new Bus();
        bus.setPlaque(plaque);
        bus.setType(type);
        bus.setCompany(company);
        busService.save(bus);

        for (int i=0;i<=availableSeat;i++){
            Seat seat = new Seat();
            seat.setSeatNumber(i);
            seat.setSeatType(SeatType.AVAILABLE);
            seat.setBus(bus);
            seatService.save(seat);
        }
    }

    private static void addCompany() {
        Company company = new Company();
        System.out.println("enter company name:");
        String name = scanner.next();
        company.setName(name);
        companyService.save(company);
    }

    private static void addAdmin() throws ParseException {
        System.out.println("enter new admin info:(firstname,lastname,gender,email)");
        String userInfo = scanner.next();
        String[] splitInfo = userInfo.split(",");
        String firstname = splitInfo[0];
        String lastname = splitInfo[1];
        Gender gender = Gender.getValue(splitInfo[2]);
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
