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
    // Methods
    public void convert() {

    }
    public void deposit() {

    }
    public static void viewCurrencies() {
        for (double currency : wallet) {
            System.out.println(currency);
        }
    }
    public void withdraw() {

    }
    public static void main(String[] args) {

    }
}
