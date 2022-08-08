package ru.geekbrains.request;

import ru.geekbrains.domain.HttpRequest;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RequestHandlerImpl implements RequestHandler {

    private final BufferedReader input;

    public RequestHandlerImpl(BufferedReader input) {
        this.input = input;
    }

    @Override
    public HttpRequest readRequest() {
        try {
            while (!input.ready()) ;

            String line;
            List<String> requestLines = new ArrayList<>();

            while (input.ready()) {
                line = input.readLine();
                System.out.println(line);
                requestLines.add(line);
            }

            RequestParser requestParser = new RequestParserImpl(requestLines);

            return requestParser.parseRequest();
        } catch (IOException ex) {
            throw new IllegalStateException(ex);
        }
    }

}
