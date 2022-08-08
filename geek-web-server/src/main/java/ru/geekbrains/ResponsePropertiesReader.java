package ru.geekbrains;

import java.util.Map;

public interface ResponsePropertiesReader {

    String getHttpVersion();
    Map<String, String> getResponseHeaders();

}
