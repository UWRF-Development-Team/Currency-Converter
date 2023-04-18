import java.util.Scanner;
public class CurrencyConverterV2 {
    // Do we take this object-oriented for JUnit

    // Currency Constant Values
    //  - Now located in Enum CurrencyValue
    //  - Do CurrencyValue.SELECTED_CURRENCY.getValue() to
    //    get the values of each currency -- replacing SELECTED_CURRENCY
    //    with the desired currency in screaming snake (all caps and
    //    underscores)

    // Wallet
    static double dollarWallet = 0;
    static double euroWallet = 0;
    static double francWallet = 0;
    static double pesoWallet = 0;
    static double poundWallet = 0;
    static double rubeWallet = 0;
    static String currencyList = """
                (1) - Dollars
                (2) - Euros
                (3) - Francs
                (4) - Pesos
                (5) - Pounds
                (6) - Rubes""";
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
        wallet[2] = amount;
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
        System.out.println("What currency would you like to convert from?\n" + currencyList);
        int from = Utilities.makeChoiceWithinRange(new int[]{1, 6});
        System.out.println("What currency would you like to convert to?\n" + currencyList);
        int to = Utilities.makeChoiceWithinRange(new int[]{1, 6});
        System.out.println("How much would you like to convert?");
        double amount = Utilities.amountChoice();
        boolean hasEnoughMoney = Utilities.hasEnoughMoney(amount, from);
        if (hasEnoughMoney) {
            System.out.println(Utilities.convertPrompt(from, to, amount));
            Utilities.subtractMoney(amount, from);
            Utilities.addMoney(Utilities.tableAmount(to, amount), to);
        }
    }
    public static void deposit() {
        System.out.println("Which currency would you like to deposit?");
        System.out.println(currencyList);
        int choice = Utilities.makeChoiceWithinRange(new int[]{1, 6});
        System.out.println("How much would you like to deposit: ");
        double amount = Utilities.amountChoice();
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
            int referenceLength = 25;
            String phraseLength = String.format("%s: %.2f\n", currencyName, wallet[i]);
            String padding = "";
            int difference = referenceLength-phraseLength.length();
            if (difference > 0) {
                for (int j = 0; j < difference; j++) {
                    padding += " ";
                }
            }
            System.out.printf("%s: %s %.2f\n", currencyName, padding, wallet[i]);
        }
    }

    public static void withdraw() {
        int choice = 0;
        System.out.println(currencyList);
        choice = Utilities.makeChoiceWithinRange(new int[]{1, 6});
        System.out.println("How much would you like to withdraw: ");
        double amount = Utilities.amountChoice();
        if (Utilities.hasEnoughMoney(amount, choice)) {
            Utilities.subtractMoney(amount, choice);
        }
    }
    public static void chooseOption() {
        System.out.println("""
                Please choose an option:
                (1) Convert
                (2) Deposit
                (3) View Currencies
                (4) Withdraw""");
        int choice = Utilities.makeChoiceWithinRange(new int[]{1, 4});
        switch (choice) {
            case 1 -> {
                convert();
            }
            case 2 -> {
                deposit();
            }
            case 3 -> {
                viewCurrencies();
            }
            case 4 -> {
                withdraw();
            }
        }
    }
    public static void startCurrencyConverter() {
        boolean continueConverting = true;
        while (continueConverting) {
            chooseOption();
            System.out.println("Would you like to continue? (0) No (1) Yes");
            int choice = Utilities.makeChoiceWithinRange(new int[]{1,2});
            if (choice == 0) {
                continueConverting = false;
            } else {
                continueConverting = true;
            }
        }
    }
    public static void main(String[] args) {
        System.out.println("Welcome to the currency converter!");
        startCurrencyConverter();
    }
}
