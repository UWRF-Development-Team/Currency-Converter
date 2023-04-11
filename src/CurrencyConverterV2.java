import java.sql.SQLOutput;
import java.util.Scanner;

public class CurrencyConverterV2 {
    // Currency Constant Values
    final double DOLLAR = 1;
    final double EURO = 1.06;
    final double FRANC = 0.90;
    final double PESO = 0.05;
    final double POUND = 1.20;
    final double RUBE = 0.013;

    // Wallet
    static double dollarWallet = 0;
    static double euroWallet = 0;
    static double francWallet = 0;
    static double pesoWallet = 0;
    static double poundWallet = 0;
    static double rubeWallet = 0;

    static double[] wallet = {dollarWallet, euroWallet, francWallet, pesoWallet, poundWallet, rubeWallet};

    static Scanner input = new Scanner(System.in);
    // Get & Set Dollars
    public static double getDollars() {
        return wallet[0];
    }
    public static void setDollars(double amount) {
        wallet[0] = amount;
    }

    // Get & Set Euros
    public static double getEuros() {
        return wallet[1];
    }
    public static void setEuros(double amount) {
        wallet[1] = amount;
    }

    // Get & Set Franc
    public static double getFrancs() {
        return wallet[2];
    }
    public static void setFrancs(double amount) {
        ;wallet[2] = amount;
    }
    // Get & Set Pesos
    public static double getPesos() {
        return wallet[3];
    }
    public static void setPesos(double amount) {
        wallet[3] = amount;
    }

    // Get & Set Pounds
    public static double getPounds() {
        return wallet[4];
    }
    public static void setPounds(double amount) {
        wallet[4] = amount;
    }

    // Get & Set Rubes
    public static double getRubes() {
        return wallet[5];
    }
    public static void setRubes(double amount) {
        wallet[5] = amount;
    }

    // Methods
    public static void convert() {
        String currency = """
                (1) - Dollars
                (2) - Euros
                (3) - Francs
                (4) - Pesos
                (5) - Pounds
                (6) - Rubes""";

        System.out.println("What currency would you like to convert from?" + currency);
        int from = input.nextInt();
        System.out.println("What currency would you like to convert to?" + currency);
        int to = input.nextInt();
        System.out.println("How much would you like to convert?");
        double amount = input.nextDouble();


    }
    public static void deposit() {
        int choice = 0;
        System.out.println("""
                Please choose the denomination you want to deposit:
                (1) - Dollars
                (2) - Euros
                (3) - Francs
                (4) - Pesos
                (5) - Pounds
                (6) - Rubes""");
        choice = input.nextInt();
        System.out.println("How much would you like to deposit: ");
        double amount = input.nextDouble();
        switch(choice) {
            case 1 -> {
                setDollars(amount);
            }
            case 2 -> {
                setEuros(amount);
            }
            case 3 -> {
                setFrancs(amount);
            }
            case 4 -> {
                setPesos(amount);
            }
            case 5 -> {
                setPounds(amount);
            }
            case 6 -> {
                setRubes(amount);
            }
        }
    }

    public static void viewCurrencies() {
        for (int i = 0; i < wallet.length; i++) {
            StringBuilder currencyName = new StringBuilder();
            switch (i) {
                case 0 -> {
                    currencyName.append("US Dollars");
                }
                case 1 -> {
                    currencyName.append("Euros");
                }
                case 2 -> {
                    currencyName.append("Francs");
                }
                case 3 -> {
                    currencyName.append("Pesos");
                }
                case 4 -> {
                    currencyName.append("UK Pound");
                }
                case 5 -> {
                    currencyName.append("Rubes");
                }
            }
            System.out.printf("%s: %.2f\n", currencyName.toString(), wallet[i]);
        }
    }

    public static void withdraw() {
        int choice = 0;
        System.out.println("""
                What currency would you like to withdraw from: 
                (1) - Dollars
                (2) - Euros
                (3) - Francs
                (4) - Pesos
                (5) - Pounds
                (6) - Rubes""");
        choice = input.nextInt();
        System.out.println("How much would you like to withdraw: ");
        double amount = input.nextDouble();
        switch (choice) {
            case 1 -> {
                if (hasEnoughMoney(amount, choice)) {
                    setDollars(getDollars() - amount);
                }
            }
            case 2 -> {
                if (hasEnoughMoney(amount, choice)) {
                    setEuros(getEuros() - amount);
                }
            }
            case 3 -> {
                if (hasEnoughMoney(amount, choice)) {
                    setFrancs(getFrancs() - amount);
                }
            }
            case 4 -> {
                if (hasEnoughMoney(amount, choice)) {
                    setPesos(getPesos() - amount);
                }
            }
            case 5 -> {
                if (hasEnoughMoney(amount, choice)) {
                    setPounds(getPounds() - amount);
                }
            }
            case 6 -> {
                if (hasEnoughMoney(amount, choice)) {
                    setRubes(getRubes() - amount);
                }
            }
        }
    }
    public static boolean hasEnoughMoney(double amount, int choice) {
        // If the choice of currency minus the amount wanted to withdraw/convert is negative,
        // then you cannot complete the conversion or withdrawal.
        switch (choice) {
            // Can withdraw US Dollars
            case 1 -> {
                if (getDollars() - amount < 0) {
                    return false;
                } else {
                    return true;
                }
            }
            case 2 -> {
                    // Can withdraw Euros
                if (getEuros() - amount < 0) {
                    return false;
                } else {
                    return true;
                }
            }
            case 3 -> {
                // Can withdraw Francs
                if (getFrancs() - amount < 0) {
                    return false;
                } else {
                    return true;
                }
            }
            case 4 -> {
                // Can withdraw Pesos
                if (getPesos() - amount < 0) {
                    return false;
                } else {
                    return true;
                }
            }
            case 5 -> {
                // Can withdraw UK Pounds
                if (getPounds() - amount < 0) {
                    return false;
                } else {
                    return true;
                }
            }
            case 6 -> {
                // Can withdraw Rubes
                if (getRubes() - amount < 0) {
                    return false;
                } else {
                    return true;
                }
            }
        }
        return false;
    }
    public static void chooseOption() {
        System.out.println("""
                Please choose an option:
                (1) Convert
                (2) Deposit
                (3) View Currency
                (4) Withdraw""");
    }
    public static void main(String[] args) {
        //viewCurrencies();
        deposit();
        viewCurrencies();
    }
}
