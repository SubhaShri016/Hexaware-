package com.java.loanmanagement.Exception;

public class InvalidLoanException extends RuntimeException {
    private static final long serialVersionUID = 1L; 

    public InvalidLoanException(String message) {
        super(message);
    }
}
