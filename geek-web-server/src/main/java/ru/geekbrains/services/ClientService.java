package ru.geekbrains.services;

import ru.geekbrains.domain.HttpRequest;
import ru.geekbrains.request.RequestHandler;
import ru.geekbrains.request.RequestHandlerImpl;
import ru.geekbrains.response.ResponseBuilder;
import ru.geekbrains.response.ResponseBuilderImpl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class ClientService implements Runnable {

    private final Socket socket;

    private final String folder;

    private final String responsePropertiesPath;

    public ClientService(Socket socket, String folder, String responsePropertiesPath) {
        this.socket = socket;
        this.folder = folder;
        this.responsePropertiesPath = responsePropertiesPath;
    }

    @Override
    public void run() {
        try (BufferedReader input = new BufferedReader(
                new InputStreamReader(
                        socket.getInputStream(), StandardCharsets.UTF_8));
             PrintWriter output = new PrintWriter(socket.getOutputStream())) {
            RequestHandler requestHandler = new RequestHandlerImpl(input);
            HttpRequest httpRequest = requestHandler.readRequest();
            FileService fileService = new FileServiceImpl(folder);
            ResponseBuilder responseBuilder = new ResponseBuilderImpl(fileService, httpRequest, responsePropertiesPath);
            String response = responseBuilder.buildResponse();
            output.print(response);
            System.out.println("Client disconnected!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
