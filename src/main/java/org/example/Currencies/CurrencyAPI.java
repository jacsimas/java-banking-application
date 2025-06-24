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

    public CurrencyAPI() throws IOException {
    }


    public HashMap currencyGetter() throws IOException {
        String url_str = "https://v6.exchangerate-api.com/v6/79f34354ceb4dc1276fecca5/latest/USD";


        // Making Request
        URL url = new URL(url_str);
        HttpURLConnection request = (HttpURLConnection) url.openConnection();
        request.connect();

// Convert to JSON
        JsonParser jp = new JsonParser();
        JsonElement root = jp.parse(new InputStreamReader((InputStream) request.getContent()));
        JsonObject jsonobj = root.getAsJsonObject();

        // Accessing object
        String req_result = jsonobj.get("result").getAsString();
//        double req_eur =  jsonobj.get("EUR").getAsDouble();
        JsonObject conversion_rates = jsonobj.getAsJsonObject("conversion_rates");
        double usd = conversion_rates.get("USD").getAsDouble();
      double eur =  conversion_rates.get("EUR").getAsDouble();
        String req_timedate = jsonobj.get("time_last_update_utc").getAsString();
        System.out.println(req_result);
        System.out.println(req_timedate);

      //  for (int i = 0; i < ?; i++) { }

        return new Gson().fromJson(conversion_rates, HashMap.class);

        // System.out.println("USD: " + usd);
       // System.out.println("USD to EUR: " + eur);

        /* TODO: fetch all the currencies, into a currency arrayList I believe, use it to do currency exchange
         calculations and make a feature for users to choose if they want to keep their money in different
         currencies
         USE:
         https://www.exchangerate-api.com/docs/java-currency-api
         */
    }

}
