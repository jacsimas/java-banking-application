package org.example.Currencies;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;

public class CurrencyAPI {


    public HashMap getCurrencies() throws IOException {
        final String url_str = "https://v6.exchangerate-api.com/v6/79f34354ceb4dc1276fecca5/latest/USD";


        URL url = new URL(url_str);
        HttpURLConnection request = (HttpURLConnection) url.openConnection();
        request.connect();


        JsonParser jp = new JsonParser();
        JsonElement root = jp.parse(new InputStreamReader((InputStream) request.getContent()));
        JsonObject jsonobj = root.getAsJsonObject();

        String req_result = jsonobj.get("result").getAsString();

        JsonObject conversion_rates = jsonobj.getAsJsonObject("conversion_rates");
        double usd = conversion_rates.get("USD").getAsDouble();
      double eur =  conversion_rates.get("EUR").getAsDouble();
        String req_timedate = jsonobj.get("time_last_update_utc").getAsString();
        System.out.println(req_result);
        System.out.println(req_timedate);

        return new Gson().fromJson(conversion_rates, HashMap.class);

        /* TODO: fetch all the currencies, into a currency arrayList I believe, use it to do currency exchange
         calculations and make a feature for users to choose if they want to keep their money in different
         currencies
         USE:
         https://www.exchangerate-api.com/docs/java-currency-api
         */
    }

}
