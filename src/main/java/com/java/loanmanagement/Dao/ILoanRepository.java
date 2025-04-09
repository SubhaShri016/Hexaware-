package com.java.loanmanagement.Dao;

import com.java.loanmanagement.model.Loan;
import com.java.loanmanagement.Exception.InvalidLoanException;
import java.util.List;

public interface ILoanRepository {
    boolean applyLoan(Loan loan);                    
    double calculateInterest(int loanId) throws InvalidLoanException;  
    double calculateInterest(double principal, double rate, int tenure); 
    String loanStatus(int loanId);                    
    double calculateEMI(int loanId) throws InvalidLoanException;  
    double calculateEMI(double principal, double rate, int tenure);  
    boolean loanRepayment(int loanId, double amount); 
    List<Loan> getAllLoans();                       
    Loan getLoanById(int loanId) throws InvalidLoanException;  
}
