package com.java.loanmanagement.model;

public class HomeLoan extends Loan {
    private String propertyAddress;
    private int propertyValue; // Declared propertyValue field

    public HomeLoan(int loanId, Customer customer, double principalAmount, double interestRate, int loanTerm, String loanType, String loanStatus, String propertyAddress, int propertyValue) {
        super(loanId, customer, principalAmount, interestRate, loanTerm, loanType, loanStatus);
        this.propertyAddress = propertyAddress;
        this.propertyValue = propertyValue;
    }

    // Getters and Setters
    public String getPropertyAddress() {
        return propertyAddress;
    }

    public void setPropertyAddress(String propertyAddress) {
        this.propertyAddress = propertyAddress;
    }

    public int getPropertyValue() {
        return propertyValue;
    }

    public void setPropertyValue(int propertyValue) {
        this.propertyValue = propertyValue;
    }

    // Override print method
    @Override
    public void printLoanInfo() {
        super.printLoanInfo();
        System.out.println("Property Address: " + propertyAddress);
        System.out.println("Property Value: " + propertyValue);
    }
}