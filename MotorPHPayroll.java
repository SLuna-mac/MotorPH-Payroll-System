import java.util.*;
import java.io.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

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

        try (BufferedReader br = new BufferedReader(new FileReader("employees.csv"))) {

            br.readLine(); // skip header

            while ((line = br.readLine()) != null) {

                String[] data = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");

                int empNumber = Integer.parseInt(data[0].trim());

                if (empNumber == employeeNumber) {

                    String lastName = data[1].trim();
                    String firstName = data[2].trim();
                    String birthday = data[3].trim();
                    String hourlyRate = data[data.length - 1].replace("\"", "").trim();

                    System.out.println("\nEmployee Number: " + empNumber);
                    System.out.println("Employee Name: " + firstName + " " + lastName);
                    System.out.println("Birthday: " + birthday);
                    System.out.println("Hourly Rate: " + hourlyRate);

                    return;
                }
            }

            System.out.println("Employee number does not exist");

        } catch (Exception e) {
            System.out.println("Error reading employee file.");
        }
    }

    // =========================
    // PERSON 3 – ATTENDANCE LOGIC
    // =========================
    static double computeDailyHours(String login, String logout) {

        // Person 3 will implement this
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
<<<<<<< HEAD
    static void computeSalary() {
        Scanner sc = new Scanner(System.in);

        System.out.println("First Cut Off");

        System.out.print("Enter hours worked: ");
        double hoursWorked1 = sc.nextDouble();
        if (hoursWorked1 > 80) {
            hoursWorked1 = 80;
            System.out.println("No overtime applied, maximum 80 hours");
        }

        System.out.print("Enter hourly rate: ");
        double hourlyRate1 = sc.nextDouble();

        double grossSalary1 = hoursWorked1 * hourlyRate1;
        System.out.println("Gross Salary: " + grossSalary1);

        double netSalary1 = grossSalary1;
        System.out.println("Net Salary: " + netSalary1);

        System.out.println("Second Cut Off");

=======
    static void computeSalary {
        Scanner sc = new Scanner(System.in);

        System.out.println("First Cut Off");
        
        System.out.print("Enter hours worked: ");
        double hoursWorked1 = sc.nextDouble();
        if (hoursWorked1 > 80) {
            hoursWorked1 = 80;
            System.out.println("No overtime applied, maximum 80 hours");
        }//No overtime indicated
        System.out.print("Enter hourly rate: ");
        double hourlyRate1 = sc.nextDouble();
        
        double grossSalary1 = hoursWorked1 * hourlyRate1;
        System.out.println("Gross Salary: " + grossSalary1);
        //First cut off
        double netSalary1 = grossSalary1;
        System.out.println("Net Salary: " + netSalary1);

        System.out.println("Second Cut Off");
        
>>>>>>> 3d03835cda9168fd7bf9922c804eab03b6eda926
        System.out.print("Enter hours worked: ");
        double hoursWorked2 = sc.nextDouble();
        if (hoursWorked2 > 80) {
            hoursWorked2 = 80;
            System.out.println("No overtime applied, maximum 80 hours");
<<<<<<< HEAD
        }

        System.out.print("Enter hourly rate: ");
        double hourlyRate2 = sc.nextDouble();

        double grossSalary2 = hoursWorked2 * hourlyRate2;
        System.out.println("Gross Salary: " + grossSalary2);

        double totalGrossPay = grossSalary1 + grossSalary2;
        double SSS = 0;

        try (BufferedReader br = new BufferedReader(new FileReader("SSS_Table.csv"))) {
            br.readLine();
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length < 3) continue;
                double start = Double.parseDouble(parts[0].trim());
                double end = Double.parseDouble(parts[1].trim());
                double contribution = Double.parseDouble(parts[2].trim());
                if (totalGrossPay >= start && totalGrossPay <= end) {
                    SSS = contribution;
                    break;
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading file!");
        }

        double philHealth = totalGrossPay * 0.03;
        if (totalGrossPay <= 10000) philHealth = 300;
        else if (totalGrossPay >= 60000) philHealth = 1800;

        double philHealthEmployee = philHealth * 0.5;

        double pagIbig = totalGrossPay;
        double employeeRate;
        double maxContribution = 100;

        if (pagIbig >= 1000 && pagIbig <= 1500) employeeRate = 0.01;
        else if (pagIbig > 1500) employeeRate = 0.02;
        else employeeRate = 0;

        double pagIbigEmployee = Math.min(pagIbig * employeeRate, maxContribution);

        double taxableIncome = totalGrossPay - (SSS + philHealthEmployee + pagIbigEmployee);

        double tax;

=======
        }//No overtime indicated
        System.out.print("Enter hourly rate: ");
        double hourlyRate2 = sc.nextDouble();
        //Second cut off
        double grossSalary2 = hoursWorked2 * hourlyRate2;
        System.out.println("Gross Salary: " + grossSalary2);
        //combined for deductions
        double totalGrossPay = grossSalary1 + grossSalary2;
        double SSS = 0;
        //csv file for SSS contribution
        try (BufferedReader br = new BufferedReader(new FileReader("SSS_Table.csv"))) {
    br.readLine();
    String line;
    while ((line = br.readLine()) != null) {
        String[] parts = line.split(",");
        if (parts.length < 3) continue;
        double start = Double.parseDouble(parts[0].trim());
        double end = Double.parseDouble(parts[1].trim());
        double contribution = Double.parseDouble(parts[2].trim());
        if (totalGrossPay >= start && totalGrossPay <= end) {
            SSS = contribution;
            break;
        }
    }
} catch (IOException e) {
    System.out.println("Error reading file!");
}
        //PhilHealth
        double philHealth = totalGrossPay * 0.03;
        if (totalGrossPay <= 10000) philHealth = 300;
        else if (totalGrossPay >= 60000) philHealth = 1800;
        double philHealthEmployee = philHealth * 0.5;
        //Pag-IBIG
        double pagIbig = totalGrossPay;
        double employeeRate;
        double maxContribution = 100;
        if (pagIbig >= 1000 && pagIbig <= 1500) employeeRate = 0.01;
        else if (pagIbig > 1500) employeeRate = 0.02;
        else employeeRate = 0;
        double pagIbigEmployee = Math.min(pagIbig * employeeRate, maxContribution);
        /* Withholding tax
           combined gross pay to get SSS/PagIBIG/PhilHealth contribution.
           SSS/PagIBIG/PhilHealth - combined gross pay for withholding tax.
        */
        double taxableIncome = totalGrossPay - (SSS + philHealthEmployee + pagIbigEmployee);
        double tax;
>>>>>>> 3d03835cda9168fd7bf9922c804eab03b6eda926
        if (taxableIncome <= 20832) tax = 0;
        else if (taxableIncome <= 33333) tax = (taxableIncome - 20833) * 0.20;
        else if (taxableIncome <= 66666) tax = 2500 + (taxableIncome - 33333) * 0.25;
        else if (taxableIncome <= 166666) tax = 10833 + (taxableIncome - 66667) * 0.30;
        else if (taxableIncome <= 666666) tax = 40833.33 + (taxableIncome - 166667) * 0.32;
        else tax = 200833.33 + (taxableIncome - 666667) * 0.35;
<<<<<<< HEAD

        double totalDeductions = SSS + philHealthEmployee + pagIbigEmployee + tax;

        double netSalary2 = grossSalary2 - totalDeductions;

        System.out.println("Deductions:");
=======
        
        double totalDeductions = SSS + philHealthEmployee + pagIbigEmployee + tax;
        //second cut off - total deductions
        double netSalary2 = grossSalary2 - totalDeductions;

        System.out.println("Deductions: ");
>>>>>>> 3d03835cda9168fd7bf9922c804eab03b6eda926
        System.out.println("SSS: " + SSS);
        System.out.println("PhilHealth: " + philHealthEmployee);
        System.out.println("Pag-IBIG: " + pagIbigEmployee);
        System.out.println("Tax: " + tax);
        System.out.println("Total Deductions: " + totalDeductions);
        System.out.println("Net Salary: " + netSalary2);
<<<<<<< HEAD
=======
        
>>>>>>> 3d03835cda9168fd7bf9922c804eab03b6eda926
    }

    // =========================
    // PERSON 6 – OUTPUT DISPLAY
    // =========================
    static void printPayroll(String employeeName,
                             String birthday,
                             double hours1,
                             double hours2,
                             double gross1,
                             double gross2,
                             double net1,
                             double net2) {

        System.out.println("\n==============================");
        System.out.println("MotorPH Payroll Summary");
        System.out.println("==============================");

        System.out.println("Employee Name: " + employeeName);
        System.out.println("Birthday: " + birthday);

        System.out.println("\nCutoff: June 1 - June 15");
        System.out.println("Total Hours Worked: " + hours1);
        System.out.println("Gross Salary: " + gross1);
        System.out.println("Net Salary: " + net1);

        System.out.println("\nCutoff: June 16 - June 30");
        System.out.println("Total Hours Worked: " + hours2);
        System.out.println("Gross Salary: " + gross2);
        System.out.println("Net Salary: " + net2);

        System.out.println("==============================");
    }
}
