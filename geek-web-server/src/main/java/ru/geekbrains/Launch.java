package ru.geekbrains;

public class Launch {

    public static void main(String[] args) {
        PropertiesReader propertiesReader = new PropertiesReader("geek-web-server/src/main/resources/server-config.properties");
        propertiesReader.readProperties();
        new WebServer(propertiesReader.getWWW(), propertiesReader.getPort()).start();

    }

}
