package ru.geekbrains;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class WebServer {

    private final String WWW;

    private final int port;

    public WebServer(String www, int port) {
        WWW = www;
        this.port = port;
    }

    public void start() {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Server started!");

            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("New client connected!");
                new Thread(new ClientHandler(socket, WWW)).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
