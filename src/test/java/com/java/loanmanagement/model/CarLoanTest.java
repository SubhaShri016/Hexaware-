package com.java.loanmanagement.model;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class CarLoanTest {

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
        CarLoan carLoan = new CarLoan(0, null, 0, 0, 0, null, null, null, 0.0); // Dummy values for setters test
        Customer customer = new Customer(1); // Minimal Customer

        carLoan.setLoanId(105);
        carLoan.setCustomer(customer);
        carLoan.setPrincipalAmount(25000);
        carLoan.setInterestRate(6.0);
        carLoan.setLoanTerm(60);
        carLoan.setLoanType("CarLoan");
        carLoan.setLoanStatus("Approved");
        carLoan.setCarModel("Sedan");
        carLoan.setCarValue(30000.0);

        assertEquals(105, carLoan.getLoanId());
        assertEquals(customer, carLoan.getCustomer());
        assertEquals(25000, carLoan.getPrincipalAmount(), 0.01);
        assertEquals(6.0, carLoan.getInterestRate(), 0.01);
        assertEquals(60, carLoan.getLoanTerm());
        assertEquals("CarLoan", carLoan.getLoanType());
        assertEquals("Approved", carLoan.getLoanStatus());
        assertEquals("Sedan", carLoan.getCarModel());
        assertEquals(30000.0, carLoan.getCarValue(), 0.01);
    }

    @Test
    public void testConstructor() {
        Customer customer = new Customer(2); // Minimal Customer
        CarLoan carLoan = new CarLoan(106, customer, 30000, 6.5, 72, "CarLoan", "Pending", "SUV", 35000.0);

        assertEquals(106, carLoan.getLoanId());
        assertEquals(customer, carLoan.getCustomer());
        assertEquals(30000, carLoan.getPrincipalAmount(), 0.01);
        assertEquals(6.5, carLoan.getInterestRate(), 0.01);
        assertEquals(72, carLoan.getLoanTerm());
        assertEquals("CarLoan", carLoan.getLoanType());
        assertEquals("Pending", carLoan.getLoanStatus());
        assertEquals("SUV", carLoan.getCarModel());
        assertEquals(35000.0, carLoan.getCarValue(), 0.01);
    }
    

     @Test
    public void testPrintCarLoanInfo() {
        CarLoan carLoan = new CarLoan(107, new Customer(3, "Test Customer", null, null, null, 0), 40000, 5.8, 48, "CarLoan", "Approved", "Truck", 45000.0);
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        carLoan.printLoanInfo(); // Call printLoanInfo (inherited from Loan)

        String expectedOutput = outContent.toString();
        assertTrue(expectedOutput.contains("Loan ID: 107"));
        assertTrue(expectedOutput.contains("Customer Name: Test Customer"));
        assertTrue(expectedOutput.contains("Car Model: Truck")); // Specific to CarLoan
        System.setOut(System.out); // Restore standard output
    }
}