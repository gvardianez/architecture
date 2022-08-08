package ru.geekbrains;

import ru.geekbrains.domain.ServerProperties;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ServerPropertiesReaderImpl implements ServerPropertiesReader {

    private final String propertiesPath;

    public ServerPropertiesReaderImpl(String propertiesPath) {
        this.propertiesPath = propertiesPath;
    }

    @Override
    public ServerProperties readProperties() {
        try (FileInputStream fis = new FileInputStream(propertiesPath)) {
            Properties properties = new Properties();
            properties.load(fis);
            return new ServerProperties(Integer.parseInt(properties.getProperty("server.port")), properties.getProperty("server.www"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }


}
