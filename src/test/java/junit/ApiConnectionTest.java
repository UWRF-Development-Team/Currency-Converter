package junit;

import org.falcon.model.api.ApiConnection;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class ApiConnectionTest {
    private ApiConnection testApiConnection;
    @BeforeEach
    public void setUp() {
        this.testApiConnection = new ApiConnection();
    }
    @Test
    public void testResponseSuccess() {
        this.testApiConnection.fetchResponse();
        String response = this.testApiConnection.getResponse();
        System.out.println(response);
        assertNotEquals(ApiConnection.RESPONSE_NOT_FOUND_MESSAGE, response);
    }
    @Test
    public void testGetReadableResponse() {
        final String[] artifacts = {"{", "}", "\"", ","};
        this.testApiConnection.fetchResponse();
        String readableResponse = this.testApiConnection.getReadableResponse();
        System.out.println(readableResponse);
        boolean containsArtifacts = false;
        for (String artifact : artifacts) {
            if (readableResponse.contains(artifact)) {
                containsArtifacts = true;
                break;
            }
        }
        assertFalse(containsArtifacts);
    }
    @Test
    public void testGetMapResponseCurrencies() {
        this.testApiConnection.fetchResponse();
        Map<String, Double> currencyHashMap = this.testApiConnection.getMapResponseCurrencies();
        currencyHashMap.forEach((key, value) -> {
            System.out.println(key + ": " + value);
        });
        assertFalse(currencyHashMap.isEmpty());
    }
}
