package com.java.loanmanagement.model;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class HomeLoanTest {

    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @Before
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @After
    public void tearDown() {
        System.setOut(standardOut);
    }


    @Test
    public void testGettersAndSetters() {
        HomeLoan homeLoan = new HomeLoan(0, null, 0, 0, 0, null, null, null, 0); // Dummy values for setters test
        Customer customer = new Customer(1); // Minimal Customer

        homeLoan.setLoanId(103);
        homeLoan.setCustomer(customer);
        homeLoan.setPrincipalAmount(1000000);
        homeLoan.setInterestRate(7.0);
        homeLoan.setLoanTerm(300);
        homeLoan.setLoanType("HomeLoan");
        homeLoan.setLoanStatus("Approved");
        homeLoan.setPropertyAddress("789 Pine Ln");
        homeLoan.setPropertyValue(1500000);

        assertEquals(103, homeLoan.getLoanId());
        assertEquals(customer, homeLoan.getCustomer());
        assertEquals(1000000, homeLoan.getPrincipalAmount(), 0.01);
        assertEquals(7.0, homeLoan.getInterestRate(), 0.01);
        assertEquals(300, homeLoan.getLoanTerm());
        assertEquals("HomeLoan", homeLoan.getLoanType());
        assertEquals("Approved", homeLoan.getLoanStatus());
        assertEquals("789 Pine Ln", homeLoan.getPropertyAddress());
        assertEquals(1500000, homeLoan.getPropertyValue());
    }

    @Test
    public void testConstructor() {
        Customer customer = new Customer(2); // Minimal Customer
        HomeLoan homeLoan = new HomeLoan(104, customer, 1200000, 7.2, 360, "HomeLoan", "Pending", "101 Oak St", 2000000);

        assertEquals(104, homeLoan.getLoanId());
        assertEquals(customer, homeLoan.getCustomer());
        assertEquals(1200000, homeLoan.getPrincipalAmount(), 0.01);
        assertEquals(7.2, homeLoan.getInterestRate(), 0.01);
        assertEquals(360, homeLoan.getLoanTerm());
        assertEquals("HomeLoan", homeLoan.getLoanType());
        assertEquals("Pending", homeLoan.getLoanStatus());
        assertEquals("101 Oak St", homeLoan.getPropertyAddress());
        assertEquals(2000000, homeLoan.getPropertyValue());
    }

     @Test
    public void testPrintHomeLoanInfo() {
        HomeLoan homeLoan = new HomeLoan(105, new Customer(3, "Test Customer", null, null, null, 0), 1500000, 6.8, 240, "HomeLoan", "Approved", "456 Elm St", 2500000);
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        homeLoan.printLoanInfo(); // Call printLoanInfo (inherited from Loan)

        String expectedOutput = outContent.toString();
        assertTrue(expectedOutput.contains("Loan ID: 105"));
        assertTrue(expectedOutput.contains("Customer Name: Test Customer"));
        assertTrue(expectedOutput.contains("Property Address: 456 Elm St")); // Specific to HomeLoan
        System.setOut(System.out); // Restore standard output
    }
}