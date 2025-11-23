package org.example;

public abstract class StateOfVendingMachine {
    protected VendingMachine machine;

    public StateOfVendingMachine(VendingMachine machine) {
        this.machine = machine;
    }

    public abstract void onSnackSelection(String snackName);
    public abstract void onMoneyInserted(double amountPaid);
    public abstract void onDispenseSnack();
}


class IdleState extends StateOfVendingMachine {

    public IdleState(VendingMachine machine) {
        super(machine);
    }

    @Override
    public void onSnackSelection(String snackName) {
        Snack snack = machine.getSnack(snackName);
        if(snack != null){
            if(snack.isAvailable()){
                machine.setSelectedSnack(snackName);
                machine.setState(machine.getWaitingForMoneyState());
                System.out.printf("Selected: " + snack.getName() + "  |  Price: $%.2f%n", snack.getPrice());
            }
            else{
                System.out.println(snack.getName() + " is out of stock.");
                System.out.println("Please try again.");
            }
        }
        else{
            System.out.println(snackName + " is not available");
        }
    }

    @Override
    public void onMoneyInserted(double amountPaid) {

    }

    @Override
    public void onDispenseSnack() {

    }
}


class WaitingForMoneyState extends StateOfVendingMachine {

    public WaitingForMoneyState(VendingMachine machine) {
        super(machine);
    }

    @Override
    public void onSnackSelection(String snackName) {


    }

    @Override
    public void onMoneyInserted(double amountPaid) {
        machine.setInsertedMoney(machine.getInsertedMoney() + amountPaid);
        Snack snack = machine.getSnack(machine.getSelectedSnack());
        System.out.printf("Inserted: $%.2f %n", amountPaid);

        if(machine.getInsertedMoney() >= snack.getPrice()){
            System.out.println("Sufficient funds!");
            machine.setState(machine.getDispensingSnackState());
            machine.getState().onDispenseSnack();
        }
        else{
            System.out.println("Insufficient funds!");
            System.out.printf(snack.getName() + " price: $%.2f%n", snack.getPrice());
            System.out.printf("Need $%.2f more%n", snack.getPrice() - machine.getInsertedMoney());
        }
    }

    @Override
    public void onDispenseSnack() {

    }
}


class DispensingSnackState extends StateOfVendingMachine {

    public DispensingSnackState(VendingMachine machine) {
        super(machine);
    }

    @Override
    public void onSnackSelection(String snackName) {
    }

    @Override
    public void onMoneyInserted(double amountPaid) {

    }

    @Override
    public void onDispenseSnack() {
        boolean vendingSuccess = machine.getSnackDispenser().handle(
                machine.getSelectedSnack(),
                machine
        );

        if(vendingSuccess){
            machine.setSelectedSnack(null);
            machine.setState(machine.getIdleState());
        }
        else{
            System.out.println("Unable to dispense. Returning money.");
            machine.setInsertedMoney(0);
            machine.setSelectedSnack(null);
            machine.setState(machine.getIdleState());
        }
    }
}