package pl.gotowala.server;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import pl.gotowala._model.CurrencyType;
import pl.gotowala.graphics.JavaFx;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

public final class NbpApiConnector {

    private JsonArray rates;

    private NbpApiConnector() {
    }

    /**
     * Connecting with NBP API and get currencyType currency information with startDate and endDate range as JsonArray.
     *
     * @return JsonArray with information about currency.
     */
    public static JsonArray getExchangeRatesFromLink(String startDate, String endDate, CurrencyType currencyType) throws IOException {

        String link = "http://api.nbp.pl/api/exchangerates/rates/c/" + currencyType + "/" + startDate + "/" + endDate + "/?format=json";
        URL url = new URL(link);
        URLConnection connection = url.openConnection();
        connection.connect();

        try {
            InputStream is = connection.getInputStream();
            Scanner scanner = new Scanner(is);
            String allOfRate = scanner.nextLine();
            Gson gson1 = new Gson();
            is.close();
            return (gson1.fromJson(allOfRate, NbpApiConnector.class).rates);

        } catch (FileNotFoundException e) {
            JavaFx.showAlertMessage("Wrong date range or date format");
            return new JsonArray();
        }
    }
}
