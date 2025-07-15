package org.example.Currencies;

import org.example.Abstraction.CurrencyApiSource;
import java.io.IOException;
import java.net.URL;

public class CurrencyAPI implements CurrencyApiSource {

    private static final String url_str = System.getenv("CURRENCY_API");

    @Override
    public URL connectCurrencyApi() throws IOException {
        return new URL(url_str);
    }
}
