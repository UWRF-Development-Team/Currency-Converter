import org.junit.*;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;

public class TestCurrencyConverter {
    Wallet testWallet = new Wallet();
    CurrencyConverter testCurrencyConverter = new CurrencyConverter(testWallet);
    @Test
    public void testWalletConstructor() {
        assertThat(0.00, equalTo(testWallet.getDollars()));
        assertThat(0.00, equalTo(testWallet.getEuros()));
        assertThat(0.00, equalTo(testWallet.getFrancs()));
        assertThat(0.00, equalTo(testWallet.getPesos()));
        assertThat(0.00, equalTo(testWallet.getPounds()));
        assertThat(0.00, equalTo(testWallet.getRubes()));
    }
}