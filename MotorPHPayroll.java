import java.util.*;
import java.io.*;

public class MotorPHPayroll {

    public static void main(String[] args) {
        login();
    }

    // =========================
    // PERSON 1 - LOGIN SYSTEM
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
        } else if (username.equals("payroll staff")) {
            payrollMenu();
        } else {
            System.out.println("Incorrect username and/or password");
            System.exit(0);
        }
    }

    static void employeeMenu() {
        Scanner sc = new Scanner(System.in);
        System.out.println("\nEMPLOYEE MENU");
        System.out.println("1. Enter your employee number");
        System.out.println("2. Exit the program");

        int choice = sc.nextInt();
        if (choice == 1) {
            System.out.print("Enter employee number: ");
            int empNum = sc.nextInt();
            readEmployeeData(empNum);
        } else if (choice == 2) {
            System.exit(0);
        } else {
            System.out.println("Invalid option");
            System.exit(0);
        }
    }

    static void payrollMenu() {
        Scanner sc = new Scanner(System.in);
        System.out.println("\nPAYROLL STAFF MENU");
        System.out.println("1. Process Payroll");
        System.out.println("2. Exit the program");

        int choice = sc.nextInt();
        if (choice == 1) {
            System.out.println("Payroll processing will be implemented here.");
        } else if (choice == 2) {
            System.exit(0);
        } else {
            System.out.println("Invalid option");
            System.exit(0);
        }
    }

    // =========================
    // PERSON 2 - EMPLOYEE DATA
    // =========================
    static void readEmployeeData(int employeeNumber) {
        // Implementation for reading employee details
    }

    // =========================
    // PERSON 3 - ATTENDANCE LOGIC
    // =========================
    static double computeDailyHours(String login, String logout) {
        // Logic to calculate hours between login and logout
        return 0.0;
    }

    // =========================
    // PERSON 4 - HOURS AGGREGATION
    // =========================
    static double computeCutoffHours(int employeeNumber) {
        double totalHours = 0.0;

        // try-with-resources: one reader, auto-closes [fixes leader's roast]
        try (BufferedReader br = new BufferedReader(new FileReader("attendance.csv"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                // check employee number (column 0), call compute once (cols 4 & 5)
                if (Integer.parseInt(data[0]) == employeeNumber) {
                    totalHours += computeDailyHours(data[4], data[5]);
                }
            }
        } catch (Exception e) {
            // handles file errors
        }
        return totalHours; 
    }

    // =========================
    // PERSON 5 - SALARY COMPUTATION
    // =========================
    static void computeSalary(double hours, double rate) {
        // Implementation for salary calculation
    }

    // =========================
    // PERSON 6 - OUTPUT DISPLAY
    // =========================
    static void printPayroll() {
        // Implementation for displaying results
    }
}
