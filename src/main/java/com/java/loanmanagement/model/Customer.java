package com.java.loanmanagement.model;

public class Customer {
    private int customerId;
    private String name;
    private String email;
    private String phone; 
    private String address;
    private int creditScore;

    
    public Customer() {}

    
    public Customer(int customerId) {
        this.customerId = customerId;
    }

    
    public Customer(int customerId, String name, String email, String phone, String address, int creditScore) { // Changed phoneNumber to phone in constructor
        this.customerId = customerId;
        this.name = name;
        this.email = email;
        this.phone = phone; 
        this.address = address;
        this.creditScore = creditScore;
    }

    // Getters and Setters
    public int getCustomerId() { return customerId; }
    public void setCustomerId(int customerId) { this.customerId = customerId; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPhone() { return phone; } 
    public void setPhone(String phone) { this.phone = phone; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public int getCreditScore() { return creditScore; }
    public void setCreditScore(int creditScore) { this.creditScore = creditScore; }

    
    public void printCustomerInfo() {
        System.out.println("Customer ID: " + customerId);
        System.out.println("Name: " + name);
        System.out.println("Email: " + email);
        System.out.println("Phone Number: " + phone); 
        System.out.println("Address: " + address);
        System.out.println("Credit Score: " + creditScore);
    }
}