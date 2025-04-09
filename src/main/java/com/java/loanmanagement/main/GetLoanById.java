package com.java.loanmanagement.main;

import com.java.loanmanagement.Dao.ILoanRepository;
import com.java.loanmanagement.Dao.LoanRepositoryImpl;
import com.java.loanmanagement.model.Loan;
import com.java.loanmanagement.Exception.InvalidLoanException;
import java.util.Scanner;

public class GetLoanById {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ILoanRepository repo = new LoanRepositoryImpl();

        System.out.print("Enter Loan ID: ");
        int loanId = scanner.nextInt();

        try {
            Loan loan = repo.getLoanById(loanId);
            System.out.println("\n🔹 Loan Details");
            System.out.println("Loan ID: " + loan.getLoanId());
            System.out.println("Customer Name: " + loan.getCustomer());
            System.out.println("Loan Amount: " + loan.getPrincipalAmount());
            System.out.println("Loan Type: " + loan.getLoanType());
            System.out.println("Loan Status: " + loan.getLoanStatus());
            System.out.println("Interest Rate: " + loan.getInterestRate());
            System.out.println("Loan Term: " + loan.getLoanTerm());
            System.out.println("----------------");
        } catch (InvalidLoanException e) {
            System.out.println(e.getMessage());
        }

        scanner.close();
    }
}
