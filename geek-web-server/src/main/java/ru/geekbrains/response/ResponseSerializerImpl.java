package ru.geekbrains.response;

import ru.geekbrains.domain.HttpResponse;

public class ResponseSerializerImpl implements ResponseSerializer {

    private final static String NEW_LINE = "\r\n";

    @Override
    public String serialize(HttpResponse response) {
        StringBuilder sb = new StringBuilder();

        sb.append(response.getHttpVersion())
                .append(" ")
                .append(response.getStatusCode().getCode())
                .append(" ")
                .append(response.getStatusCode().toString())
                .append(NEW_LINE);

        response.getHeaders().forEach((s, s2) -> sb.append(s)
                .append(": ")
                .append(s2)
                .append(NEW_LINE));

        sb.append(NEW_LINE)
                .append(response.getBody());

        return sb.toString();
    }
}
