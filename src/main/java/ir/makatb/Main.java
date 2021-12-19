package ir.makatb;

import ir.makatb.model.Admin;
import ir.makatb.service.AdminService;

import java.util.Scanner;

public class Main {
    static final Scanner scanner = new Scanner(System.in);
    static final AdminService adminService = new AdminService();

    public static void main(String[] args) {

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
        System.out.println("password");
        String password = scanner.next();
        Admin admin = adminService.findByUsername(username);
        if (admin.getPassword().equals(password)) {
            System.out.println("1)add new Admin\n2)add new Company\n3)add new Bus\n2)add new Ticket");
        }else{
            System.out.println("wrong password");
        }
    }
}
