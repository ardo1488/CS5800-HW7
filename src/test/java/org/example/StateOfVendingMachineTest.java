package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StateOfVendingMachineTest {

    private VendingMachine vm;

    @BeforeEach
    void setup() {
        vm = new VendingMachine();
        vm.addSnack(new Snack("Coke", 1.50, 2));
        vm.addSnack(new Snack("Snickers", 1.75, 0)); // out of stock
    }

    // IdleState.onSnackSelection

    @Test
    void onSnackSelection_AvailableMovesToWaiting_Test() {
        vm.getState().onSnackSelection("Coke");
        assertEquals("Coke", vm.getSelectedSnack());
        assertTrue(vm.getState() instanceof WaitingForMoneyState);
    }

    @Test
    void onSnackSelection_UnavailableStaysIdle_Test() {
        vm.getState().onSnackSelection("Snickers");
        assertTrue(vm.getState() instanceof IdleState);
        assertNull(vm.getSelectedSnack());
    }

    // IdleState.onMoneyInserted (no-op)

    @Test
    void onMoneyInserted_InIdle_NoStateChange_Test() {
        StateOfVendingMachine before = vm.getState();
        vm.getState().onMoneyInserted(1.00);
        assertSame(before, vm.getState());
    }

    @Test
    void onMoneyInserted_InIdle_NoMoneyStored_Test() {
        vm.getState().onMoneyInserted(2.00);
        assertEquals(0.0, vm.getInsertedMoney(), 1e-9);
    }

    // IdleState.onDispenseSnack (no-op)

    @Test
    void onDispenseSnack_InIdle_NoStateChange_Test() {
        StateOfVendingMachine before = vm.getState();
        vm.getState().onDispenseSnack();
        assertSame(before, vm.getState());
    }

    @Test
    void onDispenseSnack_InIdle_NoSelectionCleared_Test() {
        vm.setSelectedSnack("Coke");
        vm.getState().onDispenseSnack();
        assertEquals("Coke", vm.getSelectedSnack());
    }

    // WaitingForMoneyState.onSnackSelection (no-op)

    @Test
    void onSnackSelection_InWaiting_NoChangeToSelection_Test() {
        vm.setState(vm.getWaitingForMoneyState());
        vm.setSelectedSnack("Coke");
        vm.getState().onSnackSelection("Pepsi");
        assertEquals("Coke", vm.getSelectedSnack());
    }

    @Test
    void onSnackSelection_InWaiting_StateRemainsWaiting_Test() {
        vm.setState(vm.getWaitingForMoneyState());
        vm.getState().onSnackSelection("Coke");
        assertTrue(vm.getState() instanceof WaitingForMoneyState);
    }

    // WaitingForMoneyState.onMoneyInserted

    @Test
    void onMoneyInserted_InsufficientRemainsWaiting_Test() {
        vm.getState().onSnackSelection("Coke");
        vm.getState().onMoneyInserted(1.00);
        assertTrue(vm.getState() instanceof WaitingForMoneyState);
        assertEquals(1.00, vm.getInsertedMoney(), 1e-9);
    }

    @Test
    void onMoneyInserted_SufficientDispensesAndResets_Test() {
        vm.getState().onSnackSelection("Coke");
        vm.getState().onMoneyInserted(2.00);
        assertTrue(vm.getState() instanceof IdleState);
        assertNull(vm.getSelectedSnack());
        assertEquals(1, vm.getSnack("Coke").getQuantity());
        assertEquals(0.0, vm.getInsertedMoney(), 1e-9);
    }

    // WaitingForMoneyState.onDispenseSnack (no-op)

    @Test
    void onDispenseSnack_InWaiting_NoStateChange_Test() {
        vm.setState(vm.getWaitingForMoneyState());
        StateOfVendingMachine before = vm.getState();
        vm.getState().onDispenseSnack();
        assertSame(before, vm.getState());
    }

    @Test
    void onDispenseSnack_InWaiting_NoMoneyReset_Test() {
        vm.setState(vm.getWaitingForMoneyState());
        vm.setInsertedMoney(1.00);
        vm.getState().onDispenseSnack();
        assertEquals(1.00, vm.getInsertedMoney(), 1e-9);
    }

    // DispensingSnackState.onSnackSelection (no-op)

    @Test
    void onSnackSelection_InDispensing_NoStateChange_Test() {
        vm.setState(vm.getDispensingSnackState());
        StateOfVendingMachine before = vm.getState();
        vm.getState().onSnackSelection("Coke");
        assertSame(before, vm.getState());
    }

    @Test
    void onSnackSelection_InDispensing_NoSelectionChange_Test() {
        vm.setState(vm.getDispensingSnackState());
        vm.setSelectedSnack("Coke");
        vm.getState().onSnackSelection("Pepsi");
        assertEquals("Coke", vm.getSelectedSnack());
    }

    // DispensingSnackState.onMoneyInserted (no-op)

    @Test
    void onMoneyInserted_InDispensing_NoMoneyChange_Test() {
        vm.setState(vm.getDispensingSnackState());
        vm.setInsertedMoney(1.00);
        vm.getState().onMoneyInserted(0.50);
        assertEquals(1.00, vm.getInsertedMoney(), 1e-9);
    }

    @Test
    void onMoneyInserted_InDispensing_StateUnchanged_Test() {
        vm.setState(vm.getDispensingSnackState());
        StateOfVendingMachine before = vm.getState();
        vm.getState().onMoneyInserted(1.00);
        assertSame(before, vm.getState());
    }

    // DispensingSnackState.onDispenseSnack

    @Test
    void onDispenseSnack_SuccessResetsToIdle_Test() {
        vm.getState().onSnackSelection("Coke");
        vm.getState().onMoneyInserted(2.00); // triggers dispense and returns to idle
        assertTrue(vm.getState() instanceof IdleState);
    }

    @Test
    void onDispenseSnack_FailureResetsAndClears_Test() {
        vm.getState().onSnackSelection("Snickers");
        vm.getState().onMoneyInserted(5.00); // out of stock -> failure branch
        assertTrue(vm.getState() instanceof IdleState);
        assertNull(vm.getSelectedSnack());
        assertEquals(0.0, vm.getInsertedMoney(), 1e-9);
    }
}
