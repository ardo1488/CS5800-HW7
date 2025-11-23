package org.example;

public class Driver {
    public static void main(String[] args) {

        VendingMachine vendingMachine = new VendingMachine();

        vendingMachine.addSnack(new Snack("Coke", 1.50, 5));
        vendingMachine.addSnack(new Snack("Pepsi", 1.50, 3));
        vendingMachine.addSnack(new Snack("Cheetos", 2.00, 4));
        vendingMachine.addSnack(new Snack("Doritos", 2.00, 2));
        vendingMachine.addSnack(new Snack("KitKat", 1.25, 6));
        vendingMachine.addSnack(new Snack("Snickers", 1.75, 0));

        System.out.println("TEST 1: Buying Coke with exact money");
        System.out.println("-".repeat(50));
        vendingMachine.getState().onSnackSelection("Coke");
        vendingMachine.getState().onMoneyInserted(1.50);
        System.out.println();

        System.out.println("TEST 2: Buying Cheetos with extra money");
        System.out.println("-".repeat(50));
        vendingMachine.getState().onSnackSelection("Cheetos");
        vendingMachine.getState().onMoneyInserted(1.00);
        vendingMachine.getState().onMoneyInserted(1.50);
        System.out.println();

        System.out.println("TEST 3: Trying to buy out-of-stock Snickers");
        System.out.println("-".repeat(50));
        vendingMachine.getState().onSnackSelection("Snickers");
        vendingMachine.getState().onMoneyInserted(2.00);
        System.out.println();

        System.out.println("TEST 4: Buying KitKat with multiple insertions");
        System.out.println("-".repeat(50));
        vendingMachine.getState().onSnackSelection("KitKat");
        vendingMachine.getState().onMoneyInserted(0.25);
        vendingMachine.getState().onMoneyInserted(0.50);
        vendingMachine.getState().onMoneyInserted(0.50);
        System.out.println();

        System.out.println("TEST 5: Trying to buy non-existent item");
        System.out.println("-".repeat(50));
        vendingMachine.getState().onSnackSelection("Sprite");
        System.out.println();

    }
}
