package org.falcon.model.deprecated;

import java.util.InputMismatchException;
import java.util.Scanner;
public class Utilities extends Wallet {
    static Scanner input = new Scanner(System.in);
    public static String betweenNumsPrompt(int[] range) {
        return "Please choose an integer between " + range[0] + " and "
                + range[1] + ".";
    }
    public static int makeChoiceWithinRange(int[] range) {
        int choice = 0;
        boolean inRange = false;
        while (!inRange) {
            try {
                choice = input.nextInt();
            } catch (InputMismatchException e) {
                System.err.println(e);
                System.out.println(betweenNumsPrompt(range));
            }
            if (range[1] == 2) {
                inRange = switch (choice) {
                    case 1, 2 -> true;
                    default -> false;
                };
            } else if (range[1] == 4) {
                inRange = switch (choice) {
                    case 1, 2, 3, 4 -> true;
                    default -> false;
                };
            } else {
                inRange = switch (choice) {
                    case 1, 2, 3, 4, 5, 6 -> true;
                    default -> false;
                };
            }
            if (!inRange) {
                System.out.println(Utilities.betweenNumsPrompt(range));
            }
        }
        return choice;
    }
    public static String overdraftPrompt() {
        return "Card declined. Please deposit money or use a different currency.";
    }
    public static double amountChoice() {
        while (true) {
            try {
                return input.nextDouble();
            } catch (InputMismatchException e) {
                System.err.println(e);
                System.out.println("Please input a number - decimal or otherwise.");
            }
        }
    }
    public static double tableAmount(int to, double amount) {
        return switch (to) {
            case 1 -> amount * CurrencyValueWebAPI.CurrencyValue.DOLLAR.getValue();
            case 2 -> amount * CurrencyValueWebAPI.CurrencyValue.EURO.getValue();
            case 3 -> amount * CurrencyValueWebAPI.CurrencyValue.FRANC.getValue();
            case 4 -> amount * CurrencyValueWebAPI.CurrencyValue.PESO.getValue();
            case 5 -> amount * CurrencyValueWebAPI.CurrencyValue.POUND.getValue();
            case 6 -> amount * CurrencyValueWebAPI.CurrencyValue.RUBLE.getValue();
            default -> throw new IllegalStateException("Unexpected value: " + to);
        };
    }
    public static String convertPrompt(int from, int to, double amount) {
        String phrase = String.format("You converted %.2f %s to %.2f %s.", amount, getCurrencyName(from), tableAmount(to, amount), getCurrencyName(to));
        return phrase;
    }
    public static String getCurrencyName(int choice) {
       return switch(choice) {
            case 1 -> "US Dollars";
            case 2 -> "Euros";
            case 3 -> "Francs";
            case 4 -> "Pesos";
            case 5 -> "UK Pounds";
            case 6 -> "Rubles";
           default -> throw new IllegalStateException("Unexpected value: " + choice);
       };
    }
}