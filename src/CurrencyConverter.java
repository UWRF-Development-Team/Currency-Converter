import java.util.*;
import java.io.*;
public class CurrencyConverter {
    static HashMap<String, Double> currencies = new HashMap<>();
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static String[] currencyType = {"usDollars", "rubes", "ukPounds", "euros", "pesos"};
    // add Francs
    static final double usDollars = 1;
    static final double rubes = 0.013;
    static final double ukPounds = 1.20;
    static final double euro = 1.06;
    static final double peso = 0.05;

    public CurrencyConverter() {
        for (String curr : currencyType) {
            currencies.put(curr, 0.00);
        }
    }

    public void setUSDollars(double amount) {
        currencies.put("usDollars", amount);
    }

    public double getUSDollars() {
        return currencies.get("usDollars");
    }

    public void setUkPounds(double amount) {
        currencies.put("ukPounds", amount);
    }

    public double getUkPounds() {
        return currencies.get("ukPounds");
    }

    public void setEuro(double amount) {
        currencies.put("euros", amount);
    }

    public double getEuro() {
        return currencies.get("euros");
    }

    public void setRubes(double amount) {
        currencies.put("rubes", amount);
    }

    public double getRubes() {
        return currencies.get("rubes");
    }

    public void setPesos(double amount) {
        currencies.put("pesos", amount);
    }

    public double getPesos() {
        return currencies.get("pesos");
    }

    public static void main(String[] args) throws IOException {
        // Instantiate CurrencyConverter object and set amount to 0.00
        startCurrencyConverter();
    } //end main

