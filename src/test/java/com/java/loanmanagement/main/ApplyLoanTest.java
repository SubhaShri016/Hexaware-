package com.java.loanmanagement.main;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

public class ApplyLoanTest {

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
    public void testApplyLoan_SuccessPath() {
        // Correct input order: Customer ID, Principal, Loan Type, Rate, Term
        provideInput("1\n10000\nHome\n5\n12\n"); // Correct Input Order
        ApplyLoan.main(new String[]{});
        assertTrue(getOutput().contains("✅ Loan Applied Successfully!"));
    }

    @Test
    public void testApplyLoan_CustomerNotFound() {
        // Simple customer not found case - provide invalid customer ID
        provideInput("9999\n");
        ApplyLoan.main(new String[]{});
        assertTrue(getOutput().contains("Customer not found"));
    }
}