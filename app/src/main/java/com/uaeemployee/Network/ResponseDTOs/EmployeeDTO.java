package com.uaeemployee.Network.ResponseDTOs;

import java.io.Serializable;

public class EmployeeDTO implements Serializable{

    private int EmployeeID;
    private int SubSubOrganizationID;
    private String FirstName;
    private String LastName;
    private String Gender;
    private String Email;
    private String Address;
    private String ContactNo;
    private String Salary;
    private String CountryName;

    public EmployeeDTO(int employeeID, int subSubOrganizationID, String firstName, String lastName, String gender, String email, String address, String contactNo, String salary, String countryName) {
        EmployeeID = employeeID;
        SubSubOrganizationID = subSubOrganizationID;
        FirstName = firstName;
        LastName = lastName;
        Gender = gender;
        Email = email;
        Address = address;
        ContactNo = contactNo;
        Salary = salary;
        CountryName = countryName;
    }

    public int getEmployeeID() {
        return EmployeeID;
    }

    public void setEmployeeID(int employeeID) {
        EmployeeID = employeeID;
    }

    public int getSubSubOrganizationID() {
        return SubSubOrganizationID;
    }

    public void setSubSubOrganizationID(int subSubOrganizationID) {
        SubSubOrganizationID = subSubOrganizationID;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String gender) {
        Gender = gender;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getContactNo() {
        return ContactNo;
    }

    public void setContactNo(String contactNo) {
        ContactNo = contactNo;
    }

    public String getSalary() {
        return Salary;
    }

    public void setSalary(String salary) {
        Salary = salary;
    }

    public String getCountryName() {
        return CountryName;
    }

    public void setCountryName(String countryName) {
        CountryName = countryName;
    }
}
