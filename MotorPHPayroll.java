import java.util.*;
import java.io.*;

public class MotorPHPayroll {

    public static void main(String[] args) {
        login();
    }

    // =========================
    // PERSON 1 – LOGIN SYSTEM
    // =========================
    static void login() {

        Scanner sc = new Scanner(System.in);

        System.out.print("Username: ");
        String username = sc.nextLine();

        System.out.print("Password: ");
        String password = sc.nextLine();

        if (!password.equals("12345")) {
            System.out.println("Incorrect username and/or password");
            System.exit(0);
        }

        if (username.equals("employee")) {
            employeeMenu();
        }
        else if (username.equals("payroll staff")) {
            payrollMenu();
        }
        else {
            System.out.println("Incorrect username and/or password");
            System.exit(0);
        }
    }

    // =========================
    // PERSON 1 – EMPLOYEE MENU
    // =========================
    static void employeeMenu() {

        Scanner sc = new Scanner(System.in);

        System.out.println("\nEMPLOYEE MENU");
        System.out.println("1. Enter your employee number");
        System.out.println("2. Exit the program");

        int choice = sc.nextInt();

        if (choice == 1) {

            System.out.print("Enter employee number: ");
            int empNum = sc.nextInt();

            // send employee number to Person 2 module
            readEmployeeData(empNum);

        }
        else if (choice == 2) {
            System.exit(0);
        }
        else {
            System.out.println("Invalid option");
            System.exit(0);
        }
    }

    // =========================
    // PERSON 1 – PAYROLL STAFF MENU
    // =========================
    static void payrollMenu() {

        Scanner sc = new Scanner(System.in);

        System.out.println("\nPAYROLL STAFF MENU");
        System.out.println("1. Process Payroll");
        System.out.println("2. Exit the program");

        int choice = sc.nextInt();

        if (choice == 1) {
            System.out.println("Payroll processing will be implemented here.");
        }
        else if (choice == 2) {
            System.exit(0);
        }
        else {
            System.out.println("Invalid option");
            System.exit(0);
        }
    }

    // =========================
    // PERSON 2 – EMPLOYEE DATA
    // =========================
    static void readEmployeeData(int employeeNumber) {

    }

    // =========================
    // PERSON 3 – ATTENDANCE LOGIC
    // =========================
    static double computeDailyHours(String login, String logout) {
        return 0;
    }

    // =========================
    // PERSON 4 – HOURS AGGREGATION
    // =========================
    static double computeCutoffHours(int employeeNumber) {
        return 0;
    }

    // =========================
    // PERSON 5 – SALARY COMPUTATION
    // =========================
    static void computeSalary(double hours, double rate) {

    }

    // =========================
    // PERSON 6 – OUTPUT DISPLAY
    // =========================
    static void printPayroll() {

    }

}