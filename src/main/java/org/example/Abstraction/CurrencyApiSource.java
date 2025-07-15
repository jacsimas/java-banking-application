package org.example.Abstraction;

import java.io.IOException;
import java.net.URL;

public interface CurrencyApiSource {

    URL connectCurrencyApi() throws IOException;
}
