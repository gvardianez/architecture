package ru.geekbrains.request;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RequestParser {

    private final List<String> requestLines;
    private String fileName;

    public List<String> getRequestLines() {
        return requestLines;
    }

    public String getFileName() {
        return fileName;
    }

    public RequestParser() {
        requestLines = new ArrayList<>();
    }

    public void parseRequest(BufferedReader input) {
        try {
            while (!input.ready()) ;
            while (input.ready()) {
                requestLines.add(input.readLine());
            }
            fileName = requestLines.get(0).split(" ")[1];
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

}
