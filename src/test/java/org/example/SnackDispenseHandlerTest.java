package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SnackDispenseHandlerTest {

    private VendingMachine vm;

    @BeforeEach
    void setup() {
        vm = new VendingMachine();
        vm.addSnack(new Snack("Coke", 1.50, 2));
        vm.addSnack(new Snack("Pepsi", 1.50, 1));
        vm.addSnack(new Snack("Snickers", 1.75, 0)); // out of stock
    }

    @Test
    void setNext_ChainsToNextHandler_Test() {
        SnackDispenseHandler a = new CokeHandler();
        SnackDispenseHandler b = new PepsiHandler();
        a.setNext(b);
        vm.setInsertedMoney(2.00);
        boolean handled = a.handle("Pepsi", vm); // should delegate to PepsiHandler
        assertTrue(handled);
    }

    @Test
    void setNext_ReturnsTheNextHandler_Test() {
        SnackDispenseHandler a = new CokeHandler();
        SnackDispenseHandler b = new PepsiHandler();
        assertSame(b, a.setNext(b));
    }

    @Test
    void handle_MatchingNameAndFunds_Dispenses_Test() {
        vm.setInsertedMoney(2.00);
        boolean ok = vm.getSnackDispenser().handle("Coke", vm);
        assertTrue(ok);
        assertEquals(1, vm.getSnack("Coke").getQuantity());
        assertEquals(0.0, vm.getInsertedMoney(), 1e-9);
    }

    @Test
    void handle_InsufficientFunds_ReturnsFalse_Test() {
        vm.setInsertedMoney(1.00);
        boolean ok = vm.getSnackDispenser().handle("Coke", vm);
        assertFalse(ok);
        assertEquals(2, vm.getSnack("Coke").getQuantity());
        assertEquals(1.00, vm.getInsertedMoney(), 1e-9);
    }

    @Test
    void handle_DelegatesAcrossChain_SuccessOnLaterHandler_Test() {
        vm.setInsertedMoney(2.00);
        boolean ok = vm.getSnackDispenser().handle("Pepsi", vm); // Coke -> Pepsi
        assertTrue(ok);
        assertEquals(0, vm.getSnack("Pepsi").getQuantity());
        assertEquals(0.0, vm.getInsertedMoney(), 1e-9);
    }

    @Test
    void handle_OutOfStock_ReturnsFalse_Test() {
        vm.setInsertedMoney(5.00);
        boolean ok = vm.getSnackDispenser().handle("Snickers", vm);
        assertFalse(ok);
        assertEquals(0, vm.getSnack("Snickers").getQuantity());
        assertEquals(5.00, vm.getInsertedMoney(), 1e-9);
    }
}
