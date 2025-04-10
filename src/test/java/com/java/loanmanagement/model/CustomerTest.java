package com.java.loanmanagement.model;

import static org.junit.Assert.*;
import org.junit.Test;

public class CustomerTest {

    @Test
    public void testGettersAndSetters() {
        Customer customer = new Customer();

        customer.setCustomerId(1);
        customer.setName("John Doe");
        customer.setEmail("john@example.com");
        customer.setPhone("123-456-7890");
        customer.setAddress("123 Main St");
        customer.setCreditScore(700);

        assertEquals(1, customer.getCustomerId());
        assertEquals("John Doe", customer.getName());
        assertEquals("john@example.com", customer.getEmail());
        assertEquals("123-456-7890", customer.getPhone());
        assertEquals("123 Main St", customer.getAddress());
        assertEquals(700, customer.getCreditScore());
    }

    @Test
    public void testConstructor() {
        Customer customer = new Customer(2, "Jane Smith", "jane@example.com", "987-654-3210", "456 Oak Ave", 750);

        assertEquals(2, customer.getCustomerId());
        assertEquals("Jane Smith", customer.getName());
        assertEquals("jane@example.com", customer.getEmail());
        assertEquals("987-654-3210", customer.getPhone());
        assertEquals("456 Oak Ave", customer.getAddress());
        assertEquals(750, customer.getCreditScore());
    }

    @Test
    public void testSetNullValues() {
        Customer customer = new Customer();

        customer.setName(null);
        customer.setEmail(null);
        customer.setPhone(null);
        customer.setAddress(null);

        assertNull(customer.getName());
        assertNull(customer.getEmail());
        assertNull(customer.getPhone());
        assertNull(customer.getAddress());
    }

    @Test
    public void testSetEmptyStringValues() {
        Customer customer = new Customer();

        customer.setName("");
        customer.setEmail("");
        customer.setPhone("");
        customer.setAddress("");

        assertEquals("", customer.getName());
        assertEquals("", customer.getEmail());
        assertEquals("", customer.getPhone());
        assertEquals("", customer.getAddress());
    }

    @Test
    public void testSetZeroAndNegativeCustomerId() {
        Customer customer = new Customer();

        customer.setCustomerId(0);
        assertEquals(0, customer.getCustomerId());

        customer.setCustomerId(-1);
        assertEquals(-1, customer.getCustomerId());
    }

    @Test
    public void testSetNegativeCreditScore() {
        Customer customer = new Customer();

        customer.setCreditScore(-100);
        assertEquals(-100, customer.getCreditScore());
    }
}