package ru.geekbrains.domain;

import ru.geekbrains.response.ResponseStatus;

import java.util.Map;

public class HttpResponse {

    private ResponseStatus statusCode;

    private String httpVersion;

    private Map<String, String> headers;

    private String body;

    public HttpResponse() {
    }

    public String getHttpVersion() {
        return httpVersion;
    }

    public void setHttpVersion(String httpVersion) {
        this.httpVersion = httpVersion;
    }

    public ResponseStatus getStatusCode() {
        return statusCode;
    }

    public void setStatusCode( ResponseStatus statusCode) {
        this.statusCode = statusCode;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public void setHeaders(Map<String, String> headers) {
        this.headers = headers;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
