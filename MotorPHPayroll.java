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
package com.example;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) {

        int employeeNumber = 10001;

        String[] employee = readEmployeeData(employeeNumber);

        if (employee != null) {
        }

    }

    static String[] readEmployeeData(int employeeNumber) {

        String line;

        try {

            InputStream input = Main.class.getClassLoader()
                    .getResourceAsStream("employees.csv");

            if (input == null) {
                return null;
            }

            BufferedReader br = new BufferedReader(new InputStreamReader(input));

            br.readLine(); // skip header

            while ((line = br.readLine()) != null) {

                String[] data = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");

                int empNumber = Integer.parseInt(data[0].trim());

                if (empNumber == employeeNumber) {

                    String firstName = data[1].trim();
                    String lastName = data[2].trim();
                    String birthday = data[3].trim();
                    String hourlyRate = data[data.length - 1].replace("\"", "").trim();

                    br.close();

                    return new String[]{
                            String.valueOf(empNumber),
                            firstName,
                            lastName,
                            birthday,
                            hourlyRate
                    };
                }
            }

            br.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

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
