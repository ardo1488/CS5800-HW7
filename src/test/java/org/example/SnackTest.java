package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SnackTest {

    @Test
    void getName_ReturnsCurrentName_Test() {
        Snack s = new Snack("Coke", 1.5, 3);
        assertEquals("Coke", s.getName());
    }

    @Test
    void setName_UpdatesName_Test() {
        Snack s = new Snack("Coke", 1.5, 3);
        s.setName("Pepsi");
        assertEquals("Pepsi", s.getName());
    }

    @Test
    void getPrice_ReturnsCurrentPrice_Test() {
        Snack s = new Snack("Coke", 1.5, 3);
        assertEquals(1.5, s.getPrice(), 0.0001);
    }

    @Test
    void setPrice_UpdatesPrice_Test() {
        Snack s = new Snack("Coke", 1.5, 3);
        s.setPrice(2.0);
        assertEquals(2.0, s.getPrice(), 0.0001);
    }

    @Test
    void getQuantity_ReturnsCurrentQuantity_Test() {
        Snack s = new Snack("Coke", 1.5, 3);
        assertEquals(3, s.getQuantity());
    }

    @Test
    void setQuantity_UpdatesQuantity_Test() {
        Snack s = new Snack("Coke", 1.5, 3);
        s.setQuantity(10);
        assertEquals(10, s.getQuantity());
    }

    @Test
    void isAvailable_WhenQuantityPositive_ReturnsTrue_Test() {
        Snack s = new Snack("Coke", 1.5, 1);
        assertTrue(s.isAvailable());
    }

    @Test
    void isAvailable_WhenQuantityZero_ReturnsFalse_Test() {
        Snack s = new Snack("Coke", 1.5, 0);
        assertFalse(s.isAvailable());
    }

    @Test
    void dispense_DecrementsQuantity_Test() {
        Snack s = new Snack("Coke", 1.5, 2);
        s.dispense();
        assertEquals(1, s.getQuantity());
    }

    @Test
    void dispense_WhenQuantityZero_AllowsNegativeAndDecrements_Test() {
        Snack s = new Snack("Coke", 1.5, 0);
        s.dispense();
        assertEquals(-1, s.getQuantity()); // matches current implementation behavior
    }
}
