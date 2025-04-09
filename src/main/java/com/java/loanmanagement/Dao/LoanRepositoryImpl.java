package com.java.loanmanagement.Dao;

import com.java.loanmanagement.Exception.InvalidLoanException;
import com.java.loanmanagement.Util.DBConnUtil;
import com.java.loanmanagement.model.Customer;
import com.java.loanmanagement.model.Loan;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.sql.Statement;

public class LoanRepositoryImpl implements ILoanRepository {

    public int getNextLoanId() throws SQLException {
        String query = "SELECT MAX(loanId) FROM Loan";
        int nextId = 1; 
        try (Connection conn = DBConnUtil.getDBConn("loan.properties");
             Statement stmt = conn.createStatement(); 
             ResultSet rs = stmt.executeQuery(query)) {

            if (rs.next()) {
                int maxId = rs.getInt(1);
                
                if (!rs.wasNull()) {
                    nextId = maxId + 1;
                }
            }
        } 
        System.out.println("DEBUG: Calculated next Loan ID: " + nextId); // For debugging purposes
        return nextId;
    }

    @Override
    public boolean applyLoan(Loan loan) {
        
        String query = "INSERT INTO Loan (loanId, customerId, principalAmount, loanType, loanStatus, interestRate, loanTerm) VALUES (?, ?, ?, ?, ?, ?, ?)";

       
        if (loan.getLoanId() <= 0) {
            System.err.println("ERROR: Loan ID was not generated or is invalid before calling applyLoan. Loan ID provided: " + loan.getLoanId());
            return false; // Prevent insertion with invalid ID
        }

        try (Connection conn = DBConnUtil.getDBConn("loan.properties");
             PreparedStatement pstmt = conn.prepareStatement(query)) { // No RETURN_GENERATED_KEYS needed

            
            pstmt.setInt(1, loan.getLoanId());
            pstmt.setInt(2, loan.getCustomer().getCustomerId());
            pstmt.setDouble(3, loan.getPrincipalAmount());
            pstmt.setString(4, loan.getLoanType());
            pstmt.setString(5, loan.getLoanStatus());
            pstmt.setDouble(6, loan.getInterestRate());
            pstmt.setInt(7, loan.getLoanTerm());

            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            
            if (e.getErrorCode() == 1062) { 
                System.err.println("ERROR: Failed to insert loan. Duplicate Loan ID (" + loan.getLoanId() + ") detected. This might indicate a race condition if multiple users are applying simultaneously.");
            } else {
                
                System.err.println("ERROR: SQL Exception occurred while applying loan: " + e.getMessage());
                System.err.println("SQLState: " + e.getSQLState());
                System.err.println("Error Code: " + e.getErrorCode());
            }
            
        } catch (Exception e) {
           
            System.err.println("ERROR: An unexpected error occurred during applyLoan: " + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public double calculateInterest(int loanId) throws InvalidLoanException {
        Loan loan = getLoanById(loanId);
        return calculateInterest(loan.getPrincipalAmount(), loan.getInterestRate(), loan.getLoanTerm());
    }

    @Override
    public double calculateInterest(double principal, double rate, int months) {
        return (principal * rate * months) / 100;
    }

    @Override
    public String loanStatus(int loanId) throws InvalidLoanException {
        String query = "SELECT loanStatus FROM Loan WHERE loanId = ?";
        try (Connection conn = DBConnUtil.getDBConn("loan.properties");
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, loanId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getString("loanStatus");
            } else {
                throw new InvalidLoanException("Loan ID not found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public double calculateEMI(int loanId) throws InvalidLoanException {
        Loan loan = getLoanById(loanId);
        return calculateEMI(loan.getPrincipalAmount(), loan.getInterestRate(), loan.getLoanTerm());
    }

    @Override
    public double calculateEMI(double principal, double rate, int months) {
        double monthlyRate = rate / (12 * 100);
        return (principal * monthlyRate * Math.pow(1 + monthlyRate, months)) /
                (Math.pow(1 + monthlyRate, months) - 1);
    }

    @Override
    public boolean loanRepayment(int loanId, double amount) throws InvalidLoanException {
        String query = "UPDATE Loan SET principalAmount = principalAmount - ? WHERE loanId = ?";
        try (Connection conn = DBConnUtil.getDBConn("loan.properties");
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setDouble(1, amount);
            pstmt.setInt(2, loanId);
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<Loan> getAllLoans() {
        List<Loan> loans = new ArrayList<>();
        String query = "SELECT * FROM Loan";
        try (Connection conn = DBConnUtil.getDBConn("loan.properties");
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                Loan loan = new Loan();
                loan.setLoanId(rs.getInt("loanId"));
                loan.setPrincipalAmount(rs.getDouble("principalAmount"));
                loan.setLoanType(rs.getString("loanType"));
                loan.setLoanStatus(rs.getString("loanStatus"));
                loan.setInterestRate(rs.getDouble("interestRate"));
                loan.setLoanTerm(rs.getInt("loanTerm"));
                loans.add(loan);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return loans;
    }

    @Override
    public Loan getLoanById(int loanId) throws InvalidLoanException {
        String query = "SELECT * FROM Loan l JOIN Customer c ON l.customerId = c.customerId WHERE loanId = ?";
        try (Connection conn = DBConnUtil.getDBConn("loan.properties");
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, loanId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                Loan loan = new Loan();
                loan.setLoanId(rs.getInt("loanId"));
                loan.setPrincipalAmount(rs.getDouble("principalAmount"));
                loan.setLoanType(rs.getString("loanType"));
                loan.setLoanStatus(rs.getString("loanStatus"));
                loan.setInterestRate(rs.getDouble("interestRate"));
                loan.setLoanTerm(rs.getInt("loanTerm"));

                Customer customer = new Customer();
                customer.setCustomerId(rs.getInt("customerId"));
                customer.setName(rs.getString("name"));
                loan.setCustomer(customer);

                return loan;
            } else {
                throw new InvalidLoanException("Loan ID not found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
