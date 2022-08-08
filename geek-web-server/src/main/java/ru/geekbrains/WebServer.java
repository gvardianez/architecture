package ru.geekbrains;

import ru.geekbrains.services.ClientService;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class WebServer {

    private final String WWW;

    private final int port;

    private final String responsePropertiesPath;

    public WebServer(String www, int port, String responsePropertiesPath) {
        WWW = www;
        this.port = port;
        this.responsePropertiesPath = responsePropertiesPath;
    }

    public void start() {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Server started!");

            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("New client connected!");
                new Thread(new ClientService(socket, WWW, responsePropertiesPath)).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
