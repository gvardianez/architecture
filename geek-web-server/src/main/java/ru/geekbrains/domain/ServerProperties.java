package ru.geekbrains.domain;

public class ServerProperties {

    private final int Port;

    private final String WWW;

    public ServerProperties(int port, String WWW) {
        Port = port;
        this.WWW = WWW;
    }

    public int getPort() {
        return Port;
    }

    public String getWWW() {
        return WWW;
    }
}