    public static void startCurrencyConverter() throws IOException {
        CurrencyConverter myWallet = new CurrencyConverter();
        // Ask user for total amount in wallet
        boolean isOption = false;
        // this part isn't working because we aren't reassigning it a new value
        boolean continueAddingToWallet = true;
        System.out.println("Welcome to the currency converter!");
        outer:
        while (!isOption || continueAddingToWallet) {
            int currencyChoice = 0;
            System.out.println("Which denomination of currency do you have?\n" +
                    "None (0), US Dollars (1), UK Pounds (2), Euros (3), Pesos (4), Rubes (5)?");
            try {
                currencyChoice = Integer.parseInt(input.readLine());
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (NumberFormatException e) {
                System.err.println(e + ": please enter a number.");
            }
            if (currencyChoice < 0 || currencyChoice > 5) {
                System.out.println("(" + currencyChoice + ") is not an option.");
            } else if (currencyChoice == 0) {
                break outer;
            } else {
                switch (currencyChoice) {
                    case 1 -> {
                        if (startCurrencyConverterHelp(1,continueAddingToWallet, myWallet) == false)
                            break outer;
                    }
                    case 2 -> {
                        if (startCurrencyConverterHelp(2,continueAddingToWallet, myWallet) == false)
                            break outer;
                    }
                    case 3 -> {
                        if (startCurrencyConverterHelp(3,continueAddingToWallet, myWallet) == false)
                            break outer;
                    }
                    case 4 -> {
                        if (startCurrencyConverterHelp(4,continueAddingToWallet, myWallet) == false)
                            break outer;
                    }
                    case 5 -> {
                        if (startCurrencyConverterHelp(5,continueAddingToWallet, myWallet) == false)
                            break outer;
                    }
                }
            }
        }
        boolean continueConverting = true;
        while (continueConverting) {
            myWallet.chooseOption();
            System.out.println("Would you like to continue (1) or exit (2)?");
            int choice = 0;
            try {
                choice = Integer.parseInt(input.readLine());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            switch (choice) {
                case 1 -> {
                    continueConverting = true;
                }
                case 2 -> {
                    continueConverting = false;
                }
                default -> {
                    System.out.println("Your input " + choice + " is not an option. Try again.");
                }
            }
        }
        //try for user input, catch error for string/character input
    }

    // This help method is adding amount twice. and it is wrong when you call only one currecny both
    // in the value and the terminatig condition.
    public static boolean startCurrencyConverterHelp(int key, boolean continueAddingToWallet, CurrencyConverter myWallet) throws IOException {
        String type = currencySelector(key);

        boolean validInput = false;
        while (!validInput) {
            try {
                System.out.println("Please input the amount of " + type +" in your wallet.");
                double amount = Double.parseDouble(input.readLine());
                currencySetter(key, amount, myWallet);
                validInput = true;
                boolean moreCurrencyValidInput = false;
                while (!moreCurrencyValidInput) {
                    System.out.println("Do you have more currency in your wallet? No (0) Yes (1)");
                    int moreCurrency = Integer.parseInt(input.readLine());
                    if (moreCurrency == 0) {
                        continueAddingToWallet = false;
                        moreCurrencyValidInput = true;
                        return false;
                    } else if (moreCurrency == 1) {
                        continueAddingToWallet = true;
                        moreCurrencyValidInput = true;
                    } else {
                        System.out.println(moreCurrency + ": is not an option.");
                        moreCurrencyValidInput = false;
                    }
                }
            } catch (NumberFormatException e) {
                System.err.println(e + ": please enter a number.");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
    }
        return true;
    }

    public static String currencySelector(int key){
        switch (key) {
            case 1 -> {
               return "Dollar";
            }
            case 2 -> {
               return "Rubic";
            }
            case 3 -> {
               return "Pound";
            }
            case 4 -> {
               return "Euro";
            }
            case 5 -> {
                return "Pesos";
            }
        }
        return "*******CurrencySelector Broken******* Blame on Jeremy";
    }

    public static void currencySetter(int key, double amount, CurrencyConverter myWallet) {
        switch (key) {
            case 1 -> {
                myWallet.setUSDollars(amount);
            }
            case 2 -> {
                myWallet.setRubes(amount);
            }
            case 3 -> {
                myWallet.setUkPounds(amount);
            }
            case 4 -> {
                myWallet.setEuro(amount);
            }
            case 5 -> {
                myWallet.setPesos(amount);
            }
        }
    }

    public void makeDeposit () {
        try {
            boolean isValid = false;
            while (!isValid) {
                System.out.println("What would you like to deposit?\n1: USD\n2: Rubes\n3: Pounds\n4: Euro\n5: Peso");
                int choice = Integer.parseInt(input.readLine());
                System.out.println("How much would you like to deposit?");
                String depositAmount = String.valueOf(Double.parseDouble(input.readLine()));
                switch (choice) {
                    case 1 -> {
                        currencies.put("usDollars", getUSDollars() + Double.valueOf(depositAmount));
                        this.announceWallet();
                    }
                    case 2 -> {
                        currencies.put("rubes", getRubes() + Double.valueOf(depositAmount));
                        this.announceWallet();
                    }
                    case 3 -> {
                        currencies.put("ukPounds", getUkPounds() + Double.valueOf(depositAmount));
                        this.announceWallet();
                    }
                    case 4 -> {
                        currencies.put("euros", getEuro() + Double.valueOf(depositAmount));
                        this.announceWallet();
                    }
                    case 5 -> {
                        currencies.put("pesos", getPesos() + Double.valueOf(depositAmount));
                        this.announceWallet();
                    }
                    default -> {
                        System.out.println("Invalid entry, try again.");
                    }
                }
                isValid = true;
                break;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void makeWithdrawal () {
        try {
            System.out.println("What would you like to withdrawal?\n1: USD\n2: Rubes\n3: Pounds\n4: Euro\n5: Peso");
            int choice = Integer.parseInt(input.readLine());
            System.out.println("How much would you like to withdraw?");
            double withdrawalAmount = Double.parseDouble(input.readLine());
            boolean isValid = false;
            switch (choice) {
                case 1 -> {
                    if (this.walletWithdraw("usDollars", withdrawalAmount) > 0) {
                        this.announceWallet();
                    } else {
                        this.announceWallet();
                    }
                }
                case 2 -> {
                    if (this.walletWithdraw("rubes", withdrawalAmount) > 0) {
                        this.announceWallet();
                    } else {
                        this.announceWallet();
                    }
                }
                case 3 -> {
                    if (this.walletWithdraw("ukPounds", withdrawalAmount) > 0) {
                        this.announceWallet();
                    } else {
                        this.announceWallet();
                    }
                }
                case 4 -> {
                    if (this.walletWithdraw("euros", withdrawalAmount) > 0) {
                        this.announceWallet();
                    } else {
                        this.announceWallet();
                    }
                }
                case 5 -> {
                    if (this.walletWithdraw("pesos", withdrawalAmount) > 0) {
                        this.announceWallet();
                    } else {
                        this.announceWallet();
                    }
                }
                default -> {
                    {
                        System.out.println("Invalid entry, try again.");
                    }
                    isValid = true;
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void announceWallet () {
        for (Map.Entry<String, Double> curr : currencies.entrySet()) {
            String key = "";
            switch (curr.getKey()) {
                case "usDollars" -> {
                    key = "US Dollars";
                }
                case "ukPounds" -> {
                    key = "UK Pounds";
                }
                case "euros" -> {
                    key = "Euros";
                }
                case "rubes" -> {
                    key = "Rubes";
                }
                case "pesos" -> {
                    key = "Pesos";
                }
            }
            System.out.print(key + ": " + curr.getValue());
            System.out.println();
        }
    }
    public void chooseOption () {
        int choice = 0;
        System.out.println("Would you like to (1) deposit currency, (2) withdraw currency, (3) convert your currency, or (4) view your currencies?");
        boolean isValid = false;
        while (!isValid) {
            try {
                choice = Integer.parseInt(input.readLine());
                isValid = false;
                switch (choice) {
                    case 1 -> {
                        this.makeDeposit();
                        isValid = true;
                    }
                    case 2 -> {
                        this.makeWithdrawal();
                        isValid = true;
                    }
                    case 3 -> {
                        this.convert();
                        isValid = true;
                    }
                    case 4 -> {
                        this.announceWallet();
                        isValid = true;
                    }
                    default -> {
                        System.out.println("Invalid entry, try again.");
                        isValid = false;
                    }
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (NumberFormatException e) {
                System.err.println(e + ": Please enter an integer.");
            }
        }
    }
    public int walletWithdraw (String currencyType,double amount){
        if (currencies.get(currencyType) - amount >= 0) {
            System.out.printf("Withdrawal of %.2f successful\n", (float) amount);
            switch (currencyType) {
                case "usDollars" -> {
                    this.setUSDollars(currencies.get(currencyType) - amount);
                }
                case "ukPound" -> {
                    this.setUkPounds(currencies.get(currencyType) - amount);
                }
                case "euros" -> {
                    this.setEuro(currencies.get(currencyType) - amount);
                }
                case "pesos" -> {
                    this.setPesos(currencies.get(currencyType) - amount);
                }
                case "rubes" -> {
                    this.setRubes(currencies.get(currencyType) - amount);
                }
            }
            return 1;
        } else {
            System.out.println("Insufficient funds.");
            return -1;
        }
    }
    //switch method. Switches are good for user selection
    public void hasMoneyToConvert ( int choice){
        switch (choice) {
            case 1 -> {

            }
        }
    }
    public double convert() throws InputMismatchException, IOException {
        System.out.println("""
                    Please select which currency you would like to convert from: 
                    1: Rubes
                    2: UK Pound
                    3: Euro
                    4: Peso
                    5: US Dollar""");
        int choiceFrom = Integer.parseInt(input.readLine());
        System.out.println("""
                    Please select which currency you would like to convert to: 
                    1: Rubes
                    2: UK Pound
                    3: Euro
                    4: Peso
                    5: US Dollar""");
        int choiceTo = Integer.parseInt(input.readLine());
        String currencyKey = "";
        switch (choiceFrom) {
            case 1 -> {
                currencyKey = "rubes";
            }
            case 2 -> {
                currencyKey = "ukPounds";
            }
            case 3 -> {
                currencyKey = "euros";
            }
            case 4 -> {
                currencyKey = "pesos";
            }
            case 5 -> {
                currencyKey = "usDollars";
            }
        }
        System.out.println("Please select how much you would like to convert");
        double amountToConvert = Double.parseDouble(input.readLine());
        switch (choiceTo) {
            case 1 -> {
                return amountToConvert * rubes;
            }
            case 2 -> {
                return amountToConvert * ukPounds;
            }
            case 3 -> {
                return amountToConvert * euro;
            }
            case 4 -> {
                return amountToConvert * peso;
            }
            case 5 -> {
                return amountToConvert * usDollars;
            }
            default -> System.out.println("You did not make a proper selection");
        }
        System.out.println("Error, returning \"-1\"");
        return -1;
    }

    public boolean canConvert() {
        return true;
    }

    // check if the wallet has the currency needed to be added.
    // otherwise add.

}//end class