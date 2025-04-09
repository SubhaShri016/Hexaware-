package com.java.loanmanagement.Util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DBConnUtil {
    public static Connection getDBConn(String propertiesFile) {
        String connString = DBPropertyUtil.getDBConnectionString(propertiesFile);
        try {
            return DriverManager.getConnection(connString);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
