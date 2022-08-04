package ru.geekbrains;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class PropertiesReader {

    private String WWW;
    private int port;
    private final String propertiesPath;

    public PropertiesReader(String propertiesPath) {
        this.propertiesPath = propertiesPath;
    }

    public String getWWW() {
        return WWW;
    }

    public int getPort() {
        return port;
    }
    public void readProperties(){
        try (FileInputStream fis = new FileInputStream(propertiesPath)) {
            Properties properties = new Properties();
            properties.load(fis);

            WWW = properties.getProperty("server.www");
            port = Integer.parseInt(properties.getProperty("server.port"));

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}
