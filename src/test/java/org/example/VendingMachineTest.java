package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class VendingMachineTest {

    @Test
    void addSnack_AddsSnackToMap_Test() {
        VendingMachine vm = new VendingMachine();
        Snack s = new Snack("Coke", 1.50, 3);
        vm.addSnack(s);
        assertSame(s, vm.getSnack("Coke"));
    }

    @Test
    void addSnack_OverwritesSameNameSnack_Test() {
        VendingMachine vm = new VendingMachine();
        Snack a = new Snack("Coke", 1.50, 3);
        Snack b = new Snack("Coke", 2.00, 1);
        vm.addSnack(a);
        vm.addSnack(b);
        assertSame(b, vm.getSnack("Coke"));
    }

    @Test
    void getSnack_ReturnsExistingSnack_Test() {
        VendingMachine vm = new VendingMachine();
        Snack s = new Snack("Pepsi", 1.50, 2);
        vm.addSnack(s);
        assertEquals("Pepsi", vm.getSnack("Pepsi").getName());
    }

    @Test
    void getSnack_WhenMissingReturnsNull_Test() {
        VendingMachine vm = new VendingMachine();
        assertNull(vm.getSnack("Unknown"));
    }

    @Test
    void setState_ChangesCurrentState_Test() {
        VendingMachine vm = new VendingMachine();
        vm.setState(vm.getWaitingForMoneyState());
        assertSame(vm.getWaitingForMoneyState(), vm.getState());
    }

    @Test
    void setState_CanReturnToIdleState_Test() {
        VendingMachine vm = new VendingMachine();
        vm.setState(vm.getWaitingForMoneyState());
        vm.setState(vm.getIdleState());
        assertSame(vm.getIdleState(), vm.getState());
    }

    @Test
    void selectSnack_ReturnsSnack_Test() {
        VendingMachine vm = new VendingMachine();
        Snack s = new Snack("Cheetos", 2.00, 4);
        vm.addSnack(s);
        assertSame(s, vm.selectSnack("Cheetos"));
    }

    @Test
    void selectSnack_WhenUnknownReturnsNull_Test() {
        VendingMachine vm = new VendingMachine();
        assertNull(vm.selectSnack("Random"));
    }

    @Test
    void setInsertedMoney_AssignsValue_Test() {
        VendingMachine vm = new VendingMachine();
        vm.setInsertedMoney(1.25);
        assertEquals(1.25, vm.getInsertedMoney(), 1e-9);
    }

    @Test
    void setInsertedMoney_OverwritesPreviousValue_Test() {
        VendingMachine vm = new VendingMachine();
        vm.setInsertedMoney(2.00);
        vm.setInsertedMoney(0.50);
        assertEquals(0.50, vm.getInsertedMoney(), 1e-9);
    }

    @Test
    void getInsertedMoney_ReflectsAssignedValue_Test() {
        VendingMachine vm = new VendingMachine();
        vm.setInsertedMoney(3.00);
        assertEquals(3.00, vm.getInsertedMoney(), 1e-9);
    }

    @Test
    void getInsertedMoney_DefaultsToZero_Test() {
        VendingMachine vm = new VendingMachine();
        assertEquals(0.0, vm.getInsertedMoney(), 1e-9);
    }

    @Test
    void setSelectedSnack_AssignsSelection_Test() {
        VendingMachine vm = new VendingMachine();
        vm.setSelectedSnack("Doritos");
        assertEquals("Doritos", vm.getSelectedSnack());
    }

    @Test
    void setSelectedSnack_CanClearSelectionWithNull_Test() {
        VendingMachine vm = new VendingMachine();
        vm.setSelectedSnack("KitKat");
        vm.setSelectedSnack(null);
        assertNull(vm.getSelectedSnack());
    }

    @Test
    void getSelectedSnack_ReturnsSelection_Test() {
        VendingMachine vm = new VendingMachine();
        vm.setSelectedSnack("KitKat");
        assertEquals("KitKat", vm.getSelectedSnack());
    }

    @Test
    void getSelectedSnack_DefaultsToNull_Test() {
        VendingMachine vm = new VendingMachine();
        assertNull(vm.getSelectedSnack());
    }

    @Test
    void getSnackDispenser_ReturnsNonNullChain_Test() {
        VendingMachine vm = new VendingMachine();
        assertNotNull(vm.getSnackDispenser());
    }

    @Test
    void getSnackDispenser_CanHandleUnknownByReturningFalse_Test() {
        VendingMachine vm = new VendingMachine();
        assertFalse(vm.getSnackDispenser().handle("Unknown", vm));
    }

    @Test
    void getState_DefaultsToIdle_Test() {
        VendingMachine vm = new VendingMachine();
        assertSame(vm.getIdleState(), vm.getState());
    }

    @Test
    void getIdleState_ReturnsInstanceOfIdleState_Test() {
        VendingMachine vm = new VendingMachine();
        assertTrue(vm.getIdleState() instanceof IdleState);
    }

    @Test
    void getWaitingForMoneyState_ReturnsInstanceOfWaiting_Test() {
        VendingMachine vm = new VendingMachine();
        assertTrue(vm.getWaitingForMoneyState() instanceof WaitingForMoneyState);
    }

    @Test
    void getDispensingSnackState_ReturnsInstanceOfDispensing_Test() {
        VendingMachine vm = new VendingMachine();
        assertTrue(vm.getDispensingSnackState() instanceof DispensingSnackState);
    }
}
