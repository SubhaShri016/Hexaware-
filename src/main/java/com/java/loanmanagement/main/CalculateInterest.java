package com.java.loanmanagement.main;

import com.java.loanmanagement.Dao.ILoanRepository;
import com.java.loanmanagement.Dao.LoanRepositoryImpl;
import com.java.loanmanagement.Exception.InvalidLoanException;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.NoSuchElementException; // Import NoSuchElementException

public class CalculateInterest {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ILoanRepository repo = new LoanRepositoryImpl();
        int loanId = 0; // Initialize loanId to 0 or a default invalid value

        try {
            System.out.print("Enter Loan ID: ");
            if (scanner.hasNextInt()) { // **Check if there is an integer input**
                loanId = scanner.nextInt();
                scanner.nextLine(); // Consume newline
            } else {
                System.out.println("❌ Invalid input. Please enter a valid Loan ID (number).");
                scanner.next(); // Consume invalid input token
                return; // Exit method if no valid input
            }


            double interest = repo.calculateInterest(loanId);
            System.out.println("✅ Interest Amount: " + interest);

        } catch (InputMismatchException e) {
            System.out.println("❌ Invalid input. Please enter a valid Loan ID (number).");
            scanner.next(); // Consume invalid input token (already there, but keep for consistency)
        } catch (InvalidLoanException e) {
            System.out.println("❌ " + e.getMessage());
        } catch (NoSuchElementException e) { // **Catch NoSuchElementException explicitly**
            System.out.println("❌ Invalid input. Please enter a valid Loan ID (number)."); // Treat no input as invalid
        }
        finally {
            scanner.close();
        }
    }
}