import java.util.*; 
import java.io.*;
import java.time.Duration;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

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

        if (username.equalsIgnoreCase("employee")) {
            employeeMenu();
        }
        else if (username.equalsIgnoreCase("payroll staff")) {
            payrollMenu();
        }
        else {
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

        }
        else {
            System.exit(0);
        }
    }

    static void payrollMenu() {

        Scanner sc = new Scanner(System.in);

        System.out.println("\nPAYROLL STAFF MENU");
        System.out.println("1. Process Payroll");
        System.out.println("2. Exit");

        int choice = sc.nextInt();

        if (choice == 1) {
            payrollProcessMenu();
        }
        else {
            System.exit(0);
        }
    }

    // =========================
    // PERSON 2 – EMPLOYEE DATA
    // =========================
    static void readEmployeeData(int employeeNumber) {

        try (BufferedReader br = new BufferedReader(new FileReader("employees.csv"))) {

            br.readLine();
            String line;

            while ((line = br.readLine()) != null) {

                if (line.trim().isEmpty()) continue;

                String[] data = line.split(",");

                int empNumber = Integer.parseInt(data[0].trim());

                if (empNumber == employeeNumber) {

                    String lastName = data[1].trim();
                    String firstName = data[2].trim();
                    String birthday = data[3].trim();
                    String hourlyRate = data[data.length - 1].trim();

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

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("H:mm");

        LocalTime loginTime = LocalTime.parse(login, formatter);
        LocalTime logoutTime = LocalTime.parse(logout, formatter);

        LocalTime startWork = LocalTime.of(8, 0);
        LocalTime endWork = LocalTime.of(17, 0);

        if (loginTime.isBefore(startWork))
            loginTime = startWork;

        if (logoutTime.isAfter(endWork))
            logoutTime = endWork;

        if (logoutTime.isBefore(loginTime))
            return 0;

        Duration worked = Duration.between(loginTime, logoutTime);

        return worked.toMinutes() / 60.0;
    }

    // =========================
    // PERSON 4 – HOURS AGGREGATION
    // =========================
    static double[] computeCutoffHours(int employeeNumber) {

        double cutoff1 = 0;
        double cutoff2 = 0;

        try (BufferedReader br = new BufferedReader(new FileReader("attendance.csv"))) {

            br.readLine();

            String line;

            while ((line = br.readLine()) != null) {

                if (line.trim().isEmpty()) continue;

                String[] data = line.split(",");

                int id = Integer.parseInt(data[0].trim());

                if (id == employeeNumber) {

                    String date = data[3].trim();
                    int day = Integer.parseInt(date.split("/")[1]);

                    String login = data[4].trim();
                    String logout = data[5].trim();

                    double hours = computeDailyHours(login, logout);

                    if (day <= 15)
                        cutoff1 += hours;
                    else
                        cutoff2 += hours;
                }
            }

        } catch (Exception e) {
            System.out.println("Error reading attendance file.");
        }

        return new double[]{cutoff1, cutoff2};
    }

    // =========================
    // PERSON 5 – SALARY COMPUTATION
    // =========================
    static double[] computeSalary(double hours1, double hours2, double hourlyRate) {

        double grossSalary1 = hours1 * hourlyRate;
        double grossSalary2 = hours2 * hourlyRate;
        double totalGrossSalary = grossSalary1 + grossSalary2;

        double SSS = 0;

        try (BufferedReader br = new BufferedReader(new FileReader("SSS_Table.csv"))) {

            br.readLine();
            String line;

            while ((line = br.readLine()) != null) {

                String[] parts = line.split(",");

                double start = Double.parseDouble(parts[0].trim());
                double end = Double.parseDouble(parts[1].trim());
                double contribution = Double.parseDouble(parts[2].trim());

                if (totalGrossSalary >= start && totalGrossSalary <= end) {
                    SSS = contribution;
                    break;
                }
            }

        } catch (IOException e) {
            System.out.println("Error reading file");
        }

        double philHealth = totalGrossSalary * 0.03;

        if (totalGrossSalary <= 10000)
            philHealth = 300;
        else if (totalGrossSalary >= 60000)
            philHealth = 1800;

        double philHealthEmployee = philHealth * 0.5;

        double pagIbigRate = 0;

        if (totalGrossSalary >= 1000 && totalGrossSalary <= 1500)
            pagIbigRate = 0.01;
        else if (totalGrossSalary > 1500)
            pagIbigRate = 0.02;

        double pagIbigEmployee = Math.min(totalGrossSalary * pagIbigRate, 100);

        double taxableIncome = totalGrossSalary - (SSS + philHealthEmployee + pagIbigEmployee);

        double tax;

        if (taxableIncome <= 20832)
            tax = 0;
        else if (taxableIncome <= 33333)
            tax = (taxableIncome - 20833) * 0.20;
        else if (taxableIncome <= 66666)
            tax = 2500 + (taxableIncome - 33333) * 0.25;
        else if (taxableIncome <= 166666)
            tax = 10833 + (taxableIncome - 66667) * 0.30;
        else if (taxableIncome <= 666666)
            tax = 40833.33 + (taxableIncome - 166667) * 0.32;
        else
            tax = 200833.33 + (taxableIncome - 666667) * 0.35;

        double totalDeductions = SSS + philHealthEmployee + pagIbigEmployee + tax;
        double netSalary = totalGrossSalary - totalDeductions;

        return new double[]{grossSalary1, grossSalary2, totalDeductions, netSalary};
    }

    // =========================
    // PERSON 6 – PAYROLL PROCESS
    // =========================
    static void payrollProcessMenu() {

        Scanner sc = new Scanner(System.in);

        System.out.println("\nPROCESS PAYROLL");
        System.out.println("1. One employee");
        System.out.println("2. All employees");
        System.out.println("3. Exit");

        int choice = sc.nextInt();

        if (choice == 1) {

            System.out.print("Enter employee number: ");
            int empNum = sc.nextInt();

            processOneEmployee(empNum);

        }
        else if (choice == 2) {

            processAllEmployees();

        }
        else {
            System.exit(0);
        }
    }

    static void processOneEmployee(int employeeNumber) {

        try (BufferedReader br = new BufferedReader(new FileReader("employees.csv"))) {

            br.readLine();
            String line;

            while ((line = br.readLine()) != null) {

                if (line.trim().isEmpty()) continue;

                String[] data = line.split(",");

                int id = Integer.parseInt(data[0].trim());

                if (id == employeeNumber) {

                    double hourlyRate = Double.parseDouble(data[data.length - 1].trim());

                    double[] hours = computeCutoffHours(employeeNumber);

                    double[] payroll = computeSalary(hours[0], hours[1], hourlyRate);

                    System.out.println("\nCutoff 1 Hours: " + hours[0]);
                    System.out.println("Gross Salary: " + payroll[0]);

                    System.out.println("\nCutoff 2 Hours: " + hours[1]);
                    System.out.println("Gross Salary: " + payroll[1]);

                    System.out.println("\nTotal Deductions: " + payroll[2]);
                    System.out.println("Net Salary: " + payroll[3]);

                    return;
                }
            }

            System.out.println("Employee number does not exist");

        } catch (Exception e) {
            System.out.println("Error processing payroll.");
        }
    }

    static void processAllEmployees() {

        try (BufferedReader br = new BufferedReader(new FileReader("employees.csv"))) {

            br.readLine();
            String line;

            while ((line = br.readLine()) != null) {

                if (line.trim().isEmpty()) continue;

                int employeeNumber = Integer.parseInt(line.split(",")[0].trim());

                processOneEmployee(employeeNumber);
            }

        } catch (Exception e) {
            System.out.println("Error processing payroll.");
        }
    }
}
