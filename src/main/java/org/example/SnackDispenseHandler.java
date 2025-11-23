package org.example;

public abstract class SnackDispenseHandler {
    protected SnackDispenseHandler nextHandler;

    public SnackDispenseHandler setNext(SnackDispenseHandler handler) {
        this.nextHandler = handler;
        return handler;
    }

    public abstract boolean handle(String snackName, VendingMachine vendingMachine);
}

class CokeHandler extends SnackDispenseHandler {
    @Override
    public boolean handle(String snackName, VendingMachine vendingMachine) {
        if(snackName.equalsIgnoreCase("Coke")) {
            Snack snack = vendingMachine.getSnack("Coke");
            if(snack.isAvailable() && vendingMachine.getInsertedMoney() >= snack.getPrice()){
                snack.dispense();
                vendingMachine.setInsertedMoney(0);
                return true;
            }
            return false;
        }
        else if(nextHandler != null) {
            return nextHandler.handle(snackName, vendingMachine);
        }
        return false;
    }
}


class PepsiHandler extends SnackDispenseHandler {
    @Override
    public boolean handle(String snackName, VendingMachine vendingMachine) {
        if(snackName.equalsIgnoreCase("Pepsi")) {
            Snack snack = vendingMachine.getSnack("Pepsi");
            if(snack.isAvailable() && vendingMachine.getInsertedMoney() >= snack.getPrice()){
                snack.dispense();
                vendingMachine.setInsertedMoney(0);
                return true;
            }
            return false;
        }
        else if(nextHandler != null) {
            return nextHandler.handle(snackName, vendingMachine);
        }
        return false;
    }
}


class CheetosHandler extends SnackDispenseHandler {
    @Override
    public boolean handle(String snackName, VendingMachine vendingMachine) {
        if(snackName.equalsIgnoreCase("Cheetos")) {
            Snack snack = vendingMachine.getSnack("Cheetos");
            if(snack.isAvailable() && vendingMachine.getInsertedMoney() >= snack.getPrice()){
                snack.dispense();
                vendingMachine.setInsertedMoney(0);
                return true;
            }
            return false;
        }
        else if(nextHandler != null) {
            return nextHandler.handle(snackName, vendingMachine);
        }
        return false;
    }
}

class DoritosHandler extends SnackDispenseHandler {
    @Override
    public boolean handle(String snackName, VendingMachine vendingMachine) {
        if(snackName.equalsIgnoreCase("Doritos")) {
            Snack snack = vendingMachine.getSnack("Doritos");
            if(snack.isAvailable() && vendingMachine.getInsertedMoney() >= snack.getPrice()){
                snack.dispense();
                vendingMachine.setInsertedMoney(0);
                return true;
            }
            return false;
        }
        else if(nextHandler != null) {
            return nextHandler.handle(snackName, vendingMachine);
        }
        return false;
    }
}

class KitKatHandler extends SnackDispenseHandler {
    @Override
    public boolean handle(String snackName, VendingMachine vendingMachine) {
        if(snackName.equalsIgnoreCase("KitKat")) {
            Snack snack = vendingMachine.getSnack("KitKat");
            if(snack.isAvailable() && vendingMachine.getInsertedMoney() >= snack.getPrice()){
                snack.dispense();
                vendingMachine.setInsertedMoney(0);
                return true;
            }
            return false;
        }
        else if(nextHandler != null) {
            return nextHandler.handle(snackName, vendingMachine);
        }
        return false;
    }
}

class SnickersHandler extends SnackDispenseHandler {
    @Override
    public boolean handle(String snackName, VendingMachine vendingMachine) {
        if(snackName.equalsIgnoreCase("Snickers")) {
            Snack snack = vendingMachine.getSnack("Snickers");
            if(snack.isAvailable() && vendingMachine.getInsertedMoney() >= snack.getPrice()){
                snack.dispense();
                vendingMachine.setInsertedMoney(0);
                return true;
            }
            return false;
        }
        else if(nextHandler != null) {
            return nextHandler.handle(snackName, vendingMachine);
        }
        return false;
    }
}
