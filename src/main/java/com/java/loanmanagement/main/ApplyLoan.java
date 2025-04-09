package com.java.loanmanagement.main;

import com.java.loanmanagement.model.Customer;
import com.java.loanmanagement.Dao.ILoanRepository; 
import com.java.loanmanagement.Dao.LoanRepositoryImpl;
import com.java.loanmanagement.model.Loan;
import com.java.loanmanagement.Util.DBConnUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class ApplyLoan {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
       
        LoanRepositoryImpl repo = new LoanRepositoryImpl();

        System.out.print("Enter Customer ID: ");
        int customerId = scanner.nextInt();
        scanner.nextLine(); 

       
        Customer customer = getCustomerFromDB(customerId);
        if (customer == null) {
            System.out.println("❌ Customer not found with ID: " + customerId + "! Exiting...");
            scanner.close(); // Close scanner
            return;
        }
        System.out.println("Found Customer: " + customer.getName()); 

        System.out.print("Enter Principal Amount: ");
        double principal = scanner.nextDouble();

        System.out.print("Enter Loan Type (e.g., Home, Car, Personal): ");
        String type = scanner.next();
        scanner.nextLine(); // Consume newline

        System.out.print("Enter Interest Rate (e.g., 5.5 for 5.5%): ");
        double rate = scanner.nextDouble();

        System.out.print("Enter Loan Term (in months): ");
        int term = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        try {
           
            System.out.println("Attempting to generate next Loan ID...");
            int nextLoanId = repo.getNextLoanId();

           
            System.out.println("Creating Loan object with ID: " + nextLoanId);
            Loan loan = new Loan(nextLoanId, customer, principal, rate, term, type, "Pending"); // Use generated ID

            
            System.out.println("Submitting loan application to repository...");
            if (repo.applyLoan(loan)) {
                System.out.println("✅ Loan Applied Successfully! Assigned Loan ID: " + nextLoanId);
            } else {
                System.out.println("❌ Failed to Apply for Loan. Check error messages above.");
                
            }

        } catch (SQLException e) {
            System.err.println("❌ Database Error occurred during loan application process: " + e.getMessage());
            e.printStackTrace(); 
        } catch (Exception e) {
            System.err.println("❌ An unexpected application error occurred: " + e.getMessage());
            e.printStackTrace(); 
        } finally {
            scanner.close(); 
            System.out.println("Application finished.");
        }
    }

    
    private static Customer getCustomerFromDB(int customerId) {
        
        String query = "SELECT name, email, address, phone, creditScore FROM Customer WHERE customerId = ?";
        try (Connection conn = DBConnUtil.getDBConn("loan.properties");
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, customerId);
            try (ResultSet rs = pstmt.executeQuery()) { 
                if (rs.next()) {
                    String name = rs.getString("name");
                    String email = rs.getString("email");
                    String address = rs.getString("address");
                    String phone = rs.getString("phone");
                    int creditScore = rs.getInt("creditScore");
                    
                    return new Customer(customerId, name, email, address, phone, creditScore);
                }
            } 
        } catch (SQLException e) {
            System.err.println("ERROR: Failed to fetch customer data for ID " + customerId + ": " + e.getMessage());
            
        } catch (Exception e) {
            System.err.println("ERROR: Unexpected error fetching customer data: " + e.getMessage());
            e.printStackTrace();
        }
        
        return null;
    }
}