import java.util.InputMismatchException;
import java.util.Scanner;
public class Utilities extends CurrencyConverterV2 {
    static Scanner input = new Scanner(System.in);

    public static void print(String phrase) {
        System.out.println(phrase);
    }

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
                    case 0, 1 -> true;
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

    public static boolean hasEnoughMoney(double amount, int choice) {
        // If the choice of currency minus the amount wanted to withdraw/convert is negative,
        // then you cannot complete the conversion or withdrawal.
        switch (choice) {
            // Can withdraw US Dollars
            case 1 -> {
                if (0 > getDollars() - amount) {
                    System.out.println(overdraftPrompt());
                    return false;
                } else {
                    return true;
                }
            }
            case 2 -> {
                // Can withdraw Euros
                if (getEuros() - amount < 0) {
                    System.out.println(overdraftPrompt());
                    return false;
                } else {
                    return true;
                }
            }
            case 3 -> {
                // Can withdraw Francs
                if (getFrancs() - amount < 0) {
                    System.out.println(overdraftPrompt());
                    return false;
                } else {
                    return true;
                }
            }
            case 4 -> {
                // Can withdraw Pesos
                if (getPesos() - amount < 0) {
                    System.out.println(overdraftPrompt());
                    return false;
                } else {
                    return true;
                }
            }
            case 5 -> {
                // Can withdraw UK Pounds
                if (getPounds() - amount < 0) {
                    System.out.println(overdraftPrompt());
                    return false;
                } else {
                    return true;
                }
            }
            case 6 -> {
                // Can withdraw Rubes
                if (getRubes() - amount < 0) {
                    System.out.println(overdraftPrompt());
                    return false;
                } else {
                    return true;
                }
            }
            default -> {
                System.out.println(choice + " is not an option.\n" +
                        Utilities.betweenNumsPrompt(new int[]{1, 6}));
            }
        }
        return false;
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
            case 1 -> amount * CurrencyValue.DOLLAR.getValue();
            case 2 -> amount * CurrencyValue.EURO.getValue();
            case 3 -> amount / CurrencyValue.FRANC.getValue();
            case 4 -> amount / CurrencyValue.PESO.getValue();
            case 5 -> amount * CurrencyValue.POUND.getValue();
            case 6 -> amount / CurrencyValue.RUBE.getValue();
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
            case 6 -> "Rubes";
           default -> throw new IllegalStateException("Unexpected value: " + choice);
       };
    }
    public static void subtractMoney(double amount, int choice) {
        switch (choice) {
            case 1 -> {
                setDollars(getDollars() - amount);
            }
            case 2 -> {
                setEuros(getEuros() - amount);
            }
            case 3 -> {
                setFrancs(getFrancs() - amount);
            }
            case 4 -> {
                setPesos(getPesos() - amount);
            }
            case 5 -> {
                setPounds(getPounds() - amount);
            }
            case 6 -> {
                setRubes(getRubes() - amount);
            }
        }
    }
        public static void addMoney(double amount, int choice) {
        switch (choice) {
            case 1 -> {
                setDollars(getDollars() + amount);
            }
            case 2 -> {
                setEuros(getEuros() + amount);
            }
            case 3 -> {
                setFrancs(getFrancs() + amount);
            }
            case 4 -> {
                setPesos(getPesos() + amount);
            }
            case 5 -> {
                setPounds(getPounds() + amount);
            }
            case 6 -> {
                setRubes(getRubes() + amount);
            }
        }
    }
}