package com.java.loanmanagement.model;

public class CarLoan extends Loan {
    private String carModel;
    private Double carValue; 

    public CarLoan(int loanId, Customer customer, double principalAmount, double interestRate, int loanTerm, String loanType, String loanStatus, String carModel, Double carValue) {
        super(loanId, customer, principalAmount, interestRate, loanTerm, loanType, loanStatus);
        this.carModel = carModel;
        this.carValue = carValue;
    }

    // Getters and Setters
    public String getCarModel() {
        return carModel;
    }

    public void setCarModel(String carModel) {
        this.carModel = carModel;
    }

    public Double getCarValue() {
        return carValue;
    }

    public void setCarValue(Double carValue) {
        this.carValue = carValue;
    }

    
    @Override
    public void printLoanInfo() {
        super.printLoanInfo();
        System.out.println("Car Model: " + carModel);
        System.out.println("Car Value: " + carValue);
    }
}