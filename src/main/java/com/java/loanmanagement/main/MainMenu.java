package com.java.loanmanagement.main;
import com.java.loanmanagement.model.Customer;

import com.java.loanmanagement.Dao.LoanRepositoryImpl;
import com.java.loanmanagement.Exception.InvalidLoanException;
import com.java.loanmanagement.model.Loan;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class MainMenu {
    public static void main(String[] args) {
        LoanRepositoryImpl loanRepo = new LoanRepositoryImpl();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n======= Loan Management System =======");
            System.out.println("1. Apply for Loan");
            System.out.println("2. Calculate Interest");
            System.out.println("3. Check Loan Status");
            System.out.println("4. Calculate EMI");
            System.out.println("5. Make Loan Repayment");
            System.out.println("6. Retrieve Loan Details");
            System.out.println("7. List All Loans");
            System.out.println("8. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1:
                    applyLoan(loanRepo, scanner);
                    break;
                case 2:
                    calculateInterest(loanRepo, scanner);
                    break;
                case 3:
                    checkLoanStatus(loanRepo, scanner);
                    break;
                case 4:
                    calculateEMI(loanRepo, scanner);
                    break;
                case 5:
                    makeRepayment(loanRepo, scanner);
                    break;
                case 6:
                    retrieveLoanDetails(loanRepo, scanner);
                    break;
                case 7:
                    listAllLoans(loanRepo);
                    break;
                case 8:
                    System.out.println("✅ Exiting Loan Management System. Goodbye!");
                    scanner.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("❌ Invalid choice. Please try again.");
            }
        }
    }

    private static void applyLoan(LoanRepositoryImpl loanRepo, Scanner scanner) {
        System.out.println("\n--- Apply for Loan ---");
        System.out.print("Enter Customer ID: ");
        int customerId = scanner.nextInt();
        scanner.nextLine(); 

        Customer customer = new Customer();
        customer.setCustomerId(customerId);
        
        System.out.println("DEBUG: Using placeholder Customer object for ID: " + customerId);

        System.out.print("Enter Loan Type (e.g., Home, Car, Personal): ");
        String loanType = scanner.nextLine();

        System.out.print("Enter Principal Amount: ");
        double principal = scanner.nextDouble();

        System.out.print("Enter Interest Rate (e.g., 5.5 for 5.5%): ");
        double interestRate = scanner.nextDouble();

        System.out.print("Enter Loan Term (in months): ");
        int loanTerm = scanner.nextInt();
        scanner.nextLine(); 

        try {
            
            System.out.println("Attempting to generate next Loan ID...");
            int nextLoanId = loanRepo.getNextLoanId();

            
            System.out.println("Creating Loan object with ID: " + nextLoanId);
            Loan loan = new Loan(nextLoanId, customer, principal, interestRate, loanTerm, loanType, "Pending"); // Use generated ID

            
            System.out.println("Submitting loan application to repository...");
            boolean success = loanRepo.applyLoan(loan);

            if (success) {
                System.out.println("✅ Loan applied successfully! Assigned Loan ID: " + nextLoanId);
            } else {
                System.out.println("❌ Loan application failed. Check error messages above.");
                
            }
        } catch (SQLException e) {
            System.err.println("❌ Database Error occurred during loan application process: " + e.getMessage());
            e.printStackTrace(); 
        } catch (Exception e) {
            System.err.println("❌ An unexpected application error occurred: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static void calculateInterest(LoanRepositoryImpl loanRepo, Scanner scanner) {
        System.out.print("Enter Loan ID: ");
        int loanId = scanner.nextInt();
        try {
            double interest = loanRepo.calculateInterest(loanId);
            System.out.println("✅ Calculated Interest: " + interest);
        } catch (InvalidLoanException e) {
            System.out.println("❌ " + e.getMessage());
        }
    }

    private static void checkLoanStatus(LoanRepositoryImpl loanRepo, Scanner scanner) {
        System.out.print("Enter Loan ID: ");
        int loanId = scanner.nextInt();
        try {
            String status = loanRepo.loanStatus(loanId);
            System.out.println("✅ Loan Status: " + status);
        } catch (InvalidLoanException e) {
            System.out.println("❌ " + e.getMessage());
        }
    }

    private static void calculateEMI(LoanRepositoryImpl loanRepo, Scanner scanner) {
        System.out.print("Enter Loan ID: ");
        int loanId = scanner.nextInt();
        try {
            double emi = loanRepo.calculateEMI(loanId);
            System.out.println("✅ Monthly EMI: " + emi);
        } catch (InvalidLoanException e) {
            System.out.println("❌ " + e.getMessage());
        }
    }

    private static void makeRepayment(LoanRepositoryImpl loanRepo, Scanner scanner) {
        System.out.print("Enter Loan ID: ");
        int loanId = scanner.nextInt();
        System.out.print("Enter Repayment Amount: ");
        double amount = scanner.nextDouble();

        try {
            boolean success = loanRepo.loanRepayment(loanId, amount);
            if (success) {
                System.out.println("✅ Loan repayment successful!");
            } else {
                System.out.println("❌ Loan repayment failed.");
            }
        } catch (InvalidLoanException e) {
            System.out.println("❌ " + e.getMessage());
        }
    }

    private static void retrieveLoanDetails(LoanRepositoryImpl loanRepo, Scanner scanner) {
        System.out.print("Enter Loan ID: ");
        int loanId = scanner.nextInt();
        try {
            Loan loan = loanRepo.getLoanById(loanId);
            System.out.println("\n===== Loan Details =====");
            System.out.println("Loan ID: " + loan.getLoanId());
            System.out.println("Customer ID: " + loan.getCustomer().getCustomerId());
            System.out.println("Loan Type: " + loan.getLoanType());
            System.out.println("Principal Amount: " + loan.getPrincipalAmount());
            System.out.println("Interest Rate: " + loan.getInterestRate());
            System.out.println("Loan Term: " + loan.getLoanTerm());
            System.out.println("Loan Status: " + loan.getLoanStatus());
        } catch (InvalidLoanException e) {
            System.out.println("❌ " + e.getMessage());
        }
    }

    private static void listAllLoans(LoanRepositoryImpl loanRepo) {
        List<Loan> loans = loanRepo.getAllLoans();
        if (loans.isEmpty()) {
            System.out.println("❌ No loans found.");
        } else {
            System.out.println("\n===== Loan List =====");
            for (Loan loan : loans) {
                System.out.println("Loan ID: " + loan.getLoanId() +
                       // ", Customer ID: " + loan.getCustomer().getCustomerId() +
                        ", Loan Type: " + loan.getLoanType() +
                        ", Amount: " + loan.getPrincipalAmount() +
                        ", Status: " + loan.getLoanStatus());
            }
        }
    }
}