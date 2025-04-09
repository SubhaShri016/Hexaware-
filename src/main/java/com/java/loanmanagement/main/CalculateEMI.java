package com.java.loanmanagement.main;

import com.java.loanmanagement.Dao.ILoanRepository;
import com.java.loanmanagement.Dao.LoanRepositoryImpl;
import com.java.loanmanagement.Exception.InvalidLoanException;
import java.util.Scanner;

public class CalculateEMI {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ILoanRepository repo = new LoanRepositoryImpl();

        System.out.print("Enter Loan ID: ");
        int loanId = scanner.nextInt();

        try {
            double emi = repo.calculateEMI(loanId);
            System.out.println("✅ EMI for Loan ID " + loanId + " is: " + emi);
        } catch (InvalidLoanException e) {
            System.out.println(e.getMessage());
        }

        scanner.close();
    }
}
