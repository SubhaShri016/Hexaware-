package com.java.loanmanagement.model;

public class Loan {
    private int loanId; // **Added declaration for loanId field**
    private Customer customer;
    private double principalAmount;
    private double interestRate;
    private int loanTerm;
    private String loanType;
    private String loanStatus;

    public Loan() {
    }

    public Loan(int loanId, Customer customer, double principalAmount, double interestRate, int loanTerm, String loanType, String loanStatus) {
        this.loanId = loanId;
        this.customer = customer;
        this.principalAmount = principalAmount; // Corrected assignment
        this.interestRate = interestRate;
        this.loanTerm = loanTerm;
        this.loanType = loanType;
        this.loanStatus = loanStatus;
    }

    // Getters and Setters

    public int getLoanId() {
        return loanId;
    }

    public void setLoanId(int loanId) {
        this.loanId = loanId;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public double getPrincipalAmount() {
        return principalAmount;
    }

    public void setPrincipalAmount(double principalAmount) {
        this.principalAmount = principalAmount; // Corrected assignment
    }

    public double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(double interestRate) {
        this.interestRate = interestRate;
    }

    public int getLoanTerm() {
        return loanTerm;
    }

    public void setLoanTerm(int loanTerm) {
        this.loanTerm = loanTerm;
    }

    public String getLoanType() {
        return loanType;
    }

    public void setLoanType(String loanType) {
        this.loanType = loanType;
    }

    public String getLoanStatus() {
        return loanStatus;
    }

    public void setLoanStatus(String loanStatus) {
        this.loanStatus = loanStatus;
    }

    public void printLoanInfo() {
        System.out.println("Loan ID: " + loanId);
        System.out.println("Customer Name: " + customer.getName());
        System.out.println("Loan Type: " + loanType);
        System.out.println("Principal Amount: " + principalAmount);
        System.out.println("Interest Rate: " + interestRate);
        System.out.println("Loan Term: " + loanTerm + " months");
        System.out.println("Loan Status: " + loanStatus);
    }
}