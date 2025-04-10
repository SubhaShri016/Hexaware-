package com.java.loanmanagement.model;

import static org.junit.Assert.*;
import org.junit.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class LoanTest {

    @Test
    public void testGettersAndSetters() {
        Loan loan = new Loan();
        Customer customer = new Customer(1); // Using minimal Customer constructor for simplicity

        loan.setLoanId(101);
        loan.setCustomer(customer);
        loan.setPrincipalAmount(500000);
        loan.setInterestRate(7.5);
        loan.setLoanTerm(36);
        loan.setLoanType("HomeLoan");
        loan.setLoanStatus("Pending");

        assertEquals(101, loan.getLoanId());
        assertEquals(customer, loan.getCustomer());
        assertEquals(500000, loan.getPrincipalAmount(), 0.01);
        assertEquals(7.5, loan.getInterestRate(), 0.01);
        assertEquals(36, loan.getLoanTerm());
        assertEquals("HomeLoan", loan.getLoanType());
        assertEquals("Pending", loan.getLoanStatus());
    }

    @Test
    public void testConstructor() {
        Customer customer = new Customer(2); // Using minimal Customer constructor
        Loan loan = new Loan(102, customer, 750000, 8.0, 48, "CarLoan", "Approved");

        assertEquals(102, loan.getLoanId());
        assertEquals(customer, loan.getCustomer());
        assertEquals(750000, loan.getPrincipalAmount(), 0.01);
        assertEquals(8.0, loan.getInterestRate(), 0.01);
        assertEquals(48, loan.getLoanTerm());
        assertEquals("CarLoan", loan.getLoanType());
        assertEquals("Approved", loan.getLoanStatus());
    }

    @Test
    public void testPrintLoanInfo() {
        Loan loan = new Loan(103, new Customer(3, "Test Customer", null, null, null, 0), 600000, 6.9, 36, "HomeLoan", "Pending");
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        loan.printLoanInfo();

        String expectedOutput = outContent.toString(); // Capture the output
        assertTrue(expectedOutput.contains("Loan ID: 103")); // Basic verification - check for Loan ID
        assertTrue(expectedOutput.contains("Customer Name: Test Customer")); // Check for Customer Name
        assertTrue(expectedOutput.contains("Loan Type: HomeLoan")); // Check for Loan Type
        System.setOut(System.out); // Restore standard output
    }
}