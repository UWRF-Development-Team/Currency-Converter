package junit;

import org.falcon.model.deprecated.CurrencyConverter;
import org.falcon.model.deprecated.Wallet;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
public class CurrencyConverterTest {
    Wallet testWallet = new Wallet();
    CurrencyConverter testCurrencyConverter = new CurrencyConverter(testWallet);
    @Test
    public void testWalletConstructor() {
        assertThat(0.00, equalTo(testCurrencyConverter.getWallet().getDollars()));
        assertThat(0.00, equalTo(testCurrencyConverter.getWallet().getEuros()));
        assertThat(0.00, equalTo(testCurrencyConverter.getWallet().getFrancs()));
        assertThat(0.00, equalTo(testCurrencyConverter.getWallet().getPesos()));
        assertThat(0.00, equalTo(testCurrencyConverter.getWallet().getPounds()));
        assertThat(0.00, equalTo(testCurrencyConverter.getWallet().getRubles()));
    }
}