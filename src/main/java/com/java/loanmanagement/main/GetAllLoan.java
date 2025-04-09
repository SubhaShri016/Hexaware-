package com.java.loanmanagement.main;

import com.java.loanmanagement.Dao.ILoanRepository;
import com.java.loanmanagement.Dao.LoanRepositoryImpl;
import com.java.loanmanagement.model.Loan;
import java.util.List;

public class GetAllLoan {
    public static void main(String[] args) {
        ILoanRepository repo = new LoanRepositoryImpl();
        List<Loan> loans = repo.getAllLoans();

        if (loans.isEmpty()) {
            System.out.println("❌ No loans found.");
        } else {
            for (Loan loan : loans) {
                System.out.println("\n🔹 Loan ID: " + loan.getLoanId());
                System.out.println("Customer Name: " + loan.getCustomer());
                System.out.println("Loan Amount: " + loan.getPrincipalAmount());
                System.out.println("Loan Type: " + loan.getLoanType());
                System.out.println("Loan Status: " + loan.getLoanStatus());
                System.out.println("Interest Rate: " + loan.getInterestRate());
                System.out.println("Loan Term: " + loan.getLoanTerm());
                System.out.println("-----------");
            }
        }
    }
}
