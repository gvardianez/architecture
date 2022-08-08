package ru.geekbrains.request;

import ru.geekbrains.domain.HttpRequest;

import java.util.*;

public class RequestParserImpl implements RequestParser {

    private final List<String> requestLines;

    public RequestParserImpl(List<String> requestLines) {
        this.requestLines = requestLines;
    }

    @Override
    public HttpRequest parseRequest() {
        HttpRequest httpRequest = new HttpRequest();

        String firstLine = requestLines.remove(0);
        httpRequest.setMethod(firstLine.split(" ")[0]);
        httpRequest.setPath(firstLine.split(" ")[1]);

        Map<String, String> headers = Collections.unmodifiableMap(new HashMap<>(){{
            String header;
            String[] headerParts;
            while (!requestLines.get(0).equals("")) {
                header = requestLines.remove(0);
                headerParts = header.split(":", 2);
                put(headerParts[0], headerParts[1].trim());
            }
        }});

        requestLines.remove(0);

        StringBuilder sb = new StringBuilder();

        requestLines.forEach(line -> sb.append(line).append("\n"));

        String body = sb.toString();

        httpRequest.setHeaders(headers);
        httpRequest.setBody(body);

        return httpRequest;
    }

}
