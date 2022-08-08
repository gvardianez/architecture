package ru.geekbrains;

import ru.geekbrains.domain.ServerProperties;

public class Launch {

    private static final String SERVER_PROPERTIES_PATH = "geek-web-server/src/main/resources/server-config.properties";

    private static final String RESPONSE_PROPERTIES_PATH  = "geek-web-server/src/main/resources/response-config.properties";

    public static void main(String[] args) {
        ServerPropertiesReader serverPropertiesReader = new ServerPropertiesReaderImpl(SERVER_PROPERTIES_PATH);
        ServerProperties serverProperties = serverPropertiesReader.readProperties();
        new WebServer(serverProperties.getWWW(), serverProperties.getPort(), RESPONSE_PROPERTIES_PATH).start();
    }

}
