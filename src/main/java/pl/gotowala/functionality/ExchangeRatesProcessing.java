package pl.gotowala.functionality;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import pl.gotowala._model.CurrencyType;
import pl.gotowala.graphics.JavaFx;
import pl.gotowala.server.NbpApiConnector;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ExchangeRatesProcessing {

    private static JsonArray allInfoAboutRates = new JsonArray();
    private String no;
    private String effectiveDate;
    private double bid;
    private double ask;

    /**
     * Connecting with NBP API and get currencyType currency information with startDate and endDate range.
     * Then putting that information in allInfoAboutRates's field as JsonArray.
     */
    public static void putExchangeRatesToFieldRates(String startDate, String endDate, CurrencyType currencyType) {
        try {
            allInfoAboutRates = NbpApiConnector.getExchangeRatesFromLink(startDate, endDate, currencyType);
        } catch (IOException e) {
            JavaFx.showAlertMessage("Connection error");
        }

    }

    /**
     * @return List of ask's rates from field allInfoAboutRates
     */
    public static List<Double> getAllAskRates() {
        Gson gson = new Gson();
        List<Double> askRates = new ArrayList<>();

        for (JsonElement element : allInfoAboutRates) {
            askRates.add(gson.fromJson(element, ExchangeRatesProcessing.class).ask);
        }
        return askRates;
    }

    /**
     * @return List of bid's rates from field allInfoAboutRates
     */
    public static List<Double> getAllBidRates() {
        Gson gson = new Gson();
        List<Double> bidRates = new ArrayList<>();

        for (JsonElement element : allInfoAboutRates) {
            bidRates.add(gson.fromJson(element, ExchangeRatesProcessing.class).bid);
        }
        return bidRates;
    }

    /**
     * @return Average of list elements
     */
    public static double getAverageOfRates(List<Double> ratesList) {
        if (ratesList.contains(null)) {
            return 0;
        }
        double ratesSum = 0;

        for (Double element : ratesList) {
            ratesSum += element;
        }
        return round(ratesSum / ratesList.size(), 4);
    }

    /**
     * @return Standard Deviation of list elements
     */
    public static double getStandardDeviationOfRates(List<Double> ratesList) {
        if (ratesList.contains(null)) {
            return 0;
        }
        double average = getAverageOfRates(ratesList);
        double std = 0;

        for (Double element : ratesList) {
            std += (element - average) * (element - average);
        }
        std /= ratesList.size();

        return round(Math.sqrt(std), 4);
    }

    /**
     * @param value  double number's to rounding
     * @param places place to which want rounding
     * @return rounded value
     */
    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        long factor = (long) Math.pow(10, places);
        value = value * factor;
        long tmp = Math.round(value);
        return (double) tmp / factor;
    }
}

