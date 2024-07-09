package org.falcon.model.api;

import lombok.Getter;
import lombok.Setter;
import org.falcon.model.SupportedCurrencies;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
public class ApiConnection {
    private String url;
    private String response;
    public static final String PREFIX_URL = "https://openexchangerates.org/api/latest.json?app_id=";
    public ApiConnection() {
        String apiKey = System.getenv("OPENEX_KEY");
        this.url = PREFIX_URL + apiKey;
    }
    public void update() {
        this.fetchResponse();
        Map<String, Double> currencyHashMap = this.mapResponseCurrencies();
        SupportedCurrencies.updateCurrenciesFromMap(currencyHashMap);
    }
    public void fetchResponse() {
        StringBuilder responseJson = new StringBuilder();
        HttpsURLConnection urlConnection = null;
        BufferedReader inputReader = null;
        try {
            urlConnection = (HttpsURLConnection) new URI(this.url).toURL().openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();
            inputReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            String jsonLine;
            while ((jsonLine = inputReader.readLine()) != null) {
                responseJson.append(jsonLine);
            }
        } catch (URISyntaxException | IOException ex) {
            throw new RuntimeException(ex);
        } finally {
            if (responseJson.isEmpty()) {
                this.response = "Error: No data received from API";
                System.out.println("Error: No data received from API");
            } else {
                this.response = responseJson.toString();
            }
            disconnect(inputReader, urlConnection);
        }
    }
    public String getReadableResponse() {
        StringBuilder responseJson = new StringBuilder(this.response);
        responseJson.delete(0, responseJson.indexOf(": {") + 1);
        String jsonString = responseJson.toString().replace("{", "")
                                                   .replace("}", "")
                                                   .replace("\"", "")
                                                   .replace(",", "\n")
                                                   .replace(":", " : ")
                                                   .replace("     ", "    ")
                                                   .replace("    ", "");
        return jsonString;
    }
    public Map<String, Double> mapResponseCurrencies() {
        String[] keyValue = this.getReadableResponse().split("\n");
        Map<String, Double> currencyHashMap = new HashMap<>();
        for (int i = 0; i < keyValue.length; i++) {
            String[] singleCurrency = keyValue[i].split(" : ");
            if (Character.isAlphabetic(singleCurrency[0].charAt(0))) {
                String currency = singleCurrency[0].trim();
                Double value = Double.valueOf(String.format("%.2f",
                        Double.parseDouble(singleCurrency[1].trim())));
                if (value < 0.01) {
                    value = Double.valueOf(String.format("%.3f",
                            Double.parseDouble(singleCurrency[1].trim())));
                }
                currencyHashMap.put(currency, value);
            }
        }
        return currencyHashMap;
    }

    public static void disconnect(BufferedReader inputReader, HttpsURLConnection urlConnection) {
        if (inputReader != null) {
            try {
                inputReader.close();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
        if (urlConnection != null) {
            urlConnection.disconnect();
        }
    }
}
