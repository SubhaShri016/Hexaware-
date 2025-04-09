package com.java.loanmanagement.main;

import com.java.loanmanagement.Dao.ILoanRepository;
import com.java.loanmanagement.Dao.LoanRepositoryImpl;
import com.java.loanmanagement.Exception.InvalidLoanException;
import java.util.Scanner;

public class CalculateInterest {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ILoanRepository repo = new LoanRepositoryImpl();

        System.out.print("Enter Loan ID: ");
        int loanId = scanner.nextInt();

        try {
            double interest = repo.calculateInterest(loanId);
            System.out.println("✅ Interest Amount: " + interest);
        } catch (InvalidLoanException e) {
            System.out.println(e.getMessage());
        }

        scanner.close();
    }
}
