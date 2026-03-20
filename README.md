MotorPHPayroll
CP1 - MS2 Source Code

Team Details
Each team member is assigned a specific module to ensure clear responsibility and organized development.
Person 1 – Project Lead / Integrator
Responsibilities:
Handles login system, employee menu, and payroll menu
Controls the main program flow
Integrates all modules into one system
Oversees final testing

Person 2 – Employee Data Module
Responsibilities:
Reads employee data from employees.csv
Extracts:
Employee Number
Name
Birthday
Hourly Rate
Displays employee information

Person 3 – Attendance and Time Computation
Responsibilities:
Reads login and logout time from attendance.csv
Applies 10-minute grace period
Applies working limits (8:00 AM to 5:00 PM)
Deducts 1 hour lunch break
Computes daily working hours capped at 8 hours

Person 4 – Cutoff Hours Aggregation
Responsibilities:
Processes attendance records per employee
Calls computeDailyHours() method
Groups hours into two cutoffs:
Day 1 to 15
Day 16 to end
Returns total hours per cutoff

Person 5 – Salary and Deduction Computation
Responsibilities:
Computes gross salary per cutoff using hours × hourly rate
Computes total gross salary from both payroll cutoffs
Applies deductions:
SSS (based on SSS_Table.csv)
PhilHealth (3% with limits applied)
Pag-IBIG (1% or 2% capped at 100)
Withholding tax based on defined income brackets in the program
Computes total deductions and net salary

Person 6 – Payroll Processing and Output
Responsibilities:
Handles payroll processing menu
Processes payroll for one or all employees
Displays output in structured format including:
Employee Number
Cutoff 1 Hours and Gross Salary
Cutoff 2 Hours and Gross Salary
Total Deductions
Net Salary

Program Details
MotorPHPayroll is a Java-based payroll system that reads employee and attendance data from CSV files and computes payroll information.
The program begins with a login system where users can log in as either employee or payroll staff.
Employee users can enter their employee number to view personal details including name, birthday, and hourly rate.
Payroll staff users can process payroll either for a single employee or for all employees.
The system reads employee data from employees.csv and retrieves attendance records from attendance.csv. It calculates daily working hours using login and logout times while applying payroll rules such as grace period, lunch deduction, working hour limits, and an 8-hour cap per day.
Total working hours are grouped into two payroll cutoffs: days 1–15 and days 16–end of the month.
Salary is computed using total hours worked and hourly rate. The system then applies deductions including SSS, PhilHealth, Pag-IBIG, and withholding tax to compute the final net salary.

Files Used
employees.csv
Contains employee information including hourly rate
attendance.csv
Contains employee attendance records with login and logout times
SSS_Table.csv
Contains salary brackets and corresponding SSS contributions

How to Run the Program
Compile the program
javac MotorPHPayroll.java
Run the program
java MotorPHPayroll
Sample Input
Employee View:
Username: employee
Password: 12345
Payroll View:
Username: payroll staff
Password: 12345

Output Description
For payroll processing, the system displays results per employee in the following format:
Employee #: [Employee Number]
Cutoff 1 Hours: [Total Hours]
Gross Salary: [Computed Salary]
Cutoff 2 Hours: [Total Hours]
Gross Salary: [Computed Salary]
Total Deductions: [SSS + PhilHealth + Pag-IBIG + Tax]
Net Salary: [Final Salary After Deductions]
Each employee’s payroll is displayed separately with labeled sections for clarity. All values are formatted to two decimal places for consistency.

Project Plan Link
[MO-IT101 Project Plan_Group 45](https://docs.google.com/document/d/1-yuWogwFI0ELiFEDR-aVTQXcsZ-ACjhQbn9nW-KrSk0/edit?usp=sharing)

End of Documentation
