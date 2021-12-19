package ir.makatb;

import java.util.Scanner;

public class Main {
    static final Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        System.out.println("1)manager\n2)customer");
        int role = scanner.nextInt();
        switch (role){
            case 1:
                System.out.println("username:");
                String username = scanner.next();
                System.out.println("password");
                String password = scanner.next();



                break;
            case 2:
                break;
        }
    }
}
