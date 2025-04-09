package com.java.loanmanagement.Util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class DBPropertyUtil {
    public static String getDBConnectionString(String fileName) {
        Properties props = new Properties();
        try (InputStream inputStream = DBPropertyUtil.class.getClassLoader().getResourceAsStream(fileName)) { // Use getResourceAsStream
            if (inputStream == null) {
                System.err.println("Error: Property file '" + fileName + "' not found in classpath"); // Added error log
                throw new IOException("Property file '" + fileName + "' not found in classpath"); // More informative exception
            }
            props.load(inputStream);
            String connectionString = props.getProperty("loan.url") + "?user=" + props.getProperty("loan.user") + "&password=" + props.getProperty("loan.password");
            System.out.println("Loaded connection string: " + connectionString); // Added connection string log
            return connectionString;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}