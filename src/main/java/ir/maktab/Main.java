package ir.maktab;

import ir.maktab.enums.BusType;
import ir.maktab.enums.City;
import ir.maktab.enums.Gender;
import ir.maktab.enums.SeatType;
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
    static final SeatService seatService = new SeatService();
    static final TicketService ticketService = new TicketService();
    static final CustomerService customerService = new CustomerService();

    public static void main(String[] args) throws ParseException {
        //
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
                1)search for ticket
                """);
        int choice = scanner.nextInt();
        switch (choice) {
            case 1:
                searchForTicket();
        }

    }

    private static void searchForTicket() throws ParseException {
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
                showTicketWithPagination(origin, destination, date);
                break;
            case 2:
                showTicketWithPagination(origin, destination, null);
                break;
        }
    }

    private static void showTicketWithPagination(City origin, City destination, Date date) {
        System.out.print("enter Number of results:");
        int maxResultInPage = scanner.nextInt();
        int startResult = 0;
        while (true) {
            List<Ticket> tickets = ticketService.listPaginatedTickets(origin, destination, date, startResult, maxResultInPage);
            System.out.println(tickets);
            int result = tickets.size();
            //TODO
            //result safhe baad ro begiram behtare
            forContinuing:
            while (true) {
                System.out.print("1)show details 2)nextPage 3)previousPage 4)exit");
                int choice = scanner.nextInt();
                switch (choice) {
                    case 1:
                        //showTicketDetail();
                        //TODO
                        break;
                    case 2:
                        if (result < maxResultInPage) {
                            System.out.println("no next page!");
                            continue forContinuing;
                        }
                        startResult += maxResultInPage;
                        break forContinuing;
                    case 3:
                        if (startResult == 0) {
                            System.out.println("no previous page!");
                            continue forContinuing;
                        }
                        startResult -= maxResultInPage;
                        break forContinuing;
                    default:
                        break;
                }
            }
        }
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
                addTicket();
                break;
            case 5:
                //TODO
                break;
        }
    }

    private static void addTicket() throws ParseException {
        System.out.println("enter ticket info:(date(yyyy-MM-dd),time(hh:mm),Price,origin,destination,busPlaque");
        String info = scanner.next();
        String[] splitInfo = info.split(",");
        Date date = new SimpleDateFormat("yyyy-MM-dd").parse(splitInfo[0]);
        Date time = new SimpleDateFormat("hh:mm").parse(splitInfo[1]);
        Long price = Long.parseLong(splitInfo[2]);
        City origin = City.getValue(splitInfo[3]);
        City destination = City.getValue(splitInfo[4]);
        String busPlaque = splitInfo[5];
        Bus bus = busService.findByPlaque(busPlaque);
        List<Seat> seats = bus.getSeats();
        for (Seat seat : seats) {
            Ticket ticket = new Ticket();
            ticket.setDate(date);
            ticket.setTime(time);
            ticket.setPrice(price);
            ticket.setOrigin(origin);
            ticket.setDestination(destination);
            ticket.setSeat(seat);
            ticketService.save(ticket);
        }
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

        for (int i = 1; i <= availableSeat; i++) {
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
