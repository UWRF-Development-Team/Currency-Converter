package org.falcon.model.deprecated;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;
import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.Map;
public class CurrencyValueWebAPI {
    StringBuilder printedData;
    private double dollarsValueFromExURL;
    private double eurosValueFromExURL;
    private double francsValueFromExURL;
    private double pesosValueFromExURL;
    private double poundsValueFromExURL;
    private double rublesValueFromExURL;
    double[] currencyValueArray;
    final String[] THREE_LETTER_CURRENCIES;
    // https://docs.openexchangerates.org/reference/supported-currencies
    /**
     * This constructor initializes the CurrencyValueWebAPI class.
     * The constructor calls the retrieveData() method to get the initial
     * values.
     */
    public CurrencyValueWebAPI() {
        this.printedData = new StringBuilder();
        this.THREE_LETTER_CURRENCIES = new String[]{"USD", "EUR", "CHF", "MXN", "RUB", "GBP"};
        this.dollarsValueFromExURL = 0.00;
        this.eurosValueFromExURL = 0.00;
        this.francsValueFromExURL = 0.00;
        this.pesosValueFromExURL = 0.00;
        this.poundsValueFromExURL = 0.00;
        this.rublesValueFromExURL = 0.00;
        this.currencyValueArray = new double[]{this.getDollarsValueFromExURL(),
                                               this.getEurosValueFromExURL(),
                                               this.getFrancsValueFromExURL(),
                                               this.getPesosValueFromExURL(),
                                               this.getPoundsValueFromExURL(),
                                               this.getRublesValueFromExURL()};
        this.retrieveData();

    }
    /**
     * This method returns an array of the currency values.
     * @return double[]
     */
    public double[] getCurrencyValueArray() {
        for (int i = 0; i < 6; i++) {
            switch (i) {
                case 0 -> {
                    this.setCurrencyValueArray(i, this.getDollarsValueFromExURL());
                }
                case 1 -> {
                    this.setCurrencyValueArray(i, this.getEurosValueFromExURL());
                }
                case 2 -> {
                    this.setCurrencyValueArray(i, this.getFrancsValueFromExURL());
                }
                case 3 -> {
                    this.setCurrencyValueArray(i, this.getPesosValueFromExURL());
                }
                case 4 -> {
                    this.setCurrencyValueArray(i, this.getPoundsValueFromExURL());
                }
                case 5 -> {
                    this.setCurrencyValueArray(i, this.getRublesValueFromExURL());
                }
            }
        }
        return this.currencyValueArray;
    }

