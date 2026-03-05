package com.example;
import java.io.*;
public class Main {

    public static void main(String[] args) {

        try {

            InputStream input = Main.class.getClassLoader()
                    .getResourceAsStream("employees.csv");

            if (input == null) {
                System.out.println("File not found!");
                return;
            }

            BufferedReader br = new BufferedReader(new InputStreamReader(input));

            String line;

            // Skip header
            br.readLine();

            while ((line = br.readLine()) != null) {

                String[] data = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");

                String empNumber = data[0].trim();
                String firstName = data[1].trim();
                String lastName = data[2].trim();
                String birthday = data[3].trim();


                double hourlyRate = Double.parseDouble(
                        data[data.length - 1].replace("\"", "").trim()
                );

                Employee employee = new Employee(
                        empNumber,
                        firstName + " " + lastName,
                        birthday
                        ,
                        hourlyRate
                );

                processEmployee(employee);
            }

            br.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void processEmployee(Employee employee) {

        //System.out.println("Employee Number: " + employee.getEmployeeNumber());
        //System.out.println("Name: " + employee.getName());
        //System.out.println("Birthday: " + employee.getBirthday());
        //System.out.println("Hourly Rate: " + employee.getHourlyRate());

        //System.out.println("-------------------------------");
    }

    // Employee Class
    public static class Employee {

        private String employeeNumber;
        private String name;
        private String birthday;
        private double hourlyRate;

        public Employee(String employeeNumber,
                        String name,
                        String birthday,
                        double hourlyRate) {

            this.employeeNumber = employeeNumber;
            this.name = name;
            this.birthday = birthday;
            this.hourlyRate = hourlyRate;
        }

        public String getEmployeeNumber() { return employeeNumber; }
        public String getName() { return name; }
        public String getBirthday() { return birthday; }
        public double getHourlyRate() { return hourlyRate; }
    }
}
