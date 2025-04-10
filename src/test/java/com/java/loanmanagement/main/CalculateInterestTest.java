package com.java.loanmanagement.main;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

public class CalculateInterestTest {

    private final InputStream systemIn = System.in;
    private final PrintStream systemOut = System.out;
    private ByteArrayInputStream testIn;
    private ByteArrayOutputStream testOut;

    @Before
    public void setUpOutput() {
        testOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(testOut));
    }

    private void provideInput(String data) {
        testIn = new ByteArrayInputStream(data.getBytes());
        System.setIn(testIn);
    }

    private String getOutput() {
        return testOut.toString();
    }

    @After
    public void restoreSystemInputOutput() {
        System.setIn(systemIn);
        System.setOut(systemOut);
    }

    @Test
    public void testCalculateInterest_ValidLoanId() {
        provideInput("101\n"); // Input valid Loan ID
        CalculateInterest.main(new String[]{});
        assertTrue(getOutput().contains("✅ Interest Amount:")); // Check for success output with interest amount
    }

    @Test
    public void testCalculateInterest_InvalidLoanId() {
        provideInput("999\n"); // Input invalid Loan ID
        CalculateInterest.main(new String[]{});
        assertTrue(getOutput().contains("❌ InvalidLoanException: Loan ID not found.")); // Check for InvalidLoanException message
    }

    @Test
    public void testCalculateInterest_InvalidInput_LoanId_NonNumeric() {
        provideInput("InvalidLoanId\n"); // Input non-numeric Loan ID
        CalculateInterest.main(new String[]{});
        assertTrue(getOutput().contains("❌ Invalid input. Please enter a valid Loan ID (number).")); // Check for InputMismatchException handling message
    }

    @Test
    public void testCalculateInterest_NoInput_LoanId() {
        provideInput("\n"); // Provide no input, just Enter
        CalculateInterest.main(new String[]{});
        assertTrue(getOutput().contains("❌ Invalid input. Please enter a valid Loan ID (number).")); // No input should also be treated as invalid
    }
}