package ir.maktab;

import ir.maktab.dto.TripDto;
import ir.maktab.enums.BusType;
import ir.maktab.enums.City;
import ir.maktab.enums.Gender;
import ir.maktab.model.*;
import ir.maktab.model.builder.AdminBuilder;
import ir.maktab.model.builder.CustomerBuilder;
import ir.maktab.service.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Main {
    static final Scanner scanner = new Scanner(System.in);
    static final AdminService adminService = new AdminService();
    static final CompanyService companyService = new CompanyService();
    static final BusService busService = new BusService();
    static final TripService tripService = new TripService();
    static final TicketService ticketService = new TicketService();
    static final CustomerService customerService = new CustomerService();

    public static void main(String[] args) throws ParseException {

        System.out.println("1)manager\n2)customer");
        int role = scanner.nextInt();
        switch (role) {
            case 1:
                adminLogin();
                break;
            case 2:
                customerLogin();
                break;
        }
    }

    private static void addCustomer() throws ParseException {
        System.out.println("enter your info:(firstname,lastname,phoneNumber,nationalCode,gender)");
        String userInfo = scanner.next();
        String[] splitInfo = userInfo.split(",");
        String firstname = splitInfo[0];
        String lastname = splitInfo[1];
        String phoneNumber = splitInfo[2];
        String nationalCode = splitInfo[3];
        Gender gender = Gender.getValue(splitInfo[4]);

        System.out.println("enter birthdate like this:(2021-11-08)");
        Date birthdate = new SimpleDateFormat("yyyy-MM-dd").parse(scanner.next());

        Customer customer = CustomerBuilder.aCustomer()
                .withFirstname(firstname)
                .withLastname(lastname)
                .withPhoneNumber(phoneNumber)
                .withNationalCode(nationalCode)
                .withGender(gender)
                .withBirthdate(birthdate)
                .build();

        customerService.save(customer);
    }

    private static void customerLogin() throws ParseException {
        System.out.println("nationalCode:");
        String nationalCode = scanner.next();
        try {
            Customer customer = customerService.findByNationalCode(nationalCode);
            customerActs();

        } catch (RuntimeException e) {
            addCustomer();
            customerActs();
        }
    }

    private static void customerActs() throws ParseException {
        System.out.println("""
                1)search for trip
                """);
        int choice = scanner.nextInt();
        switch (choice) {
            case 1:
                searchForTrip();
                break;
            case 2:
                break;
        }

    }

    private static void searchForTrip() throws ParseException {
        System.out.println("origin:");
        City origin = City.getValue(scanner.next());
        System.out.println("destination:");
        City destination = City.getValue(scanner.next());
        System.out.println("do you want to enter date:1)yes 2)no");
        int yesOrNo = scanner.nextInt();
        switch (yesOrNo) {
            case 1:
                System.out.println("enter date(yyyy-MM-dd):");
                Date date = new SimpleDateFormat("yyyy-MM-dd").parse(scanner.next());
                showTripWithPagination(origin, destination, date);
                break;
            case 2:
                showTripWithPagination(origin, destination, null);
                break;
        }
    }

    private static void showTripWithPagination(City origin, City destination, Date date) {
        System.out.println("enter Number of results:");
        int maxResultInPage = scanner.nextInt();
        int startResult = 0;
        exit:
        while (true) {
            List<TripDto> trips = tripService.listTripByPaginated(origin, destination, date, startResult, maxResultInPage);
            System.out.println(trips);
            int result = trips.size();
            //TODO
            //result safhe baad ro begiram behtare
            showPage:
            while (true) {
                System.out.println("1)show details 2)nextPage 3)previousPage 4)exit");
                int choice = scanner.nextInt();
                switch (choice) {
                    case 1:
                        //showTicketDetail();
                        //TODO
                        break;
                    case 2:
                        if (result < maxResultInPage) {
                            System.out.println("no next page!");
                            continue showPage;
                        }
                        startResult += maxResultInPage;
                        break showPage;
                    case 3:
                        if (startResult == 0) {
                            System.out.println("no previous page!");
                            continue showPage;
                        }
                        startResult -= maxResultInPage;
                        break showPage;
                    default:
                        break exit;
                }
            }
        }
    }

    private static void adminActs() throws ParseException {
        boolean exit = false;
        do {
            System.out.println("""
                    1)add new Admin
                    2)add new Company
                    3)add new Bus
                    4)add new Trip
                    5)show reports
                    6)exit
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
                    addTrip();
                    break;
                case 5:
                    //TODO
                    break;
                case 6:
                    exit = true;
                    break;
            }
        } while (!exit);
    }

    private static void adminLogin() throws ParseException {
        System.out.println("nationalCode:");
        String nationalCode = scanner.next();
        boolean repeat = true;
        do {
            System.out.println("password");
            String password = scanner.next();
            try {
                Admin admin = adminService.findByNationalCode(nationalCode);
                if (admin.getPassword().equals(password)) {
                    adminActs();
                    repeat = false;
                } else {
                    System.out.println("wrong password:");
                }
            } catch (RuntimeException | ParseException e) {
                System.out.println(e.getMessage());
                addAdmin();
                repeat = false;
            }
        } while (repeat);
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
        bus.setAvailableSeat(availableSeat);
        busService.save(bus);
    }

    private static void addCompany() {
        Company company = new Company();
        System.out.println("enter company name:");
        String name = scanner.next();
        company.setName(name);
        companyService.save(company);
    }

    private static void addTrip() throws ParseException {
        System.out.println("enter trip info:(date(yyyy-MM-dd),time(hh:mm),origin,destination,busPlaque,price");
        String info = scanner.next();
        String[] splitInfo = info.split(",");
        Date date = new SimpleDateFormat("yyyy-MM-dd").parse(splitInfo[0]);
        Date time = new SimpleDateFormat("hh:mm").parse(splitInfo[1]);
        City origin = City.getValue(splitInfo[2]);
        City destination = City.getValue(splitInfo[3]);
        String busPlaque = splitInfo[4];
        Long price = Long.parseLong(splitInfo[5]);
        Bus bus = busService.findByPlaque(busPlaque);
        Integer availableSeat = bus.getAvailableSeat();

        Trip trip = new Trip();
        trip.setDate(date);
        trip.setTime(time);
        trip.setOrigin(origin);
        trip.setDestination(destination);
        trip.setBus(bus);
        trip.setPrice(price);
        tripService.save(trip);

        for (int i = 1; i <= availableSeat; i++) {
            Ticket ticket = new Ticket();
            ticket.setSeatNumber(i);
            ticket.setTrip(trip);
            ticketService.save(ticket);
        }
    }

    private static void addAdmin() throws ParseException {
        System.out.println("enter new admin info:(firstname,lastname,phoneNumber,nationalCode,gender)");
        String userInfo = scanner.next();
        String[] splitInfo = userInfo.split(",");
        String firstname = splitInfo[0];
        String lastname = splitInfo[1];
        String phoneNumber = splitInfo[2];
        String nationalCode = splitInfo[3];
        Gender gender = Gender.getValue(splitInfo[4]);

        System.out.println("enter birthdate like this:(2021-11-08)");
        Date birthdate = new SimpleDateFormat("yyyy-MM-dd").parse(scanner.next());

        Admin admin = AdminBuilder.anAdmin()
                .withFirstname(firstname)
                .withLastname(lastname)
                .withPhoneNumber(phoneNumber)
                .withNationalCode(nationalCode)
                .withGender(gender)
                .withBirthdate(birthdate)
                .withPassword("admin")
                .build();

        adminService.save(admin);
    }

}
