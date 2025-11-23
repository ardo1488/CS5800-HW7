package org.example;

import java.util.Map;
import java.util.HashMap;

public class VendingMachine {
    private  Map<String, Snack> snacks;
    private double insertedMoney;
    private String selectedSnack;
    private StateOfVendingMachine state;

    private StateOfVendingMachine idleState;
    private StateOfVendingMachine waitingForMoneyState;
    private StateOfVendingMachine dispensingSnackState;

    private SnackDispenseHandler snackDispenser;


    public VendingMachine() {
        this.snacks = new HashMap<>();
        this.insertedMoney = 0;
        this.selectedSnack = null;

        this.idleState = new IdleState(this);
        this.waitingForMoneyState  = new WaitingForMoneyState(this);
        this.dispensingSnackState = new DispensingSnackState(this);

        this.state = idleState;

        this.snackDispenser = new CokeHandler();
        this.snackDispenser.setNext(new PepsiHandler())
                .setNext(new CheetosHandler())
                .setNext(new DoritosHandler())
                .setNext(new KitKatHandler())
                .setNext(new SnickersHandler());
    }

    public void addSnack(Snack snack) {
        snacks.put(snack.getName(), snack);
    }

    public Snack getSnack(String snackName) {
        return snacks.get(snackName);
    }

    public void setState(StateOfVendingMachine state) {
        this.state = state;
    }

    public Snack selectSnack(String snackName) {
        return snacks.get(snackName);
    }

    public void setInsertedMoney(double insertedMoney) {
        this.insertedMoney = insertedMoney;
    }

    public double getInsertedMoney() {
        return insertedMoney;
    }

    public void setSelectedSnack(String selectedSnack) {
        this.selectedSnack = selectedSnack;
    }

    public String getSelectedSnack() {
        return selectedSnack;
    }

    public SnackDispenseHandler getSnackDispenser() {
        return snackDispenser;
    }

    public StateOfVendingMachine getState() { return state; }

    public StateOfVendingMachine getIdleState() { return idleState; }

    public StateOfVendingMachine getWaitingForMoneyState() { return waitingForMoneyState; }

    public StateOfVendingMachine getDispensingSnackState() { return dispensingSnackState; }
}
