package ir.maktab;

import ir.maktab.dto.ReservationDto;
import ir.maktab.dto.TripDto;
import ir.maktab.enums.*;
import ir.maktab.model.*;
import ir.maktab.model.builder.AdminBuilder;
import ir.maktab.model.builder.CustomerBuilder;
import ir.maktab.model.builder.OwnerBuilder;
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
    static final ReservationService reservationService = new ReservationService();
    static final OwnerService ownerService = new OwnerService();

    public static void main(String[] args) throws ParseException {
        System.out.println("1)manager\n2)customer\n3)exit");
        int role = scanner.nextInt();
        boolean exit = false;
        do {
            switch (role) {
                case 1:
                    adminLogin();
                    break;
                case 2:
                    customerLogin();
                    break;
                case 3:
                    exit = true;
                    break;
            }
        } while (!exit);
    }

    private static Customer addCustomer() throws ParseException {
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
        return customer;
    }

    private static Owner addOwner() throws ParseException {
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
        Owner owner = OwnerBuilder.anOwner()
                .withFirstname(firstname)
                .withLastname(lastname)
                .withPhoneNumber(phoneNumber)
                .withNationalCode(nationalCode)
                .withGender(gender)
                .withBirthdate(birthdate)
                .build();

        ownerService.save(owner);
        return owner;
    }

    private static void customerLogin() throws ParseException {
        System.out.println("nationalCode:");
        String nationalCode = scanner.next();
        try {
            customerService.findByNationalCode(nationalCode);
            customerActs(nationalCode);

        } catch (RuntimeException e) {
            addCustomer();
            customerActs(nationalCode);
        }
    }

    private static void customerActs(String nationalCode) throws ParseException {
        System.out.println("""
                1)search for trip
                2)buy ticket
                3)show reservations
                4)exit
                """);
        int choice = scanner.nextInt();
        switch (choice) {
            case 1:
                searchForTrip();
                break;
            case 2:
                reservation(nationalCode);
                break;
            case 3:
                showReservations(nationalCode);
                break;
            case 4:
                break;
        }
    }

    private static void showReservations(String nationalCode) {
        Customer customer = customerService.findByNationalCode(nationalCode);
        List<ReservationDto> reservationOfCustomer = reservationService.findReservationByCustomer(customer);
        System.out.println(reservationOfCustomer);
    }

    private static void reservation(String nationalCode) throws ParseException {
        Customer customer = customerService.findByNationalCode(nationalCode);
        System.out.println("enter trip id:");
        int id = scanner.nextInt();
        Trip trip = tripService.get(id);
        Bus bus = trip.getBus();
        System.out.println("Number of tickets required:");
        int numberOfTickets = scanner.nextInt();
        List<Ticket> availableSeats = ticketService.findAvailableSeatByTrip(trip);
        availableSeats.stream().map(Ticket::getSeatNumber).forEach(number -> System.out.print(number + " , "));
        System.out.println();
        Long tripPrice = trip.getPrice();
        long totalPrice = 0;
        Reservation reservation = new Reservation();
        reservation.setCustomer(customer);
        reservation.setReservationType(ReservationType.PROCESSING);

        reservationService.save(reservation);
        for (int i = 0; i < numberOfTickets; i++) {
            System.out.println("enter seat number");
            int seatNumber = scanner.nextInt();
            try {
                Ticket ticket = ticketService.findTicketBySeatNumber(seatNumber, trip);
                System.out.println("you have to enter ticket owner info");
                Owner owner = addOwner();
                ticket.setTicketType(TicketType.UNAVAILABLE);
                ticket.setOwner(owner);
                ticket.setReservation(reservation);
                ticketService.update(ticket);
                Integer availableSeat = bus.getAvailableSeat();
                int newAvailableSeat = availableSeat - 1;
                bus.setAvailableSeat(newAvailableSeat);
                busService.update(bus);
                totalPrice = totalPrice + tripPrice;

            } catch (RuntimeException e) {
                e.getMessage();
            }
        }
        reservation.setTotalPrice(totalPrice);
        reservationService.update(reservation);
    }

    private static void searchForTrip() throws ParseException {
        Date date = null;
        String companyName = null;
        BusType busType = null;
        Date minTime = null;
        Date maxTime = null;
        Long minPrice = null;
        Long maxPrice = null;

        System.out.println("origin:");
        City origin = City.getValue(scanner.next());
        System.out.println("destination:");
        City destination = City.getValue(scanner.next());

        System.out.println("do you want to enter date? 1)yes 2)no");
        int searchDate = scanner.nextInt();
        if (searchDate == 1) {
            System.out.println("enter date(yyyy-MM-dd):");
            date = new SimpleDateFormat("yyyy-MM-dd").parse(scanner.next());
        }

        System.out.println("do you want to enter busType? 1)yes 2)no");
        int searchBusType = scanner.nextInt();
        if (searchBusType == 1) {
            System.out.println("enter type:(VIP or NORMAl)");
            busType = BusType.getValue(scanner.next());
        }

        System.out.println("do you want to enter companyName? 1)yes 2)no");
        int searchCompany = scanner.nextInt();
        if (searchCompany == 1) {
            System.out.println("enter companyName:");
            companyName = scanner.next();
        }

        System.out.println("Do you want to set a limit for time? 1)yes 2)no");
        int searchTimeLimit = scanner.nextInt();
        if (searchTimeLimit == 1) {
            System.out.println("enter min time:(hh:mm)");
            minTime = new SimpleDateFormat("hh:mm").parse(scanner.next());
            System.out.println("enter max time:(hh:mm)");
            maxTime = new SimpleDateFormat("hh:mm").parse(scanner.next());
        }
        System.out.println("Do you want to set a limit for price? 1)yes 2)no");
        int searchPriceLimit = scanner.nextInt();
        if (searchPriceLimit == 1) {
            System.out.println("enter min price:");
            minPrice = Long.parseLong(scanner.next());
            System.out.println("enter max time:");
            maxPrice = Long.parseLong(scanner.next());
        }
        Condition condition = new Condition();
        condition.setDate(date);
        condition.setBusType(busType);
        condition.setCompanyName(companyName);
        condition.setMinTime(minTime);
        condition.setMaxTime(maxTime);
        condition.setMinPrice(minPrice);
        condition.setMaxPrice(maxPrice);
        showTripWithPagination(origin, destination, condition);
    }

    private static void showTripWithPagination(City origin, City destination, Condition condition) {
        System.out.println("enter Number of results:");
        int maxResultInPage = scanner.nextInt();
        int startResult = 0;
        exit:
        while (true) {
            List<TripDto> trips = tripService.listTripByPaginated(origin, destination, condition, startResult, maxResultInPage);
            System.out.println(trips);
            List<TripDto> nextTrips = tripService.listTripByPaginated(origin, destination, condition, startResult + maxResultInPage, maxResultInPage);
            int result = nextTrips.size();
            showPage:
            while (true) {
                System.out.println("1)show details 2)nextPage 3)previousPage 4)exit");
                int choice = scanner.nextInt();
                switch (choice) {
                    case 1:
                        showTicketDetail();
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

    private static void showTicketDetail() {
        System.out.println("enter trip Id:");
        int id = scanner.nextInt();
        Trip trip = tripService.get(id);
        System.out.println(trip);
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
                    showStatusOfBuses();
                    break;
                case 6:
                    exit = true;
                    break;
            }
        } while (!exit);
    }

    private static void showStatusOfBuses() {
        System.out.println("enter bus type:");
        BusType busType = BusType.getValue(scanner.next());
        List<Trip> buses = tripService.findBusReservations(busType);
        System.out.println(buses);
    }

    private static void adminLogin() throws ParseException {
        System.out.println("nationalCode:");
        String nationalCode = scanner.next();
        boolean repeat = true;
        do {
            System.out.println("password:");
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
                System.out.println("for add yourself enter administrative password:");
                String pass = scanner.next();
                if (pass.equals("administrative"))
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
        bus.setNumberOfSeats(availableSeat);
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
            ticket.setTicketType(TicketType.AVAILABLE);
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
