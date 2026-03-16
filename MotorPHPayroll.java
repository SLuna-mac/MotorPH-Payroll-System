import java.util.*; 
import java.io.*;
import java.time.Duration;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class MotorPHPayroll {

    public static void main(String[] args) {
        login(); // program starts by asking user credentials
    }

    // =========================
    // PERSON 1 – LOGIN SYSTEM
    // =========================
    static void login() {

        Scanner sc = new Scanner(System.in);

        System.out.print("Username: ");
        String username = sc.nextLine(); // read username from user

        System.out.print("Password: ");
        String password = sc.nextLine(); // read password from user

        if (!password.equals("12345")) { // check required password
            System.out.println("Incorrect username and/or password");
            System.exit(0); // terminate program if password is incorrect
        }

        if (username.equalsIgnoreCase("employee")) { // employee login branch
            employeeMenu();
        }
        else if (username.equalsIgnoreCase("payroll staff")) { // payroll staff branch
            payrollMenu();
        }
        else {
            System.out.println("Incorrect username and/or password");
            System.exit(0); // terminate program if username invalid
        }
    }

    static void employeeMenu() {

        Scanner sc = new Scanner(System.in);

        System.out.println("\nEMPLOYEE MENU");
        System.out.println("1. Enter your employee number");
        System.out.println("2. Exit the program");

        int choice = sc.nextInt(); // read menu option

        if (choice == 1) {

            System.out.print("Enter employee number: ");
            int empNum = sc.nextInt(); // employee enters their ID

            readEmployeeData(empNum); // display employee info from CSV

        }
        else {
            System.exit(0); // exit program
        }
    }

    static void payrollMenu() {

        Scanner sc = new Scanner(System.in);

        System.out.println("\nPAYROLL STAFF MENU");
        System.out.println("1. Process Payroll");
        System.out.println("2. Exit");

        int choice = sc.nextInt(); // payroll menu selection

        if (choice == 1) {
            payrollProcessMenu(); // open payroll processing options
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
            // open employee CSV file for reading

            br.readLine(); // skip header row
            String line;

            while ((line = br.readLine()) != null) { // loop through each employee record

                String[] data = line.split(",");

                int empNumber = Integer.parseInt(data[0].trim()); // extract employee ID

                if (empNumber == employeeNumber) { // match entered employee number

                    String lastName = data[1].trim();
                    String firstName = data[2].trim();
                    String birthday = data[3].trim();
                    String hourlyRate = data[data.length - 1].trim(); // last column = hourly rate

                    System.out.println("\nEmployee Number: " + empNumber);
                    System.out.println("Employee Name: " + firstName + " " + lastName);
                    System.out.println("Birthday: " + birthday);
                    System.out.println("Hourly Rate: " + hourlyRate);

                    return; // stop searching once employee is found
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

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("h:mm:ss a"); 
        // format used by the attendance CSV file

        LocalTime loginTime = LocalTime.parse(login, formatter); // convert login text to time object
        LocalTime logoutTime = LocalTime.parse(logout, formatter); // convert logout text to time object

        LocalTime startWork = LocalTime.of(8, 0);  // official start time
        LocalTime endWork = LocalTime.of(17, 0);   // official end time

        if (loginTime.isBefore(startWork))
            loginTime = startWork; // ignore early login before 8 AM

        if (logoutTime.isAfter(endWork))
            logoutTime = endWork; // ignore overtime beyond 5 PM

        if (logoutTime.isBefore(loginTime))
            return 0; // safety check for invalid data

        Duration worked = Duration.between(loginTime, logoutTime); 
        // calculate difference between login and logout

        return worked.toMinutes() / 60.0; // convert minutes to hours (no rounding rule)
    }

    // =========================
    // PERSON 4 – HOURS AGGREGATION
    // =========================
    static double[] computeCutoffHours(int employeeNumber) {

        double cutoff1 = 0; // hours worked from day 1–15
        double cutoff2 = 0; // hours worked from day 16–30

        try (BufferedReader br = new BufferedReader(new FileReader("attendance.csv"))) {

            String line;

            while ((line = br.readLine()) != null) { // read attendance records

                String[] data = line.split(",");

                int id = Integer.parseInt(data[0].trim()); // employee ID column

                if (id == employeeNumber) {

                    String date = data[3].trim();
                    int day = Integer.parseInt(date.split("/")[1]); 
                    // extract day from date to determine cutoff period

                    String login = data[4].trim();
                    String logout = data[5].trim();

                    double hours = computeDailyHours(login, logout); 
                    // call Person 3 method to compute daily work hours

                    if (day <= 15)
                        cutoff1 += hours; // accumulate first cutoff hours
                    else
                        cutoff2 += hours; // accumulate second cutoff hours
                }
            }

        } catch (Exception e) {
            System.out.println("Error reading attendance file.");
        }

        return new double[]{cutoff1, cutoff2}; // return both cutoff totals
    }

    // =========================
    // PERSON 5 – SALARY COMPUTATION
    // =========================
    static double[] computeSalary(double hours1, double hours2, double hourlyRate) {

        double grossSalary1 = hours1 * hourlyRate; // salary for first cutoff
        double grossSalary2 = hours2 * hourlyRate; // salary for second cutoff
        double totalGrossSalary = grossSalary1 + grossSalary2; // total monthly gross

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
                    SSS = contribution; // select correct SSS bracket
                    break;
                }
            }

        } catch (IOException e) {
            System.out.println("Error reading file");
        }

        double philHealth = totalGrossSalary * 0.03; // philhealth rate

        if (totalGrossSalary <= 10000)
            philHealth = 300;
        else if (totalGrossSalary >= 60000)
            philHealth = 1800;

        double philHealthEmployee = philHealth * 0.5; // employee share

        double pagIbigRate = 0;

        if (totalGrossSalary >= 1000 && totalGrossSalary <= 1500)
            pagIbigRate = 0.01;
        else if (totalGrossSalary > 1500)
            pagIbigRate = 0.02;

        double pagIbigEmployee = Math.min(totalGrossSalary * pagIbigRate, 100); 
        // Pag-IBIG capped at 100

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

        double totalDeductions = SSS + philHealthEmployee + pagIbigEmployee + tax; // sum deductions
        double netSalary = totalGrossSalary - totalDeductions; // final take-home pay

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

            processOneEmployee(empNum); // process payroll for selected employee

        }
        else if (choice == 2) {

            processAllEmployees(); // process payroll for all employees

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

                String[] data = line.split(",");

                int id = Integer.parseInt(data[0].trim());

                if (id == employeeNumber) {

                    double hourlyRate = Double.parseDouble(data[data.length - 1].trim()); 
                    // extract hourly rate of employee

                    double[] hours = computeCutoffHours(employeeNumber); 
                    // get cutoff hours from Person 4 module

                    double[] payroll = computeSalary(hours[0], hours[1], hourlyRate); 
                    // compute payroll using Person 5 module

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

                int employeeNumber = Integer.parseInt(line.split(",")[0].trim()); 
                // get employee ID from CSV

                processOneEmployee(employeeNumber); // process payroll for each employee
            }

        } catch (Exception e) {
            System.out.println("Error processing payroll.");
        }
    }
}
