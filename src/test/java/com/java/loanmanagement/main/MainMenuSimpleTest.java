package com.java.loanmanagement.main;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

public class MainMenuSimpleTest {

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
    public void testMainMenu_Exit() {
        provideInput("8\n");
        MainMenu.main(new String[]{});
        assertTrue(getOutput().contains("Exiting Loan Management System. Goodbye!"));
    }

    @Test
    public void testMainMenu_InvalidChoice() {
        provideInput("9\n8\n");
        MainMenu.main(new String[]{});
        assertTrue(getOutput().contains("Invalid choice. Please try again."));
        assertTrue(getOutput().contains("Exiting Loan Management System. Goodbye!"));
    }

    @Test
    public void testApplyLoan_MenuOption() {
        provideInput("1\n1\nHome\n10000\n5\n12\n8\n");
        MainMenu.main(new String[]{});
        assertTrue(getOutput().contains("--- Apply for Loan ---"));
        assertTrue(getOutput().contains("Exiting Loan Management System. Goodbye!"));
    }

    @Test
    public void testCalculateInterest_MenuOption() {
        provideInput("2\n101\n8\n");
        MainMenu.main(new String[]{});
        assertTrue(getOutput().contains("Enter Loan ID:"));
        assertTrue(getOutput().contains("Exiting Loan Management System. Goodbye!"));
    }

     @Test
    public void testCalculateInterest_MenuOption_InvalidLoanId() {
        provideInput("2\n999\n8\n"); // Invalid Loan ID for Calculate Interest
        MainMenu.main(new String[]{});
        assertTrue(getOutput().contains("❌ InvalidLoanException: Loan ID not found.")); // Check for InvalidLoanException message
        assertTrue(getOutput().contains("Exiting Loan Management System. Goodbye!"));
    }

    @Test
    public void testCheckLoanStatus_MenuOption() {
        provideInput("3\n201\n8\n");
        MainMenu.main(new String[]{});
        assertTrue(getOutput().contains("Enter Loan ID:"));
        assertTrue(getOutput().contains("Exiting Loan Management System. Goodbye!"));
    }

    @Test
    public void testCheckLoanStatus_MenuOption_InvalidLoanId() {
        provideInput("3\n999\n8\n"); // Invalid Loan ID for Check Loan Status
        MainMenu.main(new String[]{});
        assertTrue(getOutput().contains("❌ InvalidLoanException: Loan ID not found.")); // Check for InvalidLoanException message
        assertTrue(getOutput().contains("Exiting Loan Management System. Goodbye!"));
    }

    @Test
    public void testCalculateEMI_MenuOption() {
        provideInput("4\n301\n8\n");
        MainMenu.main(new String[]{});
        assertTrue(getOutput().contains("Enter Loan ID:"));
        assertTrue(getOutput().contains("Exiting Loan Management System. Goodbye!"));
    }

    @Test
    public void testCalculateEMI_MenuOption_InvalidLoanId() {
        provideInput("4\n999\n8\n"); // Invalid Loan ID for Calculate EMI
        MainMenu.main(new String[]{});
        assertTrue(getOutput().contains("❌ InvalidLoanException: Loan ID not found.")); // Check for InvalidLoanException message
        assertTrue(getOutput().contains("Exiting Loan Management System. Goodbye!"));
    }

    @Test
    public void testMakeLoanRepayment_MenuOption() {
        provideInput("5\n401\n100\n8\n");
        MainMenu.main(new String[]{});
        assertTrue(getOutput().contains("Enter Loan ID:"));
        assertTrue(getOutput().contains("Exiting Loan Management System. Goodbye!"));
    }

    @Test
    public void testMakeLoanRepayment_MenuOption_InvalidLoanId() {
        provideInput("5\n999\n100\n8\n"); // Invalid Loan ID for Make Loan Repayment
        MainMenu.main(new String[]{});
        assertTrue(getOutput().contains("❌ InvalidLoanException: Loan ID not found.")); // Check for InvalidLoanException message
        assertTrue(getOutput().contains("Exiting Loan Management System. Goodbye!"));
    }


    @Test
    public void testRetrieveLoanDetails_MenuOption() {
        provideInput("6\n501\n8\n");
        MainMenu.main(new String[]{});
        assertTrue(getOutput().contains("Enter Loan ID:"));
        assertTrue(getOutput().contains("Exiting Loan Management System. Goodbye!"));
    }

     @Test
    public void testRetrieveLoanDetails_MenuOption_InvalidLoanId() {
        provideInput("6\n999\n8\n"); // Invalid Loan ID for Retrieve Loan Details
        MainMenu.main(new String[]{});
        assertTrue(getOutput().contains("❌ InvalidLoanException: Loan ID not found.")); // Check for InvalidLoanException message
        assertTrue(getOutput().contains("Exiting Loan Management System. Goodbye!"));
    }

    @Test
    public void testListAllLoans_MenuOption() {
        provideInput("7\n8\n");
        MainMenu.main(new String[]{});
        assertTrue(getOutput().contains("===== Loan List ====="));
        assertTrue(getOutput().contains("Exiting Loan Management System. Goodbye!"));
    }
}