package com.java.loanmanagement.main;

import com.java.loanmanagement.Dao.ILoanRepository;
import com.java.loanmanagement.Dao.LoanRepositoryImpl;
import com.java.loanmanagement.Exception.InvalidLoanException;
import java.util.Scanner;

public class LoanStatus {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ILoanRepository repo = new LoanRepositoryImpl();

        System.out.print("Enter Loan ID: ");
        int loanId = scanner.nextInt();

        try {
            String status = repo.loanStatus(loanId);
            System.out.println("✅ Loan Status: " + status);
        } catch (InvalidLoanException e) {
            System.out.println(e.getMessage());
        }

        scanner.close();
    }
}
