DROP DATABASE IF EXISTS LoanManagementDB;

CREATE DATABASE LoanManagementDB;

USE LoanManagementDB;

DROP TABLE IF EXISTS Customer;
CREATE TABLE Customer (
    customerId INT PRIMARY KEY,
    name VARCHAR(100),
    email VARCHAR(100),
    phone VARCHAR(15),
    address VARCHAR(200),
    creditScore INT
);

DROP TABLE IF EXISTS Loan;
CREATE TABLE Loan (
    loanId INT PRIMARY KEY,
    customerId INT,
    principalAmount DOUBLE,
    interestRate DOUBLE,
    loanTerm INT,
    loanType VARCHAR(50),
    loanStatus VARCHAR(50),
    FOREIGN KEY (customerId) REFERENCES Customer(customerId)
);

DROP TABLE IF EXISTS HomeLoan;
CREATE TABLE HomeLoan (
    loanId INT PRIMARY KEY,
    propertyAddress VARCHAR(200),
    propertyValue INT,
    FOREIGN KEY (loanId) REFERENCES Loan(loanId)
);

DROP TABLE IF EXISTS CarLoan;
CREATE TABLE CarLoan (
    loanId INT PRIMARY KEY,
    carModel VARCHAR(100),
    carValue INT,
    FOREIGN KEY (loanId) REFERENCES Loan(loanId)
);

INSERT INTO Customer VALUES
(1, 'John Doe', 'john@example.com', '1234567890', 'New York', 750),
(2, 'Alice Smith', 'alice@example.com', '9876543210', 'Los Angeles', 710),
(3, 'Bob Johnson', 'bob@example.com', '4567891230', 'Chicago', 680),
(4, 'Mary Jane', 'mary@example.com', '3216549870', 'Houston', 790),
(5, 'David Brown', 'david@example.com', '9517538420', 'Phoenix', 720),
(6, 'Emma Wilson', 'emma@example.com', '7894561230', 'San Antonio', 705),
(7, 'Olivia Taylor', 'olivia@example.com', '6549873210', 'San Diego', 730),
(8, 'Liam Martin', 'liam@example.com', '8529637410', 'Dallas', 765),
(9, 'Noah Lee', 'noah@example.com', '3692581470', 'San Jose', 780),
(10, 'Sophia White', 'sophia@example.com', '1478523690', 'Austin', 700);

INSERT INTO Loan VALUES
(101, 1, 500000, 7.5, 240, 'HomeLoan', 'Approved'),
(102, 2, 300000, 8.2, 180, 'CarLoan', 'Pending'),
(103, 3, 400000, 7.0, 120, 'HomeLoan', 'Approved'),
(104, 4, 250000, 9.0, 60, 'CarLoan', 'Approved'),
(105, 5, 600000, 6.8, 300, 'HomeLoan', 'Pending'),
(106, 6, 320000, 7.9, 72, 'CarLoan', 'Approved'),
(107, 7, 450000, 6.5, 180, 'HomeLoan', 'Approved'),
(108, 8, 280000, 8.0, 84, 'CarLoan', 'Rejected'),
(109, 9, 550000, 7.2, 240, 'HomeLoan', 'Pending'),
(110, 10, 310000, 7.7, 96, 'CarLoan', 'Approved');

INSERT INTO HomeLoan VALUES
(101, '123 Main St, New York', 800000),
(103, '456 Oak Ave, Chicago', 600000),
(105, '789 Pine Rd, Phoenix', 900000),
(107, '321 Elm St, San Diego', 750000),
(109, '654 Maple Blvd, San Jose', 850000);

INSERT INTO CarLoan VALUES
(102, 'Toyota Corolla', 250000),
(104, 'Honda Civic', 220000),
(106, 'Ford Focus', 230000),
(108, 'Hyundai Elantra', 210000),
(110, 'Chevrolet Malibu', 240000);

select * from Customer;
select * from Loan;
select * from HomeLoan;
select * from CarLoan;

DESCRIBE loan;
ALTER TABLE loan
DROP PRIMARY KEY;
