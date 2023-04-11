import org.junit.Test;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;
public class TestCurrencyConverter extends CurrencyConverter {
    CurrencyConverter testWallet = new CurrencyConverter();
    // Test Setters and Getters
    @Test
    public void testSetUSDollars() {
        testWallet.setUSDollars(20.0);
        assertThat(20.0, equalTo(testWallet.getUSDollars()));
    }
    @Test
    public void testSetUKPounds() {
        testWallet.setUkPounds(20.0);
        assertThat(20.0, equalTo(testWallet.getUkPounds()));

    }
    @Test
    public void testSetEuro() {
        testWallet.setEuro(20.0);
        assertThat(20.0, equalTo(testWallet.getEuro()));
    }
    @Test
    public void testSetRubes() {
        testWallet.setRubes(20.0);
        assertThat(20.0, equalTo(testWallet.getRubes()));
    }
    @Test
    public void testSetPesos() {
        testWallet.setPesos(20.0);
        assertThat(20.0, equalTo(testWallet.getPesos()));
    }
    // Test Conversion
    // Overridden to be able to test effectively
    int choice;
    double amountToConvert;
        public double convert(int from, int to, double amountToConvert) throws InputMismatchException, IOException {
        String currencyKey = "";
        switch (from) {
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
        switch (to) {
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
    @Test
    public void testConvertToRubes() throws IOException {
        amountToConvert = 100.0;

        assertThat(1.3, equalTo(convert(5, 1, amountToConvert)));
    }
//    @Test
//    public void testConvertToUKPounds() {
//        amountToConvert = 100.0;
//        choice = 2;
//        assertThat(120.0, equalTo(convert(1, 2, amountToConvert)));
//    }
//    @Test
//    public void testConvertToEuro() {
//        amountToConvert = 100.0;
//        choice = 3;
//        assertThat(106.0, equalTo(convert(1, 3, amountToConvert)));
//    }
//    @Test
//    public void testConvertToPeso() {
//        amountToConvert = 100.0;
//        choice = 4;
//        assertThat(5.0, equalTo(convert(1)));
//    }
//    @Test
//    public void testConvertError() {
//        amountToConvert = 100.0;
//        choice = 5;
//        assertThat(-1.0, equalTo(convert(1)));
//    }
//
//    @Override
//    public int walletWithdraw(String currencyType, double amount) {
//        if (currencies.get(currencyType) - amount >= 0) {
//            return 1;
//        } else {
//            return -1;
//        }
//    }
//    @Test
//    public void testWalletWithdrawUSDollarsPass() {
//        testWallet.setUSDollars(25.0);
//        assertThat(1, equalTo(walletWithdraw( "usDollars", 20.0)));
//    }
//    @Test
//    public void testWalletWithdrawUSDollarsFail() {
//        testWallet.setUSDollars(5.0);
//        assertThat(-1, equalTo(walletWithdraw( "usDollars", 20.0)));
//    }
//    @Test
//    public void testWalletWithdrawUkPoundPass() {
//        testWallet.setUkPounds(25.0);
//        assertThat(1, equalTo(walletWithdraw( "ukPounds", 20.0)));
//    }
//    @Test
//    public void testWalletWithdrawUkPoundFail() {
//        testWallet.setUkPounds(5.0);
//        assertThat(-1, equalTo(walletWithdraw( "ukPounds", 20.0)));
//    }
//    @Test
//    public void testWalletWithdrawRubesPass() {
//        testWallet.setRubes(25.0);
//        assertThat(1, equalTo(walletWithdraw( "rubes", 20.0)));
//    }
//    @Test
//    public void testWalletWithdrawRubesFail() {
//        testWallet.setRubes(5.0);
//        assertThat(-1, equalTo(walletWithdraw( "rubes", 20.0)));
//    }
//    @Test
//    public void testWalletWithdrawEuroPass() {
//        testWallet.setEuro(25.0);
//        assertThat(1, equalTo(walletWithdraw( "euros", 20.0)));
//    }
//    @Test
//    public void testWalletWithdrawEuroFail() {
//        testWallet.setEuro(5.0);
//        assertThat(-1, equalTo(walletWithdraw( "euros", 20.0)));
//    }
//    @Test
//    public void testWalletWithdrawPesoPass() {
//        testWallet.setPesos(25.0);
//        assertThat(1, equalTo(walletWithdraw( "pesos", 20.0)));
//    }
//    @Test
//    public void testWalletWithdrawPesoFail() {
//        testWallet.setPesos(5.0);
//        assertThat(-1, equalTo(walletWithdraw( "pesos", 20.0)));
//    }
//    @Test
//    public void testConstructorValues() {
//        assertThat(0.0, equalTo(currencies.get("usDollars")));
//        assertThat(0.0, equalTo(currencies.get("ukPounds")));
//        assertThat(0.0, equalTo(currencies.get("rubes")));
//        assertThat(0.0, equalTo(currencies.get("euros")));
//        assertThat(0.0, equalTo(currencies.get("pesos")));
//    }

}