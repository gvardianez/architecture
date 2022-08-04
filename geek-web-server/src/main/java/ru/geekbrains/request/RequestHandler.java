package ru.geekbrains.request;

import java.io.BufferedReader;

public class RequestHandler {

    private final BufferedReader input;

    private String fileName;

    public RequestHandler(BufferedReader input) {
        this.input = input;
    }

    public String getFileName() {
        return fileName;
    }

    public void start() {
        RequestParser requestParser = new RequestParser();
        requestParser.parseRequest(input);
        requestParser.getRequestLines().forEach(System.out::println);
        fileName = requestParser.getFileName();

    }

}
