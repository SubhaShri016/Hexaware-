package com.java.loanmanagement.main;

import com.java.loanmanagement.Dao.ILoanRepository;
import com.java.loanmanagement.Dao.LoanRepositoryImpl;
import com.java.loanmanagement.Exception.InvalidLoanException;
import java.util.Scanner;

public class LoanRepayment {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ILoanRepository repo = new LoanRepositoryImpl();

        System.out.print("Enter Loan ID: ");
        int loanId = scanner.nextInt();

        System.out.print("Enter Repayment Amount: ");
        double amount = scanner.nextDouble();

        try {
            boolean success = repo.loanRepayment(loanId, amount);
            if (success) {
                System.out.println("✅ Loan Repayment Successful!");
            } else {
                System.out.println("❌ Loan Repayment Failed.");
            }
        } catch (InvalidLoanException e) {
            System.out.println(e.getMessage());
        }

        scanner.close();
    }
}
