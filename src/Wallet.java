public class Wallet {
    private double dollars;
    private double euros;
    private double francs;
    private double pesos;
    private double pounds;
    private double rubles;
    private double[] walletArray;
    static String currencyList = """
                (1) - Dollars
                (2) - Euros
                (3) - Francs
                (4) - Pesos
                (5) - Pounds
                (6) - Rubles""";
    public Wallet() {
        this.dollars = 0.00;
        this.euros = 0.00;
        this.francs = 0.00;
        this.pesos = 0.00;
        this.pounds = 0.00;
        this.rubles = 0.00;
        this.walletArray = new double[]{dollars, euros, francs, pesos, pounds, rubles};
    }
    // Get & Set Dollars
    public double getDollars() {
        return this.dollars;
    }
    public void setDollars(double amount) {
        this.dollars = amount;
    }
    // Get & Set Euros
    public double getEuros() {
        return this.euros;
    }
    public void setEuros(double amount) {
        this.euros = amount;
    }
    // Get & Set Francs
    public double getFrancs() {
        return this.francs;
    }
    public void setFrancs(double amount) {
        this.francs = amount;
    }
    // Get & Set Pesos
    public double getPesos() {
        return this.pesos;
    }
    public void setPesos(double amount) {
        this.pesos = amount;
    }
    // Get & Set Pounds
    public double getPounds() {
        return this.pounds;
    }
    public void setPounds(double amount) {
        this.pounds = amount;
    }
    // Get & Set Rubes
    public double getRubles() {
        return this.rubles;
    }
    public void setRubles(double amount) {
        this.rubles = amount;
    }
    // Get & Set Wallet
    public double[] getWalletArray() {
        return this.walletArray;
    }
    public void convert() {
        System.out.println("What currency would you like to convert from?\n" + currencyList);
        int from = Utilities.makeChoiceWithinRange(new int[]{1, 6});
        System.out.println("What currency would you like to convert to?\n" + currencyList);
        int to = Utilities.makeChoiceWithinRange(new int[]{1, 6});
        System.out.println("How much would you like to convert?");
        double amount = Utilities.amountChoice();
        boolean hasEnoughMoney = this.hasEnoughMoney(amount, from);
        if (hasEnoughMoney) {
            System.out.println(Utilities.convertPrompt(from, to, amount));
            this.subtractMoney(amount, from);
            this.addMoney(Utilities.tableAmount(to, amount), to);
        }
    }
    public void deposit() {
        System.out.println("Which currency would you like to deposit?");
        System.out.println(currencyList);
        int choice = Utilities.makeChoiceWithinRange(new int[]{1, 6});
        System.out.println("How much would you like to deposit: ");
        double amount = Utilities.amountChoice();
        switch(choice) {
            case 1 -> {
                this.setDollars(amount);
            }
            case 2 -> {
                this.setEuros(amount);
            }
            case 3 -> {
                this.setFrancs(amount);
            }
            case 4 -> {
                this.setPesos(amount);
            }
            case 5 -> {
                this.setPounds(amount);
            }
            case 6 -> {
                this.setRubles(amount);
            }
        }
    }
    public void viewCurrencies() {
        for (int i = 0; i < this.getWalletArray().length; i++) {
            StringBuilder currencyName = new StringBuilder();
            double walletAmount = 0.00;
            switch (i) {
                case 0 -> {
                    currencyName.append("US Dollars");
                    walletAmount = this.getDollars();
                }
                case 1 -> {
                    currencyName.append("Euros");
                    walletAmount = this.getEuros();
                }
                case 2 -> {
                    currencyName.append("Francs");
                    walletAmount = this.getFrancs();
                }
                case 3 -> {
                    currencyName.append("Pesos");
                    walletAmount = this.getPesos();
                }
                case 4 -> {
                    currencyName.append("UK Pound");
                    walletAmount = this.getPounds();
                }
                case 5 -> {
                    currencyName.append("Rubes");
                    walletAmount = this.getRubles();
                }
            }
            int referenceLength = 25;
            String phraseLength = String.format("%s: %.2f\n", currencyName, walletAmount);
            String padding = "";
            int difference = referenceLength-phraseLength.length();
            if (difference > 0) {
                for (int j = 0; j < difference; j++) {
                    padding += " ";
                }
            }
            System.out.printf("%s: %s %.2f\n", currencyName, padding, walletAmount);
        }
    }
    public void withdraw() {
        int choice = 0;
        System.out.println(currencyList);
        choice = Utilities.makeChoiceWithinRange(new int[]{1, 6});
        System.out.println("How much would you like to withdraw: ");
        double amount = Utilities.amountChoice();
        if (this.hasEnoughMoney(amount, choice)) {
            this.subtractMoney(amount, choice);
        }
    }
    public boolean hasEnoughMoney(double amount, int choice) {
        // If the choice of currency minus the amount wanted to withdraw/convert is negative,
        // then you cannot complete the conversion or withdrawal.
        switch (choice) {
            // Can withdraw US Dollars
            case 1 -> {
                if (0 > this.getDollars() - amount) {
                    System.out.println(Utilities.overdraftPrompt());
                    return false;
                } else {
                    return true;
                }
            }
            case 2 -> {
                // Can withdraw Euros
                if (this.getEuros() - amount < 0) {
                    System.out.println(Utilities.overdraftPrompt());
                    return false;
                } else {
                    return true;
                }
            }
            case 3 -> {
                // Can withdraw Francs
                if (this.getFrancs() - amount < 0) {
                    System.out.println(Utilities.overdraftPrompt());
                    return false;
                } else {
                    return true;
                }
            }
            case 4 -> {
                // Can withdraw Pesos
                if (this.getPesos() - amount < 0) {
                    System.out.println(Utilities.overdraftPrompt());
                    return false;
                } else {
                    return true;
                }
            }
            case 5 -> {
                // Can withdraw UK Pounds
                if (this.getPounds() - amount < 0) {
                    System.out.println(Utilities.overdraftPrompt());
                    return false;
                } else {
                    return true;
                }
            }
            case 6 -> {
                // Can withdraw Rubes
                if (this.getRubles() - amount < 0) {
                    System.out.println(Utilities.overdraftPrompt());
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
    public void subtractMoney(double amount, int choice) {
        switch (choice) {
            case 1 -> {
                this.setDollars(this.getDollars() - amount);
            }
            case 2 -> {
                this.setEuros(this.getEuros() - amount);
            }
            case 3 -> {
                this.setFrancs(this.getFrancs() - amount);
            }
            case 4 -> {
                this.setPesos(this.getPesos() - amount);
            }
            case 5 -> {
                this.setPounds(this.getPounds() - amount);
            }
            case 6 -> {
                this.setRubles(this.getRubles() - amount);
            }
        }
    }
    public void addMoney(double amount, int choice) {
        switch (choice) {
            case 1 -> {
                this.setDollars(this.getDollars() + amount);
            }
            case 2 -> {
                this.setEuros(this.getEuros() + amount);
            }
            case 3 -> {
                this.setFrancs(this.getFrancs() + amount);
            }
            case 4 -> {
                this.setPesos(this.getPesos() + amount);
            }
            case 5 -> {
                this.setPounds(this.getPounds() + amount);
            }
            case 6 -> {
                this.setRubles(this.getRubles() + amount);
            }
        }
    }
}
