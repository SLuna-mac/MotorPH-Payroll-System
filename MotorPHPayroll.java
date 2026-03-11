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

    String line;

    try {

        BufferedReader br = new BufferedReader(new FileReader("employees.csv"));

        br.readLine(); // skip header row

        while ((line = br.readLine()) != null) {

            String[] data = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");

            int empNumber = Integer.parseInt(data[0].trim());
       
    }

            if (empNumber == employeeNumber) {

                String firstName = data[1].trim();
                String lastName = data[2].trim();
                String birthday = data[3].trim();
                String hourlyRate = data[data.length - 1].replace("\"", "").trim();

                //System.out.println("Employee Number: " + empNumber);
                //System.out.println("Name: " + firstName + " " + lastName);
                //System.out.println("Birthday: " + birthday);
                //System.out.println("Hourly Rate: " + hourlyRate);

                break;
            }
        }

        br.close();

    } catch (Exception e) {
        e.printStackTrace();
    }
}
    // =========================
    // PERSON 3 – ATTENDANCE LOGIC
    // =========================
    static double computeDailyHours(String login, String logout) {

        // Person 3 will implement time calculations here
        return 0;

    }

    // =========================
    // PERSON 4 – HOURS AGGREGATION
    // =========================
    static double computeCutoffHours(int employeeNumber) {

        double totalHours = 0.0;
        String line;

        try (BufferedReader br = new BufferedReader(new FileReader("attendance.csv"))) {

            while ((line = br.readLine()) != null) {

                String[] data = line.split(",");

                int id = Integer.parseInt(data[0].trim());

                if (id == employeeNumber) {

                    String login = data[4].trim();
                    String logout = data[5].trim();

                    double dailyHours = computeDailyHours(login, logout);

                    totalHours += dailyHours;
                }
            }

        } catch (Exception e) {
            System.out.println("Error reading attendance file.");
        }

        return totalHours;
    }

    // =========================
    // PERSON 5 – SALARY COMPUTATION
    // =========================
    static void computeSalary(double hours, double rate) {

        // Person 5 will implement salary and deductions here

    }

    // =========================
    // PERSON 6 – OUTPUT DISPLAY
    // =========================
    static void printPayroll() {

        // Person 6 will implement formatted output here

    }
}
