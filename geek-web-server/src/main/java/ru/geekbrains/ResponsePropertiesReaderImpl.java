package ru.geekbrains;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class ResponsePropertiesReaderImpl implements ResponsePropertiesReader {

    private final String propertiesPath;
    private final Properties properties;

    public ResponsePropertiesReaderImpl(String propertiesPath) {
        this.propertiesPath = propertiesPath;
        properties = new Properties();
    }

    @Override
    public String getHttpVersion() {
        try (FileInputStream fis = new FileInputStream(propertiesPath)) {
            properties.load(fis);
            return properties.getProperty("response.httpVersion");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public Map<String, String> getResponseHeaders() {
        try (FileInputStream fis = new FileInputStream(propertiesPath)) {
            properties.load(fis);

            String delimiter = properties.getProperty("response.headersDelimiter");
            String header = properties.getProperty("response.headers");

            Map<String, String> headers = new HashMap<>();

            for (String s : header.split(delimiter)) {
                headers.put(s.split(":", 2)[0], s.split(":", 2)[1].trim());
            }

            return Collections.unmodifiableMap(headers);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }


}