    /**
     * This method retrieves data from the Open Exchange Rates API.
     * The data is then parsed and the values are updated.
     * The values are then stored in the CurrencyValue enum.
     */
    public void retrieveData() {
        try {
            //----------------------Get-Data----------------------------------
            String keyPath = "src/key.txt";

//            String apiKey = new FileLineRetriever(0,
//                    keyPath).getData();
            String apiKey = System.getenv("OPENEX_KEY");
            final String OPENEX_URL = "https://openexchangerates.org/api/" +
                                      "latest.json?app_id=" + apiKey;
            URL inputFromURL = new URL(OPENEX_URL);
            if (!inputFromURL.toString().equals(OPENEX_URL) ||
                    !inputFromURL.toExternalForm().equals(OPENEX_URL)) {
                System.out.println("Unexpected URL. Terminating...");
                System.exit(0);
            }
            HttpsURLConnection connection = (HttpsURLConnection) inputFromURL.openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(3000);
            connection.setReadTimeout(3000);
            if (connection.getResponseCode() != HttpURLConnection.HTTP_OK) {
                System.out.println("Unexpected connection status. Terminating...");
                connection.disconnect();
                System.exit(0);
            }
            InputStreamReader inputStreamReader = new InputStreamReader(connection.getInputStream());
            BufferedReader parser = new BufferedReader(inputStreamReader);
            String catchAndUseData = "";
            while ((catchAndUseData = parser.readLine()) != null) {
                this.addPrintedData(catchAndUseData);
            }
            System.out.println(this.getPrintedData());
            parser.close();
            connection.disconnect();
            //---------------------Parse-Data---------------------------------
            this.getPrintedDataObj().delete(0, this.getPrintedData().indexOf(": {") + 1);
            this.setPrintedData(this.getPrintedData().replace("{", "")
                                                     .replace("}", "")
                                                     .replace("\"", "")
                                                     .replace(",", "\n")
                                                     .replace(":", " : ")
                                                     .replace("     ", "    ")
                                                     .replace("    ", ""));
            String[] keyValue = this.getPrintedData().split("\n");
            HashMap<String, Double> currencyHashMap = new HashMap<>();
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
            //-------------------Update-Values--------------------------------
            for (Map.Entry<String, Double> mapValues : currencyHashMap.entrySet()) {
                switch (mapValues.getKey()) {
                    case "USD" -> {
                        this.setDollarsValueFromExURL(mapValues.getValue());
                    }
                    case "EUR" -> {
                        this.setEurosValueFromExURL(mapValues.getValue());
                    }
                    case "CHF" -> {
                        this.setFrancsValueFromExURL(mapValues.getValue());
                    }
                    case "MXN" -> {
                        this.setPesosValueFromExURL(mapValues.getValue());
                    }
                    case "RUB" -> {
                        this.setRublesValueFromExURL(mapValues.getValue());
                    }
                    case "GBP" -> {
                        this.setPoundsValueFromExURL(mapValues.getValue());
                    }
                }
            }
        } catch (MalformedURLException err) {
            err.printStackTrace();
        } catch (IOException err) {
            err.printStackTrace();
        }
    }
    //-----------------------------org.falcon.model.deprecated.Utilities----------------------------------
    public void setCurrencyValueArray(int index, double amount) {
        this.currencyValueArray[index] = amount;
    }
    public StringBuilder getPrintedDataObj() {
        return this.printedData;
    }
    public String getPrintedData() {
        return this.printedData.toString();
    }
    public void setPrintedData(String printedData) {
        this.getPrintedDataObj().delete(0, this.getPrintedDataObj().length());
        this.getPrintedDataObj().append(printedData);
    }
    public void addPrintedData(String token) {
        this.printedData.append(token);
    }
    //------------------------------Getters-----------------------------------
    public String[] getTHREE_LETTER_CURRENCIES() {
        return this.THREE_LETTER_CURRENCIES;
    }
    public double getDollarsValueFromExURL() {
        return this.dollarsValueFromExURL;
    }
    public double getEurosValueFromExURL() {
        return this.eurosValueFromExURL;
    }
    public double getFrancsValueFromExURL() {
        return this.francsValueFromExURL;
    }
    public double getPesosValueFromExURL() {
        return this.pesosValueFromExURL;
    }
    public double getPoundsValueFromExURL() {
        return this.poundsValueFromExURL;
    }
    public double getRublesValueFromExURL() {
        return this.rublesValueFromExURL;
    }
    //------------------------------Setters-----------------------------------
    public void setDollarsValueFromExURL(double dollarsValueFromExURL) {
        this.dollarsValueFromExURL = dollarsValueFromExURL;
    }

    public void setEurosValueFromExURL(double eurosValueFromExURL) {
        this.eurosValueFromExURL = eurosValueFromExURL;
    }

    public void setFrancsValueFromExURL(double francsValueFromExURL) {
        this.francsValueFromExURL = francsValueFromExURL;
    }

    public void setPesosValueFromExURL(double pesosValueFromExURL) {
        this.pesosValueFromExURL = pesosValueFromExURL;
    }

    public void setPoundsValueFromExURL(double poundsValueFromExURL) {
        this.poundsValueFromExURL = poundsValueFromExURL;
    }

    public void setRublesValueFromExURL(double rublesValueFromExURL) {
        this.rublesValueFromExURL = rublesValueFromExURL;
    }
    //-------------------------Enum-CurrencyValue-----------------------------
    /**
     * This enum stores the values of the currencies.
     * The values are updated by the CurrencyValueWebAPI class.
     */
    public enum CurrencyValue {
    DOLLAR(new CurrencyValueWebAPI().getDollarsValueFromExURL()),
    EURO(new CurrencyValueWebAPI().getEurosValueFromExURL()),
    FRANC(new CurrencyValueWebAPI().getFrancsValueFromExURL()),
    PESO(new CurrencyValueWebAPI().getPesosValueFromExURL()),
    POUND(new CurrencyValueWebAPI().getPoundsValueFromExURL()),
    RUBLE(new CurrencyValueWebAPI().getRublesValueFromExURL());
    final double value;
    CurrencyValue(double value) {
        this.value = value;
    }
    public double getValue() {
        return this.value;
    }
}
//------------------------------------Main------------------------------------
    public static void main(String[] args) {
        CurrencyValueWebAPI currencyValueWebAPI = new CurrencyValueWebAPI();
        System.out.println(currencyValueWebAPI.getPrintedData());
        for (double value : currencyValueWebAPI.getCurrencyValueArray()) {
            System.out.println(value);
        }
    }
}