import java.util.Scanner;
public class CurrencyConverter {
    Wallet wallet;
    public CurrencyConverter(Wallet wallet) {
        this.wallet = wallet;
    }
    public Wallet getWallet() {
        return this.wallet;
    }
    public void chooseOption() {
        System.out.println("""
                Please choose an option:
                (1) Convert
                (2) Deposit
                (3) View Currencies
                (4) Withdraw""");
        int choice = Utilities.makeChoiceWithinRange(new int[]{1, 4});
        switch (choice) {
            case 1 -> {
                this.getWallet().convert();
            }
            case 2 -> {
                this.getWallet().deposit();
            }
            case 3 -> {
                this.getWallet().viewCurrencies();
            }
            case 4 -> {
                this.getWallet().withdraw();
            }
        }
    }
    public void startCurrencyConverter() {
        boolean continueConverting = true;
        while (continueConverting) {
            this.chooseOption();
            System.out.println("Would you like to continue? (1) No (2) Yes");
            int choice = Utilities.makeChoiceWithinRange(new int[]{1,2});
            if (choice == 1) {
                continueConverting = false;
            } else {
                continueConverting = true;
            }
        }
    }
    public static void main(String[] args) {
        System.out.println("Welcome to the currency converter!");
        CurrencyConverter currencyConverter = new CurrencyConverter(new Wallet());
        currencyConverter.startCurrencyConverter();
    }
}
