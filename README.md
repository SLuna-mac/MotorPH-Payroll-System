MotorPHPayrollSystem Group 45
CP1 - MS2 Source Code 

Basic Payroll Program

This program reads employee information and attendance records from CSV files, computes total hours worked per payroll cutoff, and calculates salary including deductions.

---

How the Program Works

Imports

* BufferedReader and FileReader are used to read CSV files line by line
* LocalTime and Duration are used to compute working hours
* DateTimeFormatter is used to parse time values
* Scanner is used to accept user input

---

Main Class and Method
public class MotorPHPayroll

* This is the main class of the program
* The main method starts the system by calling the login method

---

Login System

* The program asks for a username and password
* Two roles are supported:

  * employee
  * payroll staff
* Incorrect credentials will terminate the program

---

Employee Menu

* Allows user to enter an employee number
* Reads employee details from employees.csv
* Displays:

  * Employee Number
  * Employee Name
  * Birthday
  * Hourly Rate

---

Payroll Menu

* Allows payroll staff to process:

  * One employee
  * All employees

---

Reading Employee Data

* The program reads employees.csv line by line
* Skips the header row
* Matches the entered employee number
* Extracts employee details and hourly rate

---

Attendance Processing

* The program reads attendance.csv
* Matches records based on employee number
* Extracts:

  * Date
  * Login time
  * Logout time

---

Compute Daily Hours

* Login and logout times are parsed using H:mm format
* Grace period is applied until 8:10 AM
* Maximum working time is capped at 5:00 PM
* One hour lunch break is deducted
* Total hours are capped at 8 hours

---

Cutoff Computation

* Days 1–15 → Cutoff 1
* Days 16–end → Cutoff 2
* Hours are accumulated separately for each cutoff

---

Salary Computation

* Gross salary is calculated using:
  Hours × Hourly Rate

* Deductions include:

  * SSS (based on salary bracket from SSS_Table.csv)
  * PhilHealth (3% with limits applied)
  * Pag-IBIG (1% or 2% capped at 100)
  * Tax (based on taxable income brackets)

* Net Salary is computed as:
  Gross Salary − Total Deductions

---

Output

* Displays per employee:

  * Cutoff 1 Hours and Salary
  * Cutoff 2 Hours and Salary
  * Total Deductions
  * Net Salary

---

Notes

* CSV files must be in the same directory as the program

* File names must match exactly:
  employees.csv
  attendance.csv
  SSS_Table.csv

* The program currently processes available attendance data

* If only one date is present, only Cutoff 1 will have values

* The system follows payroll rules such as:

  * Grace period
  * Lunch deduction
  * Maximum working hours

---

End of Program Documentation
