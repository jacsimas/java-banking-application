package org.example.Currencies;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.example.Abstraction.CurrencyApiSource;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;

public class CurrencyAPI implements CurrencyApiSource {

    private static final String url_str = System.getenv("CURRENCY_API");

    @Override
    public URL connectCurrencyApi() throws IOException {
        return new URL(url_str);
    }
}
